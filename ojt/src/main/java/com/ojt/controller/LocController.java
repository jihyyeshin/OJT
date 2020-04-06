package com.ojt.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ojt.domain.LocVO;
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
	// ���浵 �� ���� ����� �Ÿ� ��ȸ
	// ajax ��� �� utf-8 ����
	@RequestMapping(value="/locationTest",produces = "application/text; charset=utf8",method=RequestMethod.POST)
	@ResponseBody
	public String postLocation( @RequestParam String location
	        , @RequestParam float lat
	        , @RequestParam float lng) throws Exception {
		Logger.info("post location");
		LatLngVO vo=new LatLngVO(location, lat, lng);
		System.out.println("this is loc: "+vo.getLocation()+", lat: "+vo.getLat()+", lng: "+vo.getLng());
		LocVO result=service.location(vo);
		// ����� ���� ���
		/* �ð� ���� ����ó�� �� ���ֱ�! */
		if(result==null) {
			// �ѹ� �� �˻�
			String[] spl=vo.getLocation().split(" ");
			vo.setLocation(spl[0]);
			LocVO altResult=service.location(vo);
			if (altResult==null) return "����� �����ϴ�.\n�Է� ��ġ�� �ٽ� Ȯ�����ּ���.";
			result=altResult;
		}
		return result.getAgent()+"|"+result.getAgentName()+"|"+result.getZip()+" "+result.getDetail();
	}
	
	// �븮�� �� ������ ��ȸ
	@RequestMapping(value="/showItem")
//	@ResponseBody
//	public @ResponseBody LatLngVO postShowItem(@RequestParam String agentId) {
//		
//		return agentId; 
//	}
	public @ResponseBody LatLngVO doC() {
		LatLngVO vo = new LatLngVO("���̽� ������", 0, 0);

		return vo;
	}
}
