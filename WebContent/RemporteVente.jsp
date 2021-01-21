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
				<td> <h3>${model.article.nomArticle}</h3></td>
				<td>	
			</tr>
			<tr>
				<td>Description : </td>
				<td> ${model.article.description}</td>
			</tr>
			<tr>
				<td>Meilleure offre : </td>
				<td> ${meilleureOffre} pts</td>	
			</tr>
			<tr>
				<td>Mise à prix :</td>
				<td>${model.article.miseAPrix} points</td>					
			</tr>
			<tr>
				<td>Retrait : </td>
				<td>${model.article.retrait.rue}</td>				
			</tr>
			<tr>
				<td></td>
				<td>${model.article.retrait.codePostal}    ${model.article.retrait.ville}</td>				
			</tr>
			<tr>
				<td>Vendeur : </td>
				<td>${model.article.utilisateur.nom}</td>				
			</tr>
			<tr>
				<td>Tél : </td>
				<td>${model.article.utilisateur.telephone}</td>				
			</tr>
		</table>
				
			
	<form action="AccueilConnecteServlet" method="post">
			<input type="submit" value="Back">
	</form>

${message }
</body>
</html>