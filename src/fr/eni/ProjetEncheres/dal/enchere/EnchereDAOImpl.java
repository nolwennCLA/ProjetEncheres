package fr.eni.ProjetEncheres.dal.enchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.dal.ConnectionProvider;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public class EnchereDAOImpl implements EnchereDAO {

	private final String INSERT = "INSERT into ENCHERE (dateEnchere, montantEnchere, noUtilisateur, noArticle) values (?,?,?,?)";
	private final String DELETE = "DELETE from ENCHERE where noEnchere=?";
	private final String UPDATE_UTILISATEUR = "UPDATE UTILISATEUR set credit=? where noUtilisateur=?";
	private final String UPDATE_RECREDITATION = "";
	private final String SELECT_ALL = "SELECT * from ENCHERE";
	private final String SELECTBYID = "select * from ENCHERE where noEnchere = ?";
	private final String SELECT_MEILLEURE_OFFRE = "SELECT max(montantEnchere) from ENCHERE where noArticle=?";

	private UtilisateurManager um = UtilisateurManagerSingl.getInstance();
	private ArticleManager am = ArticleManagerSing.getInstance();

	@Override
	public Enchere insert(Enchere enchere) throws EnchereDALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			// conversion des dates, du format java.util.date vers le format java.sql.date
			java.sql.Date dateEnchereSQL;
			dateEnchereSQL = new java.sql.Date(enchere.getDateEnchere().getTime());

			// alimentation de la requête
			stmt.setDate(1, dateEnchereSQL);
			stmt.setInt(2, enchere.getMontantEnchere());
			stmt.setInt(3, enchere.getUtilisateur().getNoUtilisateur());
			stmt.setInt(4, enchere.getArticle().getNoArticle());

			// exécution de a requête
			stmt.executeUpdate();

			// récupération de la clé générée
			ResultSet rs = stmt.getGeneratedKeys();

			// si une clé a bien été générée, alors on l'intégre à l'Enchere
			if (rs.next()) {
				enchere.setNoEnchere(rs.getInt(1));
				System.out.println(enchere.toString());
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EnchereDALException("Problème dans l'insertion d'une enchère ");
		}
		return enchere;
	}

	@Override
	public void delete(Integer id) throws EnchereDALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(DELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EnchereDALException("Problème dans le delete d'une enchère ");
		}

	}

	public void updateUtilisateur(Enchere enchere) throws EnchereDALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_UTILISATEUR);
			stmt.setInt(1, enchere.getUtilisateur().getCredit());
			stmt.setInt(2, enchere.getUtilisateur().getNoUtilisateur());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EnchereDALException("problème pour modifier l'enchère");
		}
	}

	@Override
	public List<Enchere> selectAll()
			throws EnchereDALException, UtilisateurExceptionBLL, BLL_ArticleException, DAL_ArticleException,
			BLL_RetraitException, DAL_RetraitException, BLL_CategorieException, DAL_CategorieException {
		List<Enchere> list = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("noEnchere"));
				enchere.setDateEnchere(rs.getDate("dateEnchere"));
				enchere.setMontantEnchere(rs.getInt("montantEnchere"));
				enchere.setArticle(am.selectionnerArticleParId(rs.getInt("noArticle")));
				enchere.setUtilisateur(um.getUtilisateurParId(rs.getInt("noUtilisateur")));

				list.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EnchereDALException("problème avec la liste des enchères");
		}
		return list;
	}

	@Override
	public void update(Enchere enchere) throws EnchereDALException {
		// TODO Auto-generated method stub

	}

	@Override
	public Enchere selectById(Integer id)
			throws EnchereDALException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException,
			DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		Enchere enchere = new Enchere();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECTBYID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				enchere.setNoEnchere(rs.getInt("noEnchere"));
				enchere.setDateEnchere(rs.getDate("dateEnchere"));
				enchere.setMontantEnchere(rs.getInt("montantEnchere"));
				enchere.setArticle(am.selectionnerArticleParId(rs.getInt("noArticle")));
				enchere.setUtilisateur(um.getUtilisateurParId(rs.getInt("noUtilisateur")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EnchereDALException("problème avec la sélection d'une enchère par id");
		}
		return enchere;
	}

	@Override
	public Enchere selectMeilleurOffre(Integer noArticle) throws EnchereDALException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		Enchere enchere = new Enchere();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_MEILLEURE_OFFRE);
			stmt.setInt(1, noArticle);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
//				enchere.setNoEnchere(rs.getInt("noEnchere"));
//				enchere.setDateEnchere(rs.getDate("dateEnchere"));
				enchere.setMontantEnchere(rs.getInt("montantEnchere"));
//				enchere.setArticle(am.selectionnerArticleParId(rs.getInt("noArticle")));
//				enchere.setUtilisateur(um.getUtilisateurParId(rs.getInt("noUtilisateur")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EnchereDALException("problème avec la sélection d'une enchère par id");
		}
		return enchere;
	}

}
