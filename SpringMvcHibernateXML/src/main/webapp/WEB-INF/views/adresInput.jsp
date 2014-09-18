<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voer hier uw adres in</title>
</head>
<body>
	<div align="center">
	<fieldset>	
	<legend><b>Voer nieuw adres in</b></legend>
	<form method="post" action = "addAddress" modelAttribute="adres">	
	  <table>
	    <tr>
	      <td align="right">Postcode:</td>
	      <td align="left"><input type="text" name="postcode"  placeholder="Postcode" pattern="^[0-9]{4}[a-zA-Z]{2}$" title="vb postcode 1000AA" required autofocus></td>
	    </tr>
	    <tr>
	      <td align="right">Straatnaam:</td>
	      <td align="left"><input type="text" name="straatNaam" placeholder="Straatnaam" required></td>
	    </tr>
	    <tr>
	      <td align="right">Huisnummer:</td>
	      <td align="left"><input type="text" name="huisNummer" placeholder="bv 1" pattern="^[0-9]+$" title="vb huisnummer 15" required></td>
	      <td align="right"><input type="text" name="toevoeging" placeholder="bv II" maxlength="4" size="4"></td>
	    </tr>     
	     <tr>
	      <td align="right">Woonplaats:</td>
	      <td align="left"><input type="text" name="woonPlaats" placeholder="Woonplaats" required></td>
	    </tr>
	     <tr>
	      <td align="right"></td>
	      <td align="center"><input type="submit" value="Opslaan"></td>
	    </tr>
	    
	  </table>
	</form>
	</fieldset>
	</div>



</body>
</html>