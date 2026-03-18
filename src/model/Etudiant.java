package model;

import java.util.ArrayList;

public class Etudiant extends Utilisateur{

	private int promotion;
	private ArrayList listeInscription;
	
	public Etudiant(String nom, String prenom, String identifiantConnexion, String motDePasse, int promotion) {
		super(nom, prenom, identifiantConnexion, motDePasse);
		this.promotion = promotion;
	}
	
	public Etudiant(int id, String nom, String prenom, String identifiantConnexion, String motDePasse, int promotion) {
		super(id, nom, prenom, identifiantConnexion, motDePasse);
		this.promotion = promotion;
	}
	
	public Etudiant(int id, String nom, String prenom, String identifiantConnexion, String motDePasse, int promotion, ArrayList listeInscription) {
		super(id, nom, prenom, identifiantConnexion, motDePasse);
		this.promotion = promotion;
		this.listeInscription = listeInscription;
	}

	public int getPromotion() {
		return this.promotion;
	}

	@Override
	public String toString() {
		return super.toString() + "Etudiant [promotion=" + promotion + ", listeInscription=" + listeInscription + "]";
	}
	
	
}
