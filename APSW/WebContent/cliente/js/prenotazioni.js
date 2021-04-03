function onDeleteButtonClicked(codPrenotazione) {
	$.post("../deletePrenotazione",
		{
			codPrenotazione: codPrenotazione,
		}).done(function(data) {
			var parsedData = JSON.parse(data);
			if (parsedData["success"] == true){
				alert("La prenotazione è stata cancellata con successo.\nLa cifra sarà rimborsata entro due giorni lavorativi");
				window.location = "prenota";
			}
			else alert("Si è verificato un errore durante la cancellazione della prenotazione.\nRiprovare.");
		});
}