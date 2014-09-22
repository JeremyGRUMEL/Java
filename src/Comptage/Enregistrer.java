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
 * Classe permettant d'enregistrer des compteurs en fonction de plusieurs choix
 * @author Jérémy GRUMEL
 */
public class Enregistrer {
	/**
	 * Fonction permettant d'enregistrer le tableau des compteurs
	 * @param tableauResultats : le tableau des compteurs
	 */
	public Enregistrer(JTable tableauResultats){		
		try {
			JFileChooser sortie = new JFileChooser();
			int chemin = sortie.showSaveDialog(null);
			if(chemin == JFileChooser.APPROVE_OPTION){
				@SuppressWarnings("resource")
				PrintStream fichier_sortie = new PrintStream(new FileOutputStream(sortie.getSelectedFile().getAbsolutePath()
						+ Propriete.getProperty("Enregistrer", "xls")));
				fichier_sortie.print(Propriete.getProperty("Enregistrer", "print"));		
				for(int i=0; i< tableauResultats.getRowCount(); i++) {		
					for(int j=0; j < tableauResultats.getColumnCount(); j++) {		
						fichier_sortie.print(tableauResultats.getValueAt(i,j).toString() 
									+ Propriete.getProperty("Enregistrer", "tab"));		
					}	
					fichier_sortie.print(Propriete.getProperty("Enregistrer", "sl"));			
				}		
				JOptionPane.showMessageDialog(null, Propriete.getProperty("Enregistrer", "fichierc") 
				+ sortie.getSelectedFile().getAbsolutePath()
				+ Propriete.getProperty("Enregistrer", "xls"));		
			}	
			else{		
				JOptionPane.showMessageDialog(null, Propriete.getProperty("Enregistrer", "fichiernc"));		
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();	
		} 	
	}
	/**
	 * Fonction permettant d'enregistrer les compteurs sélectionnés
	 * @param resultat : le tableau des compteurs sélectionnés
	 */
	public Enregistrer(String[][] resultat) {	
		try {
			JFileChooser sortie = new JFileChooser();
			int chemin = sortie.showSaveDialog(null);
			if(chemin == JFileChooser.APPROVE_OPTION){
				@SuppressWarnings("resource")
				PrintStream fichier_sortie = new PrintStream(new FileOutputStream(sortie.getSelectedFile().getAbsolutePath()
						+ Propriete.getProperty("Enregistrer", "xls")));
				fichier_sortie.print(Propriete.getProperty("Enregistrer", "print"));		
				for(int i = 0; i < resultat.length; i++){	
					for(int j = 0; j < resultat[0].length; j++){	
						fichier_sortie.print(resultat[i][j].toString() + Propriete.getProperty("Enregistrer", "tab"));	
					}
					fichier_sortie.print(Propriete.getProperty("Enregistrer", "sl"));	
				}
				JOptionPane.showMessageDialog(null, Propriete.getProperty("Enregistrer", "fichierc") 
				+ sortie.getSelectedFile().getAbsolutePath() + Propriete.getProperty("Enregistrer", "xls"));	
			}	
			else{		
				JOptionPane.showMessageDialog(null, Propriete.getProperty("Enregistrer", "fichiernc"));	
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 	
	}
	/**
	 * Fonction permettant d'enregistrer des compteurs selon les choix rentrés au clavier
	 * @throws FileNotFoundException
	 */
	public Enregistrer() throws FileNotFoundException {
		String id = JOptionPane.showInputDialog(null, Propriete.getProperty("Enregistrer", "renseigner")
				, Propriete.getProperty("Enregistrer", "titre"), JOptionPane.QUESTION_MESSAGE);
		if(id != null){		
			JFileChooser sortie = new JFileChooser();
			int chemin = sortie.showSaveDialog(null);
			if(chemin == JFileChooser.APPROVE_OPTION){		
				String[] ids = id.split(Propriete.getProperty("Enregistrer", "split"));
				@SuppressWarnings("resource")
				PrintStream fichier_sortie = new PrintStream(new FileOutputStream(sortie.getSelectedFile().getAbsolutePath()
						+ Propriete.getProperty("Enregistrer", "xls")));
				fichier_sortie.print(Propriete.getProperty("Enregistrer", "print"));
				for(int i = 0; i < ids.length; i++) {	
					ArrayList<String> resultat = RequeteBDD.Requete(Integer.parseInt(ids[i]));
				    if(!resultat.isEmpty()){	
						for(int k = 0; k < resultat.size(); k++){
							fichier_sortie.print(resultat.get(k).toString() + Propriete.getProperty("Enregistrer", "tab"));			
						}
						fichier_sortie.print(Propriete.getProperty("Enregistrer", "sl"));				    	
				    } 	
			    	else{
			    		JOptionPane.showMessageDialog(null, Propriete.getProperty("Enregistrer", "cpt") 
			    				+ ids[i] + Propriete.getProperty("Enregistrer", "ne"));	
			    	}				    	
				}					
				JOptionPane.showMessageDialog(null, Propriete.getProperty("Enregistrer", "fichierc") 
				+ sortie.getSelectedFile().getAbsolutePath() + Propriete.getProperty("Enregistrer", "xls"));				    
			}	
			else{				
				JOptionPane.showMessageDialog(null, Propriete.getProperty("Enregistrer", "fichiernc"));				
			}
		}		
	}
}