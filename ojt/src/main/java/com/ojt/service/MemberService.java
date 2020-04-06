package com.ojt.service;

import com.ojt.domain.MemberVO;

public interface MemberService {
	public void signup(MemberVO vo )throws Exception;
	public MemberVO login(MemberVO vo )throws Exception;
}
