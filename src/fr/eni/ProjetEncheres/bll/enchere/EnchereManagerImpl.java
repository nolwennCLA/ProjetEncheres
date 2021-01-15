package fr.eni.ProjetEncheres.bll.enchere;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDALException;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDAO;

public class EnchereManagerImpl implements EnchereManager {

	EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();

//	Utilisateur connecté / non connecté : lister les enchères en cours
	public List<Enchere> getListEnchere() throws EnchereExceptionBLL {

		List<Enchere> lstEnchere = new ArrayList<Enchere>();
		try {
			lstEnchere = enchereDAO.selectAll();
		} catch (EnchereDALException e) {
			e.printStackTrace();
			throw new EnchereExceptionBLL("Sélection de la liste d'enchères impossible");
		}
		return lstEnchere;

	}

	@Override
	public List<Enchere> getListEnchereParCategorie(Categorie categorie) throws EnchereExceptionBLL {
		List<Enchere> lstEnchere = new ArrayList<Enchere>();
		List<Enchere> lstEnchereCategorie = new ArrayList<Enchere>();
//		try {
//			lstEnchere = enchereDAO.selectAll();
//		} catch (EnchereDALException e) {
//			e.printStackTrace();
//			throw new EnchereExceptionBLL("Sélection de la liste d'enchères impossible");
//		}
		getListEnchere();
		for (Enchere e : lstEnchere) {
			if (e.getArticle().getCategorie().equals(categorie)) {
				lstEnchereCategorie.add(e);
			}
		}
		return lstEnchereCategorie;
	}

	@Override
	public List<Enchere> getListEnchereParArticle(Article article) throws EnchereExceptionBLL {
		List<Enchere> lstEnchere = new ArrayList<Enchere>();
		List<Enchere> lstEnchereArticle = new ArrayList<Enchere>();
//		try {
//			lstEnchere = enchereDAO.selectAll();
//		} catch (EnchereDALException e) {
//			e.printStackTrace();
//			throw new EnchereExceptionBLL("Sélection de la liste d'enchères impossible");
//		}
		getListEnchere();
		for (Enchere e : lstEnchere) {
			if (e.getArticle().getNomArticle().equals(article)) {
				lstEnchereArticle.add(e);
			}
		}
		return lstEnchereArticle;
	}

//	Insetion d'une enchère dans la BDD après avoir validé l'enchère (voir méthode validerEnchere)
	public void addEnchere(Enchere enchere) throws EnchereExceptionBLL, EnchereDALException {
		List<Enchere> lstEnchere = new ArrayList<Enchere>();

		// Vérifie que l'enchère n'existe pas déjà dans la BDD
		if (enchere.getNoEnchere() != null) {
			throw new EnchereExceptionBLL("Enchère déjà existante");
		}

		// Test la validation de l'enchère, si ok insertion dans la BDD
		try {
			validerEnchere(enchere);
			enchereDAO.insert(enchere);
		} catch (EnchereDALException e) {
			e.printStackTrace();
			throw new EnchereExceptionBLL("Echec de addEnchere");
		}

		// L'enchère rentre alors dans une liste	
		lstEnchere.add(enchere);
		
		// Le crédit de points de l'enchérisseur est débité du montant de l'enchère proposé
		Integer nouveauCredit = 0;
		nouveauCredit = enchere.getUtilisateur().getCredit() - enchere.getMontantEnchere();
		enchere.getUtilisateur().setCredit(nouveauCredit);
		enchereDAO.updateUtilisateur(enchere);
		
		Integer meilleureOffre = enchere.getMontantEnchere();
		enchere.getArticle().setMiseAPrix(meilleureOffre);


		System.out.println(lstEnchere);
		System.out.println(
				"Enchère acceptée. Votre nouveau crédit est de : " + enchere.getUtilisateur().getCredit() + " points");

		// Le meilleur enchérisseur précédent (s'il existe donc si la table enchère à au
		// moins 1 enchère) est re-crédité de son offre
		if (enchere.getNoEnchere() != null) {
			Integer AncienEncherisseur = lstEnchere.size() - 1;

		}
	}

	public void validerEnchere(Enchere enchere) throws EnchereExceptionBLL {
		boolean valide = true;
		StringBuffer sb = new StringBuffer();

		if (enchere == null) {
			throw new EnchereExceptionBLL("Enchère null");
		}

		// La date de l'enchère est obligatoire
		if (enchere.getDateEnchere() == null) {
			sb.append("La date de l'enchère est obligatoire");
			valide = false;
		}
		// La date de l'enchère >= dateDebutEnchere et <= dateFinEnchere
		if (enchere.getDateEnchere().before(enchere.getArticle().getDateDebutEncheres())
				|| enchere.getDateEnchere().after(enchere.getArticle().getDateFinEncheres())) {
			sb.append(
					"La date de l'enchère doit être comprise entre la date de début et la date de fin spécifié dans l'article en vente");
			valide = false;
		}

		// Le montant de l'enchère n'est ni null ni inférieure à 0
		if (enchere.getMontantEnchere() == null || enchere.getMontantEnchere() <= 0) {
			sb.append("Le montant de l'enchère est obligatoire et celui-ci doit être supérieur à 0");
			valide = false;
		}

		// Mon prix proposé > mise à prix de l'article
		if (enchere.getMontantEnchere() < enchere.getArticle().getMiseAPrix()) {
			sb.append("Le prix proposé doit être supérieur au tarif actuel de l'enchère");
			valide = false;
		}

		// L'enchère est acceptée si mon nombre de points ne devient pas négatif
		if (enchere.getUtilisateur().getCredit() >= enchere.getMontantEnchere()) {
			sb.append("Enchère non valide : votre crédit n'est pas suffisant");
			valide = false;
		}

		if (!valide) {
			throw new EnchereExceptionBLL(sb.toString());
		}
	}

//	En tant qu'enchérisseur, je deviens acquéreur si au terme de l'enchère j'ai
//	proposé l'enchère la plus haute
	@Override
	public void remporteVente(Enchere enchere) throws EnchereExceptionBLL {

	}

	@Override
	public Enchere selectionneEnchere(Integer id) throws EnchereExceptionBLL {
		Enchere enchere = new Enchere();
		try {
			enchere = enchereDAO.selectById(id);
		} catch (EnchereDALException e) {
			e.printStackTrace();
			throw new EnchereExceptionBLL("selection de l'enchère par id impossible");
		}
		return enchere;
	}

}
