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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager um = UtilisateurManagerSingl.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("pseudo")!=null ) {
			String pseudo = request.getParameter("pseudo");
			String mdp= request.getParameter("mdp");
			Boolean trouve = false;
			try {
				for (Utilisateur util : um.getListUtilisateur()) {
					if (pseudo.equals(util.getPseudo()) && mdp.equals(util.getMotDePasse())) {
						trouve =true;
					}
					//TODO verifier si l'identifiant et le mot de passe sont present dans la table
				}
				if (trouve) {
					request.getSession().setAttribute("identifiant", request.getParameter("pseudo"));
					request.getSession().setAttribute("mdp", request.getParameter("mdp"));
					request.getRequestDispatcher("/CreaCompteServlet").forward(request, response);
				}else {
					System.out.println("Le pseudo et/ou le mot de passe n'existe pas");
					request.setAttribute("message1", "Le pseudo et/ou le mot de passe n'existe pas");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} catch (UtilisateurExceptionBLL e) {
				request.setAttribute("message2", "Erreur lors de la recherche du compte utilisateur");
				e.printStackTrace();
			}
			
		}
		else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
