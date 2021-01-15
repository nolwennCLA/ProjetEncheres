package fr.eni.ProjetEncheres.dal.enchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.dal.dal.ConnectionProvider;

public class EnchereDAOImpl implements EnchereDAO {

	private final String INSERT = "INSERT into ENCHERE (dateEnchere, montantEnchere, noUtilisateur, noArticle) values (?,?,?,?)";
	private final String DELETE = "DELETE from ENCHERE where noEnchere=?";
	private final String UPDATE_ARTICLE = "UPDATE ARTICLE set miseAPrix=? where noArticle=?";
	private final String UPDATE_UTILISATEUR = "UPDATE UTILISATEUR set credit=? where noUtilisateur=?";
	private final String UPDATE_RECREDITATION = "";
	private final String SELECT_ALL = "SELECT * from ENCHERE";

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

			// si une clé a bien été générée, alors on l'intégre à l'Article
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
		// TODO Auto-generated method stub

	}

	public void updateArticle(Enchere enchere) throws EnchereDALException {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_ARTICLE);
			stmt.setInt(1, enchere.getArticle().getMiseAPrix());
			stmt.setInt(2, enchere.getArticle().getNoArticle());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EnchereDALException("problème pour modifier l'enchère");
		}
	}
	
	public void updateUtilisateur(Enchere enchere) throws EnchereDALException {
		try (Connection cnx = ConnectionProvider.getConnection()){
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
	public List<Enchere> selectAll() throws EnchereDALException {
		List<Enchere> list = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt(1));
				enchere.setDateEnchere(rs.getDate(2));
				enchere.setMontantEnchere(rs.getInt(3));
				enchere.getUtilisateur().setNoUtilisateur(rs.getInt(4));
				enchere.getArticle().setNoArticle(rs.getInt(5));

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

}
