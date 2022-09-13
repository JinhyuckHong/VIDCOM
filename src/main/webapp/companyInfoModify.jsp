<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="HTML Study">
<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta name="author" content="Bruce">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>VideoWork</title>
<link rel="stylesheet" href="companyInfoModifyCss.css">
</head>
<body id="body">
<div class="box" id="boxjs">
	<div class="registBox">
	<div class="registbtn">
	<a href="mainPage.do" id="mainlogo">VIDCOM</a>
	</div>
	<div id="companyregisterdisplay">
	<form action="companyInfoModifyOK.do" method="post" onsubmit="return checkFn2()">
		<div class="id" id="companyIdTXT">아이디</div>
		<input type="text" name="id" id="companyId" value="${companyInfo.id}" style="background-color:#364ED1; color:white " readonly>
		<div class="pw" id="companyPwTXT">비밀번호</div>
		<input type="password" name="pw" id="pw2">
		<p id="pw2CheckText"></p>
		<div class="pwCheck2">비밀번호 확인</div>
		<input type="password" name="pwCheck" id="pwCheck2">
		<p id="pwerror2"></p>
		<div class="companyname">기업명</div>
		<input type="text" name="companyname" id="companyInputName" value="${companyInfo.companyname}" style="background-color:#364ED1; color:white " readonly>
		<div class="email" id="email2">이메일</div>
		<input type="text" name="email" id="companyInputEmail" value="${companyInfo.email}">
		<div class="address" id="address2">주소</div>
		<input type="text" id="companypostcode" placeholder="우편번호"
			name="postcode" value="${companyInfo.postcode}" readonly> <input type="button"
			onclick="companyexecDaumPostcode()" id="companyAddressBtn" value="우편번호 찾기"><input
			type="text" id="companyroadAddress" placeholder="도로명주소"
			name="roadAddress" value="${companyInfo.roadAddress}"> <input type="text"
			id="companyjibunAddress" placeholder="지번주소" name="jibunAddress" value="${companyInfo.jibunAddress}">
		<span id="guide" style="color: #999; display: none"></span> <input
			type="text" id="companydetailAddress" placeholder="상세주소"
			name="detailAddress" value="${companyInfo.detailAddress}"> <input type="text"
			id="companyextraAddress" placeholder="참고항목" name="extraAddress" value="${companyInfo.extraAddress}">
		<div class="phonenumber" id="phonenumber2">기업연락처</div>
		<input type="text" name="phonenumber"  id="companyInputNumber" value="${companyInfo.phonenumber}"><input
			type="submit" value="정보수정" class="submit" id="submit2"> <input
			type="reset" value="초기화" class="reset" id="reset2">
	</form>
	</div>
	<script src="jqueryJS.js"></script>
	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="companyInfoModifyJS.js"></script>
	</div>
	</div>
</body>
</html>