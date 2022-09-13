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
<link rel="stylesheet" href="communityBoardWriteCss.css">
</head>
<body>
<c:if test="${userInfo ne null}">
	<header>
		<div class="logo">
			<a href="mainPage.do">
			<c:choose>
				<c:when test="${userInfo ne null and companyInfo eq null}"> <!-- 유저가 로그인했을때  -->
					<h1 style="color:#08C299">VIDCOM</h1>
					<p id="uselogin">Individual Member</p>
				</c:when>
			</c:choose>
			</a>
			</div>
	</header>
	<section>
	<form action="communityBoardWriteOK.do" method="post">
	<div class="previewmenu">
		<div id="boxShadow">
			<div id="communityboardWrite">
		<table>
			<tr>
				<td>
					<input type="text" onclick="titleValueChange()" id="title" name="title" placeholder="제목을 입력해 주세요">
				</td>
			</tr>
			<tr>
				<td>
					<hr align="center"/>
				</td>
			</tr>
			<tr>
				<td>
					<textarea id="contents" name="contents" rows="15" cols="81" placeholder="욕설 및 비방, 선정적 게시글은 제재대상입니다.&#10"></textarea>
					<hr align="center"/>
				</td>
			</tr>
		</table>
	</div>
		<div id="underMenu">
			<input type="submit" value="등록">
			<a href="communityboard.do"><button type="button">목록</button></a>
		</div>
	</div>
	</div>	
	</form>
	</section>
	<script src = "communityBoardJS.js"></script>
	</c:if>
	<c:if test="${userInfo eq null}">페이지에러 <a href="mainPage.do">홈으로</a></c:if>
</body>
</html>