package com.study.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.code.vo.CodeVO;
import com.study.exception.DaoException;

public class CommonCodeDaoOracle implements ICommonCodeDao{
	@Override
	public List<CodeVO> getCodeListByParent(Connection conn, String parentCode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		List<CodeVO> list = new ArrayList<CodeVO>();
		
		try {
			 sb.append("SELECT		  					");
		    sb.append("     comm_cd     	  			");
		    sb.append("     , comm_nm	      			 ");
		    sb.append("     , comm_parent			    ");
		    sb.append("     , comm_ord			 		 ");
		    sb.append(" FROM		 						 ");
		    sb.append("     comm_code		 			 ");
		    sb.append(" WHERE comm_parent = ?			 ");
		    sb.append(" ORDER BY comm_ord ASC          ");
		    
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); // \s = 공백이 2, = 2개이상인
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, parentCode);
			rs = pstmt.executeQuery();
			
			CodeVO code = null;
			while(rs.next()) {
				code = new CodeVO(); //하나하나 값에 모두 다른 주소값을 줌
				code.setCommCd(rs.getString("comm_cd"));	
				code.setCommNm(rs.getString("comm_nm"));	
				code.setCommOrd(rs.getInt("comm_ord"));	
				code.setCommParent(rs.getString("comm_parent"));	
				list.add(code);
			} //while
			
			return list;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(rs != null)try{rs.close();}catch(SQLException e){e.printStackTrace();}
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){e.printStackTrace();}
		}
	}
}
