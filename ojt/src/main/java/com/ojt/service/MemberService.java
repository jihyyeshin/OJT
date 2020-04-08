package com.ojt.service;

import com.ojt.domain.MemberVO;

public interface MemberService {
	public void signup(MemberVO vo )throws Exception;
	public MemberVO login(MemberVO vo )throws Exception;
	public void loginDate(MemberVO vo) throws Exception;
	public void loginFail(MemberVO vo) throws Exception;
}
