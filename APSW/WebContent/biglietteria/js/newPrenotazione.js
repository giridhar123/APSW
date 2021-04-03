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
	dataInserita.setHours(today.getHours());
	dataInserita.setMinutes(today.getMinutes());
	dataInserita.setSeconds(today.getSeconds());
	dataInserita.setMilliseconds(today.getMilliseconds());

	if (dataInserita.getTime() >= today.getTime()) {
		$("#errorDiv").html("");
		$("#numeroPersone").removeAttr("disabled");
		if ($("#numeroPersone").val() != ""){
			onNumeroPersoneChanged();
		}
	}
	else {
		$("#errorDiv").html("Devi inserire una data maggiore o uguale a quella di oggi");
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
	
	checkBoxChanged(); //Aggiorna lo stato del campo della carta di credito
	
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

function checkBoxChanged() {
	var checked = $("#isContanti").prop("checked");
	
	if (checked)
		$("#numeroCarta").attr("disabled", true);
	else $("#numeroCarta").removeAttr("disabled");
}