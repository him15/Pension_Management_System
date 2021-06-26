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
<title>Calculation Success</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
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

		<div class="card text-white align-items-center">

			<div class="card-body">
				<h2 id="formheading" class="card-title mb-4">Pension Details</h2>
				<table>
					<tbody>
						<tr>
							<td class="tablefield h4">Name:</td>
							<td></td>
							<td class="h4 pl-4 font-weight-bold">${pensioner.name}</td>
						</tr>
						<tr>
							<td class="tablefield h4">DOB:</td>
							<td></td>

							<td class="h4 pl-4 font-weight-bold"><fmt:formatDate
									value="${pensioner.dateOfBirth}" pattern="dd-MM-yyyy" /></td>
						</tr>
						<tr>
							<td class="tablefield h4">PAN Number:</td>
							<td></td>
							<td class="h4 pl-4 font-weight-bold">${pensioner.panNumber}</td>
						</tr>
						<tr>
							<td class="tablefield h4">Pension Type:</td>
							<td></td>
							<td class="h4 pl-4 font-weight-bold">${pensioner.typeOfPension}</td>
						</tr>
						<tr>
							<td class="tablefield h4">Pension Amount:</td>
							<td></td>
							<td class="h4 pl-4 font-weight-bold">Rs.
								${pensioner.pensionAmount}</td>
						</tr>
					</tbody>

				</table>
				<div class="text-center">
					<a class="tryagain" href="/processPensionForm" class="text-white"><button
							class="btn btn-success mt-4 btn-block btn-lg">Process
							Pension</button></a> <a class="tryagain" href="/pensionerDetailForm"
						class="text-white"><button
							class="btn btn-success mt-4 btn-block btn-lg">Calculate
							Again</button></a>
				</div>
			</div>
		</div>
	</div>
	<footer class="text-lg-start container-fluid mt-auto"> <!-- Copyright -->
	Copyright 2021 <!-- Copyright --> </footer>
</body>

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