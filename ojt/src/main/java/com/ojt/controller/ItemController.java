package com.ojt.controller;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.gargoylesoftware.htmlunit.*;
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
			@RequestParam String agentA, @RequestParam String memberid) throws Exception {
		System.out.println("showItem");
		DataSet set=new DataSet();
		RecVO vo=new RecVO();
		vo.setAgentF(agentF);
		vo.setAgentA(agentA);
		vo.setMemberid(memberid);
		
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
		if(list.size()==0) {
			list=service.randRecommendList(vo);
		}
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
		if(list.size()==0) {
			list=service.randRecommendList(vo);
			
			return list;
		}
		else {
			List<ItemVO> result=new ArrayList<ItemVO>();
			
			int i=0;
			while(i<list.size()) {
				result.add(list.get(i));
				
				for(int j=0;j<list.size();j++) {
					int simil=result.get(i).getName().compareTo(list.get(j).getName());
					if(simil > -10000) { // 비슷한 경우
						list.remove(j);
					}
				}
				i++;
			}
			
			return result;
		}
		
	}
	/***********************************************************************************************/
		
	// 상품의 디테일 화면
	@RequestMapping(value="/items/detail", method=RequestMethod.POST)
	public String postDetail(HttpServletRequest req, String agentF, String agentA,
			String memberid, String item) throws Exception {
		Logger.info("post detail");
		System.out.println("post detail");
		HttpSession session = req.getSession();
		
		String returnVal="itemDetail";
		
		ItemVO vo=service.itemDetail(item);
		session.setAttribute("agentF", agentF);
		session.setAttribute("agentA", agentA);
		session.setAttribute("memberid", memberid);
		session.setAttribute("item", vo);

		/* 이미지 크롤링 */
		String val=URLEncoder.encode(vo.getName(), "UTF-8");// 인코딩
		String url="https://www.google.com/search?q="+val
				+"&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiSpOf5vL_pAhWCF4gKHf-yC_wQ_AUoAXoECAwQAw&biw=1536&bih=775&dpr=1.25";
		
		Connection.Response response = Jsoup.connect(url)
	              .method(Connection.Method.GET)
	              .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
	              .execute();
			
			
		Document googleDocument = response.parse();
		
		String text=googleDocument.html();
		
		System.out.println("!!!!!!!1!!!!!!"+googleDocument.html().contains("https://encrypted"));
		
		System.out.println("!!!!!!!!"+googleDocument.html().indexOf("http://chinalife.co.kr/files/goods/01_14966.png"));
		int idx=googleDocument.html().indexOf("https://encrypted");
		String src="";
		for(int i=idx;i<idx+1000;i++) {
			char a=googleDocument.html().charAt(i);
			if(a != '\"') {
				src+=a;
				System.out.print(a);
			}
			else break;
		}
		
		
		try {
			String fileNm ="C:\\Users\\User\\Downloads\\Text\\t4.txt";
			File file = new File(fileNm);

			FileWriter fileWrite = new FileWriter(file, false);
			fileWrite.write(text);
	
			fileWrite.flush(); 
	
			fileWrite.close();
		}catch (Exception e){
			e.printStackTrace(); 
		}


		
//		Connection.Response response = Jsoup.connect("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+val)
//                .method(Connection.Method.GET)
//                .execute();
		
		
		
		//Element id=googleDocument.select("div[class=isv-r PNCib MSM1fd BUooTd]").first();
		//String attr=id.attr("data-tbnid");
		
		//url+=("#imgrc="+attr);
		//System.out.println("url:+"+url);

		
		//System.out.println(googleDocument.select("img[class=rg_i Q4LuWd]"));
		//src=googleDocument.select("img[class=rg_i Q4LuWd tx8vtf]").first().attr("src");
		//googleDocument.getElementsByAttributeValueContaining(key, match)
//		url=googleDocument.select("a[class=VFACy kGQAp]").first().attr("href");
//		Jsoup.connect(url)
//        .method(Connection.Method.GET)
//        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
//        .execute();
//		
//		googleDocument = response.parse();
		
		////////////////////////////
		
//		WebClient webClient = new WebClient();
//
//		webClient.waitForBackgroundJavaScriptStartingBefore(3000);
//        HtmlPage page = (HtmlPage) webClient.getPage(new URL(url));
//
//        final List<?> images = page.getByXPath("//img");
//        for (Object imageObject : images) {
//            HtmlImage image = (HtmlImage) imageObject;
//            if(image.getSrcAttribute().contains("https://")) {
//            	src=image.getSrcAttribute();
//            	break;
//            }
//        }		
        
        
        //src=http://img.cjthemarket.com/images/file/product/099/20191224151543044.jpg?RS=700x700
		
//		DomNodeList< DomElement > list = page.getElementsByTagName( "table" );
//		System.out.println(list);
//		String src="";
//		for(DomElement domElement:list) {
//			//System.out.println("TEXT:"+domElement.getAttribute("class"));
//			if( domElement.getAttribute("class").equals("TxbwNb") ) {
//				HtmlTable test= (HtmlTable) domElement;
//				page=(HtmlPage) test.click();
//				System.out.println(page.asXml());
//				break;
//			}
//		}
		
//		DomNodeList< DomElement > list = page.getElementsByTagName( "img" );
//		String src="";
//		for(DomElement domElement:list) {
//			System.out.println("TEXT:"+domElement.getAttribute("class"));
//			if( domElement.getAttribute("class").equals("t0fcAb") ) {
//				System.out.println("HERE");
//				HtmlImage test=(HtmlImage) domElement;
//				page=(HtmlPage) test.click();
//				// 한 가지 선택
//				
//				src=page.getElementsByTagName("img").get(0).getAttribute("src");
//				
//				
//				//System.out.println(page.asXml());
//				break;
//			}
//		}
//		System.out.println("FIN");
		
		
		//System.out.println(page.asXml());
		
		/* 더 마켓 홈페이지 처리 */
//		HtmlPage page = (HtmlPage) webClient.getPage("https://www.cjthemarket.com/");
//		
//		HtmlForm form=page.getFormByName("search");
//		
//		/* 문자 변환: 먼저 그냥 검색해본 후, 없다면 & 영어, 숫자 없앤 후 띄어쓰기 기준으로 전부 검색해보기 & 그래도 없으면 구글 이미지 가져와야 함 */
//		String searchVal=vo.getName();
//		searchVal.replaceAll("[a-z0-9)+]", "");// 영어, 숫자, 일부문자 제거
//		System.out.println("SearchVal"+searchVal);
//		
//        form.getInputByName("query").setValueAttribute(searchVal);//vo.getName());
//        
//        page=form.getHtmlElementsByTagName("button").get(1).click();
//        
//        List<DomElement> list=page.getElementsByTagName("div");
//        String src="";
//        for(DomElement l : list) {
//        	if(l.getAttribute("class").equals("thumb")) {
//        		src=l.getElementsByTagName("img").get(0).getAttribute("src");
//        		src=src.substring(0, src.length()-7);
//        		src+="700x700";
//        		System.out.println(src);
//        		break;
//        	}
//        }
        
        
		session.setAttribute("src", src);
		
		/***********************************************************/
//		Connection.Response response = Jsoup.connect(url)
//                .method(Connection.Method.GET)
//                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
//                .execute();
//		
//		
//		Document googleDocument = response.parse();
//		
//		Element id=googleDocument.select("div[class=isv-r PNCib MSM1fd BUooTd]").first();
//		System.out.println("ID:"+id.text());
//		String attr=id.attr("data-tbnid");
//		System.out.println("ATTR:"+attr);
//		
//		// 이미지 확대
//		url+=("#imgrc="+attr);
//		System.out.println("URL:"+url);
//		response = Jsoup.connect(url)
//                .method(Connection.Method.GET)
//                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
//                .execute();
//		
//		googleDocument = response.parse();
//		
//		Element div=googleDocument.select("a[class=wXeWr islib nfEiy mM5pbd]").first();//WaWKOe RfPPs]").first();
//		System.out.println("DIV!!!!!"+div);
//		String href=div.attr("href");
//		System.out.println("HREF"+href);
//		
//		
//
//		session.setAttribute("src", "/resources/img/CJ_logo_black.png");
//		
		/***********************************************************/
		
		
		//System.out.println("googleDocument"+googleDocument);
		//Element img=googleDocument.select("img[class=n3VNCb]").first();
		//System.out.println("IMG:"+img);
		//Node node=div.nextSibling();
		//System.out.println(node.toString());
//		Element img=googleDocument.select("div[class=A8mJGd]").first();
//		System.out.println("IMG!!:"+img);
//		String src=img.attr("src");
//		System.out.println("SRC:"+src);
//		session.setAttribute("src", src);
		
		
		/***********************************************************/
		/***********************************************************/

		
		
		
//		Element img=googleDocument.select("img[class=rg_i Q4LuWd tx8vtf]").first();
//		
//		String src=img.attr("src");
//		System.out.println(src);
		
//		Element a = googleDocument.select("a[class=wXeWr islib nfEiy mM5pbd]").first();
		//wXeWr islib nfEiy mM5pbd
//		if(a!=null) System.out.println("a:"+a);
		
//		String href=a.attr("href");
//		if(href!=null) {
//			System.out.println("jhrf:"+href);
//			System.out.println(href);
//			response=Jsoup.connect(href)
//                .method(Connection.Method.GET)
//                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
//                .execute();
//			googleDocument = response.parse();
//			Element img= googleDocument.select("img[class=irc_mi]").first();
//			if(img!=null) {
//				String src = img.attr("src");
//				if(src!=null) {
//					System.out.println("src"+src);
//					session.setAttribute("src", src);
//				}
//			}
//		}
		
//		if(thumb==null) {
//			session.setAttribute("src", "/resources/img/CJ_logo_black.png");
//			returnVal="itemDetail";
//		}else {
//			Elements img=thumb.select("img");
//			if(img==null) {
//				session.setAttribute("src", "/resources/img/CJ_logo_black.png");
//				returnVal="itemDetail";
//			}else {
//				String src = img.attr("src");
//				if(src==null) {
//					session.setAttribute("src", "/resources/img/CJ_logo_black.png");
//					returnVal="itemDetail";
//				}
//				else session.setAttribute("src", src);
//			}
//		}
		/* 인터넷 최저가 Func */
		val=URLEncoder.encode(vo.getName(), "UTF-8");// 인코딩
		response = Jsoup.connect(
				"https://search.shopping.naver.com/search/all.nhn?origQuery="+val+"&pagingIndex=1&pagingSize=40&viewType=list&sort=price_asc&frm=NVSCTAB&query="+val)
			    .method(Connection.Method.GET)
                .execute();
		googleDocument = response.parse();
		
		Element goodsList= googleDocument.select("ul[class=goods_list]").first();
		String LowestCost="";
		String LowestUrl="";
		if(goodsList==null) LowestCost="";
		else LowestCost=goodsList.select("li").eq(0).select("span[class=num _price_reload]").text();
		
		if(LowestCost=="") {
			LowestCost="(없음)";
			LowestUrl="#";
		}else{
			LowestCost+="원";
			LowestUrl=goodsList.select("li").eq(0).select("a[class=link]").attr("href");
		}

		session.setAttribute("LowestCost", LowestCost);
		session.setAttribute("LowestUrl", LowestUrl);
			
		return returnVal;
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
