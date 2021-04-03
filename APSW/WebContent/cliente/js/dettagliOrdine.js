function onDeleteButtonClicked(codOrdine, metodoPagamento){
	$.post("deleteOrdineBar",
			{
				codOrdine: codOrdine
			}).done(function(data) {
				var parsedData = JSON.parse(data);
				if (parsedData["success"] == true){
					if(metodoPagamento=="Contanti"){
						alert("L'ordine è stato cancellato correttamente");
					}else{
						alert("L'ordine è stato cancellato correttamente. \n La cifra sarà rimborsata entro due giorni lavorativi");
					}
					window.location = "ordini";
				}else{
					alert("Si è verificato un errore durante la cancellazione della prenotazione.\nRiprovare.");
				}
			});
}