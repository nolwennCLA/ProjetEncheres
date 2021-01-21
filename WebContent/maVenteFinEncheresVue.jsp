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

	<h4>${pseudoGagnant} a remporté l'enchère</h4>
	
	<h4>Le nom de l'article : ${nomArticle}</h4>
	
	<form action="MaVenteFinEncheres" method="GET">
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
											<td>${codePostal} ${ville}</td>
		</tr>
		<tr>
			<td>Vendeur : </td><td>${vendeur}</td>	
		</tr>
	</table>
		<input type="submit" name="bouton" value="Retrait effectué">
	</form>

</body>
</html>