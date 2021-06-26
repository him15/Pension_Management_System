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
<title>Pensioner Detail</title>
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

	<div class="container">

		<div class="card text-white">

			<div class="card-body">
				<h2 id="formheading" class="card-title">
					Enter Pensioner Details<span class="h6 ml-4 pl-4 mandatory">*
						- Mandatory Field</span>
				</h2>
				<form:form id="pensionerDetailForm" name="pensionerDetailForm"
					modelAttribute="pensioner" action="/getPensionDetail" method="GET">

					<div class="form-row">
						<div class="form-group col-md-8 col-sm-12">

							<form:label class="formlabel" path="name">Name<span
									class="mandatory">*</span>
							</form:label>
							<form:input class="form-control" type="text" path="name"
								name="name" placeholder="Enter pensioner name" minlength="1"
								required="true"></form:input>
							<form:errors path="name"></form:errors>
							<br>
						</div>
						<div class="form-group col-md-4 col-sm-12">
							<form:label class="formlabel" path="dateOfBirth">Date of Birth<span
									class="mandatory">*</span>
							</form:label>
							<form:input id="dateOfBirth" class="form-control"
								path="dateOfBirth" type="text" name="dateOfBirth"
								placeholder="dd-MM-yyyy" required="true"></form:input>
							<form:errors path="dateOfBirth"></form:errors>
							<br>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-sm-12">

							<form:label class="formlabel" path="panNumber">Permanent Account Number (PAN)<span
									class="mandatory">*</span>
							</form:label>
							<form:input class="form-control" type="text" path="panNumber"
								name="panNumber" placeholder="Enter PAN number"
								pattern="[a-zA-Z0-9]{10}" required="true"></form:input>
							<form:errors path="panNumber"></form:errors>
							<br>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-sm-12">

							<form:label class="formlabel" path="aadhaarNumber">Aadhaar Number<span
									class="mandatory">*</span>
							</form:label>
							<form:input class="form-control" type="text" path="aadhaarNumber"
								name="aadhaarNumber" placeholder="Enter 16 digit Aadhaar number"
								pattern="[0-9]{16}" required="true"></form:input>
							<form:errors path="aadhaarNumber"></form:errors>
							<br>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-sm-12">

							<form:label class="formlabel" path="typeOfPension">Pension Type<span
									class="mandatory">*</span>
							</form:label>

							<form:select class="form-control" name="typeOfPension"
								path="typeOfPension" required="true">
								<option>Self Pension</option>
								<option>Family Pension</option>
							</form:select>
							<form:errors path="typeOfPension"></form:errors>
							<br>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-3"></div>

						<div class="form-group col-sm-12 col-md-6">

							<input class="btn btn-success btn-block btn-lg formlabel"
								type="submit" name="submit" value="Calculate Pension">
						</div>
					</div>
				</form:form>
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
		icons : {
			rightIcon : '<i class="material-icons calendar">date_range</i>'
		}
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