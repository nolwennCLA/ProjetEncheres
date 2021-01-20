package fr.eni.ProjetEncheres.ihm.utilisateur;

import fr.eni.ProjetEncheres.bo.Utilisateur;

public class PageProfilModel {
	
	Utilisateur utilisateur = new Utilisateur();

	public PageProfilModel() {
		super();
	}

	public PageProfilModel(Utilisateur utilisateur) {
		super();
		this.utilisateur = utilisateur;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageProfilModel [utilisateur=");
		builder.append(utilisateur);
		builder.append("]");
		return builder.toString();
	}
	
	


}
