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
 * Classe permettant d'enregistrer des historiques en fonction de plusieurs choix
 * @author Jérémy GRUMEL
 */
public class EnregistrerHistorique {
	/**
	 * Fonction permettant d'enregistrer le tableau des historiques
	 * @param tableauResultats : le tableau des historiques
	 */
	public EnregistrerHistorique(JTable tableauResultats) {
		try {
			JFileChooser sortie = new JFileChooser();
			int chemin = sortie.showSaveDialog(null);			
			if(chemin == JFileChooser.APPROVE_OPTION){
				@SuppressWarnings("resource")
				PrintStream fichier_sortie = new PrintStream(new FileOutputStream(sortie.getSelectedFile().getAbsolutePath()
						+ Propriete.getProperty("EnregistrerHistorique", "xls")));
				fichier_sortie.print(Propriete.getProperty("EnregistrerHistorique", "print"));				
				for(int i=0; i< tableauResultats.getRowCount(); i++) {					
					for(int j=0; j < tableauResultats.getColumnCount(); j++) {						
						fichier_sortie.print(tableauResultats.getValueAt(i,j).toString() 
								+ Propriete.getProperty("EnregistrerHistorique", "tab"));						
					}					
					fichier_sortie.print(Propriete.getProperty("EnregistrerHistorique", "sl"));				
				}			
				JOptionPane.showMessageDialog(null, Propriete.getProperty("EnregistrerHistorique", "fichierc")
				+ sortie.getSelectedFile().getAbsolutePath() + Propriete.getProperty("EnregistrerHistorique", "xls"));				
			}		
			else{				
				JOptionPane.showMessageDialog(null, Propriete.getProperty("EnregistrerHistorique", "fichiernc"));				
			}		
		} 		
		catch (FileNotFoundException e) {
			e.printStackTrace();		
		} 	
	}
	/**
	 * Fonction permettant d'enregistrer des historiques selon les choix rentrés au clavier
	 * @throws FileNotFoundException
	 */
	public EnregistrerHistorique()throws FileNotFoundException {	
		String id = JOptionPane.showInputDialog(null, Propriete.getProperty("EnregistrerHistorique", "renseigner"),
				Propriete.getProperty("EnregistrerHistorique", "titre"), JOptionPane.QUESTION_MESSAGE);
		if(id != null){		
			JFileChooser sortie = new JFileChooser();
			int chemin = sortie.showSaveDialog(null);
			if(chemin == JFileChooser.APPROVE_OPTION){	
				String[] ids = id.split(Propriete.getProperty("EnregistrerHistorique", "split"));
				@SuppressWarnings("resource")
				PrintStream fichier_sortie = new PrintStream(new FileOutputStream(sortie.getSelectedFile().getAbsolutePath()
						+ Propriete.getProperty("EnregistrerHistorique", "xls")));
				fichier_sortie.print(Propriete.getProperty("EnregistrerHistorique", "print"));
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
				    }				    	
			    	else{		    		
			    		JOptionPane.showMessageDialog(null, Propriete.getProperty("EnregistrerHistorique", "cpt") 
			    				+ ids[i] + Propriete.getProperty("EnregistrerHistorique", "ne"));		    		
			    	}			    	
				}				
				JOptionPane.showMessageDialog(null, Propriete.getProperty("EnregistrerHistorique", "fichierc")
				+ sortie.getSelectedFile().getAbsolutePath() + Propriete.getProperty("EnregistrerHistorique", "xls"));				    
			}		
			else{		
				JOptionPane.showMessageDialog(null, Propriete.getProperty("EnregistrerHistorique", "fichiernc"));		
			}
		}	
	}
}