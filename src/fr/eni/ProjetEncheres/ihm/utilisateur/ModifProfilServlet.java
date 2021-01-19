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
 * Servlet implementation class ModifProfilServlet
 */
@WebServlet("/ModifProfilServlet")
public class ModifProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UtilisateurManager um = UtilisateurManagerSingl.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CreaCompteModel model= new CreaCompteModel();
		Utilisateur utilisateur = null;
		// je récupere l'utilisateur en session
		try {
			 utilisateur = um.getUtilisateurParPseudo((String)request.getSession().getAttribute("pseudo"));
		} catch (UtilisateurExceptionBLL e2) {
			request.setAttribute("message6", e2.getMessage());
			e2.printStackTrace();
		}
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("tel");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("cpo");
		String ville = request.getParameter("ville");
		String mdpActuel = request.getParameter("mdpActuel");
		String NouveauMotDePasse = request.getParameter("nouveauMdp");
		String ConfirmationMotDePasse = request.getParameter("conf");
		
		// si l'utilisateur clique sur le bouton enregistrer
		if (request.getParameter("enregistrer")!=null) {
			
			// si le mot de passe actuel est bon
			if (mdpActuel.equals(request.getSession().getAttribute("motDePasse"))) {
			
				// si le nouveau mot de passe est identique à la confirmation du mot de passe
			if (NouveauMotDePasse.equals(ConfirmationMotDePasse)) {
				
				// je met à jour les infos de l'utilisateur
				utilisateur.setPseudo(pseudo);
				utilisateur.setNom(nom);
				utilisateur.setPrenom(prenom);
				utilisateur.setEmail(email);
				utilisateur.setTelephone(telephone);
				utilisateur.setRue(rue);
				utilisateur.setCodePostal(codePostal);
				utilisateur.setVille(ville);
				utilisateur.setMotDePasse(NouveauMotDePasse);
			
				// j'update en BDD
				try {
					um.updateUtilisateur(utilisateur);
					// je modifie la liste du model avec la nouvelle liste en BDD
					try {
						model.setListUtilisateur(um.getListUtilisateur());
					} catch (UtilisateurExceptionBLL e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						request.setAttribute("message3", e.getMessage());
					}
					// je met à jour le model
					request.setAttribute("model", model);
					// je met en session toutes les infos de l'utilisateur
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
					
					// je dirige vers la page d'accueil connecté
					request.getRequestDispatcher("accueilConnecteVue.jsp").forward(request, response);
			
					
				} catch (UtilisateurExceptionBLL e) {
					e.printStackTrace();
					request.setAttribute("message1", e.getMessage());
					System.out.println("erreur lors de la modification de l'utilisateur");
				}
			}else {
				request.setAttribute("message2", "Erreur lors de la confirmation du mot de passe");
				
			}
			} else {
				request.setAttribute("message4", "Erreur sur le mot de Passe Actuel");
			}
			
	}
		
		// si l'utilisateur clique sur le bouton supprimer
		if (request.getParameter("delete")!=null) {
			// si le mot de passe est le bon
			if (mdpActuel.equals(request.getSession().getAttribute("motDePasse"))) {
				
				// je supprime l'utilisateur de la liste de BDD
				
				try {
					um.deleteUtilisateur((String)request.getSession().getAttribute("pseudo"));
				} catch (UtilisateurExceptionBLL e1) {
					request.setAttribute("message5", e1.getMessage());
					e1.printStackTrace();
				}
						
				// je modifie le model
				try {
					model.setListUtilisateur(um.getListUtilisateur());
				} catch (UtilisateurExceptionBLL e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("message3", e.getMessage());
				}
				// je mets à jour le model
				request.setAttribute("model", model);
				//je renvoie vers la page accueil non connecté
				request.getRequestDispatcher("accueilNonConnecteVue.jsp").forward(request, response);
				
			
		} else {
			request.setAttribute("message4", "Erreur sur le mot de Passe Actuel");
		}
		}
	
		request.getRequestDispatcher("modifProfil.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
