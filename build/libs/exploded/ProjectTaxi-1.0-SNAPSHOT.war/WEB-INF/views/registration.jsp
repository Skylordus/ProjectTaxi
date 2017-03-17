<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Registration</title>
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

			</div>		
		</nav>
	</header>


	<div id="main-content" class="container">

		<section class="row">
			<div class="md-container col-xs-12">

				<div class="md-block" style="height: 650px">

					<div class="sub-md-block">
						<h1 class="order-header">Registration:</h1>
						<h3 style="color: darkred; font-weight: bold; visibility: <%=request.getAttribute("unfilled")%>">Please fill out all required fields marked with *</h3>
						<form class="registration-form" action="/registration" method="post">
							<div class="order-labels">
								<label for="user_login">Login:<x>*</x></label> <br>
								<label for="user_password">Password:<x>*</x></label> <br>
								<label for="user_name">Name:<x>*</x></label> <br>
								<label for="user_surname">Surname:<x>*</x></label> <br>
								<label for="user_patronymic">Patronymic:</label> <br>
								<label for="user_birthdate">Birthdate:</label> <br>
								<label for="user_email">Email:<x>*</x></label> <br>
								<hr style="width: 700px">
								<label for="user_role">Account type:<x>*</x></label> <br>
								<label for="account_password">Special password:</label> <br>
								<p style="color: red; font-size: 0.7em;">For account types other than client</p>
							</div>
							<div class="order-inputs">
								<input class="order-address" style="margin-bottom: 20px; margin-top: 5px" type="text" name="user_login" id="user_login" placeholder="enter your login"> <br>
								<input class="order-address" style="margin-bottom: 20px" type="password" name="user_password" id="user_password" placeholder="enter your password"> <br>
								<input class="order-address" style="margin-bottom: 20px" type="text" name="user_name" id="user_name" placeholder="enter your name"> <br>
								<input class="order-address" style="margin-bottom: 20px" type="text" name="user_surname" id="user_surname" placeholder="enter your surname"> <br>
								<input class="order-address" style="margin-bottom: 20px" type="text" name="user_patronymic" id="user_patronymic" placeholder="enter your middle name"> <br>
								<input class="order-address" style="margin-bottom: 20px" type="date" name="user_birthdate" id="user_birthdate" placeholder="enter birthdate, format yyyy-mm-dd"> <br>
								<input class="order-address" style="margin-bottom: 20px" type="email" name="user_email" id="user_email" placeholder="enter your email address"> <br>
								<div style="margin-top: 43px; margin-bottom: 18px; color: #f18500; font-weight: bold">
									<input type="radio" name="user_role" id="user_role" checked="checked" value="client">Client
									<input type="radio" name="user_role" value="driver">Driver
									<input type="radio" name="user_role" value="admin">Administrator<br>
								</div>
								<input class="order-address" type="password" name="account_password" id="account_password" placeholder="enter password given to you by administrator">
							</div>
							<input class="new-order-button" type="submit" name="reg_button" value="Submit" formmethod="post">
						</form>

					</div>
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