package fr.eni.ProjetEncheres.dal.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManager;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManagerSing;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.dal.dal.ConnectionProvider;


public class DAOArticleJdbcImpl implements DAOArticle {
	
	private final String INSERT = "INSERT INTO Articles "
			+ "(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, etatVente, noCategorie, noUtilisateur, noRetrait)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private final String SELECT_ALL = "SELECT * FROM Articles";
	
	private final String SELECT = "SELECT * FROM Articles WHERE noArticle = ?";
	
	private final String UPDATE = "UPDATE Articles SET"
			+ "nomArticle = ?,"
			+ "description = ?,"
			+ "dateDebutEncheres = ?,"
			+ "dateFinEncheres = ?,"
			+ "miseAPrix = ?,"
			+ "prixVente = ?,"
			+ "etatVente = ?,"
			+ "noCategorie = ?"
			+ "noUtilisateur = ?,"
			+ "noRetrait = ?"
			+ "WHERE noArticle = ?";
	
	private final String DELETE = "DELETE FROM Articles WHERE noArticle = ?";
			
	private List<Article> lstArt;
	private UtilisateurManager um = UtilisateurManagerSingl.getInstance();
	private RetraitManager rm = RetraitManagerSing.getInstance();

	@Override
	public Article insert(Article article) throws DAL_ArticleException {
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {
			
			//conversion des dates, du format java.util.date vers le format java.sql.date
			java.sql.Date dateDebutEncheresSQL;
			java.sql.Date dateFinEncheresSQL;
			
			dateDebutEncheresSQL = new java.sql.Date(article.getDateDebutEncheres().getTime());
			dateFinEncheresSQL = new java.sql.Date(article.getDateFinEncheres().getTime());
			
			//alimentation de la requête
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, dateDebutEncheresSQL);
			pstmt.setDate(4, dateFinEncheresSQL);
			pstmt.setInt(5, article.getMiseAPrix());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setString(7, article.getEtatVente());
			pstmt.setInt(8, article.getCategorie().getNoCategorie());
			pstmt.setInt(9, article.getUtilisateur().getNoUtilisateur());
			if(article.getRetrait().getNoRetrait() != null) {
				pstmt.setInt(10, article.getRetrait().getNoRetrait());
			} else {
				pstmt.setNull(10, Types.INTEGER);
			}
			
			//exécution de a requête
			pstmt.executeUpdate();
			
			//récupération de la clé générée
			ResultSet rs = pstmt.getGeneratedKeys();
			
			//si une clé a bien été générée, alors on l'intégre à l'Article
			if(rs.next()) {
				article.setNoArticle(rs.getInt(1));
				System.out.println(article.toString());
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_ArticleException("DAL_problème méthode insert()");
		}
		
		//on retourne l'article
		return article;
	}

	@Override
	public List<Article> selectAll() throws DAL_ArticleException, UtilisateurExceptionBLL, BLL_RetraitException {
		
		lstArt = new ArrayList<>();
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);) {
			
			//exécution de la requête
			ResultSet rs = pstmt.executeQuery();
			
			//si la requête ne renvoie pas de résultat, on lance une exception
			if(rs == null) {
				throw new DAL_ArticleException("DAL_aucun résultat pour selectAll()");
			
			//si la requête renvoie un résultat, pour chaque ligne on crée un Article et on l'insère à une liste
			} else {
				while(rs.next()) {
					Article art = new Article();
					
					art.setNoArticle(rs.getInt("noArticle"));
					art.setNomArticle(rs.getString("nomArticle"));
					art.setDescription(rs.getString("description"));
					art.setDateDebutEncheres(rs.getDate("dateDebutEncheres"));
					art.setDateDebutEncheres(rs.getDate("dateFinEncheres"));
					art.setMiseAPrix(rs.getInt("miseAPrix"));
					art.setPrixVente(rs.getInt("prixVente"));
					art.setUtilisateur(um.getUtilisateurParId(rs.getInt("noUtilisateur")));
					//TODO faire un CategorieLanager pour recuperer une catagorie à partir de son no
//					art.setCategorie(categorie);
					art.setRetrait(rm.selectionnerRetrait(rs.getInt("noRetrait")));
					
					lstArt.add(art);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_ArticleException("DAL_problème méthode selectAll()");
		}
		
		//on retourne la liste
		return lstArt;
	}

	@Override
	public Article select(Integer noArticle) throws DAL_ArticleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article update(Article article) throws DAL_ArticleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer noArticle) throws DAL_ArticleException {
		// TODO Auto-generated method stub

	}

}
