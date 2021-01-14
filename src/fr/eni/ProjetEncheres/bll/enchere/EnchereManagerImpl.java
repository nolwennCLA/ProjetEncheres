package fr.eni.ProjetEncheres.bll.enchere;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.bo.Utilisateur;

public class EnchereManagerImpl implements EnchereManager {

	List<Enchere> lstEncheres = new ArrayList<Enchere>();

//	Utilisateur non connecté : lister les enchères en cours
	@Override
	public List<Enchere> getListEnchereNonConnect(Categorie categorie, Article article) {
		// TODO Auto-generated method stub
		return null;
	}

//	 Utilisateur connecté : lister les enchères en cours, celles auxquelles je
//	 participe (où j'ai fais au moins une offre), mes enchères gagnées
	@Override
	public List<Enchere> getListEnchereConnect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void faireEnchere(Enchere enchere) throws EnchereException {

		boolean RG1OK, RG2OK = false;

		do {
			// RG1 : mon prix proposé > tarif actuel de l'article
			if (enchere.getMontantEnchere() > enchere.getArticle().getMiseAPrix()) {
				RG1OK = true;
			} else {
				throw new EnchereException("Le prix proposé doit être supérieur au tarif actuel de l'enchère");
			}

			// RG2 : enchère acceptée si mon nombre de points ne devient pas négatif
			if (enchere.getUtilisateur().getCredit() >= enchere.getMontantEnchere()) {
				RG2OK = true;
			} else {
				throw new EnchereException("Enchère non valide : votre crédit n'est pas suffisant");
			}
		} while (!RG1OK && !RG2OK);
		
		//Si l'enchère est validée : le montant de l'enchère devient la nouvelle mise à prix de l'article
		Integer nouvelleMiseAPrix = enchere.getMontantEnchere();
		enchere.getArticle().setMiseAPrix(nouvelleMiseAPrix);

		//Le crédit de points de l'utilisateur est débité du montant de l'enchère proposé
		Integer nouveauCredit = 0;
		nouveauCredit = enchere.getUtilisateur().getCredit() - enchere.getMontantEnchere();
		enchere.getUtilisateur().setCredit(nouveauCredit);
		System.out.println("Enchère acceptée. Votre nouveau crédit est de : " + enchere.getUtilisateur().getCredit() + " points");
		
		//Le meilleur enchérisseur précédent (s'il existe donc si la table enchère à au moins 1 enchère) est re-crédité de son offre
		if (enchere.getNoEnchere() != null) {
			
		}
		
		
		
		}

//	En tant qu'enchérisseur, je deviens acquéreur si au terme de l'enchère j'ai
//	proposé l'enchère la plus haute
	@Override
	public void remporteVente(Enchere enchere) throws EnchereException {
		
		

	}

}
