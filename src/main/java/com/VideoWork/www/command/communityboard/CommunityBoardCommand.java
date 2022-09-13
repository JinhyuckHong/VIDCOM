package com.VideoWork.www.command.communityboard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.CommunityDAO;
import com.VideoWork.www.boardDTO.CommunityDTO;
import com.VideoWork.www.command.Command;

public class CommunityBoardCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CommunityDAO dao = CommunityDAO.getCommunityDAO();
		int curpage = 0;
		if(request.getParameter("curPage") != null)
			curpage = Integer.parseInt(request.getParameter("curPage"));
		ArrayList<CommunityDTO> list = dao.communityBoardDAO(curpage);
		int totalPage = dao.calTotalPage();
		System.out.println("totalPage = " + totalPage);
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage);
	}
}
