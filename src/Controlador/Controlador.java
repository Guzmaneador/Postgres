package Controlador;

import Modelo.AlumnoVO;
import java.util.ArrayList;

/**
 *
 * @author Guzman
 */
public interface Controlador {

    public void iniciar();
    
    public ArrayList<String> AsignaturasProfesorCon(String dni);
    
    public ArrayList<String> dniProfesoresCon();

    public ArrayList<String> AlumnosProfesorCon(String dni);

    public ArrayList<String> siglaAsignaturaCon();

    public ArrayList<String> alumnosAsignaturaCon(String siglas, int curso);

    public ArrayList<String> cursoAsignaturaCon(int i);

    public ArrayList<String> dniAlumnosCon();

    public AlumnoVO obtenerDatosAlumnoCon(String dni);

}
