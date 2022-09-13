package com.VideoWork.www.command.userboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.UserBoardDAO;
import com.VideoWork.www.boardDTO.UserBoardDTO;
import com.VideoWork.www.command.Command;

public class UserBoardViewCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		UserBoardDAO dao = UserBoardDAO.getUserBoardDAO();
		UserBoardDTO dto = new UserBoardDTO();
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dto.setUsername(request.getParameter("username"));
		dto.setId(request.getParameter("id"));
		dao.userBoardViewDAO(dto);
		request.setAttribute("dto", dto);
	}
}
