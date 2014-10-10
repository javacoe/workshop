<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.Enumeration" %>
<jsp:useBean id="adres" class="nl.kadaster.web.domain.Adres" scope="session" />
<%! boolean[] errorCodes; %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Adres invoerformulier</title>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse
}
</style>
</head>
<body>
	<h1>Adres invoerformulier</h1>
	<p>Vul hieronder een adres in om op te slaan in de database.</p>
	<%
		if (session.getAttribute("errorCodes") != null)
			errorCodes = (boolean[]) session.getAttribute("errorCodes");
		else
			errorCodes = new boolean[6];
		if (session.getAttribute("errors") != null) {
	%><p>De volgende velden zijn niet goed ingevoerd:</p>
	<ul><%=session.getAttribute("errors")%></ul>
	<%
		}
	%>
	<form method="post" action="SaveAdres">
		<table>
			<tr>
				<td colspan="2"><%=(errorCodes[1]) ? "<font color=\"red\">Straat: </font>"
					: "Straat: "%><input
					type="text" name="straat" size="35"
					value="<%=adres.getStraat()%>" title="Een straatnaam."
					placeholder="bijv. Reykjavikplein" autofocus></td>
			</tr>
			<tr>
				<td><%=(errorCodes[2]) ? "<font color=\"red\">Huisnummer: </font>"
					: "Huisnummer: "%><input
					type="text" name="huisnummer" size="5"
					value="<%=adres.getHuisnummer()%>" title="Alleen cijfers."
					placeholder="bijv. 1" required></td>
				<td><%=(errorCodes[3]) ? "<font color=\"red\">Toevoeging: </font>"
					: "Toevoeging: "%><input
					type="text" name="toevoeging" size="5"
					value="<%=adres.getToevoeging()%>" title="Bijvoorbeeld hs of I."
					placeholder="bijv. hs"></td>
			</tr>
			<tr>
				<td><%=(errorCodes[4]) ? "<font color=\"red\">Postcode: </font>"
					: "Postcode: "%><input
					type="text" name="postcode" size="7"
					value="<%=adres.getPostcode()%>"
					title="4 letters gevolgd door 2 cijfers."
					pattern="[1-9]\d{3}\s?[a-zA-Z]{2}" placeholder="bijv. 3543 KA"
					required></td>
				<td><%=(errorCodes[5]) ? "<font color=\"red\">Woonplaats: </font>"
					: "Woonplaats: "%><input
					type="text" name="woonplaats" size="20"
					value="<%=adres.getWoonplaats()%>"
					title="Een woonplaats in Nederland." placeholder="bijv. Utrecht"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Opslaan"></td>
			</tr>
		</table>
	</form>
</body>
</html>