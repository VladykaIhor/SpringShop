<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<style>
    .container {
        height: 10vh;
        width: 50%;
        text-align: center;
        border-radius: 10px;
        margin: 0px auto;
        float: none;
    }

    .container form {
        padding: auto;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
</style>
<body>
<form class="container" action="/signout" method="post">
    <button class="btn btn-dark" type="submit">Logout</button>
</form>
<h1 class="container"> Here's the most recent product list : </h1>
<div class="container">
    <table class="table table-sm">
        <thead>
        <tr>
            <th class="container">Name</th>
            <th class="container">Description</th>
            <th class="container">Price</th>
        </tr>
        </thead>
        <c:forEach var="products" items="${products}">
        <tbody>
        <tr>
            <td align="center">${products.name}</td>
            <td align="center">${products.description}</td>
            <td align="center">${products.price}</td>
            <td align="center">
                <form action="/user/products/add_to_cart" method="post">
                    <input type="hidden" name="id" value="${products.id}">
                    <button class="list-group-item list-group-item-action" type="submit">To Cart</button>
                </form>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <form action="/user/orderConfirmation" method="post">
        <button class="btn btn-success" type="submit">Confirm Order</button>
    </form>
</div>
</body>
</html>
