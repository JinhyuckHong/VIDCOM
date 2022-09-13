package com.VideoWork.www.command.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.VideoWork.www.command.Command;
import com.VideoWork.www.memberDTO.UserDTO;

public class userLoginErrorCheckCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		UserDTO dto = new UserDTO();
		HttpSession session = request.getSession();
	}
}
