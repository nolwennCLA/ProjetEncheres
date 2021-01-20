package fr.eni.ProjetEncheres.bll.enchere;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.enchere.DAL_EnchereException;
import fr.eni.ProjetEncheres.dal.enchere.DAOEnchere;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;


public class EnchereManagerImpl implements EnchereManager {
	
	
	DAOEnchere enchereDao = DAOFactory.getDAOEnchere();
	UtilisateurManager um = UtilisateurManagerSingl.getInstance();
	
	List<Enchere> lstEnch;
	
	Integer meilleureOffre;
	Enchere encherePrecedente;
	
	@Override
	public Enchere creerEnchere(Enchere enchere) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		
		if(enchere != null && enchere.getNoEnchere() == null) {
			if(verifPossibiliteEnchere(enchere)) {
				debitCredit(enchere);
				enchere = enchereDao.insert(enchere);
			} else {
				throw new BLL_EnchereException("BLL_EnchereManagerImpl_creerEnchere() : Enchère impossible");
			}
			
		} else {
			throw new BLL_EnchereException("BLL_EnchereManagerImpl_creerEnchere() : L'enchère existe déjà en base ou est nulle");
		}
		return enchere;
	}

	@Override
	public List<Enchere> listerEncheres() throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		return enchereDao.selectAll();
	}

	@Override
	public Enchere selectionnerEnchereParId(Integer noEnchere) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		Enchere enchere = null;
		if(noEnchere != null) {
			enchere = enchereDao.selectById(noEnchere);
		} else {
			throw new BLL_EnchereException("BLL_EnchereManagerImpl_selectionnerEnchereParId() : Le noEnchere est null");
		}
		return enchere;
	}
	
	//a priori inutile
	@Override
	public Enchere modifierEnchere(Enchere enchere) throws BLL_EnchereException {
		// TODO Auto-generated method stub
		return null;
	}
	
	//a priori inutile
	@Override
	public void supprimerEnchere(Integer noEnchere) throws BLL_EnchereException, DAL_EnchereException {
		if(noEnchere != null) {
			enchereDao.delete(noEnchere);
		} else {
			throw new BLL_EnchereException("BLL_EnchereManagerImpl_supprimerenchere() : Le noEnchere est null");
		}

	}


	
	@Override
	public List<Enchere> selectionnerEnchereParNoArticle(Integer noArticle) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		if(noArticle != null) {
			lstEnch = enchereDao.selectByNoArticle(noArticle);
		} else {
			throw new BLL_EnchereException("BLL_EnchereManagerImpl_selectionnerEnchereParNoArticle() : Le noArticle est null");
		}
		return lstEnch;
	}	
	
	public List<Enchere> selectionnerEncheresOuvertes() throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		List<Enchere> lstEnchEC = new ArrayList<>();
		lstEnch = this.listerEncheres();
		for(Enchere ench : lstEnch) {
			if(ench.getArticle().getEtatVente().equals("EC")) {
				lstEnchEC.add(ench);
			}
		}
		return lstEnchEC;
	}
	
	@Override
	public List<Enchere> selectionnerEnchereParNomArticle(String nomArticle) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		List<Enchere> lstEncheresParNomArticle;
		if(nomArticle != null) {
			List<Enchere> lstEncheres = this.listerEncheres();
			lstEncheresParNomArticle = new ArrayList<>();
			for(Enchere ench : lstEncheres) {
				if(ench.getArticle().getNomArticle().contains(nomArticle)) {
					lstEncheresParNomArticle.add(ench);
				}
			}
		} else {
			throw new BLL_EnchereException("BLL_EnchereManagerImpl_selectionnerEnchereParNomArticle() : Le nomArticle est null");
		}
		return lstEncheresParNomArticle;
	}	

	@Override
	public List<Enchere> selectionnerEnchereParCategorieArticle(Integer noCategorie) throws DAL_EnchereException, BLL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		List<Enchere> lstEncheresParCategorieArticle;
		if(noCategorie != null) {
			List<Enchere> lstEncheres = this.listerEncheres();
			lstEncheresParCategorieArticle = new ArrayList<>();
			for(Enchere ench : lstEncheres) {
				if(ench.getArticle().getCategorie().getNoCategorie() == noCategorie) {
					lstEncheresParCategorieArticle.add(ench);
				}
			}
		} else {
			throw new BLL_EnchereException("BLL_EnchereManagerImpl_selectionnerEnchereParCategorieArticle() : Le noCategorie est null");
		}
		return lstEncheresParCategorieArticle;
	}

	@Override
	public List<Enchere> selectionnerEnchereParNomEtCategorieArticle(String nomArticle, Integer noCategorie) throws DAL_EnchereException, BLL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		List<Enchere> lstEncheresParNomEtCategorieArticle;
		if(nomArticle != null && noCategorie != null) {
			List<Enchere> lstEncheres = this.listerEncheres();
			lstEncheresParNomEtCategorieArticle = new ArrayList<>();
			for(Enchere ench : lstEncheres) {
				if(ench.getArticle().getNomArticle().contains(nomArticle) && ench.getArticle().getCategorie().getNoCategorie() == noCategorie) {
					lstEncheresParNomEtCategorieArticle.add(ench);
				}
			}
		} else {
			throw new BLL_EnchereException("BLL_EnchereManagerImpl_selectionnerEnchereParCategorieArticle() : Le noCategorie ou le nomArticle est null");
		}
		return lstEncheresParNomEtCategorieArticle;
	}
	
	
	public boolean verifPossibiliteEnchere(Enchere enchere) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		
		boolean encherePossible = true;
		
		//on vérifie que l'état de vente de l'article est "EC"
		if(enchere.getArticle().getEtatVente() != "EC") {
			encherePossible = false;
			throw new BLL_EnchereException("L'article n'est pas actuellement en vente");
		}
		
		//on vérifie que le montant de l'enchère est supérieur à la mise à prix (pour une 1ère enchère)
		if(enchere.getMontantEnchere() <= enchere.getArticle().getMiseAPrix()) {
			encherePossible = false;
			throw new BLL_EnchereException("Votre enchère doit être supérieure à la mise à prix");
		}
		
		//on vérifie que le montant de l'enchère est supérieur à la meilleure offre (après la 1ère enchère)
		//(la meilleure offre est l'enchère sur l'article en question dont le montant est le plus grand)
		encherePrecedente = recupererDerniereEnchere(enchere);
		if(encherePrecedente != null) {
			meilleureOffre = encherePrecedente.getMontantEnchere();
			if(enchere.getMontantEnchere() <= meilleureOffre) {
				encherePossible = false;
				throw new BLL_EnchereException("Votre enchère doit être supérieure à la meilleure offre");
			}
		}
		
		//on vérifie que le montant de l'enchère est inférieur ou égal aux crédits de l'utilisateur
		if(enchere.getMontantEnchere() > enchere.getUtilisateur().getCredit()) {
			encherePossible = false;
			throw new BLL_EnchereException("Votre enchère ne doit pas dépasser vos crédits");
		}
		
		return encherePossible;
	}
	
	
	public Enchere recupererDerniereEnchere(Enchere enchere) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		Enchere derniereEnchere = null;
		List<Enchere> lstEncheresSurArticle = this.selectionnerEnchereParNoArticle(enchere.getArticle().getNoArticle());
		if(lstEncheresSurArticle.size() != 0) {
			derniereEnchere = lstEncheresSurArticle.get(0);
		}
		return derniereEnchere;
	}


	public void debitCredit(Enchere enchere) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		encherePrecedente = recupererDerniereEnchere(enchere);
		//credit du dernier encherisseur avant l'actuel (s'il y en a un)
		if(encherePrecedente != null) {
			Utilisateur precedent = encherePrecedente.getUtilisateur();
			precedent.setCredit(precedent.getCredit() + meilleureOffre);
			um.updateUtilisateur(precedent);
		}
		
		//debit de l'encherisseur actuel
		Utilisateur actuel = enchere.getUtilisateur();
		actuel.setCredit(actuel.getCredit() - enchere.getMontantEnchere());
		um.updateUtilisateur(actuel);
		
	}
	
	
}
