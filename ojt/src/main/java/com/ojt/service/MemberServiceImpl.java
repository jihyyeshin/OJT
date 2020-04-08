package com.ojt.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ojt.domain.MemberVO;
import com.ojt.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
	@Inject
	private MemberDAO dao;
	
	@Override
	public void signup(MemberVO vo) throws Exception{
		dao.signup(vo);
	}
	
	@Override
	public MemberVO login(MemberVO vo )throws Exception{
		return dao.login(vo);
	}
	@Override
	public void loginDate(MemberVO vo) throws Exception{
		dao.loginDate(vo);
	}
	@Override
	public void loginFail(MemberVO vo) throws Exception{
		dao.loginFail(vo);
	}
}
