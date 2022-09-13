package com.VideoWork.www.command.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.VideoWork.www.command.Command;
import com.VideoWork.www.memberDAO.UserDAO;

public class NicknameCheckCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		UserDAO dao = UserDAO.getUserDAO();
		int result1 = dao.checkNickname(request.getParameter("nickname"));
		int result2 = dao.myNickname(request.getParameter("id"),request.getParameter("nickname"));
		request.setAttribute("result", result1+result2);
	}
}
