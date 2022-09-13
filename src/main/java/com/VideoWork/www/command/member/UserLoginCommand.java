package com.VideoWork.www.command.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.VideoWork.www.command.Command;
import com.VideoWork.www.memberDAO.UserDAO;
import com.VideoWork.www.memberDTO.UserDTO;

public class UserLoginCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		UserDAO dao = UserDAO.getUserDAO();
		UserDTO dto = new UserDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", dao.userLoginDAO(dto));
	}
}
