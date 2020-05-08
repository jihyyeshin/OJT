package com.ojt.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

import com.ojt.domain.ItemVO;
import com.ojt.service.ItemService;

@Controller
public class ItemController {
	@Inject
	ItemService service;
	
	private static final Logger Logger=LoggerFactory.getLogger(LocController.class);
	
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String getItem() throws Exception {
		System.out.println("/items(get)");
		//test();
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
	public @ResponseBody List<ItemVO> showItem(@RequestParam String agent) throws Exception {
		List<ItemVO> list=service.itemList(agent);
		
		return list;
	}
	// 디테일 화면
	@RequestMapping(value="/items/detail", method=RequestMethod.POST)
	public String postDetail(HttpServletRequest req, String agent, String memberid, String item) throws Exception {
		Logger.info("post detail");
		HttpSession session = req.getSession();
		
		ItemVO vo=service.itemDetail(item);
		session.setAttribute("agent", agent);
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
	public void test() throws Exception {
		String agent="153441";
		List<ItemVO> list=service.itemList(agent);
		String src="";
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getSrc()!= null) continue;
			
			String input=list.get(i).getName();
			String val=URLEncoder.encode(input, "UTF-8");// 인코딩
			Connection.Response response = Jsoup.connect("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+val)
	                .method(Connection.Method.GET)
	                .execute();
			
			Document googleDocument = response.parse();
			Element thumb = googleDocument.select("a[class=thumb]").first();
			if(thumb==null) {
				src= "";
			}
			else {
				Elements img=thumb.select("img");
				if(img==null) {
					src= "";
				} 
				else {
					src= img.attr("src");
					if(src==null) {
						src= "";
					}
				}
			}
			ItemVO voinsert = new ItemVO();
			voinsert.setSrc(src);
			voinsert.setName(list.get(i).getName());
			service.itemCrawl(voinsert);
		}
		System.out.println("FIN");
	}
}
