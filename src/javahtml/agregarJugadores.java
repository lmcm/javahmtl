/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahtml;

import Model.Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Aurum CEO
 */
public class agregarJugadores {

    public static void main(String[] args) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        Model m = new Model(1, "");

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
           
            br = new BufferedReader(new InputStreamReader(
                new FileInputStream("jugadores.txt"),
                "UTF-8"));

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {

                StringTokenizer tokens = new StringTokenizer(linea, ",");

                while (tokens.hasMoreTokens()) {
                  
                    m.insertJugador(Integer.parseInt(tokens.nextToken()), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }
}
