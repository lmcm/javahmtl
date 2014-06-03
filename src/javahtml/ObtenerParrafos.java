/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahtml;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Aurum CEO
 */
public class ObtenerParrafos {

    String url, clase;
    Document doc;
    String idioma;

    public ObtenerParrafos(String url, String clase, String idioma) {
        this.url = url;
        this.clase = clase;
        this.idioma = idioma;

    }

    public Document conectarPagina() {
        
        System.out.println("MI :dddd URL  " +this.url);
        try {
            
            switch (this.idioma) {
                case "Español":
                    this.url = "http://es.fifa.com" + this.url;               
                    doc = Jsoup.connect(this.url).get();
                
                   
                    break;
                case "Ingles":
                    this.url = "http://www.fifa.com" + this.url;
                    doc = Jsoup.connect(this.url).get();
                  

                    break;

            }

        } catch (IOException ex) {
           
            ex.printStackTrace();

        }
        return doc;
    }

    public String obtenerParrafo() {
        
        System.out.println("url parafo " +this.url);
        String p = "";
       
        Elements articulo = conectarPagina().select("div[class = " + this.clase + "]");//  
        System.out.println("imprimiendo articulo " +articulo);
        Elements pa = articulo.select("p");

        for (Element parrafo : pa) {
            p += parrafo.text();

        }

        return p;
    }

    public String obtenerUrlVideo() {
        String url = "";
        Elements pa = null;
        Elements u = null;

        Elements articulo = conectarPagina().select("div[class = " + this.clase + "]");//  
        pa = articulo.select("div [class = article-top-content]");
        u = pa.select("input#youtube-video-embedsrc");
        url = u.attr("value");
        System.out.println("mi url youtube es " + url);

//        for (Element parrafo : pa) {
//            p += parrafo.text();
//
//        }
//        p=url.attr("value");
//        System.out.println("mi url video es :" +p);
        return url;
    }

}
