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
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
/**
 * Classe permettant l'affichage et la gestion des historiques
 * @author Jérémy GRUMEL
 */
@SuppressWarnings("serial")
public class Historique extends JFrame{
	@SuppressWarnings({ })
	private JTable resultArea = new JTable(){
	    public boolean isCellEditable(int iRowIndex, int iColumnIndex)
	    {
	          return false;
	    }
	 };	
	private DefaultTableModel modele = new DefaultTableModel();	
	private JMenuBar barreMenu = new JMenuBar();
	private JMenu menu = new JMenu(Propriete.getProperty("Historique", "menu"));
	private JMenu operation = new JMenu(Propriete.getProperty("Historique", "operation"));
	private JMenuItem supprDesHist = new JMenuItem(Propriete.getProperty("Historique", "supprDesHist"));
	private JMenuItem enrDesHist = new JMenuItem(Propriete.getProperty("Historique", "enrDesHist"));
	private JMenuItem supprHist = new JMenuItem(Propriete.getProperty("Historique", "supprHist"));
	private JMenuItem enrHist = new JMenuItem(Propriete.getProperty("Historique", "enrHist"));	
	private JPopupMenu popDoubleClick = new JPopupMenu();
	private JMenuItem suppr = new JMenuItem(Propriete.getProperty("Historique", "suppr"));
	private JMenuItem emp = new JMenuItem(Propriete.getProperty("Historique", "emp"));
	private JPopupMenu popClicDroit = new JPopupMenu();
	private JMenuItem supprCD = new JMenuItem(Propriete.getProperty("Historique", "supprCD"));
	private JMenuItem empCD = new JMenuItem(Propriete.getProperty("Historique", "empCD"));	
	/**
	 * Fonction permettant l'affichage et la gestion des historiques
	 */
	public Historique(){ 		
		Affichage();
		TitreBarreMenu();
	}
	/**
	 * Fonction permettant l'affichage du titre et des menus ainsi que leurs gestions
	 */
	private void TitreBarreMenu() {
		this.setTitle("Historique"); 
		this.menu.add(operation);
		this.menu.addSeparator();
		this.menu.add(supprHist);
		this.menu.addSeparator();
		this.menu.add(enrHist);
		this.operation.add(supprDesHist);
		this.operation.add(enrDesHist);		
		this.barreMenu.add(menu);
		this.setJMenuBar(barreMenu);
	    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.pack();
        Toolkit tk = Toolkit.getDefaultToolkit();  
        int xSize = ((int) tk.getScreenSize().getWidth());  
        int ySize = ((int) tk.getScreenSize().getHeight());  
        this.setSize(xSize,ySize); 
	    this.setVisible(true);
		this.setLocationRelativeTo(null);		
	    // Listener pour le bouton enrHist
		enrHist.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {				
				new EnregistrerHistorique(resultArea);			
			}			
	    });		
	    // Listener pour le bouton supprHist
		supprHist.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {					
				new SupprimerHistorique(resultArea);			
			    for (int i = modele.getRowCount() - 1; i > -1; i--) {		    	
			        modele.removeRow(i);			        
			    }			    
			    ArrayList<String> tous = RequeteBDD.SelectionnerHistorique();
				new AffichageHistoriques(modele, tous);							
			}			
	    });		
	    // Listener pour le bouton enrDesHist
		enrDesHist.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {					
				try {				
					new EnregistrerHistorique();				
				} catch (FileNotFoundException e) {				
					e.printStackTrace();				
				}				
			}			
	    });		
	    // Listener pour le bouton supprDesHist
		supprDesHist.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {					
				try {					
					new SupprimerHistorique();				
				} catch (FileNotFoundException e) {				
					e.printStackTrace();					
				}				
			    for (int i = modele.getRowCount() - 1; i > -1; i--) {			    	
			        modele.removeRow(i);			        
			    }			    
			    ArrayList<String> tous = RequeteBDD.SelectionnerHistorique();
				new AffichageHistoriques(modele, tous);				
			}			
	    });		
	}
	/**
	 * Fonction permettant l'affichage des résultats et des composants pour la recherche et modification des historiques
	 */
	private void Affichage() {
		final JPanel panelPrincipal = new JPanel(null);
		panelPrincipal.setLayout(new GridLayout(2,1));
		JScrollPane scrollingArea = new JScrollPane(resultArea);
	    panelPrincipal.add(scrollingArea);
		final JPanel recherche = new JPanel(null);
		panelPrincipal.add(recherche);
		resultArea.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		Modele.Historique(modele, resultArea);
		ArrayList<String> tous = RequeteBDD.SelectionnerHistorique();
		new AffichageHistoriques(modele, tous);
		new EspaceRequeteHistorique(recherche, modele);
		this.add(panelPrincipal);	
		popDoubleClick.add(suppr);
		popDoubleClick.add(emp);
		popClicDroit.add(supprCD);
		popClicDroit.add(empCD);	
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
	    // Listener pour le bouton suppr
	    suppr.addActionListener(new ActionListener() {	    	
			public void actionPerformed(ActionEvent arg0) {				
				try {				
					JFileChooser sortie = new JFileChooser();
					int chemin = sortie.showSaveDialog(null);
					if(chemin == JFileChooser.APPROVE_OPTION){					
			    		PrintStream fichier_sortie;
			    		fichier_sortie = new PrintStream(new FileOutputStream(sortie.getSelectedFile().getAbsolutePath()
			    				+ Propriete.getProperty("Historique", "xls")));	
						fichier_sortie.print(Propriete.getProperty("Historique", "print"));
						new SupprimerHistorique((String)resultArea.getValueAt(resultArea.getSelectedRow(), 0), fichier_sortie);				
					    for (int i = modele.getRowCount() - 1; i > -1; i--) {					    	
					        modele.removeRow(i);					        
					    }					    
					    ArrayList<String> tous = RequeteBDD.SelectionnerHistorique();
						new AffichageHistoriques(modele, tous);
						JOptionPane.showMessageDialog(null, Propriete.getProperty("Historique", "fichierc") 
						+ sortie.getSelectedFile().getAbsolutePath() + Propriete.getProperty("Historique", "xls"));					
					}				
					else{					
						JOptionPane.showMessageDialog(null, Propriete.getProperty("Historique", "fichiernc"));						
					}			
				}			
				catch (FileNotFoundException e) {				
					e.printStackTrace();					
				}
			}		
	    });	    
	    // Listener pour le bouton emp
	    emp.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {				
				new GMap((String)resultArea.getValueAt(resultArea.getSelectedRow(), 4));			
			}			
	    });	    
	    // Listener pour le bouton supprCD
	    supprCD.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {				
				try {			
					String array = Arrays.toString(resultArea.getSelectedRows());
					String[] ligne = array.split(Propriete.getProperty("Historique", "split"));				
					JFileChooser sortie = new JFileChooser();
					int chemin = sortie.showSaveDialog(null);
					if(chemin == JFileChooser.APPROVE_OPTION){						
			    		PrintStream fichier_sortie;
			    		fichier_sortie = new PrintStream(new FileOutputStream(sortie.getSelectedFile().getAbsolutePath()
			    				+ Propriete.getProperty("Historique", "xls")));	
						fichier_sortie.print(Propriete.getProperty("Historique", "print"));					
						for(int i = 1; i < ligne.length; i++){					
							new SupprimerHistorique((String)resultArea.getValueAt(Integer.parseInt(ligne[i]), 0), fichier_sortie);						
						}					
					    for (int i = modele.getRowCount() - 1; i > -1; i--) {				    	
					        modele.removeRow(i);				        
					    }				    
					    ArrayList<String> tous = RequeteBDD.SelectionnerHistorique();
						new AffichageHistoriques(modele, tous);
						JOptionPane.showMessageDialog(null, Propriete.getProperty("Historique", "fichierc") 
						+ sortie.getSelectedFile().getAbsolutePath() + Propriete.getProperty("Historique", "xls"));				
					}			
					else{					
						JOptionPane.showMessageDialog(null, Propriete.getProperty("Historique", "fichiernc"));					
					}			
				}			
				catch (FileNotFoundException e) {					
					e.printStackTrace();				
				}				
			}			
	    });	    
	    // Listener pour le bouton empCD
	    empCD.addActionListener(new ActionListener() {    	
			public void actionPerformed(ActionEvent arg0) {				
				String array = Arrays.toString(resultArea.getSelectedRows());
				String[] ligne = array.split(Propriete.getProperty("Historique", "split"));
				for(int i = 1; i < ligne.length; i++){				
					new GMap((String)resultArea.getValueAt(Integer.parseInt(ligne[i]), 4));					
				}		
			}		
	    });		
	}
}