package com.ojt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {
	private static final Logger Logger=LoggerFactory.getLogger(LocController.class);
	
	// 주문
	@RequestMapping(value = "/items/order", method = RequestMethod.POST)
	public String postOrder(String memberid, String item, String amount, String qty) throws Exception{
		Logger.info("post order");
		System.out.println("memberid: "+memberid+" item: "+item+" amount: "+amount+" qty: "+qty);
		//service.signup(vo);
		return "order"; //주문 완료화면
	}
}
