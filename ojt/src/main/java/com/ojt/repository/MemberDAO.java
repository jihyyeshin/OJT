package com.ojt.repository;
import com.ojt.domain.MemberVO;

public interface MemberDAO {
	// 회원가입
	public void signup(MemberVO vo) throws Exception;
	// 로그인
	public MemberVO login(MemberVO vo) throws Exception;
	// 로그인 날짜 업데이트
	public void loginDate(MemberVO vo) throws Exception;
	// 로그인 실패 처리: 실패 cnt+1
	public void loginFail(MemberVO vo) throws Exception;
	// 임시 비밀번호
	public void tempPwd(MemberVO vo) throws Exception;
}
