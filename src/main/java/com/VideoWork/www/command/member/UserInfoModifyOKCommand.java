package com.VideoWork.www.command.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.VideoWork.www.command.Command;
import com.VideoWork.www.memberDAO.UserDAO;
import com.VideoWork.www.memberDTO.UserDTO;

public class UserInfoModifyOKCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		UserDTO dto = new UserDTO();
		UserDAO dao = UserDAO.getUserDAO();
		HttpSession session = request.getSession();
		
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setUsername(request.getParameter("username"));
		dto.setNickname(request.getParameter("nickname"));
		dto.setEmail(request.getParameter("email"));
		dto.setPostcode(request.getParameter("postcode"));
		dto.setRoadAddress(request.getParameter("roadAddress"));
		dto.setJibunAddress(request.getParameter("jibunAddress"));
		dto.setDetailAddress(request.getParameter("detailAddress"));
		dto.setExtraAddress(request.getParameter("extraAddress"));
		dto.setBirthYear(Integer.parseInt(request.getParameter("birthyear")));
		dto.setBirthMonth(Integer.parseInt(request.getParameter("birthmonth")));
		dto.setBirthDate(Integer.parseInt(request.getParameter("birthdate")));
		dto.setPhonenumber(request.getParameter("phonenumber"));
		dto.setGender(request.getParameter("gender"));
		dao.userInfoModifyDAO(dto);
		session.setAttribute("userInfo", dto);
	}

}
