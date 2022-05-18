<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <link rel="stylesheet" href="https://bootswatch.com/5/minty/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>


    <title>Add film</title>


</head>
<body>
<h1>Add film</h1>

<form  action="Controller" method="GET">
<input type="hidden" name="actionType" value="addFilms" />
    <table style="with: 50%">
    <form  action="addFilms.jsp" method="GET">
        <tr>
            <td>Name</td>

            <td> <input type="text" name="name" /> </td>

        </tr>
        <tr>
            <td>Director</td>
            <td><input type="text" name="director" /></td>
        </tr>
        <tr>
            <td>Release Date</td>

            <td><input type="text" name="releaseDate" /></td>

        </tr>
        <tr>
              <td>Genre</td>

              <td><input type="text" name="genre" /></td>

                </tr>
         <tr>
               <td>Info</td>

               <td><input type="text" name="info" /></td>

          </tr>

        </table>
    <c:if test="${not empty errorMsgFilm}">
        <p style="color: red">${errorMsgFilm}</p>
    </c:if>
    <input class="btn btn-primary" type="submit" value="Enter"  />
    </form> </form>
    <p></p>
    <p></p>
    <a class="btn btn-primary" href="main.jsp">Return</a>


</body>
</html>