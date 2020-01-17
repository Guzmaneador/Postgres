package Controlador;

import Modelo.AlumnoDAO;
import Modelo.AlumnoVO;
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
    AlumnoDAO alumnoDAO;
    
    public ControladorImpl(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.alumnoDAO = new AlumnoDAO();
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

    @Override
    public ArrayList<String> cursoAsignaturaCon(int i) {
             return modelo.cursoAsignaturaMod(i);

    }

    @Override
    public ArrayList<String> dniAlumnosCon() {
        return modelo.dniAlumnosMod();
    }


    @Override
    public AlumnoVO obtenerDatosAlumnoCon(String dni) {
        return alumnoDAO.read(dni);    
    }
}