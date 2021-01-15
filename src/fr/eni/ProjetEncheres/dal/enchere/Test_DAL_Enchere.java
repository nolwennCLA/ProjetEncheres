package fr.eni.ProjetEncheres.dal.enchere;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;

public class Test_DAL_Enchere {

	private static EnchereDAO dao = DAOFactory.getEnchereDAO();

	public static void main(String[] args) throws EnchereDALException, ParseException {

		Date aujourdhui = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String demain = "15-01-2021";

		
		Utilisateur ut = new Utilisateur("1", "nom", "prenom", "email", "telephone", "rue", "codePostal", "ville", "motDePasse");
		Categorie cat = new Categorie("meuble");
//		Article art = new Article("nomArticle", "description", aujourdhui, sdf.parse(demain), 200, ut, cat);
//		Enchere ench = new Enchere(aujourdhui, 40, ut, art);
		
//		dao.insert(ench);

	}

}
