package com.ojt.controller;

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

import com.ojt.domain.DataSet;
import com.ojt.domain.ItemVO;
import com.ojt.domain.RecVO;
import com.ojt.service.ItemService;
/****************************************************위치 기반 대리점 선택***************************************************************/

@Controller
public class ItemController {
	@Inject
	ItemService service;
	
	private static final Logger Logger=LoggerFactory.getLogger(LocController.class);
	/******************************************아이템 View 처리******************************************/
	// 아이템 View 출력 (get, post 둘 다)
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
	/******************************************상품 조회 Func******************************************/
	// 대리점 별 아이템 , 가격 조회
	@RequestMapping(value="/showItem")
	public @ResponseBody DataSet showItem(@RequestParam int page, @RequestParam String agentF, 
			@RequestParam String agentA) throws Exception {
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
		return set;
	}
	// 베스트 아이템 기반 상품 추천
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
	// 베스트 아이템의 상품군 기반 상품 추천
	@RequestMapping(value="/showLvlItems")
	@ResponseBody
	public List<ItemVO> showLvlItems(@RequestParam String agentF, String agentA, String memberid) throws Exception {
		System.out.println("showLvlItems");
		
		RecVO vo=new RecVO();
		vo.setAgentF(agentF);
		vo.setAgentA(agentA);
		vo.setMemberid(memberid);

		List<ItemVO> list=service.itemLvlList(vo);
		return list;
	}
	/***********************************************************************************************/
		
	// 상품의 디테일 화면
	@RequestMapping(value="/items/detail", method=RequestMethod.POST)
	public String postDetail(HttpServletRequest req, String agentF, String agentA,
			String memberid, String item) throws Exception {
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
//		String val=URLEncoder.encode(vo.getName(), "UTF-8");// 인코딩
//		Connection.Response response = Jsoup.connect(
//				"https://search.shopping.naver.com/search/all.nhn?origQuery="+val+"&pagingIndex=1&pagingSize=40&viewType=list&sort=price_asc&frm=NVSCTAB&query="+val)
//			    .method(Connection.Method.GET)
//                .execute();
//		
//		Document googleDocument = response.parse();
//		
//		Element goodsList = googleDocument.select("ul[class=goods_list]").first();
//		if(goodsList==null) {
//			session.setAttribute("src", "/app/resources/img/CJ_logo_black.png");
//			return "itemDetail";
//		}
//		
//		Elements img=goodsList.select("li").eq(0).select("span[class=num _price_reload]");
//		System.out.println("img!!!!!!!"+img.text());
//		if(img==null) {
//			session.setAttribute("src", "/app/resources/img/CJ_logo_black.png");
//			return "itemDetail";
//		} 
//		
//		String src = img.attr("src");
//		if(src==null) {
//			session.setAttribute("src", "/app/resources/img/CJ_logo_black.png");
//			return "itemDetail";
//		}
//		session.setAttribute("src", src);
//			
			
		return "itemDetail";
	}
//  // 크롤링
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
