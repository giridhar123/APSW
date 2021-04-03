function onComplete(codOrdine){
	$.post("completaOrdine",
			{
				codOrdine: codOrdine
			}).done(function(data) {
				var parsedData = JSON.parse(data);
				if (parsedData["success"] == true){
					alert("L'ordine è stato completato correttamente");
					window.location = "ordiniSospesi";
				}else{
					alert("Si è verificato un errore durante il completamento dell'ordine.\nRiprovare.");
				}
			});
}