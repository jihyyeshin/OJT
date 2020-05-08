package com.ojt.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ojt.domain.MemberVO;
import com.ojt.service.MemberService;
/**
 * Handles requests for the application home page.
 */

@Controller
public class MemberController {

private static final Logger Logger=LoggerFactory.getLogger(MemberController.class);
	@Inject
	MemberService service;
	
	// 스플래시 이미지
	@RequestMapping(value="/splash", method=RequestMethod.GET)
	public void splash() {
		System.out.println("get splash");
	}
	// index
	@RequestMapping("/")
	public String index() {
		Logger.info("login");
		
		return "login";//"login";//"uitest";//
	}
	
	// 회원가입View
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public void getSignup() throws Exception{
		System.out.println("get signup");
	}
	// 회원가입
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String postSignup(MemberVO vo, RedirectAttributes rttr) throws Exception{
		System.out.println("post signup");
		service.signup(vo);
		
		// 회원가입 시 rec Insert 처리
		service.recInit(vo);

		rttr.addFlashAttribute("msg", "회원가입 완료");
		return "redirect:/"; // 처음 화면으로 
	}

	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		Logger.info("post login");
		 
		HttpSession session = req.getSession();
		// 로그인 정보 가져오기
		MemberVO login = service.login(vo);
		// 아이디, 비밀번호를 잘못 입력한 경우
		if(login == null) {
		  service.loginFail(vo);// 로그인 실패+1
		  
		  session.setAttribute("member", null);
		  rttr.addFlashAttribute("msg", "아이디, 비밀번호를 다시 입력하세요.");
		  return "redirect:/";
		} 
		
		// 로그인 한 이력이 한 번 이상 있을 때
		if(login.getFinalInDtm()!=null) {
			LocalDate today=LocalDate.now(); // 현재 날짜
			LocalDate getDate=LocalDate.parse(login.getFinalInDtm(), DateTimeFormatter.ISO_DATE);
				 
			// 90일 간 로그인 정보가 없다면 사용 중지
			if(getDate.until(today).getYears()>0 || getDate.until(today).getMonths()>2) { 
				session.setAttribute("member", null);
				rttr.addFlashAttribute("msg", "90일 간 접속 기록이 없습니다.");
				return "redirect:/";
			}
			// 5회 이상 비밀번호 틀릴 시 자동 잠금
			if(login.getFailInCnt()>=5) {
				session.setAttribute("member", null);
				rttr.addFlashAttribute("msg", "5회 이상 비밀번호를 잘못 입력하여 계정이 잠겼습니다.");
				return "redirect:/";
			}
		}
		
		// 로그인 날짜=NOW, 입력 실패=0 으로 업데이트
		service.loginDate(vo); 
		// 로그인 정보 -> 뷰
		session.setAttribute("member", login);
		 
		return "redirect:/";
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String getLogout(HttpSession session, RedirectAttributes rttr) throws Exception{
		Logger.info("get logout");
		session.invalidate();
		rttr.addFlashAttribute("msg", "로그아웃 완료");
		return "redirect:/";
	}
	
	// 임시 비밀번호 View
	@RequestMapping(value="/tmpPwd", method=RequestMethod.GET)
	public String getTempPwd(MemberVO vo, RedirectAttributes rttr) throws Exception{
		Logger.info("get temp password");
		return "tmpPwd";
	}
	// 임시 비밀번호 발급(비밀번호 초기화)
	@RequestMapping(value="/tmpPwd", method=RequestMethod.POST)
	public String postTempPwd(MemberVO vo, RedirectAttributes rttr) throws Exception{
		Logger.info("post temp password");
		vo.setPassword(randomPw());
		service.tempPwd(vo);
		rttr.addFlashAttribute("msg", "임시 비밀번호 발급 완료");
		return "redirect:/";
	}
	
	// 숫자, 영문, 특수문자 한 개 이상 포함한 랜덤 임시 비밀번호
	 public static String randomPw(){ 
		  char pwCollectionSpCha[]  = new char[] {'!','@','#','$','%','^','&','*','(',')'}; 
		  char pwCollectionNum[]   = new char[] {'1','2','3','4','5','6','7','8','9','0',}; 
		  char pwCollectionAlpha[] = new char[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z', 
	              'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		  char pwCollectionAll[]  = new char[] {'1','2','3','4','5','6','7','8','9','0', 
		              'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z', 
		              'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z', 
		              '!','@','#','$','%','^','&','*','(',')'}; 
		  return getRandPw(1, pwCollectionAlpha) + getRandPw(1, pwCollectionNum) + getRandPw(5, pwCollectionAll) + getRandPw(1, pwCollectionSpCha); 
	 }
	 // 랜덤 비밀번호 발급
	 public static String getRandPw(int size, char[] pwCollection){
	        String ranPw = "";
	        for (int i = 0; i < size; i++) {
	            int selectRandomPw = (int) (Math.random() * (pwCollection.length));
	            ranPw += pwCollection[selectRandomPw];
	        }
	        return ranPw;
	 }
}

