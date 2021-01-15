package fr.eni.ProjetEncheres.ihm.utilisateur;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Utilisateur;

public class CreaCompteModel {

	List<Utilisateur>listUtilisateur= new ArrayList<>();

	public CreaCompteModel() {
		super();
	}

	public CreaCompteModel(List<Utilisateur> listUtilisateur) {
		super();
		this.listUtilisateur = listUtilisateur;
	}

	public List<Utilisateur> getListUtilisateur() {
		return listUtilisateur;
	}

	public void setListUtilisateur(List<Utilisateur> listUtilisateur) {
		this.listUtilisateur = listUtilisateur;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreaCompteModel [listUtilisateur=");
		builder.append(listUtilisateur);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
