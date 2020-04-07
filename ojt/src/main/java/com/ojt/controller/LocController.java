package com.ojt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ojt.domain.LocVO;
import com.ojt.domain.ItemVO;
import com.ojt.domain.LatLngVO;
import com.ojt.service.LocService;

@Controller
public class LocController {
	@Inject
	LocService service;
	
	private static final Logger Logger=LoggerFactory.getLogger(LocController.class);
	
	@RequestMapping(value = "/location", method = RequestMethod.GET)
	public String getLocation() {
		Logger.info("get location");
		return "location";
	}
	// 위경도 별 가장 가까운 거리 조회
	// ajax 통신 시 utf-8 유지
	@RequestMapping(value="/locationTest",produces = "application/text; charset=utf8",method=RequestMethod.POST)
	@ResponseBody
	public String postLocation( @RequestParam String location
	        , @RequestParam float lat
	        , @RequestParam float lng
	        , @RequestParam char gbn) throws Exception {
		Logger.info("post location");
		LatLngVO vo=new LatLngVO(location, lat, lng, gbn);
		System.out.println(vo.getLat()+" "+ vo.getLng()+" "+ vo.getGbn());
		LocVO result=service.location(vo);
		// 결과가 없는 경우
		/* 시간 나면 예외처리 더 해주기! */
		if(result==null) {
			// 한번 더 검색
			String[] spl=vo.getLocation().split(" ");
			vo.setLocation(spl[0]);
			LocVO altResult=service.location(vo);
			if (altResult==null) return "결과가 없습니다.\n입력 위치를 다시 확인해주세요.";
			result=altResult;
		}
		return result.getAgent()+"|"+result.getAgentName()+"|"+result.getZip()+" "+result.getDetail();
	}
	
	// 대리점 별 아이템 , 가격 조회
	@RequestMapping(value="/showItem")
	public @ResponseBody List<ItemVO> showItem(@RequestParam String agent) throws Exception {
		List<ItemVO> list=service.itemList(agent);
		
		return list;
	}
}
