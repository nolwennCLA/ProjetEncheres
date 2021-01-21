<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <% String chemin; %> --%>


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
			<input id="ventesEnCours" type="radio" value="EC" name="mesVentes" disabled="disabled"><label for="ventesEnCours">mes ventes en cours</label><br>
			<input id="ventesNonDebutees" type="radio" value="AV" name="mesVentes" disabled="disabled"><label for="ventesNonDebutees">ventes non débutées</label><br>
			<input id="ventesTerminees" type="radio" value="VT" name="mesVentes" disabled="disabled"><label for="ventesTerminees">ventes terminées</label>
		</td>
	</tr>
	</table>
	</form>
	
	
	
	<%--si l'utilisateur a sélectionné le bouton 'Mes Ventes' --%>
	<c:if test="${bouton.equals('ventes')}">
	
	<c:forEach var="art" items="${model.getLstArt()}">
	
		<%--on sélectionne uniquement les items de l'utilisateur en sessions --%>
		<c:if test="${art.getUtilisateur().getNoUtilisateur() == noSess}">
		
			<c:choose>
				<%--si l'utilisateur a coché une sous categorie --%>
				<c:when test="${critere != null}">
				
					<%--si l'etat de vente de l'article correspond à la sous categorie, on affiche --%>
					<c:if test="${art.getEtatVente().equals(critere)}">
						<p>					
							<c:if test="${art.getEtatVente().equals('AV')}">
								<a href="<%=request.getContextPath()+"/EnchereNonCommenceeServlet"%>?noArticle=${art.getNoArticle()}">${art.getNomArticle()}</a><br>
							</c:if>
							<c:if test="${art.getEtatVente().equals('EC')}">
								<a href="<%=request.getContextPath()+"/EncherirServlet"%>?noArticle=${art.getNoArticle()}">${art.getNomArticle()}</a><br>
							</c:if>
							<c:if test="${art.getEtatVente().equals('VT')}">
								<a href="<%=request.getContextPath()+"/MaVenteFinEncheresServlet"%>?noArticle=${art.getNoArticle()}">${art.getNomArticle()}</a><br>
							</c:if>
							Prix : ${art.getMiseAPrix()} points<br>
							Fin de l'enchère : ${art.getDateFinEncheres()}<br>
							Vendeur : <a href="<%=request.getContextPath()+"/PageProfilServlet"%>?pseudo=${art.getUtilisateur().getPseudo()}">${art.getUtilisateur().getPseudo()}</a>	
						</p>
					</c:if>
				</c:when>
				
				<%--si l'utilisateur n'a pas coché de sous categorie, on affiche tous les articles de l'utilisateur --%>
				<c:otherwise>
					<p>
						<c:if test="${art.getEtatVente().equals('AV')}">
							<a href="<%=request.getContextPath()+"/EnchereNonCommenceeServlet"%>?noArticle=${art.getNoArticle()}">${art.getNomArticle()}</a><br>
						</c:if>
						<c:if test="${art.getEtatVente().equals('EC')}">
							<a href="<%=request.getContextPath()+"/MaVenteFinEncheresServlet"%>?noArticle=${art.getNoArticle()}">${art.getNomArticle()}</a><br>
						</c:if>
						<c:if test="${art.getEtatVente().equals('VT')}">
							<a href="<%=request.getContextPath()+"/MaVenteFinEncheresServlet"%>?noArticle=${art.getNoArticle()}">${art.getNomArticle()}</a><br>
						</c:if>
						Prix : ${art.getMiseAPrix()} points<br>
						Fin de l'enchère : ${art.getDateFinEncheres()}<br>
						Vendeur : <a href="<%=request.getContextPath()+"/PageProfilServlet"%>?pseudo=${art.getUtilisateur().getPseudo()}">${art.getUtilisateur().getPseudo()}</a>			
					</p>
				</c:otherwise>
			</c:choose>
			
		</c:if>
	</c:forEach>

	</c:if>
	
	
	<%--si l'utilisateur a sélectionné le bouton 'Achats' --%>
	<c:if test="${bouton.equals('achats')}">
	
		<c:choose>
				<%--si l'utilisateur a coché une sous categorie --%>
				<c:when test="${critere != null}">
				
				<%--si la sous-cat est 'Enchères ouvertes' --%>
				<c:if test="${sousCat.equals('encheresOuvertes')}">
				
					<c:forEach var="art" items="${model.getLstArt()}">
					
						<%--si la date du jour est comprise entre la dateDebutEnchere et la dateFinEnchere de l'article, on affiche --%>
						<c:if test="${art.getDateDebutEncheres().compareTo(critere) <= 0
									&& art.getDateFinEncheres().compareTo(critere) >= 0}">	
							<p>					
								<a href="<%=request.getContextPath()+"/EncherirServlet"%>?noArticle=${art.getNoArticle()}">${art.getNomArticle()}</a><br>
								Prix : ${art.getMiseAPrix()} points<br>
								Fin de l'enchère : ${art.getDateFinEncheres()}<br>
								Vendeur : <a href="<%=request.getContextPath()+"/PageProfilServlet"%>?pseudo=${art.getUtilisateur().getPseudo()}">${art.getUtilisateur().getPseudo()}</a>	
							</p>
						
						</c:if>
						
					</c:forEach>
					
				</c:if>
				
					
				<%--si la sous-cat est 'Mes enchères' --%>
				<c:if test="${sousCat.equals('mesEncheres')}">
				
					<c:forEach var="ench" items="${model.getLstEnch()}">
					
						<%--si le numéro de l'enchérisseur correspond à celui de l'utilisateur en session, on affiche --%>
						<c:if test="${ench.getUtilisateur().getNoUtilisateur() == critere}">
							
							<p>					
								<c:if test="${art.getEtatVente().equals('AV')}">
									<a href="<%=request.getContextPath()+"/AccueilConnecteServlet"%>?noArticle=${art.getNoArticle()}">${art.getNomArticle()}</a><br>
								</c:if>
								<c:if test="${art.getEtatVente().equals('EC')}">
									<a href="<%=request.getContextPath()+"/EncherirServlet"%>?noArticle=${art.getNoArticle()}">${art.getNomArticle()} </a><br>
								</c:if>
								<c:if test="${art.getEtatVente().equals('VT')}">
									<a href="<%=request.getContextPath()+"/AcquisitionServlet"%>?noArticle=${art.getNoArticle()}">${art.getNomArticle()}</a><br>
								</c:if>
								Prix : ${art.getMiseAPrix()} points<br>
								Fin de l'enchère : ${art.getDateFinEncheres()}<br>
								Vendeur : <a href="<%=request.getContextPath()+"/PageProfilServlet"%>?pseudo=${art.getUtilisateur().getPseudo()}">${art.getUtilisateur().getPseudo()}</a>	
							</p>
						
						</c:if>
						
					</c:forEach>

				</c:if>
				
				
				<%--si la sous-cat est 'Mes enchères remportées' --%>
				<c:if test="${sousCat.equals('encheresRemportees')}">
				
					<c:forEach var="ench" items="${model.getLstEnch()}">
						
						<%--si l'enchère est terminée et qu'elle est rattachée à l'utilisateur en session, c'est que celui-ci l'a remportée --%>
						<c:if test="${ench.getUtilisateur().getNoUtilisateur() == critere
									&& ench.getArticle().getEtatVente().equals('VT')}">
						
							<p>
								<a href="<%=request.getContextPath()+"/RemporteVenteServlet"%>?noArticle=${ench.getArticle().getNoArticle()}">${ench.getArticle().getNomArticle()}</a><br>
								Prix : ${ench.getArticle().getMiseAPrix()} points<br>
								Fin de l'enchère : ${ench.getArticle().getDateFinEncheres()}<br>
								Vendeur : <a href="<%=request.getContextPath()+"/PageProfilServlet"%>?pseudo=${art.getUtilisateur().getPseudo()}">${art.getUtilisateur().getPseudo()}</a>	
							</p>
						
						</c:if>
						
					</c:forEach>
					
				</c:if>
				
				</c:when>
				
				<%--si l'utilisateur n'a pas coché de sous categorie, on affiche tous les articles --%>
				<c:otherwise>
				
					<c:forEach var="art" items="${model.getLstArt()}">

					<p>					
						<c:if test="${art.getEtatVente().equals('AV')}">
							<a href="<%=request.getContextPath()+"/AccueilConnecteServlet"%>?noArticle=${art.getNoArticle()}">${art.getNomArticle()}</a><br>
						</c:if>
						<c:if test="${art.getEtatVente().equals('EC')}">
							<a href="<%=request.getContextPath()+"/EncherirServlet"%>?noArticle=${art.getNoArticle()}">${art.getNomArticle()} </a><br>
						</c:if>
						<c:if test="${art.getEtatVente().equals('VT')}">
							<a href="<%=request.getContextPath()+"/AcquisitionServlet"%>?noArticle=${art.getNoArticle()}">${art.getNomArticle()}</a><br>
						</c:if>
						Prix : ${art.getMiseAPrix()} points<br>
						Fin de l'enchère : ${art.getDateFinEncheres()}<br>
						Vendeur : <a href="<%=request.getContextPath()+"/PageProfilServlet"%>?pseudo=${art.getUtilisateur().getPseudo()}">${art.getUtilisateur().getPseudo()}</a>	
					</p>
						

				</c:forEach>

				</c:otherwise>
		</c:choose>

	</c:if>

	

</body>
<script src="accueilConnecteVue.js"></script>
</html>