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
<td>Pseudo:</td><td> ${model.pseudo}  </td>
</tr>
<tr>
<td>Nom: </td><td>   </td>
</tr>
<tr>
<td>Prénom: </td><td>	</td>
</tr>
<tr>
<td>Email: </td><td>	</td>
</tr>
<tr>
<td>Téléphone: </td> <td>	</td>
 </tr>
 <tr>
<td>Rue: </td><td>	</td>
</tr>
<tr>
<td>Code postal: </td><td>	</td>
</tr>
<tr>
<td>Ville: </td><td>	</td>
</tr>
</table>



</body>
</html>