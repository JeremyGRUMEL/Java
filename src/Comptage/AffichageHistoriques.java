/*
 * Auteur : GRUMEL J�r�my
 * Date de cr�ation : 19/05/2014
 * Soci�t� : Next-One
 */
package Comptage;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 * Classe permettant l'affichage de l'historique des compteurs
 * @author J�r�my GRUMEL
 */
public class AffichageHistoriques {	
	/**
	 * Fonction permettant l'affichage de l'historique des compteurs
	 * @param modele : le mod�le de notre affichage pour l'historique des compteurs
	 * @param historiqueaAfficher : L'historique � afficher
	 */
	public AffichageHistoriques(DefaultTableModel modele, ArrayList<String> historiqueaAfficher) {
		if(!historiqueaAfficher.isEmpty()){	
			for(int i = 0; i <= historiqueaAfficher.size()-1; i++){
				if(i % 9 == 0){	
					modele.addRow(new Object[]{historiqueaAfficher.get(i),historiqueaAfficher.get(i+1),
							historiqueaAfficher.get(i+2),historiqueaAfficher.get(i+3),historiqueaAfficher.get(i+4),
							historiqueaAfficher.get(i+5),historiqueaAfficher.get(i+6),historiqueaAfficher.get(i+7),
							historiqueaAfficher.get(i+8)});
				}	
			}	
		}	
	}
}