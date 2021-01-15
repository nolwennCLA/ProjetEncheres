package fr.eni.ProjetEncheres.dal.retrait;

import java.util.List;

import fr.eni.ProjetEncheres.bo.Retrait;

public interface DAORetrait {
	
	public Retrait insert(Retrait retrait) throws DAL_RetraitException;
	
	public List<Retrait> selectAll() throws DAL_RetraitException;
	
	public Retrait select(Integer noRetrait) throws DAL_RetraitException;
	
	public Retrait update(Retrait retrait) throws DAL_RetraitException;
	
	public void delete(Integer noRetrait) throws DAL_RetraitException;
	
}
