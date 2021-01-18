<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<h2>Mon profil</h2>

<form action="CreaCompteServlet" method="post">

<label for="pseudo">Pseudo:</label> <input type="text" name="pseudo" id="pseudo" required ><br>
<label for="prenom">Prénom:</label> <input type="text" name="prenom" id="prenom" required><br>
<label for="tel">Téléphone:</label> <input type="tel" name="tel" id="tel" required><br>
<label for="cpo">Code Postal:</label> <input type="number" name="cpo" id="cpo" min="01000" max="99999" required><br>
<label for="mdp">Mot de passe actuel:</label> <input type="password" name="mdpActuel" id="mdpActuel" required><br>
<label for="mdp">Nouveau mot de passe:</label> <input type="password" name="mdpActuel" id="mdpActuel" required><br>
Crédit: ${model.utilisateur.credt}

<label for="nom">Nom:</label><input type="text" name="nom" id="nom" required><br>
<label for="email">Email:</label><input type="email" name="email" id="email" required><br>
<label for="rue">Rue:</label> <input type="text" name="rue" id="rue" required><br>
<label for="ville">Ville:</label> <input type="text" name="ville" id="ville" required><br>
<label for="conf">Confirmation:</label> <input type="password" name="conf" id="conf" required><br>


<input type="submit" value="Enregistrer" name="Enregistrer">
<input type="submit" value="Supprimer mon compte" name="delete">

</form>


${message1}
${message2}
${message3}
</body>
</html>