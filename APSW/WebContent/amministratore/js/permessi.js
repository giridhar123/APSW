function onSubmitButtonClicked() {
	var emailField = document.getElementById("email");
	
	if (!emailField.checkValidity())
		document.getElementById("errorText").innerHTML = emailField.validationMessage;
	else {
		document.getElementById("errorText").innerHTML = "";
		$.post("getUtente",
			{
				email: emailField.value
			}).done(function(data) {
				onUserInfoReceived(data);
			});
	}
}

function onUserInfoReceived(data) {
	var utente = JSON.parse(data);
	
	var htmlText;
	var outputDiv = $("#outputDiv");
	
	if(utente["success"] == true) {		
		var permesso = ["Cliente", "Bagnino", "Cassiere", "Biglietteria", "Amministratore"];
		
		htmlText =
			"<table class=\"customTable\"\">" +
				"<tr>" +
					"<th>Nome</th><th>Cognome</th><th>Email</th><th>Permesso</th>" +
				"</tr>" +
				"<tr>" +
					"<td>" + utente["nome"] + "</td>" +
					"<td>" + utente["cognome"] + "</td>" +
					"<td>" + utente["email"] + "</td>" +
					"<td><select id=\"permessi\">";
		
		for (var i = 0; i < permesso.length; ++i)
			htmlText = htmlText + "<option>" + permesso[i] + "</option>";
			
		htmlText = htmlText + "</td>" +
				"</tr>" +
			"</table>" +
			"<button class=\"customButton\" onClick=\"onEditButtonClicked()\">Modifica</button>";
	}
	else
		htmlText = "<html>Non esiste nessun utente con questo indirizzo email</html>";
	
	outputDiv.html(htmlText);
	
	if (utente["success"]) {
		document.getElementById("permessi").selectedIndex = utente["idPermesso"];
	}
}

function onEditButtonClicked() {
	var emailField = document.getElementById("email");
	var newPermesso = document.getElementById("permessi").selectedIndex;
	
	$.post("modificaPermessiUtente",
		{
			email: emailField.value,
			newPermesso: newPermesso
		}).done(function(data) {
			onUserEdited(data);
		});
}

function onUserEdited(data) {
	var result = JSON.parse(data);
	
	if (result["success"])
		alert("Permessi cambiati correttamente.");
	else alert("Si è verificato un errore, riprovare.");
}