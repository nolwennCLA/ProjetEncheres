package fr.eni.ProjetEncheres.dal.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Utilisateur;


public class UtilisateurDAOImpl implements UtilisateurDAO {
	
	private String INSERT = "INSERT into UTILISATEUR (pseudo,nom,prenom,email,telephone,rue,"
			+ "codePostal,ville,motDePasse,credit,administrateur ) values(?,?,?,?,?,?,?,?,?,?,?)";
	private String DELETE = "DELETE from UTILISATEUR where noUtilisateur=?";
	private String UPDATE = "UPDATE UTILISATEUR set pseudo=?,nom=?,prenom=?,email=?,telephone=?,"
			+ "rue=?,codePostal=?,ville=?,motDePasse=?,credit=?, administrateur=?";
	private String SELECT = "SELECT * FROM UTILISATEUR";	
	

	@Override
	public Utilisateur insert(Utilisateur utilisateur) throws UtilisateurDALException {
				
				try (Connection cnx = ConnectionProvider.getConnection()) {
					PreparedStatement stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
					stmt.setString(1, utilisateur.getPseudo());
					stmt.setString(2, utilisateur.getNom());
					stmt.setString(3, utilisateur.getPrenom());
					stmt.setString(4, utilisateur.getEmail());
					stmt.setString(5, utilisateur.getTelephone());
					stmt.setString(6, utilisateur.getRue());
					stmt.setString(7, utilisateur.getCodePostal());
					stmt.setString(8, utilisateur.getVille());
					stmt.setString(9, utilisateur.getMotDePasse());
					stmt.setInt(10, utilisateur.getCredit());
					stmt.setBoolean(11, utilisateur.isAdministrateur());
			
					int nbRows = stmt.executeUpdate();
					
					if (nbRows == 1) {
						ResultSet rs = stmt.getGeneratedKeys();
						if (rs.next()) {
							utilisateur.setNoUtilisateur(rs.getInt(1));
						}
					}
					

				} catch (SQLException e) {
					e.printStackTrace();
					throw new UtilisateurDALException("problème dans l'insertion d'un utilisateur");
				}
				return utilisateur;
				
			}
					
			

	@Override
	public void delete(Integer id) throws UtilisateurDALException {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(DELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("problème pour supprimer un utilisateur");
		}
		
	}

	@Override
	public void update(Utilisateur utilisateur) throws UtilisateurDALException {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(UPDATE);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, utilisateur.getCredit());
			stmt.setBoolean(11, utilisateur.isAdministrateur());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("problème pour modifier l'utilisateur");
		}
		
	}

	@Override
	public List<Utilisateur> selectAll() throws UtilisateurDALException {
		List<Utilisateur>liste = new ArrayList<Utilisateur>();
		
		
		return null;
	}

}
