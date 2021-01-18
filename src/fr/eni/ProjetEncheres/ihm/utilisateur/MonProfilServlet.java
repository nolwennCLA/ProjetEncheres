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
 * Servlet implementation class MonProfilServlet
 */
@WebServlet("/MonProfilServlet")
public class MonProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager um = UtilisateurManagerSingl.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MonProfilModel model= new MonProfilModel();
		String pseudo= (String) request.getServletContext().getAttribute("pseudo");
		
		// je récupere les informations de l'utilisateur connecté et je les mets dans mon modele
		try {
			for (Utilisateur u : um.getListUtilisateur()) {
				
				if (u.getPseudo().equals(pseudo)) {
					model.setUtilisateur(u);	
				}
				
			}
		} catch (UtilisateurExceptionBLL e) {
			request.setAttribute("message1", "Erreur lors de la recherche du compte utilisateur");
			e.printStackTrace();
		}
		
		request.setAttribute("model", model);
		request.getRequestDispatcher("monProfil.jsp").forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
