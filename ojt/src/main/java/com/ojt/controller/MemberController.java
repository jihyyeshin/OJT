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
	// ȸ������View
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public void getSignup() throws Exception{
		Logger.info("get signup");
	}
	// ȸ������
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String postSignup(MemberVO vo, RedirectAttributes rttr) throws Exception{
		Logger.info("post signup");
		service.signup(vo);
		rttr.addFlashAttribute("msg", "ȸ������ �Ϸ�");
		return "redirect:/"; // ó�� ȭ������ 
	}
	
	// �α���View
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(HttpServletRequest req) throws Exception{
		Logger.info("get login");
		return "login";
	}
	// �α���
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
	 Logger.info("post login");
	 
	 HttpSession session = req.getSession();
	 // �α��� ���� ��������
	 MemberVO login = service.login(vo);
	 
	 if(login == null) {
	  session.setAttribute("member", null);
	  rttr.addFlashAttribute("msg", "���̵�, ��й�ȣ�� �ٽ� �Է��ϼ���.");
	 } else {
		 // �α��� ���� �� �� �̻��� ��
		 if(login.getFinalInDtm()!=null) {
			 LocalDate today=LocalDate.now();
			 LocalDate getDate=LocalDate.parse(login.getFinalInDtm(), DateTimeFormatter.ISO_DATE);
			// �α��� �Ϸ��� �ôµ� 90�� �� �α��� ������ ���ٸ� ��� ����
			 if(getDate.until(today).getYears()>0 || getDate.until(today).getMonths()>2) { 
				 session.setAttribute("member", null);
				 rttr.addFlashAttribute("msg", "90�� �� ���� ����� �����ϴ�.");
			 }
			 else {
				 // �α��� ��¥ ������Ʈ
				 service.loginDate(vo); 
				 // �α��� ���� -> ��
				 session.setAttribute("member", login);
			 }
		 }
	 }
	 
	 return "redirect:/";
	}
	
	// �α׾ƿ�
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String getLogout(HttpSession session, RedirectAttributes rttr) throws Exception{
		Logger.info("get logout");
		session.invalidate();
		rttr.addFlashAttribute("msg", "�α׾ƿ� �Ϸ�");
		return "redirect:/";
	}
	
}

