package fr.eni.ProjetEncheres.ihm.utilisateur;

import fr.eni.ProjetEncheres.bo.Utilisateur;

public class TestModel {

	Utilisateur utilisateur= new Utilisateur();

	public TestModel() {
		super();
	}

	public TestModel(Utilisateur utilisateur) {
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
		builder.append("TestModel [utilisateur=");
		builder.append(utilisateur);
		builder.append("]");
		return builder.toString();
	}
	
	
}
