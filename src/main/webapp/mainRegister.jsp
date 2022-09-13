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
<link rel="stylesheet" href="mainRegisterCss.css">
</head>
<body id="body">
<div class="box" id="boxjs">
	<div class="registBox">
	<div class="registbtn">
	<a href="mainPage.do" id="mainlogo">VIDCOM</a>
	<button onclick="companybtn()">개인회원가입</button>
	<button onclick="userbtn()">기업회원가입</button>
	</div>
	<div id="userregisterdisplay">
		<form action="userRegisterOK.do" method="post" onsubmit="return checkFn1()">
			<div class="id" id="userIdTXT">아이디</div>
			<input type="text" name="id" id="userId"><button id="id1">중복확인</button>
			<div id="idCheckMsg"></div>
			<div class="pw" id="userPwTXT">비밀번호</div>
			<input type="password" name="pw" id="pw1">
			<div id="pw1CheckText"></div>
			<div class="pwCheck1">비밀번호 확인</div>
			<input type="password" name="pwCheck" id="pwCheck1">
			<div id="pwerror1"></div>
			<br>
			<div class="username">이름</div>
			<input type="text" name="username" id="userInputName">
			<div class="nickname">닉네임</div>
			<input type="text" name="nickname" id="nickname">
			<div id="nicknameCheckMsg"></div>
			<div class="email" id="email1">이메일</div>
			<input type="text" name="email" id="userInputEmail">
			<div class="address" id="address1">주소</div>
			<input type="text" id="userpostcode" placeholder="우편번호"
				name="postcode" readonly> <input type="button"
				onclick="userexecDaumPostcode()" id="userAddressBtn" value="우편번호 찾기"><input
				type="text" id="userroadAddress" placeholder="도로명주소"
				name="roadAddress"> <input type="text" id="userjibunAddress"
				placeholder="지번주소" name="jibunAddress"> <span id="guide"
				style="color: #999; display: none"></span> <input type="text"
				id="userdetailAddress" placeholder="상세주소" name="detailAddress">
			<input type="text" id="userextraAddress" placeholder="참고항목"
				name="extraAddress"><br />
			<div class="birthday">생년월일</div>
			<div id="birthyear">
			<select class="birthyear" name="birthyear">
				<c:forEach var="i" begin="1920" end="2022">
					<option value="${i}" id="birthyearOp">${i}</option>
				</c:forEach>
			</select><p>년</p>
			</div>
			<div id="birthmonth">
			<select class="birthmonth" name="birthmonth">
				<c:forEach var="i" begin="1" end="12">
					<option value="${i}">${i}</option>
				</c:forEach>
			</select><p>월</p>
			</div>
			<div id="birthdate">
			<select class="birthdate" name="birthdate">
				<c:forEach var="i" begin="1" end="31">
					<option value="${i}">${i}</option>
				</c:forEach>
			</select><p>일</p>
			</div>
			<div class="phonenumber" id="phonenumber1">연락처</div>
			<input type="text" name="phonenumber" id="userInputNumber">
			<div id="gender">성별</div>
			<div id="genderRadio">
			<input type="radio" id="genM" name="gender" value="남자">
			<label for="genM">남자</label>
			<input type="radio" id="genW"name="gender" value="여자">
			<label for="genW">여자</label>
			</div>
			<input type="submit" value="회원가입" class="submit" id="submit1">
			<input type="reset" value="초기화" class="reset" id="reset1">
		</form>
	</div>
	<div id="companyregisterdisplay" style="display: none">
		<form action="companyRegisterOK.do" method="post" onsubmit="return checkFn2()">
			<div class="id" id="companyIdTXT">아이디</div>
			<input type="text" name="id" id="companyId"><button id="id2">중복확인</button>
			<div id="idCheckMsg2"></div>
			<div class="pw" id="companyPwTXT">비밀번호</div>
			<input type="password" name="pw" id="pw2">
			<p id="pw2CheckText"></p>
			<div class="pwCheck2">비밀번호 확인</div>
			<input type="password" name="pwCheck" id="pwCheck2">
			<p id="pwerror2"></p>
			<div class="companyname">기업명</div>
			<input type="text" name="companyname" id="companyInputName">
			<div class="email" id="email2">이메일</div>
			<input type="text" name="email" id="companyInputEmail">
			<div class="address" id="address2">주소</div>
			<input type="text" id="companypostcode" placeholder="우편번호"
				name="postcode" readonly> <input type="button"
				onclick="companyexecDaumPostcode()" id="companyAddressBtn" value="우편번호 찾기"><br />
			<input type="text" id="companyroadAddress" placeholder="도로명주소"
				name="roadAddress"> <input type="text"
				id="companyjibunAddress" placeholder="지번주소" name="jibunAddress">
			<span id="guide" style="color: #999; display: none"></span> <input
				type="text" id="companydetailAddress" placeholder="상세주소"
				name="detailAddress"> <input type="text"
				id="companyextraAddress" placeholder="참고항목" name="extraAddress"><br />
			<div class="phonenumber" id="phonenumber2">기업연락처</div>
			<input type="text" name="phonenumber" id="companyInputNumber"><input
				type="submit" value="회원가입" class="submit" id="submit2"> <input
				type="reset" value="초기화" class="reset" id="reset2">
		</form>
	</div>
	</div>
	</div>
	<script src="jqueryJS.js"></script>
	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="mainRegisterJS.js"></script>
	<script>
		$('#id1').click(function(){
			let id = $('#userId').val();

			$.ajax({
				url : "idCheck.do",
				type:  "post",
				data: {id: id},
				dataType: 'json',
				success: function(result){
					if(result == 0){
						$("#idCheckMsg").html('이미 사용중인 아이디입니다.');
						$("#idCheckMsg").css("color","red");
						return false;
					} else{
						if(id.length != 0){
							var korCh = userId.value.search(/[ㄱ-ㅎㅏ-ㅣ가-힣]/g);
							var specialCh = userId.value.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-+<>\#\$%&\\\=\(\'\"]/g);
							if(specialCh < 0 && korCh < 0){
								$("#idCheckMsg").html('사용 가능한 아이디입니다.');
								$("#idCheckMsg").css("color","#08C299");
								return true;
							}else if(specialCh == 0 || korCh == 0){
								$("#idCheckMsg").html('\'@\' 와 \'_\'를 제외한 특수문자와 한글은 포함할 수 없습니다.');
								$("#idCheckMsg").css("color","red");
								return false;
							}else{
								$("#idCheckMsg").html('\'@\' 와 \'_\'를 제외한 특수문자와 한글은 포함할 수 없습니다.');
								$("#idCheckMsg").css("color","red");
								return false;
							}
						}else{
							$("#idCheckMsg").html('아이디를 입력해주세요.');
							$("#idCheckMsg").css("color","#7B7D7F");
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
	<script>
		$('#id2').click(function(){
			let id = $('#companyId').val();
			$.ajax({
				url : "idCheck.do",
				type:  "post",
				data: {id: id},
				dataType: 'json',
				success: function(result){
					if(result == 0){
						$("#idCheckMsg2").html('이미 사용중인 아이디 입니다.');
						$("#idCheckMsg2").css("color","red");
						return false;
					} else{
						if(id.length != 0){
							var korCh = companyId.value.search(/[ㄱ-ㅎㅏ-ㅣ가-힣]/g);
							var specialCh = companyId.value.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-+<>\#$%&\\\=\(\'\"]/g);
							if(specialCh < 0 && korCh < 0){
								$("#idCheckMsg2").html('사용 가능한 아이디입니다.');
								$("#idCheckMsg2").css("color","#425FFF");
								return true;
							}else if(specialCh == 0 || korCh == 0){
								$("#idCheckMsg2").html('\'@\' 와 \'_\'를 제외한 특수문자와 한글은 포함할 수 없습니다.');
								$("#idCheckMsg2").css("color","red");
								return false;
							}else{
								$("#idCheckMsg").html('\'@\' 와 \'_\'를 제외한 특수문자와 한글은 포함할 수 없습니다.');
								$("#idCheckMsg").css("color","red");
								return false;
							}
						}else{
							$("#idCheckMsg2").html('아이디를 입력해주세요.');
							$("#idCheckMsg2").css("color","#7B7D7F");
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
	<script>
		$('#nickname').focusout(function(){
			let nickname = $('#nickname').val();

			$.ajax({
				url : "nicknameCheck.do",
				type:  "post",
				data: {nickname: nickname},
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