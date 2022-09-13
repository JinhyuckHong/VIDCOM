function companyexecDaumPostcode() {
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
				document.getElementById('companypostcode').value = data.zonecode;
				document.getElementById("companyroadAddress").value = roadAddr;
				document.getElementById("companyjibunAddress").value = data.jibunAddress;
				if (roadAddr !== '') {
					document.getElementById("companyextraAddress").value = extraRoadAddr;
				} else {
					document.getElementById("companyextraAddress").value = '';
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
var pw2 = document.getElementById("pw2");
var pwCheck2 = document.getElementById("pwCheck2");

pw2.addEventListener("focusout",engNumFn2);
pwCheck2.addEventListener("focusout", checkFn2);

function engNumFn2(){
	var num = pw2.value.search(/[0-9]/g);
	var eng = pw2.value.search(/[a-z]/ig);
	var specialCh = pw2.value.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g);
	
	if(pw2.value.length < 8){
		document.getElementById("pw2CheckText").innerText = "비밀번호는 8자 이상입니다";
		document.getElementById("pw2CheckText").style.color = "red";
		return false;
	}else if(num < 0 || eng < 0 || specialCh < 0){
		document.getElementById("pw2CheckText").innerText = "영어,숫자,특수문자가 포함되어야 합니다";
		document.getElementById("pw2CheckText").style.color = "red";
		return false;
	}else{
		document.getElementById("pw2CheckText").innerText = "사용가능한 비밀번호입니다";
		document.getElementById("pw2CheckText").style.color = "#425FFF";
		return true;
	}
}

function checkFn2() {
	if (pw2.value == pwCheck2.value) {
		if(pw2.value.length == 0){
			document.getElementById("pwerror2").innerText = "변경할 비밀번호를 먼저 입력해주세요";
			document.getElementById("pwerror2").style.color = "black";
			return false;
		}else if(pw2.value.length != 0 && pw2.value.length > 7){
			document.getElementById("pwerror2").innerText = "비밀번호가 일치합니다";
			document.getElementById("pwerror2").style.color = "#425FFF";
			return true;
		}
	} else {
		document.getElementById("pwerror2").innerText = "비밀번호가 일치하지않습니다";
		document.getElementById("pwerror2").style.color = "red";
		return false;
	}
}