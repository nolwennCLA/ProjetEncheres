package fr.eni.ProjetEncheres.bll.article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManager;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManagerSing;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.article.DAOArticle;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public class ArticleManagerImpl implements ArticleManager {
	
	private final int MAX_LENGTH_DESCRIPTION = 300;
	private List<Article> lstArt;
	
	private DAOArticle articleDao = DAOFactory.getDAOArticle();
	
	private RetraitManager rm = RetraitManagerSing.getInstance();
	
	
	//TODO relier à la DAO

	
	
	@Override
	public Article creerArticle(Article article, Retrait retrait) throws BLL_ArticleException, DAL_ArticleException, BLL_RetraitException, DAL_RetraitException {
		
		if(article != null && retrait != null && article.getNoArticle() == null) {
		
			//si la creation est possible, alors on insert l'article et on gère le retrait
			if(verifArticle(article)) {
				
				//insertion de l'article
				articleDao.insert(article);
				
				//gestion de l'adresse de retrait
				rm.creerRetrait(article, retrait);
				
				//on ajoute l'adresse de retrait à l'Utilisateur en BDD 
				this.modifierArticle(article);
				
			} else {
				throw new BLL_ArticleException("BLL_ArticleManagerImpl_creerArticle() : Article non inséré");
			}
			
		} else {
			throw new BLL_ArticleException("BLL_ArticleManagerImpl_creerArticle() : Le Retrait ou l'Article est null, ou l'Article existe déjà en base");
		}
		return article;
	}

	
	@Override
	public List<Article> listerArticles() throws DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, UtilisateurExceptionBLL {
		return articleDao.selectAll();
	}

	
	@Override
	public Article selectionnerArticleParId(Integer noArticle) throws BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		Article article = null;
		if(noArticle != null) {
			article = articleDao.selectById(noArticle);
		} else {
			throw new BLL_ArticleException("BLL_ArticleManagerImpl_selectionnerArticleParId() : Le noArticle est null");
		}
		return article; 
	}
	
	
	@Override
	public List<Article> selectionnerArticleParNom(String nomArticle) throws BLL_ArticleException, DAL_ArticleException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, BLL_CategorieException, UtilisateurExceptionBLL {
		if(nomArticle != null && !"".equals(nomArticle)) {
			lstArt = articleDao.selectByName(nomArticle);
		} else {
			throw new BLL_ArticleException("BLL_ArticleManagerImpl_selectionnerArticleParNom() : Le nom est null ou vide");
		}
		return lstArt;
	}

	
	@Override
	public List<Article> selectionnerArticleParCategorie(Integer noCategorie) throws BLL_ArticleException, DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, UtilisateurExceptionBLL {
		if(noCategorie != null) {
			lstArt = articleDao.selectByCategory(noCategorie);
		} else {
			throw new BLL_ArticleException("BLL_ArticleManagerImpl_selectionnerArticleParCategorie() : Le noCategorie est null");
		}
		return lstArt;
	}

	
	@Override
	public List<Article> selectionnerArticleParNomEtCategorie(String nomArticle, Integer noCategorie) throws DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, BLL_ArticleException, UtilisateurExceptionBLL {
		if(nomArticle != null && !"".equals(nomArticle) && noCategorie != null) {
			lstArt = articleDao.selectByNameAndCategory(nomArticle, noCategorie);
		} else {
			throw new BLL_ArticleException("BLL_ArticleManagerImpl_selectionnerArticleParNomEtCategorie() : Le nom et/ou la categorie est null ou vide");
		}
		return lstArt;
	}
	
	
	@Override
	public Article modifierArticle(Article article) throws BLL_ArticleException, DAL_ArticleException {
		if(article.getNoArticle() != null && article != null) {
			if(verifArticle(article)) {
				article = articleDao.update(article);
			} else {
				throw new BLL_ArticleException("BLL_problème méthode modifierArticle()");
			}
		} else {
			throw new BLL_ArticleException("BLL_ArticleManagerImpl_modifierArticle() : L'Article n'existe pas ou son numéro est null");
		}
		return article;
	}

	
	@Override
	public void supprimerArticle(Integer noArticle) throws DAL_ArticleException, BLL_ArticleException {
		if(noArticle != null) {
			articleDao.delete(noArticle);
		} else {
			throw new BLL_ArticleException("BLL_ArticleManagerImpl_modifierArticle() : Le noArticle est null");
		}
		
		
	}
	
	
	
	public boolean verifArticle(Article article) throws BLL_ArticleException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal1 = Calendar.getInstance();
		
		boolean autorise = true;
		
		//le nom de l'article ne doit être ni null ni vide
		if(article.getNomArticle() == null || "".equalsIgnoreCase(article.getNomArticle()) ) {
			autorise = false;
			throw new BLL_ArticleException("BLL_ArticleManagerImpl_verifArticle() : le nom de l'article ne doit être ni null ni vide");
		}
		
		//la date de fin d'enchère doit être >= à la date de début enchère
		if(article.getDateFinEncheres().compareTo(article.getDateDebutEncheres()) < 0) {
			autorise = false;
			throw new BLL_ArticleException("BLL_ArticleManagerImpl_verifArticle() : la date de fin d'enchère doit être >= à la date de début enchère");
		}
		
		//les deux dates doivent être >= à la date du jour
		///on ne compare que les dates (pas les heures, min, sec)
		////date du jour
		Date dJour = null;
		cal1.setTime(new Date());
		try {
			dJour = new Date(sdf.parse(cal1.get(Calendar.YEAR) + "-" + cal1.get(Calendar.MONTH)+1 + "-" + cal1.get(Calendar.DAY_OF_MONTH)).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if(article.getDateDebutEncheres().compareTo(dJour) < 0
		|| article.getDateFinEncheres().compareTo(dJour) < 0) {
			autorise = false;
			throw new BLL_ArticleException("BLL_ArticleManagerImpl_verifArticle() : les dates doivent être >= à la date du jour");
		}
		
		//la mise à prix doit être >= 0
		if(article.getMiseAPrix() < 0) {
			autorise = false;
			throw new BLL_ArticleException("BLL_ArticleManagerImpl_verifArticle() : la mise à prix doit être >= 0");
		}
		
		//la catégorie doit être renseignée
		if(article.getCategorie() == null || "".equalsIgnoreCase(article.getCategorie().getLibelle())) {
			autorise = false;
			throw new BLL_ArticleException("BLL_ArticleManagerImpl_verifArticle() : la catégorie doit être renseignée");
		}
		
		//la description de l'article ne doit pas dépasser 300 caractères
		if(article.getDescription().length() > MAX_LENGTH_DESCRIPTION) {
			article.getDescription().substring(0, MAX_LENGTH_DESCRIPTION);
		}
		
		return autorise;
	}



}
