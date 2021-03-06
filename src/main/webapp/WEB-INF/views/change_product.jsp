<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Product</title>
</head>
<body>
<div style="text-align: center; background-color: beige">
    <form action="/admin/products/edit" method="post" th:th: object="product">
        <input type="hidden" name="id" value="<%=request.getAttribute("id")%>"> <br>
        Название <input type="text" name="name" value="<%= (request.getAttribute("oldName") == null) ? ""
                      : request.getAttribute("oldName")%>"> <br>
        Описание <input type="text" name="description" value="<%= (request.getAttribute("oldDescription") == null) ? ""
                      : request.getAttribute("oldDescription")%>"> <br>
        Цена <input type="number" step="0.01" min="0" placeholder="0,00" name="price"
                    value="<%= (request.getAttribute("oldPrice") == null) ? 0.00
                      : request.getAttribute("oldPrice")%>"> <br>
        <input type="submit" value="Edit product"></form>
    </form>
</div>
</body>
</html>
