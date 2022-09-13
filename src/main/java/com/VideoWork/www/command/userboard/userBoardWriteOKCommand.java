package com.VideoWork.www.command.userboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.UserBoardDAO;
import com.VideoWork.www.boardDTO.UserBoardDTO;
import com.VideoWork.www.command.Command;
import com.VideoWork.www.memberDTO.UserDTO;

public class userBoardWriteOKCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		UserBoardDTO dto = new UserBoardDTO();
		UserBoardDAO dao = UserBoardDAO.getUserBoardDAO();
		dto.setId(((UserDTO)request.getSession().getAttribute("userInfo")).getId());
		dto.setUsername(((UserDTO)request.getSession().getAttribute("userInfo")).getUsername());
		dto.setGender(((UserDTO)request.getSession().getAttribute("userInfo")).getGender());
		dto.setTitle(request.getParameter("title"));
		dto.setContents(request.getParameter("contents"));
		dto.setProgramcount(Integer.parseInt(request.getParameter("programcount")));
		dao.userBoardWriteOKDAO(dto);
	}
}
