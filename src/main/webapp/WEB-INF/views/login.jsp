<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<style>
    .container {
        height: 35vh;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .container form {
        padding: 20px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .container button {
        margin-top: 10px;
    }

    .container label {
        margin: 5px 0;
    }
</style>
<body>
<div class="jumbotron">
    <h1 class="display-4">Hello, world!</h1>
    <p class="lead">This is a simple online shop</p>
</div>
<div class="container">
    <form action="<spring:url value="/signin"/>" method="post">
        <label for="login"> Login </label>
        <input class="form-control" type="text" name="login" id="login" placeholder="Enter login">
        <label for="password">Password</label>
        <input class="form-control" type="password" id="password" name="password" placeholder="Enter password">
        <button type="submit" class="btn btn-success">Sign In</button>
    </form>
</div>
</body>
</html>
