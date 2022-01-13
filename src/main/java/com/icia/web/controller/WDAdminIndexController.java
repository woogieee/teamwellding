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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.common.util.StringUtil;
import com.icia.web.model.Paging;
import com.icia.web.model.Response;
import com.icia.web.model.WDAdmin;
import com.icia.web.model.WDAdminUser;
import com.icia.web.model.WDDress;
import com.icia.web.model.WDHall;
import com.icia.web.model.WDMakeUp;
import com.icia.web.model.WDStudio;
import com.icia.web.model.WDUser;
import com.icia.web.service.WDAdminService;
import com.icia.web.service.WDAdminUserService;
import com.icia.web.service.WDDressService;
import com.icia.web.service.WDHallService;
import com.icia.web.service.WDMakeUpService;
import com.icia.web.service.WDRezService;
import com.icia.web.service.WDStudioService;
import com.icia.web.util.CookieUtil;
import com.icia.web.util.HttpUtil;
import com.icia.web.util.JsonUtil;

@Controller("wdAdminIndexController")
public class WDAdminIndexController 
{
	private static Logger logger = LoggerFactory.getLogger(WDAdminIndexController.class);
	
	@Autowired
	private WDAdminService wdAdminService;
	
	@Autowired
	private WDAdminUserService wdAdminUserService;
	
    @Autowired
    private WDHallService wdHallService;
   
    @Autowired
    private WDRezService wdRezService;
    
    @Autowired
	private WDStudioService wdStudioService;
    
    @Autowired
	private WDDressService wdDressService;
    
    @Autowired
	private WDMakeUpService wdMakeUpService;

	//쿠키명
	@Value("#{env['auth.cookie.name']}")
	private String AUTH_COOKIE_NAME;
	
	private static final int LIST_COUNT = 10;
	private static final int PAGE_COUNT = 10;
	
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
		String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		
		String status = HttpUtil.get(request, "status"); //변수명에 대한 값을 읽어오기!
		//검색타입 (1:회원아이디, 2:회원명)
		String searchType = HttpUtil.get(request, "searchType");
		String searchValue = HttpUtil.get(request, "searchValue");
		
		//이건 유저테이블 유저 객체
		WDAdminUser wdAdminUser = new WDAdminUser();
		//이건 리스트 객체
		List<WDAdminUser> userList = null;
		
		//닉네임 달려구
		WDAdmin wdAdmin = wdAdminService.wdAdminSelect(cookieUserId);
		
		//현재페이지
		long curPage = HttpUtil.get(request, "curPage", (long)1);
		
		//총 게시물 수
		long totalCount = 0;
		//페이징객체
		Paging paging = null;
		
		//매개변수로 받은 status 먼저 셋팅
		wdAdminUser.setStatus(status);
		
		//서치타입과 서치밸유 값이 비어있는지 체크
		if(!StringUtil.isEmpty(searchType) && !StringUtil.isEmpty(searchValue))
		{
			if(StringUtil.equals(searchType, "1"))
			{
				wdAdminUser.setUserId(searchValue);
			}
			else if(StringUtil.equals(searchType, "2"))
			{
				wdAdminUser.setUserName(searchValue);
			}
			else
			{
				searchType = "";
				searchValue = "";
			}
		}
		else
		{
			searchType = "";
			searchValue = "";
		}
		
		totalCount = wdAdminUserService.wdAdmUserListCount(wdAdminUser);
		
		if(totalCount > 0) {
			paging = new Paging("/mng/userList", totalCount, LIST_COUNT, PAGE_COUNT, curPage, "curPage");
			
			paging.addParam("status", status);
			paging.addParam("searchType", searchType);
			paging.addParam("searchValue", searchValue);
			paging.addParam("curPage", curPage);
			
			//User객체에 스타트로우,엔드로우 보내기
			wdAdminUser.setStartRow(paging.getStartRow());
			wdAdminUser.setEndRow(paging.getEndRow());
			
			//유저 리스트 가져옴 (검색했으면 검색된 값을만)
			userList = wdAdminUserService.wdAdmUserList(wdAdminUser);
			//System.out.println("이거다 : "+userList.get(0).getUserName());
		}
		//list객체에 넣을거! 모델객체사용
		model.addAttribute("userList", userList);
		model.addAttribute("wdAdmin",wdAdmin);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("status", status);
		model.addAttribute("curPage", curPage);
		model.addAttribute("paging", paging);
		
		
		return "/mng/userList";
	}
	
	@RequestMapping(value="/mng/MngUserUpdate")
	public String userUpdate(Model model,HttpServletRequest request, HttpServletResponse response) {
		
		//jps에 뿌려야하니까 Model 매개변수를 받음. 화면에서 보면 유저아이디만 처리하므로 유저아이디만 가져옴
		String userId = HttpUtil.get(request, "userId");
		
		if(!StringUtil.isEmpty(userId))
		{
			//user정보조회
			WDAdminUser wdAdminUser = wdAdminUserService.wdAdminUserSelect(userId);
			
			if(wdAdminUser != null)
			{
				//널이 아니면 써야하니까
				model.addAttribute("wdAdminUser", wdAdminUser); //앞은 jsp에 사용할 이름, 뒤는 메소드내에 있는 메소드변수명
			}
		}
		
		return "/mng/MngUserUpdate";
	}
	
	//회원정보 수정
		@RequestMapping(value="/mng/userupdateProc", method=RequestMethod.POST)
		@ResponseBody
		public Response<Object> updateProc(HttpServletRequest request, HttpServletResponse response)
		{
			Response<Object> res = new Response<Object>();
			
			//관리가가 새로 입력한 정보들 받기
			String userId = HttpUtil.get(request, "userId");
			String userPwd = HttpUtil.get(request, "userPwd");
			String userName = HttpUtil.get(request, "userName");
			String userEmail = HttpUtil.get(request, "userEmail");
			String userNickname = HttpUtil.get(request, "userNickname");
			String status = HttpUtil.get(request, "status");
			
			if(!StringUtil.isEmpty(userId) && !StringUtil.isEmpty(userPwd) && !StringUtil.isEmpty(userName) && 
					!StringUtil.isEmpty(userNickname) && !StringUtil.isEmpty(userEmail) && !StringUtil.isEmpty(status))
			{
				//500번 오류 막기위한 처리 (주소치고오는 애들 막아주기)
				WDAdminUser wdAdminUser = wdAdminUserService.wdAdminUserSelect(userId);
				
				if(wdAdminUser != null)
				{
					//원래 있던 유저의 정보를 새로받은걸로 값 넣어주기
					wdAdminUser.setUserPwd(userPwd);
					wdAdminUser.setUserName(userName);
					wdAdminUser.setUserEmail(userEmail);
					wdAdminUser.setUserEmail(userNickname);
					wdAdminUser.setStatus(status);
					
					if(wdAdminUserService.wdAdmUserUpdate(wdAdminUser) > 0)
					{
						//성공
						res.setResponse(0, "Success");
					}
					else
					{
						res.setResponse(-1, "Fail");
					}
				}
				else
				{
					//정보가 없음
					res.setResponse(404, "Not Found");
				}
			}
			else
			{
				//값이 하나라도 없으면 파라미터 오류
				res.setResponse(400, "Bad Request");
			}
			
			return res;
		}
		
		@RequestMapping(value="/mng/hsdmList")
		public String hsdmList(Model model,HttpServletRequest request, HttpServletResponse response)
		{
			//홀스드메 리스트 페이징 처리를 위한 개수 체크 변수
			long hTotalCount = 0;
			long sTotalCount = 0;
			long dTotalCount = 0;
			long mTotalCount = 0;
			
			//홀스드메 리스트를 조회 할때 쓸 객체 선언 (검색기능도 이 객체에서 처리함)
			WDHall wdHall = new WDHall();
			WDStudio wdStudio = new WDStudio();
			WDDress wdDress = new WDDress();
			WDMakeUp wdMakeUp = new WDMakeUp();
			
			//홀스드메 정보가 담길 리스트 객체 선언
			List<WDHall> hList = null;
			List<WDStudio> sList = null;
			List<WDDress> dList = null;
			List<WDMakeUp> mList = null;
			
			//쿠키 조회
		    String cookieUserId = CookieUtil.getHexValue(request, AUTH_COOKIE_NAME);
		   
			//조회항목
			String searchType = HttpUtil.get(request, "searchType", "");
			//조회값
			String searchValue = HttpUtil.get(request, "searchValue", "");
			//현재 페이지
			long curPage = HttpUtil.get(request, "curPage", (long)1);
			
			//홀스드메 코드 조회
		    String whCode = HttpUtil.get(request, "WHCode", "");
		    String hCode = HttpUtil.get(request, "HCode", "");
			String sCode = HttpUtil.get(request, "sCode", "");
			String dcCode = HttpUtil.get(request, "dcCode", "");
			String dNo = HttpUtil.get(request, "dNo", ""); 
			String mCode = HttpUtil.get(request, "mCode", "");
			
			//홀스드메 페이징 처리 객체
			Paging hPaging = null;
			Paging sPaging = null;
			Paging dPaging = null;
			Paging mPaging = null;
			
			//각 홀스드메 객체마다 코드값 삽입
			wdHall.setWHCode(whCode);
			wdHall.setHCode(hCode);
			wdStudio.setsCode(sCode);
			wdDress.setDcCode(dcCode);
			wdDress.setdNo(dNo);
			wdMakeUp.setmCode(mCode);
			
			//홀스드메 각각 리스트가 총 몇개인지 개수 체크
			hTotalCount = wdHallService.WDHallListCount(wdHall);
			sTotalCount = wdStudioService.studioListCount(wdStudio);
			dTotalCount = wdDressService.dressListCount(wdDress);
			//mTotalCount = wdMakeUpService.makeUpListCount(wdMakeUp);
			mTotalCount = wdMakeUpService.makeUpListCountmr(wdMakeUp);
			
			//홀 페이징 처리
			if(hTotalCount > 0)
			{
				hPaging = new Paging("/mng/hsdmList", hTotalCount, LIST_COUNT, PAGE_COUNT, curPage, "curPage"); //페이징처리 : 인수값이 있다 = 생성자가 있다
				
				hPaging.addParam("searchType", searchType);
				hPaging.addParam("searchValue", searchValue);
				hPaging.addParam("curPage", curPage);
				
				wdHall.setStartRow(hPaging.getStartRow());
				wdHall.setEndRow(hPaging.getEndRow());
				
				hList = wdHallService.WDHallList(wdHall);
			}
			//스튜디오 페이징 처리
			if(sTotalCount > 0)
			{
				sPaging = new Paging("/mng/hsdmList", sTotalCount, LIST_COUNT, PAGE_COUNT, curPage, "curPage"); //페이징처리 : 인수값이 있다 = 생성자가 있다
				
				sPaging.addParam("searchType", searchType);
				sPaging.addParam("searchValue", searchValue);
				sPaging.addParam("curPage", curPage);
				
				wdStudio.setStartRow(sPaging.getStartRow());
				wdStudio.setEndRow(sPaging.getEndRow());
				
				sList = wdStudioService.studioList(wdStudio);
			}
			//드레스 페이징 처리
			if(dTotalCount > 0)
			{
				dPaging = new Paging("/mng/hsdmList", dTotalCount, LIST_COUNT, PAGE_COUNT, curPage, "curPage"); //페이징처리 : 인수값이 있다 = 생성자가 있다
				
				dPaging.addParam("searchType", searchType);
				dPaging.addParam("searchValue", searchValue);
				dPaging.addParam("curPage", curPage);
				
				wdDress.setStartRow(dPaging.getStartRow());
				wdDress.setEndRow(dPaging.getEndRow());
				
				dList = wdDressService.dressList(wdDress);
			}
			//메이크업 페이징 처리
			if(mTotalCount > 0)
			{
				mPaging = new Paging("/mng/hsdmList", mTotalCount, LIST_COUNT, PAGE_COUNT, curPage, "curPage"); //페이징처리 : 인수값이 있다 = 생성자가 있다
				
				mPaging.addParam("searchType", searchType);
				mPaging.addParam("searchValue", searchValue);
				mPaging.addParam("curPage", curPage);
				
				wdMakeUp.setStartRow(mPaging.getStartRow());
				wdMakeUp.setEndRow(mPaging.getEndRow());
				
				//mList = wdMakeUpService.makeUpList(wdMakeUp);
				mList = wdMakeUpService.makeUpListMinusRez(wdMakeUp);
			}
			
			model.addAttribute("hList", hList);
			model.addAttribute("hPaging",hPaging);
			model.addAttribute("sList", sList);
			model.addAttribute("sPaging",sPaging);
			model.addAttribute("dList", dList);
			model.addAttribute("dPaging",dPaging);
			model.addAttribute("mList", mList);
			model.addAttribute("mPaging",mPaging);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchValue", searchValue);
			model.addAttribute("curPage", curPage);		
			
			return "/mng/hsdmList";
		}
}
