package com.ojt.repository;
import com.ojt.domain.MemberVO;

public interface MemberDAO {
	// ȸ������
	public void signup(MemberVO vo) throws Exception;
	// �α���
	public MemberVO login(MemberVO vo) throws Exception;
	// �α��� ��¥ ������Ʈ
	public void loginDate(MemberVO vo) throws Exception;
	// �α��� ���� ó��: ���� cnt+1
	public void loginFail(MemberVO vo) throws Exception;
	// �ӽ� ��й�ȣ
	public void tempPwd(MemberVO vo) throws Exception;
}
