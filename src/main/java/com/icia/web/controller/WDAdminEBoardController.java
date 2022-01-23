package com.icia.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.icia.common.model.FileData;
import com.icia.common.util.StringUtil;
import com.icia.web.model.Paging;
import com.icia.web.model.Response;
import com.icia.web.model.WDAdmin;
import com.icia.web.model.WDEBoard;
import com.icia.web.model.WDEBoardFile;
import com.icia.web.model.WDNBoard;
import com.icia.web.service.WDAdminService;
import com.icia.web.service.WDEBoardService;
import com.icia.web.service.WDUserService;
import com.icia.web.util.CookieUtil;
import com.icia.web.util.HttpUtil;

@Controller("wdAdminEBoardController")
public class WDAdminEBoardController {
	
	private static final long LIST_COUNT = 5;
	private static final long PAGE_COUNT = 5;
	
	private static Logger logger = LoggerFactory.getLogger(WDAdminEBoardController.class);

	//쿠키명
	@Value("#{env['auth.cookie.name']}")
	private String AUTH_COOKIE_NAME;
	
	//파일 저장경로
	@Value("#{env['upload.save.event']}") 
	private String UPLOAD_SAVE_EVENT;
	
	@Autowired
	private WDEBoardService wdEBoardService;
	
	@Autowired
	private WDUserService wdUserService;
	
	@Autowired
	private WDAdminService wdAdminService;
	
	//이벤트 페이지 불러오기
	@RequestMapping(value = "/mng/eBoardList")
	public String eBoardList(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		long totalCount = 0;
		long curPage = HttpUtil.get(request, "curPage", (long)1);
		String searchType = HttpUtil.get(request, "searchType", "");
		String searchValue = HttpUtil.get(request, "searchValue", "");
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		
		List<WDEBoard> eBoard = null;
		
		Paging paging = null;
		
		WDEBoard search = new WDEBoard();
		
		WDAdmin wdAdmin = wdAdminService.wdAdminSelect(cookieUserId);
		
		if(!StringUtil.isEmpty(searchType) && !StringUtil.isEmpty(searchValue)) 
		{
			//받아온 값이 있음.
			search.setSearchType(searchType);
			search.setSearchValue(searchValue);
		}
		else 
		{
			searchType = "";
			searchValue = "";
		}
		
		totalCount = wdEBoardService.eBoardListCount(search);
		
		if(totalCount > 0 ) 
		{
			
			paging = new Paging("/board/eList", totalCount, LIST_COUNT, PAGE_COUNT, curPage, "curPage");
			paging.addParam("curPage", curPage);
			paging.addParam("searchType", searchType);
			paging.addParam("searchValue", searchValue);
			
			search.setStartRow(paging.getStartRow());
			search.setEndRow(paging.getEndRow());
			
			eBoard = wdEBoardService.eBoardList(search);
		}
		
		model.addAttribute("eBoard", eBoard);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("curPage", curPage);
		model.addAttribute("paging", paging);
		model.addAttribute("wdAdmin", wdAdmin);
			
		return "/mng/eBoardList";
	}
	
	//이벤트 수정페이지 내용 불러오기
    @RequestMapping(value="/mng/eBoardUpdate")
    public String nBoardUpdate(Model model, HttpServletRequest request, HttpServletResponse response)
    {
       //수정페이지에 필요한거 받아오기
   	long eBSeq = HttpUtil.get(request, "eBSeq", (long)0);
   	
   	WDEBoard eBoard = null;
      
       if(eBSeq > 0)
       {
          eBoard = wdEBoardService.eBoardSelect(eBSeq);
         
          model.addAttribute("eBoard", eBoard);
       }
       
       model.addAttribute("eBSeq", eBSeq);
       model.addAttribute("eBoard", eBoard);
       
       return "/mng/eBoardUpdate";
    }
	
    //이벤트 게시글 수정하기
    @RequestMapping(value="/mng/eBoardUpdateProc", method=RequestMethod.POST)
    @ResponseBody
    public Response<Object> eBoardUpdateProc(HttpServletRequest request, HttpServletResponse response)
    {
    	Response<Object> res = new Response<Object>();
        
        long eBSeq = HttpUtil.get(request, "eBSeq", (long)0);
        String adminId = HttpUtil.get(request, "adminId", "");
        String eBTitle = HttpUtil.get(request, "eBTitle", "");
        String eBContent = HttpUtil.get(request, "eBContent", "");
        
        WDEBoard wdEBoard = null;
        
        if(eBSeq > 0 && !StringUtil.isEmpty(eBTitle) && !StringUtil.isEmpty(eBContent))
        {
     	   //게시글 존재하고, 제목,내용받아옴
     	   wdEBoard = wdEBoardService.eBoardSelect(eBSeq);
     	   
     	   	System.out.println("=================================================================");
     	   	System.out.println("eBSeq : " + eBSeq);
     	   	System.out.println("=================================================================");
     	   
     	   System.out.println("eBSeq : " + eBSeq);
     	   if(wdEBoard != null )
     	   {
     		   //새로운 정보 넣어주기
     		   wdEBoard.seteBSeq(eBSeq);
     		   wdEBoard.seteBTitle(eBTitle);
     		   wdEBoard.seteBContent(eBContent);
     		   
     		   if(wdEBoardService.eBoardUpdate(wdEBoard) > 0)
     		   {
     			   res.setResponse(0, "Success");
     		   }
     		   else
     		   {
     			   res.setResponse(-1, "Fail");
     		   }
     		   
     	   }
     	   else
     	   {
     		   res.setResponse(400, "Bad Request");
     	   }
        }
        else 
			{
     	   res.setResponse(400, "Bad Request");
			}
        
        
        return res;
     }


    //이벤트 게시글 등록
    @RequestMapping(value="/mng/plusEBoard")
    public String plusEBoard(Model model, HttpServletRequest request, HttpServletResponse response)
    {
    	return "/mng/plusEBoard";
    }
    
  //공지사항 게시글 추가하기!!
    @RequestMapping(value="/mng/eBoardWrite")
    @ResponseBody
    public Response<Object> eBoardWrite(MultipartHttpServletRequest request, HttpServletResponse response)
    {
        Response<Object> ajaxResponse = new Response<Object>();
        
        String eBTitle = HttpUtil.get(request, "bTitle", "");
        String eBContent = HttpUtil.get(request, "bContent", "");
        String name = wdEBoardService.maxImgName();
        name = name.replace("E", "");
        name = name.replace(".jpg", "");
        name = name.replace(".png", "");
        int namePlus = Integer.parseInt(name)+1;
        name = "E0"+namePlus;
        FileData fileData = HttpUtil.getFile(request, "img", UPLOAD_SAVE_EVENT,name);
        int count = 0;
            
        //쿠키 조회
        String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
        //관리자 닉네임
        WDAdmin wdAdmin = wdAdminService.wdAdminSelect(cookieUserId);
        
    	WDEBoard wdEBoard = new WDEBoard();
    	
    	System.out.println("=======================================================");
    	System.out.println("name :" + name);
    	System.out.println("eBTitle : " + eBTitle);
    	System.out.println("eBContent : " + eBContent);
    	System.out.println("=======================================================");
    	
    	//게시물 제목과 내용이 비어있을 경우
        if(!StringUtil.isEmpty(eBTitle) && !StringUtil.isEmpty(eBContent))
        {
        	wdEBoard.setAdminId(cookieUserId);
        	wdEBoard.seteBTitle(eBTitle);
        	wdEBoard.seteBContent(eBContent);
        	
        	if(!StringUtil.isEmpty(fileData))
        	{
        		System.out.println("================================================");
				System.out.println("fileData : " + fileData.getFileName());
				System.out.println("================================================");
        		
        		WDEBoardFile wdEBoardFile = new WDEBoardFile();
        		
        		wdEBoardFile.setFileName(fileData.getFileName());
        		wdEBoardFile.setFileOrgName(fileData.getFileOrgName());
        		wdEBoardFile.setFileExt(fileData.getFileExt());
        		wdEBoardFile.setFileSize(fileData.getFileSize());
        		
        		wdEBoard.setWdEBoardFile(wdEBoardFile);
        		
        		try
        		{
        			if(wdEBoardService.eBoardInsert(wdEBoard) > 0)
        			{
        				wdEBoard.seteBImgName(wdEBoardFile.getFileName());
        				
        				wdEBoardService.eBoardFileInsert(wdEBoardFile);
        				System.out.println("=========================================================================");
        				System.out.println("이미지 이름 : " + wdEBoard.geteBImgName());
        				System.out.println("=========================================================================");
        				
        				//EBoardFile 이미지 이름을 EBoard 이미지 이름을 DB에 넣기
        				wdEBoardService.eBoardFileUpdate(wdEBoard);
        				
        				ajaxResponse.setResponse(0, "Successs");
        				
        				wdEBoardService.eBoardFileInsert(wdEBoardFile);
        				
        			}
        			else
        			{
        				ajaxResponse.setResponse(500, "Internal Server Error");
        			}
        		}
        		catch(Exception e)
        		{
        			logger.error("[WDAdminEBoardContorller] eBoardWrite Exception", e);
        			ajaxResponse.setResponse(400, "Server Error");
        		}
        	}
        	else
        	{
        		ajaxResponse.setResponse(-2, "Not found BoardFile");
        	}
        }
        else
        {
        	ajaxResponse.setResponse(-1, "Not found Board");
        }

        return ajaxResponse;
    }
    
    //이벤트 게시글 삭제
    @RequestMapping(value="/mng/eBoardDelete", method=RequestMethod.POST)
    @ResponseBody
    public Response<Object> eBoardDelete(HttpServletRequest request, HttpServletResponse response)
    {
 	   Response<Object> ajaxResponse = new Response<Object>();
       
 	  long bSeq = HttpUtil.get(request, "bSeq", (long)0);
       System.out.println("************** eBSeq: " + bSeq);
 	   
 	   if(bSeq > 0) 
 	   {
 		  WDEBoard eBoard = wdEBoardService.eBoardSelect(bSeq);
 		   
 		   if(eBoard != null) 
 		   {
 			 
			   if(wdEBoardService.eBoardDelete(bSeq) > 0)
			   {
				  
				   ajaxResponse.setResponse(0, "Success");
			   }
			   else
			   {
				   ajaxResponse.setResponse(500, "Internal Server Error");
			   }
 		   }
 		   else
 		   {
 			   ajaxResponse.setResponse(404, "Not Found");
 		   }
 	   }
 	   else
 	   {
 		   ajaxResponse.setResponse(400, "Bad Request");
 	   }
 	   
 	   return ajaxResponse;
    }
    
}