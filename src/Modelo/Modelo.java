package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Guzman
 */
public interface Modelo {
    public ArrayList<String> AsignaturasProfesorMod(String dni);
    
    public ArrayList<String> dniProfesoresMod();

    public ArrayList<String> alumnosProfesorMod(String dni);
    
}
