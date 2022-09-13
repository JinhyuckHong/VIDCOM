package com.VideoWork.www.command.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.VideoWork.www.command.Command;
import com.VideoWork.www.memberDAO.CompanyDAO;
import com.VideoWork.www.memberDTO.CompanyDTO;

public class CompanyLoginCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CompanyDAO dao = CompanyDAO.getCompanyDAO();
		CompanyDTO dto = new CompanyDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		HttpSession session = request.getSession();
		session.setAttribute("companyInfo", dao.companyLoginDAO(dto));
	}
}
