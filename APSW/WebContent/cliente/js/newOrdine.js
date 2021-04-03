var carrello = [];

function aggiungiProdotti(codProdotto){
	
	var nomeProdotto=$("#nomeProdotto"+codProdotto).text();
	var costoProdotto=$("#costo"+codProdotto).text();
	var quantitaProdotto=parseFloat($("#quantitaProdottiBar"+codProdotto).val());
	var conto=parseFloat($("#conto").text());
	
	$.post("aggiornaCarrello",
		{
			codProdotto: codProdotto,
			qtn: quantitaProdotto,
			cmd: "aggiungi"
		}).done(function(data) {
			result = JSON.parse(data);
			if(result){
				if (carrello[codProdotto] == undefined)
					carrello[codProdotto] = quantitaProdotto;
				else carrello[codProdotto] += quantitaProdotto;
				
				updateOutputDiv();
				$("#quantitaProdottiBar"+codProdotto).val("");
			}
		});
}

function rimuoviProdotti(i){

	var costoProdotto=$("#costo"+i).text();
	var quantitaProdotto=parseFloat($("#quantitaProdottiBar"+i).val());
	var conto=parseFloat($("#conto").text());
	var tmp=parseFloat(costoProdotto)*quantitaProdotto;
	
	$.post("aggiornaCarrello",
		{
			codProdotto: i,
			qtn: quantitaProdotto,
			cmd: "rimuovi"
		}).done(function(data) {
			result = JSON.parse(data);
			if(result){
				carrello[i] = undefined;				
				updateOutputDiv();
			}
		});
}

//aggiorno dinamicamente la visualizzazione dei prodotti nel carrello
function updateOutputDiv() {
	var htmlText = "<table class=\"customTable\">" +
						"<tr>" +
							"<th>Prodotto</th>" +
							"<th>Quantita</th>" +
							"<th>Rimuovi</th>" +
						"</tr>";
	
	elementoNelCarrello = false;
	for (var i = 0; i < carrello.length; ++i) {
		if (carrello[i] == undefined)
			continue;

		htmlText = htmlText + "<tr>" +
								"<td>" + $("#nomeProdotto"+i).text() + "</td>" +
								"<td>" + carrello[i] + "</td>" +
								"<td><button id=\"rimuoviProdotti" + i + "\" class=\"customButtonSmall\" onclick=\"rimuoviProdotti(" + i + ")\">X</button></td>" +
							"</tr>";
		
		elementoNelCarrello = true;
	}
	
	htmlText = htmlText + "</table>";
	
	if (elementoNelCarrello){
		$("#outputDiv").html(htmlText);
		$("#numeroCarta").removeAttr("disabled");
		$("#submit").removeAttr("disabled");
	}else{
		$("#outputDiv").html("");
		$("#numeroCarta").attr("disabled", true);
		$("#submit").attr("disabled", true);
	}
	updateTotale();
}

function updateTotale(){
	var tot=0;
	for (var i=0; i< carrello.length; ++i){
		if(carrello[i]==undefined){
			continue;
		}else{
			tot+=carrello[i]*$("#costo"+i).text();
		}
	}
	$("#conto").text(tot+" €");
}