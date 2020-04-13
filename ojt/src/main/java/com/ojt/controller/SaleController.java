package com.ojt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ojt.domain.BasketVO;
import com.ojt.domain.ItemVO;
import com.ojt.domain.SaleItemVO;
import com.ojt.domain.SaleVO;
import com.ojt.service.SaleService;

@Controller
@RequestMapping("/items/*")
public class SaleController {
	@Inject
	SaleService service;
	private static final Logger Logger=LoggerFactory.getLogger(LocController.class);
	
	// �ֹ�
	@RequestMapping(value = "/sale", method = RequestMethod.GET)
	public String getSale() {
		return "notFound";
	}

	@RequestMapping(value = "/sale", method = RequestMethod.POST)
	public String postSale(HttpServletRequest req, String agent, String memberid, String[] name, String[] itemchk, int[] amount, int[] qty) throws Exception{
		Logger.info("post sale");
		HttpSession session = req.getSession();
		
		Date time=new Date();
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");	
		String dtime=format.format(time);
		
		int tot=0;
		System.out.println(itemchk.length);
		for(int i=0;i<itemchk.length;i++) {
			
			tot+=amount[i]*qty[i];
			SaleItemVO sivo=new SaleItemVO(dtime, amount[i]*qty[i], amount[i], agent, itemchk[i],qty[i], i+1);
			service.saleItem(sivo); // �ֹ� ������ insert
		}
		SaleVO svo=new SaleVO(dtime, tot,agent, memberid);
		service.sale(svo); // �ֹ� ���� insert
		session.setAttribute("memberid", memberid);
		return "sale"; //�ֹ� �Ϸ�View
	}
	// ��ٱ���
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public String getBasket(HttpServletRequest req, String agent, String memberid) {
		Logger.info("get basket");
		System.out.println("/items/basket(get)");
		HttpSession session = req.getSession();
		session.setAttribute("agent", agent);
		session.setAttribute("memberid", memberid);
		return "basket";
	}
	@RequestMapping(value = "/basket", method = RequestMethod.POST)
	public String postBasket(HttpServletRequest req, String agent, String memberid, String[] name, String[] itemchk, int[] amount, int[] qty/*, String type*/) throws Exception{
		Logger.info("post basket");
		HttpSession session = req.getSession();
		System.out.println("/items/basket(post)");
		session.setAttribute("agent", agent);
		session.setAttribute("memberid", memberid);
		return "basket"; // ��ٱ��� View
	}
	// ��ٱ��� �ֱ�
	@RequestMapping(value="/insertBasket", method=RequestMethod.POST)
	public String insertBasket(HttpServletRequest req, String agent, String memberid, String[] name, String[] itemchk, String item, int[] amount, int[] qty) throws Exception {
		System.out.println("/items/basket(input)");
		HttpSession session = req.getSession();
		//System.out.println(itemchk.length);
		//System.out.println(item);
		if(item.equals("")) {// item list ȭ��
			for(int i=0;i<itemchk.length;i++) {
				BasketVO bvo=new BasketVO(memberid,amount[i]*qty[i], amount[i], agent, itemchk[i],qty[i]);
				service.basket(bvo); // ��ٱ��� insert
			}
		}else {// item detail ȭ��
			BasketVO bvo=new BasketVO(memberid, amount[0]*qty[0], amount[0], agent, item ,qty[0]);
			service.basket(bvo); // ��ٱ��� insert
		}
		session.setAttribute("agent", agent);
		session.setAttribute("memberid", memberid);
		return "redirect:/items";
	}
	
	// ��ٱ��� ������ ��ȸ
	@RequestMapping(value="/showBasket")
	public @ResponseBody List<BasketVO> showBasket(@RequestParam String memberid) throws Exception {
		List<BasketVO> list=service.basketList(memberid);
		System.out.println("/items/showBasket");
		return list;
	}
	
	// ��ٱ��� ����
	@RequestMapping(value="/deleteBasket", method=RequestMethod.GET)
	public String deletedBasket(@RequestParam int idx) throws Exception {
		Logger.info("delete basket");
		System.out.println("/items/deleteBasket");
		//System.out.println("ddidx: "+idx);
		service.deleteBasket(idx);
		//System.out.println("!??!?");
		return "redirect:/items/basket";
	}
	// ��ٱ��Ͽ��� �ֹ�(�ֹ��� ��ǰ�� ����)
	@RequestMapping(value = "/saleBasket", method = RequestMethod.POST)
	public String postBasketSale(HttpServletRequest req, String agent, String memberid, String[] name, String[] itemchk, int[] amount, int[] qty, int[] idx) throws Exception{
		Logger.info("post sale basket");
		HttpSession session = req.getSession();
		
		Date time=new Date();
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");	
		String dtime=format.format(time);
		
		int tot=0;
		System.out.println(itemchk.length);
		for(int i=0;i<itemchk.length;i++) {
			tot+=amount[i]*qty[i];
			SaleItemVO sivo=new SaleItemVO(dtime, amount[i]*qty[i], amount[i], agent, itemchk[i],qty[i], i+1);
			service.saleItem(sivo); // �ֹ� ������ insert
			// �ֹ��� ������ ��ٱ��Ͽ��� delete
			System.out.println("idx: "+idx[i]);
			service.deleteBasket(idx[i]);
		}
		SaleVO svo=new SaleVO(dtime, tot,agent, memberid);
		service.sale(svo); // �ֹ� ���� insert
		session.setAttribute("memberid", memberid);
		return "sale"; //�ֹ� �Ϸ�View
	}
}
