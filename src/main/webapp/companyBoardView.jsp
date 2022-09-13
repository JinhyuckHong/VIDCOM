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
<link rel="stylesheet" href="companyBoardViewCss.css">
</head>
<body>
	<header>
		<div class="logo">
			<a href="mainPage.do">
			<c:choose>
				<c:when test="${userInfo eq null and companyInfo eq null and adminInfo eq null}"> <!-- 인포비어잇을때  -->
					<h1 style="color:black">VIDCOM</h1>
				</c:when>
				<c:when test="${userInfo ne null and companyInfo eq null and adminInfo eq null}"> <!-- 유저가 로그인했을때  -->
					<h1 style="color:#08C299">VIDCOM</h1>
					<p id="uselogin">Individual Member</p>
				</c:when>
				<c:when test="${userInfo eq null and companyInfo ne null and adminInfo eq null}"> <!-- 업체가 로그인했을때  -->
					<h1 style="color:#425FFF">VIDCOM</h1>
					<p id="coplogin">Business Enterprise</p>
				</c:when>
				<c:when test="${userInfo eq null and companyInfo eq null and adminInfo ne null}"> <!-- 관리자가 로그인했을때  -->
					<h1 style="color:#eeb300">VIDCOM</h1>
					<p id="coplogin" style="color:#eeb300">Admin</p>
				</c:when>
			</c:choose>
			</a>
			</div>
	</header>
	<section>
	<form action="companyBoardModifyOK.do" method="post">
	<div class="previewmenu">
		<div id="boxShadow">
			<div id="companyboardView">
		<table>
			<tr>
				<td id="title">${dto.title}</td>
			</tr>
			<tr>
				<td id="companyname">${dto.companyname}</td>
			</tr>
			<tr>
				<td id="wtime">${dto.wtime}</td>
			</tr>
			<tr>
				<td id="peoplecount">모집인원 <input type="text" name="peoplecount" id="pcInput"<c:if test="${companyInfo.id eq null or companyInfo.id ne dto.id}">readonly</c:if> value="${dto.peoplecount}"></td>
			</tr>
			<tr>
				<td>
					<hr align="center"/>
				</td>
			</tr>
			<tr>
				<td>
					<textarea name="contents" rows="35" cols="76" <c:if test="${companyInfo.id eq null or companyInfo.id ne dto.id}">readonly</c:if>>${dto.contents}</textarea>
					<hr align="center"/>
				</td>
			</tr>
		</table>
		</div>
		<div id="underMenu">
		<a href="mainPage.do"><button type="button">홈으로</button></a>
		<a href="companyboard.do"><button type="button">글목록</button></a>
		<c:if test="${companyInfo.id ne null and companyInfo.id eq dto.id or adminInfo ne null}">
			<c:if test="${adminInfo eq null}">
				<input type="submit" value="수정">
				<a href="companyBoardDelete.do?no=${dto.no}"><button type="button">삭제</button></a>
			</c:if>
			<c:if test="${adminInfo ne null}">
				<a href="companyBoardDelete.do?no=${dto.no}"><button type="button" style="width:140px;background-color:red">관리자 권한으로 삭제</button></a>
			</c:if>
		</c:if>
		<input type="hidden" name="no" value="${dto.no}">
		</div>
	</div>
	</div>
	</form>
	</section>
</body>
</html>