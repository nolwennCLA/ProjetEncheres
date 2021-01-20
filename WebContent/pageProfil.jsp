<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page profil</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<table>
<tr>
<td>Pseudo:</td><td> ${model.utilisateur.pseudo}  </td>
</tr>
<tr>
<td>Nom: </td><td> ${model.utilisateur.nom}  </td>
</tr>
<tr>
<td>Prénom: </td><td> ${model.utilisateur.prenom}	</td>
</tr>
<tr>
<td>Email: </td><td> ${model.utilisateur.email}</td>
</tr>
<tr>
<td>Téléphone: </td> <td>${model.utilisateur.telephone}</td>
 </tr>
 <tr>
<td>Rue: </td><td>${model.utilisateur.rue}</td>
</tr>
<tr>
<td>Code postal: </td><td>${model.utilisateur.codePostal}</td>
</tr>
<tr>
<td>Ville: </td><td>${model.utilisateur.ville}</td>
</tr>
</table>



</body>
</html>