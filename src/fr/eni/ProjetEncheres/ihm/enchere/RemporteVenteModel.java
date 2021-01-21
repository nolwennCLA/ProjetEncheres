package fr.eni.ProjetEncheres.ihm.enchere;

import fr.eni.ProjetEncheres.bo.Article;

public class RemporteVenteModel {
	
	Article article= new Article();

	public RemporteVenteModel() {
		super();
	}

	public RemporteVenteModel(Article article) {
		super();
		this.article = article;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RemporteVenteModel [article=");
		builder.append(article);
		builder.append("]");
		return builder.toString();
	}
	
	

}
