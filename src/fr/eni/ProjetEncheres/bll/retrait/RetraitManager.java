package fr.eni.ProjetEncheres.bll.retrait;

import java.util.List;

import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public interface RetraitManager {
	
	public Retrait creerRetrait(Article article, Retrait retrait) throws BLL_RetraitException, DAL_RetraitException;
	
	public List<Retrait> listerRetraits() throws BLL_RetraitException, DAL_RetraitException;
	
	public Retrait selectionnerRetrait(Integer noRetrait) throws BLL_RetraitException, DAL_RetraitException;
	
	public Retrait modifierRetrait(Retrait retrait) throws BLL_RetraitException, DAL_RetraitException;
	
	public void supprimerRetrait(Integer noRetrait) throws BLL_RetraitException, DAL_RetraitException;
}
