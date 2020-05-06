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
	
	// ���÷��� �̹���
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
	
	// ȸ������View
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public void getSignup() throws Exception{
		System.out.println("get signup");
	}
	// ȸ������
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String postSignup(MemberVO vo, RedirectAttributes rttr) throws Exception{
		System.out.println("post signup");
		service.signup(vo);
		// ȸ������ �� recVal -1 ó��
		service.recInit();

		rttr.addFlashAttribute("msg", "ȸ������ �Ϸ�");
		return "redirect:/"; // ó�� ȭ������ 
	}

	// �α���
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		Logger.info("post login");
		 
		HttpSession session = req.getSession();
		// �α��� ���� ��������
		MemberVO login = service.login(vo);
		// ���̵�, ��й�ȣ�� �߸� �Է��� ���
		if(login == null) {
		  service.loginFail(vo);// �α��� ����+1
		  
		  session.setAttribute("member", null);
		  rttr.addFlashAttribute("msg", "���̵�, ��й�ȣ�� �ٽ� �Է��ϼ���.");
		  return "redirect:/";
		} 
		
		// �α��� �� �̷��� �� �� �̻� ���� ��
		if(login.getFinalInDtm()!=null) {
			LocalDate today=LocalDate.now(); // ���� ��¥
			LocalDate getDate=LocalDate.parse(login.getFinalInDtm(), DateTimeFormatter.ISO_DATE);
				 
			// 90�� �� �α��� ������ ���ٸ� ��� ����
			if(getDate.until(today).getYears()>0 || getDate.until(today).getMonths()>2) { 
				session.setAttribute("member", null);
				rttr.addFlashAttribute("msg", "90�� �� ���� ����� �����ϴ�.");
				return "redirect:/";
			}
			// 5ȸ �̻� ��й�ȣ Ʋ�� �� �ڵ� ���
			if(login.getFailInCnt()>=5) {
				session.setAttribute("member", null);
				rttr.addFlashAttribute("msg", "5ȸ �̻� ��й�ȣ�� �߸� �Է��Ͽ� ������ �����ϴ�.");
				return "redirect:/";
			}
		}
		
		// �α��� ��¥=NOW, �Է� ����=0 ���� ������Ʈ
		service.loginDate(vo); 
		// �α��� ���� -> ��
		session.setAttribute("member", login);
		 
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
	
	// �ӽ� ��й�ȣ View
	@RequestMapping(value="/tmpPwd", method=RequestMethod.GET)
	public String getTempPwd(MemberVO vo, RedirectAttributes rttr) throws Exception{
		Logger.info("get temp password");
		return "tmpPwd";
	}
	// �ӽ� ��й�ȣ �߱�(��й�ȣ �ʱ�ȭ)
	@RequestMapping(value="/tmpPwd", method=RequestMethod.POST)
	public String postTempPwd(MemberVO vo, RedirectAttributes rttr) throws Exception{
		Logger.info("post temp password");
		vo.setPassword(randomPw());
		service.tempPwd(vo);
		rttr.addFlashAttribute("msg", "�ӽ� ��й�ȣ �߱� �Ϸ�");
		return "redirect:/";
	}
	
	// ����, ����, Ư������ �� �� �̻� ������ ���� �ӽ� ��й�ȣ
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
	 // ���� ��й�ȣ �߱�
	 public static String getRandPw(int size, char[] pwCollection){
	        String ranPw = "";
	        for (int i = 0; i < size; i++) {
	            int selectRandomPw = (int) (Math.random() * (pwCollection.length));
	            ranPw += pwCollection[selectRandomPw];
	        }
	        return ranPw;
	 }
}

