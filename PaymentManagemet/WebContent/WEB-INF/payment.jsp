<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/payment.js"></script>

<meta charset="ISO-8859-1">
<title>Fund Management</title>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Payment Management</h1>

	<form id="formItem" name="formItem">
		
		 Payment Date:
		<input id="Funders_name" name="Funders_name" type="text" class="form-control form-control-sm"><br> 
		Payment Method:
		<input id="Project_name" name="Project_name" type="text" class="form-control form-control-sm"><br>
		 Payment Amount:
		<input id="Amount" name="Amount" type="text" class="form-control form-control-sm"><br>
		
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemGrid">
	<%
		Payment fundObj = new Payment(); 
		 out.print(fundObj.ReadPayment());
	%>
	</div>
</div> </div> </div> 
	
</body>
</html>