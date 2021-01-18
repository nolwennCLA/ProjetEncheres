package fr.eni.ProjetEncheres.bll.enchere;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.article.DAOArticle;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDALException;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDAO;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public class EnchereManagerImpl implements EnchereManager {

	EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
	ArticleManager am = ArticleManagerSing.getInstance();
	DAOArticle articleDAO = DAOFactory.getDAOArticle();

	// Vérification des contraintes et validation si OK
	public void validerEnchere(Enchere enchere) throws EnchereExceptionBLL, EnchereDALException, BLL_CategorieException,
			DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException,
			BLL_ArticleException, UtilisateurExceptionBLL {
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

		// La date de l'enchère = dateDebutEnchere et = dateFinEnchere : true -> test de
		// cette méthode pour égalité de date
//		if (enchere.getDateEnchere().equals((enchere.getArticle().getDateFinEncheres()))
//				|| enchere.getDateEnchere().equals((enchere.getArticle().getDateDebutEncheres()))) {
//			sb.append("Vous ne pouvez faire une enchère qu'entre les dates de début et de fin de vente de l'article");
//			valide = false;
//		}

		// La date de l'enchère >= dateDebutEnchere et <= dateFinEnchere spécifié dans
		// l'article en vente
		if (enchere.getDateEnchere().after(enchere.getArticle().getDateFinEncheres())
				&& enchere.getDateEnchere().before(enchere.getArticle().getDateDebutEncheres())) {
			sb.append(
					"La date de l'enchère doit être comprise entre les dates de début et de fin de vente de l'article");
			valide = false;
		}

		// Le montant de l'enchère n'est ni null ni inférieure à 0
		if (enchere.getMontantEnchere() == null || enchere.getMontantEnchere() <= 0) {
			sb.append("Le montant de l'enchère est obligatoire et celui-ci doit être supérieur à 0");
			valide = false;
		}

		// Le montant de l'enchère proposée doit être supérieure à la mise à prix de
		// l'article (1ère enchère)
		if (enchere.getMontantEnchere() < enchere.getArticle().getMiseAPrix()) {
			sb.append("Le montant proposé doit être supérieur à la mise à prix de l'article");
			valide = false;
		}

		// Le montant de l'enchère doit être supérieure à la meilleure offre actuelle
		// (2ème enchère et +)
		// récupération de l'offre la plus élevée de la colonne montantEnchere
		try {
			Integer meilleurOffre = 0;
			enchereDAO.selectMeilleurOffre(enchere.getArticle().getNoArticle());
//			meilleurOffre = 
		} catch (EnchereDALException e) {
			e.printStackTrace();
			throw new EnchereExceptionBLL("Echec de la méthode selectMeilleurOffre de la EnchereDAOImpl");
		}
		// comparaison de la plus haute enchère en BDD avec le montant de l'enchère
		// souhaitée : comment faire appel à la valeur qui sort du try/catch précédent ?
//		if (enchere.getMontantEnchere() < ) {
//			sb.append("Le montant proposé doit être supérieur à la meilleure offre");
//			valide = false;
//		}

		// L'enchère est acceptée si mon nombre de points ne devient pas négatif
		if (enchere.getUtilisateur().getCredit() < enchere.getMontantEnchere()) {
			sb.append("Enchère non valide : votre crédit n'est pas suffisant");
			valide = false;
		}

		if (!valide) {
			throw new EnchereExceptionBLL(sb.toString());
		}
	}

//	Insertion d'une enchère dans la BDD après avoir validé l'enchère (voir méthode validerEnchere)
	public void addEnchere(Enchere enchere) throws EnchereExceptionBLL, EnchereDALException, BLL_CategorieException,
			DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException,
			BLL_ArticleException, UtilisateurExceptionBLL {
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

		// Appelle de la méthode de gestion de crédits
		gestionCredit(enchere);

	}

	@Override
	public void gestionCredit(Enchere enchere) throws EnchereExceptionBLL, EnchereDALException {

		// Le crédit de points de l'enchérisseur est débité du montant de l'enchère
		// proposé
		Integer nouveauCredit = 0;
		nouveauCredit = enchere.getUtilisateur().getCredit() - enchere.getMontantEnchere();
		enchere.getUtilisateur().setCredit(nouveauCredit);
		enchereDAO.updateUtilisateur(enchere);

		// Le meilleur enchérisseur précédent (s'il existe) est re-crédité de son offre

	}

//		En tant qu'enchérisseur, je deviens acquéreur si au terme de l'enchère j'ai
//		proposé l'enchère la plus haute
	@Override
	public void remporteVente(Enchere enchere) throws EnchereExceptionBLL {

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

	@Override
	public Enchere selectionnerEnchere(Integer id)
			throws EnchereExceptionBLL, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException,
			DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		Enchere enchere = new Enchere();
		try {
			enchere = enchereDAO.selectById(id);
		} catch (EnchereDALException e) {
			e.printStackTrace();
			throw new EnchereExceptionBLL("selection de l'enchère par id impossible");
		}
		return enchere;
	}

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
	public List<Enchere> getListEnchereParCategorie(Integer noCategorie)
			throws EnchereExceptionBLL, UtilisateurExceptionBLL, BLL_ArticleException, DAL_ArticleException,
			BLL_RetraitException, DAL_RetraitException, BLL_CategorieException, DAL_CategorieException {
		List<Enchere> lstEnchere = new ArrayList<Enchere>();
		List<Enchere> lstEnchereCategorie = new ArrayList<Enchere>();
		
		//Appel de la méthode getListEnchere() qui fait un select all sur la table enchere
		lstEnchere = getListEnchere();
		
		for (Enchere enchere : lstEnchere) {
			if (enchere.getArticle().getCategorie().getNoCategorie() == noCategorie) {
				lstEnchereCategorie.add(enchere);
			}
		}
		return lstEnchereCategorie;
	}

//	Utilisateur non connecté : liste des enchères par article
	@Override
	public List<Enchere> getListEnchereParArticle(Integer noArticle)
			throws EnchereExceptionBLL, UtilisateurExceptionBLL, BLL_ArticleException, DAL_ArticleException,
			BLL_RetraitException, DAL_RetraitException, BLL_CategorieException, DAL_CategorieException {
		List<Enchere> lstEnchere = new ArrayList<Enchere>();
		List<Enchere> lstEnchereArticle = new ArrayList<Enchere>();

		// Appel de la méthode getListEnchere() qui fait un select all sur la table enchere
		lstEnchere = getListEnchere();
		
		// TODO : rajouter la contrainte : l'article est affiché si il contient le
		// critère saisi --> Dans une autre couche ? ihm ?
		for (Enchere enchere : lstEnchere) {
			if (enchere.getArticle().getNoArticle() == noArticle) {
				lstEnchereArticle.add(enchere);
			}
		}
		return lstEnchereArticle;
	}

	// Utilisateur connecté : liste des enchères ouvertes
	@Override
	public List<Enchere> getListEnchereOuvertes() throws EnchereExceptionBLL {
		// TODO Auto-generated method stub
		return null;
	}

	// Utilisateur connecté : liste de mes enchères en cours
	@Override
	public List<Enchere> getListMesEnchereEnCours() throws EnchereExceptionBLL {
		// TODO Auto-generated method stub
		return null;
	}

	// Utilisateur connecté : liste de mes enchères gagnées
	@Override
	public List<Enchere> getListMesEnchereGagnees() throws EnchereExceptionBLL {
		// TODO Auto-generated method stub
		return null;
	}

}
