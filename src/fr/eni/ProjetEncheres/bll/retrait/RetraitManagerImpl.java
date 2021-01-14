package fr.eni.ProjetEncheres.bll.retrait;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Retrait;

public class RetraitManagerImpl implements RetraitManager {
	
	private List<Retrait> lstRet = new ArrayList<>();

	@Override
	public Retrait creerRetrait(Article article, Retrait retrait) throws BLL_RetraitException {
		
//		if(article != null && retrait != null) {
		
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
					
					lstRet.add(retrait);
					article.setRetrait(retrait);
				}
			
			//si l'adresse de retrait indiquée est l'adresse de l'utilisateur, alors on ne fait rien
			}
		
//		}
		
		return retrait;
	}

	@Override
	public List<Retrait> listerRetraits() throws BLL_RetraitException {
		
		return lstRet;
	}

	@Override
	public Retrait selectionnerRetrait(Integer noRetrait) throws BLL_RetraitException {
		
		return lstRet.get(noRetrait);
	}

	@Override
	public Retrait modifierRetrait(Retrait retrait) throws BLL_RetraitException {
		
		return null;
	}

	@Override
	public void supprimerRetrait(Integer noRetrait) throws BLL_RetraitException {
		
		lstRet.remove(noRetrait);
	}

	

}
