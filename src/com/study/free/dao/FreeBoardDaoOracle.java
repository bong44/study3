package com.study.free.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.study.exception.DaoException;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

public class FreeBoardDaoOracle implements IFreeBoardDao{
	
	@Override
	public int getBoardCount(Connection conn, FreeBoardSearchVO searchVO) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" SELECT count(*)				  ");
			sb.append(" FROM free_board        	  ");
			sb.append(" WHERE bo_del_yn = 'N' 		  ");
			//검색처리
			if (StringUtils.isNotEmpty(searchVO.getSearchCategory())) {
				sb.append(" AND bo_category = ?        	  		  ");
			}
			if (StringUtils.isNotEmpty(searchVO.getSearchWord())) {
				switch (searchVO.getSearchType()) {
				case "T": sb.append(" AND bo_title LIKE '%' || ? || '%'  "); break;
				case "W": sb.append(" AND bo_writer LIKE '%' || ? || '%'  "); break;
				case "C": sb.append(" AND bo_content LIKE '%' || ? || '%'  "); break;
				}
			}
			
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); // \s = 공백이 2, = 2개이상인
			pstmt = conn.prepareStatement(sb.toString());
			//bind 변수 설정(파라미터 변수)
			int i = 1;
			if (StringUtils.isNotEmpty(searchVO.getSearchCategory())) {
				pstmt.setString(i++, searchVO.getSearchCategory());
			}
			if (StringUtils.isNotEmpty(searchVO.getSearchWord())) {
				pstmt.setString(i++, searchVO.getSearchWord());
			}
			rs = pstmt.executeQuery();
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt(1);
			} //while
			return cnt;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(rs != null)try{rs.close();}catch(SQLException e){e.printStackTrace();}
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){e.printStackTrace();}
		}
	}

	@Override
	public List<FreeBoardVO> getBoardList(Connection conn, FreeBoardSearchVO searchVO) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		List<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
		
		try {
			sb.append("select *                                                   "); 
		    sb.append("from(select rownum rnum , a.*                              "); 
		    sb.append("    from(                                                  "); 
		    
			sb.append(" SELECT											  ");
		    sb.append("     bo_no					     				  ");
		    sb.append("     , bo_title								      ");
		    sb.append("     , bo_category								      ");
		    sb.append("     , b.comm_nm AS bo_category_nm				");
		    sb.append("     , bo_writer									  ");
		    sb.append("     , bo_pass									  ");
		    sb.append("     , bo_ip						 				 ");
		    sb.append("     , bo_hit									  ");
		    sb.append("     , bo_reg_date	  					         ");
			sb.append("     , bo_mod_date  						         ");
			sb.append("     , bo_del_yn					      			   ");
			sb.append(" FROM free_board , comm_code b		        	  ");
			sb.append(" where bo_category = b.comm_cd 			     ");
			//검색처리
			if (StringUtils.isNotEmpty(searchVO.getSearchCategory())) {
				sb.append(" AND bo_category = ?        	  		  ");
			}
			if (StringUtils.isNotEmpty(searchVO.getSearchWord())) {
				switch (searchVO.getSearchType()) {
				case "T": sb.append(" AND bo_title LIKE '%' || ? || '%'  "); break;
				case "W": sb.append(" AND bo_writer LIKE '%' || ? || '%'  "); break;
				case "C": sb.append(" AND bo_content LIKE '%' || ? || '%'  "); break;
				}
			}
			sb.append("  and bo_del_yn = 'N'			               ");
			sb.append("  order by bo_no 			                  ");
		    sb.append("    				) a                             "); 
		    sb.append("    			where rownum <= ?) b                "); 
		    sb.append("			where rnum between ? and ?              "); 
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); // \s = 공백이 2, = 2개이상인
			pstmt = conn.prepareStatement(sb.toString());
			int i = 1;
			//바인드변수 처리
			if (StringUtils.isNotEmpty(searchVO.getSearchCategory())) {
				pstmt.setString(i++, searchVO.getSearchCategory());
			}
			if (StringUtils.isNotEmpty(searchVO.getSearchWord())) {
				pstmt.setString(i++, searchVO.getSearchWord());
			}
			
			pstmt.setInt(i++, searchVO.getLastRow());
			pstmt.setInt(i++, searchVO.getFirstRow());
			pstmt.setInt(i++, searchVO.getLastRow());
			rs = pstmt.executeQuery();
			FreeBoardVO free = null;
			while(rs.next()) {
				free = new FreeBoardVO(); //하나하나 값에 모두 다른 주소값을 줌
				free.setBoNo(rs.getInt("bo_no"));
				free.setBoTitle(rs.getString("bo_title"));
				free.setBoCategory(rs.getString("bo_category"));
				free.setBoCategoryNm(rs.getString("bo_category_nm"));
				free.setBoWriter(rs.getString("bo_writer"));
				free.setBoPass(rs.getString("bo_pass"));
				free.setBoIp(rs.getString("bo_ip"));
				free.setBoHit(rs.getInt("bo_hit"));
				free.setBoRegDate(rs.getString("bo_reg_date"));
				free.setBoModDate(rs.getString("bo_mod_date"));
				free.setBoDelYn(rs.getString("bo_del_yn"));
				list.add(free);
			} //while
			
			return list;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(rs != null)try{rs.close();}catch(SQLException e){e.printStackTrace();}
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){e.printStackTrace();}
		}
	}

	@Override
	public FreeBoardVO getBoard(Connection conn, int boNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" SELECT											  ");
		    sb.append("     bo_no					     				  ");
		    sb.append("     , bo_title								      ");
		    sb.append("     , bo_category								      ");
		    sb.append("     , b.comm_nm AS bo_category_nm				");
		    sb.append("     , bo_writer									  ");
		    sb.append("     , bo_pass									  ");
		    sb.append("     , bo_ip						 				 ");
		    sb.append("     , bo_hit									  ");
		    sb.append("     , bo_reg_date	  					         ");
			sb.append("     , bo_mod_date  						         ");
			sb.append("     , bo_del_yn					      			   ");
			sb.append("     , bo_content				      			   ");
			sb.append(" FROM free_board , comm_code b		        	  ");
			sb.append(" where bo_category = b.comm_cd 			     ");
			sb.append("  and bo_del_yn = 'N'			               ");
			sb.append(" and bo_no = ? 			                      ");
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); // \s = 공백이 2, = 2개이상인
			pstmt = conn.prepareStatement(sb.toString());
			//bind 변수 설정(파라미터 변수)
			pstmt.setInt(1, boNo);
			rs = pstmt.executeQuery();
			FreeBoardVO free = null;
			while(rs.next()) {
				free = new FreeBoardVO(); //하나하나 값에 모두 다른 주소값을 줌
				free.setBoNo(rs.getInt("bo_no"));
				free.setBoTitle(rs.getString("bo_title"));
				free.setBoCategory(rs.getString("bo_category"));
				free.setBoCategoryNm(rs.getString("bo_category_nm"));
				free.setBoWriter(rs.getString("bo_writer"));
				free.setBoPass(rs.getString("bo_pass"));
				free.setBoIp(rs.getString("bo_ip"));
				free.setBoHit(rs.getInt("bo_hit"));
				free.setBoRegDate(rs.getString("bo_reg_date"));
				free.setBoModDate(rs.getString("bo_mod_date"));
				free.setBoDelYn(rs.getString("bo_del_yn"));
				free.setBoContent(rs.getString("bo_content"));
			} //while
			
			return free;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(rs != null)try{rs.close();}catch(SQLException e){e.printStackTrace();}
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){e.printStackTrace();}
		}
	}

	@Override
	public int insertBoard(Connection conn, FreeBoardVO board) {
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" INSERT INTO     				    ");
		    sb.append(" free_board ( 					    ");
		    sb.append("     bo_no 							");
		    sb.append("     , bo_title 					    ");
		    sb.append("     , bo_category 					 ");
		    sb.append("     , bo_writer 					 ");
		    sb.append("     , bo_pass 					    ");
		    sb.append("     , bo_content 				    ");
		    sb.append("     , bo_ip 					    ");
		    sb.append("     , bo_hit 		  			    ");
			sb.append("     , bo_reg_date        		    ");
			sb.append("     , bo_mod_date      	     	 ");
			sb.append("     , bo_del_yn     				  ");
			sb.append(" ) VALUES (     						  ");
			sb.append("     seq_free_board.nextval     	  ");
			sb.append("     , ?         				  	  ");
			sb.append("     , ?     						  	  ");
			sb.append("     , ?     						  	  ");
			sb.append("     , ?     						  	  ");
			sb.append("     , ?     						  	  ");
			sb.append("     , ?     						  	  ");
			sb.append("     , ?     						  	  ");
			sb.append("     , sysdate     					  ");
			sb.append("     , ?     						  	  ");
			sb.append("     , 'N'     						  	  ");
			sb.append(" )     						     	     ");
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); // \s = 공백이 2, = 2개이상인
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, board.getBoTitle());
			pstmt.setString(2, board.getBoCategory());
			pstmt.setString(3, board.getBoWriter());
			pstmt.setString(4, board.getBoPass());
			pstmt.setString(5, board.getBoContent());
			pstmt.setString(6, board.getBoIp());
			pstmt.setInt(7, board.getBoHit());
			pstmt.setString(8, board.getBoModDate());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){e.printStackTrace();}
		}
	}

	@Override
	public int updateBoard(Connection conn, FreeBoardVO board) {
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" 	UPDATE free_board SET		     ");
		    sb.append(" 	      bo_title = ?		         ");
		    sb.append(" 	    , bo_category = ?		     ");
		    sb.append(" 	    , bo_writer = ?		        ");
		    sb.append(" 	    , bo_content = ?			    ");
		    sb.append(" 	    , bo_mod_date = sysdate		 ");
		    sb.append(" 	WHERE		                       ");
		    sb.append(" 	    bo_no = ?		           ");
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); // \s = 공백이 2, = 2개이상인
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, board.getBoTitle());
			pstmt.setString(2, board.getBoCategory());
			pstmt.setString(3, board.getBoWriter());
			pstmt.setString(4, board.getBoContent());
			pstmt.setInt(5, board.getBoNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){e.printStackTrace();}
		}
	}

	@Override
	public int deleteBoard(Connection conn, FreeBoardVO board) {
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" 	UPDATE free_board SET		     ");
		    sb.append(" 	      bo_del_yn = 'Y'	         ");
		    sb.append(" 	WHERE		                       ");
		    sb.append(" 	    bo_no = ?		                ");
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); // \s = 공백이 2, = 2개이상인
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, board.getBoNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){e.printStackTrace();}
		}
	}

	@Override
	public int increaseHit(Connection conn, int boNo) {
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(" 	UPDATE free_board SET		     ");
		    sb.append(" 	      bo_hit = bo_hit + 1		         ");
		    sb.append(" 	WHERE		                       ");
		    sb.append(" 	    bo_no = ?		           ");
			System.out.println(sb.toString().replaceAll("\\s{2,}", "")); // \s = 공백이 2, = 2개이상인
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, boNo);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		}finally {
			if(pstmt != null)try{pstmt.close();}catch(SQLException e){e.printStackTrace();}
		}
		
	}

}
