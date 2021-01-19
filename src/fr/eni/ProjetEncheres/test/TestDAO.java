package fr.eni.ProjetEncheres.test;

import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDALException;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDAO;

public class TestDAO {

	private static UtilisateurDAO dao = DAOFactory.getUtilisateurDAO();
	
	public static void main(String[] args) throws UtilisateurDALException {
	
		//dao.insert(new Utilisateur("nono", "cla", "nolw", "abc", "0298870000", "rue du grand large", "29000", "quimper", "bidule"));
	//dao.delete(7);
//	Utilisateur claire= new Utilisateur("clacla", "denis", "claire", "email", "telephone", "rue", "codePostal", "ville", "motDePasse");
//		dao.insert(claire);
	
//		claire.setPseudo("galette des rois");
//		dao.update(claire);
		
		//dao.insert(new Utilisateur("doudou", "ours", "peluche", " ", "telephone", "rue", "29120", "PA", "doudou"));
		//System.out.println(dao.selectAll());
		
		
//		System.out.println(dao.selectById(10));
//		Utilisateur Vincent= new Utilisateur("vinc", "le bras", "vincent", "tyu", "  ", "lkj", "2580", "quimper", "password");
//		Utilisateur Julien= new Utilisateur("jul", "clerc", "julien", "lh", "  ", "lkj", "2580", "quimper", "password");
//		dao.insert(Julien);
//		dao.insert(Vincent);
		
//		Vincent.setPseudo("abc");
//		dao.update(Vincent);
//		
	System.out.println(dao.selectById(45));
		
		
	
	}

}
