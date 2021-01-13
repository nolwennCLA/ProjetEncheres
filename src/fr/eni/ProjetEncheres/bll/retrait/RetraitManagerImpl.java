package fr.eni.ProjetEncheres.bll.retrait;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Retrait;

public class RetraitManagerImpl implements RetraitManager {
	
	private List<Retrait> lstRet = new ArrayList<>();

	@Override
	public Retrait creerRetrait(Article article, Retrait retrait) throws BLL_RetraitException {
	//////------Gestion du retrait transmis par l'ihm:------//////
		//si l'adresse de retrait indiquée par l'utilisateur est différente de sa propre adresse
		if(!retrait.getRue().equalsIgnoreCase(article.getUtilisateur().getRue())
		|| !retrait.getCodePostal().equalsIgnoreCase(article.getUtilisateur().getCodePostal())
		|| !retrait.getVille().equalsIgnoreCase(article.getUtilisateur().getVille())) {
			
			//si le retrait existe déjà
			if(this.listerRetraits().contains(retrait)) {
				
				//alors on ajoute l'article à la liste d'article du retrait
				for(Retrait r : this.listerRetraits()) {
					if(r.getRue().equalsIgnoreCase(retrait.getRue())
					&& r.getCodePostal().equals(retrait.getCodePostal())
					&& r.getVille().equals(retrait.getVille())) {
						this.selectionnerRetrait(r.getNoRetrait()).getListArticle().add(article);	
					} 
				}
				
			//si l'adresse de retrait n'existe pas, on crée un nouveau Retrait	
			} else {
				
				lstRet.add(retrait);
			}
		
		//si l'adresse de retrait indiquée est l'adresse de l'utilisateur, alors on ne fait rien
		}
		
		return retrait;
	}

	@Override
	public List<Retrait> listerRetraits() throws BLL_RetraitException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Retrait selectionnerRetrait(Integer noRetrait) throws BLL_RetraitException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Retrait modifierRetrait(Retrait retrait) throws BLL_RetraitException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerRetrait(Integer noRetrait) throws BLL_RetraitException {
		// TODO Auto-generated method stub

	}

	

}
