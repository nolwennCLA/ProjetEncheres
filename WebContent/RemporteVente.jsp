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
				<td><label for="nomArticle">Article : </label></td>
				<td><!-- Faire le lien avec le noArticle de la page accueilConnectéServlet -->		
			</tr>
			<tr>
				<td><label for="description">Description : </label>
				<td><!-- Faire le lien avec le noArticle de la page accueilConnectéServlet -->
			</tr>
			<tr>
				<td><label for="meilleureOffre">Meilleure offre : </label></td>
				<td><!-- Faire le lien avec le noArticle de la page accueilConnectéServlet -->	
			</tr>
			<tr>
				<td><label for="miseAPrix">Mise à prix : </label></td>
				<td><!-- Faire le lien avec le noArticle de la page accueilConnectéServlet -->					
			</tr>
			<tr>
				<td><label for="retrait">Retrait : </label></td>
				<td><!-- Faire le lien avec le noArticle de la page accueilConnectéServlet -->					
			</tr>
			<tr>
				<td><label for="vendeur">Vendeur : </label></td>
				<td><!-- Faire le lien avec le noArticle de la page accueilConnectéServlet -->					
			</tr>
			<tr>
				<td><label for="vendeur">Téléphone : </label></td>
				<td><!-- Faire le lien avec le noArticle de la page accueilConnectéServlet -->					
			</tr>
		</table>
				
			
	<form action="AccueilConnecteServlet" method="post">
			<input type="submit" value="Back">
	</form>

</body>
</html>