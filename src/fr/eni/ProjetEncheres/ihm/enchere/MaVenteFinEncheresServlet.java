package fr.eni.ProjetEncheres.ihm.enchere;

import java.io.IOException;
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
import fr.eni.ProjetEncheres.bll.enchere.BLL_EnchereException;
import fr.eni.ProjetEncheres.bll.enchere.EnchereManager;
import fr.eni.ProjetEncheres.bll.enchere.EnchereManagerSing;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.enchere.DAL_EnchereException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

/**
 * Servlet implementation class MaVenteFinEnchereServlet
 */
@WebServlet("/MaVenteFinEncheresServlet")
public class MaVenteFinEncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ArticleManager am = ArticleManagerSing.getInstance();
	EnchereManager em = EnchereManagerSing.getInstance();
	UtilisateurManager um = UtilisateurManagerSingl.getInstance();
	
	
	Article a;
	Integer noArticle;
	List<Enchere> lstEnch;
	Enchere derniereEnchere;
	Integer afficheEnchere;
	
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		//on récupère l'article à partir du paramètre et on attribue l'article à la session
		try {
			a = am.selectionnerArticleParId(noArticle);
		} catch (BLL_CategorieException | DAL_CategorieException | BLL_RetraitException | DAL_RetraitException
				| DAL_ArticleException | BLL_ArticleException | UtilisateurExceptionBLL e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("article", a);
		
		
		//on récupère la liste des enchères sur l'article
		try {
			lstEnch = em.selectionnerEnchereParNoArticle(noArticle);
		} catch (BLL_EnchereException | DAL_EnchereException | BLL_CategorieException | DAL_CategorieException
				| BLL_RetraitException | DAL_RetraitException | DAL_ArticleException | BLL_ArticleException
				| UtilisateurExceptionBLL e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		//on récupère la dernière enchère, celle du gagnant
		if(lstEnch.size() != 0) {
			derniereEnchere = lstEnch.get(0);
			afficheEnchere = 1;
			request.setAttribute("afficheEnchere", afficheEnchere);
		} else {
			derniereEnchere = null;
			afficheEnchere = 0;
			request.setAttribute("afficheEnchere", afficheEnchere);
		}
		
		
		//on attribue à la requête les paramètres attendus
		if(derniereEnchere != null) {
			Utilisateur gagnant = null;
			try {
				gagnant = um.getUtilisateurParId(derniereEnchere.getUtilisateur().getNoUtilisateur());
			} catch (UtilisateurExceptionBLL e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			String pseudoGagnant = gagnant.getPseudo();
			request.setAttribute("pseudoGagnant", pseudoGagnant);
			
			request.setAttribute("nomArticle", a.getNomArticle());
			
			request.setAttribute("description", a.getDescription());
			
			request.setAttribute("meilleureOffre", derniereEnchere.getMontantEnchere());
			
			request.setAttribute("miseAPrix", a.getMiseAPrix());
			
			request.setAttribute("dateFinEncheres", a.getDateFinEncheres());
			
			if(a.getRetrait() != null) {
				request.setAttribute("rue", a.getRetrait().getRue());
				request.setAttribute("codePostal", a.getRetrait().getCodePostal());
				request.setAttribute("ville", a.getRetrait().getVille());
			} else {
				request.setAttribute("rue", a.getUtilisateur().getRue());
				request.setAttribute("codePostal", a.getUtilisateur().getCodePostal());
				request.setAttribute("ville", a.getUtilisateur().getVille());
			}
			
			request.setAttribute("vendeur", uSess.getPseudo());
			
		} else {
			request.setAttribute("nomArticle", a.getNomArticle());
			request.setAttribute("description", a.getDescription());
			request.setAttribute("miseAPrix", a.getMiseAPrix());
			request.setAttribute("dateFinEncheres", a.getDateFinEncheres());
			request.setAttribute("vendeur", uSess.getPseudo());
		}
		
		
		
		
		
		
		request.getRequestDispatcher("maVenteFinEncheresVue.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
