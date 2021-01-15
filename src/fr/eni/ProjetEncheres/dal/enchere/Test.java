package fr.eni.ProjetEncheres.dal.enchere;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManager;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManagerSing;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.article.DAOArticle;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;

public class Test {

	public static void main(String[] args) throws UtilisateurExceptionBLL, BLL_CategorieException, DAL_CategorieException, ParseException, DAL_ArticleException {

		DAOArticle articleDao = DAOFactory.getDAOArticle();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UtilisateurManager um = UtilisateurManagerSingl.getInstance();
		CategorieManager cm = CategorieManagerSing.getInstance();

		Utilisateur user = um.getUtilisateurParId(7);
		Categorie cat = cm.selectionnerCategorie(3);

		Article art = new Article("peigne", "peigne traditionnel", sdf.parse("2021-01-20"), sdf.parse("2021-01-25"),
				150, 0, "AV", cat, user, null);

		articleDao.insert(art);
	}

}
