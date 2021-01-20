package fr.eni.ProjetEncheres.ihm.accueil;

import java.io.IOException;

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
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.article.DAOArticle;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

/**
 * Servlet implementation class AccueilNonConnecteServlet
 */
@WebServlet(urlPatterns={"","/AccueilNonConnecteServlet"})

public class AccueilNonConnecteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AccueilNonConnecteModel model = new AccueilNonConnecteModel();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategorieManager cm = CategorieManagerSing.getInstance();
		ArticleManager am = ArticleManagerSing.getInstance();
		
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
			
			//si recherche sans nom et sur toutes les catégories
			if("".equals(request.getParameter("rechercheNom")) && request.getParameter("rechercheCategorie").equals("toutes")) {
				try {
					model.setLstArt(am.listerArticles());
				} catch (DAL_ArticleException | BLL_CategorieException | DAL_CategorieException | BLL_RetraitException
						| DAL_RetraitException | UtilisateurExceptionBLL e) {
					request.setAttribute("message", e.getMessage());
					e.printStackTrace();
				}
			
			//si rechercher sur nom et sur toutes les catégories
			} else if(!"".equals(request.getParameter("rechercheNom")) && request.getParameter("rechercheCategorie").equals("toutes")) {
				try {
					model.setLstArt(am.selectionnerArticleParNom(request.getParameter("rechercheNom")));
				} catch (BLL_ArticleException | DAL_ArticleException | BLL_CategorieException | DAL_CategorieException
						| BLL_RetraitException | DAL_RetraitException | UtilisateurExceptionBLL e) {
					request.setAttribute("message", e.getMessage());
					e.printStackTrace();
				}
			
			//si recherche sans nom mais sur une catégorie particulière
			} else if("".equals(request.getParameter("rechercheNom")) && !request.getParameter("rechercheCategorie").equals("toutes")) {
				try {
					model.setLstArt(am.selectionnerArticleParCategorie(Integer.parseInt(request.getParameter("rechercheCategorie"))));
				} catch (BLL_ArticleException | DAL_ArticleException | BLL_CategorieException | DAL_CategorieException
						| BLL_RetraitException | DAL_RetraitException | NumberFormatException | UtilisateurExceptionBLL e) {
					request.setAttribute("message", e.getMessage());
					e.printStackTrace();
				}
			
			//si recherche sur nom et sur catégorie particulière
			} else if(!"".equals(request.getParameter("rechercheNom")) && !request.getParameter("rechercheCategorie").equals("toutes")) {
				try {
					model.setLstArt(am.selectionnerArticleParNomEtCategorie(request.getParameter("rechercheNom"), Integer.parseInt(request.getParameter("rechercheCategorie"))));
				} catch (BLL_ArticleException | DAL_ArticleException | BLL_CategorieException | DAL_CategorieException
						| BLL_RetraitException | DAL_RetraitException | NumberFormatException | UtilisateurExceptionBLL e) {
					request.setAttribute("message", e.getMessage());
					e.printStackTrace();
				}
			}
			
			//on attribue le modèle à la requête
			request.setAttribute("model", model);
		

		
		}
		
		request.getRequestDispatcher("accueilNonConnecteVue.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
