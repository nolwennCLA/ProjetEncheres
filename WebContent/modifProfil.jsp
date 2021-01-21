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


<table>
<tr>
<td><label for="pseudo">Pseudo :</label></td>
<td> <input type="text" name="pseudo" id="pseudo" value="${pseudo}" required  ></td>
<td><label for="nom">Nom:</label></td>
<td><input type="text" name="nom" id="nom" value="${nom}" required></td>
</tr>

<tr>
<td><label for="prenom">Prénom : </label> </td>
<td><input type="text" name="prenom" id="prenom" value="${prenom}" required></td>
<td><label for="email">Email : </label></td>
<td><input type="email" name="email" id="email" value="${email}"required></td>
</tr>

<tr>
<td><label for="tel">Téléphone : </label> </td>
<td><input type="tel" name="tel" id="tel" value="${telephone}" required></td>
<td><label for="rue">Rue : </label></td>
<td><input type="text" name="rue" id="rue" value="${rue}"required></td>
</tr>

<tr>
<td><label for="cpo">Code Postal : </label></td>
<td><input type="number" name="cpo" id="cpo" min="01000" max="99999" value="${codePostal}"required></td>
<td><label for="ville">Ville : </label></td> 
<td><input type="text" name="ville" id="ville" value="${ville}"required></td>
</tr>

<tr>
<td><label for="mdp">Mot de passe actuel: </label></td>
<td><input type="password" name="mdpActuel" id="mdpActuel" required></td>
<td></td>
<td></td>
</tr>

<tr>
<td><label for="nouveauMdp">Nouveau mot de passe:</label></td>
<td><input type="password" name="nouveauMdp" id="nouveauMdp"></td>
<td><label for="conf">Confirmation : </label></td>
<td> <input type="password" name="conf" id="conf"></td>
</tr>

crédit: 

<table>
<tr>
<td><input type="submit" value="Enregistrer" name="enregistrer"></td>
<td><input type="submit" value="Supprimer mon compte" name="delete"></td>
</tr>

</table>
</form>


${message1}
${message2}
${message3}
${message4}
${message5}
${message6}
</body>
</html>