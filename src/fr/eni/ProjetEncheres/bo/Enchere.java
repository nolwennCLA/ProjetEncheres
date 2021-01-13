package fr.eni.ProjetEncheres.bo;

import java.util.Date;

public class Enchere {

	private Integer noEnchere;
	private Date dateEnchere;
	private Integer montantEnchere;
	private Utilisateur utilisateur;
	private Article article;

	public Enchere() {

	}

	public Enchere(Date dateEnchere, Integer montantEnchere, Utilisateur utilisateur, Article article) {

		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateur = utilisateur;
		this.article = article;
	}
	

	public Integer getNoEnchere() {
		return noEnchere;
	}

	

	public void setNoEnchere(Integer noEnchere) {
		this.noEnchere = noEnchere;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Integer getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
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
		builder.append("Enchere [dateEnchere=");
		builder.append(dateEnchere);
		builder.append(", montantEnchere=");
		builder.append(montantEnchere);
		builder.append(", utilisateur=");
		builder.append(utilisateur);
		builder.append(", article=");
		builder.append(article);
		builder.append("]");
		return builder.toString();
	}

}
