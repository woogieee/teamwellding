package com.icia.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.web.model.EmailDTO;
import com.icia.web.model.Response;
import com.icia.web.model.WDUser;
import com.icia.web.service.EmailService;
import com.icia.web.service.WDUserService;

@Controller
//@RequestMapping("/board/*")
public class EmailController 
{
	@Inject
	EmailService emailService;
	
	@Autowired
	private WDUserService wduserService;
	
	@RequestMapping("/board/send.do")
	public String send(EmailDTO dto, Model model)
	{
		try
		{
			emailService.sendMail(dto); //이메일발송
			model.addAttribute("message", "이메일이 발송되었습니다.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("message", "이메일이 발송실패,,");
		}
		
		return "/board/gosu";
	}
	
	@RequestMapping("/board/check.do")
	@ResponseBody
	public Response<Object> emailCheck(EmailDTO dto, Model model){
		
		Response<Object> ajaxResponse = new Response<Object>();
		
		WDUser wdUser = new WDUser();
		
		int uCheck = 0;
		
		System.out.println("==============================================");
		System.out.println("EamilDTO : " + dto );
		System.out.println("==============================================");
		
        char[] tmp = new char[10];
			for(int i=0; i<tmp.length; i++) {
				int div = (int) Math.floor( Math.random() * 2 );
				
				if(div == 0) { // 0이면 숫자로
					tmp[i] = (char) (Math.random() * 10 + '0') ;
				}else { //1이면 알파벳
					tmp[i] = (char) (Math.random() * 26 + 'A') ;
				}
			}
		String ran = new String(tmp);
		
		wdUser.setUserId(ran);
		wdUser.setUserNickname(ran);
		wdUser.setUserEmail(ran);
		
		try
		{
			uCheck = emailService.checkMail(dto); //이메일발송
			wdUser.setuCheck(uCheck);
			wduserService.checkInsert(wdUser);
			ajaxResponse.setResponse(0, "Success");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ajaxResponse.setResponse(-1, "Fail");
		}
		
		return ajaxResponse;
	}
	
	
	
}
