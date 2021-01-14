package fr.eni.ProjetEncheres.bll.utilisateur;

import java.util.List;

import fr.eni.ProjetEncheres.bo.Utilisateur;

public interface UtilisateurManager {
	
	public void addUtilisateur (Utilisateur utilisateur )throws UtilisateurExceptionBLL;
	public void updateUtilisateur (Utilisateur utilisateur)throws UtilisateurExceptionBLL;
	public void deleteUtilisateur(Integer id) throws UtilisateurExceptionBLL;
	public List<Utilisateur> getListUtilisateur() throws UtilisateurExceptionBLL;
	public Utilisateur getUtilisateur(String pseudo) throws UtilisateurExceptionBLL;
	
}
