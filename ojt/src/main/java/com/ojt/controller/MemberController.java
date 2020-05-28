package com.ojt.controller;

import java.security.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ojt.domain.MemberVO;
import com.ojt.domain.SaleItemVO;
import com.ojt.domain.SaleListVO;
import com.ojt.service.MemberService;
import com.ojt.service.SaleService;

/****************************************************회원 관련 Func***************************************************************/
@Controller
public class MemberController {

private static final Logger Logger=LoggerFactory.getLogger(MemberController.class);
	@Inject
	MemberService service;
	@Inject
	SaleService service2;
	
	// Splash Image
	@RequestMapping(value="/splash", method=RequestMethod.GET)
	public void splash() {
		System.out.println("get splash");
	}
	// Index - 홈 화면
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
		// 암호화 하여 저장
		String buf=encryptSHA256(vo.getPassword());
		vo.setPassword(buf);
		//System.out.println("this is pw:"+buf);
		try {
			service.signup(vo);
		} catch (Exception e) {
			e.printStackTrace();
			rttr.addFlashAttribute("msg", "이미 존재하는 아이디입니다.\n다시 회원가입 해주세요.");
			return "redirect:/"; // 처음 화면으로 
		}

		service.recInit(vo);


		rttr.addFlashAttribute("msg", "회원가입 완료");
		return "redirect:/"; // 처음 화면으로 
	}

	// 로그인 및 로그인 조건
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		Logger.info("post login");
		 
		HttpSession session = req.getSession();
		// 동일하게 암호화해서 확인
		String buf=encryptSHA256(vo.getPassword());
		vo.setPassword(buf);
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

	// 주문 내역
	@RequestMapping(value = "/saleList", method = RequestMethod.GET)
	public String getSaleList() {
		System.out.println("/items/saleList (get)");
		return "notFound";
	}

	// 주문 내역
	@RequestMapping(value = "/saleList", method = RequestMethod.POST)
	public String postSaleList() {
		System.out.println("/items/saleList (post)");
		return "saleList";
	}

	@RequestMapping(value = "/showSaleList")
	public @ResponseBody List<SaleListVO> showSaleList(@RequestParam String memberid, @RequestParam String date) throws Exception {
		System.out.println("showSaleList");
		System.out.println(memberid);
		SaleItemVO vo=new SaleItemVO();
		vo.setMemberid(memberid);
		vo.setDelivDate(date);
		List<SaleListVO> list = service2.showSaleList(vo);
		return list;
	}
	
	
	public String encryptSHA256(String str){
	
	    String sha = "";
	
	    try{
	       MessageDigest sh = MessageDigest.getInstance("SHA-256");
	       sh.update(str.getBytes());
	       byte byteData[] = sh.digest();
	       StringBuffer sb = new StringBuffer();
	       for(int i = 0 ; i < byteData.length ; i++){
	           sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
	       }
	
	       sha = sb.toString();
	
	   }catch(NoSuchAlgorithmException e){
	       //e.printStackTrace();
	       System.out.println("Encrypt Error - NoSuchAlgorithmException");
	       sha = null;
	   }
	
	   return sha;
	 } 
}