<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>


<table>
<tr>
<td>Pseudo:</td><td> ${pseudo} </td>
</tr>
<tr>
<td>Nom: </td><td>${nom} </td>
</tr>
<tr>
<td>Prénom: </td><td>${prenom}</td>
</tr>
<tr>
<td>Email: </td><td>${email}</td>
</tr>
<tr>
<td>Téléphone: </td> <td>${telephone}</td>
 </tr>
 <tr>
<td>Rue: </td><td>${rue}</td>
</tr>
<tr>
<td>Code postal: </td><td>${codePostal}</td>
</tr>
<tr>
<td>Ville: </td><td>${ville}</td>
</tr>
</table>


<form action="ModifProfilServlet" method="post">
<input type="submit" value="Modifier">
</form>

</body>
</html>