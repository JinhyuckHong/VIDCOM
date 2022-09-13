package com.VideoWork.www.command.companyboard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.CompanyBoardDAO;
import com.VideoWork.www.boardDTO.CommunityDTO;
import com.VideoWork.www.boardDTO.CompanyFailDTO;
import com.VideoWork.www.command.Command;

public class CompanyBoardCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CompanyBoardDAO dao = CompanyBoardDAO.getCompanyBoardDAO();
		int curpage = 0;
		if(request.getParameter("curPage") != null)
			curpage = Integer.parseInt(request.getParameter("curPage"));
		ArrayList<CompanyFailDTO> list = dao.companyBoardDAO(curpage);
		int totalPage = dao.calTotalPage();
		System.out.println("totalPage = " + totalPage);
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage);
	}

}
