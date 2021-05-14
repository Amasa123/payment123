$(document).on("click", "#btnSave", function(event){ 
	
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
 
	 
	// Form validation-------------------
	var status = validateItemForm(); 
	if (status != true) 
	 { 
	 $("#alertError").text(status); 
	 $("#alertError").show(); 
	 
 return; 
} 


// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
	$.ajax( 
	{ 
	 url : "FundAPI", 
	 type : type, 
	 data : $("#formItem").serialize(), 
	 dataType : "text", 
	 complete : function(response, status) { 
		 
		 onItemSaveComplete(response.responseText, status); 
	 } 
	}); 
});

function onItemSaveComplete(response, status){ 
	if (status == "success") {
		
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") { 
			 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 $("#divItemsGrid").html(resultSet.data); 
		 } 
		 else if (resultSet.status.trim() == "error") {
			 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
	} 
	else if (status == "error") { 
		
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
	} else{ 
		
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
		}
	$("#hidItemIDSave").val(""); 
	$("#formItem")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event){ 
		
		 $("#hidItemIDSave").val($(this).data("userid")); 
		 $("#Funders_name").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#Project_name").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#Amount").val($(this).closest("tr").find('td:eq(2)').text()); 
		// $("#").val($(this).closest("tr").find('td:eq(3)').text()); 
		 
});





$(document).on("click", ".btnRemove", function(event) { 
	 $.ajax( 
	 { 
	 	url : "FundAPI", 
	 	type : "DELETE", 
	 	data : "ID=" + $(this).data("userid"),
	 	dataType : "text", 
	 	complete : function(response, status) { 
	 		onItemDeleteComplete(response.responseText, status); 
	 	} 
	}); 
})
	


function onItemDeleteComplete(response, status){
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response); 
			if (resultSet.status.trim() == "success"){
			
				$("#alertSuccess").text("Successfully deleted."); 
				$("#alertSuccess").show(); 
				$("#divItemsGrid").html(resultSet.data); 
				
			} else if (resultSet.status.trim() == "error") { 
				
				$("#alertError").text(resultSet.data); 
				$("#alertError").show(); 
		} 
	} 
	else if (status == "error") { 
		$("#alertError").text("Error while deleting."); 
		$("#alertError").show(); 
	} 
	else { 
		$("#alertError").text("Unknown error while deleting.."); 
		$("#alertError").show(); 
	} 
}

// CLIENT-MODEL================================================================
function validateItemForm(){
	// CODE

	
// PRICE-------------------------------
if ($("#Amount").val().trim() == ""){
	
	return "Insert Item Price.";
}
// is numerical value
var tmpPrice = $("#Amount").val().trim();
if (!$.isNumeric(tmpPrice)){
			
	return "Insert a numerical value for budget.";
}
		
// convert to decimal price
$("#Amount").val(parseFloat(tmpPrice).toFixed(2));


	return true;
}