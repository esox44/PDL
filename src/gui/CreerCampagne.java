package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CampagneDAO;
import model.Campagne;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class CreerCampagne extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JSpinner JSpinnerDateFin = new JSpinner();
	JSpinner JSpinnerDateDebut = new JSpinner();
	JSpinner JSpinnerNBChoixMax = new JSpinner();
	JSpinner JSpinnerPromotion = new JSpinner();
	JComboBox JComboBoxStatut = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerCampagne frame = new CreerCampagne();
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
	public CreerCampagne() {
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
		
		JLabel JLabelCrerCampagne = new JLabel("Créer une Campagne");
		JLabelCrerCampagne.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		JLabelCrerCampagne.setBounds(233, 36, 223, 41);
		contentPane.add(JLabelCrerCampagne);
		
		JSpinnerDateFin.setBounds(432, 157, 113, 20);
		contentPane.add(JSpinnerDateFin);
		JSpinnerDateFin.setModel(new SpinnerDateModel(new Date(1773961200000L), null, null, Calendar.DAY_OF_YEAR));
		
		JLabel JLabelDateFin = new JLabel("Date fin : ");
		JLabelDateFin.setBounds(233, 160, 189, 14);
		contentPane.add(JLabelDateFin);
		
		JSpinnerDateDebut.setBounds(432, 122, 113, 20);
		contentPane.add(JSpinnerDateDebut);
		JSpinnerDateDebut.setModel(new SpinnerDateModel(new Date(1773961200000L), null, null, Calendar.DAY_OF_YEAR));
		
		JLabel JLabelDateDebut = new JLabel("Date début : ");
		JLabelDateDebut.setBounds(233, 125, 189, 14);
		contentPane.add(JLabelDateDebut);
		
		JSpinnerNBChoixMax.setBounds(432, 193, 43, 20);
		contentPane.add(JSpinnerNBChoixMax);
		
		JLabel JLabelNBChoixMax = new JLabel("Nombre de choix maximum :");
		JLabelNBChoixMax.setBounds(233, 196, 189, 14);
		contentPane.add(JLabelNBChoixMax);
		
		JLabel JLabelPromo = new JLabel("Promotion :");
		JLabelPromo.setBounds(233, 232, 189, 14);
		contentPane.add(JLabelPromo);
		
		JSpinnerPromotion.setBounds(432, 229, 43, 20);
		contentPane.add(JSpinnerPromotion);
		
		JComboBoxStatut.setModel(new DefaultComboBoxModel(new String[] {"En préparation", "Ouverte", "Fermée", "En traitement", "Validée", "Archivée"}));
		JComboBoxStatut.setBounds(432, 271, 135, 32);
		contentPane.add(JComboBoxStatut);
		
		JLabel JLabelStatut = new JLabel("Statut :");
		JLabelStatut.setBounds(233, 280, 189, 14);
		contentPane.add(JLabelStatut);
		
		JButton JButtonCreer = new JButton("Créer");
		JButtonCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CampagneDAO campagneDAO = new CampagneDAO();	
				Campagne campagne = campagneValide();
				if(campagne != null) {
					if(campagneDAO.add(campagne) > 0) {
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
	public Campagne campagneValide() {
	    
	    // 1. Date de début
	    Date dateDebutRecuperee = (Date) JSpinnerDateDebut.getValue();
	    if (dateDebutRecuperee == null) {
	        afficherErreur(contentPane, "Veuillez renseigner une date de début.");
	        return null;
	    }
	    LocalDateTime dateDebut = dateDebutRecuperee.toInstant()
	            .atZone(ZoneId.systemDefault())
	            .toLocalDateTime();
	    
	    // 2. Date de fin 
	    Date dateFinRecuperee = (Date) JSpinnerDateFin.getValue(); 
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
	    
	    // 4. Champs numériques
	    Object objChoixMax = JSpinnerNBChoixMax.getValue();
	    Object objPromotion = JSpinnerPromotion.getValue();
	    
	    if (objChoixMax == null || objPromotion == null) {
	        afficherErreur(contentPane, "Veuillez remplir tous les champs numériques.");
	        return null;
	    }
	    
	    int nbChoixMax = (int) objChoixMax;
	    int promotion = (int) objPromotion;
	    
	    if (nbChoixMax <= 0) {
	        afficherErreur(contentPane, "Le nombre de choix maximum doit être strictement supérieur à 0.");
	        return null;
	    }

	    // 5. Statut (Liste déroulante)
	    String statut = (String) JComboBoxStatut.getSelectedItem();
	    if (statut == null || statut.trim().isEmpty()) {
	        afficherErreur(contentPane, "Veuillez sélectionner un statut pour la campagne.");
	        return null;
	    }
	    
	    // 6. Tout est valide !
	    return new Campagne(dateDebut, dateFin, nbChoixMax, promotion, statut);
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
