package com.VideoWork.www.command.communityboard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.VideoWork.www.boardDAO.CommunityDAO;
import com.VideoWork.www.boardDAO.CompanyBoardDAO;
import com.VideoWork.www.boardDAO.UserBoardDAO;
import com.VideoWork.www.boardDTO.CommunityDTO;
import com.VideoWork.www.boardDTO.CompanyFailDTO;
import com.VideoWork.www.boardDTO.UserBoardDTO;
import com.VideoWork.www.command.Command;

public class CommunityBestBoardCommand implements Command {

	@Override
	public void excute(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		CommunityDAO commudao = CommunityDAO.getCommunityDAO();
		CompanyBoardDAO companydao = CompanyBoardDAO.getCompanyBoardDAO();
		UserBoardDAO userdao = UserBoardDAO.getUserBoardDAO();
		
		ArrayList<CommunityDTO> commulist = commudao.communityBestBoardDAO();
		ArrayList<CompanyFailDTO> companylist = companydao.companyBestBoardDAO();
		ArrayList<UserBoardDTO> userboardlist = userdao.userBestBoardDAO();
		request.setAttribute("commylist", commulist);
		request.setAttribute("companylist", companylist);
		request.setAttribute("userboardlist", userboardlist);
	}
}
