package com.VideoWork.www.command.communityboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.CommunityDAO;
import com.VideoWork.www.boardDTO.CommunityDTO;
import com.VideoWork.www.command.Command;
import com.VideoWork.www.memberDTO.UserDTO;

public class communityBoardWriteOKCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CommunityDAO dao = CommunityDAO.getCommunityDAO();
		CommunityDTO dto = new CommunityDTO();
		dto.setId(((UserDTO)request.getSession().getAttribute("userInfo")).getId());
		dto.setNickname(((UserDTO)request.getSession().getAttribute("userInfo")).getNickname());
		dto.setTitle(request.getParameter("title"));
		dto.setContents(request.getParameter("contents"));
		dao.communityBoardWriteOKDAO(dto);
	}
}
