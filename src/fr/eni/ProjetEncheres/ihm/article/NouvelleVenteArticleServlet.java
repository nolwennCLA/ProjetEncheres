package fr.eni.ProjetEncheres.ihm.article;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

/**
 * Servlet implementation class NouvelleVenteArticleServlet
 */
@WebServlet("/NouvelleVenteArticleServlet")
public class NouvelleVenteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager am = ArticleManagerSing.getInstance();
		UtilisateurManager um = UtilisateurManagerSingl.getInstance();
		
		CategorieManager cm = CategorieManagerSing.getInstance();
		
		
		String path = "/nouvelleVenteArticleVue.jsp";
		
		
		//si une session est ouverte, on crée un Utilisateur de session
		Utilisateur uSess = null;
		if(request.getSession() != null) {
			uSess = (Utilisateur) request.getSession().getAttribute("utilisateur");	
		}
		

		//on assigne les catégories à la select
		try {
			request.setAttribute("lstCategories", cm.listerCategories());
		} catch (BLL_CategorieException e1) {
			e1.printStackTrace();
		} catch (DAL_CategorieException e1) {
			e1.printStackTrace();
		}
			
		
		
		//si l'utilisateur a cliqué sur le bouton Enregistrer
		if(request.getParameter("bouton") != null && request.getParameter("bouton").equals("Enregistrer")) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			//récupération des paramètres de la vue
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			String categorieStr = request.getParameter("categorie");
			String photo = request.getParameter("photo");
			String miseAPrixStr = request.getParameter("miseAPrix");
			String dateDebutEncheresStr = request.getParameter("dateDebutEncheres");
			String dateFinEncheresStr = request.getParameter("dateFinEncheres");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			
			//conversion des paramètres que le nécessitent
			////la catégorie
			Categorie categorie = null;
			try {
				categorie = cm.selectionnerCategorie(Integer.parseInt(categorieStr));
			} catch (NumberFormatException | BLL_CategorieException | DAL_CategorieException e1) {
				e1.printStackTrace();
			}
			
			////la mise à prix
			Integer miseAPrix = Integer.parseInt(miseAPrixStr);
			
			////les dates
			Date dateDebutEncheres = null;
			Date dateFinEncheres = null;
			try {
				dateDebutEncheres = new Date(sdf.parse(dateDebutEncheresStr).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			try {
				dateFinEncheres = new Date(sdf.parse(dateFinEncheresStr).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			//création d'un Retrait
			Retrait retrait = new Retrait(rue, codePostal, ville);
			
			//création d'un Article
			Article article = new Article(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, 0, "AV", categorie, uSess, retrait);
			
			//insertion de l'Article dans la base
			try {
				am.creerArticle(article, retrait);
			} catch (BLL_ArticleException | DAL_ArticleException | BLL_RetraitException | DAL_RetraitException e) {
				e.printStackTrace();
				request.setAttribute("messageErreur", e.getMessage());
			}

		
		//si l'utilisateur a cliqué sur le bouton Annuler
		} else if(request.getParameter("bouton") != null && request.getParameter("bouton").equals("Annuler")) {
			path = "accueilConnecteVue.jsp";
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
