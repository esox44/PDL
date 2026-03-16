package model;

import java.time.LocalDateTime;

public class Inscription {

	private int id;
	private LocalDateTime date;
	private int ordrePreference;
	private String statut;
	private Etudiant etudiant;
	private Session session;
	
	public Inscription(LocalDateTime date, int ordrePreference, String statut, Etudiant etudiant, Session session) {
		this.date = date;
		this.ordrePreference = ordrePreference;
		this.statut = statut;
		this.etudiant = etudiant;
		this.session = session;
	}
	
	public Inscription(int id, LocalDateTime date, int ordrePreference, String statut, Etudiant etudiant, Session session) {
		this.id = id;
		this.date = date;
		this.ordrePreference = ordrePreference;
		this.statut = statut;
		this.etudiant = etudiant;
		this.session = session;
	}
	
	public int getId() {
		return id;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public int getOrdrePreference() {
		return ordrePreference;
	}
	public String getStatut() {
		return statut;
	}
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public Session getSession() {
		return session;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
