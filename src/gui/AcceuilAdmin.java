package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.SessionDAO;
import model.Session;

public class AcceuilAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable JTableAccueil;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SessionDAO sessionDAO = new SessionDAO();
                    
                    AcceuilAdmin frame = new AcceuilAdmin(sessionDAO.getListSelonIdCampagne(1));
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
    public AcceuilAdmin(ArrayList<Session> listSession) {
        // Configuration de base de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 599, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // --- 1. LE TITRE BIEN MARQUÉ ---
        JLabel lblTitre = new JLabel("LISTE DES SESSIONS");
        lblTitre.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitre.setBounds(10, 11, 565, 30);
        contentPane.add(lblTitre);

        // --- 2. LE SCROLLPANE ---
        JScrollPane JScrollPaneAccueil = new JScrollPane();
        JScrollPaneAccueil.setBounds(10, 50, 565, 252); 
        JScrollPaneAccueil.setBorder(BorderFactory.createEmptyBorder());
        JScrollPaneAccueil.getViewport().setBackground(contentPane.getBackground());
        contentPane.add(JScrollPaneAccueil);

        JTableAccueil = new JTable();
        JScrollPaneAccueil.setViewportView(JTableAccueil);

        // --- 3. MODÈLE DE DONNÉES (Rendu non modifiable) ---
        String[] colonnes = {"ID", "Date", "Début", "Fin", "Capacité"};
        
        // SURCHARGE ICI : On empêche l'édition de toutes les cellules
        DefaultTableModel modeleTableau = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Les cellules ne sont plus cliquables pour l'édition
            }
        };
        JTableAccueil.setModel(modeleTableau);

        // --- 4. PERSONNALISATION DU JTABLE ---
        JTableAccueil.setShowVerticalLines(false);
        JTableAccueil.setShowHorizontalLines(false);
        JTableAccueil.setIntercellSpacing(new Dimension(0, 10));
        JTableAccueil.setRowHeight(40); 
        JTableAccueil.setBackground(contentPane.getBackground());
        JTableAccueil.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     

        // --- 5. GESTION DU CLIC (Déclenchement de l'action) ---
        JTableAccueil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { 
                    int ligneSelectionnee = JTableAccueil.getSelectedRow();
                    
                    if (ligneSelectionnee != -1) {
                        String idAffiche = (String) JTableAccueil.getValueAt(ligneSelectionnee, 0);
                        int idSession = Integer.parseInt(idAffiche.replace("#", ""));
                        
                        // L'appel explicite à la méthode de la classe mère
                        AcceuilAdmin.this.executerActionSession(idSession); 
                    }
                }
            }
        });

        // --- 6. APPLICATION DE LA COULEUR UNIQUE SUR LES LIGNES ---
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.WHITE);
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                
                if (isSelected) {
                    c.setBackground(new Color(200, 220, 255));
                }
                return c;
            }
        };

        for (int i = 0; i < JTableAccueil.getColumnCount(); i++) {
            JTableAccueil.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
                
        // --- 7. REMPLISSAGE DES DONNÉES ---
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatHeure = DateTimeFormatter.ofPattern("HH:mm");
        
        for (Session session : listSession) {
            Object[] ligne = new Object[] {
                "#"+session.getId(),
                session.getDateHeureDebut().format(formatDate),
                session.getDateHeureDebut().format(formatHeure),
                session.getDateHeureFin().format(formatHeure),
                session.getCapacite()
            };
            modeleTableau.addRow(ligne);
        }   
    }

    private void executerActionSession(int idSession) {
        // Pour l'instant, cela affiche juste un message dans la console
        System.out.println("Clic détecté ! L'ID de la session est : " + idSession);
        
        // Tu pourras ajouter ici ton code, par exemple :
        // FenetreDetailSession detail = new FenetreDetailSession(idSession);
        // detail.setVisible(true);
    }

} // FIN DE LA CLASSE (il manquait cette accolade)