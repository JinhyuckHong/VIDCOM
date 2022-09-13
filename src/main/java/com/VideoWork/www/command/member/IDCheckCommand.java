package com.VideoWork.www.command.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.command.Command;
import com.VideoWork.www.memberDAO.UserDAO;

public class IDCheckCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		UserDAO dao = UserDAO.getUserDAO();
		int result = dao.checkId(request.getParameter("id"));
		request.setAttribute("result", result);
	}
}
