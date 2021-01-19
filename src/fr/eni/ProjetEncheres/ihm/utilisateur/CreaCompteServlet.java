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
 * Servlet implementation class CreaCompteServlet
 */
@WebServlet("/CreaCompteServlet")
public class CreaCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager um = UtilisateurManagerSingl.getInstance();

    /**
     * Default constructor. 
     */
    public CreaCompteServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CreaCompteModel model= new CreaCompteModel();
		// si l'utilisateur clique sur le bouton creer
		if (request.getParameter("creer")!=null) {
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("tel");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("cpo");
			String ville = request.getParameter("ville");
			String motDePasse = request.getParameter("mdp");
			String ConfirmationMotDePasse = request.getParameter("conf");
			
			// si le mot de passe est le meme que la confirmation
			if (motDePasse.equals(ConfirmationMotDePasse)) {
				
				// je cree un utilisateur avec les données entrées
				Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
				
				// j'ajoute l'utilisateur en BDD
				try {
					um.addUtilisateur(utilisateur);
				} catch (UtilisateurExceptionBLL e) {
					e.printStackTrace();
					request.setAttribute("message1", e.getMessage());
					
				}
				// je modifie la liste du model
					try {
						model.setListUtilisateur(um.getListUtilisateur());
					} catch (UtilisateurExceptionBLL e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						request.setAttribute("message3", e.getMessage());
					}
					// je mets à jour le model
					request.setAttribute("model", model);
					
					// je met en session toutes les infos de l'utilisateur
					request.getSession().setAttribute("identifiant", request.getParameter("identifiant"));
					request.getSession().setAttribute("utilisateur", utilisateur);
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
					
					// je dirige vers la pasge d'accueil connecté
					request.getRequestDispatcher("accueilConnecteVue.jsp").forward(request, response);
					
				
			}else {
				request.setAttribute("message2", "Erreur lors de la confirmation du mot de passe");
				
			}

	}
		// si l'utilisateur clique sur le bouton annuler il est renvoyé vers la page d'acceuilnon connecté
		if (request.getParameter("annuler")!=null) {
			request.getRequestDispatcher("accueilNonConnecteVue.jsp").forward(request, response);
		}
		
		
		request.getRequestDispatcher("creaCompte.jsp").forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
