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
public class AlumnoDAO {
    private final String SQL_READ_ALUMNO="SELECT * FROM alumnos as Alu WHERE Alu.dni = ?";
       
    private final String SQL_UPDATE_ALUMNO = "UPDATE alumnos SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, telefonos = ? WHERE dni = ?";
    
    private final String SQL_CREATE_ALUMNO = "INSERT INTO alumnos VALUES(dni, nombre, apellidos, fecha_nacimiento, telefonos,direccion) "+
                                                                            "VALUES (?,?,?,?,?,"+
                                                                            "(?,?,?,?))";
    
    private final String SQL_ASIGNATURAS_ALUMNO = "SELECT Rel.siglas, Rel.id_alumnos FROM relacions AS Rel";
    
    Conexion conexion;
    Connection miConexion ;
    PreparedStatement miStatement;

    public AlumnoDAO() {
        conexion = new Conexion();
    }
    
    
    
    public AlumnoVO read(String dni) {
        AlumnoVO alumno = new AlumnoVO();
        try {

            
            miConexion = conexion.realizaConexion();
            miStatement = miConexion.prepareStatement(SQL_READ_ALUMNO);
            miStatement.setString(1, dni);
            ResultSet rs = miStatement.executeQuery();
            while (rs.next()) {
                alumno.setIdAlumno(rs.getInt("id_alumno"));
                alumno.setDni(rs.getString("dni"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellidos"));
                alumno.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                alumno.setTelefono((ArrayList<String>) rs.getArray("telefonos"));    
            }
            miConexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return alumno;
    }
    
    
    
    
    public void update (AlumnoVO alumno){
        try {            
            miConexion = conexion.realizaConexion();
            miStatement = miConexion.prepareStatement(SQL_UPDATE_ALUMNO);
            miStatement.setString(1, alumno.getNombre());
            miStatement.setString(2, alumno.getApellido());           
            miStatement.setDate(3, alumno.getFechaNacimiento());           
            miStatement.setArray(4, (Array) alumno.getTelefono());
            //WHERE//
            miStatement.setString(5, alumno.getDni());           
            miStatement.executeUpdate();

            miConexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void create(AlumnoVO alumno ){
        try {            
            miConexion = conexion.realizaConexion();
            miStatement = miConexion.prepareStatement(SQL_CREATE_ALUMNO);
            miStatement.setString(1, alumno.getDni());           
            miStatement.setString(2, alumno.getNombre());
            miStatement.setString(3, alumno.getApellido());           
            miStatement.setDate(4, alumno.getFechaNacimiento());           
            miStatement.setArray(5, (Array) alumno.getTelefono());
            miStatement.setString(6, alumno.getMunicipio());
            miStatement.setString(7, alumno.getCalle());
            miStatement.setInt(8, alumno.getNumero());
            miStatement.setInt(9, alumno.getCodigoPostal());
            miStatement.executeQuery();
            miConexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloImpl.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    
    /*Este metodo que revice como parametro un objetoAlumnoVO, recorre la tabla relacion
        que contiene los ids de los alumnos matriculados en cada asignatura, el metodo busca que el id de
        objeto AlumnoVo se encuentre en la lista, de ser asi inserta las siglas de esa asignatura en un Array
        q finalmente tendra las siglas de las asignatura en las que el alumno se encuentre matriculado.
        El metodo devuelve dicho Array*/
    
    public void  asignaturasMatriculado(AlumnoVO alumno){
        ArrayList<String> asignaturasMatriculado=new ArrayList<>();
            try {
            miConexion = conexion.realizaConexion();
            miStatement = miConexion.prepareStatement(SQL_READ_ALUMNO);
            ResultSet rs = miStatement.executeQuery();
            while (rs.next()) {
                String[] idsAlumnos=obtenerIdsAlumnos(rs.getString("id_alumnos"));
                for (int i = 0; i < idsAlumnos.length; i++) {
                    if(Integer.parseInt(idsAlumnos[i]) == alumno.getIdAlumno()){
                        asignaturasMatriculado.add(rs.getString("siglas"));
                        break;
                    }
                    
                }
                
            }
            miConexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        alumno.setAsignaturasMatriculado(asignaturasMatriculado);
        
    }

    private String[] obtenerIdsAlumnos(String cadena) {
        String[] sinCorchetes = cadena.split("\\{");
        String[] sinCorchetes2 = sinCorchetes[1].split("\\}");
        String[] sinComas = sinCorchetes2[0].split(",");
        return sinComas;
    }
            
    public void obtenerDireccion(String cadena,AlumnoVO alumno){
        
        String[] sinCorchetes = cadena.split("\\{");
        String[] sinCorchetes2 = sinCorchetes[1].split("\\}");
        String[] sinComas = sinCorchetes2[0].split(",");
        
        String[] municipio = sinComas[0].split("\"");
        alumno.setMunicipio(municipio[1]);
        
        String[] calle = sinComas[1].split("\"");
        alumno.setCalle(calle[1]);
        
        alumno.setNumero(Integer.parseInt(calle[2]));
        
        alumno.setCodigoPostal(Integer.parseInt(calle[3]));
        
        
    }
    

}