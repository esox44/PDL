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
			
		    System.out.println("id session : " + inscription.getIdSession());
		    System.out.println("id etudiant : " + inscription.getIdEtudiant());
		    
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

		System.out.println("=== DÉBUT DU TEST DE LA BASE DE DONNÉES ===");

        try {
        	
            // --------------------------------------------------------
            // ÉTAPE 1 : Création de la Campagne
            // --------------------------------------------------------
            CampagneDAO campagneDAO = new CampagneDAO();
            LocalDateTime debutC = LocalDateTime.now();
            LocalDateTime finC = debutC.plusDays(30);
            
            // Hypothèse sur votre constructeur (dateDebut, dateFin, nbChoixMax, promotion, statut)
            Campagne campagne = new Campagne(debutC, finC, 5, 2025, "Création");
            campagneDAO.ajouterCampagne(campagne);
            System.out.println("[OK] Campagne créée avec l'ID : " + campagne.getId());

            // --------------------------------------------------------
            // ÉTAPE 2 : Création de la Dominante
            // --------------------------------------------------------
            DominanteDAO dominanteDAO = new DominanteDAO();
            Dominante dominante = new Dominante("Génie Logiciel"); // L'ID 0 sera écrasé par la BDD
            dominanteDAO.ajouterDominante(dominante);
            System.out.println("[OK] Dominante créée avec l'ID : " + dominante.getId());

            // --------------------------------------------------------
            // ÉTAPE 3 : Création de la Session (lie Campagne + Dominante)
            // --------------------------------------------------------
            SessionDAO sessionDAO = new SessionDAO();
            LocalDateTime debutS = LocalDateTime.now().plusDays(1);
            LocalDateTime finS = debutS.plusHours(2);
            
            Session session = new Session(debutS, finS, 30, campagne.getId(), dominante.getId());
            sessionDAO.ajouterSession(session);
            System.out.println("[OK] Session créée avec l'ID : " + session.getId());

            // --------------------------------------------------------
            // ÉTAPE 4 : Création de l'Étudiant (Élève)
            // --------------------------------------------------------
            // /!\ Assurez-vous d'avoir créé EtudiantDAO sur le même modèle que les autres !
            EtudiantDAO etudiantDAO = new EtudiantDAO();
            
            // Hypothèse de constructeur : nom, prenom, identifiant, mdp, promo
            Etudiant etudiant = new Etudiant("Khaled", "Corentin", "ckhaled", "azerty123", 2025);
            etudiantDAO.add(etudiant); // Pensez bien à récupérer l'ID généré dans votre DAO !
            System.out.println("[OK] Étudiant créé avec l'ID : " + etudiant.getId());
            
            
            System.out.println("id session : " + session.getId());
		    System.out.println("id etudiant : " + etudiant.getId());
		    
            
            // --------------------------------------------------------
            // ÉTAPE 5 : Création de l'Inscription (lie Étudiant + Session)
            // --------------------------------------------------------
            InscriptionDAO inscriptionDAO = new InscriptionDAO();
            Inscription inscription = new Inscription(LocalDateTime.now(),1,"En attente",1, 1);
            inscriptionDAO.add(inscription);
            System.out.println("[OK] Inscription réalisée avec succès avec l'ID : " + inscription.getId());

            System.out.println("\n=== TEST TERMINÉ AVEC SUCCÈS ! ===");
			
        } catch (Exception e) {
            System.err.println("\n[ERREUR] Le scénario s'est arrêté à cause de l'erreur suivante :");
            e.printStackTrace();
        }
			
		
	}
}