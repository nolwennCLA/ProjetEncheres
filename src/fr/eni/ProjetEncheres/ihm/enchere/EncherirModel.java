package fr.eni.ProjetEncheres.ihm.enchere;

import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Enchere;

public class EncherirModel {
	
	private Article article;
	private Enchere enchere;

	
	public EncherirModel() {
		super();
	}

	public EncherirModel(Article article) {
		super();
		this.article = article;
	}

	public EncherirModel(Enchere enchere) {
		super();
		this.enchere = enchere;
	}

	
	public EncherirModel(Article article, Enchere enchere) {
		super();
		this.article = article;
		this.enchere = enchere;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Enchere getEnchere() {
		return enchere;
	}

	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EncherirModel [article=");
		builder.append(article);
		builder.append(", enchere=");
		builder.append(enchere);
		builder.append("]");
		return builder.toString();
	}



	
	
	

}
