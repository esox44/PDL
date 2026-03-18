package model;

import java.time.LocalDate;
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
	 * date
	 */
	private LocalDate date;

	/**
	 * heure de debut
	 */
	private String heureDebut;

	/**
	 * heure de fin
	 */
	private String heureFin;

	/**
	 * capacite
	 */
	private int capacite;

	/**
	 * Constructor
	 * @param date date de la session
	 * @param heureDebut heure de debut
	 * @param heureFin heure de fin
	 * @param capacite capacite maximale
	 */
	public Session(LocalDate date, String heureDebut, String heureFin, int capacite) {
		this.date = LocalDate.now();
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.capacite = capacite;
	}

	/**
	 * getter pour l'attribut id
	 * @return valeur de l'identifiant de la session
	 */
	public int getId() {
		return id;
	}

	/**
	 * getter pour l'attribut date
	 * @return valeur de la date
	 */
	public LocalDate getDate() {
		return this.date;
	}

	/**
	 * getter pour l'attribut heureDebut
	 * @return valeur de l'heure de debut
	 */
	public String getHeureDebut() {
		return heureDebut;
	}

	/**
	 * getter pour l'attribut heureFin
	 * @return valeur de l'heure de fin
	 */
	public String getHeureFin() {
		return heureFin;
	}

	/**
	 * getter pour l'attribut capacite
	 * @return valeur de la capacite
	 */
	public int getCapacite() {
		return capacite;
	}

	/**
	 * setter pour l'attribut date
	 * @param date : nouvelle valeur de la date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * setter pour l'attribut heureDebut
	 * @param heureDebut : nouvelle valeur de l'heure de debut
	 */
	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}

	/**
	 * setter pour l'attribut heureFin
	 * @param heureFin : nouvelle valeur de l'heure de fin
	 */
	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
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
		return "Session [ref : " + id + ", " + date + ", " + heureDebut + ", " + heureFin + ", " + capacite + "]";
	}
}
