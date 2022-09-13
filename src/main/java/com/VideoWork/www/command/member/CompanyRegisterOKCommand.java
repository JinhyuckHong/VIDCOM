package com.VideoWork.www.command.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.command.Command;
import com.VideoWork.www.memberDAO.CompanyDAO;
import com.VideoWork.www.memberDTO.CompanyDTO;

public class CompanyRegisterOKCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CompanyDAO dao = CompanyDAO.getCompanyDAO();
		CompanyDTO dto = new CompanyDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setCompanyname(request.getParameter("companyname"));
		dto.setEmail(request.getParameter("email"));
		dto.setPostcode(request.getParameter("postcode"));
		dto.setRoadAddress(request.getParameter("roadAddress"));
		dto.setJibunAddress(request.getParameter("jibunAddress"));
		dto.setDetailAddress(request.getParameter("detailAddress"));
		dto.setExtraAddress(request.getParameter("extraAddress"));
		dto.setPhonenumber(request.getParameter("phonenumber"));
		dao.companyRegisterDAO(dto);
	}
}
