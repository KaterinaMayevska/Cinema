<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<% List seances = (List)session.getAttribute("allSeances");
 request.setAttribute("seances", seances);
 List films = (List)session.getAttribute("allFilmsS");
  request.setAttribute("allFilmsS", films);
 %>
<html>
<head>
    <title>Seances</title>

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
<h1>See all possible seances</h1>
<form action = "Controller" method="GET" >
<input type="hidden" name="actionType" value="seances" />
<input class="btn btn-primary" name="seances" type="submit" value="Reload" />
 <c:if test="${isAdmin==true}">
         <a class="btn btn-primary" href="addSeances.jsp">Add</a>
 </c:if>
 <a class="btn btn-primary" href="main.jsp">Main</a></form>
<div class = "twotables">

<table class = "table-1">
  <thead class="thead-dark">
 <tr>

        <td>Name</td>
        <td>Director</td>
        <td>Genre</td>
    </tr>
</thead>
    <c:if test="${not empty allFilmsS}">
    <c:forEach items="${allFilmsS}" var="item">
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

    <c:if test="${not empty seances}">
    <c:forEach items="${seances}" var="item">
        <tr>


            <td><c:out value="${item.price}" /></td>
            <td><c:out value="${item.date}" /></td>
             <c:if test="${isAdmin==true}">
                        <td><form action="Controller" method="POST">
                          <input type="hidden" name="actionType" value="deleteSeance" />
                          <input type="hidden" name="seanceId" value="${item.id}" />
                          <td><input type="submit" name="Delete" value="Delete" /></td>
                         </form>   </td>
               </c:if>


        </tr>
    </c:forEach>
    </c:if>


</table>
<div>

</body>
</html>