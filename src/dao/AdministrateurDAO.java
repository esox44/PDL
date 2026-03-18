package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table Administrateur
 * 
 * @author ESIGELEC - TIC Department
 * @version 2.0
 * */
public class AdministrateurDAO extends ConnectionDAO {
	/**
	 * Constructor
	 * 
	 */
	public AdministrateurDAO() {
		super();
	}

	/**
	 * Permet d'ajouter un Administrateur dans la table Administrateur.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param supplier l'administrateur a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Administrateur administrateur) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			int generated_Keys;
			
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			// 1. Préparation de l'insertion
			String sql = "INSERT INTO ADMINISTRATEUR(nom, prenom, identifiantConnexion, motDePasse) VALUES(?, ?, ?, ?)";
			
			// 2. On indique à Oracle le nom de la colonne générée à nous renvoyer
		    String[] colonnesRetournees = { "idAdministrateur" };
		    ps = con.prepareStatement(sql, colonnesRetournees);
			
		    // 3. preparation de l'instruction SQL, chaque ? represente une valeur
		    ps.setString(1, administrateur.getNom());
			ps.setString(2, administrateur.getPrenom());
			ps.setString(3, administrateur.getIdentifiantConnexion());
			ps.setString(4, administrateur.getMotDePasse());
		    
			// 4. Execution de la requete
			returnValue = ps.executeUpdate();
			
			// 5. Récupération de la clé générée
		    try (ResultSet rs = ps.getGeneratedKeys()) {
		        if (rs.next()) {
		            // Ajout de l'ID directement à l'objet inscription !
		        	administrateur.setId(rs.getInt(1));
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
	 * Permet de recuperer un administrateur a partir de son identifiant de connexion
	 * 
	 * @param identifiantConnexion de l administrateur a recuperer
	 * @return l administrateur trouve;
	 * 			null si aucun administrateur ne correspond a cette reference
	 */
	public Administrateur getAdministrateurSelonidAministrateur(int idAdministrateur) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Administrateur returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ADMINISTRATEUR WHERE idAdministrateur = ?");
			ps.setInt(1, idAdministrateur);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Administrateur(rs.getInt("idAdministrateur"),
									       rs.getString("nom"),
									       rs.getString("prenom"),
									       rs.getString("identifiantConnexion"),
									       rs.getString("motDePasse"));
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
	 * Permet de recuperer un administrateur a partir de son son identifiant de connexion et mdp
	 * 
	 * @param identifiantConnexion de l administrateur
	 * @param motDePasse de l administrateur
	 * @return l administrateur trouve;
	 * 			null si aucun etudiant ne correspond a cette reference
	 */
	public Administrateur getAdministrateurConnexion(String identifiantConnexion, String motDePasse) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Administrateur returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ADMINISTRATEUR WHERE identifiantConnexion LIKE ? AND motDePasse LIKE ?");
			ps.setString(1, identifiantConnexion);
			ps.setString(2, motDePasse);
			
			
			// on execute la requete
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Administrateur(rs.getInt("idAdministrateur"),
									       rs.getString("nom"),
									       rs.getString("prenom"),
									       rs.getString("identifiantConnexion"),
									       rs.getString("motDePasse"));
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
		AdministrateurDAO administrateurDAO = new AdministrateurDAO();
		
		
		// test du constructeur
		Administrateur administrateur = new Administrateur("Loris","soude","17","17");
		
		// test de la methode add
		returnValue = administrateurDAO.add(administrateur);
		System.out.println(returnValue + " administrateur ajoute");
		
		
		// test de la methode get
		//Administrateur administrateur2 = administrateurDAO.getAdminSelonidAministrateur(1);
		// appel implicite de la methode toString de la classe Object (a eviter)
		System.out.println(administrateur);
		System.out.println();
		
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
			System.out.println(returnValue + " administrateur supprime");
		}
		*/
		System.out.println();
	}
}