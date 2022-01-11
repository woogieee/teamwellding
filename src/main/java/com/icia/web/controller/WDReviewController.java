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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.icia.common.model.FileData;
import com.icia.common.util.StringUtil;
import com.icia.web.model.Paging;
import com.icia.web.model.Response;
import com.icia.web.model.WDBoardFile;
import com.icia.web.model.WDComment;
import com.icia.web.model.WDFBoard;
import com.icia.web.model.WDReview;
import com.icia.web.model.WDReviewFile;
import com.icia.web.model.WDUser;
import com.icia.web.service.WDReviewService;
import com.icia.web.service.WDUserService;
import com.icia.web.util.CookieUtil;
import com.icia.web.util.HttpUtil;

@Controller("WDReviewController")
public class WDReviewController {
	
	private static Logger logger= LoggerFactory.getLogger(WDReviewController.class);
	
   //쿠키명
   @Value("#{env['auth.cookie.name']}")
   private String AUTH_COOKIE_NAME;
   
   //파일저장경로
   @Value("#{env['upload.save.dir']}")
   private String UPLOAD_SAVE_DIR;
   
   @Autowired
   private WDReviewService wdReviewService;
   
   @Autowired
   private WDUserService wdUserService;
   
   private static final int LIST_COUNT = 10;    //한페이지의 게시물 수
   private static final int PAGE_COUNT = 5;      //페이징 수
   

   @RequestMapping(value = "/board/reviews")
   public String review(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
	   
		  //조회값
		  String searchValue = HttpUtil.get(request, "searchValue", "");
		  //현재페이지
		  long curPage = HttpUtil.get(request, "curPage", (long)1);
		  
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		WDUser wdUser = wdUserService.userSelect(cookieUserId);
		  
		  List<WDReview> list = null;
		  WDReview wdReview = new WDReview();
		  long totalCount = 0;
		  
		  Paging paging = null;
		  
		  if(!StringUtil.isEmpty(searchValue)) {
			  wdReview.setSearchValue(searchValue);
		  }
		  
		  totalCount = wdReviewService.ReviewListCount(wdReview);
		  
		logger.debug("[totalCount] = "+totalCount);
		
		if(totalCount > 0) {
			paging = new Paging("/board/reviews", totalCount, LIST_COUNT, PAGE_COUNT, curPage, "curPage");
			paging.addParam("searchValue",searchValue);
			paging.addParam("curPage", curPage);
			
			wdReview.setStartRow(paging.getStartRow());
			wdReview.setEndRow(paging.getEndRow());
			
			list = wdReviewService.ReviewList(wdReview);

		}
		model.addAttribute("list",list);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("curPage", curPage);
		model.addAttribute("paging", paging);
		model.addAttribute("wdUser", wdUser);
	   
	   return "/board/reviews";
   }
   @RequestMapping(value="/board/reviewWrite")
   @ResponseBody
   public Response<Object> rezCheck(HttpServletRequest request, HttpServletResponse response){
	   
	   Response<Object> ajaxResponse = new Response<Object>();
	   
		//쿠키 값
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		
		//글 쓰고 돌아갈 때 서치벨류 현재페이지 세팅이 필요함
		String searchValue = HttpUtil.get(request, "searchValue", "");
		long curPage = HttpUtil.get(request, "curPage", (long)1);	
		
		WDReview wdReview = null;
		wdReview = wdReviewService.rezCheck(cookieUserId);
		if(wdReview != null) {
			if(Integer.valueOf(wdReview.getToday()) >= Integer.valueOf(wdReview.getWDate())) {
				ajaxResponse.setResponse(0, "Success");
			}
			else {
				ajaxResponse.setResponse(401, "Bad Request");
			}			
		}
		else {
			ajaxResponse.setResponse(400, "No parameter");
		}
	   
	   return ajaxResponse;
	   
   }
   
   @RequestMapping(value = "/board/reviewWriteGo")
   public String reviewWrite(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
	   
	   //쿠키 값
	   String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
	 	
	   WDUser wdUser = wdUserService.userSelect(cookieUserId);
	   
	   model.addAttribute("wdUser",wdUser);	 		
	   
	   return "/board/reviewWriteGo";
   }
   
    //리뷰게시물 작성
	@RequestMapping(value="/board/reviewWriteProc", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> writeProc(MultipartHttpServletRequest request, HttpServletResponse response)
	{
		Response<Object> ajaxResponse = new Response<Object>();
		
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		String hiBbsTitle = HttpUtil.get(request, "hiBbsTitle", "");
		String hiBbsContent = HttpUtil.get(request, "hiBbsContent", "");
		
		WDReview wdReviewRez = null;
		wdReviewRez = wdReviewService.rezCheck(cookieUserId);		
		
		FileData fileData = HttpUtil.getFile(request, "hiBbsFile", UPLOAD_SAVE_DIR);
		
		if(!StringUtil.isEmpty(hiBbsTitle) && !StringUtil.isEmpty(hiBbsContent)) 
		{
			WDReview wdReview = new WDReview();
			wdReview.setUserId(cookieUserId);
			wdReview.setRTitle(hiBbsTitle);
			wdReview.setRContent(hiBbsContent);
			wdReview.setRezNo(wdReviewRez.getRezNo());
			wdReview.setWDate(wdReviewRez.getWDate());
			
			if(fileData != null && fileData.getFileSize() > 0) 
			{
				WDReviewFile wdReviewFile = new WDReviewFile();
				
				wdReviewFile.setrFileName(fileData.getFileName());
				wdReviewFile.setrFileOrgName(fileData.getFileOrgName());
				wdReviewFile.setrFileExt(fileData.getFileExt());
				wdReviewFile.setrFileSize(fileData.getFileSize());
				
				wdReview.setReviewFile(wdReviewFile);
			}
		
			try 
			{
				if(wdReviewService.reviewInsert(wdReview) > 0) 
				{
					ajaxResponse.setResponse(0, "Success");
				}
				else 
				{
					ajaxResponse.setResponse(500, "Internal Server Error");
				}
			}
			catch(Exception e) 
			{
				logger.error("[WDReviewController] /board/reviewWriteProc Exception", e);
				ajaxResponse.setResponse(500, "Internal Server Error");
			}
		
		}
		else 
		{
			ajaxResponse.setResponse(400, "Bad Request: No parameter");
		}
		
		return ajaxResponse;
	}
	
	//상세 뷰페이지
	@RequestMapping(value="/board/reviewInfo")
	public String reviewInfo(ModelMap model, HttpServletRequest request, HttpServletResponse response) 
	{
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		
		long RSeq = HttpUtil.get(request, "RSeq", (long)0);
		String searchValue = HttpUtil.get(request, "searchValue", "");
		long curPage = HttpUtil.get(request, "curPage", (long)1);
		
		String boardMe = "N";
		WDReview wdReview = null;
		
		if(RSeq > 0) 
		{
			wdReview = wdReviewService.ReviewSelect(RSeq);			
			
			if(wdReview != null && StringUtil.equals(wdReview.getUserId(), cookieUserId)) 
			{
				boardMe = "Y";
			}		
			
		}
		
		if(wdReview.getReviewFile() != null) 
		{
			String url = wdReview.getReviewFile().getrFileName();
			model.addAttribute("url", url);
		}
		
		WDUser wdUser = wdUserService.userSelect(cookieUserId);
		
		model.addAttribute("wdUser", wdUser);
		model.addAttribute("cookieUserId",cookieUserId);
		model.addAttribute("RSeq", RSeq);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("curPage", curPage);
		model.addAttribute("wdReview", wdReview);
		model.addAttribute("boardMe", boardMe);
		
		return "/board/reviewInfo";
	}

}
