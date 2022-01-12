package com.icia.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.icia.common.util.StringUtil;
import com.icia.web.model.KakaoPayApprove;
import com.icia.web.model.KakaoPayOrder;
import com.icia.web.model.KakaoPayReady;
import com.icia.web.model.Response;
import com.icia.web.model.WDRez;
import com.icia.web.service.KakaoPayService;
import com.icia.web.service.WDRezService;
import com.icia.web.util.CookieUtil;
import com.icia.web.util.HttpUtil;

@Controller("kakaoPayController")
public class KakaoPayController 
{
   private static Logger logger = LoggerFactory.getLogger(KakaoPayController.class);
   
   @Autowired
   private KakaoPayService kakaoPayService;
   
   @Autowired
   private WDRezService wdRezService;
   
   // 쿠키명
   @Value("#{env['auth.cookie.name']}")
   private String AUTH_COOKIE_NAME;
   
   private static final String KAKAO_PAY_ORDER_NAME = "kakaPayOrderSession";
   
   @RequestMapping(value="/kakao/pay")
   public String pay(HttpServletRequest request, HttpServletResponse response)
   {
      return "/kakao/pay";
   }
   
   @RequestMapping(value="/kakao/payPopUp", method=RequestMethod.POST)
   public String payPopUp(ModelMap model, HttpServletRequest request, HttpServletResponse response)
   {
      String pcUrl = HttpUtil.get(request, "pcUrl", "");
      String orderId = HttpUtil.get(request, "orderId", "");
      String tId = HttpUtil.get(request, "tId", "");
      String userId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
      
      //동욱 추가
      String cCode = HttpUtil.get(request, "cCode", "");
      String rezNo = HttpUtil.get(request, "rezNo", "");
      int rezFullPrice = HttpUtil.get(request, "rezFullPrice", 0);
      
      
      model.addAttribute("pcUrl", pcUrl);
      model.addAttribute("orderId", orderId);
      model.addAttribute("tId", tId);
      model.addAttribute("userId", userId);
      
      //동욱 추가
      model.addAttribute("cCode", cCode);
      model.addAttribute("rezNo", rezNo);
      model.addAttribute("rezFullPrice", rezFullPrice);
      
      return "/kakao/payPopUp";
   }
   
   @RequestMapping(value="/kakao/payReady", method=RequestMethod.POST)
   @ResponseBody
   public Response<Object> payReady(HttpServletRequest request, HttpServletResponse response)
   {
      Response<Object> ajaxResponse = new Response<Object>();
      
      String orderId = StringUtil.uniqueValue();
      String userId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
      String itemCode = HttpUtil.get(request, "itemCode", "");
      String itemName = HttpUtil.get(request, "itemName", "");
      String cCode = HttpUtil.get(request, "cCode", "");
      int quantity = HttpUtil.get(request, "quantity", (int)0);
      int totalAmount = HttpUtil.get(request, "totalAmount", (int)0);
      int taxFreeAmount = HttpUtil.get(request, "taxFreeAmount", (int)0);
      int vatAmount = HttpUtil.get(request, "vatAmount", (int)0);

      KakaoPayOrder kakaoPayOrder = new KakaoPayOrder();
      
      kakaoPayOrder.setPartnerOrderId(orderId);
      kakaoPayOrder.setPartnerUserId(userId);
      kakaoPayOrder.setItemCode(itemCode);
      kakaoPayOrder.setItemName(itemName);
      kakaoPayOrder.setQuantity(quantity);
      kakaoPayOrder.setTotalAmount(totalAmount);
      kakaoPayOrder.setTaxFreeAmount(taxFreeAmount);
      kakaoPayOrder.setVatAmount(vatAmount);
      
      KakaoPayReady kakaoPayReady = kakaoPayService.kakaoPayReady(kakaoPayOrder);
      
      if(kakaoPayReady != null)
      {
         logger.debug("[KakaoPayController] payReady : " + kakaoPayReady);
                  
         kakaoPayOrder.settId(kakaoPayReady.getTid());
         
         JsonObject json = new JsonObject();
         
         json.addProperty("orderId", orderId);
         json.addProperty("tId", kakaoPayReady.getTid());
         json.addProperty("appUrl", kakaoPayReady.getNext_redirect_app_url());
         json.addProperty("mobileUrl", kakaoPayReady.getNext_redirect_mobile_url());
         json.addProperty("pcUrl", kakaoPayReady.getNext_redirect_pc_url());
         
         //동욱 추가
         json.addProperty("cCode", cCode);
         json.addProperty("rezNo", itemCode);
         json.addProperty("rezFullPrice", totalAmount);

         
         ajaxResponse.setResponse(0, "success", json); 
         
      }
      else
      {
         ajaxResponse.setResponse(-1, "fail", null);
      }
      
      return ajaxResponse;
   }
   
   @RequestMapping(value="/kakao/paySuccess")
   public String paySuccess(ModelMap model, HttpServletRequest request, HttpServletResponse response)
   {
      String pgToken = HttpUtil.get(request, "pg_token", "");
      
      model.addAttribute("pgToken", pgToken);
      
      return "/kakao/paySuccess";
   }
   
   @RequestMapping(value="/kakao/payResult")
   public String payResult(ModelMap model, HttpServletRequest request, HttpServletResponse response)
   {
      KakaoPayApprove kakaoPayApprove = null;
      
      String userId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
      String orderId = HttpUtil.get(request, "orderId", "");
      String tId = HttpUtil.get(request, "tId", "");
      String pgToken = HttpUtil.get(request, "pgToken", "");
      
      //동욱 추가
      String itemCode = HttpUtil.get(request, "itemCode", "");
      System.out.println("아이템코드" + itemCode);
      String cCode = HttpUtil.get(request, "cCode", "");
      int totalAmount = HttpUtil.get(request,  "totalAmount", (int)0);
      System.out.println("아이템코드" + totalAmount);
      
      String rezNo = HttpUtil.get(request, "rezNo", "");
      
      int rezFullPrice = HttpUtil.get(request, "rezFullPrice", 0);
      //여기까지
      
      KakaoPayOrder kakaoPayOrder = new KakaoPayOrder();
      
      kakaoPayOrder.setPartnerOrderId(orderId);
      kakaoPayOrder.setPartnerUserId(userId);
      kakaoPayOrder.settId(tId);
      kakaoPayOrder.setPgToken(pgToken);
      
      kakaoPayApprove = kakaoPayService.kakaoPayApprove(kakaoPayOrder);
      
      WDRez wdRez = new WDRez();
      wdRez.setUserId(userId);
      wdRez.setRezNo(itemCode);
      wdRez.setRezFullPrice(totalAmount);
      wdRez.setcCode(cCode);
      wdRez.setRezNo(rezNo);
      wdRez.setRezFullPrice(rezFullPrice);
      if(wdRezService.rezUpdatePay(wdRez) > 0) 
      {
     	 System.out.println("성공일까");    	 
      }
      
      model.addAttribute("kakaoPayApprove", kakaoPayApprove);
      
      System.out.println("쿠폰코드" + cCode);
      System.out.println("아이템코드" + rezNo);
      System.out.println("총금액" + rezFullPrice);
      
      return "/kakao/payResult";
   }
      
   @RequestMapping(value="/kakao/payFail")
   public String payFail(HttpServletRequest request, HttpServletResponse response)
   {
      HttpSession session = request.getSession(true);
      KakaoPayOrder kakaoPayOrder = (KakaoPayOrder)session.getAttribute(KAKAO_PAY_ORDER_NAME);
      
      if(kakaoPayOrder != null)
      {
         session.removeAttribute(KAKAO_PAY_ORDER_NAME);
      }
      
      return "/kakao/payFail";
   }
   
   @RequestMapping(value="/kakao/payCancel")
   public String payCancel(HttpServletRequest request, HttpServletResponse response)
   {
      HttpSession session = request.getSession(true);
      KakaoPayOrder kakaoPayOrder = (KakaoPayOrder)session.getAttribute(KAKAO_PAY_ORDER_NAME);
      
      if(kakaoPayOrder != null)
      {
         session.removeAttribute(KAKAO_PAY_ORDER_NAME);
      }
      
      return "/kakao/payCancel";
   }
}