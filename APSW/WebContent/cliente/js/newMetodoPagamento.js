function validateForm() {
	var scadenzaAnno = $("#scadenzaAnno").val();
	var scadenzaMese = $("#scadenzaMese").val();

	var oggi = new Date();
	var annoCorrente = oggi.getFullYear();
	var meseCorrente = oggi.getMonth() + 1;
	
	var error = false;
	
	if (scadenzaAnno == annoCorrente) {
		if (scadenzaMese <= meseCorrente)
			error = true;
	} else if (scadenzaAnno < annoCorrente)
		error = true;
	
	if (error) {
		alert("La carta è già scaduta.");
		return false; //non validare il form
	}
	
	var numeroCarta = $("#numeroCarta").val();
	var vcc = $("#vcc").val();

	// "Validazione" del form tramite AJAX POST
	$.post("creaMetodoPagamento",
		{
			numeroCarta: numeroCarta,
			anno: scadenzaAnno,
			mese: scadenzaMese,
			vcc: vcc,
		}).done(function(data) {
			var parsedData = JSON.parse(data);
			if (parsedData["success"] == true)
				window.location = "metodiPagamento";
			else alert("Hai già inserito questo metodo di pagamento.\nRiprovare.");
		});
	return false; // "Non validare" il form --> in realtà in base al valore ricevuto dalla post viene fatto il redirect
}