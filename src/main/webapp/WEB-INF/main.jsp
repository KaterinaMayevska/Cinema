<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
  <meta charset="UTF-8" />
   <link rel="stylesheet" href="https://bootswatch.com/5/minty/bootstrap.min.css"></link>
   <title>Cinema</title>

</head>
<body>
    <h1>Welcome</h1>


<div th:fragment="navigation(activeTab)">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item" >
                    <a class="nav-link" href="films.jsp">Films</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="seances.jsp">Seances</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="seanceTickets.jsp">Tickets</a>
                </li>
                <li class="nav-item">
                <c:if test="${isAdmin==true}">
                    <a class="nav-link" href="users.jsp">Users</a>
                 </c:if>
                </li>
                 <li class="nav-item">
                 <c:if test="${isAdmin==false}">
                 <a class="nav-link" href="userTicket.jsp">My tickets</a>
                 </c:if>
                  </li>

                <li class="nav-item" >
                     <a class="btn btn-primary" href="welcome.jsp">Log out</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
</body>
</html>