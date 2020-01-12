package Controlador;

import Modelo.Modelo;
import Vista.GUI;
import Vista.Vista;
import java.util.ArrayList;

/**
 *
 * @author Guzman
 */
public class ControladorImpl implements Controlador{
    Modelo modelo;
    Vista vista;
    
    public ControladorImpl(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    @Override
    public void iniciar() {
//        Conexion conection = new Conexion();
//        conection.realizaConexion();
        GUI gui = new GUI();
        gui.setVisible(true);
    }

    @Override
    public ArrayList<String> AsignaturasProfesorCon(String dni) {
        return modelo.AsignaturasProfesorMod(dni);
    }

    @Override
    public ArrayList<String> dniProfesoresCon() {
        return modelo.dniProfesoresMod();
    }

    @Override
    public ArrayList<String> AlumnosProfesorCon(String dni) {
        return modelo.alumnosProfesorMod(dni);
    }

    @Override
    public ArrayList<String> siglaAsignaturaCon() {
        return modelo.siglasAsignaturaMod();
    }

    @Override
    public ArrayList<String> alumnosAsignaturaCon(String siglas, int curso) {
               return modelo.alumnosAsignaturaMod(siglas, curso);
    }
}