package model;

public class Administrateur extends Utilisateur{

	public Administrateur(String nom, String prenom, String identifiantConnexion, String motDePasse) {
		super(nom, prenom, identifiantConnexion, motDePasse);
	}
	
	public Administrateur(int id, String nom, String prenom, String identifiantConnexion, String motDePasse) {
		super(id, nom, prenom, identifiantConnexion, motDePasse);
	}

	
}
