/*
 * Auteur : GRUMEL Jérémy
 * Date de création : 19/05/2014
 * Société : Next-One
 */
package Comptage;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 * Classe permettant de supprimer des compteurs
 * @author Jérémy GRUMEL
 */
public class SupprimerCompteurs {
	/**
	 * Fonction permettant de supprimer des compteurs selon les choix entrés au clavier
	 */
	public SupprimerCompteurs(){		
		String id = JOptionPane.showInputDialog(null, Propriete.getProperty("SupprimerCompteurs", "id")
				, Propriete.getProperty("SupprimerCompteurs", "titre"), JOptionPane.QUESTION_MESSAGE);		
		if(id != null){			
			String[] ids = id.split(Propriete.getProperty("SupprimerCompteurs", "split"));			
			for(int i = 0; i < ids.length; i++) {				
			    ArrayList<String> resultat = RequeteBDD.Requete(Integer.parseInt(ids[i]));			    
			    if(!resultat.isEmpty()){
			    	int option = JOptionPane.showConfirmDialog(null, Propriete.getProperty("SupprimerCompteurs", "option") 
			    			+ ids[i] + Propriete.getProperty("SupprimerCompteurs", "question")
			    			, Propriete.getProperty("SupprimerCompteurs", "attention"), JOptionPane.YES_NO_CANCEL_OPTION
			    			, JOptionPane.QUESTION_MESSAGE);				    
			    	if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){			    	
				    	RequeteBDD.Supprimer(Integer.parseInt(ids[i]));
				    	RequeteBDD.CreerHistorique(Integer.parseInt(resultat.get(0)), Integer.parseInt(resultat.get(1)),
				    			resultat.get(4), resultat.get(5), resultat.get(6), false);
				    	JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerCompteurs", "cpt") 
				    			+ ids[i] + Propriete.getProperty("SupprimerCompteurs", "suppr"));			    	
				    }			    	
			    	else{		    		
			    		JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerCompteurs", "cpt") 
			    				+ ids[i] + Propriete.getProperty("SupprimerCompteurs", "nsuppr"));		    		
			    	}		    	
				}			
				else{				
					JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerCompteurs", "cpt") 
							+ ids[i] + Propriete.getProperty("SupprimerCompteurs", "np"));		
				}				
			}			
		}		
	}
	/**
	 * Fonction permettant de supprimer un compteur
	 * @param id : L'ID du compteur
	 */
	public SupprimerCompteurs(String id) {		
	    ArrayList<String> resultat = RequeteBDD.Requete(Integer.parseInt(id));	    
	    if(!resultat.isEmpty()){			 
	    	int option = JOptionPane.showConfirmDialog(null, Propriete.getProperty("SupprimerCompteurs", "id2") 
	    			+ Integer.parseInt(id) + Propriete.getProperty("SupprimerCompteurs", "question")
	    			, Propriete.getProperty("SupprimerCompteurs", "attention"), JOptionPane.YES_NO_CANCEL_OPTION
	    			, JOptionPane.QUESTION_MESSAGE);		    
	    	if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){		    	
		    	RequeteBDD.Supprimer(Integer.parseInt(id));
		    	RequeteBDD.CreerHistorique(Integer.parseInt(resultat.get(0)), Integer.parseInt(resultat.get(1)),
		    			resultat.get(4), resultat.get(5), resultat.get(6), false);
		    	JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerCompteurs", "cpt") 
		    			+ Integer.parseInt(id) + Propriete.getProperty("SupprimerCompteurs", "suppr"));	    	
		    }	    	
	    	else{	    		
	    		JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerCompteurs", "cpt") 
	    				+ Integer.parseInt(id) + Propriete.getProperty("SupprimerCompteurs", "nsuppr"));	    		
	    	}		
	    }	    
		else{		
			JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerCompteurs", "cpt") 
					+ Integer.parseInt(id) + Propriete.getProperty("SupprimerCompteurs", "np"));	
		}	    
	}
	/**
	 * Fonction permettant de supprimer les compteurs du tableau affiché
	 * @param resultArea : Notre tableau
	 */
	public SupprimerCompteurs(JTable resultArea) {		
    	int option = JOptionPane.showConfirmDialog(null, Propriete.getProperty("SupprimerCompteurs", "options")
    			, Propriete.getProperty("SupprimerCompteurs", "attention"), JOptionPane.YES_NO_CANCEL_OPTION
    			, JOptionPane.QUESTION_MESSAGE);    
    	if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){
			for(int i = 0; i < resultArea.getRowCount(); i++) {
			    ArrayList<String> resultat = RequeteBDD.Requete(Integer.parseInt((String)resultArea.getValueAt(i, 0)));
			    if(!resultat.isEmpty()){		
			    	RequeteBDD.Supprimer(Integer.parseInt(resultat.get(0)));
			    	RequeteBDD.CreerHistorique(Integer.parseInt(resultat.get(0)), Integer.parseInt(resultat.get(1)),
			    			resultat.get(4), resultat.get(5), resultat.get(6), false);  	
				}			
				else{				
					JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerCompteurs", "cpt") 
							+ resultat.get(0) + Propriete.getProperty("SupprimerCompteurs", "np"));		
				}			
			}		
    	}		
	}	
}