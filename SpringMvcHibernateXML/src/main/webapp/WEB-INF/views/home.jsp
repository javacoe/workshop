<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <% String errorMessage = (String) request.getAttribute("error_message"); %>
	    <b> ${error_message} </b>	       
        <div align="center">
            <h1>Adressen</h1>
            <h2><a href="nieuwAdres">Nieuw Adres</a></h2>		    
            <table border="1">
                <th>ID</th>
                <th>HUIS_NUMMER</th>
                <th>POST_CODE</th>
                <th>STRAAT_NAAM</th>
                <th>TOEVOEGING</th>
                <th>WOON_PLAATS</th>
                 
                <c:forEach var="adres" items="${adresList}" varStatus="status">
                <tr>
                    <!-- <td>${status.index + 1}</td> -->
                    <td>${adres.id}</td>
                    <td>${adres.huisNummer}</td>
                    <td>${adres.postcode}</td>
                    <td>${adres.straatNaam}</td>
                    <td>${adres.toevoeging}</td>
                    <td>${adres.woonPlaats}</td>
                    <td>
                        <a href="aanpassen?id=${adres.id}">Aanpassen</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="verwijderen?id=${adres.id}">Verwijderen</a>
                    </td>
                             
                </tr>
                </c:forEach>             
            </table>
        </div>
    </body>
</html>>
