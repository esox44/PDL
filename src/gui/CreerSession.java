package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import dao.DominanteDAO;
import dao.SessionDAO;
import model.Dominante;
import model.Session;

public class CreerSession extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JSpinner JSpinnerDateHeureFin = new JSpinner();
	JSpinner JSpinnerDateHeureDebut = new JSpinner();
	JSpinner JSpinnerCapacite = new JSpinner();
	JComboBox<Dominante> JComboBoxDominante = new JComboBox<Dominante>();
	int idCampagne;
	ArrayList<Dominante> listDominante;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					DominanteDAO dominanteDAO = new DominanteDAO();					
					CreerSession frame = new CreerSession(1, dominanteDAO.getList());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreerSession(int idCampagne, ArrayList<Dominante> listDominante) {
		
		this.idCampagne = idCampagne;
		this.listDominante = listDominante;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(69, 123, 157));
		panel.setForeground(new Color(69, 123, 157));
		panel.setBounds(24, 24, 135, 352);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton JButtonDominante = new JButton("Dominante");
		JButtonDominante.setBounds(10, 54, 116, 32);
		panel.add(JButtonDominante);
		
		JButton JButtonCampagne = new JButton("Campagne");
		JButtonCampagne.setBounds(10, 11, 116, 32);
		panel.add(JButtonCampagne);
		
		JLabel JLabelCrerSession = new JLabel("Créer une Session");
		JLabelCrerSession.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		JLabelCrerSession.setBounds(233, 36, 223, 41);
		contentPane.add(JLabelCrerSession);
		
		JSpinnerDateHeureFin.setBounds(432, 157, 113, 20);
		contentPane.add(JSpinnerDateHeureFin);
		JSpinnerDateHeureFin.setModel(new SpinnerDateModel(new Date(1773961200000L), null, null, Calendar.DAY_OF_YEAR));
		
		JLabel JLabelDateHeureFin = new JLabel("Date et heure de fin : ");
		JLabelDateHeureFin.setBounds(233, 160, 189, 14);
		contentPane.add(JLabelDateHeureFin);
		
		JSpinnerDateHeureDebut.setBounds(432, 122, 113, 20);
		contentPane.add(JSpinnerDateHeureDebut);
		JSpinnerDateHeureDebut.setModel(new SpinnerDateModel(new Date(1773961200000L), null, null, Calendar.DAY_OF_YEAR));
		
		JLabel JLabelDateHeureDebut = new JLabel("Date et heure de début : ");
		JLabelDateHeureDebut.setBounds(233, 125, 189, 14);
		contentPane.add(JLabelDateHeureDebut);
		
		JSpinnerCapacite.setBounds(432, 193, 43, 20);
		contentPane.add(JSpinnerCapacite);
		
		JLabel JLabelCapacite = new JLabel("Capacité :");
		JLabelCapacite.setBounds(233, 196, 189, 14);
		contentPane.add(JLabelCapacite);
		
		JComboBoxDominante.setBounds(432, 231, 135, 32);
		// ON APPLIQUE LE RENDERER DIRECTEMENT ICI EN "CLASSE ANONYME" :
		JComboBoxDominante.setRenderer(new DefaultListCellRenderer() {
		    @Override
		    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		        
		        if (value instanceof Dominante) {
		            // On extrait et on affiche juste le nom
		            setText(((Dominante) value).getNom()); 
		        }
		        return this;
		    }
		});
		for(Dominante dominante : listDominante) {
			JComboBoxDominante.addItem(dominante);
		}		
		contentPane.add(JComboBoxDominante);
		
		JLabel JLabelDominante = new JLabel("Dominante :");
		JLabelDominante.setBounds(233, 240, 189, 14);
		contentPane.add(JLabelDominante);
		
		JButton JButtonCreer = new JButton("Créer");
		JButtonCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionDAO sessionDAO = new SessionDAO();	
				Session session = sessionValide();
				if(session != null) {
					if(sessionDAO.add(session) > 0) {
						afficherSucces(contentPane,"L'action à bien été effectuée");
					}else {
						afficherErreur(contentPane, "L'action n'à pas été effectuée, une erreur est survenue");
					}					
				}	
			}
		});
		JButtonCreer.setBounds(429, 344, 116, 32);
		contentPane.add(JButtonCreer);
	}
	
	
	/**
	 * Vérification que les champs sont valides
	 */
	public Session sessionValide() {
	    
	    // 1. Date de début
	    Date dateDebutRecuperee = (Date) JSpinnerDateHeureDebut.getValue();
	    if (dateDebutRecuperee == null) {
	        afficherErreur(contentPane, "Veuillez renseigner une date de début.");
	        return null;
	    }
	    LocalDateTime dateDebut = dateDebutRecuperee.toInstant()
	            .atZone(ZoneId.systemDefault())
	            .toLocalDateTime();
	    
	    // 2. Date de fin 
	    Date dateFinRecuperee = (Date) JSpinnerDateHeureFin.getValue(); 
	    if (dateFinRecuperee == null) {
	        afficherErreur(contentPane, "Veuillez renseigner une date de fin.");
	        return null;
	    }
	    LocalDateTime dateFin = dateFinRecuperee.toInstant()
	            .atZone(ZoneId.systemDefault())
	            .toLocalDateTime();

	    // 3. Logique des dates (Chronologie)
	    if (dateFin.isBefore(dateDebut)) {
	        afficherErreur(contentPane, "Incohérence : La date de fin ne peut pas être antérieure à la date de début.");
	        return null; 
	    }
	    
	    // 4. Vérification de la session de 30 minutes EXACTEMENT
	    long dureeEnMinutes = java.time.temporal.ChronoUnit.MINUTES.between(dateDebut, dateFin);

	    if (dureeEnMinutes != 30) {
	        afficherErreur(contentPane, "La session doit durer exactement 30 minutes. (Durée actuelle : " + dureeEnMinutes + " minutes).");
	        return null;
	    }
	    
	    // 5. Champs numériques
	    Object capaciteO = JSpinnerCapacite.getValue();
	    
	    if (capaciteO == null) {
	        afficherErreur(contentPane, "Veuillez remplir tous les champs numériques.");
	        return null;
	    }
	    
	    int capacite = (int) capaciteO;
	    
	    if (capacite <= 0) {
	        afficherErreur(contentPane, "Le nombre de choix maximum doit être strictement supérieur à 0.");
	        return null;
	    }

	    // 6. Dominante (Liste déroulante)
	    Dominante dominanteSelectionnee = (Dominante) JComboBoxDominante.getSelectedItem();

	    if (dominanteSelectionnee == null) {
	        afficherErreur(contentPane, "Veuillez sélectionner une dominante.");
	        return null;
	    }

	    // Vous avez maintenant accès à l'ID en arrière-plan ET au nom !
	    int idDominante = dominanteSelectionnee.getId();
	    
	    // 6. Tout est valide !
	    return new Session(dateDebut, dateFin, capacite, idCampagne, idDominante);
	}
	
	/**
	 * Affiche une pop-up de succès
	 */
	public void afficherSucces(Component parent, String message) {
	    JOptionPane.showMessageDialog(parent, 
	                                  message, 
	                                  "Succès", 
	                                  JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Affiche une pop-up d'erreur
	 */
	public void afficherErreur(Component parent, String message) {
	    JOptionPane.showMessageDialog(parent, 
	                                  message, 
	                                  "Erreur", 
	                                  JOptionPane.ERROR_MESSAGE);
	}
}
