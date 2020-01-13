package Controlador;

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

}
