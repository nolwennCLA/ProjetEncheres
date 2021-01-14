package fr.eni.ProjetEncheres.bll.enchere;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.bo.Utilisateur;

public class TestBLL {

	private static EnchereManager manager = EnchereManagerSingl.getInstance();
	
	public static void main(String[] args) throws ParseException, EnchereException {

		Date aujourdhui = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String demain = "15-01-2021";
		

		Utilisateur ut = new Utilisateur("1", "nom", "prenom", "email", "telephone", "rue", "codePostal", "ville", "motDePasse");
		Utilisateur ut2 = new Utilisateur("2", "nom", "prenom", "email", "telephone", "rue", "codePostal", "ville", "motDePasse");
		Categorie cat = new Categorie("meuble");
		Article art = new Article("nomArticle", "description", aujourdhui, sdf.parse(demain), 200, ut, cat);
		Article art2 = new Article("nomArticle", "description", aujourdhui, sdf.parse(demain), 50, ut, cat);
		Enchere ench = new Enchere(aujourdhui, 100, ut, art);
		Enchere ench2 = new Enchere(aujourdhui, 60, ut, art2);
		Enchere ench3 = new Enchere(aujourdhui, 60, ut, art2);
		Enchere ench4 = new Enchere(aujourdhui, 50, ut, art2);
		Enchere ench5 = new Enchere(aujourdhui, 65, ut2, art2);

		
//		********* NE COMMENTER QUE LE BLOC A TESTER ***********
		
		//test RG1 : mon prix proposé > tarif actuel de l'article
//		manager.faireEnchere(ench);
//		manager.faireEnchere(ench4);
		
		//test RG2 : enchère acceptée si mon nombre de points ne devient pas négatif + re-créditation de l'ancien anchérisseur
		System.out.println("Mise à prix initiale de l'article : " + art2.getMiseAPrix() + " points");
		System.out.println("vous êtes l'utilisateur " + ench2.getUtilisateur().getPseudo() + ", votre offre : " + ench2.getMontantEnchere() + " points");
		manager.faireEnchere(ench2);
		System.out.println("Nouvelle mise à prix de l'article : " + art2.getMiseAPrix() + " points");
		System.out.println("vous êtes l'utilisateur " + ench5.getUtilisateur().getPseudo() + ", votre offre : " + ench5.getMontantEnchere() + " points");
		manager.faireEnchere(ench5);
		System.out.println("Nouvelle mise à prix de l'article : " + art2.getMiseAPrix() + " points");
		

		
		

	}

}
