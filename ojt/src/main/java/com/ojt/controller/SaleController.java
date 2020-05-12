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

import com.ojt.domain.BasketVO;
import com.ojt.domain.DataSet;
import com.ojt.domain.ItemVO;
import com.ojt.domain.RecVO;
import com.ojt.domain.SaleItemVO;
import com.ojt.domain.SaleListVO;
import com.ojt.domain.SaleVO;
import com.ojt.service.SaleService;

@Controller
@RequestMapping("/items/*")
public class SaleController {
	@Inject
	SaleService service;
	private static final Logger Logger = LoggerFactory.getLogger(LocController.class);

	// 주문
	@RequestMapping(value = "/sale", method = RequestMethod.GET)
	public String getSale() {
		System.out.println("/items/sale (get)");
		return "notFound";
	}

	@RequestMapping(value = "/sale", method = RequestMethod.POST)
	public String postSale(HttpServletRequest req, String[] agent, String agentF, String agentA, String memberid, String[] name, String[] itemchk,
			int[] amount, int[] qty) throws Exception {
		Logger.info("post sale");
		System.out.println("/items/sale (post)");
		HttpSession session = req.getSession();
		Date time = new Date();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dtime = format.format(time);

		int totA = 0;
		int totF = 0;
		for (int i = 0; i < itemchk.length; i++) {
			if(agent[i].equals(agentF)) totF += amount[i] * qty[i];
			else if(agent[i].equals(agentA)) totA += amount[i] * qty[i];
			
			SaleItemVO sivo = new SaleItemVO(dtime, "",amount[i] * qty[i], amount[i], agent[i], itemchk[i], qty[i], i + 1,
					memberid, "");
			service.saleItem(sivo); // 주문 아이템 insert

			// 주문 아이템 넣을 때, sold=1 시켜줘야 함
			service.recAdd(sivo);
		}
		SaleVO svo = new SaleVO();
		
		if(totF!=0) {
			svo = new SaleVO(dtime, totF, agentF, memberid);
			service.sale(svo); // 주문 정보 insert
		}
		
		if(totA!=0) {
			svo = new SaleVO(dtime, totA, agentA, memberid);
			service.sale(svo); // 주문 정보 insert
		}
		
		session.setAttribute("memberid", memberid);
		return "sale"; // 주문 완료View
	}

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

	// 장바구니 넣기
	@RequestMapping(value = "/insertBasket", method = RequestMethod.POST)
	public String insertBasket(HttpServletRequest req, String[] agent, String agentF, String agentA, String memberid, String[] name, String[] itemchk,
			String item, int[] amount, int[] qty) throws Exception {
		System.out.println("/items/basket(input)");
		HttpSession session = req.getSession();

		if (item.equals("")) {// item list 화면
			for (int i = 0; i < itemchk.length; i++) {
				BasketVO bvo = new BasketVO(memberid, name[i], amount[i] * qty[i], amount[i], agent[i], itemchk[i],
						qty[i]);
				service.basket(bvo); // 장바구니 insert
			}
		} else {// item detail 화면
			BasketVO bvo = new BasketVO(memberid, name[0], amount[0] * qty[0], amount[0], agent[0], item, qty[0]);
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
	
	// 장바구니 - 최근 구매 목록 조회
	@RequestMapping(value="/showRecent")
	public @ResponseBody List<SaleItemVO> showRecent(@RequestParam String memberid) throws Exception{
		System.out.println("/items/showRecent");
		System.out.println("show recent memberid"+memberid);
		List<SaleItemVO> list = service.showRecent(memberid);
		
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
			int[] amount, int[] qty, int[] idx) throws Exception {
		Logger.info("post sale basket");
		HttpSession session = req.getSession();

		Date time = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dtime = format.format(time);

		int totF = 0;
		int totA = 0;
		for (int i = 0; i < itemchk.length; i++) {
			
			if(agent[i].equals(agentF)) totF += amount[i] * qty[i];
			else if(agent[i].equals(agentA)) totA += amount[i] * qty[i];

			SaleItemVO sivo = new SaleItemVO(dtime, "",amount[i] * qty[i], amount[i], agent[i], itemchk[i], qty[i], i + 1,
					memberid, "");
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
	// 최근 주문한 상품 동일하게 주문!
	@RequestMapping(value = "/saleRecent", method = RequestMethod.POST)
	public String postRecentSale(HttpServletRequest req, String[] agent, String agentF, String agentA, String memberid,
			String[] item, int[] amount, int[] qty) throws Exception {
		Logger.info("post sale recent");
		System.out.println("post sale recent");
		System.out.println(agent[0]);
		System.out.println(agentF);
		System.out.println(agentA);
		System.out.println(memberid);
		System.out.println(item[0]);

		HttpSession session = req.getSession();

		Date time = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dtime = format.format(time);

		int totF = 0;
		int totA = 0;
		System.out.println(item.length);
		for (int i = 0; i < item.length; i++) {

			if (agent[i].equals(agentF))
				totF += amount[i] * qty[i];
			else if (agent[i].equals(agentA))
				totA += amount[i] * qty[i];

			SaleItemVO sivo = new SaleItemVO(dtime, "", amount[i] * qty[i], amount[i], agent[i], item[i], qty[i],
					i + 1, memberid, "");
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
	
	@RequestMapping(value="/showSaleList")
	public @ResponseBody List<SaleListVO> showSaleList(@RequestParam String memberid) throws Exception {
		System.out.println("showSaleList");
		List<SaleListVO> list=service.showSaleList(memberid);
		return list;
	}
}
