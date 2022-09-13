package com.VideoWork.www.command.userboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.UserBoardDAO;
import com.VideoWork.www.boardDTO.UserBoardDTO;
import com.VideoWork.www.command.Command;

public class UserBoardModifyOKCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		UserBoardDTO dto = new UserBoardDTO();
		UserBoardDAO dao = UserBoardDAO.getUserBoardDAO();
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dto.setProgramcount(Integer.parseInt(request.getParameter("programcount")));
		dto.setContents(request.getParameter("contents"));
		dao.userModifyOKDAO(dto);
	}

}
