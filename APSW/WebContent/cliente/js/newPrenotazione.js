var posti;
var radioButtonActivated = [];

function disableForm() {
	$("#numeroPersone").attr("disabled", "true");
	$("#turno").attr("disabled", "true");
	$("#posto").attr("disabled", "true");
	$("#numeroCarta").attr("disabled", "true");
	$("#submit").attr("disabled", "true");
}

function onDateChanged() {
	// la data minima che l'utente può scegliere è domani
	var today = new Date();  
	
	var dataInserita = new Date($("#data").val());

	if (dataInserita.getTime() > today.getTime()) {
		$("#errorDiv").html("");
		$("#numeroPersone").removeAttr("disabled");
		if ($("#numeroPersone").val() != ""){
			onNumeroPersoneChanged();
		}
	}
	else {
		$("#errorDiv").html("È possibile prenotare solo da domani");
		disableForm();
	}
}

function onNumeroPersoneChanged() {
	var data = $("#data").val();
	
	var numeroPersone = $("#numeroPersone").val();
	
	$.post("../GetPrenotazioniLibere",
		{
			data: data,
			numeroPersone: numeroPersone
		}).done(function(data) {
			posti = JSON.parse(data);
			updateTurno();
		});
}

function onTurnoChanged() {
	updatePosto();
}

function updateTurno() {
	var turno = $("#turno");
	turno.empty();
	
	for (var i = 0; i < Object.keys(posti).length; ++i)
		turno.append("<option>" + Object.keys(posti)[i] + "</option>");
	
	turno.removeAttr("disabled");
	
	updatePosto();
}

function updatePosto() {
	var selectedTurno = $("#turno :selected").text();
	
	for (var i = 0; i < radioButtonActivated.length; ++i) {
		radioButtonActivated[i].attr("disabled", true);
		radioButtonActivated[i].prop("checked", false);
	}
	
	radioButtonActivated = [];
	updateCosto();
	
	for (var i = 0; i < posti[selectedTurno].length; ++i) {
		var codPosto = posti[selectedTurno][i][0];
		$("#posto" + codPosto).removeAttr("disabled");
		radioButtonActivated.push($("#posto" + codPosto));
	}
	
	var numeroCarta = $("#numeroCarta");
	numeroCarta.removeAttr("disabled");
	
	var submitButton = $("#submit");
	submitButton.removeAttr("disabled");
}

function updateCosto() {
	var selectedTurno = $("#turno :selected").text();
	var selectedCodPostoArray = -1;
	
	for (var i = 0; i < radioButtonActivated.length; ++i) {
		if (radioButtonActivated[i].prop("checked")) {
			selectedCodPostoArray = i;
			break;
		}
	}
	
	if (selectedCodPostoArray == -1)
		$("#costoTotale").html("0.00 €");
	else
		$("#costoTotale").html(posti[selectedTurno][selectedCodPostoArray][1] + " €");
}