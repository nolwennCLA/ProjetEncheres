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

<h2>Modifier mon profil</h2>


<form action="ModifProfilServlet" method="post">

<label for="pseudo">Pseudo:</label> <input type="text" name="pseudo" id="pseudo" value="${pseudo}" required ><br>
<label for="prenom">Prénom:</label> <input type="text" name="prenom" id="prenom" value="${prenom}" required><br>
<label for="tel">Téléphone:</label> <input type="tel" name="tel" id="tel" value="${telephone}" required><br>
<label for="cpo">Code Postal:</label> <input type="number" name="cpo" id="cpo" min="01000" max="99999" value="${codePostal}"required><br>
<label for="mdpActuel">Mot de passe actuel:</label> <input type="password" name="mdpActuel" id="mdpActuel" required><br>
<label for="nouveauMdp">Nouveau mot de passe:</label> <input type="password" name="nouveauMdp" id="nouveauMdp" required><br>

<label for="nom">Nom:</label><input type="text" name="nom" id="nom" value="${nom}" required><br>
<label for="email">Email:</label><input type="email" name="email" id="email" value="${email}"required><br>
<label for="rue">Rue:</label> <input type="text" name="rue" id="rue" value="${rue}"required><br>
<label for="ville">Ville:</label> <input type="text" name="ville" id="ville" value="${ville}"required><br>
<label for="conf">Confirmation:</label> <input type="password" name="conf" id="conf" required><br>


<input type="submit" value="Enregistrer" name="enregistrer">
<input type="submit" value="Supprimer mon compte" name="delete">

</form>


${message1}
${message2}
${message3}
${message4}
${message5}
${message6}
</body>
</html>