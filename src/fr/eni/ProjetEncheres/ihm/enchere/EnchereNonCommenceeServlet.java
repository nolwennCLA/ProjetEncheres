package fr.eni.ProjetEncheres.ihm.enchere;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import fr.eni.ProjetEncheres.bll.retrait.RetraitManager;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManagerSing;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Retrait;
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
	RetraitManager rm = RetraitManagerSing.getInstance();
	
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Article a;
	String path;
	Integer noArticle;
	Integer afficheRetrait;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//on assigne les catégories à la select
		try {
			request.setAttribute("lstCategories", cm.listerCategories());
		} catch (BLL_CategorieException e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		} catch (DAL_CategorieException e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}

		//on récpère l'utilisateur en session et on l'attribue à la requête
		Utilisateur uSess = (Utilisateur) request.getSession().getAttribute("utilisateur");
		request.setAttribute("utilisateurSess", uSess);
		
		
		//on récupère le paramètre en URL ou en session 
		if(request.getParameter("noArticle") != null) {
			noArticle = Integer.parseInt(request.getParameter("noArticle"));
			request.getSession().setAttribute("noArticle", noArticle);
		} else {
			noArticle = (Integer) request.getSession().getAttribute("noArticle");
		}	
			
			
		//on récupère l'Article à partir du paramètre et on attribue l'article à la requête
		try {
			a = am.selectionnerArticleParId(noArticle);
		} catch (BLL_CategorieException | DAL_CategorieException | BLL_RetraitException | DAL_RetraitException
				| DAL_ArticleException | BLL_ArticleException | UtilisateurExceptionBLL e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		request.setAttribute("article", a);
		
		
		//on vérifie si l'article a un retrait et on attribue la réponse à la jsp
		if(a.getRetrait() != null) {
			afficheRetrait = 1;
		} else {
			afficheRetrait = 0;
		}
		request.setAttribute("afficheRetrait", afficheRetrait);
		
		
		path = "enchereNonCommenceeVue.jsp";
		

		
		
		//si l'utilisateur a cliqué sur le bouton Enregistrer
		if(request.getParameter("bouton") != null && request.getParameter("bouton").equals("Enregistrer")) {
			
			//on récupère les paramètre envoyés par l'utilisateur
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			Integer noCategorie = Integer.parseInt(request.getParameter("categorie"));
			Integer miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
			
			String dd = request.getParameter("dateDebutEncheres");
			String df = request.getParameter("dateFinEncheres");
			
			
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			
			
			
			//on intègre ces paramètres à l'Article récupéré
			a.setNomArticle(nomArticle);
			a.setDescription(description);
			try {
				a.setDateDebutEncheres(new Date(sdf.parse(dd).getTime()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				a.setDateFinEncheres(new Date(sdf.parse(df).getTime()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			a.setMiseAPrix(miseAPrix);
			try {
				a.setCategorie(cm.selectionnerCategorie(noCategorie));
			} catch (BLL_CategorieException | DAL_CategorieException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
			
			//on vérifie s'il faut préparer un retrait
			Retrait ret = null;
			//si l'article a déjà un retrait
			if(!rue.equalsIgnoreCase(uSess.getRue()) || !codePostal.equalsIgnoreCase(uSess.getCodePostal()) || !ville.equalsIgnoreCase(uSess.getVille())) {
				ret = new Retrait(rue, codePostal, ville);
				try {
					ret = rm.creerRetrait(a, ret);
				} catch (BLL_RetraitException | DAL_RetraitException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
			
			System.out.println(a.toString());
			//on update l'article en base
			try {
				a = am.modifierArticle(a);
			} catch (BLL_ArticleException | DAL_ArticleException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
			path = "enchereNonCommenceeVue.jsp";
			
		}
		
		
		
		
		//si l'utilisateur a cliqué sur le bouton Annuler
		if(request.getParameter("bouton") != null && request.getParameter("bouton").equals("Annuler")) {
			
			path = "accueilConnecteVue.jsp";
		}
		
		
		//si l'utilisateur a cliqué sur le bouton Annuler la vente
		if(request.getParameter("bouton") != null && request.getParameter("bouton").equals("Annuler la vente")) {
			
			try {
				am.supprimerArticle(noArticle);
			} catch (DAL_ArticleException | BLL_ArticleException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
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
