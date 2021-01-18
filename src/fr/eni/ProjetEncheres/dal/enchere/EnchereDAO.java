package fr.eni.ProjetEncheres.dal.enchere;

import java.util.List;

import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public interface EnchereDAO {

	public Enchere insert(Enchere enchere) throws EnchereDALException;

	public void delete(Integer id) throws EnchereDALException;

	public void update(Enchere enchere) throws EnchereDALException;

	public List<Enchere> selectAll()
			throws EnchereDALException, UtilisateurExceptionBLL, BLL_ArticleException, DAL_ArticleException,
			BLL_RetraitException, DAL_RetraitException, BLL_CategorieException, DAL_CategorieException;

	public void updateUtilisateur(Enchere enchere) throws EnchereDALException;

	public Enchere selectById(Integer id)
			throws EnchereDALException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException,
			DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;

	public Enchere selectMeilleurOffre(Integer noArticle) throws EnchereDALException,
			BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
}
