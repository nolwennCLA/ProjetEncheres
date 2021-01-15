package fr.eni.ProjetEncheres.bll.article;

//import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManager;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManagerSing;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.article.DAOArticle;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public class ArticleManagerImpl implements ArticleManager {
	
	private final int MAX_LENGTH_DESCRIPTION = 300;
//	private List<Article> lstArt = new ArrayList<>();
	
	private DAOArticle articleDao = DAOFactory.getDAOArticle();
	
	private RetraitManager rm = RetraitManagerSing.getInstance();
	
	
	//TODO relier à la DAO
	
	@Override
	public Article creerArticle(Article article, Retrait retrait) throws BLL_ArticleException, BLL_RetraitException, DAL_ArticleException, DAL_RetraitException, UtilisateurExceptionBLL {
		
		//si la creation est possible, alors on insert l'article et on gère le retrait
		if(verifArticle(article)) {
			
			//la description de l'article ne doit pas dépasser 300 caractères
			if(article.getDescription().length() > MAX_LENGTH_DESCRIPTION) {
				article.getDescription().substring(0, MAX_LENGTH_DESCRIPTION);
			}
			
			//insertion de l'article
			articleDao.insert(article);
			
			//gestion de l'adresse de retrait
			rm.creerRetrait(article, retrait);
			
			//on ajoute l'adresse de retrait à l'Utilisateur en BDD 
			this.modifierArticle(article);
			
		} else {
			throw new BLL_ArticleException("BLL_problème méthode creerArticle() dans ArticleManagerImpl");
		}
		
		return article;
	}

	@Override
	public List<Article> listerArticles() throws BLL_ArticleException, DAL_ArticleException, UtilisateurExceptionBLL, BLL_RetraitException, DAL_RetraitException {
		return articleDao.selectAll();
	}

	@Override
	public Article selectionnerArticle(Integer noArticle) throws BLL_ArticleException, DAL_ArticleException, UtilisateurExceptionBLL, BLL_RetraitException, DAL_RetraitException {
		return articleDao.select(noArticle);
	}
	
	@Override
	public Article modifierArticle(Article article) throws UtilisateurExceptionBLL, BLL_RetraitException, DAL_ArticleException, BLL_ArticleException {
		
		if(verifArticle(article)) {
			articleDao.update(article);
			
		} else {
			throw new BLL_ArticleException("BLL_problème méthode modifierArticle()");
		}
		
		return article;
	}

	@Override
	public void supprimerArticle(Integer noArticle) throws BLL_ArticleException, DAL_ArticleException {
		articleDao.delete(noArticle);
	}
	
	
	
	public boolean verifArticle(Article article) throws BLL_ArticleException {
		
		boolean autorise = true;
		
		//le nom de l'article ne doit être ni null ni vide
		if(article.getNomArticle() == null || "".equalsIgnoreCase(article.getNomArticle()) ) {
			autorise = false;
			throw new BLL_ArticleException("le nom de l'article ne doit être ni null ni vide");
		}
		
		//la date de fin d'enchère doit être >= à la date de début enchère
		if(article.getDateFinEncheres().compareTo(article.getDateDebutEncheres()) < 0) {
			autorise = false;
			throw new BLL_ArticleException("la date de fin d'enchère doit être >= à la date de début enchère");
		}
		
		//les deux dates doivent être >= à la date du jour
		if(article.getDateDebutEncheres().compareTo(new Date()) < 0
		|| article.getDateFinEncheres().compareTo(new Date()) < 0) {
			autorise = false;
			throw new BLL_ArticleException("les dates doivent être >= à la date du jour");
		}
		
		//la mise à prix doit être >= 0
		if(article.getMiseAPrix() < 0) {
			autorise = false;
			throw new BLL_ArticleException("la mise à prix doit être >= 0");
		}
		
		//la catégorie doit être renseignée
		if(article.getCategorie() == null || "".equalsIgnoreCase(article.getCategorie().getLibelle())) {
			autorise = false;
			throw new BLL_ArticleException("la catégorie doit être renseignée");
		}
		
		return autorise;
	}

}
