package com.VideoWork.www.command.userboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.UserBoardDAO;
import com.VideoWork.www.command.Command;

public class userBoardDeleteCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		UserBoardDAO dao = UserBoardDAO.getUserBoardDAO();
		dao.userBoardDeleteDAO(Integer.parseInt(request.getParameter("no")));
	}
}
