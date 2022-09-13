package com.VideoWork.www.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	public void excute(HttpServletResponse response, HttpServletRequest request)
		throws ServletException, IOException;
}
