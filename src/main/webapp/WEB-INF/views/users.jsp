<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
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
<div class="container">
    <h2> List of all users</h2>

    <a href="register"> Add new user</a> <br>
    <a href="products">List of all items</a> <br>


    <form action="/signout" method="post">
        <button type="submit">Logout</button>
    </form>
    <tbody>
    <c:forEach var="product" items="${allProductsInCart}">
        <tr>${product.id} </tr>
    </c:forEach>
    </tbody>
</div>

<div class="container">
    <table border="1">
        <thead>
        <th>Id</th>
        <th>Email</th>
        <th>Login</th>
        <th>Password</th>
        <th>Role</th>
        </thead>
        <tbody>
        <c:forEach var="user" items="${allUsers}">
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>
                    <button class="btn btn-success">
                        <a href="/admin/edit/user?id=${user.id}" name="edit"> Edit </a>
                    </button>
                </td>
                <td>
                    <form action="/admin/remove/user" method="post">
                        <input class="btn btn-success" type="hidden" name="id" value="${user.id}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
