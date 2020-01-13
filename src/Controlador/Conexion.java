package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Guzman
 */
public class Conexion {


    public Connection realizaConexion(){
            Connection conn = null;
            String urlDatabase =  "jdbc:postgresql://192.168.1.135:5432/matricula"; 
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(urlDatabase,  "munchi", "linux");
                System.out.println("La conexión se realizo sin problemas! =) ");

            } catch (Exception e) {
                System.out.println("Ocurrio un error : "+e.getMessage());
            }
            return conn;
    }
}