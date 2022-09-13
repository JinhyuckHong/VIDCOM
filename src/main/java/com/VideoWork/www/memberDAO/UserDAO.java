package com.VideoWork.www.memberDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.VideoWork.www.memberDTO.AdminDTO;
import com.VideoWork.www.memberDTO.UserDTO;

public class UserDAO {
	private static UserDAO userDAO = new UserDAO();
	private String CONNECTION_POOL = "jdbc/project";
	private final String TABLE_NAME = "usermember";
	private final String TABLE_ADMIN_NAME = "admin";
	private final String TABLE_NAME_COMPANY = "companymember";
	private DataSource dataSource;
	
	private final String INSERT_USERINFO_SQL =
			"insert into "+ TABLE_NAME + " (id, pw, username, nickname, email, postcode, roadAddress, jibunAddress, detailAddress, extraAddress, birthYear, birthMonth, birthDate, phonenumber, gender) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String LOGIN_USER_SQL =
			"select * from "+TABLE_NAME+" where id = ? and pw = ?";
	private final String LOGIN_ADMIN_SQL =
			"select * from "+TABLE_ADMIN_NAME+" where id = ? and pw = ?";
	private final String UPDATE_USER_SQL =
			"update "+TABLE_NAME+" set pw=?, username=?, nickname=?, email=?, postcode=?, roadAddress=?, jibunAddress=?, detailAddress=?, extraAddress=?, birthyear=?, birthmonth=?, birthdate=?, phonenumber=?, gender=? where id=?";
	private final String UPDATE_ADMIN_SQL =
			"update "+TABLE_ADMIN_NAME+" set pw=? where id=?";
	private final String MY_NICKNAME_SQL =
			"select nickname from "+TABLE_NAME+" where id = ?";
	
	
	private UserDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/" + CONNECTION_POOL);
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static UserDAO getUserDAO() {
		return userDAO;
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn=dataSource.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public int checkId(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getConnection();
		String sql = "select id from "+TABLE_NAME+" where id = ? "+"union select id from "+TABLE_NAME_COMPANY+" where id = ?";
		int result = 0;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, id);
			rs = ps.executeQuery();
			if(rs.next()) result = 0;
			else result = 1;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return result;
	}
	
	public int checkNickname(String nickname) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		String sql = "select nickname from "+TABLE_NAME+" where nickname = ?";
		int result = 0;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, nickname);
			rs = ps.executeQuery();
			if(rs.next()) result = 0;
			else result = 1;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return result;
	}
	public int myNickname(String id, String nickname) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(MY_NICKNAME_SQL);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
			if(rs.getString("nickname").equals(nickname)) result=1;
			else result = 0;
			}
			else result = 0;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return result;
	}
	
	
	public void userRegisterDAO(UserDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(INSERT_USERINFO_SQL);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getUsername());
			ps.setString(4, dto.getNickname());
			ps.setString(5, dto.getEmail());
			ps.setString(6, dto.getPostcode());
			ps.setString(7, dto.getRoadAddress());
			ps.setString(8, dto.getJibunAddress());
			ps.setString(9, dto.getDetailAddress());
			ps.setString(10, dto.getExtraAddress());
			ps.setInt(11, dto.getBirthYear());
			ps.setInt(12, dto.getBirthMonth());
			ps.setInt(13, dto.getBirthDate());
			ps.setString(14, dto.getPhonenumber());
			ps.setString(15, dto.getGender());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps,conn);
		}
	}
	public UserDTO userLoginDAO(UserDTO dto) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = getConnection();
		try {
			ps = conn.prepareStatement(LOGIN_USER_SQL);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			rs=ps.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setUsername(rs.getString("username"));
				dto.setNickname(rs.getString("nickname"));
				dto.setEmail(rs.getString("email"));
				dto.setPostcode(rs.getString("postcode"));
				dto.setRoadAddress(rs.getString("roadAddress"));
				dto.setJibunAddress(rs.getString("jibunAddress"));
				dto.setDetailAddress(rs.getString("detailAddress"));
				dto.setExtraAddress(rs.getString("extraAddress"));
				dto.setBirthYear(rs.getInt("birthYear"));
				dto.setBirthMonth(rs.getInt("birthMonth"));
				dto.setBirthDate(rs.getInt("birthDate"));
				dto.setPhonenumber(rs.getString("phonenumber"));
				dto.setGender(rs.getString("gender"));
			}else {
				dto = null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,ps,conn);
		}
		return dto;
	}
	public void userInfoModifyDAO(UserDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(UPDATE_USER_SQL);
			ps.setString(1, dto.getPw());
			ps.setString(2, dto.getUsername());
			ps.setString(3, dto.getNickname());
			ps.setString(4, dto.getEmail());
			ps.setString(5, dto.getPostcode());
			ps.setString(6, dto.getRoadAddress());
			ps.setString(7, dto.getJibunAddress());
			ps.setString(8, dto.getDetailAddress());
			ps.setString(9, dto.getExtraAddress());
			ps.setInt(10, dto.getBirthYear());
			ps.setInt(11, dto.getBirthMonth());
			ps.setInt(12, dto.getBirthDate());
			ps.setString(13, dto.getPhonenumber());
			ps.setString(14, dto.getGender());
			ps.setString(15, dto.getId());
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	
	public AdminDTO adminLoginDAO(AdminDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(LOGIN_ADMIN_SQL);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			rs=ps.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
			}else {
				dto = null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,ps,conn);
		}
		return dto;
	}
	public void adminInfoModifyDAO(AdminDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result=0;
		try {
			ps = conn.prepareStatement(UPDATE_ADMIN_SQL);
			ps.setString(1, dto.getPw());
			ps.setString(2, dto.getId());
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	
	private void close(PreparedStatement ps, Connection conn) {
		try {
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
