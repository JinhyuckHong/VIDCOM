function userexecDaumPostcode() {
	new daum.Postcode(
		{
			oncomplete: function(data) {
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
				document.getElementById('userpostcode').value = data.zonecode;
				document.getElementById("userroadAddress").value = roadAddr;
				document.getElementById("userjibunAddress").value = data.jibunAddress;
				if (roadAddr !== '') {
					document.getElementById("userextraAddress").value = extraRoadAddr;
				} else {
					document.getElementById("userextraAddress").value = '';
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
var pw1 = document.getElementById("pw1");
var pwCheck1 = document.getElementById("pwCheck1");

pw1.addEventListener("focusout",engNumFn1);
pwCheck1.addEventListener("focusout", checkFn1);

function engNumFn1(){
	var num = pw1.value.search(/[0-9]/g);
	var eng = pw1.value.search(/[a-z]/ig);
	var specialCh = pw1.value.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g);
	
	if(pw1.value.length < 8){
		document.getElementById("pw1CheckText").innerText = "비밀번호는 8자 이상입니다";
		document.getElementById("pw1CheckText").style.color = "red";
		return false;
	}else if(num < 0 || eng < 0 || specialCh < 0){
		document.getElementById("pw1CheckText").innerText = "영어,숫자,특수문자가 포함되어야 합니다";
		document.getElementById("pw1CheckText").style.color = "red";
		return false;
	}else{
		document.getElementById("pw1CheckText").innerText = "사용가능한 비밀번호입니다";
		document.getElementById("pw1CheckText").style.color = "#08C299";
		return true;
	}
}

function checkFn1() {
	if (pw1.value == pwCheck1.value) {
		if(pw1.value.length == 0){
			document.getElementById("pwerror1").innerText = "변경할 비밀번호를 먼저 입력해주세요";
			document.getElementById("pwerror1").style.color = "#7B7D7F";
			return false;
		}else if(pw1.value.length != 0 && pw1.value.length > 7){
			document.getElementById("pwerror1").innerText = "비밀번호가 일치합니다";
			document.getElementById("pwerror1").style.color = "#08C299";
			return true;
		}
	} else {
		document.getElementById("pwerror1").innerText = "비밀번호가 일치하지않습니다";
		document.getElementById("pwerror1").style.color = "red";
		return false;
	}
}