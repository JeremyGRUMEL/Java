/*
 * Auteur : GRUMEL Jérémy
 * Date de création : 19/05/2014
 * Société : Next-One
 */
package Comptage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 * Classe permettant de supprimer les historiques
 * @author Jérémy GRUMEL
 */
public class SupprimerHistorique {
	/**
	 * Fonction permettant de supprimer les historiques du tableau affiché
	 * @param resultArea : Notre tableau
	 */
	public SupprimerHistorique(JTable resultArea) {	
		try{			
			JFileChooser sortie = new JFileChooser();
			int chemin = sortie.showSaveDialog(null);			
			if(chemin == JFileChooser.APPROVE_OPTION){				
				int option = JOptionPane.showConfirmDialog(null, Propriete.getProperty("SupprimerHistorique", "id"),
						Propriete.getProperty("SupprimerHistorique", "attention"), JOptionPane.YES_NO_CANCEL_OPTION
						, JOptionPane.QUESTION_MESSAGE);			    
		    	if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){	
					@SuppressWarnings("resource")
					PrintStream fichier_sortie = new PrintStream(new FileOutputStream(sortie.getSelectedFile().getAbsolutePath()
							+ Propriete.getProperty("SupprimerHistorique", "xls")));
					fichier_sortie.print(Propriete.getProperty("SupprimerHistorique", "print"));			
					for(int i=0; i< resultArea.getRowCount(); i++) {						
						for(int j=0; j < resultArea.getColumnCount(); j++) {							
							fichier_sortie.print(resultArea.getValueAt(i,j).toString()
									+Propriete.getProperty("SupprimerHistorique", "tab"));							
						}						
						fichier_sortie.print(Propriete.getProperty("SupprimerHistorique", "sl"));					
					}
					for(int i = 0; i < resultArea.getRowCount(); i++) {						
					    ArrayList<String> resultat = RequeteBDD.RequeteHistorique(Integer.parseInt((String)resultArea.getValueAt(i, 0)));		
					    if(!resultat.isEmpty()){
					    	RequeteBDD.SupprimerHistorique(Integer.parseInt(resultat.get(0)));		  	
						}						
					}					
					JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerHistorique", "creead") 
							+ sortie.getSelectedFile().getAbsolutePath()+Propriete.getProperty("SupprimerHistorique", "xls"));				
				}		    	
				else{					
					JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerHistorique", "nc"));					
				}
			}				
		}					
		catch (FileNotFoundException e) {
			e.printStackTrace();		
		} 
	}
	/**
	 * Fonction permettant de supprimer les historiques entrés au clavier
	 * @throws FileNotFoundException
	 */
	public SupprimerHistorique() throws FileNotFoundException {		
		try{			
			JFileChooser sortie = new JFileChooser();
			int chemin = sortie.showSaveDialog(null);			
			if(chemin == JFileChooser.APPROVE_OPTION){				
				String id = JOptionPane.showInputDialog(null, Propriete.getProperty("SupprimerHistorique", "choix")
						, Propriete.getProperty("SupprimerHistorique", "titre"), JOptionPane.QUESTION_MESSAGE);				
				if(id != null){					
					int option = JOptionPane.showConfirmDialog(null, Propriete.getProperty("SupprimerHistorique", "option")
							, Propriete.getProperty("SupprimerHistorique", "attention"), JOptionPane.YES_NO_CANCEL_OPTION
							, JOptionPane.QUESTION_MESSAGE);				    
			    	if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){			    		
			    		@SuppressWarnings("resource")
						PrintStream fichier_sortie = new PrintStream(new FileOutputStream(sortie.getSelectedFile().getAbsolutePath()+".xls"));
						fichier_sortie.print(Propriete.getProperty("SupprimerHistorique", "print"));					
						String[] ids = id.split(Propriete.getProperty("SupprimerHistorique", "split"));
						for(int i = 0; i < ids.length; i++) {	
							ArrayList<String> resultat = RequeteBDD.RequeteHistoriqueCompteur(Integer.parseInt(ids[i]));
						    if(!resultat.isEmpty()){
						    	for(int j = 0; j < resultat.size(); j++){
						    		if(j % 9 == 0){
								    	fichier_sortie.print(resultat.get(j) + Propriete.getProperty("SupprimerHistorique", "tab") 
								    			+ resultat.get(j+1) + Propriete.getProperty("SupprimerHistorique", "tab") 
								    			+ resultat.get(j+4) + Propriete.getProperty("SupprimerHistorique", "tab")  
								    			+ resultat.get(j+2) + Propriete.getProperty("SupprimerHistorique", "tab")  
								    			+ resultat.get(j+3) + Propriete.getProperty("SupprimerHistorique", "tab")  
								    			+ resultat.get(j+5) + Propriete.getProperty("SupprimerHistorique", "tab")  
								    			+ resultat.get(j+6) + Propriete.getProperty("SupprimerHistorique", "tab")  
								    			+ resultat.get(j+7) + Propriete.getProperty("SupprimerHistorique", "tab")
								    			+ resultat.get(j+8) + Propriete.getProperty("SupprimerHistorique", "tab")
								    			+ Propriete.getProperty("SupprimerHistorique", "sl"));	
						    		}
						    	}
						    	RequeteBDD.SupprimerHistoriqueCompteur(Integer.parseInt(ids[i]));
						    	
						    	JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerHistorique", "cpt")  
						    			+ ids[i] + Propriete.getProperty("SupprimerHistorique", "suppc")  
						    			+ sortie.getSelectedFile().getAbsolutePath()+Propriete.getProperty("SupprimerHistorique", "xls"));
						    }  						    
						    else{					    		
					    		JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerHistorique", "cpt")  
					    				+ ids[i] + Propriete.getProperty("SupprimerHistorique", "np") );				    		
					    	}			    		
						}		    
					}
			    	else{
					JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerHistorique", "ncns") );
			    	}
				}				
				else{					
					JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerHistorique", "ns") );					
				}				
			}			
			else{				
				JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerHistorique", "nc") );				
			}			
		}					
		catch (FileNotFoundException e) {
			e.printStackTrace();	
		} 		    
	}
	/**
	 * Fonction permettant de supprimer l'historique d'un compteur
	 * @param id : L'ID de notre compteur
	 * @param fichier_sortie : Notre fichier contenant le rapport
	 */
	public SupprimerHistorique(String id, PrintStream fichier_sortie) {					
		int option = JOptionPane.showConfirmDialog(null, Propriete.getProperty("SupprimerHistorique", "option2") 
				+ id + Propriete.getProperty("SupprimerHistorique", "question")
				, Propriete.getProperty("SupprimerHistorique", "attention")
				, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);    
    	if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){    						
				ArrayList<String> resultat = RequeteBDD.RequeteHistorique(Integer.parseInt(id));
			    if(!resultat.isEmpty()){
			    	fichier_sortie.print(resultat.get(0) + Propriete.getProperty("SupprimerHistorique", "tab") 
			    			+ resultat.get(1) + Propriete.getProperty("SupprimerHistorique", "tab") 
			    			+ resultat.get(4) + Propriete.getProperty("SupprimerHistorique", "tab")  
			    			+ resultat.get(2) + Propriete.getProperty("SupprimerHistorique", "tab")  
			    			+ resultat.get(3) + Propriete.getProperty("SupprimerHistorique", "tab")  
			    			+ resultat.get(5) + Propriete.getProperty("SupprimerHistorique", "tab")  
			    			+ resultat.get(6) + Propriete.getProperty("SupprimerHistorique", "tab")  
			    			+ resultat.get(7) + Propriete.getProperty("SupprimerHistorique", "tab")
			    			+ Propriete.getProperty("SupprimerHistorique", "sl"));	
			    	RequeteBDD.SupprimerHistorique(Integer.parseInt(id));			    	
			    }  				    
			    else{		    		
		    		JOptionPane.showMessageDialog(null, Propriete.getProperty("SupprimerHistorique", "cpt") 
		    				+ id + Propriete.getProperty("SupprimerHistorique", "np"));		    		
		    	}
		}
	}	
}