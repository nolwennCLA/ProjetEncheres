package fr.eni.ProjetEncheres.ihm.enchere;

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
import fr.eni.ProjetEncheres.bll.enchere.BLL_EnchereException;
import fr.eni.ProjetEncheres.bll.enchere.EnchereManager;
import fr.eni.ProjetEncheres.bll.enchere.EnchereManagerSing;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.enchere.DAL_EnchereException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

/**
 * Servlet implementation class EncherirServlet
 */
@WebServlet("/EncherirServlet")
public class EncherirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EncherirServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnchereManager em = EnchereManagerSing.getInstance();
		ArticleManager am = ArticleManagerSing.getInstance();
		
		// Récupération du noArticle qui a transité par l'url
		Integer noArticle = null;
		if (request.getParameter("noArticle")!=null) {
			 noArticle= Integer.parseInt(request.getParameter("noArticle"));
			 request.getSession().setAttribute("noArticle", noArticle);
		}else {
			noArticle = (Integer) request.getSession().getAttribute("noArticle");
		}
		
		// Création des objets
		Article article = null;
//		Enchere enchere = null;

		// Création des model
		EncherirModel model= new EncherirModel();

		try {
			article = am.selectionnerArticleParId(noArticle);
		} catch (BLL_ArticleException | BLL_CategorieException | DAL_CategorieException | BLL_RetraitException
				| DAL_RetraitException | DAL_ArticleException | UtilisateurExceptionBLL e) {
			request.setAttribute("message1", e.getMessage());
			e.printStackTrace();
		}

		model.setArticle(article);
		
		//Si l'enchère est ouverte : récupération de la meilleure offre
		
		//em.recupererDerniereEnchere(em.selectionnerEnchereParId(noEnchere));

		//Si l'utilisateur clique sur le bouton enchérir
		if (request.getParameter("montantEnchere") != null) {
			
			//Récupération du montant enregistré dans le formulaire et mise dans l'application
			String montantEnchereString = request.getParameter("montantEnchere");

			// convertion du montant de l'enchère en Integer
			Integer montantEnchere = Integer.parseInt(montantEnchereString);

			// la date de l'enchère est la date du jour
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date maintenant = new Date();
			
			//Récupération de l'utilisateur
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			
			
			//Création de l'enchère
			Enchere enchere = new Enchere(maintenant, montantEnchere, utilisateur, article);
			model.setEnchere(enchere);
			System.out.println("l'enchere est cree");
			
			//Enregristrement de l'enchère dans la BDD
			try {
				em.creerEnchere(enchere);
				
			} catch (BLL_EnchereException | DAL_EnchereException | BLL_CategorieException | DAL_CategorieException
					| BLL_RetraitException | DAL_RetraitException | DAL_ArticleException | BLL_ArticleException
					| UtilisateurExceptionBLL e) {
				request.setAttribute("message1", e.getMessage());
				e.printStackTrace();
			}
			
			//Je mets dans le contexte d'application pour que l'enchère soit visible par tous les utilisateurs
			//this.getServletContext().setAttribute("meilleurOffre", enchere);

			
		}
		
		
		request.setAttribute("model", model);
		

		request.getRequestDispatcher("Encherir.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
