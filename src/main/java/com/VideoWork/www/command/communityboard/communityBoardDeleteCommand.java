package com.VideoWork.www.command.communityboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.CommunityDAO;
import com.VideoWork.www.command.Command;

public class communityBoardDeleteCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CommunityDAO dao = CommunityDAO.getCommunityDAO();
		dao.communityBoardDeleteDAO(Integer.parseInt(request.getParameter("no")));
	}
}
