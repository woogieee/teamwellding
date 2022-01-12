package com.icia.web.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.common.util.StringUtil;
import com.icia.web.model.Response;
import com.icia.web.model.WDRez;
import com.icia.web.model.WDUser;
import com.icia.web.service.WDDressService;
import com.icia.web.service.WDHallService;
import com.icia.web.service.WDMakeUpService;
import com.icia.web.service.WDRezService;
import com.icia.web.service.WDStudioService;
import com.icia.web.service.WDUserService;
import com.icia.web.util.CookieUtil;
import com.icia.web.util.HttpUtil;

@Controller("wdRezController")
public class WDRezController {
	
	private static Logger logger = LoggerFactory.getLogger(WDRezController.class);

		//쿠키명
		@Value("#{env['auth.cookie.name']}")
		private String AUTH_COOKIE_NAME;
		
		//유저서비스
		@Autowired
		private WDUserService wdUserService;
		
		//홀 서비스
		@Autowired
		private WDHallService wdHallService;
		
		//스튜디오 서비스
		private WDStudioService wdStudioService;
		
		//메이크업 서비스
		@Autowired
		private WDMakeUpService wdMakeUpService;
		
		//드레스 서비스
		@Autowired
		private WDDressService wdDressService;
		
		@Autowired
		private WDRezService wdRezService;
	
	/*@RequestMapping(value="/cart")
	public String cart(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
				
		WDRez wdRez = null;
		
		if(!StringUtil.isEmpty(cookieUserId))
		{	
			WDUser wdUser = wdUserService.userSelect(cookieUserId);
			if(StringUtil.equals(wdUser.getStatus(), "Y")) 
			{
				wdRez.setUserId(cookieUserId);
				wdRez = wdRezService.rezList(wdRez);
				model.addAttribute("wdUser", wdUser);
			}
		}
		
		model.addAttribute("wdRez", wdRez);
		
		return "/cart";
	}*/
	
	
	@RequestMapping(value="/user/wishlist")
	public String wishlist(ModelMap model, HttpServletRequest request, HttpServletResponse response) 
	{
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		
		WDUser wdUser = wdUserService.userSelect(cookieUserId);
		
		if(wdUser != null) 
		{
			if(StringUtil.equals(wdUser.getStatus(), "Y")) 
			{
				WDRez search = new WDRez();
				//search = wdRezService.rezSelect(wdUser.getUserId());
				
				search.setUserId(wdUser.getUserId());
				//결제상태가 N인 애들만 가져와야 하기 때문에 상태가 N인 애들만 검색할 것!
				search.setRezStatus("N");
				
				//유저 아이디와 N인 상태의 조건으로 검색한 결과를 wdRez에 담음
				WDRez wdRez = wdRezService.rezSelect(search);
				
				//다시 해당 결과를 가지고 다른 테이블과 조인한 결과를 wdRez객체에 다시 담음
				wdRez = wdRezService.rezList(wdRez);
				
				
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
		
		return "/user/wishlist";
	}

	//장바구니에서 홀 제거
	@RequestMapping(value="/user/updateHproc", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> updateHproc(HttpServletRequest request, HttpServletResponse response)
	{
		Response<Object> ajaxResponse = new Response<Object>();
		
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		String rezNo = HttpUtil.get(request, "rezNo", "");
		
		WDUser wdUser = wdUserService.userSelect(cookieUserId);
		
		
		if(wdUser != null) 
		{
			if(rezNo != "" && rezNo != null) 
			{
				WDRez wdRez = new WDRez();
				
				wdRez.setUserId(wdUser.getUserId());
				wdRez.setRezNo(rezNo);
				
				if(wdRezService.rezHallUpdate(wdRez) > 0) 
				{
					ajaxResponse.setResponse(0, "Success");
				}
				else 
				{
					ajaxResponse.setResponse(501, "internal server error");
				}
			}
			else 
			{
				ajaxResponse.setResponse(502, "bad request");
			}
		}
		else 
		{
			ajaxResponse.setResponse(500, "not yours");
		}
		
		return ajaxResponse;
	}
	
	
	//장바구니에서 스튜디오 제거
	@RequestMapping(value="/user/updateSproc", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> updateSproc(HttpServletRequest request, HttpServletResponse response)
	{
		Response<Object> ajaxResponse = new Response<Object>();
		
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		String rezNo = HttpUtil.get(request, "rezNo", "");
		
		WDUser wdUser = wdUserService.userSelect(cookieUserId);
		
		
		if(wdUser != null) 
		{
			if(rezNo != "" && rezNo != null) 
			{
				WDRez wdRez = new WDRez();
				
				wdRez.setUserId(wdUser.getUserId());
				wdRez.setRezNo(rezNo);
				
				if(wdRezService.rezStudioUpdate(wdRez) > 0) 
				{
					ajaxResponse.setResponse(0, "Success");
				}
				else 
				{
					ajaxResponse.setResponse(501, "internal server error");
				}
			}
			else 
			{
				ajaxResponse.setResponse(502, "bad request");
			}
		}
		else 
		{
			ajaxResponse.setResponse(500, "not yours");
		}
		
		return ajaxResponse;
	}
	
	
	//장바구니에서 드레스 제거
	@RequestMapping(value="/user/updateDproc", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> updateDproc(HttpServletRequest request, HttpServletResponse response)
	{
		Response<Object> ajaxResponse = new Response<Object>();
		
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		String rezNo = HttpUtil.get(request, "rezNo", "");
		
		WDUser wdUser = wdUserService.userSelect(cookieUserId);
		
		
		if(wdUser != null) 
		{
			if(rezNo != "" && rezNo != null) 
			{
				WDRez wdRez = new WDRez();
				
				wdRez.setUserId(wdUser.getUserId());
				wdRez.setRezNo(rezNo);
				
				if(wdRezService.rezDressUpdate(wdRez) > 0) 
				{
					ajaxResponse.setResponse(0, "Success");
				}
				else 
				{
					ajaxResponse.setResponse(501, "internal server error");
				}
			}
			else 
			{
				ajaxResponse.setResponse(502, "bad request");
			}
		}
		else 
		{
			ajaxResponse.setResponse(500, "not yours");
		}
		
		return ajaxResponse;		
	}
	
	//장바구니에서 메이크업 제거
	@RequestMapping(value="/user/updateMproc", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> updateMproc(HttpServletRequest request, HttpServletResponse response)
	{
		Response<Object> ajaxResponse = new Response<Object>();
		
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		String rezNo = HttpUtil.get(request, "rezNo", "");
		
		WDUser wdUser = wdUserService.userSelect(cookieUserId);
		
		
		if(wdUser != null) 
		{
			if(rezNo != "" && rezNo != null) 
			{
				WDRez wdRez = new WDRez();
				
				wdRez.setUserId(wdUser.getUserId());
				wdRez.setRezNo(rezNo);
				
				if(wdRezService.rezMakeupUpdate(wdRez) > 0) 
				{
					ajaxResponse.setResponse(0, "Success");
				}
				else 
				{
					ajaxResponse.setResponse(501, "internal server error");
				}
			}
			else 
			{
				ajaxResponse.setResponse(502, "bad request");
			}
		}
		else 
		{
			ajaxResponse.setResponse(500, "not yours");
		}
		
		return ajaxResponse;		
	}
	
	//결제 취소 리스트(관리자) 
	@RequestMapping(value="/user/cancelList")
	public String cancelPayment(ModelMap model, HttpServletRequest request, HttpServletResponse response) 
	{
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		List<WDRez> wdRez = null;
		WDUser wdUser = wdUserService.userSelect(cookieUserId);
		
		if(wdUser != null) 
		{
			if(StringUtil.equals(wdUser.getStatus(), "Y")) 
			{
				WDRez search = new WDRez();
				//search = wdRezService.rezSelect(wdUser.getUserId());
				
				search.setUserId(wdUser.getUserId());
				
				//유저 아이디와 Y인 상태의 조건과 결제 상태 C인 상태만 가져옴
				wdRez = wdRezService.rezCancelSelect(search);
					
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
		
		return "/user/cancelList";
	}
	
}
