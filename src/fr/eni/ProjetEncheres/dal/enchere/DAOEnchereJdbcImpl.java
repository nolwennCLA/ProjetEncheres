package fr.eni.ProjetEncheres.dal.enchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class DAOEnchereJdbcImpl implements DAOEnchere {
	
	private final String INSERT = "INSERT INTO Enchere (dateEnchere, montantEnchere, noUtilisateur, noArticle) VALUES (?, ?, ?, ?)";
	
	private final String SELECT_ALL = "SELECT * FROM Enchere";
	
	private final String SELECT_BY_ID = "SELECT * FROM Enchere WHERE noEnchere = ?";
	
	private final String SELECT_BY_NOARTICLE = "SELECT * FROM Enchere WHERE noArticle = ? ORDER BY montantEnchere DESC";
	
//	private final String SELECT_BY_ARTICLE_NAME = "SELECT"
	
	//a priori inutile
	private final String UPDATE = "UPDATE Enchere SET "
			+ "dateEnchere = ?, "
			+ "montantEnchere = ?, "
			+ "noArticle = ?, "
			+ "noUtilisateur = ?";
	
	private final String DELETE = "DELETE FROM Enchere WHERE noEnchere = ?";
	
	
	
	
	private List<Enchere> lstEnch;
	private Enchere ench;
	private ArticleManager am = ArticleManagerSing.getInstance();
	private UtilisateurManager um = UtilisateurManagerSingl.getInstance();
	
	
	
	
	
	@Override
	public Enchere insert(Enchere enchere) throws DAL_EnchereException {
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {
			
			//conversion des dates, du format java.util.date vers le format java.sql.date
			java.sql.Date dateEnchere;
			dateEnchere = new java.sql.Date(enchere.getDateEnchere().getTime());
			
			
			//alimentation de la requête
			pstmt.setDate(1, dateEnchere);
			pstmt.setInt(2, enchere.getMontantEnchere());
			pstmt.setInt(3, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(4, enchere.getArticle().getNoArticle());
		
			//exécution de a requête
			pstmt.executeUpdate();
			
			//récupération de la clé générée
			ResultSet rs = pstmt.getGeneratedKeys();
			
			//si une clé a bien été générée, alors on l'intègre à l'Article
			if(rs.next()) {
				enchere.setNoEnchere(rs.getInt(1));
			
			//si aucune clé n'a été générée, alors on lance une exception
			} else {
				throw new DAL_EnchereException("DAL_DAOEnchereJdbcImp_insert() : Enchère non créée");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_EnchereException("DAL_DAOEnchereJdbcImp_insert() : Problème dans la méthode");
		}
		
		//on retourne l'enchère
		return enchere;
	}

	@Override
	public List<Enchere> selectAll() throws DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		
		lstEnch = new ArrayList<>();
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);) {
			
			//exécution de la requête
			ResultSet rs = pstmt.executeQuery();
			
			//si la requête ne renvoie pas de résultat, on lance une exception
			if(rs == null) {
//				throw new DAL_EnchereException("DAL_DAOEnchereJdbcImp_selectAll() : Aucun résultat en base");
				System.out.println("DAL_DAOEnchereJdbcImp_selectAll() : Aucun résultat en base");
			
			//si la requête renvoie un résultat, pour chaque ligne on crée un Article et on l'insère à une liste
			} else {
				while(rs.next()) {
					ench = new Enchere();
					
					ench.setNoEnchere(rs.getInt("noEnchere"));
					ench.setDateEnchere(rs.getDate("dateEnchere"));
					ench.setMontantEnchere(rs.getInt("montantEnchere"));
					ench.setArticle(am.selectionnerArticleParId(rs.getInt("noArticle")));
					ench.setUtilisateur(um.getUtilisateurParId(rs.getInt("noUtilisateur")));
					
					lstEnch.add(ench);
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_EnchereException("DAL_DAOEnchereJdbcImp_selectAll() : Problème dans la méthode");
		}
		
		return lstEnch;
	}

	@Override
	public Enchere selectById(Integer noEnchere) throws DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_ID);) {
			
			//alimentation de la requête
			pstmt.setInt(1, noEnchere);
			
			//exécution de la requête
			ResultSet rs = pstmt.executeQuery();
			
			//si la requête retourne un résultat, on crée un nouvel Article
			if(rs.next()) {
				ench = new Enchere();
				
				ench.setNoEnchere(rs.getInt("noEnchere"));
				ench.setDateEnchere(rs.getDate("dateEnchere"));
				ench.setMontantEnchere(rs.getInt("montantEnchere"));
				ench.setArticle(am.selectionnerArticleParId(rs.getInt("noArticle")));
				ench.setUtilisateur(um.getUtilisateurParId(rs.getInt("noUtilisateur")));


			//si la requête ne retourne pas de résultat, on lance une exception	
			} else  {
//				throw new DAL_EnchereException("DAL_DAOArticleJdbcImp_select() : Aucun résultat en base");
				System.out.println("DAL_DAOEnchereJdbcImp_selectById() : Aucun résultat en base");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_EnchereException("DAL_DAOEnchereJdbcImp_selectById() : Problème dans la méthode");
		}
		
		//on retourne l'enchère
		return ench;
	}
	
	
	//a priori inutile
	@Override
	public Enchere update(Enchere enchere) throws DAL_EnchereException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer noEnchere) throws DAL_EnchereException {
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE);) {
			
			//alimentation de la requête
			pstmt.setInt(1, noEnchere);
			
			//exécution de la requête
			if(pstmt.executeUpdate() < 1) {
				throw new DAL_EnchereException("DAL_DAOEnchereJdbcImp_delete() : Enchère non supprimée");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_EnchereException("DAL_DAOEnchereJdbcImp_delete() : Problème dans la méthode");
		}

	}
	
	
	
	@Override
	public List<Enchere> selectByNoArticle(Integer noArticle) throws DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL {
		
		lstEnch = new ArrayList<>();
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_NOARTICLE);) {
			
			//alimentation de la requête
			pstmt.setInt(1, noArticle);

			//exécution de la requête
			ResultSet rs = pstmt.executeQuery();

			//si la requête ne renvoie pas de résultat, on lance une exception
			if(rs == null) {
//				throw new DAL_EnchereException("DAL_DAOEnchereJdbcImp_selectAll() : Aucun résultat en base");
				System.out.println("DAL_DAOEnchereJdbcImp_selectAll() : Aucun résultat en base");

			//si la requête renvoie un résultat, pour chaque ligne on crée un Article et on l'insère à une liste
			} else {
				while(rs.next()) {
					ench = new Enchere();

					ench.setNoEnchere(rs.getInt("noEnchere"));
					ench.setDateEnchere(rs.getDate("dateEnchere"));
					ench.setMontantEnchere(rs.getInt("montantEnchere"));
					ench.setArticle(am.selectionnerArticleParId(rs.getInt("noArticle")));
					ench.setUtilisateur(um.getUtilisateurParId(rs.getInt("noUtilisateur")));
					
					lstEnch.add(ench);

				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_EnchereException("DAL_DAOEnchereJdbcImp_selectAll() : Problème dans la méthode");
		}

		return lstEnch;
		
	}

	
	
	
	


}
