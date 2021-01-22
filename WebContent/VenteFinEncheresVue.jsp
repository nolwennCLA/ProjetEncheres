<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ma vente fin enchères</title>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<c:if test="${afficheEnchere == 1}">
		<h4>${pseudoGagnant} a remporté l'enchère</h4>
	</c:if>
	<c:if test="${afficheEnchere == 0}">
		<h4>L'article n' reçu aucune enchère</h4>
	</c:if>
	
	
	<h4>Article : ${nomArticle}</h4>
	
	
	<c:if test="${afficheEnchere == 1}">
		<table>
			<tr>
				<td>Description : </td><td>${description}</td>
			</tr>
			<tr>
				<td>Meilleure offre : </td><td>${meilleureOffre} pts par ${pseudoGagnant}</td>
			</tr>
			<tr>
				<td>Mise à prix : </td><td>${miseAPrix} points</td>
			</tr>
			<tr>
				<td>Fin de l'enchère : </td><td>${dateFinEncheres}</td>
			</tr>
			<tr>
				<td rowspan="2">Retrait : </td><td>${rue}</td>								
			</tr>
			<tr>
				<td>${codePostal} ${ville}</td>
			</tr>
			<tr>
				<td>Vendeur : </td><td>${vendeur}</td>	
			</tr>
		</table>
		
	</c:if>
	
	<c:if test="${afficheEnchere == 0}">
		<table>
			<tr>
				<td>Description : </td><td>${description}</td>
			</tr>
			<tr>
				<td>Meilleure offre : </td><td>Pas d'offre sur cette enchère</td>
			</tr>
			<tr>
				<td>Mise à prix : </td><td>${miseAPrix} points</td>
			</tr>
			<tr>
				<td>Fin de l'enchère : </td><td>${dateFinEncheres}</td>
			</tr>
			<tr>
				<td>Vendeur : </td><td>${vendeur}</td>	
			</tr>
		</table>
	</c:if>
	
	

</body>
</html>