package com.ojt.controller;

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
	// 회원가입
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public void getSignup() throws Exception{
		Logger.info("get signup");
	}
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String postSignup(MemberVO vo, RedirectAttributes rttr) throws Exception{
		Logger.info("post signup");
		service.signup(vo);
		rttr.addFlashAttribute("msg", "회원가입 완료");
		return "redirect:/"; // 처음 화면으로 
	}
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(HttpServletRequest req) throws Exception{
		Logger.info("get login");
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
	 Logger.info("post login");
	 
	 HttpSession session = req.getSession();
	 
	 MemberVO login = service.login(vo);
	 
	 if(login == null) {
	  session.setAttribute("member", null);
	  rttr.addFlashAttribute("msg", "아이디, 비밀번호를 다시 입력하세요.");
	 } else {
		 session.setAttribute("member", login);
	 }
	 
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

