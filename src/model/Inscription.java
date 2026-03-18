package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Inscription {

	private int id;
	private LocalDateTime date;
	private int ordrePreference;
	private String statut;
	private int idEtudiant;
	private int idSession;
	
	public Inscription(LocalDateTime date, int ordrePreference, String statut, int idEtudiant, int idSession) {
		this.date = date;
		this.ordrePreference = ordrePreference;
		this.statut = statut;
		this.idEtudiant = idEtudiant;
		this.idEtudiant = idEtudiant;
	}
	
	public Inscription(int id, LocalDateTime date, int ordrePreference, String statut, int idEtudiant, int idSession) {
		this.id = id;
		this.date = date;
		this.ordrePreference = ordrePreference;
		this.statut = statut;
		this.idEtudiant = idEtudiant;
		this.idSession= idSession;
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
	

	public int getIdEtudiant() {
		return idEtudiant;
	}

	public int getIdSession() {
		return idSession;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
