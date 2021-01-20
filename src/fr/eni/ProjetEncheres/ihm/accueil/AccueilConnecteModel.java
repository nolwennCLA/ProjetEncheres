package fr.eni.ProjetEncheres.ihm.accueil;

import java.util.ArrayList;
import java.util.List;


import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Enchere;

public class AccueilConnecteModel {
	private List<Article> lstArt;
	private List<Enchere> lstEnch;

	public AccueilConnecteModel() {
		lstArt = new ArrayList<>();
		lstEnch = new ArrayList<>();
	}

	public List<Article> getLstArt() {
		return lstArt;
	}

	public void setLstArt(List<Article> lstArt) {
		this.lstArt = lstArt;
	}

	public List<Enchere> getLstEnch() {
		return lstEnch;
	}

	public void setLstEnch(List<Enchere> lstEnch) {
		this.lstEnch = lstEnch;
	}

	
	
	
	
	
	
	
}
