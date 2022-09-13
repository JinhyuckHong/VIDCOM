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

import com.VideoWork.www.boardDTO.CompanyFailDTO;

public class CompanyBoardDAO {
	private static CompanyBoardDAO companyBoardDAO = new CompanyBoardDAO();
	private String CONNECTION_POOL = "jdbc/project";
	private final String TABLE_NAME_BOARD = "companyboard";
	private final String TABLE_NAME_COMPANY = "companymember";
	private DataSource dataSource;
	private int pageSize = 5;
	private final String INSERT_COMPANY_BOARD_SQL =
			"insert into " + TABLE_NAME_BOARD + " (id,companyname,title,peoplecount,contents,roadAddress) values (?,?,?,?,?,?)";
	private final String SELECT_COMPANYBOARD_SQL =
			"select * from "+TABLE_NAME_BOARD+" order by no desc limit ? , ?";
	private final String SELECT_COUNT_LIST =  "select count(*) from "+TABLE_NAME_BOARD;
	private final String GET_COMPANYBOARD_DTO_SQL =
			"select um.*, cb.* from "+TABLE_NAME_BOARD+" cb, "+TABLE_NAME_COMPANY+" um"+" where cb.no = ? and um.companyname = ?";
	private final String SELECT_BEST_COMPANYBOARD_SQL =
			"select * from "+TABLE_NAME_BOARD+" order by peoplecount desc limit 0, 5 ";
	private final String UPDATE_COMPANY_BOARD_SQL =
			"update "+TABLE_NAME_BOARD+" set peoplecount = ?, contents = ?, wtime = now() where no = ?";
	private final String DELETE_COMPANY_BOARD_SQL =
			"delete from "+TABLE_NAME_BOARD+" where no=?";
	
	private CompanyBoardDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/" + CONNECTION_POOL);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static CompanyBoardDAO getCompanyBoardDAO() {
		return companyBoardDAO;
	}
	
	public CompanyFailDTO getCompanyBoardDTO(CompanyFailDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(GET_COMPANYBOARD_DTO_SQL);
			ps.setInt(1, dto.getNo());
			ps.setString(2, dto.getCompanyname());
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setCompanyname(rs.getString("companyname"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setPeoplecount(rs.getInt("peoplecount"));
				dto.setRoadAddress(rs.getString("roadAddress"));
				dto.setWtime(timeTextSet(rs.getString("wtime")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return dto;
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
	
	public void companyBoardWriteOKDAO(CompanyFailDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(INSERT_COMPANY_BOARD_SQL);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getCompanyname());
			ps.setString(3, dto.getTitle());
			ps.setInt(4, dto.getPeoplecount());
			ps.setString(5, dto.getContents());
			ps.setString(6, dto.getRoadAddress());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}

	public CompanyFailDTO companyBoardViewDAO(CompanyFailDTO dto) {
		dto = getCompanyBoardDTO(dto);
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
	
	public ArrayList<CompanyFailDTO> companyBoardDAO(int curPage){
		ArrayList<CompanyFailDTO> list = new ArrayList<CompanyFailDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(SELECT_COMPANYBOARD_SQL);
			ps.setInt(1, curPage*pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while(rs.next()) {
				CompanyFailDTO dto = new CompanyFailDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setCompanyname(rs.getString("companyname"));
				dto.setTitle(rs.getString("title"));
				dto.setPeoplecount(rs.getInt("peoplecount"));
				dto.setContents(rs.getString("contents"));
				dto.setRoadAddress(rs.getString("roadAddress"));
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
	
	public void companyModifyOKDAO(CompanyFailDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(UPDATE_COMPANY_BOARD_SQL);
			ps.setInt(1, dto.getPeoplecount());
			ps.setString(2, dto.getContents());
			ps.setInt(3, dto.getNo());
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}close(ps, conn);
	}
	public void companyBoardDeleteDAO(int no) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(DELETE_COMPANY_BOARD_SQL);
			ps.setInt(1, no);
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	public ArrayList<CompanyFailDTO> companyBestBoardDAO(){
		ArrayList<CompanyFailDTO> list = new ArrayList<CompanyFailDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(SELECT_BEST_COMPANYBOARD_SQL);
			rs = ps.executeQuery();
			while(rs.next()) {
				CompanyFailDTO companydto = new CompanyFailDTO();
				companydto.setNo(rs.getInt("no"));
				companydto.setCompanyname(rs.getString("companyname"));
				companydto.setTitle(rs.getString("title"));
				companydto.setPeoplecount(rs.getInt("peoplecount"));
				companydto.setWtime(timeTextSet(rs.getString("wtime")));
				list.add(companydto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return list;
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
