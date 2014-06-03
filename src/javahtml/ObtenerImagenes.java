/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahtml;

import Model.Model;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Aurum CEO
 */
public class ObtenerImagenes {

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

    public ObtenerImagenes(String url, String idioma, String id) {
        this.url = url;
        this.idioma = idioma;
        this.id = id;
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
        int c = 1;
        String titulo, imagen1, imagen2, date, texto, href;
        articulos1 = this.conectar().select("div[class = row]");
        articulos4 = articulos1.select("a[class = dcm-internal]");
        images = articulos4.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

        for (int i = 0; i < articulos4.size(); i++) {
            System.out.println("Imagen " + c);
            for (int j = i; j < images.size(); j++) {
                titulo = images.get(j).attr("ph-data-picture-title");
                imagen1 = images.get(j).attr("src");
                imagen2 = images.get(j).attr("ph-data-picture-url");
                date = images.get(j).attr("ph-data-picture-date");
                texto = images.get(j).attr("ph-data-picture-comment");
                href = articulos4.get(i).attr("href");
                this.imprimir(titulo, imagen1, imagen2, date, texto);
                m.insertFotosCiudad(this.id, this.idioma, titulo, imagen1, imagen2, date, texto);

                break;

            }

            c++;

        }

//        m.cerrarConexion();
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

        String array[];
        array = new String[24];
        array[0] = "http://es.fifa.com/worldcup/destination/cities/city=6783/photos/index.html#2241988"; //belorizonte  2=español
        array[1] = "http://www.fifa.com/worldcup/destination/cities/city=6783/photos/index.html#2241988"; // belorizonte 1=ingles

        array[2] = "http://es.fifa.com/worldcup/destination/cities/city=1143/photos/index.html#2242073"; //brasilia 2
        array[3] = "http://www.fifa.com/worldcup/destination/cities/city=1143/photos/index.html#2242073";

        array[4] = "http://es.fifa.com/worldcup/destination/cities/city=50053/photos/index.html#2242152";//cuiba 2
        array[5] = "http://www.fifa.com/worldcup/destination/cities/city=50053/photos/index.html#2242152";//cuiba 1

        array[6] = "http://es.fifa.com/worldcup/destination/cities/city=35400/photos/index.html#2242198"; //curitiba 2
        array[7] = "http://www.fifa.com/worldcup/destination/cities/city=35400/photos/index.html#2242198";//curitiba 1

        array[8] = "http://es.fifa.com/worldcup/destination/cities/city=11693/photos/index.html#2242161"; //fortaleza 2
        array[9] = "http://www.fifa.com/worldcup/destination/cities/city=11693/photos/index.html#2242161";//fortaleza 1

        array[10] = "http://es.fifa.com/worldcup/destination/cities/city=2037/photos/index.html#2246604";//manaos 2
        array[11] = "http://www.fifa.com/worldcup/destination/cities/city=2037/photos/index.html#2246604";// 1

        array[12] = "http://es.fifa.com/worldcup/destination/cities/city=21518/photos/index.html#2242242";//natal 2
        array[13] = "http://www.fifa.com/worldcup/destination/cities/city=21518/photos/index.html#2242242";//1

        array[14] = "http://es.fifa.com/worldcup/destination/cities/city=1140/photos/index.html#2242218";//porto alegre 2
        array[15] = "http://www.fifa.com/worldcup/destination/cities/city=1140/photos/index.html#2242218";//1

        array[16] = "http://es.fifa.com/worldcup/destination/cities/city=6099/photos/index.html#2242281";//recife 2
        array[17] = "http://www.fifa.com/worldcup/destination/cities/city=6099/photos/index.html#2242281";//1

        array[18] = "http://es.fifa.com/worldcup/destination/cities/city=1141/photos/index.html#2242949";//rio de janeiro 2
        array[19] = "http://www.fifa.com/worldcup/destination/cities/city=1141/photos/index.html#2242949";//1

        array[20] = "http://es.fifa.com/worldcup/destination/cities/city=50071/photos/index.html";//salvador 2
        array[21] = "http://www.fifa.com/worldcup/destination/cities/city=50071/photos/index.html";//1

        array[22] = "http://es.fifa.com/worldcup/destination/cities/city=1047/photos/index.html#2243045";//sao paulo 2
        array[23] = "http://www.fifa.com/worldcup/destination/cities/city=1047/photos/index.html#2243045";// 1

        String[] arr = new String[]{"1", "1", "2", "2", "3", "3", "4", "4", "5", "5", "6", "6", "7", "7", "8", "8", "9", "9", "10", "10", "11", "11", "12", "12"};
        int c = 1;
        for (int i = 0; i < array.length; i++) {
            ObtenerImagenes n;
            if (c % 2 == 0) {
                n = new ObtenerImagenes(array[i], "1", arr[i] + "");

            } else {

                n = new ObtenerImagenes(array[i], "2", arr[i] + "");

            }

            System.out.println("__________________________________");
            c++;
            n.getElements();
            System.out.println("__________________________________");
            System.out.println("");
            System.out.println("");

        }
    }
}