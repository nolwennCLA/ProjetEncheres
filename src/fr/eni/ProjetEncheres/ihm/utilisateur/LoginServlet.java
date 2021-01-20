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
		
		
		if(request.getParameter("identifiant")!=null && request.getParameter("mdp")!=null) {
			String identifiant = request.getParameter("identifiant");
			String mdp= request.getParameter("mdp");
			Utilisateur utilisateur= new Utilisateur();
			Boolean trouve = false;
			try {
				for (Utilisateur util : um.getListUtilisateur()) {
					//identifiant = pseudo ou email
					//je recherche si l'identifiant est bien dans la BDD et qu'il correspond bien au mdp
					if ((identifiant.equals(util.getPseudo()) || identifiant.equals(util.getEmail())) && mdp.equals(util.getMotDePasse())) {
						trouve =true;
						utilisateur=util;
		
					}

				}
				// si ok, je mets tout en session
				if (trouve) {
					request.getSession().setAttribute("identifiant", request.getParameter("identifiant"));
					request.getSession().setAttribute("utilisateur", utilisateur);
					request.getSession().setAttribute("noUtilisateur", utilisateur.getNoUtilisateur());
					request.getSession().setAttribute("pseudo", utilisateur.getPseudo());
					request.getSession().setAttribute("nom", utilisateur.getNom());
					request.getSession().setAttribute("prenom", utilisateur.getPrenom());
					request.getSession().setAttribute("email", utilisateur.getEmail());
					request.getSession().setAttribute("telephone", utilisateur.getTelephone());
					request.getSession().setAttribute("rue", utilisateur.getRue());
					request.getSession().setAttribute("codePostal", utilisateur.getCodePostal());
					request.getSession().setAttribute("ville", utilisateur.getVille());
					request.getSession().setAttribute("motDePasse", utilisateur.getMotDePasse());
					request.getSession().setAttribute("credit", utilisateur.getCredit());
					
					request.getRequestDispatcher("/AccueilConnecteServlet").forward(request, response);
					System.out.println("je met en session");
				}else {
					request.setAttribute("message1", "L'identifiant et/ou le mot de passe n'existe pas");
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
