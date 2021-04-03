function segnaPagato(codPrenotazione) {
	$.post("pagaPrenotazione",
			{
				codPrenotazione: codPrenotazione
			}).done(function(data) {
				var parsedData = JSON.parse(data);
				if (parsedData["success"] == true)
					location.reload();
				else alert("C'è stato un errore durante il pagamento.\nRiprovare.");
			});
}

function onDeleteButtonClicked(codPrenotazione) {
	$.post("../deletePrenotazione",
		{
			codPrenotazione: codPrenotazione,
		}).done(function(data) {
			var parsedData = JSON.parse(data);
			if (parsedData["success"] == true){
				alert("La prenotazione è stata cancellata con successo.");
				location.reload(true);
			}
			else alert("Si è verificato un errore durante la cancellazione della prenotazione.\nRiprovare.");
		});
}