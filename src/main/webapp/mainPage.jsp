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
<link rel="stylesheet" href="mainPageCss.css">
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
			<a href="communityboard.do">자유게시판</a>
			<a href="companyboard.do">구인</a>
			<a href="userboard.do">구직</a>
		</div>
		<div class="previewmenu">
		<div id="boxShadow">
			<div id="communityboard">
				<p>자유게시판 TOP5</p>
				<div>
				<table>
					<tr>
						<td>제목</td><td>닉네임</td><td>작성일</td><td>조회수</td>
					</tr>
					<c:forEach var="communitydto" items="${commylist}">
					<tr>
						<td id="commuOverflow">
							<a href="communityBoardView.do?no=${communitydto.no}&nickname=${communitydto.nickname}">
								<c:forEach var="i" begin="1" end="${communitydto.emptynum}">-</c:forEach>
								${communitydto.title}
							</a>
						</td>
						<td id="commuNickname">${communitydto.nickname}</td>
						<td id="commuWtime">${communitydto.wtime}</td>
						<td id="commuViewcount">${communitydto.viewcount}</td>
					</tr>
					</c:forEach>
				</table>
				</div>
			</div>
			<div id="companyboard">
				<p>구인 TOP5</p>
				<table>
					<tr>
						<td>제목</td><td>업체명</td><td>작성일</td><td>모집인원수</td>
					</tr>
					<c:forEach var="companydto" items="${companylist}">
					<tr>
						<td id="companyOverflow">
							<a href="companyBoardView.do?no=${companydto.no}&companyname=${companydto.companyname}">
							${companydto.title}
							</a>
						</td>
						<td id="companyName">${companydto.companyname}</td>
						<td id="companyWtime">${companydto.wtime}</td>
						<td id="companyPeoplecount">${companydto.peoplecount}</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<div id="userboard">	
				<p>구직 TOP5</p>
				<table>
					<tr>
						<td>제목</td><td>성별</td><td>작성일</td><td>활용 프로그램수</td>
					</tr>
					<c:forEach var="userdto" items="${userboardlist}">
					<tr>
						<td id="userOverflow">
							<a href="userBoardView.do?no=${userdto.no}&id=${userdto.id}&username=${userdto.username}">
							${userdto.title}
							</a>
						</td>
						<td id="userGender">${userdto.gender}</td>
						<td id="userWtime">${userdto.wtime}</td>
						<td id="userProgramcount">${userdto.programcount}</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			</div>
		</div>
	</section>
	<footer>
	</footer>
</body>
</html>