<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	
	<p><a href="CreationCompteServlet">S'inscrire</a> - <a href="ConnexionServlet">Se connecter</a></p>
	
	<h2>Liste des enchères</h2>
	
	<h3>Filtres :</h3>
	
	<form>
	<table>
		<tr>
			<td><input type="search" name="rechercheNom" placeholder="Le nom de l'article contient..." style="width: 250px"></td>
			<td rowspan="2"><input type="submit" value="Rechercher" name="bouton"></td>
		</tr>
		
		<tr>
			<td><label for="categorie">Catégorie : </label>
			<select id="categorie" name="rechercheCategorie" required="required">
					<option value="toutes">Toutes</option>
					<c:forEach var="c" items="${lstCategories}">
						<option value="${c.getNoCategorie()}">${c.getLibelle()}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	</form>
	
	
	
	
	<c:forEach var="art" items="${model.getLstArt()}">
		<p>
			${art.getNomArticle()}<br>
			Prix : ${art.getMiseAPrix()}<br>
			Fin de l'enchère : ${art.getDateFinEncheres()}<br>
			Vendeur : ${art.getUtilisateur().getNom()}		
		</p>
	</c:forEach>
	
	
	

</body>
</html>