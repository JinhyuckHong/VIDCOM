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
<link rel="stylesheet" href="userboardCss.css">
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
		<div class="LR">
		<ul>
			<c:choose>
				<c:when test="${userInfo eq null and companyInfo eq null and adminInfo eq null}">
					<li><a href="loginMain.do" style="padding:10px 19px; color:black">로그인</a></li>
					<li><a href="mainRegister.do" style="color:black">회원가입</a></li>
					<div class="wellcom"></div>
				</c:when>
				<c:when test="${userInfo ne null and companyInfo eq null and adminInfo eq null}">
					<li style="background-color:#08C299"><a href="userInfoModify.do">개인정보변경</a></li>
					<li style="background-color:#08C299"><a href="logout.do">로그아웃</a></li>
					<div class="wellcom" id="userwellcom">${userInfo.username}님 안녕하세요.<br/>오늘도 좋은하루되세요!</div>
				</c:when>
				<c:when test="${companyInfo ne null and userInfo eq null and adminInfo eq null}">
					<li style="background-color:#425FFF"><a href="companyInfoModify.do">기업정보변경</a></li>
					<li style="background-color:#425FFF"><a href="logout.do">로그아웃</a></li>
					<div class="wellcom" id="companywellcom">${companyInfo.companyname}님 환영합니다.<br/>오늘도 좋은 인연이 있길 바랍니다!</div>
				</c:when>
				<c:when test="${companyInfo eq null and userInfo eq null and adminInfo ne null}">
					<li style="background-color:#eeb300"><a href="adminInfoModify.do">비밀번호변경</a></li>
					<li style="background-color:#eeb300"><a href="adminLogout.do">로그아웃</a></li>
				</c:when>
			</c:choose>
		</ul>
		</div>
	</header>
	<section>
		<div class="mainsection">
			<a href="mainPage.do">홈</a>
			<a href="communityboard.do">자유게시판</a>
			<a href="companyboard.do">구인</a>
		</div>
	<div class="previewmenu">
		<div id="boxShadow">
		<div id="userboard">
		<p>구직게시판</p>
	<table>
		<tr>
			<td>번호</td><td>제목</td><td>이름</td><td>활용프로그램수</td><td>성별</td><td>작성일</td>
		</tr>
		<c:forEach var="dto" items="${list}">
			<tr>
				<td id="userNo">${dto.no}</td>
				<td id="userOverflow">
					<a href="userBoardView.do?no=${dto.no}&id=${dto.id}&username=${dto.username}">
					${dto.title}
					</a>
				</td>
				<td id="userName">${dto.username}</td>
				<td id="userProgramcount">${dto.programcount}</td>
				<td id="userGender">${dto.gender}</td>
				<td id="userWtime">${dto.wtime}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="writeText">
	<c:if test="${userInfo.id ne null}">
	<div id="userwrite">
		<a href="userBoardWrite.do" style="color:white">글쓰기</a>
	</div>
	</c:if>
	</div>
	<div class="page">
	<c:set var="curPage" value="${param.curPage}"/>
	<c:if test="${curPage eq null}">
		<c:set var="curPage" value="0"/>
	</c:if>
	<c:forEach var="i" begin="0" end="${totalPage}" step="1">
		<c:choose>
			<c:when test="${i eq curPage}">${i+1}</c:when>
			<c:when test="${i ne curPage}">
				<a href="userboard.do?curPage=${i}" style="color:white">${i+1}</a>
			</c:when>
		</c:choose>
	</c:forEach>
	</div>
	</div>
	</div>
	</div>
	</section>
</body>
</html>