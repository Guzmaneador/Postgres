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

}
