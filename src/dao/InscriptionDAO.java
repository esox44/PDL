package dao;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table supplier
 * 
 * @author Corentin Khaled
 * @version 2.0
 * */
public class InscriptionDAO extends ConnectionDAO {
	/**
	 * Constructor
	 * 
	 */
	public InscriptionDAO() {
		super();
	}

	/**
	 * Permet d'ajouter une inscription dans la table Inscription.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param inscription a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Inscription inscription) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			int generated_Keys;
			
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
					
			// 1. Préparation de l'insertion
			String sql = "INSERT INTO INSCRIPTION(dateRealisee, ordrePreference, statut, idSession, idEtudiant) VALUES(?, ?, ?, ?, ?)";
				
			// 2. On indique à Oracle le nom de la colonne générée à nous renvoyer
		    String[] colonnesRetournees = { "idInscription" };
		    ps = con.prepareStatement(sql, colonnesRetournees);
		    
		    ps.setTimestamp(1, Timestamp.valueOf(inscription.getDate()));
			ps.setInt(2, inscription.getOrdrePreference());
			ps.setString(3, inscription.getStatut());
			ps.setInt(4, inscription.getIdSession());
			ps.setInt(5, inscription.getIdEtudiant());
			
			// 4. Execution de la requete
			returnValue = ps.executeUpdate();

			// 5. Récupération de la clé générée SIMPLEMENT
		    try (ResultSet rs = ps.getGeneratedKeys()) {
		        if (rs.next()) {
		            // Ajout de l'ID directement à l'objet inscription !
		            inscription.setId(rs.getInt(1));
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
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	/**
	 * Permet de modifier une dominante dans la table dominante.
	 */
	public int update(Inscription inscription) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("UPDATE inscription set statut = ? WHERE idInscription = ?");
			ps.setString(1, inscription.getStatut());
			ps.setInt(2, inscription.getId());

			returnValue = ps.executeUpdate();

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
	 * Permet de supprimer une inscription par id dans la table inscription.
	 */
	public int delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			ps = con.prepareStatement("DELETE FROM inscription WHERE idInscription = ?");
			ps.setInt(1, id);

			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage() != null && e.getMessage().contains("ORA-02292")) {
				System.out.println("Cette session est référencée ailleurs, suppression impossible !"
                        + " Supprimer d'abord les dépendances.");
			} else {
				e.printStackTrace();
			}
		} finally {
			try { if (ps != null) ps.close(); } catch (Exception ignore) {}
			try { if (con != null) con.close(); } catch (Exception ignore) {}
		}
		return returnValue;
	}
	

	/**
	 * Permet de recuperer un inscription a partir de sa reference
	 * 
	 * @param id de l inscription a recuperer
	 * @return l inscription trouve;
	 * 			null si aucun inscription ne correspond a cette reference
	 */
	public Inscription getInscription(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Inscription returnValue = null;
		
		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM INSCRIPTION WHERE idInscription = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs	 = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				
				returnValue = new Inscription(rs.getInt("idInscription"),
											rs.getObject("dateRealisee", java.time.LocalDateTime.class),
											rs.getInt("ordrePreference"),
											rs.getString("statut"),
											rs.getInt("idEtudiant"),
											rs.getInt("idSession"));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ignore) {
			}
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
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	/**
	 * Permet de recuperer tous les inscriptions dans la table inscription
	 * 
	 * @return une ArrayList d'inscription
	 */
	public ArrayList<Inscription> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Inscription> returnValue = new ArrayList<Inscription>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM inscription ORDER BY idInscription");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Inscription(rs.getInt("idInscription"),
													rs.getTimestamp("dateRealisee").toLocalDateTime(),
													rs.getInt("ordrePreference"),
													rs.getString("statut"),
													rs.getInt("idEtudiant"),
													rs.getInt("idSession")));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	
	/**
	 * ATTENTION : Cette méthode n'a pas vocation à être executée lors d'une utilisation normale du programme !
	 * Elle existe uniquement pour TESTER les méthodes écrites au-dessus !
	 * 
	 * @param args non utilisés
	 * @throws SQLException si une erreur se produit lors de la communication avec la BDD
	 */
	public static void main(String[] args) throws SQLException {

		InscriptionDAO inscriptionDAO = new InscriptionDAO();
		
		LocalDateTime myDateObj = LocalDateTime.now();
		
		// Note : L'ID '3' sera écrasé par la base de données lors de l'insertion grâce au GENERATED BY DEFAULT.
		Inscription inscription = new Inscription(myDateObj,1,"En attente", 21, 1); 
		
		// 1 // Ajout
		//inscriptionDAO.add(inscription);
        
        // 2 // Updtate
		//inscription.setId(3);
        //inscription.setStatut("toto");
        //inscriptionDAO.update(inscription);
        
        // 3 // Affichage de toute
        /**ArrayList<Inscription> listeInscription = inscriptionDAO.getList();
        for(Inscription inscription2 : listeInscription) {
        	System.out.println(inscription2);
        }
        **/
        
        // 4 // Delete
		inscriptionDAO.delete(22);
			
		
	}
}