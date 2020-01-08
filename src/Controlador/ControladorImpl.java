package Controlador;

import Modelo.Modelo;
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
        Conexion conection = new Conexion();
        conection.realizaConexion();
    }

    @Override
    public ArrayList<String> AsignaturasProfesorCon(String dni) {
        return modelo.AsignaturasProfesorMod(dni);
    }
}