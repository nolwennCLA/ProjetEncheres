package fr.eni.ProjetEncheres.dal.utilisateur;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.dal.ConnectionProvider;



public class UtilisateurDAOImpl implements UtilisateurDAO {
	
	private String INSERT = "INSERT into UTILISATEUR (pseudo,nom,prenom,email,telephone,rue,"
			+ "codePostal,ville,motDePasse,credit,administrateur ) values(?,?,?,?,?,?,?,?,?,?,?)";
	private String DELETE = "DELETE from UTILISATEUR where noUtilisateur=?";
	private String UPDATE = "UPDATE UTILISATEUR set pseudo=?,nom=?,prenom=?,email=?,telephone=?,"
			+ "rue=?,codePostal=?,ville=?,motDePasse=?,credit=?, administrateur=? where noUtilisateur= ? ";
	private String SELECTALL = "SELECT * FROM UTILISATEUR";	
	private String SELECTBYPSEUDO = "select * from utilisateur where pseudo = ?";
	private String SELECTBYID = "select * from utilisateur where noUtilisateur = ?";
	
	

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
			stmt.setInt(12, utilisateur.getNoUtilisateur());
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
		List<Utilisateur>list = new ArrayList<Utilisateur>();
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(SELECTALL);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt(1));
				utilisateur.setPseudo(rs.getString(2));
				utilisateur.setNom(rs.getString(3));
				utilisateur.setPrenom(rs.getString(4));
				utilisateur.setEmail(rs.getString(5));
				utilisateur.setTelephone(rs.getString(6));
				utilisateur.setRue(rs.getString(7));
				utilisateur.setCodePostal(rs.getString(8));
				utilisateur.setVille(rs.getString(9));
				utilisateur.setMotDePasse(rs.getString(10));
				utilisateur.setCredit(rs.getInt(11));
				utilisateur.setAdministrateur(rs.getBoolean(12));
				
				list.add(utilisateur);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("problème avec la liste d'utilisateur");
		}
		
		
		return list;
	}



	@Override
	public Utilisateur selectByPseudo(String pseudo) throws UtilisateurDALException {
		Utilisateur utilisateur = new Utilisateur();

		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(SELECTBYPSEUDO);
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
				utilisateur.setPseudo(rs.getString(2));
				utilisateur.setNom(rs.getString(3));
				utilisateur.setPrenom(rs.getString(4));
				utilisateur.setEmail(rs.getString(5));
				utilisateur.setTelephone(rs.getString(6));
				utilisateur.setRue(rs.getString(7));
				utilisateur.setCodePostal(rs.getString(8));
				utilisateur.setVille(rs.getString(9));
				utilisateur.setMotDePasse(rs.getString(10));
				utilisateur.setCredit(rs.getInt(11));
				utilisateur.setAdministrateur(rs.getBoolean(12));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("problème avec la selection d'un utilisateur par pseudo");
		}
		
		return utilisateur;
	}



	@Override
	public Utilisateur selectById(Integer id) throws UtilisateurDALException {
		Utilisateur utilisateur = new Utilisateur();

		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(SELECTBYID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
				utilisateur.setPseudo(rs.getString(2));
				utilisateur.setNom(rs.getString(3));
				utilisateur.setPrenom(rs.getString(4));
				utilisateur.setEmail(rs.getString(5));
				utilisateur.setTelephone(rs.getString(6));
				utilisateur.setRue(rs.getString(7));
				utilisateur.setCodePostal(rs.getString(8));
				utilisateur.setVille(rs.getString(9));
				utilisateur.setMotDePasse(rs.getString(10));
				utilisateur.setCredit(rs.getInt(11));
				utilisateur.setAdministrateur(rs.getBoolean(12));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UtilisateurDALException("problème avec la selection d'un utilisateur par id");
		}
		
		return utilisateur;
	}

}
