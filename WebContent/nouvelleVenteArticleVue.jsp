<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	
	<p style="color: red">${pseudoSess}</p>
	
	
	<h2>Nouvelle vente</h2>
	
	
	<form action="NouvelleVenteArticleServlet" method="GET">
	<table>
		<tr>
			<td><label for="nomArticle">Article : </label></td>
			<td><input id="nomArticle" type="text" name="nomArticle" required="required"></td>
		</tr>
		
		<tr>
			<td><label for="description">Description : </label></td>
			<td><textarea id="description" rows="3" maxlength="300" name="description"></textarea></td>
		</tr>
		
		<tr>
			<td><label for="categorie">Catégorie : </label></td>
			<td>
				<select id="categorie" name="categorie" required="required">
					<c:forEach var="c" items="${lstCategories}">
						<option value="${c.getNoCategorie()}">${c.getLibelle()}</option>
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
			<td><input id=" miseAPrix" type="number" value="100" name="miseAPrix"></td>
		</tr>
		
		<tr>
			<td><label for="dateDebutEncheres">Début des enchères : </label></td>
			<td><input id="dateDebutEncheres" type="date" name="dateDebutEncheres" required="required"></td>
		</tr>
		
		<tr>
			<td><label for="dateFinEncheres">Fin des enchères : </label></td>
			<td><input id="dateFinEncheres" type="date" name="dateFinEncheres" required="required"></td>
		</tr>
	</table>
	
	<fieldset>
	<legend>Retrait</legend>
	<table>
		<tr>
			<td><label for="rue">Rue : </label></td>
			<td><input id="rue" type="text" name="rue" value="${rue}" required="required"></td>
		</tr>
		
		<tr>
			<td><label for="codePostal">Code postal : </label></td>
			<td><input id="codePostal" type="text" name="codePostal" value="${codePostal}" required="required"></td>
		</tr>
		
		<tr>
			<td><label for="ville">Ville : </label></td>
			<td><input id="ville" type="text" name="ville" value="${ville}" required="required"></td>
		</tr>
	
	</table>
	</fieldset>
	
	<input type="submit" value="Enregistrer" name="bouton">
	<input type="reset" value="Réinitialiser" name="bouton">
	<input type="submit" value="Annuler" name="bouton" formnovalidate>

	</form>
	
	<p style="color: red">${messageErreur}</p>
	

</body>
</html>