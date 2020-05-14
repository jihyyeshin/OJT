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
/****************************************************주문 관련 Func***************************************************************/

@Controller
@RequestMapping("/items/*")
public class SaleController {
	@Inject
	SaleService service;
	private static final Logger Logger = LoggerFactory.getLogger(LocController.class);
	/******************************************기본 주문 Func******************************************/
	// 주문
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

		// 금액 전체
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
			service.saleItem(sivo); // 주문 아이템 insert

			// 주문 아이템 넣을 때, sold=1 시켜줘야 함
			service.recAdd(sivo);
		}
		SaleVO svo = new SaleVO();
		
		// 신선
		if(totF!=0) {
			svo = new SaleVO(dtime, totF, agentF, memberid);
			service.sale(svo); // 주문 정보 insert
		}
		// 상온
		if(totA!=0) {
			svo = new SaleVO(dtime, totA, agentA, memberid);
			service.sale(svo); // 주문 정보 insert
		}
		
		session.setAttribute("memberid", memberid);
		return "sale"; // 주문 완료View
	}

	/******************************************주문 확인(배송날짜입력)******************************************/
	// 주문 Final Check (배송 날짜 선택, 주문할 내역 확인)
	@RequestMapping(value = "/saleCheck", method = RequestMethod.POST)
	public String postSaleCheck(HttpServletRequest req, String[] agent, String agentF, String agentA, String memberid, 
			String[] name, String[] itemchk, int[] amount, int[] qty, String saleDiv, int[] idx) {
		System.out.println("/items/saleCheck (post)");
		HttpSession session = req.getSession();
		// idx 역할: 장바구니에서 주문한 상품 삭제 시 사용
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

	// 그대로 주문 Final Check (배송 날짜 선택, 주문할 내역 확인)
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
	/******************************************장바구니 View******************************************/
	// 장바구니 조회
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
		return "basket"; // 장바구니 View
	}
	/******************************************장바구니 처리(조회, 입력, 삭제, 주문) Func******************************************/
	// 장바구니 넣기
	@RequestMapping(value = "/insertBasket", method = RequestMethod.POST)
	public String insertBasket(HttpServletRequest req, String[] agent, String agentF, String agentA, String memberid, String[] name, String[] itemchk,
			String itemDiv, int[] amount, int[] qty) throws Exception {
		System.out.println("/items/basket(input)");
		HttpSession session = req.getSession();
		if (itemDiv.equals("")) {// item list 화면
			for (int i = 0; i < itemchk.length; i++) {
				BasketVO bvo = new BasketVO(memberid, name[i], amount[i] * qty[i], amount[i], agent[i], itemchk[i],
						qty[i]);
				service.basket(bvo); // 장바구니 insert
			}
		} else {// item detail 화면
			BasketVO bvo = new BasketVO(memberid, name[0], amount[0] * qty[0], amount[0], agent[0], itemDiv, qty[0]);
			service.basket(bvo); // 장바구니 insert
		}
		session.setAttribute("agentF", agentF);
		session.setAttribute("agentA", agentA);
		session.setAttribute("memberid", memberid);
		return "redirect:/items";
	}

	// 장바구니 아이템 조회
	@RequestMapping(value = "/showBasket")
	public @ResponseBody List<BasketVO> showBasket(@RequestParam String memberid) throws Exception {
		List<BasketVO> list = service.basketList(memberid);
		System.out.println("/items/showBasket");
		return list;
	}
	
	// 장바구니 삭제
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

	// 장바구니에서 주문(주문한 상품은 삭제)
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
			service.saleItem(sivo); // 주문 아이템 insert

			// 주문 아이템 넣을 때, sold=1 시켜줘야 함
			service.recAdd(sivo);

			// 주문한 아이템 장바구니에서 delete
			service.deleteBasket(idx[i]);
		}
		SaleVO svo = new SaleVO();

		if(totF != 0) {
			svo = new SaleVO(dtime, totF, agentF, memberid);
			service.sale(svo); // 주문 정보 insert
		}
		
		if(totA != 0) {
			svo = new SaleVO(dtime, totA, agentA, memberid);
			service.sale(svo); // 주문 정보 insert
		}
		
		session.setAttribute("memberid", memberid);
		session.setAttribute("agentF", agentF);
		session.setAttribute("agentA", agentA);
		return "sale"; // 주문 완료View
	}
	
	/******************************************최근 구매 목록 처리(조회, 주문)******************************************/
	// 장바구니 - 최근 구매 목록 조회
	@RequestMapping(value="/showRecent")
	public @ResponseBody List<SaleItemVO> showRecent(@RequestParam String memberid) throws Exception{
		System.out.println("/items/showRecent");
		System.out.println("show recent memberid"+memberid);
		List<SaleItemVO> list = service.showRecent(memberid);
		
		return list;
	}

	
	// 최근 주문한 상품 동일하게 주문
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
			service.saleItem(sivo); // 주문 아이템 insert

			// 주문 아이템 넣을 때, sold=1 시켜줘야 함
			service.recAdd(sivo);
		}
		SaleVO svo = new SaleVO();

		if (totF != 0) {
			svo = new SaleVO(dtime, totF, agentF, memberid);
			service.sale(svo); // 주문 정보 insert
		}

		if (totA != 0) {
			svo = new SaleVO(dtime, totA, agentA, memberid);
			service.sale(svo); // 주문 정보 insert
		}

		session.setAttribute("memberid", memberid);
		session.setAttribute("agentF", agentF);
		session.setAttribute("agentA", agentA);
		return "sale"; // 주문 완료View
	}
}
