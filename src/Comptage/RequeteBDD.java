/*
 * Auteur : GRUMEL Jérémy
 * Date de création : 19/05/2014
 * Société : Next-One
 */
package Comptage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 * Classe permettant la connexion a notre BDD et les requetes sur notre BDD
 * @author GRUMEL Jérémy
 */
abstract public class RequeteBDD {	
	static String URL = Propriete.getProperty("RequeteBDD", "url");
	static String user = Propriete.getProperty("RequeteBDD", "root");
	static String password = "";	
	static Connection laConnection;
	static Statement transmission, transmissionSecondaire, transmissionTerciaire, transmissionQuaternaire;
	static ResultSet leResultat;
	static ArrayList<String> reponse = new ArrayList<String>();		
	static Date date = new Date();
	static DateFormat dateFormat = new SimpleDateFormat(Propriete.getProperty("RequeteBDD", "dateformat"));
	static String dateString = dateFormat.format(date);
	static String selectall = Propriete.getProperty("RequeteBDD", "selectall");
	static String selectmax= Propriete.getProperty("RequeteBDD", "selectmax");
	static String max = Propriete.getProperty("RequeteBDD", "max");
	static String systeme = Propriete.getProperty("RequeteBDD", "systeme");
	static String etablissement = Propriete.getProperty("RequeteBDD", "etablissement");
	static String type = Propriete.getProperty("RequeteBDD", "type");
	static String localisation = Propriete.getProperty("RequeteBDD", "localisation");
	static String historique = Propriete.getProperty("RequeteBDD", "historique");
	static String update = Propriete.getProperty("RequeteBDD", "update");
	static String delete = Propriete.getProperty("RequeteBDD", "delete");
	static String set = Propriete.getProperty("RequeteBDD", "set");
	static String where = Propriete.getProperty("RequeteBDD", "where");
	static String values = Propriete.getProperty("RequeteBDD", "values");
	static String and = Propriete.getProperty("RequeteBDD", "and");
	static String virgule = Propriete.getProperty("RequeteBDD", "virgule");
	static String po = Propriete.getProperty("RequeteBDD", "po");
	static String pf = Propriete.getProperty("RequeteBDD", "pf");
	static String insert = Propriete.getProperty("RequeteBDD", "insert");
	static String ap = Propriete.getProperty("RequeteBDD", "ap");
	static String egal = Propriete.getProperty("RequeteBDD", "egal");
	static String pourcent = Propriete.getProperty("RequeteBDD", "pourcent");
	static String supeg = Propriete.getProperty("RequeteBDD", "supeg");
	static String infeg = Propriete.getProperty("RequeteBDD", "infeg");
	static String like = Propriete.getProperty("RequeteBDD", "like");
	static String idcpt = Propriete.getProperty("RequeteBDD", "idcpt");
	static String cpt = Propriete.getProperty("RequeteBDD", "cpt");
	static String dated = Propriete.getProperty("RequeteBDD", "dated");
	static String datem = Propriete.getProperty("RequeteBDD", "datem");
	static String idt = Propriete.getProperty("RequeteBDD", "idt");
	static String idloc = Propriete.getProperty("RequeteBDD", "idloc");
	static String ide = Propriete.getProperty("RequeteBDD", "ide");
	static String idl = Propriete.getProperty("RequeteBDD", "idl");
	static String loc = Propriete.getProperty("RequeteBDD", "loc");
	static String nom = Propriete.getProperty("RequeteBDD", "noms");
	static String num = Propriete.getProperty("RequeteBDD", "nums");
	static String adresse = Propriete.getProperty("RequeteBDD", "adresses");
	static String cp = Propriete.getProperty("RequeteBDD", "cps");
	static String x = Propriete.getProperty("RequeteBDD", "xs");
	static String y = Propriete.getProperty("RequeteBDD", "ys");
	static String idh = Propriete.getProperty("RequeteBDD", "idh");
	static String actif = Propriete.getProperty("RequeteBDD", "actifs");
	static String cptmaj = Propriete.getProperty("RequeteBDD", "cptmaj");
	static String cptc = Propriete.getProperty("RequeteBDD", "cptc");
	static String pv = Propriete.getProperty("RequeteBDD", "pv");
	static String sl = Propriete.getProperty("RequeteBDD", "sl");
	static String oui= Propriete.getProperty("RequeteBDD", "oui");
	static String non = Propriete.getProperty("RequeteBDD", "non");	
	/**
	 * Fonction permettant la connexion à notre BDD
	 */
	public static void Connexion(){		
		try{			
			Class.forName(Propriete.getProperty("RequeteBDD", "driver"));
			laConnection = DriverManager.getConnection(URL, user, password);
			transmission = laConnection.createStatement();	
			transmissionSecondaire = laConnection.createStatement();	
			transmissionTerciaire = laConnection.createStatement();
			transmissionQuaternaire = laConnection.createStatement();		
		}
		catch(Exception e){			
			JOptionPane.showMessageDialog(null, Propriete.getProperty("RequeteBDD", "connexion"));
			e.printStackTrace();			
		}		
	}	
	/**
	 * Fonction permettant de recuperer tous les renseignements sur un compteur à partir d'un identifiant
	 * @param id : L'identifiant de notre compteur
	 * @return : Les renseignements de notre compteur
	 */
	public static ArrayList<String> Requete(int id){		
		reponse.removeAll(reponse);		
		try {				
			leResultat = transmission.executeQuery(selectall+systeme+where+idcpt+egal+id);			
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
		try {				
			while(leResultat.next()){				
				int id_cpt = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id"));
				int compteur = leResultat.getInt(Propriete.getProperty("RequeteBDD", "compteur"));
				String date_Dep = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_depart"));
				String date_MAJ = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_maj"));
				ResultSet typ = transmissionSecondaire.executeQuery(selectall+type+where+idt+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type")));
				ResultSet localisatio = transmissionTerciaire.executeQuery(selectall+localisation+where+idl+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_localisation")));
				ResultSet eta = transmissionQuaternaire.executeQuery(selectall+etablissement+where+ide+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta")));
				reponse.add(Integer.toString(id_cpt));	
				reponse.add(Integer.toString(compteur));
				reponse.add(date_Dep);
				reponse.add(date_MAJ);				
				while(typ.next()){					
					reponse.add(typ.getString(Propriete.getProperty("RequeteBDD", "type")));					
				}				
				while(localisatio.next()){					
					reponse.add(localisatio.getString(Propriete.getProperty("RequeteBDD", "type_loc")));				
				}				
				if(eta.next()){					
					reponse.add(eta.getString(Propriete.getProperty("RequeteBDD", "nom")));					
				}				
				else{					
					reponse.add("");					
				}				
			}				
		} 
		catch (SQLException e) {		
			e.printStackTrace();			
		}
		return reponse;		
	}
	/**
	 * Fonction permettant de recuperer tous les renseignements sur un compteur à partir d'un identifiant
	 * @param id : L'identifiant de notre compteur
	 * @return : Les renseignements de notre compteur
	 */
	public static ArrayList<String> RequeteMAJ(int id){		
		reponse.removeAll(reponse);		
		try {				
			leResultat = transmission.executeQuery(selectall+systeme+where+idcpt+egal+id);			
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
		try {				
			while(leResultat.next()){				
				int id_cpt = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id"));
				int compteur = leResultat.getInt(Propriete.getProperty("RequeteBDD", "compteur"));
				String date_Dep = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_depart"));
				String date_MAJ = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_maj"));
				int typ = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type"));
				int localisatio = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_localisation"));
				int eta = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta"));
				reponse.add(Integer.toString(id_cpt));	
				reponse.add(Integer.toString(compteur));
				reponse.add(date_Dep);
				reponse.add(date_MAJ);
				reponse.add(Integer.toString(typ));	
				reponse.add(Integer.toString(localisatio));
				reponse.add(Integer.toString(eta));	
			}				
		} 
		catch (SQLException e) {		
			e.printStackTrace();			
		}
		return reponse;		
	}
	/**
	 * Fonction permettant de créer un compteur dans notre BDD
	 * @param id
	 * @param compt
	 * @param typ
	 * @param locali
	 * @param etabl
	 * @return
	 */
	public static String Creer(int id, int compt, int typ, int locali, int etabl){	
		try {			
			transmission.executeUpdate(insert+systeme+po+idcpt+virgule+cpt+virgule+dated+virgule+datem+virgule
					+idt+virgule+idloc+virgule+ide+pf+values+po+id+virgule+compt+virgule+ap+dateString+ap+virgule+ap+dateString
					+ap+virgule+typ+virgule+locali+virgule+etabl+pf);			
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
		return cptc+id+pv+compt+pv+dateString+pv+dateString+pv+typ+pv+locali+pv+ etabl+pv+sl;		
	}	
	/**
	 * Fonction permettant la mise à jour d'un compteur
	 * @param id
	 * @param compt
	 * @param date_depart
	 * @param typ
	 * @param localisatio
	 * @param etablissemen
	 * @return
	 */
	public static String MAJ(int id, int compt, String date_depart, int typ, int localisatio, int etablissemen){		
		try {			
			transmission.executeUpdate(update+systeme+set+idcpt+egal+id+virgule+cpt+egal+compt+virgule+dated+egal+ap
					+date_depart+ap+virgule+datem+egal+ap+dateString+ap+virgule+idt+egal+typ+virgule+idloc+egal
					+localisatio+virgule+ide+egal+etablissemen+where+idcpt+egal+id);		
		} 
		catch (SQLException e) {		
			e.printStackTrace();			
		}
		return cptmaj+id+pv+compt+pv+date_depart+pv+dateString+pv+typ+pv+localisatio+pv+etablissemen+pv+sl;		
	}
	/**
	 * Fonction permettant la modification du type d'un compteur
	 * @param saisie
	 * @param saisieType
	 */
	public static void ModificationType(String saisie, String saisieType) {	
		try {		
			transmission.executeUpdate(update+systeme+set+idt+egal+Integer.parseInt(saisieType)+where
					+idcpt+egal+Integer.parseInt(saisie));		
		} 
		catch (NumberFormatException e) {			
			e.printStackTrace();
		} 
		catch (SQLException e) {						
			e.printStackTrace();
		}		
	}
	/**
	 * Fonction permettant la modification de la localisation d'un compteur
	 * @param saisie
	 * @param saisieLoc
	 */
	public static void ModificationLocalisation(String saisie, String saisieLoc) {		
		try {			
			transmission.executeUpdate(update+systeme+set+idloc+egal+Integer.parseInt(saisieLoc)+where
					+idcpt+egal+Integer.parseInt(saisie));	
		} 
		catch (NumberFormatException e) {		
			e.printStackTrace();		
		} 
		catch (SQLException e) {		
			e.printStackTrace();
		}		
	}
	/**
	 * Fonction permettant de sélectionner tous les compteurs
	 * @return
	 */
	public static ArrayList<String> SelectionnerTous() {		
		reponse.removeAll(reponse);		
		try {			
			leResultat = transmission.executeQuery(selectall+systeme);			
		} 
		catch (SQLException e) {
			e.printStackTrace();		
		}	
		try {			
			while(leResultat.next()){					
				int id_p = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id"));
				int compteur = leResultat.getInt(Propriete.getProperty("RequeteBDD", "compteur"));
				String date_Dep = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_depart"));
				String date_MAJ = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_maj"));
				ResultSet type_cpt = transmissionSecondaire.executeQuery(selectall+type+where+idt+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type")));
				ResultSet localisation_cpt = transmissionTerciaire.executeQuery(selectall+localisation
						+where+idl+egal+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_localisation")));
				ResultSet eta = transmissionQuaternaire.executeQuery(selectall+etablissement+where+ide
						+egal+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta")));
				reponse.add(Integer.toString(id_p));	
				reponse.add(Integer.toString(compteur));
				reponse.add(date_Dep);
				reponse.add(date_MAJ);				
				if(type_cpt.next()){					
					reponse.add(type_cpt.getString(Propriete.getProperty("RequeteBDD", "type")));					
				}				
				else{				
					reponse.add("");					
				}				
				if(localisation_cpt.next()){					
					reponse.add(localisation_cpt.getString(Propriete.getProperty("RequeteBDD", "type_loc")));					
				}				
				else{					
					reponse.add("");					
				}				
				if(eta.next()){					
					reponse.add(eta.getString(Propriete.getProperty("RequeteBDD", "nom")));					
				}				
				else{					
					reponse.add("");					
				}				
			}				
		} 
		catch (SQLException e) {	
			e.printStackTrace();			
		}		
		return reponse;		
	}	
	/**
	 * Fonction permettant de supprimer un compteur
	 * @param id
	 */
	public static void Supprimer(int id) {		
		try {			
			transmission.execute(delete+systeme+where+idcpt+egal+id);		
		} 
		catch (NumberFormatException e) {			
			e.printStackTrace();		
		} 
		catch (SQLException e) {					
			e.printStackTrace();
		}		
	}
	/**
	 * Fonction permettant l'affichage des compteurs
	 * @param id
	 * @param cpt1
	 * @param cpt2
	 * @param date1
	 * @param date2
	 * @param type_t
	 * @param loc_t
	 * @param et_e
	 * @return
	 */
	public static ArrayList<String> affichage(int id, int cpt1, int cpt2, String date1
			, String date2, String type_t, String loc_t, String et_e) {
		reponse.removeAll(reponse);		
		String debut = selectall+systeme+where;
		String debutType = selectall+type+where;
		String debutLoc = selectall+localisation+where;
		String debutEta = selectall+etablissement+where;
		String ids = idcpt+like+ap+id+pourcent+ap;
		String cpts = cpt+supeg+cpt1+and+cpt+infeg+cpt2;
		String dates = dated+supeg+ap+date1+ap+and+datem+infeg+ap+date2+ap;
		String typeb = type+egal+ap+type_t+ap;
		String typ = idt+egal;
		String locs = loc+egal+ap+loc_t+ap;
		String idlo = idloc+egal;
		String ets = nom+egal+ap+et_e+ap;
		String et = ide+egal;
		String resultat = "";
		int cpt = 0;	
		try {			
			if(id != -1){				
				resultat = resultat+ids;
				cpt = 1;				
			}			
			if(cpt1 != -1 && cpt2 != -1){				
				if(cpt != 0){					
					resultat = resultat+and+cpts;					
				}				
				else{					
					resultat = resultat+cpts;
					cpt = 1;					
				}					
			}
			if(date1 != "" && date2 != ""){				
				if(cpt != 0){					
					resultat = resultat+and+dates;					
				}				
				else{					
					resultat = resultat+dates;
					cpt = 1;				
				}					
			}
			if(type_t != ""){
				leResultat = transmissionSecondaire.executeQuery(debutType+typeb);
				if(leResultat.next()){					
					if(cpt != 0){					
						resultat = resultat+and+typ+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type"));						
					}					
					else{						
						resultat = resultat+typ+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type"));
						cpt = 1;					
					}
				}	
			}
			if(loc_t != ""){
				leResultat = transmissionTerciaire.executeQuery(debutLoc+locs);
				if(leResultat.next()){
					if(cpt != 0){						
						resultat = resultat+and+idlo+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_loc"));				
					}					
					else{					
						resultat = resultat+idlo+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_loc"));
						cpt = 1;					
					}
				}
			}			
			if(et_e != ""){
				leResultat = transmissionQuaternaire.executeQuery(debutEta+ets);
				if(leResultat.next()){
					if(cpt != 0){						
						resultat = resultat+and+et+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta"));						
					}					
					else{						
						resultat = resultat+et+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta"));
						cpt = 1;						
					}
				}
			}		
			if(!resultat.equals("")){				
				leResultat = transmission.executeQuery(debut+resultat);				
			}						
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
		try {				
			while(leResultat.next()){					
				int id_p = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id"));
				int compteur = leResultat.getInt(Propriete.getProperty("RequeteBDD", "compteur"));
				String date_Dep = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_depart"));
				String date_MAJ = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_maj"));
				ResultSet type_cpt = transmissionSecondaire.executeQuery(selectall+type+where+idt+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type")));
				ResultSet localisation_cpt = transmissionTerciaire.executeQuery(selectall+localisation+where+idl
						+egal+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_localisation")));
				ResultSet eta = transmissionQuaternaire.executeQuery(selectall+etablissement+where+ide+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta")));
				reponse.add(Integer.toString(id_p));	
				reponse.add(Integer.toString(compteur));
				reponse.add(date_Dep);
				reponse.add(date_MAJ);			
				if(type_cpt.next()){					
					reponse.add(type_cpt.getString(Propriete.getProperty("RequeteBDD", "type")));					
				}				
				else{					
					reponse.add("");					
				}				
				if(localisation_cpt.next()){					
					reponse.add(localisation_cpt.getString(Propriete.getProperty("RequeteBDD", "type_loc")));					
				}				
				else{					
					reponse.add("");					
				}				
				if(eta.next()){					
					reponse.add(eta.getString(Propriete.getProperty("RequeteBDD", "nom")));					
				}				
				else{					
					reponse.add("");					
				}				
			}	
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
		return reponse;		
	}
	/**
	 * Fonction permettant de modifier les types des compteurs
	 * @param id
	 * @param typ
	 */
	public static void ModifierLesTypes(int id, String typ) {		
		try {			
			leResultat = transmission.executeQuery(selectall+type+where+type+egal+ap+typ+ap);			
		} catch (SQLException e) {
			e.printStackTrace();		
		}		
		try {				
			while(leResultat.next()){		
				transmissionSecondaire.executeUpdate(update+systeme+set+idt+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type"))+where+idcpt+egal+id);				
			}				
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
	}
	/**
	 * Fonction permettant de modifier les localisations des compteurs
	 * @param id
	 * @param localisatio
	 */
	public static void ModifierLesLocalisations(int id, String localisatio) {
		try {		
			leResultat = transmission.executeQuery(selectall+localisation+where+loc+egal+ap+localisatio+ap);		
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
		try {				
			while(leResultat.next()){		
				transmissionSecondaire.executeUpdate(update+systeme+set+idloc+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_loc"))+where+idcpt+egal+id);				
			}				
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
	}
	/**
	 * Fonction permettant de modifier les établissements des compteurs
	 * @param id
	 * @param etab
	 */
	public static void ModifierLesEtablissements(int id, String etab) {
		try {		
			leResultat = transmission.executeQuery(selectall+etablissement+where+nom+egal+ap+etab+ap);			
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
		try {				
			while(leResultat.next()){	
					transmissionSecondaire.executeUpdate(update+systeme+set+ide+egal
							+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta"))+where+idcpt+egal+id);				
			}				
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}	
	}	
	/**
	 * Fonction permettant de sélectionner les types
	 * @return
	 */
	public static ArrayList<String> SelectionnerTypes() {		
		reponse.removeAll(reponse);		
		try {				
			leResultat = transmission.executeQuery(selectall+type);			
		} 
		catch (SQLException e) {
			e.printStackTrace();	
		}	
		try {				
			while(leResultat.next()){					
				String typ = leResultat.getString(Propriete.getProperty("RequeteBDD", "type"));
				reponse.add(typ);				
			}				
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
		return reponse;			
	}
	/**
	 * Fonction permettant de sélectionner les localisations
	 * @return
	 */
	public static ArrayList<String> SelectionnerLocalisations() {		
		reponse.removeAll(reponse);	
		try {				
			leResultat = transmission.executeQuery(selectall+localisation);		
		} 
		catch (SQLException e) {	
			e.printStackTrace();		
		}	
		try {				
			while(leResultat.next()){					
				String typ = leResultat.getString(Propriete.getProperty("RequeteBDD", "type_loc"));	
				reponse.add(typ);				
			}				
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
		return reponse;		
	}
	/**
	 * Fonction permettant de sélectionnner les établissements
	 * @return
	 */
	public static ArrayList<String> SelectionnerEtablissements() {	
		reponse.removeAll(reponse);		
		try {				
			leResultat = transmission.executeQuery(selectall+etablissement);			
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
		try {				
			while(leResultat.next()){					
				String typ = leResultat.getString(Propriete.getProperty("RequeteBDD", "nom"));	
				reponse.add(typ);				
			}				
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
		return reponse;		
	}
	/**
	 * Fonction permettant de créer un compteur
	 * @param id
	 * @param compteur
	 * @param typ
	 * @param localisatio
	 * @param etablissemen
	 */
	public static void Creer(int id, int compteur, String typ,
			String localisatio, String etablissemen) {		
		try {			
			transmission.executeUpdate(insert+systeme+po+idcpt+virgule+cpt+virgule+dated+virgule+datem+pf+values+po+id
					+virgule+compteur+virgule+ap+dateString+ap+virgule+ap+dateString+ap+pf);			
		} 
		catch (SQLException e) {		
			e.printStackTrace();		
		}	
		RequeteBDD.ModifierLesTypes(id, typ);
		RequeteBDD.ModifierLesLocalisations(id, localisatio);
		RequeteBDD.ModifierLesEtablissements(id, etablissemen);		
	}
	/**
	 * Fonction permettant de sélectionner les historiques
	 * @return
	 */
	public static ArrayList<String> SelectionnerHistorique() {
		reponse.removeAll(reponse);	
		try {				
			leResultat = transmission.executeQuery(selectall+historique);			
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
		try {				
			while(leResultat.next()){				
				int id_hist = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_hist"));
				int id = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id"));
				ResultSet type_cpt = transmissionSecondaire.executeQuery(selectall+type+where+idt+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type")));
				ResultSet localisation_cpt = transmissionTerciaire.executeQuery(selectall+localisation
						+where+idl+egal+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_localisation")));
				ResultSet eta = transmissionQuaternaire.executeQuery(selectall+etablissement+where
						+ide+egal+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta")));
				int compteur = leResultat.getInt(Propriete.getProperty("RequeteBDD", "compteur"));
				String date_Dep = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_depart"));
				String date_MAJ = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_maj"));
				boolean acti = leResultat.getBoolean(Propriete.getProperty("RequeteBDD", "actif"));
				reponse.add(Integer.toString(id_hist));	
				reponse.add(Integer.toString(id));
				if(type_cpt.next()){					
					reponse.add(type_cpt.getString(Propriete.getProperty("RequeteBDD", "type")));					
				}				
				else{					
					reponse.add("");					
				}				
				if(localisation_cpt.next()){					
					reponse.add(localisation_cpt.getString(Propriete.getProperty("RequeteBDD", "type_loc")));				
				}				
				else{					
					reponse.add("");					
				}				
				if(eta.next()){					
					reponse.add(eta.getString(Propriete.getProperty("RequeteBDD", "nom")));					
				}				
				else{					
					reponse.add("");				
				}
				reponse.add(Integer.toString(compteur));
				reponse.add(date_Dep);
				reponse.add(date_MAJ);
				if(acti){
					reponse.add(oui);
				}
				else{
					reponse.add(non);
				}
			}				
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
		return reponse;		
	}
	/**
	 * Fonction permettant de créer un historique
	 * @param id
	 * @param compteur
	 * @param typ
	 * @param localisatio
	 * @param etablissemen
	 * @param acti
	 */
	public static void CreerHistorique(int id, int compteur, String typ,
			String localisatio, String etablissemen, boolean acti) {		
		try {			
			transmission.executeUpdate(insert+historique+po+idcpt+virgule+cpt+virgule+dated+virgule+datem+virgule+actif+pf+values+po+id
					+virgule+compteur+virgule+ap+dateString+ap+virgule+ap+dateString+ap+virgule+acti+pf);			
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
		RequeteBDD.ModifierLesTypesHistorique(id, typ, dateString, dateString, compteur);
		RequeteBDD.ModifierLesLocalisationsHistorique(id, localisatio, dateString, dateString, compteur);
		RequeteBDD.ModifierLesEtablissementsHistorique(id, etablissemen, dateString, dateString, compteur);		
	}
	/**
	 * Fonction permettant de modifier les établissements des historiques
	 * @param id
	 * @param etablissemen
	 * @param dateString2
	 * @param dateString3
	 * @param compteur
	 */
	private static void ModifierLesEtablissementsHistorique(int id,
			String etablissemen, String dateString2, String dateString3,
			int compteur) {
		try {			
			leResultat = transmission.executeQuery(selectall+etablissement+where+nom+egal+ap+etablissemen+ap);			
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
		try {				
			while(leResultat.next()){	
					transmissionSecondaire.executeUpdate(update+historique+set+ide+egal
							+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta")) 
							+where+idcpt+egal+id);			
			}				
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
	}	
	/**
	 * Fonction permettant de modifier les types des historiques
	 * @param id
	 * @param typ
	 * @param dateString2
	 * @param dateString3
	 * @param compteur
	 */
	private static void ModifierLesTypesHistorique(int id, String typ, String dateString2, String dateString3, int compteur) {
		try {			
			leResultat = transmission.executeQuery(selectall+type+where+type+egal+ap+typ+ap);			
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
		try {				
			while(leResultat.next()){		
				transmissionSecondaire.executeUpdate(update+historique+set+idt+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type")) 
						+where+idcpt+egal+id);				
			}				
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
	}
	/**
	 * Fonction permettant de modifier les localisations des historiques
	 * @param id
	 * @param localisatio
	 * @param dateString2
	 * @param dateString3
	 * @param compteur
	 */
	private static void ModifierLesLocalisationsHistorique(int id,
			String localisatio, String dateString2, String dateString3,
			int compteur) {
		try {			
			leResultat = transmission.executeQuery(selectall+localisation+where+loc+egal+ap+localisatio+ap);			
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
		try {				
			while(leResultat.next()){	
				transmissionSecondaire.executeUpdate(update+historique+set+idloc+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_loc")) 
						+where+idcpt+egal+id);			
			}			
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
	}
	/**
	 * Fonction permettant de créer un historique
	 * @param id
	 * @param compteur
	 * @param typ
	 * @param localisatio
	 * @param etablissemen
	 * @param acti
	 */
	public static void CreerHistorique(int id, int compteur, int typ,
			int localisatio, int etablissemen, boolean acti) {
		try {			
			transmission.executeUpdate(insert+historique+po+idcpt+virgule+ide+virgule+idt+virgule+idloc+virgule+cpt+virgule
					+dated+virgule+datem+virgule+actif+pf+values+po+id+virgule+etablissemen+virgule+typ+virgule+localisatio
					+virgule+compteur+virgule+ap+dateString+ap+virgule+ap+dateString+ap+virgule+acti+pf);		
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
	}	
	/**
	 * Fonction permettant de modifier les types des historiques
	 * @param id
	 * @param typ
	 */
	public static void ModifierLesTypesHistorique(int id, String typ) {
		try {		
			leResultat = transmission.executeQuery(selectall+historique+where+idcpt+egal+id);			
		} 
		catch (SQLException e) {
			e.printStackTrace();		
		}		
		try {				
			while(leResultat.next()){	
				RequeteBDD.ModifierLesTypesHistorique(id, typ, leResultat.getString(Propriete.getProperty("RequeteBDD", "date_depart"))
						, leResultat.getString(Propriete.getProperty("RequeteBDD", "date_maj"))
						, leResultat.getInt(Propriete.getProperty("RequeteBDD", "compteur")));
			}				
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}
	}
	/**
	 * Fonction permettant de sélectionner les historiques
	 * @param id
	 * @return
	 */
	public static ArrayList<String> RequeteHistorique(int id) {
		reponse.removeAll(reponse);		
		try {				
			leResultat = transmission.executeQuery(selectall+historique+where+idh+egal+id);			
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
		try {				
			while(leResultat.next()){					
				int id_hi = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_hist"));
				int id_cpt = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id"));
				int compteur = leResultat.getInt(Propriete.getProperty("RequeteBDD", "compteur"));
				String date_Dep = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_depart"));
				String date_MAJ = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_maj"));
				ResultSet type_cpt = transmissionSecondaire.executeQuery(selectall+type+where+idt+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type")));
				ResultSet localisation_cpt = transmissionTerciaire.executeQuery(selectall+localisation+where+idl
						+egal+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_localisation")));
				ResultSet eta = transmissionQuaternaire.executeQuery(selectall+etablissement+where+ide+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta")));
				reponse.add(Integer.toString(id_hi));	
				reponse.add(Integer.toString(id_cpt));			
				if(type_cpt.next()){					
					reponse.add(type_cpt.getString(Propriete.getProperty("RequeteBDD", "type")));					
				}				
				else{					
					reponse.add("");					
				}				
				if(localisation_cpt.next()){				
					reponse.add(localisation_cpt.getString(Propriete.getProperty("RequeteBDD", "type_loc")));					
				}				
				else{					
					reponse.add("");					
				}				
				if(eta.next()){					
					reponse.add(eta.getString(Propriete.getProperty("RequeteBDD", "nom")));				
				}				
				else{					
					reponse.add("");					
				}
				reponse.add(Integer.toString(compteur));
				reponse.add(date_Dep);
				reponse.add(date_MAJ);		
			}			
		} 
		catch (SQLException e) {		
			e.printStackTrace();			
		}
		return reponse;		
	}
	/**
	 * Fonction permettant de supprimer un historique en fonction de l'ID de l'historique
	 * @param id_h
	 */
	public static void SupprimerHistorique(int id_h) {
		try {			
			transmission.execute(delete+historique+where+idh+egal+id_h);		
		} 
		catch (NumberFormatException e) {			
			e.printStackTrace();		
		} 
		catch (SQLException e) {						
			e.printStackTrace();
		}		
	}	
	/**
	 * Fonction permettant de supprimer un historique en fonction de l'ID d'un compteur
	 * @param id
	 */
	public static void SupprimerHistoriqueCompteur(int id) {
		try {			
			transmission.execute(delete+historique+where+idcpt+egal+id);	
		} 
		catch (NumberFormatException e) {			
			e.printStackTrace();		
		} 
		catch (SQLException e) {						
			e.printStackTrace();
		}		
	}
	/**
	 * Fonction permettant de sélectionner les historiques d'un compteur
	 * @param id
	 * @return
	 */
	public static ArrayList<String> RequeteHistoriqueCompteur(int id) {
		reponse.removeAll(reponse);		
		try {			
			leResultat = transmission.executeQuery(selectall+historique+where+idcpt+egal+id);		
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}		
		try {				
			while(leResultat.next()){					
				int id_hi = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_hist"));
				int id_cpt = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id"));
				int compteur = leResultat.getInt(Propriete.getProperty("RequeteBDD", "compteur"));
				String date_Dep = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_depart"));
				String date_MAJ = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_maj"));
				ResultSet type_cpt = transmissionSecondaire.executeQuery(selectall+type+where+idt+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type")));
				ResultSet localisation_cpt = transmissionTerciaire.executeQuery(selectall+localisation+where+idl
						+egal+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_localisation")));
				ResultSet eta = transmissionQuaternaire.executeQuery(selectall+etablissement+where+ide+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta")));
				boolean ac = leResultat.getBoolean(Propriete.getProperty("RequeteBDD", "actif"));
				reponse.add(Integer.toString(id_hi));	
				reponse.add(Integer.toString(id_cpt));			
				if(type_cpt.next()){					
					reponse.add(type_cpt.getString(Propriete.getProperty("RequeteBDD", "type")));					
				}				
				else{					
					reponse.add("");					
				}				
				if(localisation_cpt.next()){					
					reponse.add(localisation_cpt.getString(Propriete.getProperty("RequeteBDD", "type_loc")));					
				}				
				else{					
					reponse.add("");					
				}				
				if(eta.next()){					
					reponse.add(eta.getString(Propriete.getProperty("RequeteBDD", "nom")));					
				}				
				else{					
					reponse.add("");					
				}
				reponse.add(Integer.toString(compteur));
				reponse.add(date_Dep);
				reponse.add(date_MAJ);		
				if(ac){
					reponse.add(oui);
				}
				else{
					reponse.add(non);
				}	
			}				
		} 
		catch (SQLException e) {		
			e.printStackTrace();			
		}
		return reponse;		
	}
	/**
	 * Fonction permettant l'affichage des historiques
	 * @param id_h
	 * @param id
	 * @param cpt1
	 * @param cpt2
	 * @param date1
	 * @param date2
	 * @param type_t
	 * @param loc_t
	 * @param et_e
	 * @param act
	 * @return
	 */
	public static ArrayList<String> affichageHistorique(int id_h, int id,
			int cpt1, int cpt2, String date1, String date2, String type_t,
			String loc_t, String et_e, int act) {
		reponse.removeAll(reponse);		
		String debut = selectall+historique+where;
		String debutType = selectall+type+where;
		String debutLoc = selectall+localisation+where;
		String debutEta = selectall+etablissement+where;
		String idhi = idh+like+ap+id_h+pourcent+ap;
		String ids = idcpt+like+ap+id+pourcent+ap;
		String cpts = cpt+supeg+cpt1+and+cpt+infeg+cpt2;
		String dates = dated+supeg+ap+date1+ap+and+datem+infeg+ap+date2+ap;
		String typeb = type+egal+ap+type_t+ap;
		String typ = idt+egal;
		String locs = loc+egal+ap+loc_t+ap;
		String idlo = idloc+egal;
		String ets = nom+egal+ap+et_e+ap;
		String et = ide+egal;
		String acti = actif+egal+act;
		String resultat = "";
		int cpt = 0;		
		try {		
			if(id_h != -1){				
				resultat = resultat+idhi;
				cpt = 1;				
			}			
			if(id != -1){			
				if(cpt != 0){					
					resultat = resultat+and+ids;					
				}				
				else{					
					resultat = resultat+ids;
					cpt = 1;					
				}					
			}		
			if(cpt1 != -1 && cpt2 != -1){				
				if(cpt != 0){					
					resultat = resultat+and+cpts;					
				}				
				else{					
					resultat = resultat+cpts;
					cpt = 1;					
				}					
			}
			if(date1 != "" && date2 != ""){				
				if(cpt != 0){					
					resultat = resultat+and+dates;					
				}				
				else{					
					resultat = resultat+dates;
					cpt = 1;					
				}					
			}			
			if(type_t != ""){
				leResultat = transmissionSecondaire.executeQuery(debutType+typeb);
				if(leResultat.next()){
					if(cpt != 0){						
						resultat = resultat+and+typ+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type"));						
					}				
					else{					
						resultat = resultat+typ+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type"));
						cpt = 1;						
					}
				}	
			}			
			if(loc_t != ""){
				leResultat = transmissionTerciaire.executeQuery(debutLoc+locs);
				if(leResultat.next()){
					if(cpt != 0){						
						resultat = resultat+and+idlo+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_loc"));						
					}					
					else{						
						resultat = resultat+idlo+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_loc"));
						cpt = 1;						
					}
				}
			}			
			if(et_e != ""){
				leResultat = transmissionQuaternaire.executeQuery(debutEta+ets);
				if(leResultat.next()){
					if(cpt != 0){						
						resultat = resultat+and+et+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta"));						
					}					
					else{						
						resultat = resultat+et+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta"));
						cpt = 1;						
					}
				}
			}			
			if(act != -1){				
				if(cpt != 0){					
					resultat = resultat+and+acti;					
				}			
				else{				
					resultat = resultat+acti;
					cpt = 1;					
				}					
			}			
			if(!resultat.equals("")){				
				leResultat = transmission.executeQuery(debut+resultat);				
			}						
		} 
		catch (SQLException e) {
			e.printStackTrace();		
		}		
		try {				
			while(leResultat.next()){					
				int id_his = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_hist"));
				int id_p = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id"));
				int compteur = leResultat.getInt(Propriete.getProperty("RequeteBDD", "compteur"));
				String date_Dep = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_depart"));
				String date_MAJ = leResultat.getString(Propriete.getProperty("RequeteBDD", "date_maj"));
				ResultSet type_cpt = transmissionSecondaire.executeQuery(selectall+type+where+idt+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_type")));
				ResultSet localisation_cpt = transmissionTerciaire.executeQuery(selectall+localisation+where+idl
						+egal+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_localisation")));
				ResultSet eta = transmissionQuaternaire.executeQuery(selectall+etablissement+where+ide+egal
						+leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta")));
				boolean ac = leResultat.getBoolean(Propriete.getProperty("RequeteBDD", "actif"));
				reponse.add(Integer.toString(id_his));
				reponse.add(Integer.toString(id_p));				
				if(type_cpt.next()){					
					reponse.add(type_cpt.getString(Propriete.getProperty("RequeteBDD", "type")));					
				}				
				else{					
					reponse.add("");					
				}			
				if(localisation_cpt.next()){				
					reponse.add(localisation_cpt.getString(Propriete.getProperty("RequeteBDD", "type_loc")));					
				}				
				else{				
					reponse.add("");					
				}
				if(eta.next()){					
					reponse.add(eta.getString(Propriete.getProperty("RequeteBDD", "nom")));					
				}				
				else{					
					reponse.add("");					
				}
				reponse.add(Integer.toString(compteur));
				reponse.add(date_Dep);
				reponse.add(date_MAJ);
				if(ac){
					reponse.add(oui);
				}
				else{
					reponse.add(non);
				}					
			}				
		} 
		catch (SQLException e) {		
			e.printStackTrace();			
		}		
		return reponse;		
	}
	/**
	 * Fonction permettant de sélectionner les coordonnées d'un établissement
	 * @param selection
	 * @return
	 */
	public static ArrayList<String> SelectionnerCoordonnees(String selection) {
		reponse.removeAll(reponse);		
		try {				
			leResultat = transmission.executeQuery(selectall+etablissement+where+nom+egal+ap+selection+ap);			
		} 
		catch (SQLException e) {	
			e.printStackTrace();			
		}		
		try {				
			while(leResultat.next()){					
				int id_eta = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta"));
				String no = leResultat.getString(Propriete.getProperty("RequeteBDD", "nom"));
				String adress = leResultat.getString(Propriete.getProperty("RequeteBDD", "adresse"));
				String xb = leResultat.getString(Propriete.getProperty("RequeteBDD", "x"));
				String yb = leResultat.getString(Propriete.getProperty("RequeteBDD", "y"));	
				reponse.add(Integer.toString(id_eta));
				reponse.add(no);
				reponse.add(adress);
				reponse.add(xb);
				reponse.add(yb);				
			}				
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
		return reponse;		
	}
	/**
	 * Fonction permettant de sélectionner les établissements
	 * @return
	 */
	public static ArrayList<String> SelectionnerLesEtablissements() {
		reponse.removeAll(reponse);		
		try {				
			leResultat = transmission.executeQuery(selectall+etablissement);			
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}	
		try {			
			while(leResultat.next()){	
				int id = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta"));
				String nomb = leResultat.getString(Propriete.getProperty("RequeteBDD", "nom"));
				String numb = leResultat.getString(Propriete.getProperty("RequeteBDD", "num"));
				String adresseb = leResultat.getString(Propriete.getProperty("RequeteBDD", "adresse"));
				String cpb = leResultat.getString(Propriete.getProperty("RequeteBDD", "cp"));
				reponse.add(Integer.toString(id));
				reponse.add(nomb);
				reponse.add(numb);
				reponse.add(adresseb);
				reponse.add(cpb);				
			}				
		} 
		catch (SQLException e) {		
			e.printStackTrace();			
		}		
		return reponse;
	}
	/**
	 * Fonction permettant de rechercher des établissements
	 * @param no
	 * @param adress
	 * @param nu
	 * @param c
	 * @return
	 */
	public static ArrayList<String> rechercheEtablissements(String no,
			String adress, String nu, String c) {
		reponse.removeAll(reponse);		
		String debut = selectall+etablissement+where;
		String n = nom+like+ap+pourcent+no+pourcent+ap;
		String adr = adresse+like+ap+pourcent+adress+pourcent+ap;
		String numero = num+like+ap+pourcent+nu+pourcent+ap;
		String codeP = cp+like+ap+c+pourcent+ap;
		String resultat = "";
		int cpt = 0;		
		try {			
			if(n != ""){				
				resultat = resultat+n;
				cpt = 1;				
			}			
			if(adress != ""){				
				if(cpt != 0){					
					resultat = resultat+and+adr;				
				}				
				else{					
					resultat = resultat+adr;
					cpt = 1;					
				}					
			}			
			if(nu != ""){				
				if(cpt != 0){					
					resultat = resultat+and+numero;					
				}				
				else{					
					resultat = resultat+numero;
					cpt = 1;					
				}				
			}
			if(c != ""){				
				if(cpt != 0){				
					resultat = resultat+and+codeP;					
				}				
				else{					
					resultat = resultat+codeP;
					cpt = 1;				
				}
			}			
			if(!resultat.equals("")){				
				leResultat = transmission.executeQuery(debut+resultat);				
			}		
		} 
		catch (SQLException e) {
			e.printStackTrace();		
		}		
		try {				
			while(leResultat.next()){					
				int id_et = leResultat.getInt(Propriete.getProperty("RequeteBDD", "id_eta"));
				String nb = leResultat.getString(Propriete.getProperty("RequeteBDD", "nom"));
				String adressb = leResultat.getString(Propriete.getProperty("RequeteBDD", "adresse"));
				String nume = leResultat.getString(Propriete.getProperty("RequeteBDD", "num"));
				String codePost = leResultat.getString(Propriete.getProperty("RequeteBDD", "cp"));
				reponse.add(Integer.toString(id_et));
				reponse.add(nb);
				reponse.add(nume);
				reponse.add(adressb);
				reponse.add(codePost);		
			}				
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
		return reponse;				
	}
	/**
	 * Fonction permettant de modifier les localisations des historiques
	 * @param id
	 * @param localisatio
	 */
	public static void ModifierLesLocalisationsHistorique(int id, String localisatio) {
		try {			
			leResultat = transmission.executeQuery(selectall+historique+where+idcpt+egal+id);		
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}						
		try {				
			while(leResultat.next()){	
				RequeteBDD.ModifierLesLocalisationsHistorique(id, localisatio
						, leResultat.getString(Propriete.getProperty("RequeteBDD", "date_depart"))
						, leResultat.getString(Propriete.getProperty("RequeteBDD", "date_maj"))
						, leResultat.getInt(Propriete.getProperty("RequeteBDD", "compteur")));
			}				
		} 
		catch (SQLException e) {			
			e.printStackTrace();			
		}		
	}
	/**
	 * Fonction permettant de modifier les établissements des historiques
	 * @param id
	 * @param etablissemen
	 */
	public static void ModifierLesEtablissementsHistorique(int id, String etablissemen) {
		try {		
			leResultat = transmission.executeQuery(selectall+historique+where+idcpt+egal+id);			
		} 
		catch (SQLException e) {
			e.printStackTrace();			
		}						
		try {				
			while(leResultat.next()){	
				RequeteBDD.ModifierLesEtablissementsHistorique(id, etablissemen
						, leResultat.getString(Propriete.getProperty("RequeteBDD", "date_depart"))
						, leResultat.getString(Propriete.getProperty("RequeteBDD", "date_maj"))
						, leResultat.getInt(Propriete.getProperty("RequeteBDD", "compteur")));
			}				
		} 
		catch (SQLException e) {		
			e.printStackTrace();		
		}	
	}
	/**
	 * Fonction permettant de sélectionner le plus grand ID de compteur disponible
	 * @return
	 */
	public static ArrayList<String> ObtenirID() {
		reponse.removeAll(reponse);
		try {			
			leResultat = transmission.executeQuery(selectmax+systeme);			
		} 
		catch (SQLException e) {
			e.printStackTrace();		
		}		
		try {
			while(leResultat.next()){				
				int id = leResultat.getInt(max);
				reponse.add(Integer.toString(id+1));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return reponse;
	}
}