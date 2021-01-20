<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail vente</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<h2>Détail vente</h2>
	
	<h3>${modelArticle.article.nomArticle}</h3>

	<form action="EncherirServlet" method="post">
		<table>
			<tr>
				<td><label for="description">Description : </label>
				<td>${modelArticle.article.description}</td>
			</tr>
			<tr>
				<td><label for="categorie">Catégorie : </label></td>
				<td>${modelArticle.article.categorie.libelle}</td>
			</tr>
			<tr>
				<td><label for="meilleureOffre">Meilleure offre : </label></td>
					<td><c:if test="">
					
					</c:if>
					<p style="color: red">Aucune enchère n'a été effectuée sur cet article</p>
					</td>	
			</tr>
			<tr>
				<td><label for="miseAPrix">Mise à prix : </label></td>
				<td>${modelArticle.article.miseAPrix} points</td>					
			</tr>
			<tr>
				<td><label for="finEnchère">Fin de l'enchère : </label></td>
				<td>${modelArticle.article.dateFinEncheres}</td>					
			</tr>
			<tr>
				<td><label for="retrait">Retrait : </label></td>
					<!-- Si aucun retrait n'a été renseigné lors de la création de l'article (donc par défaut c'est l'adresse du vendeur) -->
					<td><c:if test="${modelArticle.article.retrait==null}">
					${modelArticle.article.utilisateur.rue}
					<br>${modelArticle.article.utilisateur.codePostal} ${modelArticle.article.utilisateur.ville}
					</c:if>
					<!-- Sinon le retrait est celui renseigné lors de la création de l'article -->
					${modelArticle.article.retrait.rue}<br>${modelArticle.article.retrait.codePostal} ${modelArticle.article.retrait.ville}
					</td>
					
			</tr>
			<tr>
				<td><label for="vendeur">Vendeur : </label></td>
				<td>${modelArticle.article.utilisateur.pseudo}</td>				
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