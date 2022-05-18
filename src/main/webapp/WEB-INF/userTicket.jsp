<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>

<% List tickets = (List)session.getAttribute("myTickets");
    request.setAttribute("myTickets", tickets);
   List films = (List)session.getAttribute("myFilms");
 request.setAttribute("myFilms", films);
  List seances = (List)session.getAttribute("mySeances");
  request.setAttribute("mySeances", seances);
 %>
<html>
<head>
    <title>My Tickets</title>
    <style>
        .twotables {
          display: flex;
           width: 100%;
         }
        .table-1 {
           margin-right: 0px;
           width:30%
        }


        </style>

     <link rel="stylesheet" href="https://bootswatch.com/5/minty/bootstrap.min.css">


</head>
<body>
<form  action="Controller" method="GET">
<input type="hidden" name="actionType" value="userTickets" />

    <input class="btn btn-primary" type="submit" value="Reload"  /></form>
     <a class="btn btn-primary" href="main.jsp">Return</a>
<div class = "twotables">

<table class = "table-1">
  <thead class="thead-dark">
 <tr>

        <td>Name</td>
        <td>Director</td>
        <td>Genre</td>
    </tr>
</thead>
    <c:if test="${not empty myFilms}">
    <c:forEach items="${myFilms}" var="item">
        <tr>

            <td><c:out value="${item.name}" /></td>
            <td><c:out value="${item.director}" /></td>
            <td><c:out value="${item.genre}" /></td>


        </tr>
    </c:forEach>
    </c:if>


</table>



<table class = "table-1">
  <thead class="thead-dark">
    <tr>
        <td style="display:none;">Id</td>
        <td>Price (UAH) </td>
        <td>Date</td>

    </tr>
  </thead>

    <c:if test="${not empty mySeances}">
    <c:forEach items="${mySeances}" var="item">
        <tr>


            <td><c:out value="${item.price}" /></td>
            <td><c:out value="${item.date}" /></td>


        </tr>
    </c:forEach>
    </c:if>


</table>
<table class = "table-1">
  <thead class="thead-dark">
    <tr>
        <td style="display:none;">Id</td>
        <td>Line </td>
        <td>Place</td>

    </tr>
  </thead>

    <c:if test="${not empty myTickets}">
    <c:forEach items="${myTickets}" var="item">
        <tr>
            <td><c:out value="${item.line}" /></td>
            <td><c:out value="${item.place}" /></td>


        </tr>
    </c:forEach>
    </c:if>


</table>

<div>



</form>
</form>
</body>
</html>