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
<h1 align="center"> Here's the most recent product list : </h1>
<div class="container">

    <table class="table table-sm">
        <thead>
        <tr>
            <th class="container">Name</th>
            <th class="container">Description</th>
            <th class="container">Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="products" items="${products}">
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
    <table class="table table-sm">
        <h1>Products in your shopping cart :</h1>>
        <thead>
        <tr>
            <th class="container">Name</th>
            <th class="container">Description</th>
            <th class="container">Price</th>
        </tr>
        </thead>

        <form>
            <c:forEach var="cart" items="${cart}">
                <tr>
                <td align="center">${cart.name}</td>
                <td align="center">${cart.description}</td>
                <td align="center">${cart.price}</td>
                   <td>
                    <form action="/user/products/remove_from_cart" method="post">
                        <input type="hidden" name="id" value="${cart}">
                        <button class="list-group-item list-group-item-action" type="submit">Remove it</button>
                    </form>
                   </td>
            </c:forEach>
        </form>
    </table>
    <form action="/user/orderConfirmation" method="post">
        <button class="btn btn-success" type="submit">Confirm Order</button>
    </form>
</div>
</body>
</html>
