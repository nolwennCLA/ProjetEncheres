<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<table>
<tr>
<td><label for="pseudo">Pseudo :</label></td>
<td> <input type="text" name="pseudo" id="pseudo" required ></td>
<td><label for="nom">Nom:</label></td>
<td><input type="text" name="nom" id="nom" required></td>

</tr>
<tr>
<td><label for="prenom">Prénom : </label> </td>
<td><input type="text" name="prenom" id="prenom" required></td>
<td><label for="tel">Téléphone : </label> </td>
<td><input type="tel" name="tel" id="tel" required></td>
</tr>
<tr>
<td><label for="cpo">Code Postal : </label></td>
<td><input type="number" name="cpo" id="cpo" min="01000" max="99999" required></td>
<td><label for="mdp">Mot de passe : </label></td>
<td><input type="password" name="mdp" id="mdp" required></td>
</tr>
<tr>
<td><label for="email">Email : </label></td>
<td><input type="email" name="email" id="email" required></td>
<td><label for="rue">Rue : </label></td>
<td><input type="text" name="rue" id="rue" required></td>
</tr>
<tr>
<td><label for="ville">Ville : </label></td> 
<td><input type="text" name="ville" id="ville" required></td>
<td><label for="conf">Confirmation : </label></td>
<td> <input type="password" name="conf" id="conf" required></td>
</tr>

</table>

<table>
<tr>
<td><input type="submit" value="Créer" name="creer"></td>
</table>
</form>
<form><input type="submit" value="Annuler" name="annuler"></form>





${message1}
${message2}


</body>
</html>