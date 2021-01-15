<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Creation du compte utilisateur</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<h2>Mon profil</h2>

<form action="CreaCompteServlet" method="post">

<label for="pseudo">Pseudo:</label> <input type="text" name="pseudo" id="pseudo"><br>
<label for="prenom">Prénom:</label> <input type="text" name="prenom" id="prenom"><br>
<label for="tel">Téléphone:</label> <input type="tel" name="tel" id="tel"><br>
<label for="cpo">Rue:</label> <input type="number" name="cpo" id="cpo" min="5" max="5"><br>
<label for="mdp">Mot de passe:</label> <input type="text" name="mdp" id="mdp"><br>

</form>

<form action="CreaCompteServlet" method="post">

<label for="nom">Nom:</label><input type="text" name="nom" id="nom"><br>
<label for="email">Email:</label><input type="email" name="email" id="email"><br>
<label for="rue">Rue:</label> <input type="text" name="rue" id="rue"><br>
<label for="ville">Ville:</label> <input type="text" name="ville" id="ville"><br>
<label for="conf">Confirmation:</label> <input type="text" name="conf" id="conf"><br>

</form>

<form action="CreaCompteServlet" method="post">
<input type="submit" value="Créer">
<input type="submit" value="Annuler">


</body>
</html>