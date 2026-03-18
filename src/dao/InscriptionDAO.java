package dao;
import java.sql.*;
import java.time.LocalDate;
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
			 
		    ps.setObject(1, inscription.getDate());
			ps.setString(2, Integer.toString(inscription.getOrdrePreference()));
			ps.setString(3, inscription.getStatut());
			ps.setString(4, Integer.toString(inscription.getIdSession()));
			ps.setString(4, Integer.toString(inscription.getIdEtudiant()));
			 
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
	 * Permet de recuperer un inscription a partir de sa reference
	 * 
	 * @param id de l inscription a recuperer
	 * @return l inscription trouve;
	 * 			null si aucun inscription ne correspond a cette reference
	 */
	public Inscription getInscriptionSelonidInscription(int id) {
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
	 * ATTENTION : Cette méthode n'a pas vocation à être executée lors d'une utilisation normale du programme !
	 * Elle existe uniquement pour TESTER les méthodes écrites au-dessus !
	 * 
	 * @param args non utilisés
	 * @throws SQLException si une erreur se produit lors de la communication avec la BDD
	 */
	public static void main(String[] args) throws SQLException {
		int returnValue;
		InscriptionDAO inscriptionDAO = new InscriptionDAO();
		
		Session session = new Session(LocalDate.now(),"8h","9h",30);
		
		
		/**
		// test de la methode getList
		ArrayList<Supplier> list = supplierDAO.getList();
		for (Supplier s : list) {
			// appel explicite de la methode toString de la classe Object (a privilegier)
			System.out.println(s.toString());
		}
		System.out.println();
		// test de la methode delete
		// On supprime les 3 articles qu'on a créé
		returnValue = 0;
		for (int id : ids) {
//			returnValue = supplierDAO.delete(id);
			System.out.println(returnValue + " fournisseur supprime");
		}
		*/
		System.out.println();
	}
}