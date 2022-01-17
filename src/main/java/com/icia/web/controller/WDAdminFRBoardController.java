package com.icia.web.controller;

import java.io.File;
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
import org.springframework.web.servlet.ModelAndView;

import com.icia.common.util.FileUtil;
import com.icia.common.util.StringUtil;
import com.icia.web.model.Paging;
import com.icia.web.model.Response;
import com.icia.web.model.WDAdmin;
import com.icia.web.model.WDBoardFile;
import com.icia.web.model.WDFBoard;
import com.icia.web.model.WDReview;
import com.icia.web.service.WDAdminService;
import com.icia.web.service.WDCommentService;
import com.icia.web.service.WDFBoardService;
import com.icia.web.service.WDReviewService;
import com.icia.web.util.CookieUtil;
import com.icia.web.util.HttpUtil;

@Controller("wdAdminFRBoardController")
public class WDAdminFRBoardController {
   private static Logger logger = LoggerFactory.getLogger(WDAdminFRBoardController.class);
   
   @Autowired
   private WDAdminService wdAdminService;
   
   @Autowired
   private WDFBoardService wdFBoardService;
   
   @Autowired
   private WDReviewService wdReviewService;

   @Autowired
   private WDCommentService wdCommentService;
   
   @Value("#{env['upload.save.dir']}")
   private String UPLOAD_SAVE_DIR;

   //쿠키명
   @Value("#{env['auth.cookie.name']}")
   private String AUTH_COOKIE_NAME;
   
   private static final int LIST_COUNT = 10;
   private static final int PAGE_COUNT = 5;
   
   @RequestMapping(value="/mng/boardList")
   public String AdminBoardList(ModelMap model,HttpServletRequest request, HttpServletResponse response) 
   {
	   //자유게시판, 리뷰게시판 페이징을 위한 개수 체크 변수
	   long fboardCnt = 0;
	   long rboardCnt = 0;
	   
	   //자유게시판 리스트를 조회할 때 쓸 객체 선언
	   WDFBoard wdFBoard = new WDFBoard();
	   WDReview wdReview = new WDReview();
	   
	   //결과가 보여질 배열 객체 선언
	   List<WDFBoard> fList = null;
	   List<WDReview> rList = null;
	   
	   //페이징 처리 어디서 했는지 확인용
	   int frCheck = HttpUtil.get(request, "frCheck", 1);
	   model.addAttribute("frCheck", frCheck);
	   
	   String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
	   WDAdmin wdAdmin = wdAdminService.wdAdminSelect(cookieUserId);
	   
	   //조회항목
	   String searchType = HttpUtil.get(request, "searchType", "");
	   //조회값
	   String searchValue = HttpUtil.get(request, "searchValue", "");
	   //현재 페이지
       long curPage = HttpUtil.get(request, "curPage", (long)1);
	   
       
       long bSeq = HttpUtil.get(request, "bSeq", (long)0);
       long rSeq = HttpUtil.get(request, "rSeq", (long)0);
       
       Paging fPaging = null;
       Paging rPaging = null;
       
       if(!StringUtil.isEmpty(searchType) && !StringUtil.isEmpty(searchValue)) 
		{
			//받아온 값
    	   wdFBoard.setSearchType(searchType);
    	   wdFBoard.setSearchValue(searchValue);
    	   wdReview.setSearchValue(searchValue);
		}
		else 
		{
			searchType = "";
			searchValue = "";
		}
       
       fboardCnt = wdFBoardService.fBoardListCount(wdFBoard);
       rboardCnt = wdReviewService.ReviewListCount(wdReview);
       
       //자유게시판 페이징
       if(fboardCnt > 0) 
       {
    	   fPaging = new Paging("/mng/boardList", fboardCnt, LIST_COUNT, PAGE_COUNT, curPage, "curPage");
    	   
    	   fPaging.addParam("searchType", searchType);
    	   fPaging.addParam("searchValue", searchValue);
    	   fPaging.addParam("curPage", curPage);
    	   
    	   wdFBoard.setStartRow(fPaging.getStartRow());
    	   wdFBoard.setEndRow(fPaging.getEndRow());
    	   
    	   fList = wdFBoardService.fBoardList(wdFBoard);
       }
       //리뷰게시판 페이징
       if(rboardCnt > 0) 
       {
    	   rPaging = new Paging("/mng/boardList", rboardCnt, LIST_COUNT, PAGE_COUNT, curPage, "curPage");
    	   
    	   //rPaging.addParam("searchType", searchType);
    	   rPaging.addParam("searchValue", searchValue);
    	   rPaging.addParam("curPage", curPage);
    	   
    	   wdReview.setStartRow(rPaging.getStartRow());
    	   wdReview.setEndRow(rPaging.getEndRow());
    	   
    	   fList = wdFBoardService.fBoardList(wdFBoard);
       }
       
       model.addAttribute("wdAdmin",wdAdmin);
       model.addAttribute("fList", fList);
       model.addAttribute("fPaging",fPaging);
       model.addAttribute("rList", rList);
       model.addAttribute("rPaging",rPaging);
       model.addAttribute("searchType", searchType);
       model.addAttribute("searchValue", searchValue);
       model.addAttribute("curPage", curPage);
       
       return "/mng/boardList";
   }
   
   
   @RequestMapping(value="/mng/mngFboardUpdate")
   public String MngFboardUpdate(ModelMap model,HttpServletRequest request, HttpServletResponse response) 
   {
	   long bSeq = HttpUtil.get(request, "bSeq", (long)0);
	   
	   WDFBoard wdFBoard = null;
	   
	   if(bSeq>0) 
	   {
		   wdFBoard = wdFBoardService.wdFBoardView(bSeq);
		   
		   if(wdFBoard != null) 
		   {
			   model.addAttribute("wdFBoard", wdFBoard);
		   }
	   }
	   
	   return "/mng/mngFboardUpdate";
   }
   
   
   
	
	
	//게시글 삭제
   	@RequestMapping(value="/mng/fDelete", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> fdelete(HttpServletRequest request, HttpServletResponse response)
	{
		Response<Object> ajaxResponse = new Response<Object>();
		
		long bSeq = HttpUtil.get(request, "bSeq", (long)0);
		
		System.out.println("bSeq"+ bSeq);
		
		
		if(bSeq > 0) 
		{
			WDFBoard wdFBoard = wdFBoardService.wdFBoardView(bSeq);
			if(wdFBoard != null) 
			{
				
					try 
					{
						if(wdFBoardService.fBoardDelete(bSeq) > 0) 
						{
							wdCommentService.commentBoardDelete(bSeq);
							ajaxResponse.setResponse(0, "Success");
						}
						else 
						{
							ajaxResponse.setResponse(500, "Internal Server Error");
						}
					}
					catch(Exception e) 
					{
						logger.error("[WDFBoardController] delete Exception", e);
						ajaxResponse.setResponse(500, "Internal Server Error");
					}

			}
			else 
			{
				ajaxResponse.setResponse(404, "Not Exist");				
			}
			
		}
		else 
		{
			ajaxResponse.setResponse(400, "Bad Request");
		}
		
		return ajaxResponse;
	}
   	
   	
   	@RequestMapping("/board/fdownload")
   	public ModelAndView fdownload(HttpServletRequest request, HttpServletResponse response) 
   	{
   		ModelAndView modelAndView = null;
   		
   		long bSeq = HttpUtil.get(request, "bSeq", (long)0);
   		
   		if(bSeq > 0) 
   		{
   			WDBoardFile wdBoardFile = wdFBoardService.fBoardFileSelect(bSeq);
   			
   			if(wdBoardFile != null && wdBoardFile.getFileSize() > 0) 
   			{
   				//파일이 존재하면
   				File file = new File(UPLOAD_SAVE_DIR + FileUtil.getFileSeparator()+wdBoardFile.getFileName());
   			
   				logger.debug("UPLOAD_SAVE_DIR : "+UPLOAD_SAVE_DIR);
   				logger.debug("FileUtil.getFileSeparator() : "+ FileUtil.getFileSeparator());
				logger.debug("wdBoardFile.getFileName() : "+ wdBoardFile.getFileName());
				
				if(FileUtil.isFile(file)) 
				{
					modelAndView = new ModelAndView();
					
					modelAndView.setViewName("fileDownloadView");
					
					modelAndView.addObject("file", file);
					modelAndView.addObject("fileName", wdBoardFile.getFileOrgName());
				
					return modelAndView;
				}
   			}
   		}
   		return modelAndView;
   	}
}
