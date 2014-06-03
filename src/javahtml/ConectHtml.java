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
public class ConectHtml {

    private static Document doc;
    public String url;
    Elements articulos1;
    Elements articulos4;
    Elements images;
    Model m;
    String tipo;
    String articulo;
    String idioma;

    public ConectHtml(String url, String idioma, String tipo) {
        this.url = url;
        articulos1 = null;
        articulos4 = null;
        images = null;
        this.idioma = idioma;
        this.tipo = tipo;
        if (this.idioma.equals("Ingles")) {
            m = new Model(1, "Noticias");

        } else {
            m = new Model(2, "Noticias");

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

        try {
            doc = Jsoup.connect(url).get();

        } catch (IOException ex) {
            System.out.println("No se conecto ERROR");
        }
        return doc;
    }

    public void getElements() {

        articulos1 = this.conectar().select("div[class = row]");
        articulos4 = articulos1.select("a[class = dcm-internal]");
        images = articulos4.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

        int c = 1;
        for (int i = 0; i < articulos4.size(); i++) {
            System.out.println("-----------------------------------------------------");

            for (int j = i; j < images.size(); j++) {
                System.out.println("title: " + images.get(j).attr("ph-data-picture-title"));
                System.out.println("Imagen 1: " + images.get(j).attr("src"));
                System.out.println("Imagen 2: " + images.get(j).attr("ph-data-picture-url"));
                System.out.println("Date: " + images.get(j).attr("ph-data-picture-date"));
                System.out.println("TEXTOS: " + images.get(j).attr("ph-data-picture-comment"));
                System.out.println("URL: " + articulos4.get(i).attr("href"));

                switch (tipo) {
                    case "Noticias":

                        ObtenerParrafos p = new ObtenerParrafos(articulos4.get(i).attr("href"), this.articulo, this.idioma);
                        String pa = p.obtenerParrafo();

                        m.insertNoticias(images.get(j).attr("ph-data-picture-title"), images.get(j).attr("src"), images.get(j).attr("ph-data-picture-url"), images.get(j).attr("ph-data-picture-date"), pa);

                        System.out.println("imprimiendo texto");
                        System.out.println("--------------------------");
                        System.out.println(pa);
                        System.out.println("--------------------------");
                        System.out.println(" ");
                        break;

                    case "Fotos":
                        m.insertFotos(images.get(j).attr("ph-data-picture-title"), images.get(j).attr("src"), images.get(j).attr("ph-data-picture-url"), images.get(j).attr("ph-data-picture-date"), images.get(j).attr("ph-data-picture-comment"));

                        break;

                    case "Videos":
                        ObtenerParrafos v = new ObtenerParrafos(articulos4.get(i).attr("href"), this.articulo, this.idioma);
                        m.insertVideos(images.get(j).attr("ph-data-picture-title"), images.get(j).attr("src"), images.get(j).attr("ph-data-picture-url"), v.obtenerUrlVideo(), images.get(j).attr("ph-data-picture-date"), images.get(j).attr("ph-data-picture-comment"));

                        break;
                    default:
                        break;

                }

                break;
            }

            System.out.println("CONTADOR" + c++);

            System.out.println("-----------------------------------------------------");

        }
//        m.cerrarConexion();

    }

    public static void main(String[] args) {
        int con = 1;
        String noticiaEs = "http://es.fifa.com/worldcup/news/all-news.html";
        String noticiaIng = "http://www.fifa.com/worldcup/news/all-news.html";

        String fotoEs = "http://es.fifa.com/worldcup/photos/all-photos.html";
        String fotoIng = "http://www.fifa.com/worldcup/photos/all-photos.html";

        String videoEs = "http://es.fifa.com/worldcup/videos/index.html";
        String videoIng = "http://www.fifa.com/worldcup/videos/index.html";
//        
        ConectHtml n = new ConectHtml(noticiaEs, "Español", "Noticias");
        ConectHtml n2 = new ConectHtml(noticiaIng, "Ingles", "Noticias");

        ConectHtml f = new ConectHtml(fotoEs, "Español", "Fotos");
        ConectHtml f2 = new ConectHtml(fotoIng, "Ingles", "Fotos");

        ConectHtml v = new ConectHtml(videoEs, "Español", "Videos");
        ConectHtml v2 = new ConectHtml(videoIng, "Ingles", "Videos");
//        while (true) {
//            try {
//              noticias   espa
        n.getElements();
        System.out.println("----------");
        System.out.println("----------");
        System.out.println("----------");
//
//             noticias in
        n2.getElements();
        System.out.println("----------");
        System.out.println("----------");
        System.out.println("----------");
//
////            fotos es
//        f.getElements();
//        System.out.println("----------");
//        System.out.println("----------");
//        System.out.println("----------");
//
////         fotos ing
//        f2.getElements();
//        System.out.println("----------");
//        System.out.println("----------");
//        System.out.println("----------");
//
////        video es
//        v.getElements();
//        System.out.println("----------");
//        System.out.println("----------");
//        System.out.println("----------");
//
//        //videos ing
//        v2.getElements();
//        System.out.println("----------");
//        System.out.println("----------");
//        System.out.println("----------");
//        System.out.println("Contador " + con);
        con++;
//                Thread.sleep(6000);
//            } catch (InterruptedException ex) {
//                System.out.println("error al dormir hilo");
//            }

//        }
    }
//        String v = "http://es.fifa.com/worldcup/organisation/news/index.html";
//        try {
//
//            //Traer los últimos artículos
//            Elements articulos1 = doc.select("div[class = row]");
//            Elements articulos4 = articulos1.select("a[class = dcm-internal]");
//            Elements images = articulos4.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
//
//            int c = 1;
//            for (int i = 0; i < articulos4.size(); i++) {
//                System.out.println("-----------------------------------------------------");
//                System.out.println("link : " + articulos4.get(i).attr("href"));
//
//                for (int j = i; j < images.size(); j++) {
//                    System.out.println("Imagen: " + images.get(j).attr("src"));
//                    System.out.println("title: " + images.get(j).attr("ph-data-picture-title"));
//                    System.out.println("Imagen: " + images.get(j).attr("ph-data-picture-url"));
//
//                    ObtenerParrafos p = new ObtenerParrafos(articulos4.get(i).attr("href"), "article-body");
//                    System.out.println("PARRAFO ES : " + p.obtenerParrafo());
//                    System.out.println("-----------------------------------------------------");
//
//                    break;
//                }
//                System.out.println("CONTADOR" + c);
//
//                c++;
//            }
//        } catch (IOException ex) {
//            System.out.println("error de conexion");
//        }
//
//    }
}
