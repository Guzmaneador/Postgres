package Modelo;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Guzman
 */
public class AlumnoVO {
    
    private String dni;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String telefono;
    private int idAlumno;
    private String municipio;
    private String calle;
    private int numero;
    private int codigoPostal;
    private ArrayList<String> asignaturasMatriculado;


    public AlumnoVO() {
    }

    public AlumnoVO(String dni, String nombre, String apellido, Date fechaNacimiento, String telefono, int idAlumno, String municipio, String calle, int numero, int codigoPostal,ArrayList<String> asignaturasMatriculado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.idAlumno = idAlumno;
        this.municipio = municipio;
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.asignaturasMatriculado=asignaturasMatriculado;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public ArrayList<String> getAsignaturasMatriculado() {
        return asignaturasMatriculado;
    }

    public void setAsignaturasMatriculado(ArrayList<String> asignaturasMatriculado) {
        this.asignaturasMatriculado = asignaturasMatriculado;
    }
    
    
    
    

}