/*
 * Auteur : GRUMEL Jérémy
 * Date de création : 19/05/2014
 * Société : Next-One
 */
package Comptage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
/**
 * Classe permettant l'affichage satellite d'un établissement
 * @author Jérémy GRUMEL
 */
public class GMap {
	/**
	 * Fonction permettant l'affichage satellite d'un établissement
	 * @param selection : L'établissement sélectionné
	 */
	public GMap(String selection) {
		ArrayList<String> resultat = RequeteBDD.SelectionnerCoordonnees(selection);
		JGoogleMapEditorPan googleMap = new JGoogleMapEditorPan();
        try {
            googleMap.setApiKey(Propriete.getProperty("GMap", "key"));
            googleMap.showCoordinate(resultat.get(3), resultat.get(4),600, 600);
        } 
        catch (Exception ex) {
            Logger.getLogger(JGoogleMapEditorPan.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFrame frame = new JFrame();
        frame.setTitle(Propriete.getProperty("GMap", "etablissement") 
        		+ resultat.get(0) + Propriete.getProperty("GMap", "nom") 
        		+ resultat.get(1) + Propriete.getProperty("GMap", "adresse") + resultat.get(2));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(googleMap);
        frame.setSize(600, 600);
        frame.setLocation(200, 200);
        frame.setVisible(true);
		frame.setLocationRelativeTo(null);	
	}
}