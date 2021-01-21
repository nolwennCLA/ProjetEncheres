package fr.eni.ProjetEncheres.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManager;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManagerSing;
import fr.eni.ProjetEncheres.bll.enchere.BLL_EnchereException;
import fr.eni.ProjetEncheres.bll.enchere.EnchereManager;
import fr.eni.ProjetEncheres.bll.enchere.EnchereManagerSing;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManager;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManagerSing;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.article.DAOArticle;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.categorie.DAOCategorie;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.enchere.DAL_EnchereException;
import fr.eni.ProjetEncheres.dal.enchere.DAOEnchere;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;
import fr.eni.ProjetEncheres.dal.retrait.DAORetrait;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDALException;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDAO;

public class TestClaire {
	
	 public static void main(String[] args)
	            throws UtilisateurExceptionBLL, UtilisateurDALException, BLL_CategorieException, DAL_CategorieException,
	            ParseException, BLL_ArticleException, BLL_RetraitException, DAL_ArticleException, DAL_RetraitException, BLL_EnchereException, DAL_EnchereException {

	 

	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	 

	        UtilisateurManager um = UtilisateurManagerSingl.getInstance();
	        ArticleManager am = ArticleManagerSing.getInstance();
	        EnchereManager em = EnchereManagerSing.getInstance();
	        RetraitManager rm = RetraitManagerSing.getInstance();
	        CategorieManager cm = CategorieManagerSing.getInstance();

	 

	        DAOEnchere enchereDAO = DAOFactory.getDAOEnchere();
	        UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
	        DAOArticle articleDAO = DAOFactory.getDAOArticle();
	        DAORetrait retraitDAO = DAOFactory.getDAORetrait();
	        DAOCategorie categorieDAO = DAOFactory.getDAOCategorie();

	 

	        
	        // *********************************************************************
	        // *********** CREATION UTILISATEUR-CATEGORIE-RETRAIT ******************
	        // *********************************************************************

	 

	        Utilisateur claire = new Utilisateur("claire", "denis", "claire", "claire@eni", "0298", "rue", "29000",
	                "Quimper", "ClDe");
	        Utilisateur nolwenn = new Utilisateur("nolwenn", "claquin", "nolwenn", "nolwenn@eni", "0298", "rue", "29120",
	                "Combrit", "NoCl");
	        Utilisateur vincent = new Utilisateur("vincent", "lebras", "vincent", "vincent@eni", "0298", "rue", "29000",
	                "Quimper", "ViLb");
	        Categorie catInf = new Categorie("informatique");
	        Categorie catAmeu = new Categorie("ameublement");
	        Categorie catVet = new Categorie("vêtement");
	        Categorie catSL = new Categorie("Sport&Loisirs");
	        Retrait ret = new Retrait("rue1", "29000", "Quimper");
	        
	        // Décommenter les lignes suivantes pour l'insertion dans la BDD : les recommenter quand c'est fait pour éviter les doublons
//	       um.addUtilisateur(claire);
//	       um.addUtilisateur(nolwenn);
//	       um.addUtilisateur(vincent);
//	         cm.creerCategorie(catInf);
	          cm.creerCategorie(catAmeu);
	          cm.creerCategorie(catVet);
	          cm.creerCategorie(catSL);

	 

	        // Récupération des id des utilisateurs : attention à bien noter le n°
	        // d'utilisateur venant de votre BDD
	        Utilisateur claire1 = um.getUtilisateurParId(3);
	        Utilisateur nolwenn1 = um.getUtilisateurParId(4);
	        Utilisateur vincent1 = um.getUtilisateurParId(5);

	 

	        // Récupération des id des catégories : attention à bien noter le n° de
	        // catégorie venant de votre BDD
	        Categorie catInf1 = cm.selectionnerCategorie(1);
	        Categorie catAmeu1 = cm.selectionnerCategorie(2);
	        Categorie catVet1 = cm.selectionnerCategorie(3);
	        Categorie catSL1 = cm.selectionnerCategorie(4);

	 


	        // ***********************************************
	        // *********** CREATION ARTICLE ******************
	        // ***********************************************
	        
	        Article ordi = new Article("ordi", "Mac", sdf.parse("2021-01-20"), sdf.parse("2021-01-25"), 10, 0, "AV",
	                catInf1, claire1, null);
	        Article armoire = new Article("armoire", "blabla", sdf.parse("2021-01-20"), sdf.parse("2021-01-25"), 10, 0,
	                "AV", catAmeu1, nolwenn1, null);
	        Article pull = new Article("pull", "pull noir", sdf.parse("2021-01-20"), sdf.parse("2021-01-25"), 10, 0, "AV",
	                catVet1, vincent1, null);
	        Article velo = new Article("vélo", "BTwin décathlon", sdf.parse("2021-01-20"), sdf.parse("2021-01-25"), 15, 0,
	                "AV", catSL1, claire1, null);
	        
	        // Décommenter les lignes suivantes pour l'insertion dans la BDD : les recommenter quand c'est fait pour éviter les doublons
//	        am.creerArticle(ordi, ret);
//	        am.creerArticle(armoire, ret);
//	        am.creerArticle(pull, ret);
//	           am.creerArticle(velo, ret);
	        
	        //Récupération des id des articles : attention à bien noter le n° d'article venant de votre BDD
	        Article ordi1 = am.selectionnerArticleParId(10);
	        Article armoire1 = am.selectionnerArticleParId(11);
	        Article pull1 = am.selectionnerArticleParId(12);
	        Article velo1 = am.selectionnerArticleParId(13);
	        
	        
	        // ***********************************************
	        // *********** CREATION ENCHERE ******************
	         // ***********************************************
	        
	        Enchere ench1 = new Enchere(sdf.parse("2021-01-20"), 15, claire1, ordi1);
	        Enchere ench2 = new Enchere (sdf.parse("2021-01-20"), 12, nolwenn1, ordi1);
//	      em.addEnchere(ench1);
	        em.creerEnchere(ench2);

}}
