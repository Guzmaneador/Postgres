package Modelo;

import Controlador.Conexion;
import java.sql.Array;
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
    private final String SQL_ASIGNATURAS_PROFESORES="SELECT Asi.nombre FROM asignaturas AS Asi "+
                                                        "INNER JOIN relacion AS Rel "+
                                                        "ON Asi.siglas = Rel.siglas "+
                                                        "INNER JOIN profesores AS Pro "+
                                                        "ON Pro.id_profesor = Rel.id_profesor "+
                                                        "WHERE Pro.dni = ?";
    
    private final String SQL_DNI_PROFESORES ="SELECT Pro.dni FROM profesores AS Pro";
    
    private final String SQL_ARRAY_IDALUMNOS_PROFESOR="SELECT Rel.id_alumnos FROM relacion as Rel "+
                                                            "WHERE Rel.id_profesor = "+
                                                                "(SELECT Pro.id_profesor FROM profesores as Pro "+
                                                                " WHERE dni = ?)";
    
    Conexion conexion;
    Connection miConexion ;
    PreparedStatement miStatement;
    ArrayList<String> resultados;
    

    public ModeloImpl() {
        conexion = new Conexion();
        resultados = new ArrayList<String>();
        
        
    }
    


    
    
    
    
    @Override
    public ArrayList<String> AsignaturasProfesorMod(String dni) {
        try {
            resultados.clear();
            miConexion = conexion.realizaConexion();
            miStatement = miConexion.prepareStatement(SQL_ASIGNATURAS_PROFESORES);
            miStatement.setString(1, dni);
            ResultSet rs = miStatement.executeQuery();
            while (rs.next()) {
                resultados.add(rs.getString("nombre"));
            }
            miConexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultados;
        
    }

    @Override
    public ArrayList<String> dniProfesoresMod() {
            try {
            if(resultados.size() != 0)    
                resultados.clear();
            
            miConexion = conexion.realizaConexion();
            miStatement = miConexion.prepareStatement(SQL_DNI_PROFESORES);
            ResultSet rs = miStatement.executeQuery();
            while (rs.next()) {
                resultados.add(rs.getString("dni"));
            }
            miConexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultados;
    }

    @Override
    public ArrayList<String> alumnosProfesorMod(String dni) {

        try {
            resultados.clear();
            miConexion = conexion.realizaConexion();
            miStatement = miConexion.prepareStatement(SQL_ARRAY_IDALUMNOS_PROFESOR);
            miStatement.setString(1, dni);
            ResultSet rs = miStatement.executeQuery();
            while (rs.next()) {
                analizarStringSQL(rs.getString(1));
//
//                String[] sinCorchetes = rs.getString(1).split("\\{");
//                String[] sinCorchetes2 = sinCorchetes[1].split("\\}");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return resultados;
    }
    /*Debido a que una consulta de un campo con array en Postgres debuleve un cadena
        tipo {Nº1,Nº2,N..} mediante splits la dividimos y obtenemos un array con los numero
        individualizados en cada campo del ArrayList */
    public ArrayList<Integer> analizarStringSQL(String cadena){
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(-1);
        
        String[] sinCorchetes = cadena.split("\\{");
        String[] sinCorchetes2 = sinCorchetes[1].split("\\}");
        String[] sinComas = sinCorchetes2[0].split(",");
//        if(ids.size() != 0){
            //El bucle se encarga de introducir los ids en un array sin que se repitan
            for (String sinComa : sinComas) {
                for (int i = 0; i < ids.size(); i++) {
                    if(ids.get(i) == Integer.parseInt(sinComa))
                        break;
                    if(i == ids.size()-1)
                        ids.add(Integer.parseInt(sinComa));
                }
                
            }
//        }
        return ids;        
        
    }
    
    

}