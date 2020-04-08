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
	
	@RequestMapping("/")
	public String index() {
		Logger.info("index");
		return "index";
	}
	// 회원가입View
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public void getSignup() throws Exception{
		Logger.info("get signup");
	}
	// 회원가입
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String postSignup(MemberVO vo, RedirectAttributes rttr) throws Exception{
		Logger.info("post signup");
		service.signup(vo);
		rttr.addFlashAttribute("msg", "회원가입 완료");
		return "redirect:/"; // 처음 화면으로 
	}
	
	// 로그인View
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(HttpServletRequest req) throws Exception{
		Logger.info("get login");
		return "login";
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
			if(login.getFailInCnt()>5) {
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
	
}

