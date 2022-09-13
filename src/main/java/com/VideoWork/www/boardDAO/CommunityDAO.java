package com.VideoWork.www.boardDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.VideoWork.www.boardDTO.CommunityDTO;

public class CommunityDAO {
	private static CommunityDAO communityDAO = new CommunityDAO();
	private String CONNECTION_POOL = "jdbc/project";
	private final String TABLE_NAME_BOARD = "communityboard";
	private final String TABLE_NAME_USER = "usermember";
	private DataSource dataSource;
	private int pageSize = 5;
	private final String GET_CONTENTS_NUM_SQL = "select max(no) from "+TABLE_NAME_BOARD;
	private final String INSERT_COMMUNITY_BOARD_SQL = "insert into " + TABLE_NAME_BOARD + " (id,nickname,title,contents,contentsnum) values (?,?,?,?,?)";
	private final String SELECT_COMMUNITYBOARD_SQL =
			"select * from "+TABLE_NAME_BOARD+" order by contentsnum desc, replynum asc limit ? , ?";
	private final String SELECT_COUNT_LIST =  "select count(*) from "+TABLE_NAME_BOARD;
	private final String INCREASE_VIEWCOUNT_SQL =
			"update "+TABLE_NAME_BOARD+" set viewcount = viewcount+1 where no = ?";
	private final String GET_COMMUNITYBOARD_DTO_SQL =
			"select um.*, cb.* from "+TABLE_NAME_BOARD+" cb, "+TABLE_NAME_USER+" um"+" where cb.no = ? and um.nickname = ?";
	private final String SELECT_BEST_CONTENTS_SQL =
			"select * from "+TABLE_NAME_BOARD+" order by viewcount desc limit 0, 5 ";
	private final String UPDATE_COMMUNITY_BOARD_SQL =
			"update "+TABLE_NAME_BOARD+" set contents = ?, wtime = now() where no = ?";
	private final String DELETE_COMMUNITY_BOARD_SQL =
			"delete from "+TABLE_NAME_BOARD+" where no=?";
	private final String UPDATE_REPLY_NUM_SQL =
			"update "+TABLE_NAME_BOARD+" set replynum = replynum+1 where contentsnum=? and replynum>=?";
	private final String INSERT_REPLY_SQL =
			"insert into "+TABLE_NAME_BOARD+" (id, nickname, title, contents, contentsnum, replynum, emptynum) values(?,?,?,?,?,?,?)";
	
	
	private CommunityDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/" + CONNECTION_POOL);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public static CommunityDAO getCommunityDAO() {
		return communityDAO;
	}
	
	public CommunityDTO getCommunityBoardDTO(CommunityDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(GET_COMMUNITYBOARD_DTO_SQL);
			ps.setInt(1, dto.getNo());
			ps.setString(2, dto.getNickname());
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setTitle(badWorld(rs.getString("title")));
				dto.setContents(badWorld(rs.getString("contents")));
				dto.setViewcount(rs.getInt("viewcount"));
				dto.setWtime(timeTextSet(rs.getString("wtime")));
				dto.setContentsnum(rs.getInt("contentsnum"));
				dto.setReplynum(rs.getInt("replynum"));
				dto.setEmptynum(rs.getInt("emptynum"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}return dto;
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void increaseViewCount(int no) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(INCREASE_VIEWCOUNT_SQL);
			ps.setInt(1, no);
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}

	public CommunityDTO communityReplyDAO(CommunityDTO dto) {
		getCommunityBoardDTO(dto);
		return dto;
	}
	
	public void updateContentsNum(CommunityDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(UPDATE_REPLY_NUM_SQL);
			ps.setInt(1, dto.getContentsnum());
			ps.setInt(2, dto.getReplynum()+1);
			result = ps.executeUpdate();
			System.out.println("result" + result);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	
	public void communityReplyOKDAO(CommunityDTO dto) {
		updateContentsNum(dto);
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(INSERT_REPLY_SQL);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getNickname());
			ps.setString(3, dto.getTitle());
			ps.setString(4, dto.getContents());
			ps.setInt(5, dto.getContentsnum());
			ps.setInt(6, dto.getReplynum()+1);
			ps.setInt(7, dto.getEmptynum()+1);
			result = ps.executeUpdate();
			System.out.println("hello3");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	
	public CommunityDTO communityBoardViewDAO(CommunityDTO dto) {
		increaseViewCount(dto.getNo());
		dto = getCommunityBoardDTO(dto);
		return dto;
	}
	
	public int getContentsnum() {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int conNum = 0;
		try {
			ps = conn.prepareStatement(GET_CONTENTS_NUM_SQL);
			rs = ps.executeQuery();
			if(rs.next()) {
				conNum = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return conNum + 1;
	}
	
	public void communityBoardWriteOKDAO(CommunityDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int conNum = getContentsnum();
		int result = 0;
		try {
			ps = conn.prepareStatement(INSERT_COMMUNITY_BOARD_SQL);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getNickname());
			ps.setString(3, dto.getTitle());
			ps.setString(4, dto.getContents());
			ps.setInt(5, conNum);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	
	public int listCnt() {
		int cnt = 0;
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(SELECT_COUNT_LIST);
			rs = ps.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return cnt;
	}
	
	public int calTotalPage() {
		int numArticle = listCnt();
		int totalPage = numArticle / pageSize ;
		if(numArticle != 0) {
			totalPage = numArticle % pageSize == 0 ? totalPage-1 : totalPage;
		}
		return totalPage;
	}
	
	public ArrayList<CommunityDTO> communityBoardDAO(int curPage){
		ArrayList<CommunityDTO> list = new ArrayList<CommunityDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(SELECT_COMMUNITYBOARD_SQL);
			ps.setInt(1, curPage*pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while(rs.next()) {
				CommunityDTO dto = new CommunityDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setTitle(badWorld(rs.getString("title")));
				dto.setContents(badWorld(rs.getString("contents")));
				dto.setViewcount(rs.getInt("viewcount"));
				dto.setWtime(timeTextSet(rs.getString("wtime")));
				dto.setContentsnum(rs.getInt("contentsnum"));
				dto.setReplynum(rs.getInt("replynum"));
				dto.setEmptynum(rs.getInt("emptynum"));
				list.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return list;
	}
	
	public ArrayList<CommunityDTO> communityBestBoardDAO(){
		ArrayList<CommunityDTO> list = new ArrayList<CommunityDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(SELECT_BEST_CONTENTS_SQL);
			rs = ps.executeQuery();
			while(rs.next()) {
				CommunityDTO communitydto = new CommunityDTO();
				communitydto.setNo(rs.getInt("no"));
				communitydto.setId(rs.getString("id"));
				communitydto.setTitle(badWorld(rs.getString("title")));
				communitydto.setNickname(rs.getString("nickname"));
				communitydto.setWtime(timeTextSet(rs.getString("wtime")));
				communitydto.setContentsnum(rs.getInt("contentsnum"));
				communitydto.setReplynum(rs.getInt("replynum"));
				communitydto.setEmptynum(rs.getInt("emptynum"));
				communitydto.setViewcount(rs.getInt("viewcount"));
				list.add(communitydto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return list;
	}
	
	public void communityModifyOKDAO(CommunityDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(UPDATE_COMMUNITY_BOARD_SQL);
			ps.setString(1, dto.getContents());
			ps.setInt(2, dto.getNo());
			System.out.println("hello");
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}close(ps, conn);
	}
	
	public void communityBoardDeleteDAO(int no) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(DELETE_COMMUNITY_BOARD_SQL);
			ps.setInt(1, no);
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	
	public String timeTextSet(String str) {
		str = str.substring(0,16);
		str = str.replace('-', '.');
		return str;
	}
	
	public String badWorld(String str) {
		str = str.replace("나쁜친구", "*");
		return str;
	}
	
	public void close(ResultSet rs, PreparedStatement ps, Connection conn) {

		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(PreparedStatement ps, Connection conn) {

		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
