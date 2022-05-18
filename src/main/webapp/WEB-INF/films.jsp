<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<% List films = (List)session.getAttribute("films");
 request.setAttribute("films", films);
 %>
<html>
<head>
    <title>Films</title>
     <link rel="stylesheet" href="https://bootswatch.com/5/minty/bootstrap.min.css">


</head>
<body>
<form  action="Controller" method="GET">
<input type="hidden" name="actionType" value="films" />



<div class="container">
    <div th:replace="~{navbar :: navigation(films)}"/>

    <h1 style="padding-top: 20px">Films</h1>

    <c:if test="${isAdmin==true}">
        <a class="btn btn-primary" href="addFilms.jsp">Add</a>
    </c:if>

    <td><input class="btn btn-primary" type="submit" value="Show the list of films"  /></td>
    <td><a class="btn btn-primary" href="main.jsp">Main</a></td>



    <table style="margin-top: 3ch" class="table">
        <thead class="thead-dark">
        <tr>
            <td scope="col">Name</td>
            <td scope="col">Director</td>
            <td scope="col">Release date</td>
            <td scope="col">Genre</td>
            <td scope="col">Info</td>

        </tr>
        </thead>
        <tbody>

        <c:if test="${not empty films}">
        <c:forEach items="${films}" var="film">
        <tr>
            <td><c:out value="${film.name}" /> </td>
            <td><c:out value="${film.director}" /> </td>
            <td><c:out value="${film.releaseDate}" /> </td>
            <td><c:out value="${film.genre}" /> </td>
            <td><c:out value="${film.info}" /> </td>
             <c:if test="${isAdmin==true}">
            <td><form action="Controller" method="POST">
              <input type="hidden" name="actionType" value="deleteFilm" />
              <input type="hidden" name="filmId" value="${film.id}" />
              <td><input type="submit" name="Delete" value="Delete" /></td>
             </form>   </td>
             </c:if>




        </tr>
        </c:forEach>
        </c:if>

        </tbody>
    </table>



</div>

</form>
</body>
</html>