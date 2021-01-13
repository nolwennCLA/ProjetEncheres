package fr.eni.ProjetEncheres.test;

import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.utilisateur.DAOFactory;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDALException;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDAO;

public class TestDAO {

	private static UtilisateurDAO dao = DAOFactory.getUtilisateurDAO();
	
	public static void main(String[] args) throws UtilisateurDALException {
	
		//dao.insert(new Utilisateur("nono", "cla", "nolw", "abc", "0298870000", "rue du grand large", "29000", "quimper", "bidule"));
	dao.delete(2);
//		Utilisateur claire= new Utilisateur("clacla", "denis", "claire", "email", "telephone", "rue", "codePostal", "ville", "motDePasse");
//		dao.insert(claire);
//		claire.setPseudo("deleguée");
//		dao.update(claire);
	
	}

}
