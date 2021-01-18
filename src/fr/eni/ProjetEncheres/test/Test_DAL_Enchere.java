package fr.eni.ProjetEncheres.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManager;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManagerSing;
import fr.eni.ProjetEncheres.bll.enchere.EnchereExceptionBLL;
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
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.article.DAOArticle;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.categorie.DAOCategorie;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDALException;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDAO;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;
import fr.eni.ProjetEncheres.dal.retrait.DAORetrait;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDALException;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDAO;

public class Test_DAL_Enchere {

	public static void main(String[] args) throws UtilisateurExceptionBLL, BLL_ArticleException, DAL_ArticleException,
			BLL_RetraitException, DAL_RetraitException, ParseException, EnchereDALException, BLL_CategorieException,
			DAL_CategorieException, EnchereExceptionBLL, UtilisateurDALException {
		
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
       
        
       
        Utilisateur user = um.getUtilisateurParId(7);
        Utilisateur user2 = um.getUtilisateurParId(8);
//        Article art1 = am.selectionnerArticle(2);
       
       
        
        Utilisateur user3 = new Utilisateur("1", "nom", "prenom", "email", "telephone", "rue", "codePostal", "ville", "motDePasse");
//        utilisateurDAO.insert(user3);
        Utilisateur user4 = um.getUtilisateurParId(9);
        
        Categorie cat1 = new Categorie("meuble");    
        Categorie cat = cm.selectionnerCategorie(3);
//        
        Retrait ret1 = new Retrait("rue2", "codePostal2", "ville2");
//        retraitDAO.insert(ret1);
        Retrait ret = rm.selectionnerRetrait(3);
//        
        
        Article art2 = new Article("nomArticle", "description", sdf.parse("2021-01-21"), sdf.parse("2021-01-25"), 45, 0, "AV", cat, user, null);
//        am.creerArticle(art2, ret1);
        Article art3 = am.selectionnerArticle(6);
       
        
        Utilisateur user_3 = new Utilisateur("3", "nom", "prenom", "email3", "telephone", "rue", "codePostal", "ville", "motDePasse");
        Utilisateur user_4 = new Utilisateur("4", "nom", "prenom", "email4", "telephone", "rue", "codePostal", "ville", "motDePasse");
//        utilisateurDAO.insert(user_3);
//        utilisateurDAO.insert(user_4);
//        um.addUtilisateur(user_3);
//        um.addUtilisateur(user_4);
//        um.deleteUtilisateur(7);
//        um.deleteUtilisateur(8);
//        um.deleteUtilisateur(9);
        Utilisateur user_30 = um.getUtilisateurParId(10);
        Utilisateur user_40 = um.getUtilisateurParId(11);
        
        
        Article art4  =new Article("nomArticle", "description", sdf.parse("2021-01-21"), sdf.parse("2021-01-25"), 10, 0, "AV", cat, user_30, null);
//        am.creerArticle(art4, ret1);
        Article art_4 = am.selectionnerArticle(7);
        
        
        Enchere ench = new Enchere(sdf.parse("2021-01-22"), 51, user, art3);
        Enchere ench2 = new Enchere(sdf.parse("2021-01-22"), 55, user2, art3);
        Enchere ench3 = new Enchere(sdf.parse("2021-01-22"), 49, user4, art3); 
//        em.addEnchere(ench);
//        em.addEnchere(ench2);
//        em.addEnchere(ench3);
        

//        em.deleteEnchere(13);
        
        Enchere ench4 = new Enchere(sdf.parse("2021-01-22"), 14, user_30, art_4);
//        em.addEnchere(ench4);
        Enchere ench5 = new Enchere(sdf.parse("2021-01-22"), 12, user_40, art_4);
//        em.addEnchere(ench5);
        Enchere ench6 = new Enchere(sdf.parse("2021-01-22"), 15, user_30, art_4);
//      em.addEnchere(ench4);

    
        //Test des méthodes listes
      System.out.println(em.getListEnchere());
//      System.out.println(em.getListEnchereParCategorie(cat1)); //-> essayer avec une requete sql ?
       

	}

}
