package fr.eni.ProjetEncheres.bll.utilisateur;

import java.util.List;

import fr.eni.ProjetEncheres.bo.Utilisateur;

public interface UtilisateurManager {
	
	public Utilisateur addUtilisateur (Utilisateur utilisateur )throws UtilisateurExceptionBLL;
	public void updateUtilisateur (Utilisateur utilisateur)throws UtilisateurExceptionBLL;
	public void deleteUtilisateur(String pseudo) throws UtilisateurExceptionBLL;
	public List<Utilisateur> getListUtilisateur() throws UtilisateurExceptionBLL;
	public Utilisateur getUtilisateurParPseudo(String pseudo) throws UtilisateurExceptionBLL;
	public Utilisateur getUtilisateurParId(Integer id) throws UtilisateurExceptionBLL;
	
}
