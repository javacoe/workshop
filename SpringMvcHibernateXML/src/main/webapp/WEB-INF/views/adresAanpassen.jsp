<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.springhibernate.base.model.Adres" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voer hier uw adres in</title>
</head>
<body>

<% Adres adres = (Adres) request.getAttribute("adres"); %>
<h1> Adres ID = <%=adres.getId() %></h1>

	
	<fieldset>
	<legend>Post Adres</legend>
	<form method="post" action = "addAddress" modelAttribute="adres">	
	  <table>
	    <tr>
	      <td align="right">Postcode:</td>
	      <td align="left"><input type="text" name="postcode" value=<%= adres.getPostcode() %> placeholder="Postcode" pattern="^[0-9]{4}[a-zA-Z]{2}$" title="vb postcode 1000AA" required autofocus></td>
	    </tr>
	    <tr>
	      <td align="right">Straatnaam:</td>
	      <td align="left"><input type="text" name="straatNaam" value=<%=adres.getStraatNaam() %> placeholder="Straatnaam" required></td>
	    </tr>
	    <tr>
	      <td align="right">Huisnummer:</td>
	      <td align="left"><input type="text" name="huisNummer" value=<%=adres.getHuisNummer() %> placeholder="bv 1" pattern="^[0-9]+$" title="vb huisnummer 15" required></td>
	      <td align="right"><input type="text" name="toevoeging" value=<%=adres.getToevoeging() %> placeholder="bv II" maxlength="4" size="4"></td>
	    </tr>     
	     <tr>
	      <td align="right">Woonplaats:</td>
	      <td align="left"><input type="text" name="woonPlaats" value=<%=adres.getWoonPlaats() %> placeholder="Woonplaats" required></td>
	    </tr>
	     <tr>
	      <td align="right"></td>
	      <td align="center"><input type="submit" value="Opslaan"></td>
	    </tr>
	    
	  </table>
	</form>
	</fieldset>



</body>
</html>