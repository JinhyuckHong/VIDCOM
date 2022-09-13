package com.VideoWork.www.command.communityboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.CommunityDAO;
import com.VideoWork.www.boardDTO.CommunityDTO;
import com.VideoWork.www.command.Command;

public class CommunityBoardModifyOKCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CommunityDTO dto = new CommunityDTO();
		CommunityDAO dao = CommunityDAO.getCommunityDAO();
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dto.setContents(request.getParameter("contents"));
		dao.communityModifyOKDAO(dto);
	}
}
