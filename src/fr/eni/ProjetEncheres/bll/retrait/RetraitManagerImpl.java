package fr.eni.ProjetEncheres.bll.retrait;

//import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;
import fr.eni.ProjetEncheres.dal.retrait.DAORetrait;

public class RetraitManagerImpl implements RetraitManager {
	
//	private List<Retrait> lstRet = new ArrayList<>();
	private DAORetrait retraitDao = DAOFactory.getDAORetrait();
//	private ArticleManager am = ArticleManagerSing.getInstance();

	@Override
	public Retrait creerRetrait(Article article, Retrait retrait) throws BLL_RetraitException, DAL_RetraitException, UtilisateurExceptionBLL, DAL_ArticleException, BLL_ArticleException {
		
			//si l'adresse de retrait indiquée par l'utilisateur est différente de sa propre adresse
			if(!retrait.getRue().equalsIgnoreCase(article.getUtilisateur().getRue())
			|| !retrait.getCodePostal().equalsIgnoreCase(article.getUtilisateur().getCodePostal())
			|| !retrait.getVille().equalsIgnoreCase(article.getUtilisateur().getVille())) {
				
				//si le retrait existe déjà
				if(this.listerRetraits().contains(retrait)) {
					
					//alors on ajoute le retrait à l'article
					for(Retrait r : this.listerRetraits()) {
						if(r.getRue().equalsIgnoreCase(retrait.getRue())
						&& r.getCodePostal().equals(retrait.getCodePostal())
						&& r.getVille().equals(retrait.getVille())) {
							article.setRetrait(this.selectionnerRetrait(r.getNoRetrait()));	
						} 
					}
					
				//si l'adresse de retrait n'existe pas, alors on crée un nouveau Retrait et on l'ajoute à l'article	
				} else {
					
					retraitDao.insert(retrait);
					article.setRetrait(retrait);
				}
	
			//si l'adresse de retrait indiquée est l'adresse de l'utilisateur, on ajoute le retrait à l'Utilisateur
			//dans ce cas, le retrait n'est pas ajouté à la bdd et n'a donc pas de noRetrait (null)
			//NE RIEN FAIRE ICI SERAIT PEUT ÊTRE PLUS PROPRE
			} else {
				article.setRetrait(retrait);
			}
			
		//on retourne le retrait
		return retrait;
	}

	@Override
	public List<Retrait> listerRetraits() throws BLL_RetraitException, DAL_RetraitException {
		return retraitDao.selectAll();
	}

	@Override
	public Retrait selectionnerRetrait(Integer noRetrait) throws BLL_RetraitException, DAL_RetraitException {
		return retraitDao.select(noRetrait);
	}

	@Override
	public Retrait modifierRetrait(Retrait retrait) throws BLL_RetraitException, DAL_RetraitException {
		return retraitDao.update(retrait);
	}

	@Override
	public void supprimerRetrait(Integer noRetrait) throws BLL_RetraitException, DAL_RetraitException {
		retraitDao.delete(noRetrait);
	}

	

}
