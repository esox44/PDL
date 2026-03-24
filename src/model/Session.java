package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Classe Session
 * @author ESIGELEC - TIC Department
 * @version 1.2
 * */
public class Session {

	/** 
	 * identifiant de la session
	 */
	private int id;

	/**
	 * date heure de début
	 */
	private LocalDateTime dateHeureDebut;

	/**
	 * date heure de fin
	 */
	private LocalDateTime dateHeureFin;

	/**
	 * capacite
	 */
	private int capacite;
	
	/**
	 * idCampagne
	 */
	private int idCampagne;
	
	/**
	 * idDominante
	 */
	private int idDominante;
	
	/**
	 * Constructor
	 * @param date date de la session
	 * @param heureDebut heure de debut
	 * @param heureFin heure de fin
	 * @param capacite capacite maximale
	 * @param idCampagne id de la campagne de la session
	 * @param capacite id de la dominante de la session
	 */
	public Session(LocalDateTime dateHeureDebut, LocalDateTime dateHeureFin, int capacite, int idCampagne, int idDominante) {
		this.dateHeureDebut = dateHeureDebut;
		this.dateHeureFin = dateHeureFin;
		this.capacite = capacite;
		this.idCampagne = idCampagne;
		this.idDominante = idDominante;
	}
	
	/**
	 * Constructor
	 * @param id de la session
	 * @param date date de la session
	 * @param heureDebut heure de debut
	 * @param heureFin heure de fin
	 * @param capacite capacite maximale
	 * @param idCampagne id de la campagne de la session
	 * @param capacite id de la dominante de la session
	 */
	public Session(int id, LocalDateTime dateHeureDebut, LocalDateTime dateHeureFin, int capacite, int idCampagne, int idDominante) {
		this.id = id;
		this.dateHeureDebut = dateHeureDebut;
		this.dateHeureFin = dateHeureFin;
		this.capacite = capacite;
		this.idCampagne = idCampagne;
		this.idDominante = idDominante;
	}

	/**
	 * getter pour l'attribut id
	 * @return valeur de l'identifiant de la session
	 */
	public int getId() {
		return id;
	}

	/**
	 * getter pour l'attribut dateHeureDebut
	 * @return valeur de la date heure de début
	 */
	public LocalDateTime getDateHeureDebut() {
		return this.dateHeureDebut;
	}

	/**
	 * getter pour l'attribut dateHeureFin
	 * @return valeur de la date heure de fin
	 */
	public LocalDateTime getDateHeureFin() {
		return dateHeureFin;
	}

	/**
	 * getter pour l'attribut capacite
	 * @return valeur de la capacite
	 */
	public int getCapacite() {
		return capacite;
	}
	
	/**
	 * getter pour l'attribut id Campagne
	 * @return l'id de la campagne
	 */
	public int getIdCampagne() {
		return idCampagne;
	}

	/**
	 * getter pour l'attribut id Dominante
	 * @return l'id de la dominante
	 */
	public int getIdDominante() {
		return idDominante;
	}
	
	/**
	 * setter pour l'attribut id
	 * @param id : nouvelle id de la session
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * setter pour l'attribut dateHeureDebut
	 * @param dateHeureDebut : nouvelle valeur de la date heure de début
	 */
	public void setDateHeureDebut(LocalDateTime dateHeureDebut) {
		this.dateHeureDebut = dateHeureDebut;
	}

	/**
	 * setter pour l'attribut dateHeureFin
	 * @param dateHeureFin : nouvelle valeur de la date heure de fin
	 */
	public void setDateHeureFin(LocalDateTime dateHeureFin) {
		this.dateHeureFin = dateHeureFin;
	}

	/**
	 * setter pour l'attribut capacite
	 * @param capacite : nouvelle valeur de la capacite
	 */
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	/**
	 * Redefinition de la methode toString permettant de definir la traduction de l'objet en String
	 * pour l'affichage dans la console par exemple
	 */
	@Override
	public String toString() {
		return "Session [id=" + id + ", dateHeureDebut=" + dateHeureDebut + ", dateHeureFin=" + dateHeureFin
				+ ", capacite=" + capacite + "]";
	}

	
	
}
