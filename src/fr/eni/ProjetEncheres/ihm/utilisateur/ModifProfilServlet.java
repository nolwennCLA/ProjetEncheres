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
		
		if (request.getParameter("creer")!=null) {
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("tel");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("cpo");
			String ville = request.getParameter("ville");
			String NouveauMotDePasse = request.getParameter("nouveauMdp");
			String ConfirmationMotDePasse = request.getParameter("conf");
			
			//TODO verifier le mot de passe actuel
			
			if (NouveauMotDePasse.equals(ConfirmationMotDePasse)) {
				Utilisateur util = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, NouveauMotDePasse);
				try {
					um.updateUtilisateur(util);
					
				} catch (UtilisateurExceptionBLL e) {
					e.printStackTrace();
					request.setAttribute("message1", e.getMessage());
					System.out.println("erreur lors de la modification de l'utilisateur");
				}
			}else {
				request.setAttribute("message2", "Erreur lors de la confirmation du mot de passe");
				
			}
			
	}
//		if (request.getParameter("delete")!= null) {
//			//TODO faire un delete par pseuso et ici recuperer le pseudo dans la session
//			um.deleteUtilisateur(id);
//		}
		
		try {
			model.setListUtilisateur(um.getListUtilisateur());
		} catch (UtilisateurExceptionBLL e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message3", e.getMessage());
		}
		
		request.setAttribute("model", model);
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
