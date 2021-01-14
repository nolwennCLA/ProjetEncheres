package fr.eni.ProjetEncheres.dal.enchere;

import java.util.List;

import fr.eni.ProjetEncheres.bo.Enchere;

public interface EnchereDAO {
	
	public Enchere insert (Enchere enchere) throws EnchereDALException;
	public void delete (Integer id) throws EnchereDALException;
	public void update (Enchere enchere) throws EnchereDALException;
	public List<Enchere> selectAll() throws EnchereDALException;
	

}
