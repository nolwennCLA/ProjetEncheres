package fr.eni.ProjetEncheres.ihm.accueil;

import java.util.ArrayList;
import java.util.List;


import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Enchere;

public class AccueilConnecteModel {
	private List<Article> lstArt;
	private List<Enchere> lstEnch;
	private List<Enchere> lstMeilleuresOffres;
	private Enchere enchere;

	public AccueilConnecteModel() {
		lstArt = new ArrayList<>();
		lstEnch = new ArrayList<>();
		lstMeilleuresOffres = new ArrayList<>();
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

	public List<Enchere> getLstMeilleuresOffres() {
		return lstMeilleuresOffres;
	}

	public void setLstMeilleuresOffres(List<Enchere> lstMeilleuresOffres) {
		this.lstMeilleuresOffres = lstMeilleuresOffres;
	}
	
	public boolean meilleurOffrantParEnchere(Enchere enchere) {
		boolean meilleurOffrant = false;
		for(Enchere ench : this.getLstMeilleuresOffres()) {
			if(enchere.getUtilisateur().getNoUtilisateur() == ench.getUtilisateur().getNoUtilisateur()
			&& enchere.getNoEnchere() == ench.getNoEnchere()) {
				meilleurOffrant = true;
			}
		}
		return meilleurOffrant;
	}
	
	public boolean meilleurOffrantParArticle(Article article, Integer noSession) {
		boolean meilleurOffrant = false;
		for(Enchere ench : this.getLstMeilleuresOffres()) {
			if(ench.getArticle().getNoArticle() == article.getNoArticle()
			&& ench.getUtilisateur().getNoUtilisateur() == noSession) {
				meilleurOffrant = true;
			}
		}
		return meilleurOffrant;
	}
	
	
	
	
	
	
	
	
	
}
