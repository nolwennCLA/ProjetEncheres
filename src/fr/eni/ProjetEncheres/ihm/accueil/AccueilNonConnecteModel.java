package fr.eni.ProjetEncheres.ihm.accueil;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Article;

public class AccueilNonConnecteModel {
	private List<Article> lstArt;

	public AccueilNonConnecteModel() {
		lstArt = new ArrayList<>();
	}

	public List<Article> getLstArt() {
		return lstArt;
	}

	public void setLstArt(List<Article> lstArt) {
		this.lstArt = lstArt;
	}
	
	
	
	
}
