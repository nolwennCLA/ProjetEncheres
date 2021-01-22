<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Remporte Enchere</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	
	<h2>Vous avez remporté la vente</h2>

		<table>
			<tr>
				<td> <h3>${nomArticle}</h3></td>
				<td>	
			</tr>
			<tr>
				<td>Description : </td>
				<td> ${description}</td>
			</tr>
			<tr>
				<td>Meilleure offre : </td>
				<td> ${meilleureOffre} pts</td>	
			</tr>
			<tr>
				<td>Mise à prix :</td>
				<td>${miseAPrix} points</td>					
			</tr>
			<tr>
				<td rowspan="2">Retrait : </td>
				<td>${rue}</td>				
			</tr>
			<tr>
				<td>${codePostal} ${ville}</td>				
			</tr>
			<tr>
				<td>Vendeur : </td>
				<td>${vendeur}</td>				
			</tr>
			<tr>
				<td>Tél : </td>
				<td>${telephone}</td>				
			</tr>
		</table>
				
			
	<form action="AccueilConnecteServlet" method="post">
			<input type="submit" value="Back">
	</form>

${message }
</body>
</html>