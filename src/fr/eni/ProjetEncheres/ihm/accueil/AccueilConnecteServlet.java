package fr.eni.ProjetEncheres.ihm.accueil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManager;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManagerSing;
import fr.eni.ProjetEncheres.bll.enchere.BLL_EnchereException;
import fr.eni.ProjetEncheres.bll.enchere.EnchereManager;
import fr.eni.ProjetEncheres.bll.enchere.EnchereManagerSing;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.enchere.DAL_EnchereException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

/**
 * Servlet implementation class AccueilNonConnecteServlet
 */
@WebServlet("/AccueilConnecteServlet")
public class AccueilConnecteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AccueilConnecteModel model;
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	List<Enchere> lstTemp = new ArrayList<>();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategorieManager cm = CategorieManagerSing.getInstance();
		ArticleManager am = ArticleManagerSing.getInstance();
		EnchereManager em = EnchereManagerSing.getInstance();
		
		String path2 = "/accueilConnecteVue.jsp";
//		String chemin2;
		
		
		Integer noSess = (Integer) request.getSession().getAttribute("noUtilisateur");
		request.setAttribute("noSess", noSess);
		
		
		//on assigne les catégories à la select
		try {
			request.setAttribute("lstCategories", cm.listerCategories());
		} catch (BLL_CategorieException e1) {
			e1.printStackTrace();
		} catch (DAL_CategorieException e1) {
			e1.printStackTrace();
		}
		
		
		//si l'utilisateur a cliqué sur le bouton Rechercher
		if(request.getParameter("bouton") != null && request.getParameter("bouton").equals("Rechercher")) {
			
			model = new AccueilConnecteModel();
			
			
			
			//si l'utilisateur a sélectionné le bouton radio 'Mes ventes'
			if(request.getParameter("achatsVentes") != null && request.getParameter("achatsVentes").equals("ventes")) {
				
				//on attribue à la requête le bouton sélectionné
				request.setAttribute("bouton", request.getParameter("achatsVentes"));
				
				//si l'utilisateur a sélectionné une sous-catégorie de 'Mes ventes' (EC, AV ou VT)
				if(request.getParameter("mesVentes") != null) {
					
					//on attribue à la requête la sous-categorie sélectionnée
					request.setAttribute("critere", request.getParameter("mesVentes"));
					
//					if(request.getParameter("mesVentes").equals("AV")) {
//						chemin2 = request.getContextPath()+"/EnchereNonCommenceeServlet";
//						System.out.println(chemin2);
//						request.setAttribute("chemin2", chemin2);
//					}
				}
				
			}
			
			
			
			
			//si l'utilisateur a sélectionné le bouton radio 'Achats'
			if(request.getParameter("achatsVentes") != null && request.getParameter("achatsVentes").equals("achats")) {
				
				//on attribue à la requête le bouton sélectionné
				request.setAttribute("bouton", request.getParameter("achatsVentes"));
				
				
				
				
				
				//si l'utilisateur a sélectionné une sous-catégorie de 'Mes ventes' (enchères ouvertes, mes enchères ou mes enchères remportées)
				if(request.getParameter("encheres") != null) {
					
					String critere = request.getParameter("encheres");
					if("".equals(critere)) {
						System.out.println("CRITERE NULL");
					} else {
						System.out.println("critère passé : "+critere);
					}
					
					//si on cherche les enchères ouvertes on a besoin de la date du jour pour la comparer au dates début et fin encheres
					if(critere.equals("encheresOuvertes")) {
						request.setAttribute("sousCat", "encheresOuvertes");
						
						//mise au format yyy-MM-dd de la date du jour
						Date dJour = null;
						cal.setTime(new Date());		
						try {
							dJour = new Date(sdf.parse(cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH)+1 + "-" + cal.get(Calendar.DAY_OF_MONTH)).getTime());

						} catch (ParseException e) {
							e.printStackTrace();
						}
						request.setAttribute("critere", dJour);
					
					//si on cherche les enchères de l'utilisateur, on a besoin du noUtilisateur (en session)	
					} else if(critere.equals("mesEncheres")) {
						request.setAttribute("sousCat", "mesEncheres");
						request.setAttribute("noSess", noSess);
					
					
					} else if(critere.equals("encheresRemportees")) {
						//si on cherche les enchères remportées, on a besoin du noUtilisateur (en session) et de l'état de vente
						request.setAttribute("sousCat", "encheresRemportees");
						request.setAttribute("noSess", noSess);
					}
				}
			}
				
				
			//si recherche sans nom et sur toutes les catégories
			if("".equals(request.getParameter("rechercheNom")) && request.getParameter("rechercheCategorie").equals("toutes")) {
				

					try {
						model.setLstEnch(em.listerEncheres());
					} catch (BLL_EnchereException | DAL_EnchereException | BLL_CategorieException
							| DAL_CategorieException | BLL_RetraitException | DAL_RetraitException
							| DAL_ArticleException | BLL_ArticleException | UtilisateurExceptionBLL e) {
						request.setAttribute("message", e.getMessage());
						e.printStackTrace();
					}
				
				
				
					try {
						model.setLstArt(am.listerArticles());
					} catch (DAL_ArticleException | BLL_CategorieException | DAL_CategorieException
							| BLL_RetraitException | DAL_RetraitException | UtilisateurExceptionBLL e) {
						request.setAttribute("message", e.getMessage());
						e.printStackTrace();
					}
					
					
					try {
						for(Article art : am.listerArticles()) {
							lstTemp = em.selectionnerEnchereParNoArticle(art.getNoArticle());
							if(lstTemp.size() != 0) {
								model.getLstMeilleuresOffres().add(lstTemp.get(0));
							}
						}
						for(Enchere encher : model.getLstMeilleuresOffres()) {
							System.out.println("acheteur : "+encher.getUtilisateur().getNoUtilisateur() + " / article : "+encher.getArticle().getNoArticle() + " / etat : "+encher.getArticle().getEtatVente());
						}
					} catch (DAL_ArticleException | BLL_CategorieException | DAL_CategorieException
							| BLL_RetraitException | DAL_RetraitException | UtilisateurExceptionBLL
							| BLL_EnchereException | DAL_EnchereException | BLL_ArticleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
			
			
			//si rechercher sur nom et sur toutes les catégories
			} else if(!"".equals(request.getParameter("rechercheNom")) && request.getParameter("rechercheCategorie").equals("toutes")) {
			
				
					try {
						model.setLstEnch(em.selectionnerEnchereParNomArticle(request.getParameter("rechercheNom")));
					} catch (BLL_EnchereException | DAL_EnchereException | BLL_CategorieException
							| DAL_CategorieException | BLL_RetraitException | DAL_RetraitException
							| DAL_ArticleException | BLL_ArticleException | UtilisateurExceptionBLL e) {
						request.setAttribute("message", e.getMessage());
						e.printStackTrace();
					}
				
				
				
					try {
						model.setLstArt(am.selectionnerArticleParNom(request.getParameter("rechercheNom")));
					} catch (BLL_ArticleException | DAL_ArticleException | BLL_CategorieException
							| DAL_CategorieException | BLL_RetraitException | DAL_RetraitException
							| UtilisateurExceptionBLL e) {
						request.setAttribute("message", e.getMessage());
						e.printStackTrace();
					}
					
					
					try {
						for(Article art : am.listerArticles()) {
							lstTemp = em.selectionnerEnchereParNoArticle(art.getNoArticle());
							if(lstTemp.size() != 0) {
								model.getLstMeilleuresOffres().add(lstTemp.get(0));
							}
						}
					} catch (DAL_ArticleException | BLL_CategorieException | DAL_CategorieException
							| BLL_RetraitException | DAL_RetraitException | UtilisateurExceptionBLL
							| BLL_EnchereException | DAL_EnchereException | BLL_ArticleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			
				
			
				//si recherche sans nom mais sur une catégorie particulière
			} else if("".equals(request.getParameter("rechercheNom")) && !request.getParameter("rechercheCategorie").equals("toutes")) {
			
				
					try {
						model.setLstEnch(em.selectionnerEnchereParCategorieArticle(Integer.parseInt(request.getParameter("rechercheCategorie"))));
					} catch (NumberFormatException | BLL_EnchereException | DAL_EnchereException
							| BLL_CategorieException | DAL_CategorieException | BLL_RetraitException
							| DAL_RetraitException | DAL_ArticleException | BLL_ArticleException
							| UtilisateurExceptionBLL e) {
						request.setAttribute("message", e.getMessage());
						e.printStackTrace();
					}
				
				
				
					try {
						model.setLstArt(am.selectionnerArticleParCategorie(Integer.parseInt(request.getParameter("rechercheCategorie"))));
					} catch (NumberFormatException | BLL_ArticleException | DAL_ArticleException
							| BLL_CategorieException | DAL_CategorieException | BLL_RetraitException
							| DAL_RetraitException | UtilisateurExceptionBLL e) {
						request.setAttribute("message", e.getMessage());
						e.printStackTrace();
					}
					
					
					try {
						for(Article art : am.listerArticles()) {
							lstTemp = em.selectionnerEnchereParNoArticle(art.getNoArticle());
							if(lstTemp.size() != 0) {
								model.getLstMeilleuresOffres().add(lstTemp.get(0));
							}
						}
					} catch (DAL_ArticleException | BLL_CategorieException | DAL_CategorieException
							| BLL_RetraitException | DAL_RetraitException | UtilisateurExceptionBLL
							| BLL_EnchereException | DAL_EnchereException | BLL_ArticleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
				
				//si recherche sur nom et sur catégorie particulière
			} else if(!"".equals(request.getParameter("rechercheNom")) && !request.getParameter("rechercheCategorie").equals("toutes")) {
			
				
					try {
						model.setLstEnch(em.selectionnerEnchereParNomEtCategorieArticle(request.getParameter("rechercheNom"), Integer.parseInt(request.getParameter("rechercheCategorie"))));
					} catch (NumberFormatException | BLL_EnchereException | DAL_EnchereException
							| BLL_CategorieException | DAL_CategorieException | BLL_RetraitException
							| DAL_RetraitException | DAL_ArticleException | BLL_ArticleException
							| UtilisateurExceptionBLL e) {
						request.setAttribute("message", e.getMessage());
						e.printStackTrace();
					}
				
				
				
					try {
						model.setLstArt(am.selectionnerArticleParNomEtCategorie(request.getParameter("rechercheNom"), Integer.parseInt(request.getParameter("rechercheCategorie"))));
					} catch (NumberFormatException | BLL_ArticleException | DAL_ArticleException
							| BLL_CategorieException | DAL_CategorieException | BLL_RetraitException
							| DAL_RetraitException | UtilisateurExceptionBLL e) {
						request.setAttribute("message", e.getMessage());
						e.printStackTrace();
					}
					
					
					try {
						for(Article art : am.listerArticles()) {
							lstTemp = em.selectionnerEnchereParNoArticle(art.getNoArticle());
							if(lstTemp.size() != 0) {
								model.getLstMeilleuresOffres().add(lstTemp.get(0));
							}
						}
					} catch (DAL_ArticleException | BLL_CategorieException | DAL_CategorieException
							| BLL_RetraitException | DAL_RetraitException | UtilisateurExceptionBLL
							| BLL_EnchereException | DAL_EnchereException | BLL_ArticleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}

			
			
			//on attribue le modèle à la session
			request.getSession().setAttribute("model", model);
			AccueilConnecteModel modelSess = (AccueilConnecteModel) request.getSession().getAttribute("model");
			//on attribue les listes du modèle à la session
			request.getSession().setAttribute("listeArticles", modelSess.getLstArt());
			request.getSession().setAttribute("listeEncheres", modelSess.getLstEnch());
			request.getSession().setAttribute("listeMeilleuresOffres", modelSess.getLstMeilleuresOffres());
			
			

		}
		
		request.getRequestDispatcher(path2).forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
