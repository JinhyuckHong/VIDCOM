package com.VideoWork.www.command.companyboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.CompanyBoardDAO;
import com.VideoWork.www.boardDTO.CompanyFailDTO;
import com.VideoWork.www.command.Command;
import com.VideoWork.www.memberDTO.CompanyDTO;

public class companyBoardWriteOKCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CompanyFailDTO dto = new CompanyFailDTO();
		CompanyBoardDAO dao = CompanyBoardDAO.getCompanyBoardDAO();
		dto.setId(((CompanyDTO)request.getSession().getAttribute("companyInfo")).getId());
		dto.setCompanyname(((CompanyDTO)request.getSession().getAttribute("companyInfo")).getCompanyname());
		dto.setRoadAddress(((CompanyDTO)request.getSession().getAttribute("companyInfo")).getRoadAddress());
		dto.setTitle(request.getParameter("title"));
		dto.setPeoplecount(Integer.parseInt(request.getParameter("peoplecount")));
		dto.setContents(request.getParameter("contents"));
		dao.companyBoardWriteOKDAO(dto);
	}
}
