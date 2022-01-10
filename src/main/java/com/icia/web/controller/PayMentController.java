package com.icia.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icia.common.util.StringUtil;
import com.icia.web.model.WDRez;
import com.icia.web.model.WDUser;
import com.icia.web.service.WDRezService;
import com.icia.web.service.WDUserService;
import com.icia.web.util.CookieUtil;

@Controller("payMentController")
public class PayMentController 
{
	private static Logger logger = LoggerFactory.getLogger(PayMentController.class);
	
	//쿠키명
	@Value("#{env['auth.cookie.name']}")
	private String AUTH_COOKIE_NAME;
   
	@Autowired
	private PayMentController payMentController;
	
	//유저서비스
	@Autowired
	private WDUserService wdUserService;
	
	//예약 서비스
	@Autowired
	private WDRezService wdRezService;
	
	
	@RequestMapping(value="/user/payMent")
	public String payMent(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		
		WDUser wdUser = wdUserService.userSelect(cookieUserId);
		
		WDRez wdRez = null;
		
		if(wdUser != null) 
		{
			if(StringUtil.equals(wdUser.getStatus(), "Y")) 
			{
				wdRez = wdRezService.rezList(wdUser.getUserId());
				model.addAttribute("wdRez", wdRez);
				model.addAttribute("wdUser",wdUser);
			}
			else 
			{
				return "/";
			}
		}
		else 
		{
			return "/";
		}
		
		return "/user/payMent";
		
		
	}
	
	@RequestMapping(value="/user/payComplete")
	public String payComplete(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		
		WDUser wdUser = wdUserService.userSelect(cookieUserId);
		
		WDRez wdRez = null;
		
		if(wdUser != null) 
		{
			if(StringUtil.equals(wdUser.getStatus(), "Y")) 
			{
				wdRez = wdRezService.rezList(wdUser.getUserId());
				model.addAttribute("wdRez", wdRez);
				model.addAttribute("wdUser",wdUser);
			}
			else 
			{
				return "/";
			}
		}
		else 
		{
			return "/";
		}
		
		return "/user/payComplete";
	}
}
