/*
 * Auteur : GRUMEL J�r�my
 * Date de cr�ation : 19/05/2014
 * Soci�t� : Next-One
 */
package Comptage;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * Classe permettant l'affichage de notre mod�le
 * @author J�r�my GRUMEL
 */
public class Modele {
	/**
	 * Fonction permettant l'affichage des compteurs
	 * @param modele : Notre mod�le f'affichage
	 * @param resultArea : Notre tableau
	 */
	public Modele(DefaultTableModel modele, JTable resultArea){		
		modele.addColumn(Propriete.getProperty("Modele", "id"));
		modele.addColumn(Propriete.getProperty("Modele", "cpt"));
		modele.addColumn(Propriete.getProperty("Modele", "dated"));
		modele.addColumn(Propriete.getProperty("Modele", "datemaj"));
		modele.addColumn(Propriete.getProperty("Modele", "type"));
		modele.addColumn(Propriete.getProperty("Modele", "localisation"));
		modele.addColumn(Propriete.getProperty("Modele", "etablissement"));		
		resultArea.setModel(modele);		
	}	
	/**
	 * Fonction permettant l'affichage des historique
	 * @param modele : Notre mod�le d'affichage
	 * @param resultArea : Notre tableau
	 */
	public static void Historique(DefaultTableModel modele, JTable resultArea){		
		modele.addColumn(Propriete.getProperty("Modele", "id"));
		modele.addColumn(Propriete.getProperty("Modele", "idcompteur"));
		modele.addColumn(Propriete.getProperty("Modele", "type"));
		modele.addColumn(Propriete.getProperty("Modele", "localisation"));
		modele.addColumn(Propriete.getProperty("Modele", "etablissement"));
		modele.addColumn(Propriete.getProperty("Modele", "cpt"));
		modele.addColumn(Propriete.getProperty("Modele", "dated"));
		modele.addColumn(Propriete.getProperty("Modele", "datemaj"));
		modele.addColumn(Propriete.getProperty("Modele", "actif"));		
		resultArea.setModel(modele);	
	}	
	/**
	 * Fonction permettant l'affichage des �tablissements
	 * @param modele : Notre mod�le d'affichage
	 * @param resultArea : Notre tableau
	 */
	public static void Etablissement(DefaultTableModel modele, JTable resultArea){		
		modele.addColumn("ID");
		modele.addColumn("Nom");
		modele.addColumn("Num�ro");
		modele.addColumn("Adresse");
		modele.addColumn("Code Postal");	
		resultArea.setModel(modele);	
	}
}