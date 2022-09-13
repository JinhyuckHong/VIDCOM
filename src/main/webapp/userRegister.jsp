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
</head>
<body>
	<form action="userRegisterOK.do" method="post">
	<div class="id">아이디</div>
	<input type="text" name="id"><br>
	<div class="pw">비밀번호</div>
	<input type="password" name="pw"><br>
	<div class="pwCheck">비밀번호 확인</div>
	<input type="password" name="pwCheck"><br>
	<div class="username">이름</div>
	<input type="text" name="username"><br>
	<div class="nickname">닉네임</div>
	<input type="text" name="nickname"><br>
	<div class="email">이메일</div>
	<input type="text" name="email"><br>
	<div class="address">주소</div>
	<input type="text" id="postcode" placeholder="우편번호" name="postcode" readonly>
	<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br />
	<input type="text" id="roadAddress" placeholder="도로명주소" name="roadAddress">
	<input type="text" id="jibunAddress" placeholder="지번주소" name="jibunAddress">
	<span id="guide" style="color:#999;display:none"></span>
	<input type="text" id="detailAddress" placeholder="상세주소" name="detailAddress">
	<input type="text" id="extraAddress" placeholder="참고항목" name="extraAddress"><br/>
	<div class="address">생년월일</div>
	<select class="birthyear" name="birthyear">
		<c:forEach var="i" begin="1920" end="2022">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>년
	<select class="birthmonth" name="birthmonth">
		<c:forEach var="i" begin="1" end="12">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>월
	<select class="birthdate" name="birthdate">
		<c:forEach var="i" begin="1" end="31">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>일
	<div class="phonenumber">핸드폰번호</div>
	<input type="text" name="phonenumber"><br>
	<input type="radio" name="gender" value="남">남자
	<input type="radio" name="gender" value="여">여자<br>
	<input type="submit" value="회원가입" class="submit">
	<input type="reset" value="초기화" class="reset">
	</form>
	
<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function sample4_execDaumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							var roadAddr = data.roadAddress; // 도로명 주소 변수
							var extraRoadAddr = ''; // 참고 항목 변수
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraRoadAddr += data.bname;
							}
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraRoadAddr += (extraRoadAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							if (extraRoadAddr !== '') {
								extraRoadAddr = ' (' + extraRoadAddr + ')';
							}
							document.getElementById('postcode').value = data.zonecode;
							document.getElementById("roadAddress").value = roadAddr;
							document.getElementById("jibunAddress").value = data.jibunAddress;
							if (roadAddr !== '') {
								document.getElementById("extraAddress").value = extraRoadAddr;
							} else {
								document.getElementById("extraAddress").value = '';
							}
							var guideTextBox = document.getElementById("guide");
							if (data.autoRoadAddress) {
								var expRoadAddr = data.autoRoadAddress
										+ extraRoadAddr;
								guideTextBox.innerHTML = '(예상 도로명 주소 : '
										+ expRoadAddr + ')';
								guideTextBox.style.display = 'block';
							} else if (data.autoJibunAddress) {
								var expJibunAddr = data.autoJibunAddress;
								guideTextBox.innerHTML = '(예상 지번 주소 : '
										+ expJibunAddr + ')';
								guideTextBox.style.display = 'block';
							} else {
								guideTextBox.innerHTML = '';
								guideTextBox.style.display = 'none';
							}
						}
					}).open();
		}
	</script>	
</body>
</html>