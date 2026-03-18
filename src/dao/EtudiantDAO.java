package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table supplier
 * 
 * @author Corentin Khaled
 * @version 2.0
 * */
public class EtudiantDAO extends ConnectionDAO {
	/**
	 * Constructor
	 * 
	 */
	public EtudiantDAO() {
		super();
	}

	/**
	 * Permet d'ajouter un Etudiant dans la table Etudiant.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param etudiant a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Etudiant etudiant) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			int generated_Keys;
			
			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			
			// 1. Préparation de l'insertion
		    String sql = "INSERT INTO Etudiant(nom, prenom, identifiantConnexion, motDePasse, promo) VALUES(?, ?, ?, ?, ?)";
			
		    // 2. On indique à Oracle le nom de la colonne générée à nous renvoyer
		    String[] colonnesRetournees = { "idEtudiant" };
		    ps = con.prepareStatement(sql, colonnesRetournees);
		        
			// 3. preparation de l'instruction SQL, chaque ? represente une valeur
			ps.setString(1, etudiant.getNom());
			ps.setString(2, etudiant.getPrenom());
			ps.setString(3, etudiant.getIdentifiantConnexion());
			ps.setString(4, etudiant.getMotDePasse());
			ps.setString(5, Integer.toString(etudiant.getPromotion()));
			
			// 4. Execution de la requete
			returnValue = ps.executeUpdate();
			
			// 5. Récupération de la clé générée
		    try (ResultSet rs = ps.getGeneratedKeys()) {
		        if (rs.next()) {
		            // Ajout de l'ID directement à l'objet inscription !
		        	etudiant.setId(rs.getInt(1));
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
	 * Permet de recuperer un etudiant a partir de son son identifiant de connexion et mdp
	 * 
	 * @param identifiantConnexion de l etudiant
	 * @param motDePasse de l etudiant
	 * @return l etudiant trouve;
	 * 			null si aucun etudiant ne correspond a cette reference
	 */
	public Etudiant getEtudiantConnexion(String identifiantConnexion, String motDePasse) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Etudiant returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ETUDIANT WHERE identifiantConnexion LIKE ? AND motDePasse LIKE ?");
			ps.setString(1, identifiantConnexion);
			ps.setString(2, motDePasse);
			
			
			// on execute la requete
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Etudiant(rs.getInt("idEtudiant"),
									       rs.getString("nom"),
									       rs.getString("prenom"),
									       rs.getString("identifiantConnexion"),
									       rs.getString("motDePasse"),
									       rs.getInt("promo"));
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
	 * Permet de recuperer un etudiant a partir de sa reference
	 * 
	 * @param id de l etudiant a recuperer
	 * @return l etudiant trouve;
	 * 			null si aucun etudiant ne correspond a cette reference
	 */
	public Etudiant getEtudiantSelonidEtudiant(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Etudiant returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ETUDIANT WHERE idEtudiant = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Etudiant(rs.getInt("idEtudiant"),
									       rs.getString("nom"),
									       rs.getString("prenom"),
									       rs.getString("identifiantConnexion"),
									       rs.getString("motDePasse"),
									       rs.getInt("promo"));
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
	 * Permet de recuperer un Etudiant a partir de son identifiant de connexion
	 * 
	 * @param identifiantConnexion de l etudiant a recuperer
	 * @return l administrateur trouve;
	 * 			null si aucun fournisseur ne correspond a cette reference
	 */
	public Etudiant getEtudiantSelonidentifiantConnexion(int identifiantConnexion) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Etudiant returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM ETUDIANT WHERE identifiantConnexion = ?");
			ps.setInt(1, identifiantConnexion);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Etudiant(rs.getInt("idEtudiant"),
									       rs.getString("nom"),
									       rs.getString("prenom"),
									       rs.getString("identifiantConnexion"),
									       rs.getString("motDePasse"),
									       rs.getInt("promo"));
				
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
		EtudiantDAO etudiantDAO = new EtudiantDAO();
		
		Etudiant etudiant = etudiantDAO.getEtudiantConnexion("32","32");
		
		System.out.println(etudiant);
		System.out.println();
		
		
		System.out.println();
	}
}