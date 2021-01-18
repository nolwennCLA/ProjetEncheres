package fr.eni.ProjetEncheres.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManager;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManagerSing;
import fr.eni.ProjetEncheres.bll.enchere.EnchereManager;
import fr.eni.ProjetEncheres.bll.enchere.EnchereManagerSingl;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManager;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManagerSing;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.article.DAOArticle;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.categorie.DAOCategorie;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDAO;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;
import fr.eni.ProjetEncheres.dal.retrait.DAORetrait;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDALException;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDAO;

public class Test_DAL_Enchere2 {

	public static void main(String[] args) throws UtilisateurExceptionBLL, UtilisateurDALException, BLL_CategorieException, DAL_CategorieException, ParseException, BLL_ArticleException, BLL_RetraitException, DAL_ArticleException, DAL_RetraitException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	       
        UtilisateurManager um = UtilisateurManagerSingl.getInstance();
        ArticleManager am = ArticleManagerSing.getInstance();
        EnchereManager em = EnchereManagerSingl.getInstance();
        RetraitManager rm = RetraitManagerSing.getInstance();
        CategorieManager cm = CategorieManagerSing.getInstance();
       
        EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
        UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
        DAOArticle articleDAO = DAOFactory.getDAOArticle();
        DAORetrait retraitDAO = DAOFactory.getDAORetrait();
        DAOCategorie categorieDAO = DAOFactory.getDAOCategorie();
       
		
        //*********** CREATION UTILISATEUR ******************
        Utilisateur claire = new Utilisateur("claire", "denis", "claire", "claire@eni", "0298", "rue", "29000", "Quimper", "ClDe");
        Utilisateur nolwenn = new Utilisateur("nolwenn", "claquin", "nolwenn", "nolwenn@eni", "0298", "rue", "29120", "Combrit", "NoCl");
        Utilisateur vincent = new Utilisateur("vincent", "lebras", "vincent", "vincent@eni", "0298", "rue", "29000", "Quimper", "ViLb");
//        um.addUtilisateur(claire);
//        um.addUtilisateur(nolwenn);
//        um.addUtilisateur(vincent);
        Utilisateur claire1 = um.getUtilisateurParId(12);
        Utilisateur nolwenn1 = um.getUtilisateurParId(13);
        Utilisateur vincent1 = um.getUtilisateurParId(14);
        
        
      //*********** CREATION CATEGORIE ******************
        Categorie catInf = new Categorie("informatique");
        Categorie catAmeu = new Categorie("ameublement");
        Categorie catVet = new Categorie("vêtement");
//      cm.creerCategorie(catInf);
//      cm.creerCategorie(catAmeu);
//      cm.creerCategorie(catVet);
        Categorie catInf1 = cm.selectionnerCategorie(5);
        Categorie catAmeu1 = cm.selectionnerCategorie(6);
        Categorie catVet1 = cm.selectionnerCategorie(7);
        
        
      //*********** CREATION RETRAIT ******************
        Retrait ret = new Retrait("rue1", "29000", "Quimper");
        
      //*********** CREATION ARTICLE ******************
        Article ordi = new Article("ordi", "Mac", sdf.parse("2021-01-20"), sdf.parse("2021-01-25"), 10, 0, "AV", catInf1, claire1, null);
        Article armoire = new Article("armoire", "blabla", sdf.parse("2021-01-20"), sdf.parse("2021-01-25"), 10, 0, "AV", catAmeu1, nolwenn1, null);
        Article pull = new Article("pull", "pull noir", sdf.parse("2021-01-20"), sdf.parse("2021-01-25"), 10, 0, "AV", catVet1, vincent1, null);
//        am.creerArticle(ordi, ret);
//        am.creerArticle(armoire, ret);
//        am.creerArticle(pull, ret);
        Article ordi1 = am.selectionnerArticle(10);
      

        
        
        
        
        
		

	}

}
