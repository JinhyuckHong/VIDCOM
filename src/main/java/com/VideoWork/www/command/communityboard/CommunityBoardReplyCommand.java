package com.VideoWork.www.command.communityboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.CommunityDAO;
import com.VideoWork.www.boardDTO.CommunityDTO;
import com.VideoWork.www.command.Command;

public class CommunityBoardReplyCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CommunityDTO dto = new CommunityDTO();
		CommunityDAO dao = CommunityDAO.getCommunityDAO();
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dto.setId(request.getParameter("id"));
		dto.setNickname(request.getParameter("nickname"));
		dto.setTitle(request.getParameter("title"));
		dto.setContents(request.getParameter("contents"));
		dao.communityReplyDAO(dto);
		request.setAttribute("dto", dto);
	}
}
