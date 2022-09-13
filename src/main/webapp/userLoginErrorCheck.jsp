<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>VideoWork</title>
<link rel="stylesheet" href="userLoginErrorCheckCss.css">
</head>
<body id="body">
<header>
<div class="box" id="boxjs">
<div class="loginBoxLeft">
	<div class="loginbtn">
	<a href="mainPage.do" id="mainlogo">VIDCOM</a>
	<button type="button" onclick="userlogin()" id="userlogin">개인회원</button>
	<button onclick="companylogin()" id="companylogin">기업회원</button>
	</div>
	<p id="loginError">아이디 혹은 비밀번호를 잘못입력하셨습니다.</p>
	<div id="userlogindisplay">
	<form action="userLogin.do" method="post">
	<p class="userId">아이디</p>
	<input type="text" name="id" id="inputUserId">
	<p class="userPw">비밀번호</p>
	<input type="password" name="pw" id="inputUserPw"><br>
	<input type="submit" value="로그인" id="usersubmitBtn">
	<a href="mainRegister.do" id="userRegisterBtn">회원가입</a>
	</form>
	</div>
	<div id="companylogindisplay" style="display:none">
	<form action="companyLogin.do" method="post">
	<p class="companyId">아이디</p>
	<input type="text" name="id" id="inputCompanyId">
	<p class="companyPw">비밀번호</p>
	<input type="password" name="pw" id="inputCompanyPw"><br>
	<input type="submit" value="로그인" id="companysubmitBtn">
	<a href="mainRegister.do" id="companyRegisterBtn">회원가입</a>
	</form>
	</div>
</div>
<div class="loginBoxRight">
	<img src="userlogin.jpg" width="50%" height="50%" id="img">
</div>
</div>
</header>
	<script src="loginMainJS.js"></script>
</body>
</html>