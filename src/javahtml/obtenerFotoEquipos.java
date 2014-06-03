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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Aurum CEO
 */
public class obtenerFotoEquipos {

    private static Document doc;
    public String url;
    Elements articulos1;
    Elements articulos4;
    Elements images;
    Model m;
    String tipo;
    String articulo;
    String idioma;
    String id;

    public obtenerFotoEquipos(String url, String idioma) {

        String comp;

        if (idioma.equalsIgnoreCase("Ingles")) {
            comp = "http://www.fifa.com";
            m = new Model(1, "");
        } else {
            comp = "http://es.fifa.com";
            m = new Model(2, "");
        }

        this.url = comp + url;
        this.idioma = idioma;

    }

    public Document conectar() {

        try {
            doc = Jsoup.connect(url).get();
            System.out.println("conectando  " + url);

        } catch (IOException ex) {
            System.out.println("No se conecto ERROR");
            ex.printStackTrace();
        }
        return doc;
    }

    public void getElements() {
        int c = 1;
        String comp;

        Elements articulos5, articulos6 = null;
        articulos1 = this.conectar().select("div [class = row ]");
        articulos5 = articulos1.select("div[class= dcm-list nl-topstory-wrap]");
        articulos6 = articulos5.select("a[class= dcm-internal]");

        System.out.println("_SSSSSSSSSSSSSSSSSSSSSSss");
        System.out.println(articulos1);
        System.out.println("_SSSSSSSSSSSSSSSSSSSSSSss");
        images = articulos6.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        String href = articulos6.attr("href");
        String titulo = images.attr("ph-data-picture-title");
        String img1 = images.attr("src");
        String img2 = images.attr("ph-data-picture-url");
        String fecha = images.attr("ph-data-picture-date");

        if (idioma.equalsIgnoreCase("Ingles")) {
            comp = "http://www.fifa.com";
        } else {
            comp = "http://es.fifa.com";
        }

        href = comp + href;
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("Imprimiendo");
        System.out.println("Titulo  " + titulo);
        System.out.println("Imagen 1 " + img1);
        System.out.println("Imagen 2 " + img2);
        System.out.println("Fecha " + fecha);
        System.out.println("HREF " + href);
        this.url = href;
        articulos1 = this.conectar().select("div [class = article-body ]");
        String p = "";
        Elements texto = articulos1.select("p");
        for (Element texto1 : texto) {

            p += texto1.text();
        }

        m.insertFotosEquipos(titulo, img1, img2, p, fecha);
        System.out.println("Texto " + p);

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("");
        System.out.println("");

    }

    public static void main(String[] args) {

        String array[];
        array = new String[32];
        array[0] = "/worldcup/teams/team=43843/index.html";
        array[1] = "/worldcup/teams/team=43849/index.html";
        array[2] = "/worldcup/teams/team=43854/index.html";
        array[3] = "/worldcup/teams/team=43860/index.html";
        array[4] = "/worldcup/teams/team=43876/index.html";

        array[5] = "/worldcup/teams/team=43976/index.html";
        array[6] = "/worldcup/teams/team=43817/index.html";
        array[7] = "/worldcup/teams/team=43819/index.html";
        array[8] = "/worldcup/teams/team=43822/index.html";

        array[9] = "/worldcup/teams/team=43935/index.html";
        array[10] = "/worldcup/teams/team=44037/index.html";
        array[11] = "/worldcup/teams/team=43938/index.html";
        array[12] = "/worldcup/teams/team=43942/index.html";
        array[13] = "/worldcup/teams/team=43946/index.html";
        array[14] = "/worldcup/teams/team=43948/index.html";
        array[15] = "/worldcup/teams/team=43949/index.html";
        array[16] = "/worldcup/teams/team=43954/index.html";
        array[17] = "/worldcup/teams/team=43960/index.html";
        array[18] = "/worldcup/teams/team=43963/index.html";
        array[19] = "/worldcup/teams/team=43965/index.html";
        array[20] = "/worldcup/teams/team=43969/index.html";
        array[21] = "/worldcup/teams/team=43971/index.html";
        array[22] = "/worldcup/teams/team=43901/index.html";
        array[23] = "/worldcup/teams/team=43909/index.html";
        array[24] = "/worldcup/teams/team=43911/index.html";
        array[25] = "/worldcup/teams/team=43921/index.html";
        array[26] = "/worldcup/teams/team=43922/index.html";
        array[27] = "/worldcup/teams/team=43924/index.html";
        array[28] = "/worldcup/teams/team=43925/index.html";
        array[29] = "/worldcup/teams/team=43926/index.html";
        array[30] = "/worldcup/teams/team=43927/index.html";
        array[31] = "/worldcup/teams/team=43930/index.html";

        for (int i = 0; i < array.length; i++) {
            obtenerFotoEquipos o;
            if (i % 2 == 0) {
                o = new obtenerFotoEquipos(array[i], "Ingles");
                o.getElements();
            } else {
                o = new obtenerFotoEquipos(array[i - 1], "Español");
                o.getElements();
            }

        }

//        array[2] = ""; //brasilia 2
//        array[3] = "";
//
//        array[4] = "";//cuiba 2
//        array[5] = "";//cuiba 1
//
//        array[6] = ""; //curitiba 2
//        array[7] = "";//curitiba 1
//        array[8] = "http://es.fifa.com/worldcup/destination/cities/city=11693/photos/index.html#2242161"; //fortaleza 2
//        array[9] = "http://www.fifa.com/worldcup/destination/cities/city=11693/photos/index.html#2242161";//fortaleza 1
//
//        array[10] = "http://es.fifa.com/worldcup/destination/cities/city=2037/photos/index.html#2246604";//manaos 2
//        array[11] = "http://www.fifa.com/worldcup/destination/cities/city=2037/photos/index.html#2246604";// 1
//
//        array[12] = "http://es.fifa.com/worldcup/destination/cities/city=21518/photos/index.html#2242242";//natal 2
//        array[13] = "http://www.fifa.com/worldcup/destination/cities/city=21518/photos/index.html#2242242";//1
//
//        array[14] = "http://es.fifa.com/worldcup/destination/cities/city=1140/photos/index.html#2242218";//porto alegre 2
//        array[15] = "http://www.fifa.com/worldcup/destination/cities/city=1140/photos/index.html#2242218";//1
//
//        array[16] = "http://es.fifa.com/worldcup/destination/cities/city=6099/photos/index.html#2242281";//recife 2
//        array[17] = "http://www.fifa.com/worldcup/destination/cities/city=6099/photos/index.html#2242281";//1
//
//        array[18] = "http://es.fifa.com/worldcup/destination/cities/city=1141/photos/index.html#2242949";//rio de janeiro 2
//        array[19] = "http://www.fifa.com/worldcup/destination/cities/city=1141/photos/index.html#2242949";//1
//
//        array[20] = "http://es.fifa.com/worldcup/destination/cities/city=50071/photos/index.html";//salvador 2
//        array[21] = "http://www.fifa.com/worldcup/destination/cities/city=50071/photos/index.html";//1
//
//        array[22] = "http://es.fifa.com/worldcup/destination/cities/city=1047/photos/index.html#2243045";//sao paulo 2
//        array[23] = "http://www.fifa.com/worldcup/destination/cities/city=1047/photos/index.html#2243045";// 1
    }

}
