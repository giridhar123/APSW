function deleteProdotto(codProdotto){
	$.post("deleteProdottoBar",
			{
			codProdotto: codProdotto
			}).done(function(data) {
				var parsedData = JSON.parse(data);
				if (parsedData["success"] == true){
					alert("Il prodotto è stato cancellato correttamente");
					window.location = "gestioneProdotti";
				}else{
					alert("Si è verificato un errore durante la cancellazione del prodotto.\nRiprovare.");
				}
			});
}