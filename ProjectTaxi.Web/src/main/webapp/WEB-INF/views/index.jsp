<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Project Taxi</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
	<link href="https://fonts.googleapis.com/css?family=Oxygen:300,400,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Lora" rel="stylesheet">

</head>

<body>

<script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/script.js"/>"></script>
	<%
		String login = "hidden";
		String logout = "visible";
		String loginIncorrect = "hidden";
		String roleAction = "/failed";

		if (request.isUserInRole("ROLE_ADMIN")) {roleAction="/admin_account";}
		else if (request.isUserInRole("ROLE_CLIENT")) {roleAction="/client_account";}
		else if (request.isUserInRole("ROLE_DRIVER")) {roleAction="/driver_account";}
		else {login="visible"; logout="hidden";}

		if (request.getParameter("error")!=null) {
		    loginIncorrect = "visible";
		}
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
							<h1>Welcome to Project Taxi</h1>
							<br>
							<a class="phone" href="tel:8-708-519-41-49">
								<span class="glyphicon glyphicon-earphone"></span>
								8-707-519-41-49
							</a>
						</a>

					</div>

				</div>

				<div class="navbar-right">
					<p class="p-login-incorrect" style="visibility: <%=loginIncorrect%>">Either login or password incorrect</p>
					<div class="login" style="visibility: <%=login%>">
						<form action="/login" method="post">
							<div class="login-labels">
								<label for="userlogin">Login:</label> <br>
								<label for="userpassword">Password:</label>
							</div>
							<div class="login-fields">
								<input type="text" name="userlogin" id="userlogin" placeholder="login"> <br>
								<input type="password" name="userpassword" id="userpassword" placeholder="password">
							</div>
							<input class="login-button" type="submit" value="Login" formmethod="post">
						</form>
						<form action="/registration" method="get">
							<input class="reg-button" type="submit" value="Register" formmethod="get">
						</form>
					</div>

					<div class="profile-logout" style="visibility: <%=logout%>">
						<form action="<%=roleAction%>" method="get">
							<input class="reg-button" style="right: 75px" type="submit" value="Profile" formmethod="get">
						</form>
						<form action="/logout" method="get">
							<input class="reg-button" style="right: 0" type="submit" value="Log out" formmethod="get">
						</form>
					</div>

				</div>

			</div>
		</nav>
	</header>


	<div id="main-content" class="container">
		<c:if test="${regsuccess!=null}">
			<h1 style="color: #fff; font-weight: bold; text-align: center; text-shadow: 0 0 5px black">Registration successful!</h1>
		</c:if>
		<div class="jumbotron" style="padding: 0">
			<div id="jumbotron-img"></div>
		</div>
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