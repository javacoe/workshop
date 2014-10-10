<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<%@ page import="nl.kadaster.web.domain.Adres" %>
<%@ page import="nl.kadaster.business.BusinessServiceObject" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Opgeslagen adressen</title>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse
}
</style>
</head>
<body>
	<%
	//TODO dependency injection met Spring (moet dit ook een servlet worden?)	
	BusinessServiceObject businessServiceObject = new BusinessServiceObject();
		List<Adres> opgeslagenAdressen = businessServiceObject.getAdressen();
	%>
		<table>
		<tr>
			<th>Straat</th>
			<th>Huisnummer</th>
			<th>Toevoeging</th>
			<th>Postcode</th>
			<th>Woonplaats</th>
		</tr>
		<%
			for (Adres adres : opgeslagenAdressen) {
		%>
		<tr>
			<td><%=adres.getStraat()%></td>
			<td><%=adres.getHuisnummer()%></td>
			<td><%=adres.getToevoeging()%></td>
			<td><%=adres.getPostcode()%></td>
			<td><%=adres.getWoonplaats()%></td>
		</tr>
		<%
			}
		%>

	</table>
</body>
</html>