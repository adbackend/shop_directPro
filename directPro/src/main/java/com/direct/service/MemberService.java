package com.direct.service;

import com.direct.model.MemberVO;

public interface MemberService {
	
	public void memberJoin(MemberVO memberVO) throws Exception; //회원가입
	
	public int idCheck(String memberId) throws Exception; //아이디 중복 검사
	
	public MemberVO memberLogin(MemberVO memberVO) throws Exception; //로그인
	
}
