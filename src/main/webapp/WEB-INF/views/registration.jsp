<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
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

				<div class="md-block" style="height: 750px">

					<div class="sub-md-block">
						<h1 class="order-header">Registration:</h1>

						<th:form class="registration-form" action="/registration" commandName="regForm" method="post">
							<div class="order-labels">
								<label class="reg-label" for="user_login">Login:<x>*</x></label> <br>
								<label class="reg-label"for="user_password">Password:<x>*</x></label> <br>
								<label class="reg-label" for="user_name">Name:<x>*</x></label> <br>
								<label class="reg-label" for="user_surname">Surname:<x>*</x></label> <br>
								<label class="reg-label" for="user_patronymic">Patronymic:</label> <br>
								<label class="reg-label" for="user_birthdate">Birthdate:</label> <br>
								<label class="reg-label" for="user_email">Email:<x>*</x></label> <br>
								<hr style="width: 700px">
								<label class="reg-label" for="user_role">Account type:<x>*</x></label> <br>
								<label for="special_password">Special password:</label> <br>
								<p style="color: red; font-size: 0.7em;">For account types other than client</p>
							</div>
							<div class="order-inputs">
								<div class="inputs-container">
									<th:input path="user_login" cssClass="order-address" cssStyle="position: absolute; top: 0" type="text" placeholder="enter your login"/>
									<th:errors path="user_login" cssClass="error"/>
								</div>
								<div class="inputs-container">
									<th:input path="user_password" cssClass="order-address" cssStyle="position: absolute; top: 0" type="password" placeholder="enter your password"/>
									<th:errors path="user_password" cssClass="error"/>
								</div>
								<div class="inputs-container">
									<th:input path="user_name" cssClass="order-address" cssStyle="position: absolute; top: 0" type="text" placeholder="enter your name"/>
									<th:errors path="user_name" cssClass="error"/>
								</div>
								<div class="inputs-container">
									<th:input path="user_surname" cssClass="order-address" cssStyle="position: absolute; top: 0" type="text" placeholder="enter your surname"/>
									<th:errors path="user_surname" cssClass="error"/>
								</div>
								<div class="inputs-container">
									<th:input path="user_patronymic" cssClass="order-address" cssStyle="position: absolute; top: 0" type="text" placeholder="enter your middle name"/>
									<th:errors path="user_patronymic" cssClass="error"/>
								</div>
								<div class="inputs-container">
									<th:input path="user_birthdate" cssClass="order-address" cssStyle="position: absolute; top: 0" type="date" placeholder="date of birth"/>
									<th:errors path="user_birthdate" cssStyle="width: 100px; height: 15px; overflow: hidden;" cssClass="error"/>
								</div>
								<div class="inputs-container">
									<th:input path="user_email" cssClass="order-address" cssStyle="position: absolute; top: 0" type="email" placeholder="your email"/>
									<th:errors path="user_email" cssClass="error"/>
								</div>
								<div style="margin-top: 43px; margin-bottom: 18px; color: #f18500; font-weight: bold">
									<th:radiobutton path="user_role" checked="checked" value="ROLE_CLIENT"/>Client
									<th:radiobutton path="user_role" value="ROLE_DRIVER"/>Driver
									<th:radiobutton path="user_role" value="ROLE_ADMIN"/>Administrator<br>
								</div>
								<div class="inputs-container">
									<th:input path="special_password" cssClass="order-address" cssStyle="position: absolute; top: 0" type="password" placeholder="enter special password"/>
									<th:errors path="special_password" cssClass="error"/>
								</div>

							</div>
							<input class="new-order-button" style="margin-top: 50px" type="submit" name="reg_button" value="Submit" formmethod="post">
						</th:form>

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