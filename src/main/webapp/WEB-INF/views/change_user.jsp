<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Edit User</title>
</head>
<body>
<div style="text-align: center;">
    <form action="/admin/edit/user" method="post">
        <input type="hidden" name="id" value="<%=request.getAttribute("id")%>"> <br>
        Login: <input type="text" name="login" value="<%=request.getAttribute("oldLogin")%>"> <br>
        Password: <input type="text" name="password" value="<%=request.getAttribute("oldPassword")%>"> <br>
            Email: <input type="text" name="email" value="<%=request.getAttribute("oldEmail")%>"> <br>
        Role <input type="radio" value="ROLE_ADMIN" name="role"> Admin<br>
        <input type="radio" value="ROLE_USER" name="role"> User<br>
        <input type="submit" value="Edit User"></form>
    </form>
</div>
</body>
</html>
