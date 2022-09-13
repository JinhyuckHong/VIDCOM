package com.VideoWork.www.command.communityboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.CommunityDAO;
import com.VideoWork.www.boardDTO.CommunityDTO;
import com.VideoWork.www.command.Command;

public class CommunityBoardReplyOKCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CommunityDAO dao = CommunityDAO.getCommunityDAO();
		CommunityDTO dto = new CommunityDTO();
		dto.setId(request.getParameter("id"));
		System.out.println(dto.getId());
		dto.setNickname(request.getParameter("nickname"));
		System.out.println(dto.getNickname());
		dto.setTitle(request.getParameter("title"));
		System.out.println(dto.getTitle());
		dto.setContents(request.getParameter("contents"));
		System.out.println(dto.getContents());
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		System.out.println(dto.getNo());
		dto.setContentsnum(Integer.parseInt(request.getParameter("contentsnum")));
		System.out.println(dto.getContentsnum());
		dto.setReplynum(Integer.parseInt(request.getParameter("replynum")));
		System.out.println(dto.getReplynum());
		dto.setEmptynum(Integer.parseInt(request.getParameter("emptynum")));
		dao.communityReplyOKDAO(dto);
		System.out.println("hello2");
	}
}
