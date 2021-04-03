function validateForm(){
	var pass = $("#password").val();
	var hash = $.sha256(pass);
	
	$("#j_password").val(hash);
	
	return true;
}