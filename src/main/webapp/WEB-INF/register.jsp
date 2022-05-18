<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration Form</title>
      <link rel="stylesheet" href="https://bootswatch.com/5/minty/bootstrap.min.css">
</head>
<body>
<h1>Register Form</h1>

<form  action="Controller" method="GET">
<input type="hidden" name="actionType" value="register" />
    <table style="with: 50%">
        <tr>
            <td>Login</td>
            <td><input type="text" name="login" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="email" name="email" /></td>
        </tr>
        </table>
    <c:if test="${not empty errorMsgRegister}">
        <p style="color: red">${errorMsgRegister}</p>
    </c:if>
    <input type="submit" value="Submit" /></form>
</body>
</html>