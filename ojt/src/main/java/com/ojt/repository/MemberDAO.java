package com.ojt.repository;
import com.ojt.domain.MemberVO;

public interface MemberDAO {
	// ȸ������
	public void signup(MemberVO vo) throws Exception;
	// �α���
	public MemberVO login(MemberVO vo) throws Exception;
	// �α��� ��¥ ������Ʈ
	public void loginDate(MemberVO vo) throws Exception;
}
