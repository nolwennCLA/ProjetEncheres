package fr.eni.ProjetEncheres.dal.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.dal.ConnectionProvider;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;


public class DAOArticleJdbcImpl implements DAOArticle {
	
	private final String INSERT = "INSERT INTO Article "
			+ "(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, etatVente, noCategorie, noUtilisateur, noRetrait)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private final String SELECT_ALL = "SELECT * FROM Article";
	
	private final String SELECT = "SELECT * FROM Article WHERE noArticle = ?";
	
	private final String UPDATE = "UPDATE Article SET "
			+ "nomArticle = ?,"
			+ "description = ?,"
			+ "dateDebutEncheres = ?,"
			+ "dateFinEncheres = ?,"
			+ "miseAPrix = ?,"
			+ "prixVente = ?,"
			+ "etatVente = ?,"
			+ "noCategorie = ?,"
			+ "noUtilisateur = ?,"
			+ "noRetrait = ? "
			+ "WHERE noArticle = ?";
	
	private final String DELETE = "DELETE FROM Article WHERE noArticle = ?";
			
	private List<Article> lstArt;
	private Article art;
	
	private UtilisateurManager um = UtilisateurManagerSingl.getInstance();
	private RetraitManager rm = RetraitManagerSing.getInstance();
	private CategorieManager cm = CategorieManagerSing.getInstance();

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
			pstmt.setNull(10, Types.INTEGER);
//			if(article.getRetrait() == null || article.getRetrait().getNoRetrait() == null) {
//				pstmt.setNull(10, Types.INTEGER);
//			} else {
//				pstmt.setInt(10, article.getRetrait().getNoRetrait());
//			}
			
			//exécution de a requête
			pstmt.executeUpdate();
			
			//récupération de la clé générée
			ResultSet rs = pstmt.getGeneratedKeys();
			
			//si une clé a bien été générée, alors on l'intègre à l'Article
			if(rs.next()) {
				article.setNoArticle(rs.getInt(1));
			
			//si aucune clé n'a été générée, alors on lance une exception
			} else {
				throw new DAL_ArticleException("DAL_problème méthode insert() dans DAOArticleJdbcImpl");
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_ArticleException("DAL_problème dans DAOArticleJdbcImpl");
		}
		
		//on retourne l'article
		return article;
	}

	@Override
	public List<Article> selectAll() throws DAL_ArticleException, UtilisateurExceptionBLL, BLL_RetraitException, DAL_RetraitException, BLL_CategorieException, DAL_CategorieException {
		
		lstArt = new ArrayList<>();
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);) {
			
			//exécution de la requête
			ResultSet rs = pstmt.executeQuery();
			
			//si la requête ne renvoie pas de résultat, on lance une exception
			if(rs == null) {
				throw new DAL_ArticleException("DAL_aucun résultat pour selectAll() dans DAOArticleJdbcImpl");
			
			//si la requête renvoie un résultat, pour chaque ligne on crée un Article et on l'insère à une liste
			} else {
				while(rs.next()) {
					art = new Article();
					
					art.setNoArticle(rs.getInt("noArticle"));
					art.setNomArticle(rs.getString("nomArticle"));
					art.setDescription(rs.getString("description"));
					art.setDateDebutEncheres(rs.getDate("dateDebutEncheres"));
					art.setDateDebutEncheres(rs.getDate("dateFinEncheres"));
					art.setMiseAPrix(rs.getInt("miseAPrix"));
					art.setPrixVente(rs.getInt("prixVente"));
					art.setEtatVente(rs.getString("etatVente"));
					art.setUtilisateur(um.getUtilisateurParId(rs.getInt("noUtilisateur")));
					art.setCategorie(cm.selectionnerCategorie(rs.getInt("noCategorie")));
					if(rs.getInt("noRetrait") != 0) {
						art.setRetrait(rm.selectionnerRetrait(rs.getInt("noRetrait")));
					}
					
					
					
					lstArt.add(art);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_ArticleException("DAL_problème dans DAOArticleJdbcImpl");
		}
		
		//on retourne la liste
		return lstArt;
	}

	@Override
	public Article select(Integer noArticle) throws DAL_ArticleException, UtilisateurExceptionBLL, BLL_RetraitException, DAL_RetraitException, BLL_CategorieException, DAL_CategorieException {
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SELECT);) {
			
			//alimentation de la requête
			pstmt.setInt(1, noArticle);
			
			//exécution de la requête
			ResultSet rs = pstmt.executeQuery();
			
			//si la requête retourne un résultat, on crée un nouvel Article
			if(rs.next()) {
				art = new Article();
				
				art.setNoArticle(rs.getInt("noArticle"));
				art.setNomArticle(rs.getString("nomArticle"));
				art.setDescription(rs.getString("description"));
				art.setDateDebutEncheres(rs.getDate("dateDebutEncheres"));
				art.setDateDebutEncheres(rs.getDate("dateFinEncheres"));
				art.setMiseAPrix(rs.getInt("miseAPrix"));
				art.setPrixVente(rs.getInt("prixVente"));
				art.setEtatVente(rs.getString("etatVente"));
				art.setUtilisateur(um.getUtilisateurParId(rs.getInt("noUtilisateur")));
				art.setCategorie(cm.selectionnerCategorie(rs.getInt("noCategorie")));
				if(rs.getInt("noRetrait") != 0) {
					art.setRetrait(rm.selectionnerRetrait(rs.getInt("noRetrait")));
				}

			//si la requête ne retourne pas de résultat, on lance une exception	
			} else  {
				throw new DAL_ArticleException("DAL_aucun résultat pour select() dans DAOArticleJdbcImpl");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_ArticleException("DAL_problème dans DAOArticleJdbcImpl");
		}
		
		//on retourne l'article
		return art;
	}

	@Override
	public Article update(Article article) throws DAL_ArticleException, UtilisateurExceptionBLL, BLL_RetraitException {

		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(UPDATE);) {
				
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
//				pstmt.setInt(10, article.getRetrait().getNoRetrait());
				if(article.getRetrait() != null && article.getRetrait().getNoRetrait() != null) {
					pstmt.setInt(10, article.getRetrait().getNoRetrait());
				} else {
					pstmt.setNull(10, Types.INTEGER);
				}
				pstmt.setInt(11, article.getNoArticle());
				
				//exécution de a requête
				if(pstmt.executeUpdate() < 1) {
					throw new DAL_ArticleException("DAL_problème méthode update() dans DAOArticleJdbcImpl");
				}
		
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAL_ArticleException("DAL_problème méthode update() dans DAOArticleJdbcImpl");
			}
			
			//on retourne l'article
			return article;
	}

	@Override
	public void delete(Integer noArticle) throws DAL_ArticleException {
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE);) {
			
			//alimentation de la requête
			pstmt.setInt(1, noArticle);
			
			//exécution de la requête
			if(pstmt.executeUpdate() < 1) {
				throw new DAL_ArticleException("DAL_problème méthode delete() dans DAOArticleJdbcImpl");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_ArticleException("DAL_problème connexion dans DAOArticleJdbcImpl");
		}

	}

}
