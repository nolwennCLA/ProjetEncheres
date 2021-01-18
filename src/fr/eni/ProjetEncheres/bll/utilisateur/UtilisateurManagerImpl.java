package fr.eni.ProjetEncheres.bll.utilisateur;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDALException;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDAO;

public class UtilisateurManagerImpl implements UtilisateurManager {
	UtilisateurDAO utilDAO = DAOFactory.getUtilisateurDAO();

	@Override
	public Utilisateur addUtilisateur(Utilisateur utilisateur) throws UtilisateurExceptionBLL {

		Utilisateur util = new Utilisateur();

		// d'abord vérifier que le pseudo n'accepte que les caracteres alphanumérique
		// expression regulière: le \w veut dire caractere alphanumérique et le * signifie autant de caracteres que l'on veut
		// le 2eme \ est pour pouvoir utiliser le \w en tant qu'expression régulière.
		
		if (utilisateur.getPseudo().matches("\\w*")) {

			// puis verifier que le pseudo et l'email sont uniques
			if (!verifPseudo(utilisateur) && !verifEmail(utilisateur)) {
				try {
					utilDAO.insert(utilisateur);

				} catch (UtilisateurDALException e) {
					e.printStackTrace();
					throw new UtilisateurExceptionBLL("insertion de l'utilisateur impossible");
				}
			}else {
				throw new UtilisateurExceptionBLL("le pseudo et/ou l'email existent déjà. Veuillez recommencer la saisie");
			}
		} else {
			System.out.println("le pseudo ne doit contenir que les chiffres ou des lettres");
		}

		return util;
	}

	// RG: L'email doit etre unique
	private boolean verifEmail(Utilisateur utilisateur) throws UtilisateurExceptionBLL {
		List<Utilisateur> list = new ArrayList<Utilisateur>();
		boolean probleme = false;
		try {
			// je récupere la liste de la BDD
			list = utilDAO.selectAll();
		} catch (UtilisateurDALException e1) {
			e1.printStackTrace();
			throw new UtilisateurExceptionBLL("probleme à la récuperation de la liste pour verif Pseudo");
		}
		// je parcours la liste
		for (Utilisateur u : list) {

			if (u.getEmail().equals(utilisateur.getEmail())) {
				probleme = true;
				System.out.println("Cet email existe déjà");
			}
		}

		return probleme;
	}

	// RG: le pseudo doit etre unique
	private boolean verifPseudo(Utilisateur utilisateur) throws UtilisateurExceptionBLL {
		List<Utilisateur> list = new ArrayList<Utilisateur>();
		boolean probleme = false;
		try {
			// je récupere la liste de la BDD
			list = utilDAO.selectAll();
		} catch (UtilisateurDALException e1) {
			e1.printStackTrace();
			throw new UtilisateurExceptionBLL("probleme à la récuperation de la liste pour verif Pseudo");
		}

		// je parcours la liste
		for (Utilisateur u : list) {

			if (u.getPseudo().equals(utilisateur.getPseudo())) {
				probleme = true;
				System.out.println("Ce pseudo existe déjà");

			}

		}

		return probleme;
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws UtilisateurExceptionBLL {
		try {
			utilDAO.update(utilisateur);
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
			throw new UtilisateurExceptionBLL("modification de l'utilisateur impossible");
		}

	}

	@Override
	public void deleteUtilisateur(Integer id) throws UtilisateurExceptionBLL {
		try {
			utilDAO.delete(id);
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
			throw new UtilisateurExceptionBLL("suppression de l'utilisateur impossible");
		}

	}

	@Override
	public List<Utilisateur> getListUtilisateur() throws UtilisateurExceptionBLL {
		List<Utilisateur> list = new ArrayList<Utilisateur>();
		try {
			list = utilDAO.selectAll();
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
			throw new UtilisateurExceptionBLL("selection de la liste d'utilisateurs impossible");
		}
		return list;
	}

	@Override
	public Utilisateur getUtilisateurParPseudo(String pseudo) throws UtilisateurExceptionBLL {
		Utilisateur utilisateur = new Utilisateur();
		try {
			utilisateur = utilDAO.selectByPseudo(pseudo);
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
			throw new UtilisateurExceptionBLL("selection de l'utilisateur par pseudo impossible");
		}
		return utilisateur;
	}

	@Override
	public Utilisateur getUtilisateurParId(Integer id) throws UtilisateurExceptionBLL {
		Utilisateur utilisateur = new Utilisateur();
		try {
			utilisateur = utilDAO.selectById(id);
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
			throw new UtilisateurExceptionBLL("selection de l'utilisateur par id impossible");
		}
		return utilisateur;
	}
}
