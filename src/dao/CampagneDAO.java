package dao;
import java.sql.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table supplier
 * 
 * @author ESIGELEC - TIC Department
 * @version 2.0
 * */
public class CampagneDAO extends ConnectionDAO {
	/**
	 * Constructor
	 * 
	 */
	public CampagneDAO() {
		super();
	}
	/**
	 * Permet d'ajouter une campagne dans la table campagne.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param campagne le fournisseur a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int ajouterCampagne(Campagne campagne) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {
			
			int generated_Keys;
			
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			// 1. Préparation de l'insertion
			String sql = "INSERT INTO campagne(dateDebut, dateFin, nbChoixMax, statut, promotion) VALUES(?, ?, ?, ? ,? )";
			
			// 2. On indique à Oracle le nom de la colonne générée à nous renvoyer
		    String[] colonnesRetournees = { "idCampagne" };
		    ps = con.prepareStatement(sql, colonnesRetournees);
			
		    // 3. Affectation des différentes valeurs
		    ps.setTimestamp(1, Timestamp.valueOf(campagne.getDateDebut()));
		    ps.setTimestamp(2, Timestamp.valueOf(campagne.getDateFin()));
			ps.setInt(3, campagne.getNbChoixMax());
			ps.setString(4, campagne.getStatut());
			ps.setInt(5, campagne.getPromotion());

			// 4. Execution de la requete
			returnValue = ps.executeUpdate();

			// 5. Récupération de la clé générée SIMPLEMENT
		    try (ResultSet rs = ps.getGeneratedKeys()) {
		        if (rs.next()) {
		            // Ajout de l'ID directement à l'objet inscription !
		        	campagne.setId(rs.getInt(1));
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
	 * Permet de modifier uniquement le statut d'une campagne dans la table campagne
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * * @param campagne la campagne contenant le nouveau statut et l'ID à modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int modifierStatutCampagne(Campagne campagne) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			// 1. Préparation de la modification (uniquement le statut)
			String sql = "UPDATE campagne SET statut = ? WHERE idCampagne = ?";
			ps = con.prepareStatement(sql);
			
			// 2. Affectation des différentes valeurs
			ps.setString(1, campagne.getStatut());
			ps.setInt(2, campagne.getId()); // On utilise l'ID pour savoir quelle ligne modifier

			// 3. Execution de la requete
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
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	
	
	public static void main(String[] args) throws SQLException {
		
		CampagneDAO campagneDAO = new CampagneDAO();
		
		/**LocalDateTime myDateObj = LocalDateTime.now();
		myDateObj.withYear(2025);
		
		LocalDateTime myDateObj2 = LocalDateTime.now();
		myDateObj.withYear(2026);

		// Date de fin : 1er juillet 2025 à 17:00
		LocalDate dateFin = LocalDate.of(2025, 7, 1);
		
		// 1. Test de l'ajout
		Campagne campagne = new Campagne(myDateObj, myDateObj2, 7, 2025, "Création");
		campagneDAO.ajouterCampagne(campagne);
		System.out.println("Après ajout : " + campagne);
		**/
		
		// 2. Test de la modification du statut
		LocalDateTime myDateObj = LocalDateTime.now();
		Campagne campagne = new Campagne(21,myDateObj, myDateObj, 0, 0, "Création");
		campagne.setStatut("En cours"); // On change le statut de l'objet
		int lignesModifiees = campagneDAO.modifierStatutCampagne(campagne); // On met à jour en BDD
		
		System.out.println("Nombre de lignes modifiées : " + lignesModifiees);
		System.out.println("Après modification : " + campagne);
        	
	
		
	}
	
	
}