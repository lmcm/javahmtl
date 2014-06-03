/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahtml;

import Model.Model;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Aurum CEO
 */
public class ObtenerMundiales {

    private static Document doc;
    public String url;
    Elements classRow;
    Elements classH2;
    Elements images;
    Model m;
    String tipo;

    String idioma;
    String id;

    public ObtenerMundiales(String url, String idioma) {
        this.url = url;
        this.idioma = idioma;
        m = new Model(1, "");

    }

    public Document conectar() {

        try {
            doc = Jsoup.connect(url).get();

        } catch (IOException ex) {
            System.out.println("No se conecto ERROR");
        }
        return doc;
    }

    public void getElements() {
        int c = 0;

        classRow = this.conectar().select("div[class = row]");
        classH2 = classRow.select("h2");
        Elements a = classH2.select("a");
        Elements i = classRow.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        Elements pr = classRow.select("div [class= awards]");
        Elements li = pr.select("li");

        int tamaño = classH2.size();
        while (tamaño > 0) {
            System.out.println("");
            System.out.println("");
            System.out.println("Mundial : " + classH2.get(c).text());
            System.out.println("imagen 1 : " + "http://es.fifa.com" + i.get(c).attr("src"));
            System.out.println("__________________________________________________");
            this.url = "http://www.fifa.com" + a.get(c).attr("href");
            Elements articulo = this.conectar().select("div[class = row]");
            Elements h1 = articulo.select("h1");
            Elements im = articulo.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            Elements pa = articulo.select("p");
            System.out.println("titulo : " + h1.text());
            System.out.println("imagen 2: " + im.attr("src"));
            System.out.println("texto : " + pa.text());
            System.out.println("__________________________________________________");
            m.insertMundiales(idioma, classH2.get(c).text(), h1.text(), "http://es.fifa.com" + i.get(c).attr("src"), im.attr("src"), pa.text());
            c++;
            tamaño--;
        }

    }

    public String cobtenerParrafo(String url) {
        this.url = url;
        Elements articulo = this.conectar().select("div[class = row]");
        Elements h1 = articulo.select("h1");
        Elements im = articulo.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        Elements pa = articulo.select("p");
        System.out.println("titulo : " + h1.text());
        System.out.println("imagen 2: " + im.attr("src"));
        System.out.println("texto : " + pa.text());

        return "";
    }

    public void imprimir(String titulo, String imagen1, String imagen2, String date, String texto) {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("Imprimiendo");
        System.out.println("Titulo  " + titulo);
        System.out.println("Imagen 1 " + imagen1);
        System.out.println("Imagen 2 " + imagen2);
        System.out.println("Fecha " + date);
        System.out.println("Comentario " + texto);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("");
        System.out.println("");

    }

    public static void main(String[] args) {
        ObtenerMundiales o = new ObtenerMundiales("http://es.fifa.com/tournaments/archive/worldcup/index.html", "2");
//        o.getElements();

        ObtenerMundiales o2 = new ObtenerMundiales("http://www.fifa.com/tournaments/archive/worldcup/index.html", "1");
        o2.getElements();

    }
}
