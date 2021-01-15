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

//	Insetion d'une enchère dans la BDD après avoir validé l'enchère
	public void addEnchere(Enchere enchere) throws EnchereExceptionBLL {
		if (enchere.getNoEnchere() != null) {
			throw new EnchereExceptionBLL("Enchère déjà existante");
		}
		try {
			validerAddEnchere(enchere);
			enchereDAO.insert(enchere);
		} catch (EnchereDALException e) {
			e.printStackTrace();
			throw new EnchereExceptionBLL("Echec de addEnchere");
		}
	}

	public void validerAddEnchere(Enchere enchere) throws EnchereExceptionBLL {
		boolean valide = true;
		StringBuffer sb = new StringBuffer();

		if (enchere == null) {
			throw new EnchereExceptionBLL("Enchère null");
		}
		// Les attributs date et montant (>0) des enchères sont obligatoires
		if (enchere.getDateEnchere() == null) {
			sb.append("La date de l'enchère est obligatoire");
			valide = false;
		}
		if (enchere.getMontantEnchere() == null || enchere.getMontantEnchere() <= 0) {
			sb.append("Le montant de l'enchère est obligatoire et celui-ci doit être supérieur à 0");
			valide = false;
		}
		if (!valide) {
			throw new EnchereExceptionBLL(sb.toString());
		}

		//RG ; règle de gestion -> gestion des contraintes
		boolean RG1OK, RG2OK = false;

		// RG1 : mon prix proposé > tarif actuel de l'article
		if (enchere.getMontantEnchere() > enchere.getArticle().getMiseAPrix()) {
			RG1OK = true;
		} else {
			throw new EnchereExceptionBLL("Le prix proposé doit être supérieur au tarif actuel de l'enchère");
		}
		
		// RG2 : enchère acceptée si mon nombre de points ne devient pas négatif
		if (enchere.getUtilisateur().getCredit() >= enchere.getMontantEnchere()) {
			RG2OK = true;
		} else {
			throw new EnchereExceptionBLL("Enchère non valide : votre crédit n'est pas suffisant");
		}

	}

	@Override
	public void faireEnchere(Enchere enchere) throws EnchereExceptionBLL, EnchereDALException {
		List<Enchere> lstEnchere = new ArrayList<Enchere>();

		// Le montant de l'enchère devient la nouvelle mise à prix de l'article --> NON !! Ré-écrire
		lstEnchere.add(enchere);
		Integer meilleureOffre = enchere.getMontantEnchere();
		enchere.getArticle().setMiseAPrix(meilleureOffre);

		// Le crédit de points de l'utilisateur est débité du montant de l'enchère proposé
		Integer nouveauCredit = 0;
		nouveauCredit = enchere.getUtilisateur().getCredit() - enchere.getMontantEnchere();
		enchere.getUtilisateur().setCredit(nouveauCredit);
		enchereDAO.updateUtilisateur(enchere);

		
		System.out.println(lstEnchere);
		System.out.println(
				"Enchère acceptée. Votre nouveau crédit est de : " + enchere.getUtilisateur().getCredit() + " points");

		// Le meilleur enchérisseur précédent (s'il existe donc si la table enchère à au
		// moins 1 enchère) est re-crédité de son offre
		if (enchere.getNoEnchere() != null) {
			Integer AncienEncherisseur = lstEnchere.size() - 1;

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
