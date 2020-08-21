package com.study.free.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.exception.DaoDuplicateKeyException;
import com.study.exception.DaoException;
import com.study.free.dao.FreeBoardDaoOracle;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;
import com.study.member.vo.MemberVO;

public class FreeBoardServiceImpl implements IFreeBoardService{

	private IFreeBoardDao boardDao = new FreeBoardDaoOracle(); 
	
	@Override
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			//건수를 구해서 searchVO설정 -> searchVO.pageSetting()
			int cnt = boardDao.getBoardCount(conn, searchVO);
			searchVO.setTotalRowCount(cnt);
			searchVO.pageSetting();
			
			List<FreeBoardVO> list = boardDao.getBoardList(conn, searchVO);
			return list;
		} catch (SQLException e) {
			throw new DaoException("조회시", e);
		}finally {
			if(conn != null)try{conn.close();}catch(SQLException e){e.printStackTrace();}
		}
	} //getBoardList

	@Override
	public FreeBoardVO getBoard(int boNo) throws BizNotFoundException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			FreeBoardVO vo = boardDao.getBoard(conn, boNo);
			if (vo == null) {
				throw new BizNotFoundException("["+boNo+"] 조회 실패");
			}
			return vo;
		} catch (SQLException e) {
			throw new DaoException("조회시", e);
		}finally {
			if(conn != null)try{conn.close();}catch(SQLException e){e.printStackTrace();}
		}
	}

	@Override
	public void registBoard(FreeBoardVO board) throws DaoException{
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			boardDao.insertBoard(conn, board);
			} catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(conn != null)try{conn.close();}catch(SQLException e){e.printStackTrace();}
		}
	}

	@Override
	public void modifyBoard(FreeBoardVO board)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			FreeBoardVO vo = boardDao.getBoard(conn, board.getBoNo());
			if (vo == null) {
				throw new BizNotFoundException("["+board.getBoNo()+"] 조회 실패");
			}
			if (!vo.getBoPass().equals(board.getBoPass())) {
				throw new BizPasswordNotMatchedException("["+board.getBoNo()+"] 비밀번호 불일치");
			}
			int cnt = boardDao.updateBoard(conn, board);
			if (cnt < 1) {
				throw new BizNotEffectedException("["+board.getBoNo()+"] 수정 실패");
			}
		} catch (SQLException e) {
			throw new DaoException("조회시", e);
		}finally {
			if(conn != null)try{conn.close();}catch(SQLException e){e.printStackTrace();}
		}
	}

	@Override
	public void removeBoard(FreeBoardVO board)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			FreeBoardVO vo = boardDao.getBoard(conn, board.getBoNo());
			if (vo == null) {
				throw new BizNotFoundException("["+board.getBoNo()+"] 조회 실패");
			}
			if (!vo.getBoPass().equals(board.getBoPass())) {
				throw new BizPasswordNotMatchedException("["+board.getBoNo()+"] 비밀번호 불일치");
			}
			int cnt = boardDao.deleteBoard(conn, board);
			if (cnt < 1) {
				throw new BizNotEffectedException("["+board.getBoNo()+"] 수정 실패");
			}
		} catch (SQLException e) {
			throw new DaoException("조회시", e);
		}finally {
			if(conn != null)try{conn.close();}catch(SQLException e){e.printStackTrace();}
		}
	}

	@Override
	public void increaseHit(int boNo) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			boardDao.increaseHit(conn, boNo);
			} catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(conn != null)try{conn.close();}catch(SQLException e){e.printStackTrace();}
		}
	}
	
}
