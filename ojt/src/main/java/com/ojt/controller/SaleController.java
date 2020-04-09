package com.ojt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

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
	
	// 주문
	@RequestMapping(value = "/items/sale", method = RequestMethod.POST)
	public String postSale(String agent, String memberid, String[] item, int[] price, int[] qty) throws Exception{
		Logger.info("post sale");
		//System.out.println("agent: "+agent+" memberid: "+memberid+" item: "+item[1]+" price: "+price[1]+" qty: "+qty[1]);
		Date time=new Date();
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");	
		String dtime=format.format(time);
		//System.out.println("look dtime+"+dtime);
		
		int amount=0;
		for(int i=0;i<item.length;i++) {
			amount+=price[i]*qty[i];
			SaleItemVO sivo=new SaleItemVO(dtime, price[i]*qty[i], price[i], agent, item[i],qty[i], i+1);
			service.saleItem(sivo);
		}
		SaleVO svo=new SaleVO(dtime, amount,agent, memberid);
		service.sale(svo);
		return "sale"; //주문 완료화면
	}
}
