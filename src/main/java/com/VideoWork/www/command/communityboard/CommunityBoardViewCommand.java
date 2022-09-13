package com.VideoWork.www.command.communityboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.CommunityDAO;
import com.VideoWork.www.boardDTO.CommunityDTO;
import com.VideoWork.www.command.Command;

public class CommunityBoardViewCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CommunityDAO dao = CommunityDAO.getCommunityDAO();
		CommunityDTO dto = new CommunityDTO();
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		System.out.println(request.getParameter("no"));
		dto.setNickname(request.getParameter("nickname"));
		dao.communityBoardViewDAO(dto);
		request.setAttribute("dto", dto);
	}
}
