/*
 * Auteur : GRUMEL Jérémy
 * Date de création : 19/05/2014
 * Société : Next-One
 */
package Comptage;
import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
/**
 * Classe permettant l'affichage d'une GMap static
 * @author Jérémy GRUMEL
 */
@SuppressWarnings("serial")
public class JGoogleMapEditorPan extends JEditorPane {
    private int zoomFactor = 16;
    private String ApiKey = "";
    @SuppressWarnings("unused")
	private String roadmap = "roadmap";
    public final String viewTerrain = "terrain";
    public final String viewSatellite = "satellite";
    public final String viewHybrid = "hybrid";
    public final String viewRoadmap = "roadmap";
    /**
     * Constructeur de notre classe JGoogleMapEditorPan
     */
    public JGoogleMapEditorPan() {
        HTMLEditorKit kit = new HTMLEditorKit();
        HTMLDocument htmlDoc = (HTMLDocument) kit.createDefaultDocument();
        this.setEditable(false);
        this.setContentType("text/html");
        this.setEditorKit(kit);
        this.setDocument(htmlDoc);
    }
    /**
     * Fonction permettant de fixer le zoom
     * @param zoom : valeur de 0 à 21
     */
    public void setZoom(int zoom) {
        this.zoomFactor = zoom;
    }
    /**
     * Fonction permettant de fixer la clé de developpeur
     * @param key : APIKey fourni par Google
     */
    public void setApiKey(String key) {
        this.ApiKey = key;
    }
    /**
     * Fonction permettant de fixer le type de vue
     * @param roadMap
     */
    public void setRoadmap(String roadMap) {
        this.roadmap = roadMap;
    }
    /**
     * Fonction permettant d'afficher la carte d'après des coordonnées GPS
     * @param latitude
     * @param longitude
     * @param width
     * @param height
     * @return 
     * @throws Exception erreur si la APIKey est non renseignée
     */
    public void showCoordinate(String latitude, String longitude, int width, int height) throws Exception {
        this.setMap(latitude, longitude, width, height);
    }
    /**
     * Fonction permettant d'afficher la carte d'après une ville et son pays
     * @param city
     * @param country
     * @param width
     * @param height
     * @throws Exception erreur si la APIKey est non renseignée
     */
    public void showLocation(String city, String country, int width, int height) throws Exception {
        this.setMap(city, country, width, height);
    }
    /**
     * Fonction permettant d'assembler l'url et Générer le code HTML
     * @param x
     * @param y
     * @param width
     * @param height
     * @throws Exception
     */
    private void setMap(String x, String y, Integer width, Integer height) throws Exception {
        if (this.ApiKey.isEmpty()) {
            throw new Exception("Developper API Key non renseignée !!!!");
        }
        String url = "http://maps.google.com/maps/api/staticmap?";
        url += "center=" + x + "," + y;
        url += "&amp;zoom=" + this.zoomFactor;
        url += "&amp;size=" + width.toString() + "x" + height.toString();
        url += "&amp;maptype=" + this.viewHybrid;
        url += "&amp;markers=color:blue" + x + "," + y;
        url += "&amp;sensor=false";
        url += "&amp;key=" + this.ApiKey;
        String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
        html += "<html><head></head><body>";
        html += "<img src='" + url + "'>";
        html += "</body></html>";
        this.setText(html);
    }
}