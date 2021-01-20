package fr.eni.ProjetEncheres.ihm.enchere;

import java.io.IOException;
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
import fr.eni.ProjetEncheres.bll.enchere.EnchereManager;
import fr.eni.ProjetEncheres.bll.enchere.EnchereManagerSing;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
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
		String noArticleString = request.getParameter("noArticle");

		// Conversion du noArticle en Integer
		Integer noArticle = Integer.parseInt(noArticleString);

		// Création des objets
		Article article = null;
		Enchere enchere = null;

		// Création des model
		EncherirModel modelArticle = new EncherirModel();
		EncherirModel modelEnchere = new EncherirModel();

		try {
			article = am.selectionnerArticleParId(noArticle);
		} catch (BLL_ArticleException | BLL_CategorieException | DAL_CategorieException | BLL_RetraitException
				| DAL_RetraitException | DAL_ArticleException | UtilisateurExceptionBLL e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelArticle.setArticle(article);
		
		//Si l'enchère est ouverte : récupération de la meilleure offre
		
		

		//Si l'utilisateur clique sur le bouton enchérir
		if (request.getParameter("Enchérir") != null) {
			
			//Récupération du montant enregistré dans le formulaire
			String montantEnchereString = request.getParameter("montantEnchere");

			// convertion du montant de l'enchère en Integer
			Integer montantEnchere = Integer.parseInt(montantEnchereString);

			//Le montant de l'enchère proposé doit être supérieur à la meilleure offre (si existant)
			
			
			
				
			// la date de l'enchère est la date du jour
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date maintenant = new Date();

			

		}

		request.setAttribute("modelArticle", modelArticle);

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
