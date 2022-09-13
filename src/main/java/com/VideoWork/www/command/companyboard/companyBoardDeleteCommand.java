package com.VideoWork.www.command.companyboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.CompanyBoardDAO;
import com.VideoWork.www.command.Command;

public class companyBoardDeleteCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CompanyBoardDAO dao = CompanyBoardDAO.getCompanyBoardDAO();
		dao.companyBoardDeleteDAO(Integer.parseInt(request.getParameter("no")));
	}
}
