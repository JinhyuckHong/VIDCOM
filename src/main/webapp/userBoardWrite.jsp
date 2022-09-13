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
<link rel="stylesheet" href="userBoardWriteCss.css">
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
	<form action="userBoardWriteOK.do" method="post">
	<div class="previewmenu">
		<div id="boxShadow">
			<div id="userboardWrite">
				<table>
					<tr>
						<td>
							<input type="text" onclick="titleValueChange()" id="title" name="title" value="제목을 입력해 주세요" style="color:#A6A7A8">
						</td>
					</tr>
					<tr>
						<td id="programTd">
							활용가능 프로그램 수
							<input type="text" onclick="programcountValueChange()" id="programcount" name="programcount" value="0" style="color:#A6A7A8" >
						</td>
					</tr>
					<tr>
						<td>
							<hr align="center"/>
						</td>
					</tr>
					<tr>
						<td>
							<textarea id="contents" name="contents" rows="25" cols="81">==양식 내 모든 항목기입 필수== &#10&#10 1.지원분야 : &#10&#10 2.연락가능한 이메일 혹은 전화번호 : &#10&#10 3.거주지역 : &#10&#10 4.희망 업무 지역 : &#10&#10 5.희망 페이 금액 : (금액명시 혹은 협의) &#10&#10 6.활용가능 프로그램 : &#10&#10 7.보유중인 기기 : &#10&#10 ======================== &#10 (기재된 양식 외 추가내용 작성) &#10 </textarea>
							<hr align="center"/>
						</td>
					</tr>
				</table>
			</div>
			<div id="underMenu">
				<input type="submit" value="등록">
				<a href="userboard.do"><button type="button">목록</button></a>
			</div>
		</div>
	</div>
	</form>
	</section>
	<script src = "userBoardJS.js"></script>
	</c:if>
	<c:if test="${userInfo eq null}">페이지에러 <a href="mainPage.do">홈으로</a></c:if>
</body>
</html>