package com.VideoWork.www.command.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.VideoWork.www.command.Command;
import com.VideoWork.www.memberDAO.UserDAO;
import com.VideoWork.www.memberDTO.AdminDTO;

public class adminInfoModifyOKCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		AdminDTO dto = new AdminDTO();
		UserDAO dao = UserDAO.getUserDAO();
		HttpSession session = request.getSession();
		
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dao.adminInfoModifyDAO(dto);
		session.setAttribute("adminInfo", dto);
	}

}
