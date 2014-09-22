/*
 * Auteur : GRUMEL J�r�my
 * Date de cr�ation : 19/05/2014
 * Soci�t� : Next-One
 */
package Comptage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 * Classe permettant la mise � jour des compteurs
 * @author J�r�my GRUMEL
 */
public class MAJ {	
	private File file;
	private String[] infos;	
	/**
	 * Fonction permettant la mise � jour des compteurs en fonction du fichier s�lectionn�
	 * @param fc : Le fichier s�lectionn�
	 */
	public MAJ(JFileChooser fc){		
		// R�cup�ration du fichier s�lectionn�
		int fermeture = fc.showOpenDialog(null);
        file=fc.getSelectedFile();   
	    if(fermeture != JFileChooser.CANCEL_OPTION){    	
    	    BufferedReader buff;  	    
    	    int option = JOptionPane.showConfirmDialog(null, Propriete.getProperty("MAJ", "option") 
    	    + file, Propriete.getProperty("MAJ", "attention"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);   	    
    	    if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION){ 	    	
				try {				
					buff = new BufferedReader(new FileReader(file));
		    	    String line;
		    	    TraitementDonn�es traitement = new TraitementDonn�es();		    	    
		    	    while ((line = buff.readLine()) != null) {		    	    	
			    	    infos = line.split(Propriete.getProperty("MAJ", "split"));			    	    
			    	    traitement.TraiterMAJ(infos);
		    	    }
		    	    buff.close();	
		    	    JOptionPane.showMessageDialog(null, Propriete.getProperty("MAJ", "donnees"));
				} 
				catch (FileNotFoundException e) {
					e.printStackTrace();					
				} 
				catch (IOException e) {
					e.printStackTrace();			
				} 
				catch (NumberFormatException e) {
					e.printStackTrace();	
				} 
				catch (SQLException e) {
					e.printStackTrace();			
				}					
			}	   	    
	    }		
	}
	/**
	 * Fonction permettant la mise � jour d'un compteur
	 * @param id : L'identifiant du compteur
	 * @throws SQLException
	 */
	public MAJ(String id) throws SQLException {
	    TraitementDonn�es traitement = new TraitementDonn�es();
		try {
			String nombre = JOptionPane.showInputDialog(null, Propriete.getProperty("MAJ", "indication") + id
					, Propriete.getProperty("MAJ", "maj"), JOptionPane.QUESTION_MESSAGE);		
			if(!nombre.isEmpty()){
				traitement.TraiterMAJ(id,nombre);
				JOptionPane.showMessageDialog(null, Propriete.getProperty("MAJ", "donnees"));
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}   
	}
}