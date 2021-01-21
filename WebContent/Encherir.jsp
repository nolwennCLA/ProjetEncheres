<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail vente</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<h2>Détail vente</h2>

	<h3>${model.article.nomArticle}</h3>

	<form action="EncherirServlet" method="post">
		<table>
			<tr>
				<td><label for="description">Description : </label>
				<td>${model.article.description}</td>
			</tr>
			<tr>
				<td><label for="categorie">Catégorie : </label></td>
				<td>${model.article.categorie.libelle}</td>
			</tr>
			<tr>
				<td><label for="meilleureOffre">Meilleure offre : </label></td>
				<c:if test="${afficheEnchere == 1}">
					<td>${meilleureOffre} points par ${pseudoFuturGagnant}</td>
				</c:if>
				<c:if test="${afficheEnchere == 0}">
					<td>L'article n'a reçu aucune enchère</td>
				</c:if>

			</tr>
			<tr>
				<td><label for="miseAPrix">Mise à prix : </label></td>
				<td>${model.article.miseAPrix} points</td>
			</tr>
			<tr>
				<td><label for="finEnchère">Fin de l'enchère : </label></td>
				<td><fmt:formatDate pattern="dd/MM/yyyy"
						value="${model.article.dateFinEncheres}" /></td>
			</tr>
			<tr>
				<td><label for="retrait">Retrait : </label></td>
				<!-- Si aucun retrait n'a été renseigné lors de la création de l'article (donc par défaut c'est l'adresse du vendeur) -->
				<td><c:if test="${model.article.retrait==null}">
					${model.article.utilisateur.rue}
					<br>${model.article.utilisateur.codePostal} ${model.article.utilisateur.ville}
					</c:if> <!-- Sinon le retrait est celui renseigné lors de la création de l'article -->
					${model.article.retrait.rue}<br>${model.article.retrait.codePostal}
					${model.article.retrait.ville}</td>

			</tr>
			<tr>
				<td><label for="vendeur">Vendeur : </label></td>
				<td>${model.article.utilisateur.pseudo}</td>
			</tr>
		</table>
	</form>

	<form action="EncherirServlet" method="post">
		<table>
			<tr>
				<td><label for="montantEnchere">Ma proposition : </label></td>
				<td><input id="montantEnchere" type="number"
					name="montantEnchere" required="required" maxlength="3"></td>
				<!-- Mettre la dernière enchère avec 1 point en plus dans un attribut value et fixer cette valeur mini avec un attribut min-->
				<td><input type="submit" value="Encherir" name="encherir"></td>
			</tr>

		</table>
	</form>
	<br>
	<p style="color: red">${message1}</p>

</body>
</html>