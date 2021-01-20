package fr.eni.ProjetEncheres.ihm.utilisateur;

import fr.eni.ProjetEncheres.bo.Utilisateur;

public class MonProfilModel {
	
	Utilisateur utilisateur= new Utilisateur();

	public MonProfilModel() {
		super();
	}

	public MonProfilModel(Utilisateur utilisateur) {
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
		builder.append("MonProfilModel [utilisateur=");
		builder.append(utilisateur);
		builder.append("]");
		return builder.toString();
	}
	
	
}
