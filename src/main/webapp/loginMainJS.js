function userlogin(){
	document.getElementById("companylogindisplay").style.display="none";
	document.getElementById("userlogindisplay").style.display="";
	document.getElementById("body").style.backgroundColor="#E2F5F0";
	document.getElementById("boxjs").style.boxShadow="0px 0px 20px 0px #069475";
	document.getElementById("img").src="userlogin.jpg";
}
function companylogin(){
	document.getElementById("companylogindisplay").style.display="";
	document.getElementById("userlogindisplay").style.display="none";
	document.getElementById("body").style.backgroundColor="#DCE5FC";
	document.getElementById("boxjs").style.boxShadow="0px 0px 20px 0px #364ED1";
	document.getElementById("img").src="companylogin.jpg";
}