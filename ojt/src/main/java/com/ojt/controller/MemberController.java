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

/****************************************************ȸ�� ���� Func***************************************************************/
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
	// Index - Ȩ ȭ��
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
		// ��ȣȭ �Ͽ� ����
		String buf=encryptSHA256(vo.getPassword());
		vo.setPassword(buf);
		//System.out.println("this is pw:"+buf);
		try {
			service.signup(vo);
		} catch (Exception e) {
			e.printStackTrace();
			rttr.addFlashAttribute("msg", "�̹� �����ϴ� ���̵��Դϴ�.\n�ٽ� ȸ������ ���ּ���.");
			return "redirect:/"; // ó�� ȭ������ 
		}

		service.recInit(vo);


		rttr.addFlashAttribute("msg", "ȸ������ �Ϸ�");
		return "redirect:/"; // ó�� ȭ������ 
	}

	// �α��� �� �α��� ����
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		Logger.info("post login");
		 
		HttpSession session = req.getSession();
		// �����ϰ� ��ȣȭ�ؼ� Ȯ��
		String buf=encryptSHA256(vo.getPassword());
		vo.setPassword(buf);
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

	// �ֹ� ����
	@RequestMapping(value = "/saleList", method = RequestMethod.GET)
	public String getSaleList() {
		System.out.println("/items/saleList (get)");
		return "notFound";
	}

	// �ֹ� ����
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