package model;

public abstract class Utilisateur {
	
	protected int id;
	protected String nom; 
	protected String prenom; 
	protected String identifiantConnexion;
	protected String motDePasse;
	
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

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", identifiantConnexion="
				+ identifiantConnexion + ", motDePasse=" + motDePasse + "]";
	}
	
	
	
	
	
}
