package com.VideoWork.www.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.VideoWork.www.command.Command;
import com.VideoWork.www.command.communityboard.CommunityBestBoardCommand;
import com.VideoWork.www.command.communityboard.CommunityBoardCommand;
import com.VideoWork.www.command.communityboard.CommunityBoardModifyOKCommand;
import com.VideoWork.www.command.communityboard.CommunityBoardReplyCommand;
import com.VideoWork.www.command.communityboard.CommunityBoardReplyOKCommand;
import com.VideoWork.www.command.communityboard.CommunityBoardViewCommand;
import com.VideoWork.www.command.communityboard.communityBoardDeleteCommand;
import com.VideoWork.www.command.communityboard.communityBoardWriteOKCommand;
import com.VideoWork.www.command.companyboard.CompanyBoardCommand;
import com.VideoWork.www.command.companyboard.CompanyBoardModifyOKCommand;
import com.VideoWork.www.command.companyboard.CompanyBoardViewCommand;
import com.VideoWork.www.command.companyboard.companyBoardDeleteCommand;
import com.VideoWork.www.command.companyboard.companyBoardWriteOKCommand;
import com.VideoWork.www.command.member.AdminLoginCommand;
import com.VideoWork.www.command.member.CompanyInfoModifyOKCommand;
import com.VideoWork.www.command.member.CompanyLoginCommand;
import com.VideoWork.www.command.member.CompanyRegisterOKCommand;
import com.VideoWork.www.command.member.IDCheckCommand;
import com.VideoWork.www.command.member.NicknameCheckCommand;
import com.VideoWork.www.command.member.UserInfoModifyOKCommand;
import com.VideoWork.www.command.member.UserLoginCommand;
import com.VideoWork.www.command.member.UserRegisterOKCommand;
import com.VideoWork.www.command.member.adminInfoModifyOKCommand;
import com.VideoWork.www.command.userboard.UserBoardCommand;
import com.VideoWork.www.command.userboard.UserBoardModifyOKCommand;
import com.VideoWork.www.command.userboard.UserBoardViewCommand;
import com.VideoWork.www.command.userboard.userBoardDeleteCommand;
import com.VideoWork.www.command.userboard.userBoardWriteOKCommand;

/**
 * Servlet implementation class Controller
 */
@WebServlet({ "/Controller", "*.do" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String commandName = request.getServletPath();
		System.out.println("servletPath = "+commandName);
		HttpSession session = request.getSession();
		String viewPage = null;
		Command command = null;
		PrintWriter out = null;
		int flag = 0;
		if(commandName.equals("/userRegister.do")) {
			viewPage = "userRegister.jsp";
		}else if(commandName.equals("/userRegisterOK.do")){
			command = new UserRegisterOKCommand();
			command.excute(response, request);
			viewPage = "mainPage.do";
		}else if(commandName.equals("/companyRegister.do")) {
			viewPage = "companyRegister.jsp";
		}else if(commandName.equals("/companyRegisterOK.do")) {
			command = new CompanyRegisterOKCommand();
			command.excute(response, request);
			viewPage = "mainPage.do";
		}else if(commandName.equals("/loginMain.do")){
			viewPage = "loginMain.jsp";
		}else if(commandName.equals("/userLogin.do")) {
			command = new UserLoginCommand();
			command.excute(response, request);
			if(session.getAttribute("userInfo")==null && session.getAttribute("companyInfo")==null) {
				viewPage = "userLoginErrorCheck.do";
			}else {
				viewPage = "mainPage.do";
			}
		}else if(commandName.equals("/userLoginErrorCheck.do")){
			viewPage = "userLoginErrorCheck.jsp";
		}else if(commandName.equals("/companyLogin.do")) {
			command = new CompanyLoginCommand();
			command.excute(response, request);
			if(session.getAttribute("userInfo")==null && session.getAttribute("companyInfo")==null) {
				viewPage = "userLoginErrorCheck.do";
			}else {
				viewPage = "mainPage.do";
			}
		}else if(commandName.equals("/logout.do")) {
			request.getSession().invalidate();
			viewPage = "mainPage.do";
		}else if(commandName.equals("/adminLogout.do")) {
			request.getSession().invalidate();
			viewPage = "loginAdmin.do";
		}else if(commandName.equals("/mainRegister.do")) {
			viewPage = "mainRegister.jsp";
		}else if(commandName.equals("/communityboard.do")) {
			command = new CommunityBoardCommand();
			command.excute(response, request);
			viewPage = "communityboard.jsp";
			flag = 1;
		}else if(commandName.equals("/companyboard.do")) {
			command = new CompanyBoardCommand();
			command.excute(response, request);
			viewPage = "companyboard.jsp";
			flag = 1;
		}else if(commandName.equals("/communiyBoardWrite.do")) {
			viewPage = "communiyBoardWrite.jsp";
		}else if(commandName.equals("/communityBoardWriteOK.do")) {
			command = new communityBoardWriteOKCommand();
			command.excute(response, request);
			viewPage = "communityboard.do";
			flag = 0;
		}else if(commandName.equals("/userInfoModify.do")) {
			viewPage = "userInfoModify.jsp";
		}else if(commandName.equals("/companyInfoModify.do")) {
			viewPage = "companyInfoModify.jsp";
		}else if(commandName.equals("/adminInfoModify.do")) {
			viewPage = "adminInfoModify.jsp";
		}else if(commandName.equals("/communityBoardView.do")) {
			command = new CommunityBoardViewCommand();
			command.excute(response, request);
			viewPage = "communityBoardView.jsp";
			flag = 1;
		}else if(commandName.equals("/idCheck.do")) {
			command = new IDCheckCommand();
			command.excute(response, request);
			out = response.getWriter();
			System.out.println("result = "+ request.getAttribute("result"));
			out.write(request.getAttribute("result")+ "");
			flag = 2;
		}else if(commandName.equals("/nicknameCheck.do")) {
			command = new NicknameCheckCommand();
			command.excute(response, request);
			out = response.getWriter();
			System.out.println("result = "+ request.getAttribute("result"));
			out.write(request.getAttribute("result")+ "");
			flag = 2;
		}else if(commandName.equals("/mainPage.do")) {
			command = new CommunityBestBoardCommand();
			command.excute(response, request);
			viewPage = "mainPage.jsp";
			flag=1;
		}else if(commandName.equals("/communityBoardModifyOK.do")) {
			command = new CommunityBoardModifyOKCommand();
			command.excute(response, request);
			viewPage = "communityboard.do";
		}else if(commandName.equals("/communityBoardDelete.do")) {
			command = new communityBoardDeleteCommand();
			command.excute(response, request);
			viewPage = "communityboard.do";
		}else if(commandName.equals("/communityBoardReply.do")) {
			command = new CommunityBoardReplyCommand();
			command.excute(response, request);
			viewPage = "communityBoardReply.jsp";
			flag = 1;
		}else if(commandName.equals("/communityBoardReplyOK.do")) {
			command = new CommunityBoardReplyOKCommand();
			command.excute(response, request);
			viewPage = "communityboard.do";
			flag = 0;
		}else if(commandName.equals("/companyBoardWrite.do")) {
			viewPage = "companyBoardWrite.jsp";
		}else if(commandName.equals("/companyBoardWriteOK.do")) {
			command = new companyBoardWriteOKCommand();
			command.excute(response, request);
			viewPage = "companyboard.do";
			flag = 0;
		}else if(commandName.equals("/companyBoardView.do")) {
			command = new CompanyBoardViewCommand();
			command.excute(response, request);
			viewPage = "companyBoardView.jsp";
			flag = 1;
		}else if(commandName.equals("/companyBoardModifyOK.do")) {
			command = new CompanyBoardModifyOKCommand();
			command.excute(response, request);
			viewPage = "companyboard.do";
		}else if(commandName.equals("/companyBoardDelete.do")) {
			command = new companyBoardDeleteCommand();
			command.excute(response, request);
			viewPage = "companyboard.do";
		}else if(commandName.equals("/userBoardWrite.do")) {
			viewPage = "userBoardWrite.jsp";
		}else if(commandName.equals("/userBoardWriteOK.do")) {
			command = new userBoardWriteOKCommand();
			command.excute(response, request);
			viewPage = "userboard.do";
			flag = 0;
		}else if(commandName.equals("/userboard.do")) {
			command = new UserBoardCommand();
			command.excute(response, request);
			viewPage = "userboard.jsp";
			flag = 1;
		}else if(commandName.equals("/userBoardView.do")) {
			command = new UserBoardViewCommand();
			command.excute(response, request);
			viewPage = "userBoardView.jsp";
			flag = 1;
		}else if(commandName.equals("/userBoardModifyOK.do")) {
			command = new UserBoardModifyOKCommand();
			command.excute(response, request);
			viewPage = "userboard.do";
		}else if(commandName.equals("/userBoardDelete.do")) {
			command = new userBoardDeleteCommand();
			command.excute(response, request);
			viewPage = "userboard.do";
		}else if(commandName.equals("/loginAdmin.do")) {
			viewPage = "loginAdmin.jsp";
		}else if(commandName.equals("/adlogin.do")) {
			command = new AdminLoginCommand();
			command.excute(response, request);
			if(session.getAttribute("adminInfo")==null) {
				viewPage ="loginAdmin.do";
			}else {
				viewPage = "mainPage.do";
			}
		}else if(commandName.equals("/userInfoModifyOK.do")) {
			command = new UserInfoModifyOKCommand();
			command.excute(response, request);
			viewPage="mainPage.do";
		}else if(commandName.equals("/companyInfoModifyOK.do")) {
			command = new CompanyInfoModifyOKCommand();
			command.excute(response, request);
			viewPage="mainPage.do";
		}else if(commandName.equals("/adminInfoModifyOK.do")) {
			command = new adminInfoModifyOKCommand();
			command.excute(response, request);
			viewPage="mainPage.do";
		}
		if(flag==0) {
			response.sendRedirect(viewPage);
		}else if(flag==1){
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
	
}
