package model;

import java.util.Date;

/**
 * Classe Campagne
 * @author ESIGELEC - TIC Department
 * @version 1.2
 * */
public class Campagne {
	
	/** 
	 * identifiant de la campagne
	 */
	private int id;
	
	/**
	 * date de debut
	 */
	private Date dateDebut;
	
	/**
	 * date de fin
	 */
	private Date dateFin;
	
	/**
	 * nombre maximum de choix
	 */
	private int nbChoixMax;
	
	/**
	 * promotion
	 */
	private int promotion;
	
	/**
	 * indique si la campagne est archivee
	 */
	private boolean estArchive;

	/**
	 * Constructor
	 * @param id identifiant de la campagne
	 * @param dateDebut date de debut de la campagne
	 * @param dateFin date de fin de la campagne
	 * @param nbChoixMax nombre maximum de choix
	 * @param promotion promotion concernee
	 * @param estArchive indique si la campagne est archivee
	 */
	public Campagne(int id, Date dateDebut, Date dateFin, int nbChoixMax, int promotion, boolean estArchive) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbChoixMax = nbChoixMax;
		this.promotion = promotion;
		this.estArchive = estArchive;
	}

	/**
	 * getter pour l'attribut id
	 * @return valeur de l'identifiant de la campagne
	 */
	public int getId() {
		return id;
	}

	/**
	 * getter pour l'attribut dateDebut
	 * @return valeur de la date de debut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * getter pour l'attribut dateFin
	 * @return valeur de la date de fin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * getter pour l'attribut nbChoixMax
	 * @return valeur du nombre maximum de choix
	 */
	public int getNbChoixMax() {
		return nbChoixMax;
	}

	/**
	 * getter pour l'attribut promotion
	 * @return valeur de la promotion
	 */
	public int getPromotion() {
		return promotion;
	}

	/**
	 * getter pour l'attribut estArchive
	 * @return valeur indiquant si la campagne est archivee
	 */
	public boolean getEstArchive() {
		return estArchive;
	}

	/**
	 * setter pour l'attribut estArchive
	 * @param estArchive : nouvelle valeur indiquant si la campagne est archivee
	 */
	public void setEstArchive(boolean estArchive) {
		this.estArchive = estArchive;
	}

	/**
	 * Redefinition de la methode toString permettant de definir la traduction de l'objet en String
	 * pour l'affichage dans la console par exemple
	 */
	@Override
	public String toString() {
		return "Campagne [id : " + id + ", " + dateDebut
				+ ", " + dateFin + ", " + nbChoixMax
				+ ", " + promotion + ", " + estArchive + "]";
	}
}