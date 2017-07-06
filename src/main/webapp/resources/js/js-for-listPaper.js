
function addPaper() {
	$('#paperDialog').dialog("option","title", 'Add Paper');
	$('#paperDialog').dialog('open');
}

function editPaper(id) {
	
	$.get("get/" + id, function(result) {
		
		$("#paperDialog").html(result);
		$("#paperDialog").dialog("option","title", 'Edit Paper');
		$("#paperDialog").dialog('open');
		
		initializeDatePicker();
		
	});
}

function initializeDatePicker() {
	$(".datepicker").datepicker({
		dateFormat: "yy-mm-dd",
		changeMonth: true,
		changeYear: true,
		showButtonPanel: true
	});
}

function resetDialog(form) {
	
	form.find("input").val("");
}

$(document).ready(function() {
	
	$('#paperDialog').dialog({
		
		autoOpen : false,
		position : 'center',
		modal : true,
		resizable : false,
		width : 440,
		buttons : {
			"Save" : function() {
				$('#paperForm').submit();
			},
			"Cancel" : function() {
				$(this).dialog('close');
			}
		},
		
		close : function() {
			resetDialog($('#paperForm'));
			$(this).dialog('close');
		}
		
	});
	
	initializeDatePicker();
});