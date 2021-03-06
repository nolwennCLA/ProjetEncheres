package fr.eni.ProjetEncheres.dal.utilisateur;

import java.util.List;

import fr.eni.ProjetEncheres.bo.Utilisateur;

public interface UtilisateurDAO {

	public Utilisateur insert(Utilisateur utilisateur) throws UtilisateurDALException;
	public void delete(Integer id) throws UtilisateurDALException;
	public void update (Utilisateur utilisateur) throws UtilisateurDALException;
	public List<Utilisateur> selectAll() throws UtilisateurDALException;
	
}
