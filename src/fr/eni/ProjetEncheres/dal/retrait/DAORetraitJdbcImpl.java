package fr.eni.ProjetEncheres.dal.retrait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.dal.ConnectionProvider;

public class DAORetraitJdbcImpl implements DAORetrait {
	
	private final String INSERT = "INSERT INTO Retrait (rue, codePostal, ville) VALUES (?, ?, ?)";
	
	private final String SELECT_ALL = "SELECT * FROM Retrait";
	
	private final String SELECT = "SELECT * FROM Retrait WHERE noRetrait = ?";
	
	private final String UPDATE = "UPDATE Retrait SET "
			+ "rue = ?,"
			+ "codePostal = ?,"
			+ "ville = ?,"
			+ "WHERE noRetrait = ?";
	
	private final String DELETE = "DELETE FROM Retrait WHERE noRetrait = ?";
	
	private List<Retrait> lstRet;
	private Retrait ret;
	
	
	
	@Override
	public Retrait insert(Retrait retrait) throws DAL_RetraitException {
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {

			//alimentation de la requête
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCodePostal());
			pstmt.setString(3, retrait.getVille());
			
			//exécution de la requête
			pstmt.executeUpdate();
			
			//récupération de la clé générée
			ResultSet rs = pstmt.getGeneratedKeys();
			
			//si une clé a bien été générée, alors on l'intègre au Retrait
			if(rs.next()) {
				retrait.setNoRetrait(rs.getInt(1));
			
			//si aucune clé n'a été générée, alors on lance une exception
			} else {
				throw new DAL_RetraitException("DAL_DAORetraitJdbcImpl_insert() : Clé non générée");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_RetraitException("DAL_DAORetraitJdbcImpl_insert() : Problème dans la méthode");
		}
		
		//on retourne retrait
		return retrait;
	}

	@Override
	public List<Retrait> selectAll() throws DAL_RetraitException {
		
		lstRet = new ArrayList<>();
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);) {
			
			//exécution de la requête
			ResultSet rs = pstmt.executeQuery();
			
			//si la requête ne envoie aucun résultat, alors on lacne une exception
			if(rs == null) {
//				throw new DAL_RetraitException("DAL_DAORetraitJdbcImpl_selectAll() : Aucun résultat dans la base");
				System.out.println("DAL_DAORetraitJdbcImpl_selectAll() : Aucun résultat dans la base");
			
			//si la requête renvoie un résultat, alors pour chaque ligne on crée un Retrait et on l'insère dans une liste
			} else {
				while(rs.next()) {
					ret = new Retrait();
					
					ret.setNoRetrait(rs.getInt("noRetrait"));
					ret.setRue(rs.getString("rue"));
					ret.setCodePostal(rs.getString("codePostal"));
					ret.setVille(rs.getString("ville"));
					
					lstRet.add(ret);
				}
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_RetraitException("DAL_DAORetraitJdbcImpl_selectAll() : Problème dans la méthode");
		}
		
		//on retourne la liste
		return lstRet;
	}

	@Override
	public Retrait select(Integer noRetrait) throws DAL_RetraitException {
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SELECT);) {
			
			//alimentation de la requête
			pstmt.setInt(1, noRetrait);
			
			//exécution de la requête
			ResultSet rs = pstmt.executeQuery();
			
			//si la reque^te retourne une résultat, on crée un nouveau Retrait
			if(rs.next()) {
				ret = new Retrait();
				
				ret.setNoRetrait(rs.getInt("noRetrait"));
				ret.setRue(rs.getString("rue"));
				ret.setCodePostal(rs.getString("codePostal"));
				ret.setVille(rs.getString("ville"));
			
			//si la requête ne retourne rien, on lance un exception
			} else {
//				throw new DAL_RetraitException("DAL_DAORetraitJdbcImpl_select() : Aucun résultat dans la base");
				System.out.println("DAL_DAORetraitJdbcImpl_select() : Aucun résultat dans la base");
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_RetraitException("DAL_DAORetraitJdbcImpl_selectAll() : Problème dans la méthode");
		}
		
		//on retourne le Retrait
		return ret;
	}

	@Override
	public Retrait update(Retrait retrait) throws DAL_RetraitException {
		
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE);) {

				//alimentation de la requête
				pstmt.setString(1, retrait.getRue());
				pstmt.setString(2, retrait.getCodePostal());
				pstmt.setString(3, retrait.getVille());
				pstmt.setInt(4, retrait.getNoRetrait());
				
				//exécution de la requête
				if(pstmt.executeUpdate() < 1) {
					throw new DAL_RetraitException("DAL_DAORetraitJdbcImpl_update() : Retrait non modifié");
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_RetraitException("DAL_DAORetraitJdbcImpl_update() : Problème dans la méthode");
		}
		
		//on retourne le Retrait
		return ret;
	}

	@Override
	public void delete(Integer noRetrait) throws DAL_RetraitException {
		try(Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE);) {
				
			//alimentation de la requête
			pstmt.setInt(1, noRetrait);
			
			//exécution de la requête
			if(pstmt.executeUpdate() < 1) {
				throw new DAL_RetraitException("DAL_DAORetraitJdbcImpl_delete() : Retrait non supprimé");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAL_RetraitException("DAL_DAORetraitJdbcImpl_delete() : Problème dans la méthode");
		}

	}

}
