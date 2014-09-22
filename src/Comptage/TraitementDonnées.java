/*
 * Auteur : GRUMEL J�r�my
 * Date de cr�ation : 19/05/2014
 * Soci�t� : Next-One
 */
package Comptage;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Classe permettant le traitement des donn�es de mise � jour
 * @author J�r�my GRUMEL
 */
public class TraitementDonn�es extends Thread{	
	private int cpt;
	/**
	 * Fonction permettant de traiter les mise � jour
	 * @param infos : Le fichier de mise � jour
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public String TraiterMAJ(final String[] infos) throws NumberFormatException, SQLException {		
		cpt = 0;	
		ArrayList<String> reponse = RequeteBDD.Requete(Integer.parseInt(infos[0]));		
		if(!reponse.isEmpty()){		  	    	
    		cpt = Integer.parseInt(infos[1]) + Integer.parseInt(reponse.get(1));
		    String maj = RequeteBDD.MAJ(Integer.parseInt(infos[0]), cpt, reponse.get(2), Integer.parseInt(infos[2]), 
		    		Integer.parseInt(infos[3]), Integer.parseInt(infos[4]));
		    RequeteBDD.CreerHistorique(Integer.parseInt(infos[0]), cpt, Integer.parseInt(infos[2]), 
		    		Integer.parseInt(infos[3]), Integer.parseInt(infos[4]), true);
		    return maj;   
	    }	    
	    else{	    		
	    	String cree = RequeteBDD.Creer(Integer.parseInt(infos[0]), Integer.parseInt(infos[1]), Integer.parseInt(infos[2]), 
		    		Integer.parseInt(infos[3]), Integer.parseInt(infos[4]));
		    RequeteBDD.CreerHistorique(Integer.parseInt(infos[0]), cpt, Integer.parseInt(infos[2]), 
		    		Integer.parseInt(infos[3]), Integer.parseInt(infos[4]), true);
		    return cree;		    
	    }			
	}		
	/**
	 * Constructeur de notre Classe Traitement
	 */
	public TraitementDonn�es() {
	}
	/**
	 * Fonction permettant de traiter les mise � jour d'un compteur
	 * @param id : L'ID du compteur
	 * @param nombre : Le nombre de passage
	 * @return
	 */
	public String TraiterMAJ(String id, String nombre) {
		cpt = 0;	
		ArrayList<String> reponse = RequeteBDD.RequeteMAJ(Integer.parseInt(id));		
		if(!reponse.isEmpty()){		  	    	
    		cpt = Integer.parseInt(nombre) + Integer.parseInt(reponse.get(1));
		    String maj = RequeteBDD.MAJ(Integer.parseInt(reponse.get(0)), cpt, reponse.get(2)
		    		, Integer.parseInt(reponse.get(4)), Integer.parseInt(reponse.get(5))
		    		, Integer.parseInt(reponse.get(6)));
		    RequeteBDD.CreerHistorique(Integer.parseInt(reponse.get(0)), cpt
		    		, Integer.parseInt(reponse.get(4)), Integer.parseInt(reponse.get(5))
		    		, Integer.parseInt(reponse.get(6)), true);
		    return maj;   
	    }
		else{
			return "";
		}
	}	
}
