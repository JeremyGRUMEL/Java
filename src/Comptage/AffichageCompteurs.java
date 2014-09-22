/*
 * Auteur : GRUMEL J�r�my
 * Date de cr�ation : 19/05/2014
 * Soci�t� : Next-One
 */
package Comptage;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 * Classe permettant l'affichage des compteurs
 * @author J�r�my GRUMEL
 */
public class AffichageCompteurs {	
	/**
	 * Fonction permettant l'affichage des compteurs
	 * @param modele : Notre mod�le d'affichage pour les compteurs
	 * @param compteuraAfficher : La liste � afficher
	 */
	public AffichageCompteurs(DefaultTableModel modele, ArrayList<String> compteuraAfficher) {
		if(!compteuraAfficher.isEmpty()){		
			for(int i = 0; i <= compteuraAfficher.size()-1; i++){	
				if(i % 7 == 0){		
					modele.addRow(new Object[]{compteuraAfficher.get(i),compteuraAfficher.get(i+1),
							compteuraAfficher.get(i+2),compteuraAfficher.get(i+3),compteuraAfficher.get(i+4),
							compteuraAfficher.get(i+5),compteuraAfficher.get(i+6)});
				}
			}	
		}	
	}
}