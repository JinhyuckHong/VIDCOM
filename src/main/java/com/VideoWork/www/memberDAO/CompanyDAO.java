package com.VideoWork.www.memberDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.VideoWork.www.memberDTO.CompanyDTO;
import com.VideoWork.www.memberDTO.UserDTO;

public class CompanyDAO {
	private static CompanyDAO companyDAO = new CompanyDAO();
	private String CONNECTION_POOL = "jdbc/project";
	private final String TABLE_NAME = "companymember";
	private DataSource dataSource;
	
	private final String INSERT_COMPANYINFO_SQL =
			"insert into "+ TABLE_NAME + " ( id,pw,companyname,email,postcode,roadAddress,jibunAddress,detailAddress,extraAddress,phonenumber) values(?,?,?,?,?,?,?,?,?,?)";
	private final String LOGIN_COMPANY_SQL =
			"select * from "+TABLE_NAME+" where id = ? and pw = ?";
	private final String UPDATE_COMPANY_SQL =
			"update "+TABLE_NAME+" set pw=?, companyname=?, email=?, postcode=?, roadAddress=?, jibunAddress=?, detailAddress=?, extraAddress=?, phonenumber=? where id=?";
	
	private CompanyDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/" + CONNECTION_POOL);
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static CompanyDAO getCompanyDAO() {
		return companyDAO;
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
	public void companyRegisterDAO(CompanyDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(INSERT_COMPANYINFO_SQL);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getCompanyname());
			ps.setString(4, dto.getEmail());
			ps.setString(5, dto.getPostcode());
			ps.setString(6, dto.getRoadAddress());
			ps.setString(7, dto.getJibunAddress());
			ps.setString(8, dto.getDetailAddress());
			ps.setString(9, dto.getExtraAddress());
			ps.setString(10, dto.getPhonenumber());
			result = ps.executeUpdate();
		}catch(SQLException e) {
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
	public CompanyDTO companyLoginDAO(CompanyDTO dto) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = getConnection();
		try {
			ps = conn.prepareStatement(LOGIN_COMPANY_SQL);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			rs=ps.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setCompanyname(rs.getString("companyname"));
				dto.setEmail(rs.getString("email"));
				dto.setPostcode(rs.getString("postcode"));
				dto.setRoadAddress(rs.getString("roadAddress"));
				dto.setJibunAddress(rs.getString("jibunAddress"));
				dto.setDetailAddress(rs.getString("detailAddress"));
				dto.setExtraAddress(rs.getString("extraAddress"));
				dto.setPhonenumber(rs.getString("phonenumber"));
			}else {
				dto =null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs,ps,conn);
		}
		return dto;
	}
	public void companyInfoModifyDAO(CompanyDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(UPDATE_COMPANY_SQL);
			ps.setString(1, dto.getPw());
			ps.setString(2, dto.getCompanyname());
			ps.setString(3, dto.getEmail());
			ps.setString(4, dto.getPostcode());
			ps.setString(5, dto.getRoadAddress());
			ps.setString(6, dto.getJibunAddress());
			ps.setString(7, dto.getDetailAddress());
			ps.setString(8, dto.getExtraAddress());
			ps.setString(9, dto.getPhonenumber());
			ps.setString(10, dto.getId());
			result = ps.executeUpdate();
		}catch (SQLException e) {
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
