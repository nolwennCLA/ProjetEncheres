package fr.eni.ProjetEncheres.ihm.utilisateur;

import fr.eni.ProjetEncheres.bo.Utilisateur;

public class PageProfilModel {
	

	private String pseudo;
	

	public PageProfilModel() {
		super();
	}

	public PageProfilModel(String pseudo) {
		super();
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	

}
