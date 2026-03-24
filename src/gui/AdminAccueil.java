package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
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
import java.awt.event.ActionEvent;

public class AdminAccueil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAccueil frame = new AdminAccueil();
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
	public AdminAccueil() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel JLabelCrerCampagne = new JLabel("Créer une Campagne");
		JLabelCrerCampagne.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		JLabelCrerCampagne.setBounds(233, 36, 223, 41);
		contentPane.add(JLabelCrerCampagne);
		
		JButton JButtonCampagne = new JButton("Campagne");
		JButtonCampagne.setBounds(35, 45, 116, 32);
		contentPane.add(JButtonCampagne);
		
		JButton JButtonCampagne_1 = new JButton("Campagne");
		JButtonCampagne_1.setBounds(35, 99, 116, 32);
		contentPane.add(JButtonCampagne_1);
		
		JSpinner JSpinnerDateFin = new JSpinner();
		JSpinnerDateFin.setBounds(432, 171, 113, 20);
		contentPane.add(JSpinnerDateFin);
		JSpinnerDateFin.setModel(new SpinnerDateModel(new Date(1773961200000L), null, null, Calendar.DAY_OF_YEAR));
		
		JLabel JLabelDateFin = new JLabel("Date fin : ");
		JLabelDateFin.setBounds(233, 174, 50, 14);
		contentPane.add(JLabelDateFin);
		
		JSpinner JSpinnerDateDebut = new JSpinner();
		JSpinnerDateDebut.setBounds(432, 122, 113, 20);
		contentPane.add(JSpinnerDateDebut);
		JSpinnerDateDebut.setModel(new SpinnerDateModel(new Date(1773961200000L), null, null, Calendar.DAY_OF_YEAR));
		
		JLabel JLabelDateDebut = new JLabel("Date début : ");
		JLabelDateDebut.setBounds(233, 125, 66, 14);
		contentPane.add(JLabelDateDebut);
		
		JSpinner JSpinnerNBChoixMax = new JSpinner();
		JSpinnerNBChoixMax.setBounds(432, 217, 43, 20);
		contentPane.add(JSpinnerNBChoixMax);
		
		JLabel JLabelNBChoixMax = new JLabel("Nombre de choix maximum :");
		JLabelNBChoixMax.setBounds(233, 220, 151, 14);
		contentPane.add(JLabelNBChoixMax);
		
		JLabel JLabelPromo = new JLabel("Promotion :");
		JLabelPromo.setBounds(233, 274, 151, 14);
		contentPane.add(JLabelPromo);
		
		JSpinner JSpinnerPromotion = new JSpinner();
		JSpinnerPromotion.setBounds(432, 271, 43, 20);
		contentPane.add(JSpinnerPromotion);
		
		JButton JButtonCreer = new JButton("Créer");
		JButtonCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		JButtonCreer.setBounds(549, 341, 116, 32);
		contentPane.add(JButtonCreer);

	}
}
