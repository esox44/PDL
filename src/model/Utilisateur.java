package model;

public abstract class Utilisateur {
	
	private int id;
	private String nom; 
	private String prenom; 
	private String identifiantConnexion;
	private String motDePasse;
	
	public Utilisateur(int id, String nom, String prenom, String identifiantConnexion, String motDePasse) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.identifiantConnexion = identifiantConnexion;
		this.motDePasse = motDePasse;
	}
	
	
	
}
