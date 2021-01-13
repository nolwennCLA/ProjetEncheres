package fr.eni.ProjetEncheres.bll.article;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManager;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManagerSing;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Retrait;

public class ArticleManagerImpl implements ArticleManager {
	
	private final int MAX_LENGTH_DESCRIPTION = 200;
	private List<Article> lstArt = new ArrayList<>();
	
	
	private RetraitManager rm = RetraitManagerSing.getInstance();
	
	@Override
	public Article creerArticle(Article article, Retrait retrait) throws BLL_ArticleException, BLL_RetraitException {
		
		//si la creation est possible, alors on insert l'article et on gère le retrait
		if(verifArticle(article)) {
			
			//la description de l'article ne doit pas dépasser 200 caractères
			if(article.getDescription().length() > MAX_LENGTH_DESCRIPTION) {
				article.getDescription().substring(0, MAX_LENGTH_DESCRIPTION);
			}
			
			lstArt.add(article);
			
			rm.creerRetrait(article, retrait);
		}
		
		return article;
	}

	@Override
	public List<Article> listerArticles() throws BLL_ArticleException {
		return lstArt;
	}

	@Override
	public Article selectionnerArticle(Integer noArticle) throws BLL_ArticleException {
		return lstArt.get(noArticle);
	}
	
	
	@Override
	public Article modifierArticle(Article article) throws BLL_ArticleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerArticle(Integer noArticle) throws BLL_ArticleException {
		// TODO Auto-generated method stub
		
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
