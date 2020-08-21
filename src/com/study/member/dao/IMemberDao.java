package com.study.member.dao;

import java.sql.Connection;
import java.util.List;

import com.study.free.vo.FreeBoardSearchVO;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

public interface IMemberDao {

	public int insertMember(Connection conn, MemberVO member);
	public int updateMember(Connection conn, MemberVO member);
	public int deleteMember(Connection conn, MemberVO member);
	public MemberVO getMember(Connection conn, String memId);
	public List<MemberVO> getMemberList(Connection conn, MemberSearchVO searchVO);
	
	public int getMemberCount(Connection conn, MemberSearchVO searchVO) ;
}
