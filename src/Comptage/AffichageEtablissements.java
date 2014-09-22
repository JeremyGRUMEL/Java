/*
 * Auteur : GRUMEL Jérémy
 * Date de création : 19/05/2014
 * Société : Next-One
 */
package Comptage;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 * Classe permettant l'affichage des établissements
 * @author Jérémy GRUMEL
 */
public class AffichageEtablissements {	
	/**
	 * Fonction permettant l'affichage des établissements
	 * @param modele : Notre modèle d'affichage pour les établissements
	 * @param compteuraAfficher : La liste à afficher
	 */
	public AffichageEtablissements(DefaultTableModel modele, ArrayList<String> compteuraAfficher) {
		if(!compteuraAfficher.isEmpty()){	
			for(int i = 0; i <= compteuraAfficher.size()-1; i++){	
				if(i % 5 == 0){		
					modele.addRow(new Object[]{compteuraAfficher.get(i),compteuraAfficher.get(i+1),
							compteuraAfficher.get(i+2),compteuraAfficher.get(i+3),compteuraAfficher.get(i+4)});
				}
			}	
		}	
	}
}