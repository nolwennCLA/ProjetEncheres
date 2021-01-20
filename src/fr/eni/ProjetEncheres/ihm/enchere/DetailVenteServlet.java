package fr.eni.ProjetEncheres.ihm.enchere;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetEncheres.bll.enchere.EnchereManager;
import fr.eni.ProjetEncheres.bll.enchere.EnchereManagerSing;
import fr.eni.ProjetEncheres.bo.Enchere;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/DetailVenteServlet")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailVenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	EnchereManager em = EnchereManagerSing.getInstance();
		
	
	//on vient chercher le nom de l'article sélectionné : faire transiter le noArticle par l'URL
//	try {
//		request.setAttribute("nomArticle", em.selectionnerEnchereParNoArticle(noArticle));
//	} catch (BLL_CategorieException e1) {
//		e1.printStackTrace();
//	} catch (DAL_CategorieException e1) {
//		e1.printStackTrace();
//	}
		
	if (request.getParameter("montantEnchere") != null) {
		String montantEnchereString = request.getParameter("montantEnchere");
		
		//convertion du montant de l'enchère en Integer
		Integer montantEnchere = Integer.parseInt(montantEnchereString);
		
		//la date de l'enchère est la date du jour
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date maintenant = new Date();
		
		
		
//		Enchere enchere = new Enchere(maintenant, montantEnchere, utilisateur, article);
		
		
	}
		
		
		
		
		
		
		request.getRequestDispatcher("DetailVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
