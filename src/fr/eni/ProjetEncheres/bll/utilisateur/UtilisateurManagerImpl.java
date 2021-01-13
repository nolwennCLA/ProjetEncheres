package fr.eni.ProjetEncheres.bll.utilisateur;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Utilisateur;

public class UtilisateurManagerImpl implements UtilisateurManager {
	//TODO enlever la list et faire le lien avec la DAL
	List<Utilisateur>listUtilisateur = new ArrayList<Utilisateur>();

	@Override
	public void addUtilisateur(Utilisateur utilisateur) throws UtilisateurExceptionBLL {
		listUtilisateur.add(utilisateur);
		
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws UtilisateurExceptionBLL {
		// TODO relier avec la DAL pour faire le uptdate
		
	}

	@Override
	public void deleteUtilisateur(Integer id) throws UtilisateurExceptionBLL {
		// TODO relier avec la DAL 
		
	}


	@Override
	public List<Utilisateur> getListUtilisateur() throws UtilisateurExceptionBLL {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur getUtilisateur(Integer id) throws UtilisateurExceptionBLL {
		// TODO Auto-generated method stub
		return null;
	}

}
