<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.yberdaliyev.models.pojos.User" %>
<%@ page import="com.yberdaliyev.models.pojos.Order" %>
<%@ page import="com.yberdaliyev.services.OrderService" %>
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Project Taxi</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/welcome.css" />">
	<link href="https://fonts.googleapis.com/css?family=Oxygen:300,400,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Lora" rel="stylesheet">

</head>

<body>

<script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/script.js"/>"></script>
	<%
		User user = (User) session.getAttribute("user_object");
	%>

	<header>
		<nav id="header-nav" class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a href="/" class="pull-left visible-md visible-lg">
						<div id="logo-img" alt="Logo"></div>
					</a>
					<div class="navbar-brand">
						<a href="/">
							<h1>Project Taxi</h1>
							<br>
							<a class="phone" href="tel:8-708-519-41-49">
								<span class="glyphicon glyphicon-earphone"></span>
								8-707-519-41-49
							</a>
						</a>
					</div>
				</div>

				<div style="float: right; margin-top: 20px">
					<p><%=user.getFirstname()+" "+user.getLastname()%></p>
					<p style="color:#f18500;font-weight: bold">Driver</p>
					<form action="/logout" method="get">
						<input class="reg-button" style="position: inherit" type="submit" value="Log out" formmethod="get">
					</form>
				</div>
			</div>		
		</nav>
	</header>


	<div id="main-content" class="container">

		<section class="row">
			<div class="md-container col-xs-12">

				<div class="md-block">
					<c:if test="${isPicked == false}">
					<div id="pick-order-block" class="sub-md-block">

						<h1 class="order-header">Orders available for you:</h1>

						<div id="table-orders">

							<table class="table table-inverse">
								<thead>
								<tr>
									<th>ID</th>
									<th>From</th>
									<th>To</th>
									<th>Price/km</th>
									<th>Client</th>
									<th>Pickup time</th>
									<th></th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${orders}" var="order">
									<tr>
										<th scope="row">${order.getId()}</th>
										<td>
											<div>${order.getFrom()}</div>
										</td>
										<td>
											<div>${order.getTo()}</div>
										</td>
										<td>
											<div>${order.getPrice_per_km()}</div>

										</td>
										<td>
											<div>${order.getClient()}</div>
										</td>
										<td>
											<div>${order.getPickup_time().toString()}</div>
										</td>
										<td>
											<form action="/driver_account" method="post">
												<input type="hidden" name="order_id" value="${order.getId()}">
												<input type="hidden" name="type" value="pick">
												<input class="table-button" type="submit" value="Pick" formmethod="post">
											</form>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>

					</div>
					</c:if>
					<c:if test="${isPicked == true}">
					<div id="drivers-order-block" class="sub-md-block">

						<h1 class="order-header">Your order to fulfill:</h1>
						<% Order o = (Order) request.getAttribute("your_order"); %>
						<div>
							<span class="order-field">ID: </span>
							<span class="order-value"><%=o.getId()%></span>
						</div>
						<div>
							<span class="order-field">From: </span>
							<span class="order-value"><%=o.getFrom()%></span>
						</div>
						<div>
							<span class="order-field">To: </span>
							<span class="order-value"><%=o.getTo()%></span>
						</div>
						<div>
							<span class="order-field">Price: </span>
							<span class="order-value"><%=o.getPrice_per_km()%></span>
						</div>
						<div>
							<span class="order-field">Client: </span>
							<span class="order-value"><%=request.getAttribute("client")%></span>
						</div>
						<div>
							<span class="order-field">Your car: </span>
							<span class="order-value"><%=request.getAttribute("car")%></span>
						</div>
						<div>
							<span class="order-field">Status: </span>
							<span class="order-value"><%=request.getAttribute("status")%></span>
						</div>
						<div>
							<span class="order-field" style="color:red">Time to pickup: </span>
							<span class="order-value"><%=o.getPickup_time()%></span>
						</div>
						<%  String execution = "none";
							String finished = "none";
							if (o.getStatus()== OrderService.STATUS_NOT_STARTED) {
						   		 execution="block";
						    }
							if (o.getStatus()== OrderService.STATUS_FULFILLING) {
								finished="block";
							}
						%>
						<form style="display: <%=execution%>" action="/driver_account" method="post">
							<input type="hidden" name="type" value="on_execution">
							<input type="hidden" name="order_id" value="<%=o.getId()%>">
							<input style="right: 30px" class="new-order-button" type="submit" value="Start execution" formmethod="post">
						</form>
						<form style="display: <%=finished%>" id="mark-finished-button" action="/driver_account" method="post">
							<input type="hidden" name="type" value="finished">
							<input type="hidden" name="order_id" value="<%=o.getId()%>">
							<input type="hidden" name="client_id" value="<%=o.getClient()%>">
							<input style="right: 30px" class="new-order-button" type="submit" value="Mark finished" formmethod="post">
						</form>
					</div>
					</c:if>
				</div>


			</div>
		</section>
	</div> <!-- End of #main-content -->
	
	<footer class="panel-footer">
		<div class="container">
			<div class="row">
				<section id="hours" class="col-sm-4">
					<span>Hours:</span><br>
					Sunday-Thursday: 11:15am - 10:00pm <br>
					Friday: 11:15am - 2:30pm <br>
					Saturday Closed
					<hr class="visible-xs">										
				</section>
				<section id="address" class="col-sm-4">
					<span>Address:</span><br>
					7105 Reisterstown Road <br>
					Baltimore, MD 21215
					<p>*Delivery area within 3-4 miles, with  minimum order of $20 plus $3 charge for all deliveries.</p>
					<hr class="visible-xs">										
				</section>
				<section id="testimonials" class="col-sm-4">
					<p>"The best Chinese restaurant I've been to! And that's saying a lot, since I've been to many!"</p>
					<p>"Amazing food! Great service! Couldn't ask for more! I'll be back again and again!"</p>					 										
				</section>				
			</div>
			<div class="text-center">&copy; Copyright Yerlan's Bistro</div>
		</div>
	</footer>



</body>
</html>