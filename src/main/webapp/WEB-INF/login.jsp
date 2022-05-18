<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://bootswatch.com/5/minty/bootstrap.min.css">
    <title>Login Form</title>
</head>
<body>
<h1>Login</h1>
<form action = "Controller" method="GET" >
<input type="hidden" name="actionType" value="login" />
    <table style="with: 50%">
        <tr>
            <td>Login</td>
            <td><input type="text" name="login" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
    </table>
    <input  type="submit" value="Submit" /></form>
<c:if test="${not empty errorMsgLogin}">
    <tr>
        <td><p style="color: red">${errorMsgLogin}</p></td>
        <td><a href="register.jsp"><button>Sign up</button></a></td>
    </tr>
</c:if>
</form>
</body>
</html>