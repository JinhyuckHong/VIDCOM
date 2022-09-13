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
<link rel="stylesheet" href="companyBoardWriteCss.css">
</head>
<body>
	<c:if test="${companyInfo ne null}">
<header>
		<div class="logo">
			<a href="mainPage.do">
			<c:choose>
				<c:when test="${userInfo eq null and companyInfo ne null}">
					<h1 style="color:#425FFF">VIDCOM</h1>
					<p id="coplogin">Business Enterprise</p>
				</c:when>
			</c:choose>
			</a>
			</div>
	</header>
	<section>
	<form action="companyBoardWriteOK.do" method="post">
	<div class="previewmenu">
		<div id="boxShadow">
			<div id="companyboardWrite">
				<table>
					<tr>
						<td>
							<input type="text" onclick="titleValueChange()" id="title" name="title" value="제목을 입력해 주세요" style="color:#A6A7A8">
						</td>
					</tr>
					<tr>
						<td id="peopleTd">
							모집인원수
							<input type="text" onclick="peoplecountValueChange()" id="peoplecount" name="peoplecount" value="0" style="color:#A6A7A8" >
						</td>
					</tr>
					<tr>
						<td>
							<hr align="center"/>
						</td>
					</tr>
					<tr>
						<td>
						<textarea id="contents" name="contents" rows="25" cols="81"> ==양식 내 모든 항목기입 필수== &#10&#10 1.연락가능한 이메일 혹은 전화번호 : &#10&#10 2.지원방식:&nbsp&nbsp(이메일,전화,문자 중 택일) &#10&#10 3.모집분야: &#10&#10 4.작업진행지역(시,도): &#10&#10 5.예상작업기간(시간): &#10&#10 6.필요 개인장비:&nbsp&nbsp(제조사,카메라유형 등, ) &#10 개인장비사용시 지급금액: (금액명시 혹은 페이 포함중 택일) &#10&#10 7.페이 금액:&nbsp&nbsp만원&nbsp(금액명시 필수, 협의 또는 공백시 제재대상) &#10&#10 8.페이 지급 예상일: &#10&#10 ======================== &#10 (기재된 양식 외 추가 정보 필요시 작성) &#10 </textarea>
						<hr align="center"/>
						</td>
					</tr>
				</table>
			</div>
			<div id="underMenu">
				<input type="submit" value="등록">
				<a href="companyboard.do"><button type="button">목록</button></a>
			</div>
		</div>
	</div>
	</form>
	</section>
	<script src = "companyBoardJS.js"></script>
	</c:if>
	<c:if test="${companyInfo eq null}">페이지에러 <a href="mainPage.do">홈으로</a></c:if>
</body>
</html>