var emailError = false;

function validateForm() {
	if (emailError)
		return false;  //Non parte la submit
	
	var email = $("#email").val();
	var repEmail = $("#repeatEmail").val();
	
	var password = $("#password").val();
	var repPassword = $("#repeatPassword").val();

	var error = "<html>";
	if (email != repEmail)
		error = error + " <br/>Le E-Mmail devono essere uguali<br/> ";
		
	if (password != repPassword)
		error = error + " <br/>Le password devono essere uguali<br/> ";
	
	error = error + "</html>";
    
	if (error == "<html></html>"){ //se non ci sono stati errori
		cifratura();
		return true; // parte la submit
	}else {
		$("#errors").html(error + "<br/>");
		return false; // non parte la submit
	}
}

function checkEmail() {
	var email = $("#email").val();
	
	$.post("CheckEmail",
		{
			email: email,
		}).done(function(data) {
			var parsedData = JSON.parse(data);
			if (parsedData["success"] == true)
				emailError = false;
			else
				emailError = true;
			
			updateEmailErrorDiv();
		});
}

function updateEmailErrorDiv() {	
	if (emailError)
		$("#errors").html("E-Mail già esistente");
	else $("#errors").html("");
		
}

function cifratura(){
	var pass = $("#password").val();
	var hash = $.sha256(pass);
	$("#password").val(hash);
}