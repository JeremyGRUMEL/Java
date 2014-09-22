/*
 * Auteur : GRUMEL Jérémy
 * Date de création : 19/05/2014
 * Société : Next-One
 */
package Comptage;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * Classe permettant l'affichage de notre modèle
 * @author Jérémy GRUMEL
 */
public class Modele {
	/**
	 * Fonction permettant l'affichage des compteurs
	 * @param modele : Notre modèle f'affichage
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
	 * @param modele : Notre modèle d'affichage
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
	 * Fonction permettant l'affichage des établissements
	 * @param modele : Notre modèle d'affichage
	 * @param resultArea : Notre tableau
	 */
	public static void Etablissement(DefaultTableModel modele, JTable resultArea){		
		modele.addColumn("ID");
		modele.addColumn("Nom");
		modele.addColumn("Numéro");
		modele.addColumn("Adresse");
		modele.addColumn("Code Postal");	
		resultArea.setModel(modele);	
	}
}