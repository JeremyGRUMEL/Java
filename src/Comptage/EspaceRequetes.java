/*
 * Auteur : GRUMEL Jérémy
 * Date de création : 19/05/2014
 * Société : Next-One
 */
package Comptage;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
/**
 * Classe permettant l'affichage des composants nécessaires aux requêtes sur les compteurs
 * @author Jérémy GRUMEL
 */
public class EspaceRequetes {
	/**
	 * Fonction permettant l'affichage des composants nécessaires aux requêtes sur les compteurs
	 * @param recherche : Notre JPanel correspondant aux composants
	 * @param modele : Notre modèle pour l'affichage des résultats
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EspaceRequetes(JPanel recherche, final DefaultTableModel modele){		
		JButton afficher = new JButton(Propriete.getProperty("EspaceRequetes", "afficher"));
		JLabel espace = new JLabel(Propriete.getProperty("EspaceRequetes", "espace"));
		JLabel espace2 = new JLabel(Propriete.getProperty("EspaceRequetes", "espace"));
		JLabel espace3 = new JLabel(Propriete.getProperty("EspaceRequetes", "espace2"));
		JLabel espace4 = new JLabel(Propriete.getProperty("EspaceRequetes", "espace2"));
		JLabel espace5 = new JLabel(Propriete.getProperty("EspaceRequetes", "espace3"));
		JLabel espace6 = new JLabel(Propriete.getProperty("EspaceRequetes", "espace3"));
		JLabel id = new JLabel(Propriete.getProperty("EspaceRequetes", "id"));
		final JTextField idText = new JTextField(6);		
		JLabel cpt = new JLabel(Propriete.getProperty("EspaceRequetes", "cpt"));
		final JTextField cptText1 = new JTextField(6);
		JLabel a = new JLabel(Propriete.getProperty("EspaceRequetes", "a"));
		final JTextField cptText2 = new JTextField(6);		
		JLabel date = new JLabel(Propriete.getProperty("EspaceRequetes", "date"));	
		final JDateChooser calendrier = new JDateChooser();
		calendrier.setDateFormatString(Propriete.getProperty("EspaceRequetes", "calendrier"));		
		JLabel a2 = new JLabel(Propriete.getProperty("EspaceRequetes", "a"));		
		final JDateChooser calendrier2 = new JDateChooser();
		calendrier2.setDateFormatString(Propriete.getProperty("EspaceRequetes", "calendrier"));		
		JLabel type = new JLabel(Propriete.getProperty("EspaceRequetes", "type"));
		ArrayList<String> types = RequeteBDD.SelectionnerTypes();
		String[] choixType = new String[types.size() + 1];		
		for(int i = 0; i < types.size(); i++){			
			choixType[i+1] = types.get(i);			
		}		
		final JComboBox choixT = new JComboBox(choixType);
		JLabel loc = new JLabel(Propriete.getProperty("EspaceRequetes", "loc"));
		ArrayList<String> loca = RequeteBDD.SelectionnerLocalisations();
		String[] choixLoc = new String[loca.size() + 1];		
		for(int i = 0; i < loca.size(); i++){		
			choixLoc[i+1] = loca.get(i);			
		}			
		final JComboBox choixL = new JComboBox(choixLoc);	
		JLabel et = new JLabel(Propriete.getProperty("EspaceRequetes", "et"));
		final JLabel etabSelec = new JLabel("");
		JButton selec = new JButton(Propriete.getProperty("EspaceRequetes", "selec"));	
		JButton rechercher = new JButton(Propriete.getProperty("EspaceRequetes", "rechercher"));	
		recherche.setLayout(new FlowLayout());	
		recherche.add(espace);
		recherche.add(id);
		recherche.add(idText);
		recherche.add(cpt);
		recherche.add(cptText1);
		recherche.add(a);
		recherche.add(cptText2);
		recherche.add(espace2);
		recherche.add(espace3);
		recherche.add(date);
		recherche.add(calendrier);
		recherche.add(a2);
		recherche.add(calendrier2);
		recherche.add(type);
		recherche.add(choixT);
		recherche.add(loc);
		recherche.add(choixL);
		recherche.add(espace4);
		recherche.add(espace5);
		recherche.add(et);
		recherche.add(etabSelec);
		recherche.add(espace6);
		recherche.add(selec);
		recherche.add(rechercher);
		recherche.add(afficher);
	    // Listener pour le bouton afficher
	    afficher.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {					
			    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
			        modele.removeRow(i);			        
			    }			    
			    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
				new AffichageCompteurs(modele, tous);		
			}			
	    });	
	    // Listener pour le bouton selec
	    selec.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {					
			    new SelectionnerEtablissement(modele, etabSelec);	
			}			
	    });	
	    // Listener pour le bouton rechercher
	    rechercher.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {					
				int id_t, cpt_t1, cpt_t2;
				String date1, date2, type_t, loc_t, et_e;			
				if(idText.getText().equals("")){					
					id_t = -1;					
				}				
				else{					
					id_t = Integer.parseInt(idText.getText());					
				}				
				if(cptText1.getText().equals("") || cptText2.getText().equals("")){					
					cpt_t1 = -1; cpt_t2 = -1;					
				}				
				else{					
					cpt_t1 = Integer.parseInt(cptText1.getText());
					cpt_t2 = Integer.parseInt(cptText2.getText());					
				}				
				if(calendrier.getDate() == null || calendrier2.getDate() == null){					
					date1 = ""; date2 = "";					
				}				
				else{					
					date1 = new SimpleDateFormat(Propriete.getProperty("EspaceRequetes", "calendrier"))
					.format(calendrier.getDate());
					date2 = new SimpleDateFormat(Propriete.getProperty("EspaceRequetes", "calendrier"))
					.format(calendrier2.getDate());					
				}				
				if(choixT.getSelectedItem() == null){					
					type_t = "";					
				}								
				else{					
					type_t = (String)choixT.getSelectedItem();					
				}	
				if(choixL.getSelectedItem() == null){					
					loc_t = "";					
				}								
				else{					
					loc_t = (String)choixL.getSelectedItem();					
				} 				
				if(etabSelec.getText() == ""){					
					et_e = "";					
				}							
				else{					
					et_e = etabSelec.getText();					
				}		
				ArrayList<String> resultat = RequeteBDD.affichage(id_t, cpt_t1, cpt_t2, date1, date2, type_t, loc_t, et_e);			
			    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
			        modele.removeRow(i);			        
			    }
				new AffichageCompteurs(modele, resultat);
			}			
	    });		
	}
}