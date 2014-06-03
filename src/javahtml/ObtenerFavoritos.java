/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahtml;

import Model.Model;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Aurum CEO
 */
public class ObtenerFavoritos {
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    /**
     *
     * @author Aurum CEO
     */
    private static Document doc;
    public String url;
    Elements articulos1;
    Elements articulos4;
    Elements articulos5;
    Elements a1, a2, a3, a4, a5, a6;
    Elements images, images2;
    Model m;
    String tipo;
    String articulo;
    String idioma;
    String dominio1 = "http://www.fifa.com/";
    String dominio2 = "http://www.fifa.com";

    String dominio;
    String im1, img2, titulo, fecha, texto;
    ArrayList lista = new ArrayList();

    String bandera;

    public ObtenerFavoritos(String url, String idioma, String tipo) {
        this.url = url;
        articulos1 = null;
        articulos4 = null;
        images = null;
        this.idioma = idioma;
        this.tipo = tipo;
        if (this.idioma.equals("Ingles")) {
            m = new Model(1, "Favoritos");

        } else {
            m = new Model(2, "Favoritos");

        }
        switch (tipo) {
            case "Noticias":
                articulo = "article-body";
                break;
            case "Fotos":
                articulo = "row";
                break;
            case "Videos":
                articulo = "row";
                break;
        }

    }

    public Document conectar() {
//  System.out.println( this.url);
        try {
            doc = Jsoup.connect(url).get();

        } catch (IOException ex) {
            System.out.println("No se conecto ERROR");
        }
        return doc;
    }

    public void getHref() {

        articulos1 = this.conectar().select("div[class = team-qualifiedteams]");
        articulos4 = articulos1.select("a");
        articulos5 = articulos4.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
//       

        for (int i = 0; i < articulos4.size(); i++) {
            String[] split = articulos4.get(i).attr("href").split("/");
            String u = dominio1 + split[1] + "/" + split[2] + "/" + split[3] + "/news/" + split[4];
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            String[] splitban = articulos5.get(i).attr("src").split("/");
            System.out.println("u " + u);
            bandera = splitban[6];
            this.getNews(u);

            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }

    }

    public void getNews(String url) {
        this.url = url;

        a1 = this.conectar().select("div [class= row row-first]");
        a2 = a1.select("a[class= dcm-internal]");
        a3 = a2.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        for (int i = 0; i < a2.size(); i++) {
            System.out.println("__________________________________________");
//            System.out.println("href " + dominio2 + a2.get(i).attr("href"));
//            System.out.println("im chica  " + a3.get(i).attr("src"));
//            System.out.println("titulo news  " + a3.get(i).attr("ph-data-picture-title"));
//            System.out.println("fecha  " + a3.get(i).attr("ph-data-picture-date"));
//        

            lista.add(a3.get(i).attr("src"));
            lista.add(a3.get(i).attr("ph-data-picture-title"));
            lista.add(a3.get(i).attr("ph-data-picture-date"));

            obtenerTexto(dominio2 + a2.get(i).attr("href"));
            System.out.println("__________________________________________");
            System.out.println("   ");

        }
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

    }

    public void obtenerTexto(String url) {
        this.url = url;

        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        System.out.println("url llegando   " + url);
        a4 = this.conectar().select("div [class= row row-first]");
        a5 = a4.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        a5.attr("src");
        a6 = a4.select("div [class= article-body]");
        String p = a6.select("p").text();
//        System.out.println("im grande: " + a5.attr("src"));
//        System.out.println("parrafor:" + p);
//        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        lista.add(a5.attr("src"));
        lista.add(p);

        this.imprimirLista();

    }

    void imprimirLista() {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + " " + lista.get(i).toString());

        }

        m.insertFavoritos(lista.get(1).toString(),
                lista.get(0).toString(),
                lista.get(3).toString(),
                lista.get(4).toString(),
                lista.get(2).toString(), bandera);
        System.out.println("bandera = " + bandera);
        lista.clear();
    }

    public static void main(String[] args) {

        ObtenerFavoritos o = new ObtenerFavoritos("http://www.fifa.com/worldcup/teams/index.html", "1", "");
        o.getHref();
//       
    }
//      enlace en español e ingles
//
//    }
}




 