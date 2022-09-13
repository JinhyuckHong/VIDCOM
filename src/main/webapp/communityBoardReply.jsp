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
<link rel="stylesheet" href="communityBoardReplyCss.css">
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
	<form method="post" action="communityBoardReplyOK.do">
	<div class="previewmenu">
		<div id="boxShadow">
			<div id="communityboardReply">
		<table>
			<tr>
				<td><input type="text" name="title" id="title" value="re: ${dto.title}"/></td>
			</tr>
			<tr>
				<td>
					<hr align="center"/>
				</td>
			</tr>
			<tr>
				<td>
					<textarea id="contents" name="contents" cols="81" rows="15" autofocus>&#10&#13re: ${dto.contents}</textarea>
					<hr align="center"/>
				</td>
			</tr>
		</table>
	</div>
		<div id="underMenu">
		<input type="submit" value="등록"> 
		<a href="communityboard.do"><button>목록</button></a>
		</div>
		<input type="hidden" name="no" value="${dto.no}" />
		<input type="hidden" name="id" value="${userInfo.id}" />
		<input type="hidden" name="nickname" value="${userInfo.nickname}" />
		<input type="hidden" name="contentsnum" value="${dto.contentsnum}" />
		<input type="hidden" name="replynum" value="${dto.replynum}" />
		<input type="hidden" name="emptynum" value="${dto.emptynum}" />
		</div>
	</div>	
	</form>
	</section>
	</c:if>
	<c:if test="${userInfo eq null}">페이지에러 <a href="mainPage.do">홈으로</a></c:if>
</body>
</html>