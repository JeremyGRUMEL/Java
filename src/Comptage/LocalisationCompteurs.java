/*
 * Auteur : GRUMEL Jérémy
 * Date de création : 19/05/2014
 * Société : Next-One
 */
package Comptage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
/**
 * Classe permettant de modifier la localisation des compteurs
 * @author Jérémy GRUMEL
 */
public class LocalisationCompteurs {	
	@SuppressWarnings("unused")
	private String[] ids;
	@SuppressWarnings("unused")
	private String[] types;	
	@SuppressWarnings("rawtypes")
	/**
	 * Fonction permettant de modifier la localisation des compteurs en fonction des choix rentrés au clavier
	 * @param modele : Notre modèle d'affichage
	 */
	public LocalisationCompteurs(final DefaultTableModel modele){	
		String id = JOptionPane.showInputDialog(null, Propriete.getProperty("EtablissementCompteurs", "id")
				, Propriete.getProperty("EtablissementCompteurs", "titre"), JOptionPane.QUESTION_MESSAGE);		
		if(id != null){			
			final String[] ids = id.split(Propriete.getProperty("EtablissementCompteurs", "split"));
			for(int i = 0; i < ids.length; i++) {
				final int index = i;				
				ArrayList<String> resultat = RequeteBDD.Requete(Integer.parseInt(ids[i]));			  
			    if(!resultat.isEmpty()){			    	
			    	int option = JOptionPane.showConfirmDialog(null, Propriete.getProperty("EtablissementCompteurs", "option") 
			    	+ ids[i] + Propriete.getProperty("EtablissementCompteurs", "question")
			    	, Propriete.getProperty("EtablissementCompteurs", "attention"), JOptionPane.YES_NO_CANCEL_OPTION
			    	, JOptionPane.QUESTION_MESSAGE);				    
			    	if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){				    	
						final JFrame jf = new JFrame();
						JPanel panelPrincipal = new JPanel();
						jf.setTitle(Propriete.getProperty("EtablissementCompteurs", "jf") + ids[index]);
						jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						jf.add(panelPrincipal);
						jf.pack();
				        jf.setSize(460,75); 
				        jf.setVisible(true);
				        jf.setLocationRelativeTo(null);
			    		ArrayList<String> choixt = RequeteBDD.SelectionnerLocalisations();
			    		String[] choix = new String[choixt.size()];
			    		Collections.sort(choixt);
			    		for(int j = 0; j < choixt.size(); j++){		    			
			    			choix[j] = choixt.get(j);		    			
			    		}			    		
			    		@SuppressWarnings("unchecked")
						final JComboBox choixL = new JComboBox(choix);
			    		JButton valider = new JButton(Propriete.getProperty("EtablissementCompteurs", "valider"));
			    		panelPrincipal.add(choixL);
			    		panelPrincipal.add(valider);
			    	    // Listener pour le bouton valider
			    	    valider.addActionListener(new ActionListener() {		    	    	
			    			public void actionPerformed(ActionEvent arg0) {	
			    				RequeteBDD.ModifierLesLocalisations(Integer.parseInt(ids[index])
			    						, (String)choixL.getSelectedItem());
					    		RequeteBDD.ModifierLesLocalisationsHistorique(Integer.parseInt(ids[index])
					    				, (String)choixL.getSelectedItem());
						    	JOptionPane.showMessageDialog(null, Propriete.getProperty("EtablissementCompteurs", "cpt") 
						    			+ ids[index] + Propriete.getProperty("EtablissementCompteurs", "mod"));
							    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
							        modele.removeRow(i);			        
							    }			    
							    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
								new AffichageCompteurs(modele, tous);
						    	jf.dispose();
						    	jf.dispose();
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
	@SuppressWarnings("rawtypes")
	/**
	 * Fonction permettant de modifier la localisation d'un compteur
	 * @param id : L'identifiant du compteur
	 * @param modele : Notre modèle d'affichage
	 */
	public LocalisationCompteurs(final String id, final DefaultTableModel modele) {
		ArrayList<String> resultat = RequeteBDD.Requete(Integer.parseInt(id));		  
	    if(!resultat.isEmpty()){	    	
	    	int option = JOptionPane.showConfirmDialog(null, Propriete.getProperty("EtablissementCompteurs", "option") 
	    	+ id + Propriete.getProperty("EtablissementCompteurs", "question")
	    	, Propriete.getProperty("EtablissementCompteurs", "attention"), JOptionPane.YES_NO_CANCEL_OPTION
	    	, JOptionPane.QUESTION_MESSAGE);	    
	    	if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){	    	
				final JFrame jf = new JFrame();
				JPanel panelPrincipal = new JPanel();
				jf.setTitle(Propriete.getProperty("EtablissementCompteurs", "jf") + id);
				jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				jf.add(panelPrincipal);
				jf.pack();
		        jf.setSize(460,75); 
		        jf.setVisible(true);
		        jf.setLocationRelativeTo(null);
	    		ArrayList<String> choixt = RequeteBDD.SelectionnerLocalisations();
	    		String[] choix = new String[choixt.size()];
	    		Collections.sort(choixt);
	    		for(int j = 0; j < choixt.size(); j++){    			
	    			choix[j] = choixt.get(j);    			
	    		}    		
	    		@SuppressWarnings("unchecked")
				final JComboBox choixL = new JComboBox(choix);
	    		JButton valider = new JButton(Propriete.getProperty("EtablissementCompteurs", "valider"));
	    		panelPrincipal.add(choixL);
	    		panelPrincipal.add(valider);
	    	    // Listener pour le bouton valider
	    	    valider.addActionListener(new ActionListener() {    	    	
	    			public void actionPerformed(ActionEvent arg0) {	
	    				RequeteBDD.ModifierLesLocalisations(Integer.parseInt(id)
	    						, (String)choixL.getSelectedItem());
			    		RequeteBDD.ModifierLesLocalisationsHistorique(Integer.parseInt(id)
			    				, (String)choixL.getSelectedItem());
				    	JOptionPane.showMessageDialog(null, Propriete.getProperty("EtablissementCompteurs", "cpt") 
				    			+ id + Propriete.getProperty("EtablissementCompteurs", "mod"));
					    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
					        modele.removeRow(i);			        
					    }			    
					    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
						new AffichageCompteurs(modele, tous);
				    	jf.dispose();
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