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
import java.util.Collections;

import javax.swing.JButton;
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
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
/**
 * Classe permettant de modifier les établissements des compteurs en fonction de plusieurs choix
 * @author Jérémy GRUMEL
 */
public class EtablissementCompteurs {
	/**
	 * Fonction permettant de modifier les établissements selon les choix rentrés au clavier
	 * @param modele : Notre modèle pour l'affichage
	 */
	public EtablissementCompteurs(final DefaultTableModel modele) {
		String id = JOptionPane.showInputDialog(null, Propriete.getProperty("EtablissementCompteurs", "id"),
				Propriete.getProperty("EtablissementCompteurs", "titre"), JOptionPane.QUESTION_MESSAGE);		
		if(id != null){			
			final String[] ids = id.split(Propriete.getProperty("EtablissementCompteurs", "split"));
			for(int i = 0; i < ids.length; i++) {	
				final int index = i;
				ArrayList<String> resultat = RequeteBDD.Requete(Integer.parseInt(ids[i]));				  
			    if(!resultat.isEmpty()){		    	
			    	int option = JOptionPane.showConfirmDialog(null, Propriete.getProperty("EtablissementCompteurs", "option") 
			    			+ ids[i] + Propriete.getProperty("EtablissementCompteurs", "question")
			    			, Propriete.getProperty("EtablissementCompteurs", "attention")
			    			, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);			    
			    	if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){
			    		final DefaultTableModel mode = new DefaultTableModel();
			    		@SuppressWarnings({ "serial" })
			    		final JTable resultAre = new JTable(){
			    		    public boolean isCellEditable(int iRowIndex, int iColumnIndex)
			    		    {
			    		          return false;
			    		    }
			    		 };	
		    			final JPopupMenu popDoubleClick = new JPopupMenu();
		    			final JMenuItem selec = new JMenuItem(Propriete.getProperty("EtablissementCompteurs", "selec"));
						final JFrame jf = new JFrame();
						JPanel panelPrincipal = new JPanel(new GridLayout(2,1));
						JPanel panelBas = new JPanel(new FlowLayout());
						JPanel panelHaut = new JPanel(new FlowLayout());
						jf.setTitle(Propriete.getProperty("EtablissementCompteurs", "jf") + ids[i]);
						jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						jf.add(panelPrincipal);
						jf.pack();
				        jf.setSize(460,550); 
				        jf.setVisible(true);
				        jf.setLocationRelativeTo(null);
			    		ArrayList<String> choixt = RequeteBDD.SelectionnerTypes();
			    		String[] choix = new String[choixt.size()];
			    		Collections.sort(choixt);
			    		for(int j = 0; j < choixt.size(); j++){			    			
			    			choix[j] = choixt.get(j);		    			
			    		}		    		
			    		JLabel idEt = new JLabel(Propriete.getProperty("EtablissementCompteurs", "idEt"));
			    		final JLabel idEtab = new JLabel("");
			    		JLabel nomEt = new JLabel(Propriete.getProperty("EtablissementCompteurs", "nomEt"));
			    		final JLabel nomEtab = new JLabel("");
			    		JLabel espace = new JLabel(Propriete.getProperty("EtablissementCompteurs", "espace"));
			    		JButton valider = new JButton(Propriete.getProperty("CreerCompteurs", "valider"));
			    		JLabel espace2 = new JLabel(Propriete.getProperty("EtablissementCompteurs", "espace2"));
			    		JLabel nom = new JLabel(Propriete.getProperty("EtablissementCompteurs", "nom"));
			    		final JTextField eta1 = new JTextField(8);
			    		JLabel num = new JLabel(Propriete.getProperty("EtablissementCompteurs", "num"));
			    		final JTextField eta3 = new JTextField(8);
			    		JLabel adresse = new JLabel(Propriete.getProperty("EtablissementCompteurs", "adresse"));
			    		final JTextField eta2 = new JTextField(8);
			    		JLabel codeP = new JLabel(Propriete.getProperty("EtablissementCompteurs", "codeP"));
			    		final JTextField eta4 = new JTextField(8);
			    		JButton rechercher = new JButton(Propriete.getProperty("EtablissementCompteurs", "rechercher"));	
			    		JButton afficherT = new JButton(Propriete.getProperty("EtablissementCompteurs", "afficherT"));
			    		JScrollPane scrollingArea = new JScrollPane(resultAre);
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
			    		panelBas.add(scrollingArea);
			    		panelPrincipal.add(panelHaut);
			    		panelPrincipal.add(panelBas);
			    		resultAre.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			    		Modele.Etablissement(mode, resultAre);
			    		ArrayList<String> etabs = RequeteBDD.SelectionnerLesEtablissements();
			    		new AffichageEtablissements(mode, etabs);
			    		popDoubleClick.add(selec);
			    		resultAre.addMouseListener(new MouseAdapter(){    	
			    	        public void mouseClicked(MouseEvent e){        	
			    	        	if (e.getClickCount() == 2) {
			    	        		popDoubleClick.show(e.getComponent(), e.getX(), e.getY());        		
			    	        	}        	        	
			    	        }	        
			    	    });	
			    		// Listener pour le bouton selec
			    	    selec.addActionListener(new ActionListener() {	    	
			    			public void actionPerformed(ActionEvent arg0) {	
			    				idEtab.setText((String)resultAre.getValueAt(resultAre.getSelectedRow(), 0));
			    				nomEtab.setText((String)resultAre.getValueAt(resultAre.getSelectedRow(), 1));
			    			}
			    	    });
			    	    // Listener pour le bouton valider
			    	    valider.addActionListener(new ActionListener() {	    	
			    			public void actionPerformed(ActionEvent arg0) {
			    				if(idEtab.getText() != "" && nomEtab.getText() != ""){
				    				RequeteBDD.ModifierLesEtablissements(Integer.parseInt(ids[index]), nomEtab.getText());
						    		RequeteBDD.ModifierLesEtablissementsHistorique(Integer.parseInt(ids[index])
						    				, nomEtab.getText());
							    	JOptionPane.showMessageDialog(null, Propriete.getProperty("EtablissementCompteurs", "cpt") 
							    			+ ids[index] + Propriete.getProperty("EtablissementCompteurs", "mod"));
								    for (int i = modele.getRowCount() - 1; i > -1; i--) {
								        modele.removeRow(i);			        
								    }			    
								    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
									new AffichageCompteurs(modele, tous);
							    	jf.dispose();
			    				}
			    			}
			    	    });
			    	    // Listener pour le bouton rechercher
			    	    rechercher.addActionListener(new ActionListener() {	    	
			    			public void actionPerformed(ActionEvent arg0) {	
			    				String nom, adresse, num, cp;		
			    				if(eta1.getText().equals("")){					
			    					nom = "";					
			    				}				
			    				else{					
			    					nom = eta1.getText();					
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
			    			    for (int i = mode.getRowCount() - 1; i > -1; i--) {			    	
			    			        mode.removeRow(i);			        
			    			    }
			    				new AffichageEtablissements(mode, resultat);
			    			}
			    	    });
			    	    // Listener pour le bouton afficherT
			    	    afficherT.addActionListener(new ActionListener() {	    	
			    			public void actionPerformed(ActionEvent arg0) {	
			    			    for (int i = mode.getRowCount() - 1; i > -1; i--) {			    	
			    			        mode.removeRow(i);			        
			    			    }			    
			    			    ArrayList<String> tous = RequeteBDD.SelectionnerLesEtablissements();
			    				new AffichageEtablissements(mode, tous);	
			    			}
			    	    }); 	
				    }			    	
			    	else{			    		
			    		JOptionPane.showMessageDialog(null, Propriete.getProperty("EtablissementCompteurs", "cpt") 
			    				+ ids[i] + Propriete.getProperty("EtablissementCompteurs", "nmod"));			    		
			    	}
				}				
				else{					
					JOptionPane.showMessageDialog(null, Propriete.getProperty("EtablissementCompteurs", "cpt")
					+ ids[i] + Propriete.getProperty("EtablissementCompteurs", "np"));		
				}				
			}			
		}		
	}
	/**
	 * Fonction permettant de modifier l'établissement d'un compteur
	 * @param id : L'identifiant d'un compteur
	 * @param modele : Notre modèle d'affichage 
	 */
	public EtablissementCompteurs(final String id, final DefaultTableModel modele) {		
		ArrayList<String> resultat = RequeteBDD.Requete(Integer.parseInt(id));		  
	    if(!resultat.isEmpty()){	    	
	    	int option = JOptionPane.showConfirmDialog(null, Propriete.getProperty("EtablissementCompteurs", "option") 
	    			+ id + Propriete.getProperty("EtablissementCompteurs", "question")
	    			, Propriete.getProperty("EtablissementCompteurs", "attention")
	    			, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);		    
	    	if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){	    	
				final JFrame jf = new JFrame();
				JPanel panelPrincipal = new JPanel(new GridLayout(2,1));
				JPanel panelBas = new JPanel(new FlowLayout());
				JPanel panelHaut = new JPanel(new FlowLayout());
				final DefaultTableModel model = new DefaultTableModel();
				@SuppressWarnings({ "serial" })
				final JTable resultArea = new JTable(){
				    public boolean isCellEditable(int iRowIndex, int iColumnIndex)
				    {
				          return false;
				    }
				 };	
    			final JPopupMenu popDoubleClick = new JPopupMenu();
    			final JMenuItem selec = new JMenuItem(Propriete.getProperty("EtablissementCompteurs", "selec"));
				jf.setTitle(Propriete.getProperty("EtablissementCompteurs", "jf") + id);
				jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				jf.add(panelPrincipal);
				jf.pack();
		        jf.setSize(460,550); 
		        jf.setVisible(true);
		        jf.setLocationRelativeTo(null);
	    		ArrayList<String> choixt = RequeteBDD.SelectionnerTypes();
	    		String[] choix = new String[choixt.size()];
	    		Collections.sort(choixt);
	    		for(int j = 0; j < choixt.size(); j++){	    			
	    			choix[j] = choixt.get(j);	    			
	    		}	
	    		JLabel idEt = new JLabel(Propriete.getProperty("EtablissementCompteurs", "idEt"));
	    		final JLabel idEtab = new JLabel("");
	    		JLabel nomEt = new JLabel(Propriete.getProperty("EtablissementCompteurs", "nomEt"));
	    		final JLabel nomEtab = new JLabel("");
	    		JLabel espace = new JLabel(Propriete.getProperty("EtablissementCompteurs", "espace"));
	    		JButton valider = new JButton(Propriete.getProperty("CreerCompteurs", "valider"));
	    		JLabel espace2 = new JLabel(Propriete.getProperty("EtablissementCompteurs", "espace"));
	    		JLabel nom = new JLabel(Propriete.getProperty("EtablissementCompteurs", "nom"));
	    		final JTextField eta1 = new JTextField(8);
	    		JLabel num = new JLabel(Propriete.getProperty("EtablissementCompteurs", "num"));
	    		final JTextField eta3 = new JTextField(8);
	    		JLabel adresse = new JLabel(Propriete.getProperty("EtablissementCompteurs", "adresse"));
	    		final JTextField eta2 = new JTextField(8);
	    		JLabel codeP = new JLabel(Propriete.getProperty("EtablissementCompteurs", "codeP"));
	    		final JTextField eta4 = new JTextField(8);
	    		JButton rechercher = new JButton(Propriete.getProperty("EtablissementCompteurs", "rechercher"));	
	    		JButton afficherT = new JButton(Propriete.getProperty("EtablissementCompteurs", "afficherT"));
	    		JScrollPane scrollingArea = new JScrollPane(resultArea);
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
	    		panelBas.add(scrollingArea);
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
		    				RequeteBDD.ModifierLesEtablissements(Integer.parseInt(id), nomEtab.getText());
				    		RequeteBDD.ModifierLesEtablissementsHistorique(Integer.parseInt(id)
				    				, nomEtab.getText());
					    	JOptionPane.showMessageDialog(null, Propriete.getProperty("EtablissementCompteurs", "cpt") 
					    			+ id + Propriete.getProperty("EtablissementCompteurs", "mod"));
						    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
						        modele.removeRow(i);			        
						    }			    
						    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
							new AffichageCompteurs(modele, tous);
					    	jf.dispose();
	    				}
	    			}
	    	    });
	    	    // Listener pour le bouton rechercher
	    	    rechercher.addActionListener(new ActionListener() {	    	
	    			public void actionPerformed(ActionEvent arg0) {	
	    				String nom, adresse, num, cp;		
	    				if(eta1.getText().equals("")){					
	    					nom = "";					
	    				}				
	    				else{					
	    					nom = eta1.getText();					
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
	    	else{    		
	    		JOptionPane.showMessageDialog(null, Propriete.getProperty("EtablissementCompteurs", "cpt") 
	    				+ id + Propriete.getProperty("EtablissementCompteurs", "nmod"));   		
	    	}
		}	
		else{		
			JOptionPane.showMessageDialog(null, Propriete.getProperty("EtablissementCompteurs", "cpt") 
			+ id + Propriete.getProperty("EtablissementCompteurs", "np"));	
		}
	}
}