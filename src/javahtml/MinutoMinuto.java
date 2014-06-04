/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahtml;

/**
 *
 * @author Aurum CEO
 */
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
public class MinutoMinuto {

    private static Document doc;
    public String url;
    Elements articulos1;
    Elements articulos4, articulos5, articulos6, articulos7, articulos8, articulos9, score1, score2;
    Elements golLocales, golVisitantes;
    Elements images;
    Model m;
    String tipo;
    String articulo;
    String idioma;
    String id;

    public MinutoMinuto(String url, String idioma) {
        this.url = url;
        this.idioma = idioma;
        if (this.idioma.equals("Ingles")) {
            m = new Model(1, "Minuto");

        } else {
            m = new Model(2, "Minuto");

        }
    }

    public Document conectar() {

        try {
            doc = Jsoup.connect(url).get();

        } catch (IOException ex) {
            System.out.println("No se conecto ERROR");
//            this.conectar();
        }
        return doc;
    }

    public void getElements() {

        articulos1 = this.conectar().select("ul.commentaries");
        articulos4 = articulos1.select("div.time");
        articulos5 = articulos1.select("div.text");

        articulos6 = this.conectar().select("section.top-section");
        articulos7 = articulos6.select("div.match-header,  div.clearfix");

        articulos8 = articulos7.select("div.home");
        articulos9 = articulos7.select("div.away");

        String golesLocales = articulos8.select("li").text();
        String golesVisitantes = articulos9.select("li").text();

        String home = articulos7.select("a h2").get(0).text();
        String visit = articulos7.select("a h2").get(1).text();

        System.out.println(home + visit);
        System.out.println("goles locals " + golesLocales + " Goles visita " + golesVisitantes);

        for (int i = articulos4.size()-1; i >=0; i--) {

            m.insertMinutoMinuto(articulos4.get(i).text().replace("'", ""), articulos5.get(i).text(), home + ":" + visit, golesLocales.equals("")?".":golesLocales, golesVisitantes.equals("")?".":golesVisitantes);
            System.out.println(articulos4.get(i).text()+ articulos5.get(i).text()+ home + ":" + visit+golesLocales + golesVisitantes);
        }
//        m.cerrarConexion();
    }

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            MinutoMinuto m = new MinutoMinuto("http://www.goal.com/es-mx/match/mexico-vs-bosnia-herzegovina/1649177/live-commentary?ICID=MP_MS_3", "Español");
            m.getElements();
            System.out.println("______________________________________________________");
            MinutoMinuto m2 = new MinutoMinuto("http://www.goal.com/en-us/match/mexico-vs-bosnia-herzegovina/1649177/live-commentary?ICID=MP_MS_3", "Ingles");
            m2.getElements();
            Thread.sleep(40000);
        }
    }
;

}
