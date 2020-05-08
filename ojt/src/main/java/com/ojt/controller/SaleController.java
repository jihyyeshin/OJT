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
import com.ojt.domain.SaleItemVO;
import com.ojt.domain.SaleVO;
import com.ojt.service.SaleService;

@Controller
@RequestMapping("/items/*")
public class SaleController {
	@Inject
	SaleService service;
	private static final Logger Logger = LoggerFactory.getLogger(LocController.class);

	// �ֹ�
	@RequestMapping(value = "/sale", method = RequestMethod.GET)
	public String getSale() {
		System.out.println("/items/sale (get)");
		return "notFound";
	}

	@RequestMapping(value = "/sale", method = RequestMethod.POST)
	public String postSale(HttpServletRequest req, String agent, String memberid, String[] name, String[] itemchk,
			int[] amount, int[] qty) throws Exception {
		Logger.info("post sale");
		System.out.println("/items/sale (post)");
		HttpSession session = req.getSession();
		Date time = new Date();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dtime = format.format(time);

		int tot = 0;
		for (int i = 0; i < itemchk.length; i++) {
			tot += amount[i] * qty[i];
			SaleItemVO sivo = new SaleItemVO(dtime, amount[i] * qty[i], amount[i], agent, itemchk[i], qty[i], i + 1,
					memberid);
			service.saleItem(sivo); // �ֹ� ������ insert

			// �ֹ� ������ ���� ��, sold=1 ������� ��
			service.recAdd(sivo);
		}
		SaleVO svo = new SaleVO(dtime, tot, agent, memberid);

		service.sale(svo); // �ֹ� ���� insert

		session.setAttribute("memberid", memberid);
		return "sale"; // �ֹ� �Ϸ�View
	}

	// ��ٱ���
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public String getBasket(HttpServletRequest req, String agent, String memberid) {
		System.out.println("/items/basket(get) - basketPost");
		return "basketPost";
	}

	@RequestMapping(value = "/basket", method = RequestMethod.POST)
	public String postBasket(HttpServletRequest req, String agent, String memberid, String[] name, String[] itemchk,
			int[] amount, int[] qty/* , String type */) throws Exception {
		Logger.info("post basket");
		HttpSession session = req.getSession();
		System.out.println("/items/basket(post)");
		session.setAttribute("agent", agent);
		session.setAttribute("memberid", memberid);
		return "basket"; // ��ٱ��� View
	}

	// ��ٱ��� �ֱ�
	@RequestMapping(value = "/insertBasket", method = RequestMethod.POST)
	public String insertBasket(HttpServletRequest req, String agent, String memberid, String[] name, String[] itemchk,
			String item, int[] amount, int[] qty) throws Exception {
		System.out.println("/items/basket(input)");
		HttpSession session = req.getSession();

		if (item.equals("")) {// item list ȭ��
			for (int i = 0; i < itemchk.length; i++) {
				BasketVO bvo = new BasketVO(memberid, name[i], amount[i] * qty[i], amount[i], agent, itemchk[i],
						qty[i]);
				service.basket(bvo); // ��ٱ��� insert
			}
		} else {// item detail ȭ��
			BasketVO bvo = new BasketVO(memberid, name[0], amount[0] * qty[0], amount[0], agent, item, qty[0]);
			service.basket(bvo); // ��ٱ��� insert
		}
		session.setAttribute("agent", agent);
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
	public String postBasketSale(HttpServletRequest req, String agent, String memberid, String[] name, String[] itemchk,
			int[] amount, int[] qty, int[] idx) throws Exception {
		Logger.info("post sale basket");
		HttpSession session = req.getSession();

		Date time = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dtime = format.format(time);

		int tot = 0;
		System.out.println(itemchk.length);
		for (int i = 0; i < itemchk.length; i++) {
			tot += amount[i] * qty[i];
			SaleItemVO sivo = new SaleItemVO(dtime, amount[i] * qty[i], amount[i], agent, itemchk[i], qty[i], i + 1,
					memberid);
			service.saleItem(sivo); // �ֹ� ������ insert

			// �ֹ� ������ ���� ��, sold=1 ������� ��
			service.recAdd(sivo);

			// �ֹ��� ������ ��ٱ��Ͽ��� delete
			service.deleteBasket(idx[i]);
		}
		SaleVO svo = new SaleVO(dtime, tot, agent, memberid);
		service.sale(svo); // �ֹ� ���� insert
		session.setAttribute("memberid", memberid);
		return "sale"; // �ֹ� �Ϸ�View
	}
}
