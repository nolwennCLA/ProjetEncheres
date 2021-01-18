package fr.eni.ProjetEncheres.dal.categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.dal.dal.ConnectionProvider;

public class DAOCategorieJdbcImpl implements DAOCategorie {
	
	private final String INSERT = "INSERT INTO Categorie (libelle) VALUES (?)";
	
	private final String SELECT_ALL = "SELECT * FROM Categorie";
	
	private final String SELECT = "SELECT * FROM Categorie WHERE noCategorie = ?";
	
	private final String UPDATE = "UPDATE Categorie SET libelle = ? WHERE noCategorie = ?";
	
	private final String DELETE = "DELETE FROM Categorie WHERE noCategorie = ?";
	
	private List<Categorie> lstCat;
	private Categorie cat;
	
	
	
	
	@Override
	public Categorie insert(Categorie categorie) throws DAL_CategorieException {
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {
			
			//alimentation de la requête
			pstmt.setString(1, categorie.getLibelle());
			
			//exécution de la requête
			pstmt.executeUpdate();
			
			//récupération de la clé générée
			ResultSet rs = pstmt.getGeneratedKeys();
			
			//si une clé a bien été générée, on intègre la clé à la Categorie, sinon on lance une exception
			if(rs.next()) {
				categorie.setNoCategorie(rs.getInt(1));
			} else {
				throw new DAL_CategorieException("DAL_DAOCategorieJdbcImpl_insert() : Clé non générée");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_CategorieException("DAL_DAOCategorieJdbcImpl_insert() : Problème dans la méthode");
		}
		
		//on retourne categorie
		return categorie;
	}

	@Override
	public List<Categorie> selectAll() throws DAL_CategorieException {
		
		lstCat = new ArrayList<>();
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);) {
			
			//exécution de la requête
			ResultSet rs = pstmt.executeQuery();
			
			//si la requête ne revoie aucun résultat, on lance une exception
			if(rs == null) {
//				throw new DAL_CategorieException("DAL_aucun résultat pour selectAll() dans DAOCategorieJdbcImpl");
				System.out.println("DAL_DAOCategorieJdbcImpl_selectAll() : Aucun résultat en base");
				
			//sinon pour chaque ligne de résultat on crée une Categorier et on l'ajoute à une liste	
			} else {
				while(rs.next()) {
					cat = new Categorie();
					
					cat.setNoCategorie(rs.getInt("noCategorie"));
					cat.setLibelle(rs.getString("libelle"));
					
					lstCat.add(cat);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_CategorieException("DAL_DAOCategorieJdbcImpl_selectAll() : Problème dans la méthode");
		}
		
		//on retourne la liste
		return lstCat;
	}

	@Override
	public Categorie select(Integer noCategorie) throws DAL_CategorieException {
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SELECT);) {
			
			//alimentation de la requête
			pstmt.setInt(1,noCategorie);
			
			//exécution de la requête
			ResultSet rs = pstmt.executeQuery();
			
			//si la requête renvoie une résultat, on crée une nouvelle Categorie
			if(rs.next()) {
				cat = new Categorie();
				
				cat.setNoCategorie(rs.getInt("noCategorie"));
				cat.setLibelle(rs.getString("libelle"));
			
			//sinon on lance une exception
			} else {
//				throw new DAL_CategorieException("DAL_DAOCategorieJdbcImpl_select() : Aucun résultat en base");
				System.out.println("DAL_DAOCategorieJdbcImpl_select() : Aucun résultat en base");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_CategorieException("DAL_DAOCategorieJdbcImpl_select() : Problème dans la méthode");
		}
		
		//on retourne la Categorie
		return cat;
	}

	@Override
	public Categorie update(Categorie categorie) throws DAL_CategorieException {
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE);) {
			
			//alimentation de la requête
			pstmt.setString(1, categorie.getLibelle());
			pstmt.setInt(2, categorie.getNoCategorie());
			
			//exécution de la requête
			if(pstmt.executeUpdate() < 1) {
				throw new DAL_CategorieException("DAL_DAOCategorieJdbcImpl_update() : Categorie non modifiée");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_CategorieException("DAL_DAOCategorieJdbcImpl_update() : Problème dans la méthode");
		}
		
		//on retourne la Categorie
		return categorie;
	}

	@Override
	public void delete(Integer noCategorie) throws DAL_CategorieException {
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(DELETE);) {
					
				//alimentation de la requête
				pstmt.setInt(1, noCategorie);
				
				//exécution de la requête
				if(pstmt.executeUpdate() < 1) {
					throw new DAL_CategorieException("DAL_DAOCategorieJdbcImpl_delete() : Categorie non supprimée");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAL_CategorieException("DAL_DAOCategorieJdbcImpl_update() : Problème dans la méthode");
			}

	}

}
