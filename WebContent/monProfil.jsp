<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon profil</title>
</head>
<body>

${identifiant}

<p>
Pseudo: ${model.utilisateur.pseudo} <br>
Nom: ${model.utilisateur.nom} <br>
Prénom: ${model.utilisateur.prenom}<br>
Email: ${model.utilisateur.email}<br>
Téléphone: ${model.utilisateur.telephone}<br>
Rue: ${model.utilisateur.rue}<br>
Code postal: ${model.utilisateur.codePostal}<br>
Ville: ${model.utilisateur.ville}<br>
</p>

<form action="ModifProfilServlet" method="post">
<input type="submit" value="Modifier">
</form>



</body>
</html>