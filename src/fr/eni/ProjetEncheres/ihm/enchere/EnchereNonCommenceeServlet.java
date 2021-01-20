package fr.eni.ProjetEncheres.ihm.enchere;

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
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

/**
 * Servlet implementation class EnchereNonCommenceeServlet
 */
@WebServlet("/EnchereNonCommenceeServlet")
public class EnchereNonCommenceeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	CategorieManager cm = CategorieManagerSing.getInstance();
	ArticleManager am = ArticleManagerSing.getInstance();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//on assigne les catégories à la select
		try {
			request.setAttribute("lstCategories", cm.listerCategories());
		} catch (BLL_CategorieException e1) {
			e1.printStackTrace();
		} catch (DAL_CategorieException e1) {
			e1.printStackTrace();
		}

		//on récpère l'utilisateur en session et on l'attribue à la requête
		Utilisateur u = (Utilisateur) request.getSession().getAttribute("utilisateur");
		request.setAttribute("utilisateurSess", u);
		
		
		//on récupère le paramètre en URL
		Integer noArticle = Integer.parseInt(request.getParameter("noArticle"));
		System.out.println("noArticle : " +  noArticle);
		Article a = null;
		//on récupère l'Article à partir du paramètre
		try {
			a = am.selectionnerArticleParId(noArticle);
		} catch (BLL_CategorieException | DAL_CategorieException | BLL_RetraitException | DAL_RetraitException
				| DAL_ArticleException | BLL_ArticleException | UtilisateurExceptionBLL e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//on attribue l'article à la requête
		request.setAttribute("article", a);
		System.out.println(a.getDateDebutEncheres());
		
		
		request.getRequestDispatcher("enchereNonCommenceeVue.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
