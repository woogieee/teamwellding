package com.icia.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.common.util.StringUtil;
import com.icia.web.model.Response;
import com.icia.web.model.WDAdmin;
import com.icia.web.model.WDUser;
import com.icia.web.service.WDAdminService;
import com.icia.web.util.CookieUtil;
import com.icia.web.util.HttpUtil;
import com.icia.web.util.JsonUtil;

@Controller("wdAdminIndexController")
public class WDAdminIndexController 
{
	private static Logger logger = LoggerFactory.getLogger(WDAdminIndexController.class);
	
	@Autowired
	private WDAdminService wdAdminService;
	
	//쿠키명
	@Value("#{env['auth.cookie.name']}")
	private String AUTH_COOKIE_NAME;
	
	@RequestMapping(value="/mng/login") ///manager/index =>>로그인페이지!!
	@ResponseBody
	public Response<Object> mngIndex(HttpServletRequest request, HttpServletResponse response)
	{
		Response<Object> ajaxResponse = new Response<Object>();
		String adminId = HttpUtil.get(request, "userId");
		String admPwd = HttpUtil.get(request, "userPwd");
		System.out.println("탓어용~~~~~~~~~~~~~~");
		if(!StringUtil.isEmpty(adminId) && !StringUtil.isEmpty(admPwd)) 
		{
			WDAdmin admin = wdAdminService.wdAdminSelect(adminId);
			
			if(admin != null)
			{
				if(StringUtil.equals(admin.getAdmPwd(), admPwd))
				{
					if(StringUtil.equals(admin.getStatus(), "Y"))
					{
						CookieUtil.addCookie(response, "/", -1, AUTH_COOKIE_NAME, CookieUtil.stringToHex(adminId));
						System.out.println("쿠키"+CookieUtil.stringToHex(adminId));
						ajaxResponse.setResponse(0, "Success"); // 로그인 성공
					}
					else
					{
						ajaxResponse.setResponse(403, "Not Found"); // 정지된 아이디!
					}
				}
				else
				{
					ajaxResponse.setResponse(-1, "Passwords do not match"); // 비밀번호 불일치
				}
			}
			else
			{
				ajaxResponse.setResponse(404, "Not Found"); // 사용자 정보 없음 (Not Found)
			}
		}
		else
		{
			ajaxResponse.setResponse(400, "Bad Request"); // 파라미터가 올바르지 않음 (Bad Request)
		}
		
		if(logger.isDebugEnabled())
		{
			logger.debug("[WDAdminIndexController]/mng/login \n" + JsonUtil.toJsonPretty(ajaxResponse));
		}
		
		return ajaxResponse;
	}
	
	@RequestMapping(value="/mng/userList")
	public String userList(Model model,HttpServletRequest request, HttpServletResponse response)
	{
		
		
		return "/mng/userList";
	}
}
