/*
 * Auteur : GRUMEL Jérémy
 * Date de création : 19/05/2014
 * Société : Next-One
 */
package Comptage;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
/**
 * Classe permettant l'affichage de notre Interface principale
 * @author Jérémy GRUMEL
 *
 */
public class Interface extends JFrame{	
	private static final long serialVersionUID=1L;	
	private JMenuBar barreMenu = new JMenuBar();
	private JMenu menu = new JMenu(Propriete.getProperty("Interface", "menu"));
	private JMenu historique = new JMenu(Propriete.getProperty("Interface", "historique"));
	private JMenuItem histo = new JMenuItem(Propriete.getProperty("Interface", "histo"));
	private JMenuItem maj = new JMenuItem(Propriete.getProperty("Interface", "maj"));
	private JMenu operation = new JMenu(Propriete.getProperty("Interface", "operation"));
	private JMenuItem creer = new JMenuItem(Propriete.getProperty("Interface", "creer"));
	private JMenuItem supprimer = new JMenuItem(Propriete.getProperty("Interface", "supprimer"));
	private JMenuItem type = new JMenuItem(Propriete.getProperty("Interface", "type"));
	private JMenuItem localisation = new JMenuItem(Propriete.getProperty("Interface", "localisation"));
	private JMenuItem etab = new JMenuItem(Propriete.getProperty("Interface", "etab"));
	private JMenuItem suppri = new JMenuItem(Propriete.getProperty("Interface", "suppri"));
	private JMenuItem enregistrer = new JMenuItem(Propriete.getProperty("Interface", "enregistrer"));
	private JMenuItem enregistrerDesCompteurs = new JMenuItem(Propriete.getProperty("Interface", "enregistrerDesCompteurs"));
	private JPopupMenu popDoubleClick = new JPopupMenu();
	private JMenuItem majcpt = new JMenuItem(Propriete.getProperty("Interface", "majcpt"));
	private JMenuItem suppr = new JMenuItem(Propriete.getProperty("Interface", "suppr"));
	private JMenuItem typ = new JMenuItem(Propriete.getProperty("Interface", "typ"));
	private JMenuItem loc = new JMenuItem(Propriete.getProperty("Interface", "loc"));
	private JMenuItem emp = new JMenuItem(Propriete.getProperty("Interface", "emp"));
	private JMenuItem eta = new JMenuItem(Propriete.getProperty("Interface", "eta"));
	private JPopupMenu popClicDroit = new JPopupMenu();
	private JMenuItem majcpts = new JMenuItem(Propriete.getProperty("Interface", "majcpts"));
	private JMenuItem supprCD = new JMenuItem(Propriete.getProperty("Interface", "supprCD"));
	private JMenuItem typCD = new JMenuItem(Propriete.getProperty("Interface", "typCD"));
	private JMenuItem locCD = new JMenuItem(Propriete.getProperty("Interface", "locCD"));
	private JMenuItem etaCD = new JMenuItem(Propriete.getProperty("Interface", "etaCD"));
	private JMenuItem empCD = new JMenuItem(Propriete.getProperty("Interface", "empCD"));
	private JMenuItem enrCD = new JMenuItem(Propriete.getProperty("Interface", "enrCD"));
	@SuppressWarnings({ "serial" })
	private JTable resultArea = new JTable(){
	    public boolean isCellEditable(int iRowIndex, int iColumnIndex)
	    {
	          return false;
	    }
	 };	
	private DefaultTableModel modele = new DefaultTableModel();
	private String chemin_explorateur = Propriete.getProperty("Interface", "chemin_explorateur");
	static Date date = new Date();
	static DateFormat dateFormat = new SimpleDateFormat(Propriete.getProperty("Interface", "dateFormat"));
	static String dateString = dateFormat.format(date);	
	/**
	 * Fonction permettant l'affichage de notre Interface
	 */
	public Interface(){ 	
		RequeteBDD.Connexion();
		Affichage();
		TitreBarreMenu();		    
	}
	/**
	 * Fonction permettant l'affichage de notre tableau et des interactions possibles
	 */
	private void Affichage() {	
		final JPanel panelPrincipal = new JPanel(null);
		panelPrincipal.setLayout(new GridLayout(2,1));
		JScrollPane scrollingArea = new JScrollPane(resultArea);
	    panelPrincipal.add(scrollingArea);
		final JPanel recherche = new JPanel(null);
		final JPanel rechercheEtab = new JPanel(null);
		rechercheEtab.setLayout(new GridLayout(2,1));
		panelPrincipal.add(recherche);
		resultArea.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		new Modele(modele, resultArea);
		ArrayList<String> tous = RequeteBDD.SelectionnerTous();
	    new AffichageCompteurs(modele, tous);
	    new EspaceRequetes(recherche, modele);      
	    this.add(panelPrincipal);  
	    popDoubleClick.add(majcpt);
	    popDoubleClick.add(suppr);
	    popDoubleClick.add(typ);
	    popDoubleClick.add(loc);
	    popDoubleClick.add(eta);
	    popDoubleClick.add(emp);
	    popClicDroit.add(majcpts);
	    popClicDroit.add(supprCD);
	    popClicDroit.add(typCD);
	    popClicDroit.add(locCD);
	    popClicDroit.add(etaCD);
	    popClicDroit.add(empCD);
	    popClicDroit.add(enrCD);    
	    resultArea.addMouseListener(new MouseAdapter(){    	
	        public void mouseClicked(MouseEvent e){        	
	        	if (e.getClickCount() == 2) {       			
	        		popDoubleClick.show(e.getComponent(), e.getX(), e.getY());	        		
	        	}	        	
	        	if (SwingUtilities.isRightMouseButton(e)){		        			
	        		popClicDroit.show(e.getComponent(), e.getX(), e.getY());
	            }	        	
	        }	        
	    });	
	    // Listener pour le bouton majcpt
	    majcpt.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {					
				try {
					new MAJ((String)resultArea.getValueAt(resultArea.getSelectedRow(), 0));
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}		
			    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
			        modele.removeRow(i);			        
			    }			    
			    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
				new AffichageCompteurs(modele, tous);				
			}			
	    });
	    // Listener pour le bouton suppr
	    suppr.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {					
				new SupprimerCompteurs((String)resultArea.getValueAt(resultArea.getSelectedRow(), 0));				
			    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
			        modele.removeRow(i);			        
			    }			    
			    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
				new AffichageCompteurs(modele, tous);				
			}			
	    });
	    // Listener pour le bouton typ
	    typ.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {	
				new TypeCompteurs((String)resultArea.getValueAt(resultArea.getSelectedRow(), 0), modele);	
			}			
	    });	    
	    // Listener pour le bouton loc
	    loc.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {				
				new LocalisationCompteurs((String)resultArea.getValueAt(resultArea.getSelectedRow(), 0), modele);					
			}		
	    });    
	    // Listener pour le bouton eta
	    eta.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {				
				new EtablissementCompteurs((String)resultArea.getValueAt(resultArea.getSelectedRow(), 0), modele);						
			}		
	    });    
	    // Listener pour le bouton emp
	    emp.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {					
				new GMap((String)resultArea.getValueAt(resultArea.getSelectedRow(), 6));				
			}		
	    });
	    // Listener pour le bouton majcpts
	    majcpts.addActionListener(new ActionListener() {
	   		public void actionPerformed(ActionEvent arg0) {					
				String array = Arrays.toString(resultArea.getSelectedRows());
				String[] ligne = array.split(Propriete.getProperty("Interface", "split"));
				for(int i = 1; i < ligne.length; i++){					
					try {
						new MAJ((String)resultArea.getValueAt(Integer.parseInt(ligne[i]), 0));
					} 
					catch (NumberFormatException e) {
						e.printStackTrace();
					} 
					catch (SQLException e) {
						e.printStackTrace();
					}
				}				
			    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
			        modele.removeRow(i);			        
			    }			    
			    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
				new AffichageCompteurs(modele, tous);				
			}			
	    });
	    // Listener pour le bouton supprCD
	    supprCD.addActionListener(new ActionListener() {
	   		public void actionPerformed(ActionEvent arg0) {					
				String array = Arrays.toString(resultArea.getSelectedRows());
				String[] ligne = array.split(Propriete.getProperty("Interface", "split"));
				for(int i = 1; i < ligne.length; i++){					
					new SupprimerCompteurs((String)resultArea.getValueAt(Integer.parseInt(ligne[i]), 0));					
				}				
			    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
			        modele.removeRow(i);			        
			    }			    
			    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
				new AffichageCompteurs(modele, tous);				
			}			
	    });	    
	    // Listener pour le bouton typrCD
	    typCD.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {	
				String array = Arrays.toString(resultArea.getSelectedRows());
				String[] ligne = array.split(Propriete.getProperty("Interface", "split"));
				for(int i = 1; i < ligne.length; i++){				
					new TypeCompteurs((String)resultArea.getValueAt(Integer.parseInt(ligne[i]), 0), modele);				
				}							
			}			
	    });	    
	    // Listener pour le bouton locCD
	    locCD.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {					
				String array = Arrays.toString(resultArea.getSelectedRows());
				String[] ligne = array.split(Propriete.getProperty("Interface", "split"));
				for(int i = 1; i < ligne.length; i++){					
					new LocalisationCompteurs((String)resultArea.getValueAt(Integer.parseInt(ligne[i]), 0), modele);					
				}							
			}	
	    });    
	    // Listener pour le bouton etaCD
	    etaCD.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {					
				String array = Arrays.toString(resultArea.getSelectedRows());
				String[] ligne = array.split(Propriete.getProperty("Interface", "split"));
				for(int i = 1; i < ligne.length; i++){					
					new EtablissementCompteurs((String)resultArea.getValueAt(Integer.parseInt(ligne[i]), 0), modele);					
				}				
			    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
			        modele.removeRow(i);			        
			    }			    
			    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
				new AffichageCompteurs(modele, tous);				
			}			
	    });	    
	    // Listener pour le bouton empCD
	    empCD.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {					
				String array = Arrays.toString(resultArea.getSelectedRows());
				String[] ligne = array.split(Propriete.getProperty("Interface", "split"));
				for(int i = 1; i < ligne.length; i++){					
					new GMap((String)resultArea.getValueAt(Integer.parseInt(ligne[i]), 6));					
				}				
			}			
	    });	    
	    // Listener pour le bouton enrCD
	    enrCD.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {					
				String array = Arrays.toString(resultArea.getSelectedRows());
				String[] ligne = array.split(Propriete.getProperty("Interface", "split"));				
				String[][] resultat = new String[ligne.length-1][resultArea.getColumnCount()];
				for(int i = 1; i < ligne.length; i++){
					for(int j = 0; j < resultArea.getColumnCount(); j++){						
						resultat[i-1][j] = (String)resultArea.getValueAt(Integer.parseInt(ligne[i]), j);						
					}					
				}				
				new Enregistrer(resultat);
			}			
	    });		
	}
	/**
	 * Fonction permettant l'affichage du titre et des menus, ainsi que leurs gestions
	 */
	private void TitreBarreMenu() {		
		this.setTitle(Propriete.getProperty("Interface", "this"));
		this.menu.add(operation);
		this.menu.addSeparator();
		this.menu.add(maj);
		this.menu.addSeparator();
		this.menu.add(suppri);
		this.menu.addSeparator();
		this.menu.add(enregistrer);
		this.operation.add(creer);
		this.operation.add(supprimer);
		this.operation.add(type);
		this.operation.add(localisation);
		this.operation.add(etab);
		this.operation.add(enregistrerDesCompteurs);
		this.historique.add(histo);		
		this.barreMenu.add(menu);
		this.barreMenu.add(historique);
		this.setJMenuBar(barreMenu);
	    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.pack();
        Toolkit tk = Toolkit.getDefaultToolkit();  
        int xSize = ((int) tk.getScreenSize().getWidth());  
        int ySize = ((int) tk.getScreenSize().getHeight()); 
        this.setSize(xSize,ySize); 
	    this.setVisible(true);
		this.setLocationRelativeTo(null);
		// Création de notre explorateur de fichiers
	    final JFileChooser fc = new JFileChooser(chemin_explorateur);	    
	    // Listener pour le bouton maj
	    maj.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {				
				new MAJ(fc);				
			    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
			        modele.removeRow(i);			        
			    }			    
			    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
				new AffichageCompteurs(modele, tous);			
			}			
	    });	    
	    // Listener pour le bouton creer
	    creer.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {					
				new CreerCompteurs(modele);				
			}			
	    });	    
	    // Listener pour le bouton supprimer
	    supprimer.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {				
				new SupprimerCompteurs();				
			    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
			        modele.removeRow(i);			        
			    }			    
			    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
				new AffichageCompteurs(modele, tous);				
			}			
	    });	    
	    // Listener pour le bouton type
	    type.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {					
				new TypeCompteurs(modele);				
			}			
	    });	    
	    // Listener pour le bouton localisation
	    localisation.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {					
				new LocalisationCompteurs(modele);				
			}			
	    });    
	    // Listener pour le bouton etab
	    etab.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {		
				new EtablissementCompteurs(modele);		
			}			
	    });	    
	    // Listener pour le bouton enregistrerDesCompteurs
	    enregistrerDesCompteurs.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {				
				try {					
					new Enregistrer();					
				} 
				catch (FileNotFoundException e) {				
					e.printStackTrace();					
				}			
			}			
	    });	    
	    // Listener pour le bouton suppri
	    suppri.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {					
				new SupprimerCompteurs(resultArea);				
			    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
			        modele.removeRow(i);			        
			    }			    
			    ArrayList<String> tous = RequeteBDD.SelectionnerTous();
				new AffichageCompteurs(modele, tous);				
			}			
	    });	    
	    // Listener pour le bouton enregistrer
	    enregistrer.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {					
				new Enregistrer(resultArea);				
			}			
	    });	    
	    // Listener pour le bouton histo
	    histo.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {					
				new Historique();				
			}			
	    });		
	}	
}