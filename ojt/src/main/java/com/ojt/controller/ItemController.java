package com.ojt.controller;

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

import com.ojt.domain.ItemVO;
import com.ojt.service.ItemService;

@Controller
public class ItemController {
	@Inject
	ItemService service;
	
	private static final Logger Logger=LoggerFactory.getLogger(LocController.class);
	
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String getItem() {
		System.out.println("/items(get)");
		return "itemPost";
	}
	@RequestMapping(value="/items", method=RequestMethod.POST)
	public String postItem() {
		Logger.info("post items");
		System.out.println("/items(post)");
		return "items";
	}
	
	// �븮�� �� ������ , ���� ��ȸ
	@RequestMapping(value="/showItem")
	public @ResponseBody List<ItemVO> showItem(@RequestParam String agent) throws Exception {
		List<ItemVO> list=service.itemList(agent);
		
		return list;
	}
	// ������ ȭ��
	@RequestMapping(value="/items/detail", method=RequestMethod.GET)
	public String postDetail(HttpServletRequest req, String item) throws Exception {
		Logger.info("post detail");
		HttpSession session = req.getSession();
		
		ItemVO vo=service.itemDetail(item);
		session.setAttribute("item", vo);
		return "itemDetail";
	}
}
