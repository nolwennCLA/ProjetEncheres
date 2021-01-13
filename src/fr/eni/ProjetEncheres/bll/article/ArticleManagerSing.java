package fr.eni.ProjetEncheres.bll.article;

public class ArticleManagerSing {

		private static ArticleManager instance;
		
		public static ArticleManager getInstance() {
			if(instance == null) {
				instance = new ArticleManagerImpl();
			}
			return instance;
		}
		
}
