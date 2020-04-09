package com.ojt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ojt.domain.SaleItemVO;
import com.ojt.domain.SaleVO;
import com.ojt.service.SaleService;

@Controller
public class SaleController {
	@Inject
	SaleService service;
	private static final Logger Logger=LoggerFactory.getLogger(LocController.class);
	
	@RequestMapping(value = "/items/sale", method = RequestMethod.GET)
	public String getSale() {
		return "notFound";
	}
	// 주문
	@RequestMapping(value = "/items/sale", method = RequestMethod.POST)
	public String postSale(HttpServletRequest req, String agent, String memberid, String[] name, String[] item, int[] price, int[] qty) throws Exception{
		Logger.info("post sale");
		HttpSession session = req.getSession();
		
		Date time=new Date();
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");	
		String dtime=format.format(time);
		
		int amount=0;
		for(int i=0;i<item.length;i++) {
			amount+=price[i]*qty[i];
			SaleItemVO sivo=new SaleItemVO(dtime, price[i]*qty[i], price[i], agent, item[i],qty[i], i+1);
			service.saleItem(sivo); // 주문 아이템 insert
		}
		SaleVO svo=new SaleVO(dtime, amount,agent, memberid);
		service.sale(svo); // 주문 정보 insert
		session.setAttribute("memberid", memberid);
		return "sale"; //주문 완료View
	}
}
