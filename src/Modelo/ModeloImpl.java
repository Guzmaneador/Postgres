package Modelo;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guzman
 */
public class ModeloImpl implements Modelo{
    private final String SQLASIGNATURASPROFESORES="SELECT Asi.nombre FROM asignaturas AS Asi "+
                                                        "INNER JOIN relacion AS Rel "+
                                                        "ON Asi.siglas = Rel.siglas "+
                                                        "INNER JOIN profesores AS Pro "+
                                                        "ON Pro.id_profesor = Rel.id_profesor "+
                                                        "WHERE Pro.dni = ?";
    
    Conexion conexion;
    Connection miConexion ;
    PreparedStatement miStatement;
    ArrayList<String> resultados;
    

    public ModeloImpl() {
        conexion = new Conexion();
        
        
    }


    
    
    
    
    @Override
    public ArrayList<String> AsignaturasProfesorMod(String dni) {
        try {
            miConexion = conexion.realizaConexion();
            miStatement = miConexion.prepareStatement(SQLASIGNATURASPROFESORES);
            miStatement.setString(1, dni);
            ResultSet rs = miStatement.executeQuery();
            while (rs.next()) {
                resultados.add(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultados;
    }
    
    

}