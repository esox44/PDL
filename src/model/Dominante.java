package model;

/**
 * Classe Dominante
 * @author ESIGELEC - TIC Department
 * @version 1.2
 * */
public class Dominante {

	/** 
	 * identifiant de la dominante
	 */
	private int id;

	/**
	 * nom
	 */
	private String nom;

	/**
	 * Constructor
	 * @param nom nom de la dominante
	 */
	public Dominante(String nom) {
		this.nom = nom;
	}

	/**
	 * getter pour l'attribut nom
	 * @return valeur du nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * setter pour l'attribut nom
	 * @param nom : nouvelle valeur du nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Redefinition de la methode toString permettant de definir la traduction de l'objet en String
	 * pour l'affichage dans la console par exemple
	 */
	@Override
	public String toString() {
		return "Dominante [ref : " + id + ", " + nom + "]";
	}
}