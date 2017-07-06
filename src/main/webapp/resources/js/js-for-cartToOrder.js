	$(document).ready(function() {
		$('.dialog').dialog({
			autoOpen : true,
	        my: "center",
	        at: "center",
	        of: window,
			modal : true,
			resizable : false,
			width : 500,
			height: 400,
			close : function() {
				$(this).dialog('close');
			}
		});
	})
	
	
	
	function addCart(code) {
		
		var input = $('#'+code);
		input.val(parseInt(input.val())+1);
		$('#'+code).trigger('change');
		if(parseInt(input.val())!=1){
			$('#min'+code).attr('disabled',false);
		}
	}
	
	function minCart(code) {
		
		var input = $('#'+code);
		if(parseInt(input.val())==1){
			$('#min'+code).attr('disabled',true);
		} else {
			input.val(parseInt(input.val())-1);
			$('#'+code).trigger('change');
		}
	}
	
	function sendAndUpdate(code) {
		
		if($('#'+code).val()>=1) {
			$.get('singleupdate/' + code + '/' + $('#'+code).val(), function(msg){
				if(msg=='success')
					window.location.reload();
			});
		} else {
			
			alert("商品数量必须大于等于一");
			$('#'+code).val(1);
			$('#'+code).trigger('change');
		}
	}
	
	function delet(code) {
		window.location.reload();
		$.get('deleteproduct/' + code, function(msg) {
			if(msg=='success')
				window.location.reload();
		})
	}