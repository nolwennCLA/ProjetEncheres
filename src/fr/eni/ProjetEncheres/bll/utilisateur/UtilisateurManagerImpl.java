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
	public void addUtilisateur(Utilisateur utilisateur) throws UtilisateurExceptionBLL {
	
//		if (utilisateur.getPseudo().matches(".*\\w.*")) {
//			System.out.println("le pseudo est bien");
//		}else {
//			System.out.println("le pseudo n'est pas bon");
//		}
		
		
		if (verifPseudo(utilisateur) || verifEmail(utilisateur)) {
			try {
				utilDAO.insert(utilisateur);

			} catch (UtilisateurDALException e) {
				e.printStackTrace();
				throw new UtilisateurExceptionBLL("insertion de l'utilisateur impossible");
			}
		}

	}

	// RG: L'email doit etre unique
	private boolean verifEmail(Utilisateur utilisateur) throws UtilisateurExceptionBLL {
		List<Utilisateur> list = new ArrayList<Utilisateur>();
		boolean ok = false;
		try {
			// je récupere la liste de la BDD
			list = utilDAO.selectAll();
		} catch (UtilisateurDALException e1) {
			e1.printStackTrace();
			throw new UtilisateurExceptionBLL("probleme à la récuperation de la liste pour verif Pseudo");
		}
		// je parcours la liste
		for (Utilisateur u : list) {

			if (!u.getEmail().equals(utilisateur.getEmail())) {
				ok=true;
			} else {
				System.out.println("Cet email existe déjà");
			}
		}
		return ok;
	}

	// RG: le pseudo doit etre unique
	private boolean verifPseudo(Utilisateur utilisateur) throws UtilisateurExceptionBLL {
		List<Utilisateur> list = new ArrayList<Utilisateur>();
		boolean ok = false;
		try {
			// je récupere la liste de la BDD
			list = utilDAO.selectAll();
		} catch (UtilisateurDALException e1) {
			e1.printStackTrace();
			throw new UtilisateurExceptionBLL("probleme à la récuperation de la liste pour verif Pseudo");
		}
		// je parcours la liste
		for (Utilisateur u : list) {

			if (!u.getPseudo().equals(utilisateur.getPseudo())) {
				ok = true;

			} else {
				
				System.out.println("Ce pseudo existe déjà");
			}

		}
		return ok;
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
