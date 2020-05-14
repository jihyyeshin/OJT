package com.ojt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.ojt.domain.BasketVO;
import com.ojt.domain.SaleItemVO;
import com.ojt.domain.SaleVO;
import com.ojt.service.SaleService;
/****************************************************�ֹ� ���� Func***************************************************************/

@Controller
@RequestMapping("/items/*")
public class SaleController {
	@Inject
	SaleService service;
	private static final Logger Logger = LoggerFactory.getLogger(LocController.class);
	/******************************************�⺻ �ֹ� Func******************************************/
	// �ֹ�
	@RequestMapping(value = "/sale", method = RequestMethod.GET)
	public String getSale() {
		System.out.println("/items/sale (get)");
		return "notFound";
	}

	@RequestMapping(value = "/sale", method = RequestMethod.POST)
	public String postSale(HttpServletRequest req, String[] agent, String agentF, String agentA, String memberid, String[] name, String[] itemchk,
			int[] amount, int[] qty, String delivDateF, String delivDateA) throws Exception {
		Logger.info("post sale");
		System.out.println("/items/sale (post)");
		HttpSession session = req.getSession();
		Date time = new Date();
		String delivDate="";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dtime = format.format(time);

		// �ݾ� ��ü
		int totA = 0;
		int totF = 0;
		for (int i = 0; i < itemchk.length; i++) {
			if(agent[i].equals(agentF)) {
				totF += amount[i] * qty[i];
				delivDate=delivDateF;
			}
			else if(agent[i].equals(agentA)) {
				totA += amount[i] * qty[i];
				delivDate=delivDateA;
			}
			
			SaleItemVO sivo = new SaleItemVO(dtime, "",amount[i] * qty[i], amount[i], agent[i], itemchk[i], qty[i], i + 1,
					memberid, "", delivDate);
			service.saleItem(sivo); // �ֹ� ������ insert

			// �ֹ� ������ ���� ��, sold=1 ������� ��
			service.recAdd(sivo);
		}
		SaleVO svo = new SaleVO();
		
		// �ż�
		if(totF!=0) {
			svo = new SaleVO(dtime, totF, agentF, memberid);
			service.sale(svo); // �ֹ� ���� insert
		}
		// ���
		if(totA!=0) {
			svo = new SaleVO(dtime, totA, agentA, memberid);
			service.sale(svo); // �ֹ� ���� insert
		}
		
		session.setAttribute("memberid", memberid);
		return "sale"; // �ֹ� �Ϸ�View
	}

	/******************************************�ֹ� Ȯ��(��۳�¥�Է�)******************************************/
	// �ֹ� Final Check (��� ��¥ ����, �ֹ��� ���� Ȯ��)
	@RequestMapping(value = "/saleCheck", method = RequestMethod.POST)
	public String postSaleCheck(HttpServletRequest req, String[] agent, String agentF, String agentA, String memberid, 
			String[] name, String[] itemchk, int[] amount, int[] qty, String saleDiv, int[] idx) {
		System.out.println("/items/saleCheck (post)");
		HttpSession session = req.getSession();
		// idx ����: ��ٱ��Ͽ��� �ֹ��� ��ǰ ���� �� ���
		int totVal=0;
		List<SaleItemVO> slist = new ArrayList<SaleItemVO>();
		
		for (int i = 0; i < itemchk.length; i++) {
			SaleItemVO sivo = new SaleItemVO("", "",amount[i] * qty[i], amount[i], agent[i], itemchk[i], qty[i], i + 1,
					memberid, name[i], "");
			
			if(agent[i].equals(agentF)) sivo.setGbn_agent('F');
			else sivo.setGbn_agent('A');

			if(saleDiv.equals("saleBasket")) {
				sivo.setIdx(idx[i]);
			}
			slist.add(sivo);
			
			totVal+=(amount[i] * qty[i]);
		}
		Gson gson=new Gson();
		String jlist=gson.toJson(slist);
		session.setAttribute("slist", jlist);
		session.setAttribute("totVal", totVal);
		session.setAttribute("agentF", agentF);
		session.setAttribute("agentA", agentA);
		session.setAttribute("memberid", memberid);
		session.setAttribute("saleDiv", saleDiv);
		return "saleCheck";
	}

	// �״�� �ֹ� Final Check (��� ��¥ ����, �ֹ��� ���� Ȯ��)
	@RequestMapping(value = "/saleSameCheck", method = RequestMethod.POST)
	public String postSaleSameCheck(HttpServletRequest req, String[] agentS, String agentF, String agentA, String memberid,
			String[] nameS, String[] itemchkS, int[] amountS, int[] qtyS, String saleDiv) {
		System.out.println("/items/saleSameCheck (post)");
		HttpSession session = req.getSession();
		int totVal = 0;
		List<SaleItemVO> slist = new ArrayList<SaleItemVO>();
		for (int i = 0; i < itemchkS.length; i++) {
			SaleItemVO sivo = new SaleItemVO("", "", amountS[i] * qtyS[i], amountS[i], agentS[i], itemchkS[i], qtyS[i], i + 1,
					memberid, nameS[i], "");

			if (agentS[i].equals(agentF))
				sivo.setGbn_agent('F');
			else
				sivo.setGbn_agent('A');
			
			slist.add(sivo);

			totVal += (amountS[i] * qtyS[i]);
		}
		Gson gson = new Gson();
		String jlist = gson.toJson(slist);
		session.setAttribute("slist", jlist);
		session.setAttribute("totVal", totVal);
		session.setAttribute("agentF", agentF);
		session.setAttribute("agentA", agentA);
		session.setAttribute("memberid", memberid);
		session.setAttribute("saleDiv", saleDiv);
		return "saleCheck";
	}
	/******************************************��ٱ��� View******************************************/
	// ��ٱ��� ��ȸ
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public String getBasket(HttpServletRequest req, String agentF, String agentA, String memberid) {
		System.out.println("/items/basket(get) - basketPost");
		return "basketPost";
	}

	@RequestMapping(value = "/basket", method = RequestMethod.POST)
	public String postBasket(HttpServletRequest req, String memberid, String agentF, String agentA, String[] name, String[] itemchk,
			int[] amount, int[] qty/* , String type */) throws Exception {
		Logger.info("post basket");
		HttpSession session = req.getSession();
		System.out.println("/items/basket(post)");
		
		session.setAttribute("agentA", agentA);
		session.setAttribute("agentF", agentF);

		session.setAttribute("memberid", memberid);
		return "basket"; // ��ٱ��� View
	}
	/******************************************��ٱ��� ó��(��ȸ, �Է�, ����, �ֹ�) Func******************************************/
	// ��ٱ��� �ֱ�
	@RequestMapping(value = "/insertBasket", method = RequestMethod.POST)
	public String insertBasket(HttpServletRequest req, String[] agent, String agentF, String agentA, String memberid, String[] name, String[] itemchk,
			String itemDiv, int[] amount, int[] qty) throws Exception {
		System.out.println("/items/basket(input)");
		HttpSession session = req.getSession();
		if (itemDiv.equals("")) {// item list ȭ��
			for (int i = 0; i < itemchk.length; i++) {
				BasketVO bvo = new BasketVO(memberid, name[i], amount[i] * qty[i], amount[i], agent[i], itemchk[i],
						qty[i]);
				service.basket(bvo); // ��ٱ��� insert
			}
		} else {// item detail ȭ��
			BasketVO bvo = new BasketVO(memberid, name[0], amount[0] * qty[0], amount[0], agent[0], itemDiv, qty[0]);
			service.basket(bvo); // ��ٱ��� insert
		}
		session.setAttribute("agentF", agentF);
		session.setAttribute("agentA", agentA);
		session.setAttribute("memberid", memberid);
		return "redirect:/items";
	}

	// ��ٱ��� ������ ��ȸ
	@RequestMapping(value = "/showBasket")
	public @ResponseBody List<BasketVO> showBasket(@RequestParam String memberid) throws Exception {
		List<BasketVO> list = service.basketList(memberid);
		System.out.println("/items/showBasket");
		return list;
	}
	
	// ��ٱ��� ����
	@RequestMapping(value = "/deleteBasket", method = RequestMethod.GET)
	public String deletedBasket(HttpServletRequest req, @RequestParam int idx, @RequestParam String memberid)
			throws Exception {
		Logger.info("delete basket");
		HttpSession session = req.getSession();
		System.out.println("/items/deleteBasket");
		service.deleteBasket(idx);
		session.setAttribute("memberid", memberid);
		return "redirect:/items/basket";
	}

	// ��ٱ��Ͽ��� �ֹ�(�ֹ��� ��ǰ�� ����)
	@RequestMapping(value = "/saleBasket", method = RequestMethod.POST)
	public String postBasketSale(HttpServletRequest req, String[] agent, String agentF, String agentA, String memberid, String[] name, String[] itemchk,
			int[] amount, int[] qty, int[] idx, String delivDateA, String delivDateF) throws Exception {
		Logger.info("post sale basket");
		System.out.println(agent[0]);
		System.out.println(agentF);
		System.out.println(agentA);
		System.out.println(memberid);
		System.out.println(name[0]);
		System.out.println(itemchk);
		System.out.println(amount[0]);
		System.out.println(qty[0]);
		HttpSession session = req.getSession();
		String delivDate="";
		Date time = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dtime = format.format(time);

		int totF = 0;
		int totA = 0;
		for (int i = 0; i < itemchk.length; i++) {
			
			if(agent[i].equals(agentF)) {
				totF += amount[i] * qty[i];
				delivDate=delivDateF;
			}
			else if(agent[i].equals(agentA)) {
				totA += amount[i] * qty[i];
				delivDate=delivDateA;
			}

			SaleItemVO sivo = new SaleItemVO(dtime, "",amount[i] * qty[i], amount[i], agent[i], itemchk[i], qty[i], i + 1,
					memberid, "", delivDate);
			service.saleItem(sivo); // �ֹ� ������ insert

			// �ֹ� ������ ���� ��, sold=1 ������� ��
			service.recAdd(sivo);

			// �ֹ��� ������ ��ٱ��Ͽ��� delete
			service.deleteBasket(idx[i]);
		}
		SaleVO svo = new SaleVO();

		if(totF != 0) {
			svo = new SaleVO(dtime, totF, agentF, memberid);
			service.sale(svo); // �ֹ� ���� insert
		}
		
		if(totA != 0) {
			svo = new SaleVO(dtime, totA, agentA, memberid);
			service.sale(svo); // �ֹ� ���� insert
		}
		
		session.setAttribute("memberid", memberid);
		session.setAttribute("agentF", agentF);
		session.setAttribute("agentA", agentA);
		return "sale"; // �ֹ� �Ϸ�View
	}
	
	/******************************************�ֱ� ���� ��� ó��(��ȸ, �ֹ�)******************************************/
	// ��ٱ��� - �ֱ� ���� ��� ��ȸ
	@RequestMapping(value="/showRecent")
	public @ResponseBody List<SaleItemVO> showRecent(@RequestParam String memberid) throws Exception{
		System.out.println("/items/showRecent");
		System.out.println("show recent memberid"+memberid);
		List<SaleItemVO> list = service.showRecent(memberid);
		
		return list;
	}

	
	// �ֱ� �ֹ��� ��ǰ �����ϰ� �ֹ�
	@RequestMapping(value = "/saleRecent", method = RequestMethod.POST)
	public String postRecentSale(HttpServletRequest req, String[] agent, String agentF, String agentA, String memberid,
			String[] itemchk, int[] amount, int[] qty, String delivDateF, String delivDateA) throws Exception {
		Logger.info("post sale recent");
		System.out.println("post sale recent");

		HttpSession session = req.getSession();
		String delivDate="";
		Date time = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dtime = format.format(time);

		int totF = 0;
		int totA = 0;
		System.out.println(itemchk.length);
		for (int i = 0; i < itemchk.length; i++) {

			if(agent[i].equals(agentF)) {
				totF += amount[i] * qty[i];
				delivDate=delivDateF;
			}
			else if(agent[i].equals(agentA)) {
				totA += amount[i] * qty[i];
				delivDate=delivDateA;
			}

			SaleItemVO sivo = new SaleItemVO(dtime, "", amount[i] * qty[i], amount[i], agent[i], itemchk[i], qty[i],
					i + 1, memberid, "", delivDate);
			service.saleItem(sivo); // �ֹ� ������ insert

			// �ֹ� ������ ���� ��, sold=1 ������� ��
			service.recAdd(sivo);
		}
		SaleVO svo = new SaleVO();

		if (totF != 0) {
			svo = new SaleVO(dtime, totF, agentF, memberid);
			service.sale(svo); // �ֹ� ���� insert
		}

		if (totA != 0) {
			svo = new SaleVO(dtime, totA, agentA, memberid);
			service.sale(svo); // �ֹ� ���� insert
		}

		session.setAttribute("memberid", memberid);
		session.setAttribute("agentF", agentF);
		session.setAttribute("agentA", agentA);
		return "sale"; // �ֹ� �Ϸ�View
	}
}
