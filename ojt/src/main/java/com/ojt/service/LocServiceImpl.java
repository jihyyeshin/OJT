package com.ojt.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ojt.domain.ItemVO;
import com.ojt.domain.LatLngVO;
import com.ojt.domain.LocVO;
import com.ojt.repository.LocDAO;

@Service
public class LocServiceImpl implements LocService{
	@Inject
	private LocDAO dao;
	
	@Override
	public List<LocVO> locationList(LocVO vo)throws Exception{
		return dao.locationList(vo);
	}
	
	@Override
	public LocVO location(LatLngVO vo)throws Exception{
		return dao.location(vo);
	}
}
