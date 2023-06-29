package com.direct.mapper;

import com.direct.model.MemberVO;

public interface MemberMapper {
	
	public void memberJoin(MemberVO member); // 회원가입

	public int idCheck(String memberId); // 아이디 중복검사
}
