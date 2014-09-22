/*
 * Auteur : GRUMEL J�r�my
 * Date de cr�ation : 19/05/2014
 * Soci�t� : Next-One
 */
package Comptage;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 * Classe permettant l'affichage des �tablissements
 * @author J�r�my GRUMEL
 */
public class AffichageEtablissements {	
	/**
	 * Fonction permettant l'affichage des �tablissements
	 * @param modele : Notre mod�le d'affichage pour les �tablissements
	 * @param compteuraAfficher : La liste � afficher
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