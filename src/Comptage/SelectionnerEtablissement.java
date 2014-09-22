/*
 * Auteur : GRUMEL Jérémy
 * Date de création : 19/05/2014
 * Société : Next-One
 */
package Comptage;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
/**
 * Classe permettant de sélectionner un établissement
 * @author Jérémy GRUMEL
 */
public class SelectionnerEtablissement {
	private DefaultTableModel model = new DefaultTableModel();
	@SuppressWarnings({ "serial" })
	private JTable resultArea = new JTable(){
	    public boolean isCellEditable(int iRowIndex, int iColumnIndex)
	    {
	          return false;
	    }
	 };	
	private JPopupMenu popDoubleClick = new JPopupMenu();
	private JMenuItem selec = new JMenuItem(Propriete.getProperty("SelectionnerEtablissement", "selec"));
	/**
	 * Fonction permettant de sélectionner un établissement
	 * @param modele : Notre modèle d'affichage
	 * @param etabSelec : L'établissement sélectionné
	 */
	public SelectionnerEtablissement(DefaultTableModel modele, JLabel etabSelec){	
			    		final JPanel panelPrincipal = new JPanel(new GridLayout(2,1));
			    		JFrame frame = Frame(panelPrincipal);
			    		ComposantsTraitement(panelPrincipal, modele, etabSelec, frame);			    		
	}
	/**
	 * Fonction permettant l'affichage de notre fenêtre
	 * @param panelPrincipal : Notre panel Principal
	 * @return : Notre fenêtre
	 */
	private JFrame Frame(JPanel panelPrincipal) {
        JFrame frame = new JFrame();
        frame.setTitle(Propriete.getProperty("SelectionnerEtablissement", "frame"));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panelPrincipal);
        frame.setSize(450, 500);
        frame.setLocation(200, 200);
        frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		return frame;
	}
	/**
	 * Fonction permettant l'affichage et la gestion des composants
	 * @param panelPrincipal : Notre panel Principal
	 * @param modele : Notre modèle d'affichage
	 * @param etabSelec : L'établissement sélectionné
	 * @param frame : Notre fenêtre
	 */
	private void ComposantsTraitement(JPanel panelPrincipal, final DefaultTableModel modele, final JLabel etabSelec, final JFrame frame) {
		JLabel idEt = new JLabel(Propriete.getProperty("SelectionnerEtablissement", "idEt"));
		final JLabel idEtab = new JLabel("");
		JLabel nomEt = new JLabel(Propriete.getProperty("SelectionnerEtablissement", "nomEt"));
		final JLabel nomEtab = new JLabel("");
		JLabel espace = new JLabel(Propriete.getProperty("SelectionnerEtablissement", "espace"));
		JButton valider = new JButton(Propriete.getProperty("SelectionnerEtablissement", "valider"));
		JLabel espace2 = new JLabel(Propriete.getProperty("SelectionnerEtablissement", "espace"));
		JLabel nom = new JLabel(Propriete.getProperty("SelectionnerEtablissement", "nom"));
		final JTextField eta1 = new JTextField(8);
		JLabel num = new JLabel(Propriete.getProperty("SelectionnerEtablissement", "num"));
		final JTextField eta3 = new JTextField(8);
		JLabel adresse = new JLabel(Propriete.getProperty("SelectionnerEtablissement", "adresse"));
		final JTextField eta2 = new JTextField(8);
		JLabel codeP = new JLabel(Propriete.getProperty("SelectionnerEtablissement", "codeP"));
		final JTextField eta4 = new JTextField(8);
		JButton rechercher = new JButton(Propriete.getProperty("SelectionnerEtablissement", "rechercher"));	
		JButton afficherT = new JButton(Propriete.getProperty("SelectionnerEtablissement", "afficherT"));	
		final JPanel panelHaut = new JPanel(new FlowLayout());
		final JPanel panelBas = new JPanel(new FlowLayout());
		JScrollPane scrollingArea = new JScrollPane(resultArea);
	    panelBas.add(scrollingArea);
		panelHaut.add(idEt);
		panelHaut.add(idEtab);
		panelHaut.add(nomEt);
		panelHaut.add(nomEtab);
		panelHaut.add(espace);
		panelHaut.add(valider);
		panelHaut.add(espace2);
		panelHaut.add(nom);
		panelHaut.add(eta1);
		panelHaut.add(num);
		panelHaut.add(eta3);
		panelHaut.add(adresse);
		panelHaut.add(eta2);
		panelHaut.add(codeP);
		panelHaut.add(eta4);
		panelHaut.add(rechercher);
		panelHaut.add(afficherT);
		panelPrincipal.add(panelHaut);
		panelPrincipal.add(panelBas);
		resultArea.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		Modele.Etablissement(model, resultArea);
		ArrayList<String> etabs = RequeteBDD.SelectionnerLesEtablissements();
		new AffichageEtablissements(model, etabs);
		popDoubleClick.add(selec);
		resultArea.addMouseListener(new MouseAdapter(){    	
	        public void mouseClicked(MouseEvent e){        	
	        	if (e.getClickCount() == 2) {
	        		popDoubleClick.show(e.getComponent(), e.getX(), e.getY());        		
	        	}        	        	
	        }	        
	    });	
	    // Listener pour le bouton selec
	    selec.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {	
				idEtab.setText((String)resultArea.getValueAt(resultArea.getSelectedRow(), 0));
				nomEtab.setText((String)resultArea.getValueAt(resultArea.getSelectedRow(), 1));
			}
	    });
	    // Listener pour le bouton valider
	    valider.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {
				if(idEtab.getText() != "" && nomEtab.getText() != ""){
					etabSelec.setText(nomEtab.getText());
					frame.dispose();
				}
			}
	    });
	    // Listener pour le bouton rechercher
	    rechercher.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {	
				String nom, adresse, num, cp;	
				switch (eta1.getText()){
					case "":
						nom = "";
						break;
					default:
						nom = eta1.getText();
						break;			
				}
				if(eta2.getText() == null){					
					adresse = "";					
				}				
				else{					
					adresse = eta2.getText();					
				}
				if(eta3.getText() == null){					
					num = "";					
				}				
				else{					
					num = eta3.getText();					
				}
				if(eta4.getText() == null){					
					cp = "";					
				}				
				else{					
					cp = eta4.getText();					
				}
				ArrayList<String> resultat = RequeteBDD.rechercheEtablissements(nom, adresse, num, cp);			
			    for (int i = model.getRowCount() - 1; i > -1; i--) {			    	
			        model.removeRow(i);			        
			    }
				new AffichageEtablissements(model, resultat);
			}
	    });
	    // Listener pour le bouton afficherT
	    afficherT.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {	
			    for (int i = model.getRowCount() - 1; i > -1; i--) {			    	
			        model.removeRow(i);			        
			    }			    
			    ArrayList<String> tous = RequeteBDD.SelectionnerLesEtablissements();
				new AffichageEtablissements(model, tous);	
			}
	    });	
	}
}