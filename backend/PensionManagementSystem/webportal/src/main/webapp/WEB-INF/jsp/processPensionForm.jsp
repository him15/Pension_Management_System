<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Process Pension</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js"
	type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" />
<link rel="stylesheet" href="/style/style.css">

</head>
<body class="d-flex flex-column min-vh-100 text-warning">
	<nav class="navbar navbar-expand-lg navbar-dark"> <i
		class="material-icons icon-size mr-4">savings</i> <a id="navheading"
		class="navbar-brand"> State Government Pension</a>


	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarNav">
		<ul id="navlink" class="navbar-nav float-right text-right ml-auto">
			<li class="nav-item"><a class="logout text-white" href="/logout"><i
					title="Log Out" class="logout material-icons">power_settings_new</i>
			</a></li>
		</ul>

	</div>
	</nav>

	<div class="container mt-4">

		<div class="card text-white">

			<div class="card-body">
				<h2 id="formheading" class="card-title mb-4">
					Enter the Details <span class="h6 ml-4 pl-4 mandatory">* -
						Mandatory Field</span>
				</h2>

				<form:form id="processPensionForm" name="processPensionForm"
					modelAttribute="processPensionInput" action="/postProcessPension"
					method="POST">



					<div class="form-row">
						<div class="form-group col-sm-12">

							<form:label class="formlabel" path="aadhaarNumber">Aadhaar Number<span
									class="mandatory">*</span>
							</form:label>
							<form:input class="form-control" type="text" path="aadhaarNumber"
								name="aadhaarNumber" placeholder="Enter 16 digit Aadhaar number"
								value="${aadhaar}" pattern="[0-9]{16}" required="true"></form:input>
							<form:errors path="aadhaarNumber"></form:errors>
							<br>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-sm-12">

							<form:label class="formlabel" path="pensionAmount">Pension Amount (Rs.)<span
									class="mandatory">*</span>
							</form:label>
							<form:input class="form-control" type="text" path="pensionAmount"
								name="pensionAmount" placeholder="Enter pension amount"
								value="${pensionAmount}" pattern="^[1-9]\d*(\.\d+)?$"
								required="true"></form:input>
							<form:errors path="pensionAmount"></form:errors>
							<br>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-sm-12">

							<form:label class="formlabel" path="bankServiceCharge">Bank Service Charge (Rs.)<span
									class="mandatory">*</span>
							</form:label>
							<form:input class="form-control" type="text"
								path="bankServiceCharge" name="bankServiceCharge"
								placeholder="Enter bank service charge" value="${serviceCharge}"
								pattern="^[1-9]\d*(\.\d+)?$" required="true"></form:input>
							<form:errors path="bankServiceCharge"></form:errors>
							<br>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-3"></div>
						<div class="form-group col-sm-12 col-md-6">

							<input type="submit" name="submit"
								class="text-center btn btn-success btn-lg btn-block formlabel"
								value="Process Pension">
						</div>
					</div>

				</form:form>
				<div class="form-row">
					<div class="form-group col-md-3"></div>

					<div class="form-group col-sm-12 col-md-6">
						<a class="tryagain" href="/pensionerDetailForm" class="text-white">
							<button name="retry"
								class="btn btn-success btn-lg btn-block formlabel">Calculate
								Again</button>
						</a>

					</div>
				</div>


			</div>
		</div>
	</div>
	<footer class="text-lg-start container-fluid mt-auto"> <!-- Copyright -->
	Copyright 2021 <!-- Copyright --> </footer>
</body>
<script>
	$("#dateOfBirth").datepicker({
		uiLibrary : "bootstrap4",
		format : "dd-mm-yyyy",
		maxDate : new Date(),
	});
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
	integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
	crossorigin="anonymous"></script>
</html>