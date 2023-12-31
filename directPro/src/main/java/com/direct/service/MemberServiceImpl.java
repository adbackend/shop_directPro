package com.direct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direct.mapper.MemberMapper;
import com.direct.model.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper memberMapper;
	
	//회원가입
	@Override
	public void memberJoin(MemberVO memberVO) throws Exception {
		memberMapper.memberJoin(memberVO);
	}
	
	//아이디 중복검사
	@Override
	public int idCheck(String memberId) throws Exception {
		return memberMapper.idCheck(memberId);
	}
	
	//로그인
	@Override
	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		return memberMapper.memberLogin(memberVO);
	}
}
