package com.ojt.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ojt.domain.DataSet;
import com.ojt.domain.ItemVO;
import com.ojt.domain.RecVO;
import com.ojt.service.ItemService;

@Controller
public class ItemController {
	@Inject
	ItemService service;
	
	private static final Logger Logger=LoggerFactory.getLogger(LocController.class);
	
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String getItem() throws Exception {
		System.out.println("/items(get)");
		return "itemPost";
	}
	@RequestMapping(value="/items", method=RequestMethod.POST)
	public String postItem() {
		Logger.info("post items");
		System.out.println("/items(post)");
		return "items";
	}
	
	// 대리점 별 아이템 , 가격 조회
	@RequestMapping(value="/showItem")
	public @ResponseBody DataSet showItem(@RequestParam int page, @RequestParam String agentF, @RequestParam String agentA) throws Exception {
		System.out.println("showItem");
		DataSet set=new DataSet();
		RecVO vo=new RecVO();
		vo.setAgentF(agentF);
		vo.setAgentA(agentA);
		
		int totCnt=service.getListCnt(vo);
		
		/* 10개씩 출력 */
		if(page==1) {
			vo.setStartNum(1);
			vo.setEndNum(10);
		}else {
			vo.setStartNum(page+(9*(page-1)));
			vo.setEndNum(page*10);
		}
		
		List<ItemVO> list=service.itemList(vo);
		
		set.setList(list);
		set.setStartNum(vo.getStartNum());
		set.setTotCnt(totCnt);
		//System.out.println("totCnt"+totCnt);
		return set;
	}
	
	@RequestMapping(value="/showRecommendedItems")
	@ResponseBody
	public List<ItemVO> showRecItem(@RequestParam String agentF, String agentA, String memberid) throws Exception {
		System.out.println("showRecommendedItems");
		
		RecVO vo=new RecVO();
		vo.setAgentF(agentF);
		vo.setAgentA(agentA);
		vo.setMemberid(memberid);

		List<ItemVO> list=service.itemRecommendList(vo);
		return list;
	}
	@RequestMapping(value="/showLvlItems")
	@ResponseBody
	public List<ItemVO> showLvlItems(@RequestParam String agentF, String agentA, String memberid) throws Exception {
		System.out.println("showLvlItems");
		
		RecVO vo=new RecVO();
		vo.setAgentF(agentF);
		vo.setAgentA(agentA);
		vo.setMemberid(memberid);

		List<ItemVO> list=service.itemLvlList(vo);
		System.out.println("itejmjs?"+list.get(0).getItem());
		return list;
	}
	
	// 디테일 화면
	@RequestMapping(value="/items/detail", method=RequestMethod.POST)
	public String postDetail(HttpServletRequest req, String agentF, String agentA,
			/* String agent, */ String memberid, String item) throws Exception {
		Logger.info("post detail");
		HttpSession session = req.getSession();
		
		ItemVO vo=service.itemDetail(item);
		session.setAttribute("agentF", agentF);
		session.setAttribute("agentA", agentA);
		session.setAttribute("memberid", memberid);
		session.setAttribute("item", vo);

		String val=URLEncoder.encode(vo.getName(), "UTF-8");// 인코딩
		Connection.Response response = Jsoup.connect("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+val)
                .method(Connection.Method.GET)
                .execute();
		
		Document googleDocument = response.parse();
		
		Element thumb = googleDocument.select("a[class=thumb]").first();
		if(thumb==null) {
			session.setAttribute("src", "/app/resources/img/CJ_logo_black.png");
			return "itemDetail";
		}
		
		Elements img=thumb.select("img");
		if(img==null) {
			session.setAttribute("src", "/app/resources/img/CJ_logo_black.png");
			return "itemDetail";
		} 
		
		String src = img.attr("src");
		if(src==null) {
			session.setAttribute("src", "/app/resources/img/CJ_logo_black.png");
			return "itemDetail";
		}
	
		session.setAttribute("src", src);
		return "itemDetail";
	}
//	public void test() throws Exception {
//		String agent="153441";
//		List<ItemVO> list=service.itemList(agents);
//		String src="";
//		for(int i=0;i<list.size();i++) {
//			if(list.get(i).getSrc()!= null) continue;
//			
//			String input=list.get(i).getName();
//			String val=URLEncoder.encode(input, "UTF-8");// 인코딩
//			Connection.Response response = Jsoup.connect("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+val)
//	                .method(Connection.Method.GET)
//	                .execute();
//			
//			Document googleDocument = response.parse();
//			Element thumb = googleDocument.select("a[class=thumb]").first();
//			if(thumb==null) {
//				src= "";
//			}
//			else {
//				Elements img=thumb.select("img");
//				if(img==null) {
//					src= "";
//				} 
//				else {
//					src= img.attr("src");
//					if(src==null) {
//						src= "";
//					}
//				}
//			}
//			ItemVO voinsert = new ItemVO();
//			voinsert.setSrc(src);
//			voinsert.setName(list.get(i).getName());
//			service.itemCrawl(voinsert);
//		}
//		System.out.println("FIN");
//	}
}
