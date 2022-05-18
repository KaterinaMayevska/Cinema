<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>

<% List tickets = (List)session.getAttribute("theTickets");
    request.setAttribute("theTickets", tickets);
   Object theFilms = session.getAttribute("theFilms");
 request.setAttribute("theFilms", theFilms);
 Object seances = session.getAttribute("theSeances");
  request.setAttribute("theSeances", seances);
 %>
<html>
<head>
    <title>Tickets</title>

     <link rel="stylesheet" href="https://bootswatch.com/5/minty/bootstrap.min.css">


</head>
<body>
<form  action="Controller" method="GET">
<input type="hidden" name="actionType" value="seanceTickets" />
 <table style="with: 50%">
    <form  action="seanceTickets.jsp" method="GET">
        <tr>
            <td>Film Name</td>

            <td> <input type="text" name="name" /> </td>

        </tr>
        <tr>
            <td>Seance Date</td>

            <td><input type="text" name="seanceDate" /></td>

        </tr>


        </table>
    <c:if test="${not empty errorMsgTicket}">
        <p style="color: red">${errorMsgTicket}</p>
    </c:if>
    <input class="btn btn-primary" type="submit" value="Enter"  /></form>
     <a class="btn btn-primary" href="main.jsp">Return</a>


<form  action="seanceTickets.jsp" method="POST">
<div class = "container">

<table style="margin-top: 3ch" class="table">
  <thead class="thead-dark">
    <tr>
        <td style="display:none;">Id</td>
        <td>Line</td>
        <td>Place</td>
        <td>If available</td>

    </tr>
  </thead>
  <tbody>

    <c:if test="${not empty theTickets}">
    <c:forEach items="${theTickets}" var="item">
        <tr>
            <td style="display:none;"><c:out value="${item.id}" /></td>
            <td><c:out value="${item.line}" /></td>
            <td><c:out value="${item.place}" /></td>
            <td><c:choose>
                    <c:when test="${item.user_id=='1'}">
                        <c:out value="Available" />
                         <c:if test="${isAdmin==false}">
                        <form action="Controller" method="POST">
                          <input type="hidden" name="actionType" value="buyTicket" />
                          <input type="hidden" name="ticketId" value="${item.id}" />
                          <td><input type="submit" name="Buy" value="Buy" /></td>
                         </form>
                         </c:if>

                    </c:when>
                    <c:otherwise>
                        <c:out value="Not available" />
                    </c:otherwise>
                </c:choose>
                  </td>


        </tr>
    </c:forEach>
    </c:if>
    </tbody>

</table>

<div>
</form>
</form>
</body>
</html>