package fr.eni.ProjetEncheres.bll.enchere;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDALException;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDAO;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public class EnchereManagerImpl implements EnchereManager {

	EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();

//	Utilisateur connecté / non connecté : lister les enchères en cours
	public List<Enchere> getListEnchere()
			throws EnchereExceptionBLL, UtilisateurExceptionBLL, BLL_ArticleException, DAL_ArticleException,
			BLL_RetraitException, DAL_RetraitException, BLL_CategorieException, DAL_CategorieException {

		List<Enchere> lstEnchere = new ArrayList<Enchere>();
		try {
			lstEnchere = enchereDAO.selectAll();
		} catch (EnchereDALException e) {
			e.printStackTrace();
			throw new EnchereExceptionBLL("Sélection de la liste d'enchères impossible");
		}
		return lstEnchere;

	}

//	Utilisateur non connecté : liste des enchères par catégorie
	@Override
	public List<Enchere> getListEnchereParCategorie(Categorie libelle)
			throws EnchereExceptionBLL, UtilisateurExceptionBLL, BLL_ArticleException, DAL_ArticleException,
			BLL_RetraitException, DAL_RetraitException, BLL_CategorieException, DAL_CategorieException {
		List<Enchere> lstEnchere = new ArrayList<Enchere>();
		List<Enchere> lstEnchereCategorie = new ArrayList<Enchere>();
//		TODO : vérifier que je peux griser le try/catch (pour éviter la répétition) et appeler la méthode getListEnchere()
		try {
			lstEnchere = enchereDAO.selectAll();
		} catch (EnchereDALException e) {
			e.printStackTrace();
			throw new EnchereExceptionBLL("Sélection de la liste d'enchères impossible");
		}
//		getListEnchere();
		for (Enchere e : lstEnchere) {
			if (e.getArticle().getCategorie().equals(libelle)) {
				lstEnchereCategorie.add(e);
			}
		}
		return lstEnchereCategorie;
	}

//	Utilisateur non connecté : liste des enchères par article
	@Override
	public List<Enchere> getListEnchereParArticle(Article article)
			throws EnchereExceptionBLL, UtilisateurExceptionBLL, BLL_ArticleException, DAL_ArticleException,
			BLL_RetraitException, DAL_RetraitException, BLL_CategorieException, DAL_CategorieException {
		List<Enchere> lstEnchere = new ArrayList<Enchere>();
		List<Enchere> lstEnchereArticle = new ArrayList<Enchere>();
//		TODO : vérifier que je peux griser le try/catch (pour éviter la répétition) et appeler la méthode getListEnchere()
//		try {
//			lstEnchere = enchereDAO.selectAll();
//		} catch (EnchereDALException e) {
//			e.printStackTrace();
//			throw new EnchereExceptionBLL("Sélection de la liste d'enchères impossible");
//		}
		getListEnchere();
		// TODO : rajouter la contrainte : l'article est affiché si il contient le
		// critère saisi --> Dans une autre couche ? ihm ?
		for (Enchere e : lstEnchere) {
			if (e.getArticle().getNomArticle().equals(article)) {
				lstEnchereArticle.add(e);
			}
		}
		return lstEnchereArticle;
	}

	//Utilisateur connecté : liste des enchères ouvertes
	@Override
	public List<Enchere> getListEnchereOuvertes() throws EnchereExceptionBLL {
		// TODO Auto-generated method stub
		return null;
	}

	//Utilisateur connecté : liste de mes enchères en cours
	@Override
	public List<Enchere> getListMesEnchereEnCours() throws EnchereExceptionBLL {
		// TODO Auto-generated method stub
		return null;
	}

	//Utilisateur connecté : liste de mes enchères gagnées
	@Override
	public List<Enchere> getListMesEnchereGagnees() throws EnchereExceptionBLL {
		// TODO Auto-generated method stub
		return null;
	}

	//Utilisateur connecté : liste de mes ventes en cours
	@Override
	public List<Enchere> getListMesVentesEnCours() throws EnchereExceptionBLL {
		// TODO Auto-generated method stub
		return null;
	}

	//Utilisateur connecté : liste de mes ventes non débutées
	@Override
	public List<Enchere> getListMesVentesNonDebutees() throws EnchereExceptionBLL {
		// TODO Auto-generated method stub
		return null;
	}

	//Utilisateur connecté : liste de mes ventes terminées
	@Override
	public List<Enchere> getListMesVentesTerminees() throws EnchereExceptionBLL {
		// TODO Auto-generated method stub
		return null;
	}

//	Insertion d'une enchère dans la BDD après avoir validé l'enchère (voir méthode validerEnchere)
	public void addEnchere(Enchere enchere) throws EnchereExceptionBLL, EnchereDALException {
		List<Enchere> lstEnchere = new ArrayList<Enchere>();

		// Vérifie que l'enchère n'existe pas déjà dans la BDD
		if (enchere.getNoEnchere() != null) {
			throw new EnchereExceptionBLL("Enchère déjà existante");
		}

		// Teste la validation de l'enchère, si ok insertion dans la BDD
		try {
			validerEnchere(enchere);
			enchereDAO.insert(enchere);
		} catch (EnchereDALException e) {
			e.printStackTrace();
			throw new EnchereExceptionBLL("Echec de addEnchere");
		}

		// Le crédit de points de l'enchérisseur est débité du montant de l'enchère
		// proposé
		Integer nouveauCredit = 0;
		nouveauCredit = enchere.getUtilisateur().getCredit() - enchere.getMontantEnchere();
		enchere.getUtilisateur().setCredit(nouveauCredit);
		enchereDAO.updateUtilisateur(enchere);

		// L'enchère rentre alors dans une liste
		lstEnchere.add(enchere);
		System.out.println(lstEnchere);

		// Le meilleur enchérisseur précédent (s'il existe donc si la table enchère à au
		// moins 1 enchère) est re-crédité de son offre
		if (enchere.getNoEnchere() != null) {
			Integer AncienEncherisseur = lstEnchere.size() - 1;

		}
	}

	// Vérification des contraintes et validation si OK
	public void validerEnchere(Enchere enchere) throws EnchereExceptionBLL {
		boolean valide = true;
		StringBuffer sb = new StringBuffer();

		// vérification de la validité de l'enchère
		if (enchere == null) {
			throw new EnchereExceptionBLL("Enchère null");
		}

		// La date de l'enchère est obligatoire
		if (enchere.getDateEnchere() == null) {
			sb.append("La date de l'enchère est obligatoire");
			valide = false;
		}

//		// La date de l'enchère >= dateDebutEnchere et <= dateFinEnchere
//		if (enchere.getDateEnchere().before(enchere.getArticle().getDateDebutEncheres())) {
//			sb.append("La date de l'enchère doit être après la dateDébutEnchère");
//			valide = false;
//		}

////		// La date de l'enchère >= dateDebutEnchere et <= dateFinEnchere
//		if (enchere.getDateEnchere().after(enchere.getArticle().getDateFinEncheres())) {
//			sb.append("La date de l'enchère doit être avant la date de fin d'enchère");
//			valide = false;
//		}

		 // La date de l'enchère = dateDebutEnchere et = dateFinEnchere : OK -> test de cette méthode pour égalité 
// 		if (enchere.getDateEnchere().equals((enchere.getArticle().getDateFinEncheres()))
// 				|| enchere.getDateEnchere().equals((enchere.getArticle().getDateDebutEncheres()))) {
// 			sb.append("Vous ne pouvez faire une enchère qu'entre les dates de début et de fin de vente de l'article");
// 			valide = false;
// 		}
		
//		// La date de l'enchère >= dateDebutEnchere et <= dateFinEnchere spécifié dans l'article en vente
//		if (enchere.getDateEnchere().after(enchere.getArticle().getDateFinEncheres())
//				&& enchere.getDateEnchere().before(enchere.getArticle().getDateDebutEncheres())) {
//			sb.append("La date de l'enchère doit être comprise entre les dates de début et de fin de vente de l'article");
//			valide = false;
//		}

		// Le montant de l'enchère n'est ni null ni inférieure à 0
		if (enchere.getMontantEnchere() == null || enchere.getMontantEnchere() <= 0) {
			sb.append("Le montant de l'enchère est obligatoire et celui-ci doit être supérieur à 0");
			valide = false;
		}
		
		// Le montant de l'enchère proposée > mise à prix de l'article (1ère enchère)
		if (enchere.getMontantEnchere() < enchere.getArticle().getMiseAPrix()) {
			sb.append("Le montant proposé doit être supérieur au tarif de l'enchère");
			valide = false;
		}
		Integer PremiereEnchere = enchere.getMontantEnchere();
		System.out.println(PremiereEnchere);

		//Le montant de l'enchère > montant de la précédente enchère (2ème et + enchère)
		if (enchere.getMontantEnchere() <= PremiereEnchere) {
			sb.append("Le montant proposé doit être supérieur à la dernière enchère");
			valide = false;
		}

		// L'enchère est acceptée si mon nombre de points ne devient pas négatif
		// TODO : voir avec Nolwenn et Vincent pourquoi avec le signe >= ça marche pas
		// !!
		if (enchere.getUtilisateur().getCredit() < enchere.getMontantEnchere()) {
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
	public Enchere selectionnerEnchere(Integer id) throws EnchereExceptionBLL {
		Enchere enchere = new Enchere();
		try {
			enchere = enchereDAO.selectById(id);
		} catch (EnchereDALException e) {
			e.printStackTrace();
			throw new EnchereExceptionBLL("selection de l'enchère par id impossible");
		}
		return enchere;
	}

	@Override
	public void deleteEnchere(Integer id) throws EnchereExceptionBLL {
		try {
			enchereDAO.delete(id);
		} catch (EnchereDALException e) {
			e.printStackTrace();
			throw new EnchereExceptionBLL("suppression de l'enchère par id impossible");
		}

	}

}
