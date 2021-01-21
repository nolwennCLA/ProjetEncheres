<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enchere non commencée</title>
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	
	<p style="color: red">${pseudoSess}</p>
	
	
	<h2>Nouvelle vente</h2>
	
	<form action="EnchereNonCommenceeServlet" method="GET">
	<table>
		<tr>
			<td><label for="nomArticle">Article : </label></td>
			<td><input id="nomArticle" type="text" name="nomArticle" value="${article.getNomArticle()}" required="required"></td>
		</tr>
		
		<tr>
			<td><label for="description">Description : </label></td>
			<td><textarea id="description" rows="3" maxlength="300" name="description">${article.getDescription()}</textarea></td>
		</tr>
		
		<tr>
			<td><label for="categorie">Catégorie : </label></td>
			<td>
				<select id="categorie" name="categorie" required="required">
					<c:forEach var="c" items="${lstCategories}">
						<c:choose>
							<c:when test="${article.getCategorie().getNoCategorie() == c.getNoCategorie()}">
								<option value="${c.getNoCategorie()}" selected="selected">${c.getLibelle()}</option>
							</c:when>
							<c:otherwise>
								<option value="${c.getNoCategorie()}">${c.getLibelle()}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<td><label for="photo">Photo de l'article : </label></td>
			<td><input id="photo" type="file" name="photo"></td>
		</tr>
		
		<tr>
			<td><label for="miseAPrix">Mise à prix : </label></td>
			<td><input id=" miseAPrix" type="number" value="${article.getMiseAPrix()}" name="miseAPrix"></td>
		</tr>
		
		<tr>
			<td><label for="dateDebutEncheres">Début des enchères : </label></td>
			<td><input id="dateDebutEncheres" type="date" name="dateDebutEncheres" value="${article.getDateDebutEncheres()}" required="required"></td>
		</tr>
		
		<tr>
			<td><label for="dateFinEncheres">Fin des enchères : </label></td>
			<td><input id="dateFinEncheres" type="date" name="dateFinEncheres" value="${article.getDateFinEncheres()}" required="required"></td>
		</tr>
	</table>

	
	<fieldset>
	<legend>Retrait</legend>
	
	<c:if test="${afficheRetrait == 1 }">
		<table>
			<tr>
				<td><label for="rue">Rue : </label></td>
				<td><input id="rue" type="text" name="rue" value="${article.getRetrait().getRue()}" required="required"></td>
			</tr>
			<tr>
				<td><label for="codePostal">Code postal : </label></td>
				<td><input id="codePostal" type="text" name="codePostal" value="${article.getRetrait().getCodePostal()}" required="required"></td>
			</tr>
			<tr>
				<td><label for="ville">Ville : </label></td>
				<td><input id="ville" type="text" name="ville" value="${article.getRetrait().getVille()}" required="required"></td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${afficheRetrait == 0 }">
		<table>
			<tr>
				<td><label for="rue">Rue : </label></td>
				<td><input id="rue" type="text" name="rue" value="${utilisateurSess.getRue()}" required="required"></td>
			</tr>
			<tr>
				<td><label for="codePostal">Code postal : </label></td>
				<td><input id="codePostal" type="text" name="codePostal" value="${utilisateurSess.getCodePostal()}" required="required"></td>
			</tr>
			<tr>
				<td><label for="ville">Ville : </label></td>
				<td><input id="ville" type="text" name="ville" value="${utilisateurSess.getVille()}" required="required"></td>
			</tr>
		</table>
	</c:if>
	
	</fieldset>	
	
	<input type="submit" value="Enregistrer" name="bouton">
	<input type="submit" value="Annuler" name="bouton" formnovalidate>
	<input type="submit" value="Annuler la vente" name="bouton" formnovalidate>

	</form>
	
	<p style="color: red">${messageErreur}</p>
	
	<c:forEach var="art" items="${listeSelection}" varStatus="status">
		<p>
			<a href="enchereNonCommenceeVue.jsp">${art.getNomArticle()}</a><br>
			Prix : ${art.getMiseAPrix()}<br>
			Fin de l'enchère : ${art.getDateFinEncheres()}<br>
			Vendeur : ${art.getUtilisateur().getNom()}<br>
		<p>
	</c:forEach>

</body>
</html>