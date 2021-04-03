function onDeleteButtonClicked(numeroCarta) {
	$.post("deleteMetodoPagamento",
		{
			numeroCarta: numeroCarta,
		}).done(function(data) {
			var parsedData = JSON.parse(data);
			if (parsedData["success"] == true)
				window.location = "metodiPagamento";
			else alert("Si è verificato un errore durante la cancellazione del metodo di pagamento.\nRiprovare.");
		});
}