package com.VideoWork.www.command.userboard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.UserBoardDAO;
import com.VideoWork.www.boardDTO.UserBoardDTO;
import com.VideoWork.www.command.Command;

public class UserBoardCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		UserBoardDAO dao = UserBoardDAO.getUserBoardDAO();
		int curpage = 0;
		if(request.getParameter("curPage") != null)
			curpage = Integer.parseInt(request.getParameter("curPage"));
		ArrayList<UserBoardDTO> list = dao.userBoardDAO(curpage);
		int totalPage = dao.calTotalPage();
		System.out.println("totalPage = " + totalPage);
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage);
	}

}
