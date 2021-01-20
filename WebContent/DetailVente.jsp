<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail vente</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<h2>Détail vente</h2>

	<form action="DetailVenteServlet" method="post">
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
				<td><label for="categorie">Catégorie : </label></td>
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
				<td><label for="finEnchère">Fin de l'enchère : </label></td>
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
				<td><label for="montantEnchere">Ma proposition : </label></td>
				<td><input id="montantEnchere" type="number" name="montantEnchere" required="required" maxlength="3"></td>
				<!-- Mettre la dernière enchère avec 1 point en plus dans un attribut value et fixer cette valeur mini avec un attribut min-->
				<td><input type="submit" value="Enchérir">
			</tr>


		</table>
	</form>




</body>
</html>