package fr.eni.ProjetEncheres.ihm.utilisateur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Utilisateur;

/**
 * Servlet implementation class TestModel
 */
@WebServlet("/TestModel")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtilisateurManager um = UtilisateurManagerSingl.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TestModel model= new TestModel();
//		String pseudo = "lapi";
//		try {
//			model.setUtilisateur(um.getUtilisateurParPseudo(pseudo));
//		} catch (UtilisateurExceptionBLL e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Utilisateur momo= new Utilisateur("momo", "lepetit", "monique", "e@po", "0222114569", "tyui", "29000", "poiuy", "momo");
		model.setUtilisateur(momo);
		request.setAttribute("model", model);
		request.getRequestDispatcher("Test.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
