package dao;
import java.sql.*;

import java.time.LocalDateTime;
import java.time.LocalDate;

import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table supplier
 * 
 * @author ESIGELEC - TIC Department
 * @version 2.0
 * */
public class SessionDAO extends ConnectionDAO {
	
	/**
	 * Constructor
	 * 
	 */
	public SessionDAO() {
		super();
	}
	
	/**
	 * Permet d'ajouter une Session dans la table sesions.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param session la session à ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int ajouterSession(Session session) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {	
			
			int generated_Keys;
			
			con = DriverManager.getConnection(URL, LOGIN, PASS);
					
			// 1. Préparation de l'insertion
			String sql = "INSERT INTO sessionetu (dateHeureDebut, dateHeureFin, capacite, idCampagne, idDominante) VALUES(?, ?, ? ,? ,?)";
			
			// 2. Colonne générée
			String[] colonnesRetournees = { "idSession" };
			ps = con.prepareStatement(sql, colonnesRetournees);
			
			// 3. Affectation des différentes valeurs 
			ps.setTimestamp(1, java.sql.Timestamp.valueOf(session.getDateHeureDebut()));
			ps.setTimestamp(2, java.sql.Timestamp.valueOf(session.getDateHeureFin()));
			ps.setInt(3, session.getCapacite());
			ps.setInt(4, session.getIdCampagne());
			ps.setInt(5, session.getIdDominante());

			// 4. Execution de la requete
			returnValue = ps.executeUpdate();

			// 5. Récupération de la clé générée SIMPLEMENT
		    try (ResultSet rs = ps.getGeneratedKeys()) {
		        if (rs.next()) {
		            // Ajout de l'ID directement à l'objet inscription !
		        	session.setId(rs.getInt(1));
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {}
		}
		return returnValue;
	}
	
	
	/**
	 * Permet de modifier un session dans la table session.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param dominante le fournisseur a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Session session) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    int returnValue = 0;

	    try {
	        con = DriverManager.getConnection(URL, LOGIN, PASS);

	        // Prépare l'UPDATE pour la table sessionetu
	        ps = con.prepareStatement(
	            "UPDATE sessionetu SET dateHeureDebut = ?, dateHeureFin = ?, capacite = ?, idCampagne = ?, idDominante = ? WHERE idSession = ?"
	        );

	        ps.setTimestamp(1, java.sql.Timestamp.valueOf(session.getDateHeureDebut()));
	        ps.setTimestamp(2, java.sql.Timestamp.valueOf(session.getDateHeureFin()));
	        ps.setInt(3, session.getCapacite());
	        ps.setInt(4, session.getIdCampagne());      
	        ps.setInt(5, session.getIdDominante());    
	        ps.setInt(6, session.getId());                   

	      
	        returnValue = ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { 
	        	if (ps != null) ps.close(); 
	        	} 
	        catch (Exception ignore) {}
	        try { 
	        	if (con != null) con.close();
	        	}
	        catch (Exception ignore) {}
	    }

	    return returnValue;
	}
	
	
	/**
	 * Permet de supprimer une session par id dans la table session.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id de la session à supprimer
	 * @return retourne le nombre de lignes supprimees dans la table
	 */
	
	public int delete(int id) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    int returnValue = 0;

	    try {
	        // connexion à la base de données
	        con = DriverManager.getConnection(URL, LOGIN, PASS);

	        // préparation de l'instruction SQL : suppression d'une session par id
	        ps = con.prepareStatement("DELETE FROM sessionetu WHERE idSession = ?");
	        ps.setInt(1, id);

	        // exécution de la requête
	        returnValue = ps.executeUpdate();

	    } catch (Exception e) {
	        // si la session est référencée ailleurs (clé étrangère)
	        if (e.getMessage().contains("ORA-02292")) {
	            System.out.println("Cette session est référencée ailleurs, suppression impossible !"
	                             + " Supprimer d'abord les dépendances.");
	        } else {
	            e.printStackTrace();
	        }
	    } finally {
	        // fermeture du preparedStatement et de la connexion
	        try { if (ps != null) ps.close(); } catch (Exception ignore) {}
	        try { if (con != null) con.close(); } catch (Exception ignore) {}
	    }

	    return returnValue;
	}
	public static void main(String[] args) throws SQLException {
		
		
		 // Date de début : 1er juin 2025 à 09:00
        //LocalDate dateDebut = LocalDate.of(2025, 6, 1);
       // LocalDate dateDebut1 = LocalDate.of(2025, 6, 8);

        // Date de fin : 1er juillet 2025 à 17:00
       // LocalDate dateFin = LocalDate.of(2025, 7, 1);
        
        //Campagne campagne1 = new Campagne(12, dateDebut1, dateFin, 2025,  false);
        //Campagne campagne = new Campagne(12, dateDebut1, dateFin, 2025,  false);
        //Dominante dominante = new Dominante(3 , "Cyber");

       // Session session = new Session(2, LocalDateTime.now(),  LocalDateTime.now().plusHours(2), 30, campagne, dominante );
       // Session session1 = new Session(2, LocalDateTime.now(),  LocalDateTime.now().plusHours(1), 20, campagne, dominante );
        
        SessionDAO sdao = new SessionDAO();
       // sdao.ajouterSession(session);
        
        sdao.delete(2);
		
	}
	
	
}