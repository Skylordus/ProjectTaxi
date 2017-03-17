<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.yberdaliyev.models.pojos.User" %>
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

<body style="background-size: cover">

	<script src="<c:url value="/resources/js/jquery-3.1.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
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
					<p style="color:#f18500;font-weight: bold">Administrator</p>
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
                <div class="admin-md-block">
                    <table class="table">
                        <thead>
                        <tr>
                            <th id="orders-menu" class="table-buttons">Orders</th>
                            <th id="clients-menu" class="table-buttons">Clients</th>
                            <th id="drivers-menu" class="table-buttons">Drivers</th>
                            <th id="admins-menu" class="table-buttons">Admins</th>
                            <th id="cars-menu" class="table-buttons">Cars</th>
                        </tr>
                        </thead>
                    </table>

                    <div id="table-orders" class="sub-md-block">

                        <table class="table table-inverse">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>From</th>
                                <th>To</th>
                                <th>Price/km</th>
                                <th>Client_ID</th>
                                <th>Driver_ID</th>
                                <th>Status</th>
                                <th>Pickup time</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${orders}" var="order">
                            <tr>
                                <th scope="row">${order.getId()}</th>
                                <form id="order_form${order.getId()}" action="/admin_account" method="post"></form>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getFrom()}</div>
                                        <input class="form${order.getId()} order_lid${order.getId()}" type="text" name="from" placeholder="${order.getFrom()}">
                                    </td>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getTo()}</div>
                                        <input class="form${order.getId()} order_lid${order.getId()}" type="text" name="to" placeholder="${order.getTo()}">
                                    </td>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getPrice_per_km()}</div>
                                        <input  style="width: 50px" class="form${order.getId()} order_lid${order.getId()}" type="number" name="price" placeholder="${order.getPrice_per_km()}">
                                    </td>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getClient()}</div>
                                        <input  style="width: 50px" class="form${order.getId()} order_lid${order.getId()}" type="number" name="client_id" placeholder="${order.getClient()}">
                                    </td>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getDriver()}</div>
                                        <input  style="width: 50px" class="form${order.getId()} order_lid${order.getId()}" type="number" name="driver_id" placeholder="${order.getDriver()}">
                                    </td>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getStatus()}</div>
                                        <input  style="width: 50px" class="form${order.getId()} order_lid${order.getId()}" type="number" name="status" placeholder="${order.getStatus()}">
                                    </td>
                                    <td>
                                        <div class="order_fid${order.getId()}">${order.getPickup_time().toString()}</div>
                                        <input style="width: 100px" class="form${order.getId()} order_lid${order.getId()}" type="time" name="pickup_time" placeholder="${order.getPickup_time()}">
                                    </td>
                                    <input type="hidden" class="form${order.getId()} order_lid${order.getId()}" name="type" value="edit">
                                    <input type="hidden" class="form${order.getId()} order_lid${order.getId()}" name="id" value="${order.getId()}">
                                <td>
                                    <div class="order_lid${order.getId()}">
                                        <button class="table-button" onClick="submitForm('order_form${order.getId()}','form${order.getId()}')">Submit</button>
                                    </div>
                                    <div class="order_fid${order.getId()}">
                                        <button class="table-button" id="edit-button" onClick="createEditForm('order_fid${order.getId()}','order_lid${order.getId()}')">
                                            Edit
                                        </button>
                                        <form style="display: inline" action="/admin_account" method="post">
                                            <input type="hidden" name="type" value="delete">
                                            <input type="hidden" name="id" value="${order.getId()}">
                                            <input class="table-button" type="submit" value="delete" formmethod="post">
                                        </form>
                                    </div>
                                </td>

                                <style type="text/css">
                                    .order_fid${order.getId()} {
                                        display: block;
                                    }
                                    .order_lid${order.getId()} {
                                        display: none;
                                        color: black;
                                    }
                                </style>
                            </tr>
                            </c:forEach>
                            <tr>
                                <th scope="row">
                                    <button id="add-button" class="table-button" onClick="createAddForm('add-form','add-button')">
                                        Add an order
                                    </button>
                                </th>
                                <form class="add-form" action="/admin_account" method="post">

                                    <td>
                                        <input class="add-form" type="text" name="from" placeholder="from">
                                    </td>
                                    <td>
                                        <input class="add-form" type="text" name="to" placeholder="to">
                                    </td>
                                    <td>
                                        <input class="add-form" style="width: 50px" type="number" name="price" placeholder="price">
                                    </td>
                                    <td>
                                        <input class="add-form" style="width: 50px" type="number" name="client_id" placeholder="client_id">
                                    </td>
                                    <td>
                                        <input class="add-form" style="width: 50px" type="number" name="driver_id" placeholder="driver_id">
                                    </td>
                                    <td>
                                        <input class="add-form" style="width: 50px" type="number" name="status" placeholder="status">
                                    </td>
                                    <td>
                                        <input class="add-form" style="width: 100px" type="time" name="pickup_time" placeholder="time">
                                    </td>
                                    <input type="hidden" name="type" value="add">
                                    <td>
                                        <input class="add-form table-button" style="color: white" type="submit" value="Submit" formmethod="post">
                                    </td>
                                </form>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div  id="table-clients" style="overflow-x: auto" class="sub-md-block">
                        <table class="table table-inverse">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Middle name</th>
                                <th>Date registered</th>
                                <th>Orders amount</th>
                                <th>Date of birth</th>
                                <th>Login</th>
                                <th>Email</th>
                                <th>Order ID</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${clients}" var="client">
                                <tr>
                                    <th scope="row">${client.getId()}</th>
                                    <form id="client_form${client.getId()}" action="/admin_account/client" method="post"></form>
                                    <td>
                                        <div class="client_fid${client.getId()}">${client.getFirstname()}</div>
                                        <input class="cform${client.getId()} client_lid${client.getId()}" type="text" name="firstname" placeholder="${client.getFirstname()}">
                                    </td>
                                    <td>
                                        <div class="client_fid${client.getId()}">${client.getLastname()}</div>
                                        <input class="cform${client.getId()} client_lid${client.getId()}" type="text" name="lastname" placeholder="${client.getLastname()}">
                                    </td>
                                    <td>
                                        <div class="client_fid${client.getId()}">${client.getPatronymic()}</div>
                                        <input class="cform${client.getId()} client_lid${client.getId()}" type="text" name="patronymic" placeholder="${client.getPatronymic()}">
                                    </td>
                                    <td>
                                        <div class="client_fid${client.getId()}">${client.getDate_registered()}</div>
                                        <input class="cform${client.getId()} client_lid${client.getId()}" type="date" name="date_registered" placeholder="${client.getDate_registered()}">
                                    </td>
                                    <td>
                                        <div class="client_fid${client.getId()}">${client.getOrders_amount()}</div>
                                        <input style="width: 50px" class="cform${client.getId()} client_lid${client.getId()}" type="number" name="orders_amount" placeholder="${client.getOrders_amount()}">
                                    </td>
                                    <td>
                                        <div class="client_fid${client.getId()}">${client.getBirthdate()}</div>
                                        <input class="cform${client.getId()} client_lid${client.getId()}" type="date" name="birthdate" placeholder="${client.getBirthdate()}">
                                    </td>
                                    <td>
                                        <div class="client_fid${client.getId()}">${client.getLogin()}</div>
                                        <input class="cform${client.getId()} client_lid${client.getId()}" type="text" name="login" placeholder="${client.getLogin()}">
                                    </td>
                                    <td>
                                        <div class="client_fid${client.getId()}">${client.getEmail()}</div>
                                        <input class="cform${client.getId()} client_lid${client.getId()}" type="email" name="email" placeholder="${client.getEmail()}">
                                    </td>
                                    <td>
                                        <div class="client_fid${client.getId()}">${client.getOrder()}</div>
                                        <input style="width: 50px" class="cform${client.getId()} client_lid${client.getId()}" type="number" name="order" placeholder="${client.getOrder()}">
                                    </td>

                                    <input type="hidden" class="cform${client.getId()} client_lid${client.getId()}" name="type" value="edit">
                                    <input type="hidden" class="cform${client.getId()} client_lid${client.getId()}" name="id" value="${client.getId()}">
                                    <td>
                                        <div class="client_lid${client.getId()}">
                                            <button class="table-button" onClick="submitForm('client_form${client.getId()}','cform${client.getId()}')">Submit</button>
                                        </div>
                                        <div class="client_fid${client.getId()}">
                                            <button class="table-button" id="client-edit-button" onClick="createEditForm('client_fid${client.getId()}','client_lid${client.getId()}')">
                                                Edit
                                            </button>
                                            <form style="display: inline" action="/admin_account/client" method="post">
                                                <input type="hidden" name="type" value="delete">
                                                <input type="hidden" name="id" value="${client.getId()}">
                                                <input class="table-button" type="submit" value="delete" formmethod="post">
                                            </form>
                                        </div>
                                    </td>

                                    <style type="text/css">
                                        .client_fid${client.getId()} {
                                            display: block;
                                        }
                                        .client_lid${client.getId()} {
                                            display: none;
                                            color: black;
                                        }
                                    </style>
                                </tr>
                            </c:forEach>
                            <tr>
                                <th scope="row">
                                    <button id="client-add-button" class="table-button" onClick="createAddForm('client-add-form','client-add-button')">
                                        Add a client
                                    </button>
                                </th>
                                <form class="client-add-form" action="/admin_account/client" method="post">
                                    <td>
                                        <input class="client-add-form" type="text" name="firstname" placeholder="first name">
                                    </td>
                                    <td>
                                        <input class="client-add-form" type="text" name="lastname" placeholder="last name">
                                    </td>
                                    <td>
                                        <input class="client-add-form" type="text" name="patronymic" placeholder="middle name">
                                    </td>
                                    <td>
                                        <input class="client-add-form" type="date" name="date_registered" placeholder="date registered">
                                    </td>
                                    <td>
                                        <input style="width: 50px" class="client-add-form" type="number" name="orders_amount" placeholder="orders">
                                    </td>
                                    <td>
                                        <input class="client-add-form" type="date" name="birthdate" placeholder="born">
                                    </td>
                                    <td>
                                        <input class="client-add-form" type="text" name="login" placeholder="login">
                                        <input class="client-add-form" type="password" name="pwd" placeholder="password">
                                    </td>
                                    <td>
                                        <input class="client-add-form" type="email" name="email" placeholder="email">
                                    </td>
                                    <td>
                                        <input style="width: 50px" class="client-add-form" type="number" name="order" placeholder="order">
                                    </td>
                                    <input type="hidden" name="type" value="add">
                                    <td>
                                        <input class="client-add-form table-button" style="color: white" type="submit" value="Submit" formmethod="post">
                                    </td>
                                </form>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div  id="table-drivers" style="overflow-x: auto" class="sub-md-block">
                        <table class="table table-inverse">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Middle name</th>
                                <th>Date of birth</th>
                                <th>Years of experience</th>
                                <th>Car ID</th>
                                <th>Login</th>
                                <th>Email</th>
                                <th>Order ID</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${drivers}" var="driver">
                                <tr>
                                    <th scope="row">${driver.getId()}</th>
                                    <form id="driver_form${driver.getId()}" action="/admin_account/driver" method="post"></form>
                                    <td>
                                        <div class="driver_fid${driver.getId()}">${driver.getFirstname()}</div>
                                        <input class="dform${driver.getId()} driver_lid${driver.getId()}" type="text" name="firstname" placeholder="${driver.getFirstname()}">
                                    </td>
                                    <td>
                                        <div class="driver_fid${driver.getId()}">${driver.getLastname()}</div>
                                        <input class="dform${driver.getId()} driver_lid${driver.getId()}" type="text" name="lastname" placeholder="${driver.getLastname()}">
                                    </td>
                                    <td>
                                        <div class="driver_fid${driver.getId()}">${driver.getPatronymic()}</div>
                                        <input class="dform${driver.getId()} driver_lid${driver.getId()}" type="text" name="patronymic" placeholder="${driver.getPatronymic()}">
                                    </td>
                                    <td>
                                        <div class="driver_fid${driver.getId()}">${driver.getBirthdate()}</div>
                                        <input class="dform${driver.getId()} driver_lid${driver.getId()}" type="date" name="birthdate" placeholder="${driver.getBirthdate()}">
                                    </td>
                                    <td>
                                        <div class="driver_fid${driver.getId()}">${driver.getExperience_years()}</div>
                                        <input style="width: 50px" class="dform${driver.getId()} driver_lid${driver.getId()}" type="number" name="experience_years" placeholder="${driver.getExperience_years()}">
                                    </td>
                                    <td>
                                        <div class="driver_fid${driver.getId()}">${driver.getCar()}</div>
                                        <input style="width: 50px" class="dform${driver.getId()} driver_lid${driver.getId()}" type="number" name="car" placeholder="${driver.getCar()}">
                                    </td>
                                    <td>
                                        <div class="driver_fid${driver.getId()}">${driver.getLogin()}</div>
                                        <input class="dform${driver.getId()} driver_lid${driver.getId()}" type="text" name="login" placeholder="${driver.getLogin()}">
                                    </td>
                                    <td>
                                        <div class="driver_fid${driver.getId()}">${driver.getEmail()}</div>
                                        <input class="dform${driver.getId()} driver_lid${driver.getId()}" type="email" name="email" placeholder="${driver.getEmail()}">
                                    </td>
                                    <td>
                                        <div class="driver_fid${driver.getId()}">${driver.getOrder()}</div>
                                        <input style="width: 50px" class="dform${driver.getId()} driver_lid${driver.getId()}" type="number" name="order" placeholder="${driver.getOrder()}">
                                    </td>
                                    <input type="hidden" class="dform${driver.getId()} driver_lid${driver.getId()}" name="type" value="edit">
                                    <input type="hidden" class="dform${driver.getId()} driver_lid${driver.getId()}" name="id" value="${driver.getId()}">
                                    <td>
                                        <div class="driver_lid${driver.getId()}">
                                            <button class="table-button" onClick="submitForm('driver_form${driver.getId()}','dform${driver.getId()}')">Submit</button>
                                        </div>
                                        <div class="driver_fid${driver.getId()}">
                                            <button class="table-button" id="driver-edit-button" onClick="createEditForm('driver_fid${driver.getId()}','driver_lid${driver.getId()}')">
                                                Edit
                                            </button>
                                            <form style="display: inline" action="/admin_account/driver" method="post">
                                                <input type="hidden" name="type" value="delete">
                                                <input type="hidden" name="id" value="${driver.getId()}">
                                                <input class="table-button" type="submit" value="delete" formmethod="post">
                                            </form>
                                        </div>
                                    </td>

                                    <style type="text/css">
                                        .driver_fid${driver.getId()} {
                                            display: block;
                                        }
                                        .driver_lid${driver.getId()} {
                                            display: none;
                                            color: black;
                                        }
                                    </style>
                                </tr>
                            </c:forEach>
                            <tr>
                                <th scope="row">
                                    <button id="driver-add-button" class="table-button" onClick="createAddForm('driver-add-form','driver-add-button')">
                                        Add a driver
                                    </button>
                                </th>
                                <form class="driver-add-form" action="/admin_account/driver" method="post">
                                    <td>
                                        <input class="driver-add-form" type="text" name="firstname" placeholder="first name">
                                    </td>
                                    <td>
                                        <input class="driver-add-form" type="text" name="lastname" placeholder="last name">
                                    </td>
                                    <td>
                                        <input class="driver-add-form" type="text" name="patronymic" placeholder="middle name">
                                    </td>
                                    <td>
                                        <input class="driver-add-form" type="date" name="birthdate" placeholder="born">
                                    </td>
                                    <td>
                                        <input style="width: 50px" class="driver-add-form" type="number" name="experience_years" placeholder="exp_years">
                                    </td>
                                    <td>
                                        <input style="width: 50px" class="driver-add-form" type="number" name="car" placeholder="carID">
                                    </td>
                                    <td>
                                        <input class="driver-add-form" type="text" name="login" placeholder="login">
                                        <input class="driver-add-form" type="password" name="pwd" placeholder="password">
                                    </td>
                                    <td>
                                        <input class="driver-add-form" type="email" name="email" placeholder="email">
                                    </td>
                                    <td>
                                        <input style="width: 50px" class="driver-add-form" type="number" name="order" placeholder="ID">
                                    </td>
                                    <input type="hidden" name="type" value="add">
                                    <td>
                                        <input class="driver-add-form table-button" style="color: white" type="submit" value="Submit" formmethod="post">
                                    </td>
                                </form>
                            </tr>
                            </tbody>
                        </table>

                    </div>

                    <div  id="table-admins" style="overflow-x: auto" class="sub-md-block">
                        <table class="table table-inverse">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Middle name</th>
                                <th>Date of birth</th>
                                <th>Login</th>
                                <th>Email</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${admins}" var="admin">
                                <tr>
                                    <th scope="row">${admin.getId()}</th>
                                    <form id="admin_form${admin.getId()}" action="/admin_account/admin" method="post"></form>
                                    <td>
                                        <div class="admin_fid${admin.getId()}">${admin.getFirstname()}</div>
                                        <input class="aform${admin.getId()} admin_lid${admin.getId()}" type="text" name="firstname" placeholder="${admin.getFirstname()}">
                                    </td>
                                    <td>
                                        <div class="admin_fid${admin.getId()}">${admin.getLastname()}</div>
                                        <input class="aform${admin.getId()} admin_lid${admin.getId()}" type="text" name="lastname" placeholder="${admin.getLastname()}">
                                    </td>
                                    <td>
                                        <div class="admin_fid${admin.getId()}">${admin.getPatronymic()}</div>
                                        <input class="aform${admin.getId()} admin_lid${admin.getId()}" type="text" name="patronymic" placeholder="${admin.getPatronymic()}">
                                    </td>
                                    <td>
                                        <div class="admin_fid${admin.getId()}">${admin.getBirthdate()}</div>
                                        <input class="aform${admin.getId()} admin_lid${admin.getId()}" type="date" name="birthdate" placeholder="${admin.getBirthdate()}">
                                    </td>
                                    <td>
                                        <div class="admin_fid${admin.getId()}">${admin.getLogin()}</div>
                                        <input class="aform${admin.getId()} admin_lid${admin.getId()}" type="text" name="login" placeholder="${admin.getLogin()}">
                                    </td>
                                    <td>
                                        <div class="admin_fid${admin.getId()}">${admin.getEmail()}</div>
                                        <input class="aform${admin.getId()} admin_lid${admin.getId()}" type="email" name="email" placeholder="${admin.getEmail()}">
                                    </td>

                                    <input type="hidden" class="aform${admin.getId()} admin_lid${admin.getId()}" name="type" value="edit">
                                    <input type="hidden" class="aform${admin.getId()} admin_lid${admin.getId()}" name="id" value="${admin.getId()}">
                                    <td>
                                        <div class="admin_lid${admin.getId()}">
                                            <button class="table-button" onClick="submitForm('admin_form${admin.getId()}','aform${admin.getId()}')">Submit</button>
                                        </div>
                                        <div class="admin_fid${admin.getId()}">
                                            <button class="table-button" id="admin-edit-button" onClick="createEditForm('admin_fid${admin.getId()}','admin_lid${admin.getId()}')">
                                                Edit
                                            </button>
                                            <form style="display: inline" action="/admin_account/admin" method="post">
                                                <input type="hidden" name="type" value="delete">
                                                <input type="hidden" name="id" value="${admin.getId()}">
                                                <input class="table-button" type="submit" value="delete" formmethod="post">
                                            </form>
                                        </div>
                                    </td>

                                    <style type="text/css">
                                        .admin_fid${admin.getId()} {
                                            display: block;
                                        }
                                        .admin_lid${admin.getId()} {
                                            display: none;
                                            color: black;
                                        }
                                    </style>
                                </tr>
                            </c:forEach>
                            <tr>
                                <th scope="row">
                                    <button id="admin-add-button" class="table-button" onClick="createAddForm('admin-add-form','admin-add-button')">
                                        Add a admin
                                    </button>
                                </th>
                                <form class="admin-add-form" action="/admin_account/admin" method="post">
                                    <td>
                                        <input class="admin-add-form" type="text" name="firstname" placeholder="first name">
                                    </td>
                                    <td>
                                        <input class="admin-add-form" type="text" name="lastname" placeholder="last name">
                                    </td>
                                    <td>
                                        <input class="admin-add-form" type="text" name="patronymic" placeholder="middle name">
                                    </td>
                                    <td>
                                        <input class="admin-add-form" type="date" name="birthdate" placeholder="born">
                                    </td>
                                    <td>
                                        <input class="admin-add-form" type="text" name="login" placeholder="login">
                                        <input class="admin-add-form" type="password" name="pwd" placeholder="password">
                                    </td>
                                    <td>
                                        <input class="admin-add-form" type="email" name="email" placeholder="email">
                                    </td>
                                    <input type="hidden" name="type" value="add">
                                    <td>
                                        <input class="admin-add-form table-button" style="color: white" type="submit" value="Submit" formmethod="post">
                                    </td>
                                </form>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div  id="table-cars" class="sub-md-block">
                        <table class="table table-inverse">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Manufacturer</th>
                                <th>Model</th>
                                <th>Registration number</th>
                                <th>Color</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${cars}" var="car">
                                <tr>
                                    <th scope="row">${car.getId()}</th>
                                    <form id="car_form${car.getId()}" action="/admin_account/car" method="post"></form>
                                    <td>
                                        <div class="car_fid${car.getId()}">${car.getManufacturer()}</div>
                                        <input class="carform${car.getId()} car_lid${car.getId()}" type="text" name="manufacturer" placeholder="${car.getManufacturer()}">
                                    </td>
                                    <td>
                                        <div class="car_fid${car.getId()}">${car.getModel()}</div>
                                        <input class="carform${car.getId()} car_lid${car.getId()}" type="text" name="model" placeholder="${car.getModel()}">
                                    </td>
                                    <td>
                                        <div class="car_fid${car.getId()}">${car.getRegnum()}</div>
                                        <input class="carform${car.getId()} car_lid${car.getId()}" type="text" name="regnum" placeholder="${car.getRegnum()}">
                                    </td>
                                    <td>
                                        <div class="car_fid${car.getId()}">${car.getColor()}</div>
                                        <input class="carform${car.getId()} car_lid${car.getId()}" type="text" name="color" placeholder="${car.getColor()}">
                                    </td>

                                    <input type="hidden" class="carform${car.getId()} car_lid${car.getId()}" name="type" value="edit">
                                    <input type="hidden" class="carform${car.getId()} car_lid${car.getId()}" name="id" value="${car.getId()}">
                                    <td>
                                        <div class="car_lid${car.getId()}">
                                            <button class="table-button" onClick="submitForm('car_form${car.getId()}','carform${car.getId()}')">Submit</button>
                                        </div>
                                        <div class="car_fid${car.getId()}">
                                            <button class="table-button" id="car-edit-button" onClick="createEditForm('car_fid${car.getId()}','car_lid${car.getId()}')">
                                                Edit
                                            </button>
                                            <form style="display: inline" action="/admin_account/car" method="post">
                                                <input type="hidden" name="type" value="delete">
                                                <input type="hidden" name="id" value="${car.getId()}">
                                                <input class="table-button" type="submit" value="delete" formmethod="post">
                                            </form>
                                        </div>
                                    </td>

                                    <style type="text/css">
                                        .car_fid${car.getId()} {
                                            display: block;
                                        }
                                        .car_lid${car.getId()} {
                                            display: none;
                                            color: black;
                                        }
                                    </style>
                                </tr>
                            </c:forEach>
                            <tr>
                                <th scope="row">
                                    <button id="car-add-button" class="table-button" onClick="createAddForm('car-add-form','car-add-button')">
                                        Add a car
                                    </button>
                                </th>
                                <form class="car-add-form" action="/admin_account/car" method="post">
                                    <td>
                                        <input class="car-add-form" type="text" name="manufacturer" placeholder="manufacturer">
                                    </td>
                                    <td>
                                        <input class="car-add-form" type="text" name="model" placeholder="model">
                                    </td>
                                    <td>
                                        <input class="car-add-form" type="text" name="regnum" placeholder="A777XX 111RUS">
                                    </td>
                                    <td>
                                        <input class="car-add-form" type="text" name="color" placeholder="color">
                                    </td>
                                    <input type="hidden" name="type" value="add">
                                    <td>
                                        <input class="car-add-form table-button" style="color: white" type="submit" value="Submit" formmethod="post">
                                    </td>
                                </form>
                            </tr>
                            </tbody>
                        </table>
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


    <script src="<c:url value="/resources/js/script.js"/>"></script>
    <script>show(${showtable})</script>
</body>
</html>