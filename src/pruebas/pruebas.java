/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javahtml.ObtenerParrafos;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Aurum CEO
 */
public class pruebas {

    String url, clase;
    Document doc;
    String idioma;
    String p = "";
    String array[];
    int c = 0;
    Elements articulo, articulo2, articulo3, images;

    public pruebas() {
//        array = new String[5];
//        array[1] = "http://www.fifa.com/worldcup/photos/index.html#2323002";

    }

    public Document conectarPagina() {
        this.articulo2 = null;
        this.articulo = null;
        this.doc = null;
//        System.out.println("Mi pagina " + this.array[c]);
        try {
            doc = Jsoup.connect("http://www.fifa.com/worldcup/photos/all-photos.html").get();
            System.out.println("conecto correctamente");
        } catch (IOException ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        c++;
        return doc;
    }

    public void obtenerParrafo() {
        articulo = this.conectarPagina().select("div[class = row]");
        articulo2 = articulo.select("a[class = dcm-internal]");
        images = articulo2.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

        int c = 1;
        for (int i = 0; i < articulo2.size(); i++) {
            System.out.println("-----------------------------------------------------");

            for (int j = i; j < images.size(); j++) {
                System.out.println("title: " + images.get(j).attr("ph-data-picture-title"));
                System.out.println("Imagen 1: " + images.get(j).attr("src"));
                System.out.println("Imagen 2: " + images.get(j).attr("ph-data-picture-url"));
                System.out.println("Date: " + images.get(j).attr("ph-data-picture-date"));
                System.out.println("TEXTOS: " + images.get(j).attr("ph-data-picture-comment"));
                System.out.println("URL: " + articulo2.get(i).attr("href"));

//               
                break;
            }

            System.out.println("CONTADOR" + c++);

            System.out.println("-----------------------------------------------------");

        }

    }

    public static void main(String[] args) {
        pruebas p = new pruebas();

//        for (int i = 0; i < 5; i++) {
        p.obtenerParrafo();
//        }

    }
}
