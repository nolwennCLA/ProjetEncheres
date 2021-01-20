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
	
	<p style="color: red">${pseudoSess}</p>
	
	<p><a href="">Enchères</a> - <a href="<%=request.getContextPath()+"/NouvelleVenteArticleServlet"%>">Vendre un article</a> - <a href="<%=request.getContextPath()+"/MonProfilServlet"%>">Mon profil</a> - <a href="<%=request.getContextPath()+"/DeconnexionServlet"%>">Déconnexion</a></p>
	
	
	
	<h2>Liste des enchères</h2>
	
	<h3>Filtres :</h3>
	
	<form action="AccueilConnecteServlet" method="GET">
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
	
	
	
	
	<table>
	<tr>
		<td><input id="achats" type="radio" value="achats" name="achatsVentes"><label for="achats">Achats</label></td>
		<td><input id="ventes" type="radio" value="ventes" name="achatsVentes"><label for="ventes">Mes ventes</label></td>
	</tr>
	
	<tr>
		<td>
			<input id="encheresOuvertes" type="radio" value="encheresOuvertes" name="encheres" disabled="disabled"><label for="encheresOuvertes">enchères ouvertes</label><br>
			<input id="mesEncheres" type="radio" value="mesEncheres" name="encheres" disabled="disabled"><label for="mesEncheres">mes enchères</label><br>
			<input id="encheresRemportees" type="radio" value="encheresRemportees" name="encheres" disabled="disabled"><label for="encheresRemportees">mes enchères remportées</label>
		</td>
		
		<td>
			<input id="ventesEnCours" type="radio" value="ventesEnCours" name="mesVentes" disabled="disabled"><label for="ventesEnCours">mes ventes en cours</label><br>
			<input id="ventesNonDebutees" type="radio" value="ventesNonDebutees" name="mesVentes" disabled="disabled"><label for="ventesNonDebutees">ventes non débutées</label><br>
			<input id="ventesTerminees" type="radio" value="ventesTerminees" name="mesVentes" disabled="disabled"><label for="ventesTerminees">ventes terminées</label>
		</td>
	</tr>
	</table>
	</form>
	
	
<%-- 	<c:choose>
	<c:when test="${achatsVentes.equals('ventes')}">
		<c:if test="${mesVentes.equals('ventesNonDebutees')}">
			<c:forEach var="art" items="${model.getLstArt()}">
				<c:if test="${art.getUtilisateur().getNoUtilisateur() == noSess}">
					<p>
						${art.getNomArticle()}<br>
						Prix : ${art.getMiseAPrix()}<br>
						Fin de l'enchère : ${art.getDateFinEncheres()}<br>
						Vendeur : ${art.getUtilisateur().getNom()}		
					</p>
				</c:if>
			</c:forEach>
		</c:if>
	</c:when>
	</c:choose> --%>
	
	<c:if test="${achatsVentes.equals('ventes') && mesVentes.equals('ventesNonDebutees')}">
			<c:forEach var="art" items="${model.getLstArt()}">
				<c:if test="${art.getUtilisateur().getNoUtilisateur() == noSess}">
					<p>
						${art.getNomArticle()}<br>
						Prix : ${art.getMiseAPrix()}<br>
						Fin de l'enchère : ${art.getDateFinEncheres()}<br>
						Vendeur : ${art.getUtilisateur().getNom()}		
					</p>
				</c:if>
			</c:forEach>
	</c:if>
	

</body>
<script src="accueilConnecteVue.js"></script>
</html>