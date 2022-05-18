<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<% List users = (List)session.getAttribute("users");
 request.setAttribute("users", users);
 %>
<html>
<head>
    <title>Users</title>
     <link rel="stylesheet" href="https://bootswatch.com/5/minty/bootstrap.min.css">


</head>
<body>
<form  action="Controller" method="GET">
<input type="hidden" name="actionType" value="users" />



<div class="container">
    <div th:replace="~{navbar :: navigation(users)}"/>

    <h1 style="padding-top: 20px">Films</h1>

    <td><input class="btn btn-primary" type="submit" value="Show the list of users"  /></td>
    <td><a class="btn btn-primary" href="main.jsp">Main</a></td>



    <table style="margin-top: 3ch" class="table">
        <thead class="thead-dark">
        <tr>
            <td scope="col">Login</td>
            <td scope="col">Email</td>
            <td scope="col">is Admin</td>

        </tr>
        </thead>
        <tbody>

        <c:if test="${not empty users}">
        <c:forEach items="${users}" var="item">
        <tr>
            <td><c:out value="${item.login}" /> </td>
            <td><c:out value="${item.email}" /> </td>
            <td><c:out value="${item.isAdmin}" /> </td>




        </tr>
        </c:forEach>
        </c:if>

        </tbody>
    </table>



</div>

</form>
</body>
</html>