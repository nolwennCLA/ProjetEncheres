package fr.eni.ProjetEncheres.dal.enchere;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManager;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManagerSing;
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
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;
import fr.eni.ProjetEncheres.dal.retrait.DAORetrait;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDALException;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDAO;

public class Test_DAL_Enchere {

	private static EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
	private static DAOArticle articleDao = DAOFactory.getDAOArticle();
	private static UtilisateurDAO utilisateurDao = DAOFactory.getUtilisateurDAO();
	private static DAORetrait retraitDao = DAOFactory.getDAORetrait();
	private static DAOCategorie categorieDao = DAOFactory.getDAOCategorie();

	public static void main(String[] args) throws EnchereDALException, ParseException, UtilisateurDALException, DAL_ArticleException, DAL_RetraitException, DAL_CategorieException, UtilisateurExceptionBLL, BLL_CategorieException, BLL_ArticleException, BLL_RetraitException {


		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UtilisateurManager um = UtilisateurManagerSingl.getInstance();
		CategorieManager cm = CategorieManagerSing.getInstance();
		ArticleManager am = ArticleManagerSing.getInstance();
		RetraitManager rm = RetraitManagerSing.getInstance();
		

		Utilisateur ut = new Utilisateur("1", "nom", "prenom", "email", "telephone", "rue", "codePostal", "ville", "motDePasse");
		Utilisateur ut2 = new Utilisateur("2", "nom", "prenom", "email", "telephone", "rue", "codePostal", "ville", "motDePasse");
		Retrait re = new Retrait("rue", "codePostal", "ville");
		Categorie cat = new Categorie("meuble");
//		Article art = new Article("nomArticle", "description", aujourdhui, sdf.parse(demain), 200, ut, cat, re);
//		Article art = new Article("nomArticle", "description", aujourdhui, sdf.parse(demain), 50, cat, ut, re);
//		Enchere ench = new Enchere(aujourdhui, 40, ut, art);
		
		//Insertion des utilisateurs dans la BDD
//		utilisateurDao.insert(ut);
//		utilisateurDao.insert(ut2);
		
		//Insertion du retrait dans la BDD
//		retraitDao.insert(re);
		
		//Insertion de la catégorie
//		categorieDao.insert(cat);


		Utilisateur user = um.getUtilisateurParId(7);
		Categorie cat1 = cm.selectionnerCategorie(3);
//		Retrait ret1 = rm.selectionnerRetrait(3);
		Article art1 = am.selectionnerArticleParId(2);

		Article art = new Article("peigne", "peigne traditionnel", sdf.parse("2021-01-20"), sdf.parse("2021-01-25"),
				150, 0, "AV", cat1, user, null);

//		articleDao.insert(art);
		
//		System.out.println(art.getNoArticle());
//		System.out.println(ut.getNoUtilisateur());
		
		Enchere ench = new Enchere(sdf.parse("2021-01-15"), 50, user, art1);
		enchereDAO.insert(ench);
		

	}

}
