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

import com.icia.web.model.Response;
import com.icia.web.service.WDAdminService;
import com.icia.web.util.CookieUtil;

@Controller("wdAdminIndexController")
public class WDAdminIndexController 
{
	private static Logger logger = LoggerFactory.getLogger(WDAdminIndexController.class);
	
	@Autowired
	private WDAdminService wdAdminService;
	
	//쿠키명
	@Value("#{env['auth.cookie.name']}")
	private String AUTH_COOKIE_NAME;
	
	@RequestMapping(value="/manager/index") ///manager/index =>>로그인페이지!!
	@ResponseBody
	public Response<Object> mngIndex(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		if(CookieUtil.getCookie(request, AUTH_COOKIE_NAME) != null)
		{
			return "/admin/userList";
		}
		else
		{
			return "/manager/index";
		}
	}
}
