package com.VideoWork.www.command.companyboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.CompanyBoardDAO;
import com.VideoWork.www.boardDTO.CompanyFailDTO;
import com.VideoWork.www.command.Command;

public class CompanyBoardModifyOKCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CompanyFailDTO dto = new CompanyFailDTO();
		CompanyBoardDAO dao = CompanyBoardDAO.getCompanyBoardDAO();
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dto.setPeoplecount(Integer.parseInt(request.getParameter("peoplecount")));
		dto.setContents(request.getParameter("contents"));
		dao.companyModifyOKDAO(dto);
	}
}
