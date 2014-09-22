/*
 * Auteur : GRUMEL Jérémy
 * Date de création : 19/05/2014
 * Société : Next-One
 */
package Comptage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * Classe permettant la gestion des properties
 * @author Jérémy GRUMEL
 */
public class Propriete {
	/**
	 * Fonction permettant la gestion des properties
	 * @param classe : Notre classe
	 * @param propriete : Notre propriété
	 * @return : La propriété correspondant à notre classe
	 */
	public static String getProperty(String classe, String propriete) {
		 String configPath = classe + ".properties";
		 Properties c = new Properties();
		 try {
		 FileInputStream in = new FileInputStream(configPath);
		 c.load(in);
		 in.close();
		 } catch (IOException e) {
		 System.out.println("Impossible de charger le fichier " + classe + ".properties");
		 }
		return c.getProperty(propriete);
	}
}