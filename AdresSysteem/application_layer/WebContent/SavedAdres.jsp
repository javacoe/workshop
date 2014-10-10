<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="adres" class="nl.kadaster.web.domain.Adres" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adres bewaard</title>
</head>
<body>
<p>Dit adres is bewaard:</p>
<p>
Straatnaam: <%= adres.getStraat() %><br />
Huisnummer: <%= adres.getHuisnummer() %><br />
Toevoeging: <%= adres.getToevoeging() %><br />
Postcode: <%= adres.getPostcode() %><br />
Woonplaats: <%= adres.getWoonplaats() %><br />
</p>
<p>Bekijk alle <a href="ShowAdressen.jsp">hier</a> alle opgeslagen adressen.</p>
</body>
</html>