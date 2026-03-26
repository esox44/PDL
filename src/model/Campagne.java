package model;

import java.time.LocalDateTime;
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
	private LocalDateTime dateDebut;
	
	/**
	 * date de fin
	 */
	private LocalDateTime dateFin;
	
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
	private String statut;
	
	/**
	 * Constructor
	 * @param dateDebut date de debut de la campagne
	 * @param dateFin date de fin de la campagne
	 * @param nbChoixMax nombre maximum de choix
	 * @param promotion promotion concernee
	 * @param estArchive indique si la campagne est archivee
	 */
	public Campagne(LocalDateTime dateDebut, LocalDateTime dateFin, int nbChoixMax, int promotion, String statut) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbChoixMax = nbChoixMax;
		this.promotion = promotion;
		this.statut = statut;
	}
	
	
	/**
	 * Constructor
	 * @param id identifiant de la campagne
	 * @param dateDebut date de debut de la campagne
	 * @param dateFin date de fin de la campagne
	 * @param nbChoixMax nombre maximum de choix
	 * @param promotion promotion concernee
	 * @param estArchive indique si la campagne est archivee
	 */
	public Campagne(int id, LocalDateTime dateDebut, LocalDateTime dateFin, int nbChoixMax, int promotion, String statut) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbChoixMax = nbChoixMax;
		this.promotion = promotion;
		this.statut = statut;
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
	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	/**
	 * getter pour l'attribut dateFin
	 * @return valeur de la date de fin
	 */
	public LocalDateTime getDateFin() {
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
	 * getter pour l'attribut statut
	 * @return valeur indiquant le statut de la campagne
	 */
	public String getStatut() {
		return statut;
	}

	/**
	 * setter pour l'attribut id
	 * @param id : nouvelle valeur indiquant l'id de la campagne
	 */
	public void setId(int id) {
		this.id = id;
	}	
	
	/**
	 * setter pour l'attribut statut
	 * @param statut : nouvelle valeur indiquant le statut de la campagne
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}

	/**
	 * Redefinition de la methode toString permettant de definir la traduction de l'objet en String
	 * pour l'affichage dans la console par exemple
	 */
	@Override
	public String toString() {
		return "Campagne [id : " + id + ", " + dateDebut
				+ ", " + dateFin + ", " + nbChoixMax
				+ ", " + promotion + ", " + statut + "]";
	}
}