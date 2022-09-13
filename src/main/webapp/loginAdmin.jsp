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
<link rel="stylesheet" href="adminLoginCss.css">
</head>
<body id="body">
<header>
<div class="box" id="boxjs">
<div class="loginBoxLeft">
	<div class="loginbtn">
	<a href="loginAdmin.do" id="adminlogo">ADMIN LOGIN</a>
	</div>
	<div id="adminlogindisplay">
	<form action="adlogin.do" method="post">
	<p class="adminId">아이디</p>
	<input type="text" name="id" id="inputAdminId">
	<p class="adminPw">비밀번호</p>
	<input type="password" name="pw" id="inputAdminPw"><br>
	<input type="submit" value="로그인" id="adminsubmitBtn">
	</form>
</div>
</div>
</div>
</header>
</body>
</html>