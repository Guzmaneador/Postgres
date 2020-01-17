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

    public ArrayList<String> siglasAsignaturaMod();

    public ArrayList<String> alumnosAsignaturaMod(String siglas, int curso);

    public ArrayList<String> cursoAsignaturaMod(int i);

    public ArrayList<String> dniAlumnosMod();
    
}
