package Model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis
 */
public class Model {

    String query = null;
    String bd = "2014mundial";
    String login = "root";
    String password = "";
    String url = "jdbc:mysql://localhost:3306/" + bd + "??useUnicode=true&characterEncoding=utf-8";
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Boolean aux = false;
    int idioma;
    String peticion;

    public Model(int idioma, String peticion) {
        this.idioma = idioma;
        this.peticion = peticion;

    }

    public Connection creaConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
           
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("no se puede conectar");
        }
        return conn;
    }

    public void insertNoticias(String titulo, String im1, String im2, String date, String noti) {

        CallableStatement proc = null;
        try {
            proc = creaConexion().prepareCall("CALL insertarNoticia(?,?,?,?,?,?)");
            proc.setString(1, this.idioma + "");//Tipo String
            proc.setString(2, titulo);//Tipo String
            proc.setString(3, im1);//Tipo String
            proc.setString(4, im2);//Tipo String
            proc.setString(5, date);//Tipo String
            proc.setString(6, noti);//Tipo String
//            proc.registerOutParameter("resultado", Types.VARCHAR);//Tipo String
            boolean execute = proc.execute();

//            String resultado = proc.getString("resultado");
//            System.out.println("resultado : " + resultado);
//                JOptionPane.showMessageDialog(null, resultado);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // parametros de salida
        // Se ejecuta el procedimiento almacenado
    }

    public void insertFotos(String titulo, String im1, String im2, String date, String noti) {

        CallableStatement proc = null;
        try {
            proc = creaConexion().prepareCall("CALL insertarImagenes(?,?,?,?,?,?)");
            proc.setString(1, this.idioma + "");//Tipo String
            proc.setString(2, titulo);//Tipo String
            proc.setString(3, im1);//Tipo String
            proc.setString(4, im2);//Tipo String
            proc.setString(5, date);//Tipo String
            proc.setString(6, noti);//Tipo String
//            proc.registerOutParameter("resultado", Types.VARCHAR);//Tipo String
            boolean execute = proc.execute();

//            String resultado = proc.getString("resultado");
//            System.out.println("resultado : " + resultado);
//                JOptionPane.showMessageDialog(null, resultado);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // parametros de salida
        // Se ejecuta el procedimiento almacenado
    }

    public void insertVideos(String titulo, String im1, String im2, String urlVideo, String date, String comen) {
        System.out.println("llegando el url " + urlVideo);
        CallableStatement proc = null;
        try {
            proc = creaConexion().prepareCall("CALL insertarVideo(?,?,?,?,?,?,?)");
            proc.setString(1, this.idioma + "");//Tipo String
            proc.setString(2, titulo);//Tipo String
            proc.setString(3, im1);//Tipo String
            proc.setString(4, im2);//Tipo String
            proc.setString(5, urlVideo);//Tipo String
            proc.setString(6, date);//Tipo String
            proc.setString(7, comen);//Tipo String
//            proc.registerOutParameter("resultado", Types.VARCHAR);//Tipo String
            boolean execute = proc.execute();

//            String resultado = proc.getString("resultado");
//            System.out.println("resultado : " + resultado);
//                JOptionPane.showMessageDialog(null, resultado);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // parametros de salida
        // Se ejecuta el procedimiento almacenado
    }

    public void insertFotosCiudad(String ciudad, String idioma, String titulo, String im1, String im2, String date, String comen) {

        CallableStatement proc = null;
        try {
            proc = creaConexion().prepareCall("CALL insertarImagenesCiudades(?,?,?,?,?,?,?)");
            proc.setString(1, ciudad);//Tipo String
            proc.setString(2, idioma);//Tipo String
            proc.setString(3, titulo);//Tipo String
            proc.setString(4, im1);//Tipo String
            proc.setString(5, im2);//Tipo String
            proc.setString(6, date);//Tipo String
            proc.setString(7, comen);//Tipo String
//            proc.registerOutParameter("resultado", Types.VARCHAR);//Tipo String
            boolean execute = proc.execute();

//            String resultado = proc.getString("resultado");
//            System.out.println("resultado : " + resultado);
//                JOptionPane.showMessageDialog(null, resultado);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // parametros de salida
        // Se ejecuta el procedimiento almacenado
    }

    public void insertMundiales(String idioma, String titulo, String titulo2, String im1, String im2, String text) {

        CallableStatement proc = null;
        try {
            proc = creaConexion().prepareCall("CALL insertarMundiales(?,?,?,?,?,?)");
            proc.setString(1, idioma);//Tipo String
            proc.setString(2, titulo);//Tipo String
            proc.setString(3, titulo2);//Tipo String
            proc.setString(4, im1);//Tipo String
            proc.setString(5, im2);//Tipo String
            proc.setString(6, text);//Tipo String

//            proc.registerOutParameter("resultado", Types.VARCHAR);//Tipo String
            boolean execute = proc.execute();

//            String resultado = proc.getString("resultado");
//            System.out.println("resultado : " + resultado);
//                JOptionPane.showMessageDialog(null, resultado);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // parametros de salida
        // Se ejecuta el procedimiento almacenado
    }

    public void insertFotosEquipos(String titulo, String im1, String im2, String noti, String date) {

        CallableStatement proc = null;
        try {
            proc = creaConexion().prepareCall("CALL insertarFotosEquipos(?,?,?,?,?,?)");
            proc.setString(1, this.idioma + "");//Tipo String
            proc.setString(2, titulo);//Tipo String
            proc.setString(3, im1);//Tipo String
            proc.setString(4, im2);//Tipo String
            proc.setString(5, noti);//Tipo String
            proc.setString(6, date);//Tipo String
//            proc.registerOutParameter("resultado", Types.VARCHAR);//Tipo String
            boolean execute = proc.execute();

//            String resultado = proc.getString("resultado");
//            System.out.println("resultado : " + resultado);
//                JOptionPane.showMessageDialog(null, resultado);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // parametros de salida
        // Se ejecuta el procedimiento almacenado
    }

    public void insertJugador(int id, String nombre, String ape, String edad, String posEsp, String posIng, String club) {
        ResultSet exe;
        Statement proc = null;
        try {

            proc = creaConexion().createStatement();
            System.out.println("INSERT INTO jugador (idSeleccion,nombre,apellidos,edad,posicionEsp,posIng,club)"
                    + "VALUES (" + id + ", " + nombre + "," + ape + ", " + edad + ", " + posEsp + ", " + posIng + ", " + club + ");");
            proc.executeUpdate("INSERT INTO jugador ( idSeleccion, nombre, apellidos, edad, posicionEsp, posIng, club )"
                    + " VALUES ( " + id + " , '" + nombre + "' ,  '" + ape + "' , '" + edad + "' , '" + posEsp + "' , '" + posIng + "' , '" + club + "' ) ");

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

//            String resultado = proc.getString("resultado");
//            System.out.println("resultado : " + resultado);
//                JOptionPane.showMessageDialog(null, resultado);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // parametros de salida
        // Se ejecuta el procedimiento almacenado
    }

    public void insertFavoritos(String titulo, String im1, String im2, String des, String bandera, String fecha) {

        CallableStatement proc = null;
        try {
            proc = creaConexion().prepareCall("CALL insertarFavoritos(?,?,?,?,?,?,?)");
            proc.setString(1, this.idioma + "");//Tipo String
            proc.setString(2, titulo);//Tipo String
            proc.setString(3, im1);//Tipo String
            proc.setString(4, im2);//Tipo String
            proc.setString(5, des);//Tipo String
            proc.setString(6, bandera);//Tipo String
            proc.setString(7, fecha);
//            proc.registerOutParameter("resultado", Types.VARCHAR);//Tipo String
            boolean execute = proc.execute();

//            String resultado = proc.getString("resultado");
//            System.out.println("resultado : " + resultado);
//                JOptionPane.showMessageDialog(null, resultado);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // parametros de salida
        // Se ejecuta el procedimiento almacenado
    }

    public void insertMinutoMinuto(String minuto, String detalle, String equipos, String gLocal, String gVisita) {

        CallableStatement proc = null;
        try {
            proc = creaConexion().prepareCall("CALL insertarMinutoMinuto(?,?,?,?,?,?)");
            proc.setString(1, this.idioma + "");//Tipo String
            proc.setString(2, minuto);//Tipo String
            proc.setString(3, detalle);//Tipo String
            proc.setString(4, equipos);//Tipo String
            proc.setString(5, gLocal);//Tipo String
            proc.setString(6, gVisita);//Tipo String

//            proc.registerOutParameter("resultado", Types.VARCHAR);//Tipo String
            boolean execute = proc.execute();

//            String resultado = proc.getString("resultado");
//            System.out.println("resultado : " + resultado);
//                JOptionPane.showMessageDialog(null, resultado);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // parametros de salida
        // Se ejecuta el procedimiento almacenado
    }

    public void cerrarConexion() {
        try {
            System.out.println("CERRAR CONEXION OK");
            pst.close();
            conn.close();
        } catch (SQLException ex) {
        }

    }

}
