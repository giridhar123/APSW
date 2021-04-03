var emailError = false;
var passwordError = false;

function validateForm() {
	if (emailError || passwordError)
		return false; //Non parte la submit
	
	var email = $("#email").val();
	var repEmail = $("#repeatEmail").val();
	
	var newPassword = $("#newPassword").val();
	var repNewPassword = $("#repeatNewPassword").val();

	var error = "<html>";
	if (email != repEmail)
		error = error + " <br/>Le E-mail devono essere uguali<br/> ";
		
	if (newPassword != repNewPassword)
		error = error + " <br/>Le password devono essere uguali<br/> ";
	
	error = error + "</html>";
    
	if (error == "<html></html>") { //se non ci sono stati errori
		
		// "Validazione" del form tramite AJAX POST
		$.post("modificaDati",
			{
				cmd: "submit",
				nome: $("#nome").val(),
				cognome: $("#cognome").val(),
				email: $("#email").val(),
				currentPassword: cifratura($("#password").val()),
				newPassword: cifratura($("#newPassword").val()),
			}).done(function(data) {
				var parsedData = JSON.parse(data);
				if (parsedData["success"] == true) {
					alert("Dati modificati correttamente.\nVerrà effettuato il logout automatico!")
					window.location = "../index.jsp";
				}
				else alert("C'è stato un errore durante la modifica dei dati.\nRiprovare.");
			});
		return false; //non parte la submit
	}
	else {
		$("#errors").html(error + "<br/>");
		return false; // non parte la submit
	}
}

function checkEmail() {
	var email = $("#email").val();
	
	$.post("modificaDati",
		{
			cmd: "checkEmail",
			email: email,
		}).done(function(data) {
			var parsedData = JSON.parse(data);
			if (parsedData["success"] == true)
				emailError = false;
			else
				emailError = true;
			
			updateErrorDiv();
		});
}

function updateErrorDiv() {	
	var error = "";
	
	if (emailError)
		error = error + "E-Mail già esistente<br/>";
	if (passwordError)
		error = error + "Inserisci la password corrente corretta<br/>";
	
	$("#errors").html(error);
}

function checkPassword() {
	$.post("modificaDati",
		{
			cmd: "checkPassword",
			password: cifratura($("#password").val()),
		}).done(function(data) {
			var parsedData = JSON.parse(data);
			if (parsedData["success"] == true)
				passwordError = false;
			else
				passwordError = true;
			
			updateErrorDiv();
		});
}


function cifratura(password){
	return $.sha256(password);
}