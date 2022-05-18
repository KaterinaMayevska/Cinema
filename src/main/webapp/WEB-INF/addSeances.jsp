<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <link rel="stylesheet" href="https://bootswatch.com/5/minty/bootstrap.min.css">



    <title>Add seance</title>


</head>
<body>
<h1>Add new seance</h1>

<form  action="Controller" method="GET">
<input type="hidden" name="actionType" value="addSeances" />
    <table style="with: 50%">
    <form  action="addSeances.jsp" method="GET">
        <tr>
            <td>Film Name</td>

            <td> <input type="text" name="name" /> </td>

        </tr>
        <tr>
            <td>Ticket price</td>
            <td><input type="number" name="price" /></td>
        </tr>
        <tr>
            <td>Seance Date</td>

            <td><input type="text" name="date" /></td>

        </tr>


        </table>
    <c:if test="${not empty errorMsgSeance}">
        <p style="color: red">${errorMsgSeance}</p>
    </c:if>
    <input class="btn btn-primary" type="submit" value="Enter"  />
     <a class="btn btn-primary" href="main.jsp">Return</a>
    </form> </form>



</body>
</html>