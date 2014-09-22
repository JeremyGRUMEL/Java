package Comptage;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
/**
 * Classe permettant la création de nouveaux compteurs
 * et leur ajoût dans la table Historique
 * @author Jérémy GRUMEL
 */
public class CreerCompteurs {
	private DefaultTableModel model = new DefaultTableModel();
	@SuppressWarnings({ "serial" })
	private JTable resultArea = new JTable(){
	    public boolean isCellEditable(int iRowIndex, int iColumnIndex)
	    {
	          return false;
	    }
	 };	
	private JPopupMenu popDoubleClick = new JPopupMenu();
	private JMenuItem selec = new JMenuItem(Propriete.getProperty("CreerCompteurs", "selec"));
	/**
	 * Fonction permettant la création de nouveaux compteurs 
	 * et leur ajoût dans la table historique
	 * @param modele : Notre modèle de tableau
	 */
	public CreerCompteurs(DefaultTableModel modele){	
		String id = JOptionPane.showInputDialog(null, Propriete.getProperty("CreerCompteurs", "id")+RequeteBDD.ObtenirID().get(0)
				, Propriete.getProperty("CreerCompteurs", "titreId"), JOptionPane.QUESTION_MESSAGE);
		if(id != null){		
			final String[] ids = id.split(Propriete.getProperty("CreerCompteurs", "split"));	
			for(int i = 0; i < ids.length; i++) {	
			    final ArrayList<String> resultat = RequeteBDD.Requete(Integer.parseInt(ids[i]));
			    if(resultat.isEmpty()){ 
			    	int option = JOptionPane.showConfirmDialog(null, Propriete.getProperty("CreerCompteurs", "option") + ids[i] 
			    			+ Propriete.getProperty("CreerCompteurs", "question")
			    			, Propriete.getProperty("CreerCompteurs", "attention")
			    			, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			    	if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){	
			    		final String idSelect = ids[i];
			    		final JPanel panelPrincipal = new JPanel(new GridLayout(2,1));
			    		JFrame frame = Frame(panelPrincipal, idSelect);
			    		ComposantsTraitement(panelPrincipal, idSelect, modele, frame);

			    	}			    	
			    	else{			    		
			    		JOptionPane.showMessageDialog(null, Propriete.getProperty("CreerCompteurs", "cpt") 
			    				+ ids[i] + Propriete.getProperty("CreerCompteurs", "nc"));		    		
			    	}			    	
				}				
				else{					
					JOptionPane.showMessageDialog(null, Propriete.getProperty("CreerCompteurs", "cptn") 
							+ ids[i] + Propriete.getProperty("CreerCompteurs", "ex"));		
				}			
			}			
		}   
	}
	/**
	 * Fonction permettant l'affichage de la fenêtre de création du compteur
	 * @param panelPrincipal : Notre panel Principal
	 * @param idSelect : l'ID du compteur qui va être créé
	 * @return
	 */
	private JFrame Frame(JPanel panelPrincipal, String idSelect) {
        JFrame frame = new JFrame();
        frame.setTitle(Propriete.getProperty("CreerCompteurs", "creation") + idSelect);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panelPrincipal);
        frame.setSize(480, 500);
        frame.setLocation(200, 200);
        frame.setVisible(true);
		frame.setLocationRelativeTo(null);	
		return frame;
	}
	/**
	 * Fonction permettant l'affichage des composants permettant la création d'un compteur
	 * @param panelPrincipal : Notre panel Principal
	 * @param idSelect : l'ID du compteur qui va être créé
	 * @param modele : Notre modèle de notre tableau
	 * @param frame : Notre fenêtre de création
	 */
	@SuppressWarnings("unchecked")
	private void ComposantsTraitement(JPanel panelPrincipal, final String idSelect, final DefaultTableModel modele, final JFrame frame) {
		ArrayList<String> choixt = RequeteBDD.SelectionnerTypes();
		String[] choix = new String[choixt.size()];
		Collections.sort(choixt);
		for(int j = 0; j < choixt.size(); j++){	
			choix[j] = choixt.get(j);		    			
		}		
		Collections.sort(choixt);
		ArrayList<String> choixl = RequeteBDD.SelectionnerLocalisations();
		String[] choix2 = new String[choixl.size()];	
		Collections.sort(choixl);
		for(int j = 0; j < choixl.size(); j++){			    			
			choix2[j] = choixl.get(j);		    			
		}		   	
		JLabel typ = new JLabel(Propriete.getProperty("CreerCompteurs", "typ"));
		@SuppressWarnings("rawtypes")
		final JComboBox choixT = new JComboBox(choix);
		JLabel loc = new JLabel(Propriete.getProperty("CreerCompteurs", "loc"));
		@SuppressWarnings("rawtypes")
		final JComboBox choixL = new JComboBox(choix2);
		JLabel idEt = new JLabel(Propriete.getProperty("CreerCompteurs", "idEt"));
		final JLabel idEtab = new JLabel("");
		JLabel nomEt = new JLabel(Propriete.getProperty("CreerCompteurs", "nomEt"));
		final JLabel nomEtab = new JLabel("");
		JLabel espace = new JLabel(Propriete.getProperty("CreerCompteurs", "espace"));
		JButton valider = new JButton(Propriete.getProperty("CreerCompteurs", "valider"));
		JLabel espace2 = new JLabel(Propriete.getProperty("CreerCompteurs", "espace2"));
		JLabel nom = new JLabel(Propriete.getProperty("CreerCompteurs", "nom"));
		final JTextField eta1 = new JTextField(8);
		JLabel num = new JLabel(Propriete.getProperty("CreerCompteurs", "num"));
		final JTextField eta3 = new JTextField(8);
		JLabel adresse = new JLabel(Propriete.getProperty("CreerCompteurs", "adresse"));
		final JTextField eta2 = new JTextField(8);
		JLabel codeP = new JLabel(Propriete.getProperty("CreerCompteurs", "codeP"));
		final JTextField eta4 = new JTextField(8);
		JButton rechercher = new JButton(Propriete.getProperty("CreerCompteurs", "rechercher"));	
		JButton afficherT = new JButton(Propriete.getProperty("CreerCompteurs", "afficherT"));	
		final JPanel panelHaut = new JPanel(new FlowLayout());
		final JPanel panelBas = new JPanel(new FlowLayout());
		JScrollPane scrollingArea = new JScrollPane(resultArea);
	    panelBas.add(scrollingArea);
		panelHaut.add(typ);
		panelHaut.add(choixT);
		panelHaut.add(loc);
		panelHaut.add(choixL);
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
					RequeteBDD.Creer(Integer.parseInt(idSelect), 0, (String) choixT.getSelectedItem()
							, (String) choixL.getSelectedItem()
							, (String)resultArea.getValueAt(resultArea.getSelectedRow(), 1));
			    	RequeteBDD.CreerHistorique(Integer.parseInt(idSelect), 0, (String) choixT.getSelectedItem()
							, (String) choixL.getSelectedItem()
							, (String)resultArea.getValueAt(resultArea.getSelectedRow(), 1), true);
			    	JOptionPane.showMessageDialog(null, Propriete.getProperty("CreerCompteurs", "cpt") 
			    			+ idSelect + Propriete.getProperty("CreerCompteurs", "cree"));
				    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
				        modele.removeRow(i);			        
				    }			    
				    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
					new AffichageCompteurs(modele, tous);
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