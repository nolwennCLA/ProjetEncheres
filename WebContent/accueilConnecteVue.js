/**
 * 
 */



document.getElementById("achats").addEventListener("click", function(){
	
	document.getElementById("encheresOuvertes").removeAttribute("disabled");
	document.getElementById("mesEncheres").removeAttribute("disabled");
	document.getElementById("encheresRemportees").removeAttribute("disabled");
	
	document.getElementById("ventesEnCours").setAttribute("disabled", "disabled");
	document.getElementById("ventesNonDebutees").setAttribute("disabled", "disabled");
	document.getElementById("ventesTerminees").setAttribute("disabled", "disabled")
	
});

document.getElementById("ventes").addEventListener("click", function(){

	document.getElementById("encheresOuvertes").setAttribute("disabled", "disabled");
	document.getElementById("mesEncheres").setAttribute("disabled", "disabled");
	document.getElementById("encheresRemportees").setAttribute("disabled", "disabled");
	
	document.getElementById("ventesEnCours").removeAttribute("disabled", "disabled");
	document.getElementById("ventesNonDebutees").removeAttribute("disabled", "disabled");
	document.getElementById("ventesTerminees").removeAttribute("disabled", "disabled")


});