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
    private final String SQL_SIGLAS_ASIGNATURA ="SELECT Asi.siglas FROM asignaturas AS Asi";
    
    private final String SQL_ARRAY_IDALUMNOS_PROFESOR="SELECT Rel.id_alumnos FROM relacion as Rel "+
                                                            "WHERE Rel.id_profesor = "+
                                                                "(SELECT Pro.id_profesor FROM profesores as Pro "+
                                                                " WHERE dni = ?)";
    private final String SQL_ARRAY_IDALUMNOS_ASIGNATURAS="SELECT Rel.id_alumnos FROM relacion as Rel "+
                                                            "WHERE Rel.siglas = ?";
    private final String SQL_ARRAY_IDALUMNOS_CURSO="SELECT Rel.id_alumnos FROM relacion as Rel "+
                                                    "INNER JOIN asignaturas AS Asi " +
                                                    "ON Rel.siglas = Asi.siglas " +
                                                    "WHERE Asi.curso = ?;";
    
    private final String SQL_NOMBRE_ALUMNOS_ID="SELECT Alu.nombre FROM alumnos as Alu WHERE id_alumno = ?";
    
//    private final String SQL_NOMBRE_ALUMNOS_ID_CURSO="SELECT Alu.nombre FROM alumnos as Alu WHERE id_alumno = ? AND curso =?";
    
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
        ArrayList<Integer> idAlumnos = new ArrayList<>();
        try {
            resultados.clear();
            miConexion = conexion.realizaConexion();
            miStatement = miConexion.prepareStatement(SQL_ARRAY_IDALUMNOS_PROFESOR);
            miStatement.setString(1, dni);
            ResultSet rs = miStatement.executeQuery();
            while (rs.next()) {
                idAlumnos =analizarStringSQL(rs.getString(1));
            }
            
            for (Integer idAlumno : idAlumnos) {
                miStatement = miConexion.prepareStatement(SQL_NOMBRE_ALUMNOS_ID);
                miStatement.setInt(1, idAlumno);
                ResultSet rse = miStatement.executeQuery();
                while (rse.next()) {
                    resultados.add(rse.getString(1));
                }
            }
            miConexion.close();
            
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
            for (String sinComa : sinComas) {
                for (int i = 0; i < ids.size(); i++) {
                    if(ids.get(i) == Integer.parseInt(sinComa))
                        break;
                    if(i == ids.size()-1)
                        ids.add(Integer.parseInt(sinComa));
                }
                
            }
        return ids;        
        
    }

    @Override
    public ArrayList<String> siglasAsignaturaMod() {
        try {
            if(resultados.size() != 0)    
                resultados.clear();
            
            miConexion = conexion.realizaConexion();
            miStatement = miConexion.prepareStatement(SQL_SIGLAS_ASIGNATURA);
            ResultSet rs = miStatement.executeQuery();
            while (rs.next()) {
                resultados.add(rs.getString("siglas"));
            }
            miConexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultados;
    }

    @Override
    public ArrayList<String> alumnosAsignaturaMod(String siglas, int curso) {
         ArrayList<Integer> idAlumnos = new ArrayList<>();
        try {
            resultados.clear();
            miConexion = conexion.realizaConexion();
            miStatement = miConexion.prepareStatement(SQL_ARRAY_IDALUMNOS_ASIGNATURAS);
            miStatement.setString(1, siglas);
            ResultSet rs = miStatement.executeQuery();
            while (rs.next()) {
                idAlumnos =analizarStringSQL(rs.getString(1));
            }
            
            for (Integer idAlumno : idAlumnos) {

                    miStatement = miConexion.prepareStatement(SQL_NOMBRE_ALUMNOS_ID);
                    miStatement.setInt(1, idAlumno);
                
                ResultSet rse = miStatement.executeQuery();
                while (rse.next()) {
                    resultados.add(rse.getString(1));
                }
            }
            miConexion.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ModeloImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        


        
        return resultados;
    }

    @Override
    public ArrayList<String> cursoAsignaturaMod(int i) {
        ArrayList<Integer> idAlumnos = new ArrayList<>();
        try {
            resultados.clear();
            miConexion = conexion.realizaConexion();
            miStatement = miConexion.prepareStatement(SQL_ARRAY_IDALUMNOS_CURSO);
            miStatement.setInt(1, i);
            ResultSet rs = miStatement.executeQuery();
            while (rs.next()) {
                idAlumnos =analizarStringSQL(rs.getString(1));
            }
            
            for (Integer idAlumno : idAlumnos) {

                    miStatement = miConexion.prepareStatement(SQL_NOMBRE_ALUMNOS_ID);
                    miStatement.setInt(1, idAlumno);
                
                ResultSet rse = miStatement.executeQuery();
                while (rse.next()) {
                    resultados.add(rse.getString(1));
                }
            }
            miConexion.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ModeloImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        


        
        return resultados;
    }
    
    

}