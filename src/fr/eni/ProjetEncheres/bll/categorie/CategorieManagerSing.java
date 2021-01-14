package fr.eni.ProjetEncheres.bll.categorie;

public class CategorieManagerSing {
	
	private static CategorieManager instance;
	
	public static CategorieManager getInstance() {
		if(instance == null) {
			instance = new CategorieManagerImpl();
		}
		return instance;
	}
}
