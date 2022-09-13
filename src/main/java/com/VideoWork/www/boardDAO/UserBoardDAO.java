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

import com.VideoWork.www.boardDTO.UserBoardDTO;

public class UserBoardDAO {
	private static UserBoardDAO userBoardDAO = new UserBoardDAO();
	private String CONNECTION_POOL = "jdbc/project";
	private final String TABLE_NAME_BOARD = "userboard";
	private final String TABLE_NAME_USER = "usermember";
	private DataSource dataSource;
	private int pageSize = 5;
	private final String INSERT_USER_BOARD_SQL =
			"insert into " + TABLE_NAME_BOARD + " (id,username,title,contents,gender,programcount) values (?,?,?,?,?,?)";
	private final String SELECT_USERBOARD_SQL =
			"select * from "+TABLE_NAME_BOARD+" order by no desc limit ? , ?";
	private final String SELECT_COUNT_LIST =  "select count(*) from "+TABLE_NAME_BOARD;
	private final String GET_USERBOARD_DTO_SQL =
			"select um.*, cb.* from "+TABLE_NAME_BOARD+" cb, "+TABLE_NAME_USER+" um"+" where cb.no = ? and um.id = ?";
	private final String SELECT_BEST_USERBOARD_SQL =
			"select * from "+TABLE_NAME_BOARD+" order by programcount desc limit 0, 5 ";
	private final String UPDATE_USER_BOARD_SQL =
			"update "+TABLE_NAME_BOARD+" set programcount = ?, contents = ?, wtime = now() where no = ?";
	private final String DELETE_USER_BOARD_SQL =
			"delete from "+TABLE_NAME_BOARD+" where no=?";
	
	private UserBoardDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/" + CONNECTION_POOL);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static UserBoardDAO getUserBoardDAO() {
		return userBoardDAO;
	}
	
	public UserBoardDTO getUserBoardDTO(UserBoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(GET_USERBOARD_DTO_SQL);
			ps.setInt(1, dto.getNo());
			ps.setString(2, dto.getId());
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setUsername(nameSecretSet(rs.getString("username")));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setGender(rs.getString("gender"));
				dto.setProgramcount(rs.getInt("programcount"));
				dto.setWtime(timeTextSet(rs.getString("wtime")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return dto;
	}
	
	public void userBoardWriteOKDAO(UserBoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(INSERT_USER_BOARD_SQL);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getUsername());
			ps.setString(3, dto.getTitle());
			ps.setString(4, dto.getContents());
			ps.setString(5, dto.getGender());
			ps.setInt(6, dto.getProgramcount());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	
	public UserBoardDTO userBoardViewDAO(UserBoardDTO dto) {
		dto = getUserBoardDTO(dto);
		return dto;
	}
	
	public int calTotalPage() {
		int numArticle = listCnt();
		int totalPage = numArticle / pageSize ;
		if(numArticle != 0) {
			totalPage = numArticle % pageSize == 0 ? totalPage-1 : totalPage;
		}
		return totalPage;
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
	
	public ArrayList<UserBoardDTO> userBoardDAO(int curPage){
		ArrayList<UserBoardDTO> list = new ArrayList<UserBoardDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(SELECT_USERBOARD_SQL);
			ps.setInt(1, curPage*pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while(rs.next()) {
				UserBoardDTO dto = new UserBoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setUsername(nameSecretSet(rs.getString("username")));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setGender(rs.getString("gender"));
				dto.setProgramcount(rs.getInt("programcount"));
				dto.setWtime(timeTextSet(rs.getString("wtime")));
				list.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return list;
	}
	
	public void userModifyOKDAO(UserBoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(UPDATE_USER_BOARD_SQL);
			ps.setInt(1, dto.getProgramcount());
			ps.setString(2, dto.getContents());
			ps.setInt(3, dto.getNo());
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}close(ps, conn);
	}
	
	public void userBoardDeleteDAO(int no) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(DELETE_USER_BOARD_SQL);
			ps.setInt(1, no);
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	
	public ArrayList<UserBoardDTO> userBestBoardDAO(){
		ArrayList<UserBoardDTO> list = new ArrayList<UserBoardDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(SELECT_BEST_USERBOARD_SQL);
			rs = ps.executeQuery();
			while(rs.next()) {
				UserBoardDTO userboardto = new UserBoardDTO();
				userboardto.setNo(rs.getInt("no"));
				userboardto.setId(rs.getString("id"));
				userboardto.setUsername(nameSecretSet(rs.getString("username")));
				userboardto.setTitle(rs.getString("title"));
				userboardto.setGender(rs.getString("gender"));
				userboardto.setProgramcount(rs.getInt("programcount"));
				userboardto.setWtime(timeTextSet(rs.getString("wtime")));
				list.add(userboardto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return list;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public String nameSecretSet(String str) {
		str = str.substring(0,1);
		str = str+"**";
		return str;
	}
	
	public String timeTextSet(String str) {
		str = str.substring(0,16);
		str = str.replace('-', '.');
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
