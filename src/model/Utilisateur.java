package model;

public abstract class Utilisateur {
	
	private int id;
	private String nom; 
	private String prenom; 
	private String identifiantConnexion;
	private String motDePasse;
	
	public Utilisateur(String nom, String prenom, String identifiantConnexion, String motDePasse) {
		this.nom = nom;
		this.prenom = prenom;
		this.identifiantConnexion = identifiantConnexion;
		this.motDePasse = motDePasse;
	}
	
	public Utilisateur(int id, String nom, String prenom, String identifiantConnexion, String motDePasse) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.identifiantConnexion = identifiantConnexion;
		this.motDePasse = motDePasse;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getIdentifiantConnexion() {
		return identifiantConnexion;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", identifiantConnexion="
				+ identifiantConnexion + ", motDePasse=" + motDePasse + "]";
	}
	
	
	
	
	
}
