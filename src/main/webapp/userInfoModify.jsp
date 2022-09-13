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
<link rel="stylesheet" href="userInfoModifyCss.css">
</head>
<body id="body">
	<div class="box" id="boxjs">
	<div class="registBox">
	<div class="registbtn">
	<a href="mainPage.do" id="mainlogo">VIDCOM</a>
	</div>
	<div id="userregisterdisplay">
	<form action="userInfoModifyOK.do" method="post"
		onsubmit="return checkFn1()">
		<div class="id" id="userIdTXT">아이디</div>
		<input type="text" name="id" id="userId" value="${userInfo.id}" style="background-color:#069475;color:white" readonly><br>
		<div class="pw" id="userPwTXT">비밀번호</div>
		<input type="password" name="pw" id="pw1">
		<p id="pw1CheckText"></p>
		<div class="pwCheck1">비밀번호 확인</div>
		<input type="password" name="pwCheck" id="pwCheck1">
		<div id="pwerror1"></div>
		<div class="username">이름</div>
		<input type="text" name="username" id="userInputName" value="${userInfo.username}" style="background-color:#069475;color:white" readonly><br>
		<div class="nickname">닉네임</div>
		<input type="text" name="nickname" id="nickname" value="${userInfo.nickname}">
		<div id="nicknameCheckMsg"></div>
		<div class="email" id="email1">이메일</div>
		<input type="text" name="email" id="userInputEmail" value="${userInfo.email}">
		<div class="address" id="address1">주소</div>
		<input type="text" id="userpostcode" placeholder="우편번호"
			name="postcode" value="${userInfo.postcode}" readonly> <input type="button"
			onclick="userexecDaumPostcode()" id="userAddressBtn" value="우편번호 찾기"><input
			type="text" id="userroadAddress" placeholder="도로명주소"
			name="roadAddress" value="${userInfo.roadAddress}"> <input type="text" id="userjibunAddress"
			placeholder="지번주소" name="jibunAddress" value="${userInfo.jibunAddress}"> <span id="guide"
			style="color: #999; display: none"></span> <input type="text"
			id="userdetailAddress" placeholder="상세주소" name="detailAddress" value="${userInfo.detailAddress}">
		<input type="text" id="userextraAddress" placeholder="참고항목"
			name="extraAddress" value="${userInfo.extraAddress}"><br />
		<div class="birthday">생년월일</div>
		<div id="birthyear">
		<select class="birthyear" name="birthyear">
			<c:forEach var="i" begin="1920" end="2022">
				<option value="${i}" <c:if test="${userInfo.birthYear eq i}">selected</c:if> >${i}</option>
			</c:forEach>
		</select><p>년</p>
		</div>
		<div id="birthmonth">
		<select class="birthmonth" name="birthmonth">
			<c:forEach var="i" begin="1" end="12">
				<option value="${i}" <c:if test="${userInfo.birthMonth eq i}">selected</c:if> >${i}</option>
			</c:forEach>
		</select><p>월</p>
		</div>
		 <div id="birthdate">
		<select class="birthdate" name="birthdate">
			<c:forEach var="i" begin="1" end="31">
				<option value="${i}" <c:if test="${userInfo.birthDate eq i}">selected</c:if> >${i}</option>
			</c:forEach>
		</select><p>일</p>
		</div>
		<div class="phonenumber" id="phonenumber1">연락처</div>
		<input type="text" name="phonenumber" id="userInputNumber" value="${userInfo.phonenumber}">
		<div id="gender">성별</div>
		<div id="genderRadio">
		<input type="radio" id="genM" name="gender" value="남" ${userInfo.gender eq '남' ? 'checked':''}>
		<label for="genM">남자</label>
		<input type="radio" id="genW" name="gender" value="여" ${userInfo.gender eq '여' ? 'checked':''}>
		<label for="genW">여자</label>
		</div>
		<input type="submit" value="정보수정" class="submit" id="submit1">
		<input type="reset" value="초기화" class="reset" id="reset1">
	</form>
	</div>
	<script src="jqueryJS.js"></script>
	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="userInfoModifyJS.js"></script>
	</div>
	</div>
	<script>
		$('#nickname').focusout(function(){
			let nickname = $('#nickname').val();
			let id = $('#userId').val();

			$.ajax({
				url : "nicknameCheck.do?",
				type:  "post",
				data: {nickname: nickname, id: id},
				dataType: 'json',
				success: function(result){
					if(result == 0){
						$("#nicknameCheckMsg").html('이미 사용중인 닉네임입니다.');
						$("#nicknameCheckMsg").css("color","red");
						return false;
					} else{
						if(nickname.length != 0){
						$("#nicknameCheckMsg").html('사용 가능한 닉네임입니다.');
						$("#nicknameCheckMsg").css("color","#08C299");
						return true;
						}else{
							$("#nicknameCheckMsg").html('닉네임을 입력해주세요.');
							$("#nicknameCheckMsg").css("color","#7B7D7F");
							return false;
						}
					}
				},
				error:function(){
					alert("서버요청실패");
				}
			})
		})
	</script>
</body>
</html>