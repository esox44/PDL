package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EtudiantDAO;
import model.Etudiant;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Connexion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField JTextFieldConnexionLogin;
	private JTextField JTextFieldConnexionMdp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connexion frame = new Connexion();
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
	public Connexion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 250, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel JPanelConnexion = new JPanel();
		JPanelConnexion.setBackground(new Color(248, 250, 252));
		JPanelConnexion.setBounds(101, 11, 234, 230);
		contentPane.add(JPanelConnexion);
		JPanelConnexion.setLayout(null);
		
		JLabel JLabelConnexionIcone = new JLabel("");
		JLabelConnexionIcone.setBounds(82, 11, 65, 53);
		JLabelConnexionIcone.setIcon(new ImageIcon("Image\\avatar.png"));
		JPanelConnexion.add(JLabelConnexionIcone);
		
		JLabel JLabelConnexionLogin = new JLabel("Identifiant :");
		JLabelConnexionLogin.setBounds(10, 92, 90, 14);
		JPanelConnexion.add(JLabelConnexionLogin);
		
		JTextFieldConnexionLogin = new JTextField();
		JTextFieldConnexionLogin.setBounds(110, 89, 114, 20);
		JPanelConnexion.add(JTextFieldConnexionLogin);
		JTextFieldConnexionLogin.setColumns(10);
		
		JLabel JLabelConnexionMdp = new JLabel("Mot de passe :");
		JLabelConnexionMdp.setBounds(10, 122, 90, 14);
		JPanelConnexion.add(JLabelConnexionMdp);
		
		JTextFieldConnexionMdp = new JTextField();
		JTextFieldConnexionMdp.setBounds(110, 119, 114, 20);
		JTextFieldConnexionMdp.setColumns(10);
		JPanelConnexion.add(JTextFieldConnexionMdp);
		
		JLabel JLabelConnexionMdpFaux = new JLabel("New label");
		JLabelConnexionMdpFaux.setDisplayedMnemonic('0');
		JLabelConnexionMdpFaux.setBounds(10, 205, 214, 14);
		JPanelConnexion.add(JLabelConnexionMdpFaux);
		JLabelConnexionMdpFaux.setVisible(false);
		
		JButton JButtonConnexionLogin = new JButton("Connexion");
		JButtonConnexionLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EtudiantDAO etudiantDAO = new EtudiantDAO();
				Etudiant etudiant = etudiantDAO.getEtudiantConnexion(
															JTextFieldConnexionLogin.getText(),
															JTextFieldConnexionMdp.getText());	
				if(etudiant!=null) {
					JLabelConnexionMdpFaux.setText("Connexion réussi");
				}else {
					JLabelConnexionMdpFaux.setText("Login ou mdp incorrect");
				}
				JLabelConnexionMdpFaux.setVisible(true);
			}
		});
		JButtonConnexionLogin.setBounds(56, 157, 121, 22);
		JPanelConnexion.add(JButtonConnexionLogin);
		
		

	}
}
