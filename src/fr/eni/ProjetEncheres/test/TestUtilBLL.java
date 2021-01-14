package fr.eni.ProjetEncheres.test;

import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Utilisateur;

public class TestUtilBLL {
	public static void main(String[] args) throws UtilisateurExceptionBLL {
		
		UtilisateurManager um= new UtilisateurManagerSingl().getInstance();
		
		Utilisateur Vincent= new Utilisateur("vinc", "le bras", "vincent", "tyu", "  ", "lkj", "2580", "quimper", "password");
		Utilisateur Julien= new Utilisateur("jul", "clerc", "julien", "lh", "  ", "lkj", "2580", "quimper", "password");
		//um.addUtilisateur(Vincent);
		//Vincent.setPseudo("hello");
		//um.updateUtilisateur(Vincent);
		//um.addUtilisateur(Julien);
//		Julien.setNom("lepers");
//		um.updateUtilisateur(Julien);
		//um.deleteUtilisateur(5);
		//System.out.println(um.getListUtilisateur());
		Utilisateur Julie= new Utilisateur("jul", "jolie", "julie", "ffglflyufv", "  ", "lkj", "29750", "loctudy", "password");
		
		//um.addUtilisateur(Julie);
		
		
		//System.out.println(um.getUtilisateurParPseudo("vinc"));
		System.out.println(um.getUtilisateurParId(10));
		
	}

}
