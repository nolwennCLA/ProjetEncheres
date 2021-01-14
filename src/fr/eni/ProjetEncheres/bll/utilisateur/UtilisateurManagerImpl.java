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
		List<Utilisateur>list = new ArrayList<Utilisateur>();
		try {
			list= utilDAO.selectAll();
		} catch (UtilisateurDALException e1) {
			e1.printStackTrace();
			throw new UtilisateurExceptionBLL("probleme à l'insertion");
		}
		
		for (Utilisateur u : list) {
			if (!u.getPseudo().equals(utilisateur.getPseudo())) {
				try {
					
					utilDAO.insert(utilisateur);
					
					
				} catch (UtilisateurDALException e) {
					e.printStackTrace();
					throw new UtilisateurExceptionBLL("insertion de l'utilisateur impossible");
				}
				
				
			}else {
				System.out.println("Ce pseudo existe déjà");
			}
		}
		
		
		
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
		List<Utilisateur>list = new ArrayList<Utilisateur>();
		try {
			list= utilDAO.selectAll();
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
			throw new UtilisateurExceptionBLL("selection de la liste d'utilisateurs impossible");
		}
		return list;
	}

	@Override
	public Utilisateur getUtilisateur(String pseudo) throws UtilisateurExceptionBLL {
		try {
			utilDAO.selectByPseudo(pseudo);
		} catch (UtilisateurDALException e) {
			e.printStackTrace();
			throw new UtilisateurExceptionBLL("selection de l'utilisateur impossible");
		}
		return null;
	}

}
