package tp.aed2.clinica;

import tp.aed2.empleados.Administrativo;
import tp.aed2.empleados.Camillero;
import tp.aed2.empleados.Doctor;
import tp.aed2.excepciones.NoHayEmpleadoException;
import tp.aed2.liquidador.LiquidadorDeSueldo;
import tp.aed2.obras_sociales.ObraSocial;
import tp.aed2.sat.Sat;

import java.util.ArrayList;

public class Clinica {

    private static Clinica instance;

    private ArrayList<Doctor> doctores;
    private ArrayList<Administrativo> administrativos;
    private ArrayList<Camillero> camilleros;
    private ArrayList<ObraSocial> obraSocialsHabilitadas;
    private Sat sat;
    private LiquidadorDeSueldo liquidadorDeSueldo;


    private Clinica() {
        this.doctores = new ArrayList();
        this.administrativos = new ArrayList();
        this.camilleros = new ArrayList();
        this.obraSocialsHabilitadas = new ArrayList();
        this.sat = Sat.getInstance();
        this.liquidadorDeSueldo = LiquidadorDeSueldo.getInstance();
    }

    public static Clinica getInstance() {
        if(instance == null) {
            instance = new Clinica();
        }
        return instance;
    }

    public void agregarDoctor(Doctor doctor) {
        this.doctores.add(doctor);
    }

    public void agregarAdministrativo(Administrativo administrativo) {
        this.administrativos.add(administrativo);
    }

    /**
     * @param camillero
     * Agregar al empleado a la lista de camilleros,
     * a la lista de suscriptores del SAT
     */
    public void agregarCamillero(Camillero camillero) {
        this.camilleros.add(camillero);
        this.sat.agregarSuscriptor(camillero);
    }

    public void reiniciar() {
        this.doctores = new ArrayList();
        this.administrativos = new ArrayList();
        this.camilleros = new ArrayList();
        this.sat.reiniciar();
    }

    /**
     * @return un empleado administrativo al azar
     * @throws tp.aed2.excepciones.NoHayEmpleadoException cuando no hay administrativos
     */
    public Administrativo getAdministrativo() throws NoHayEmpleadoException {
        if(administrativos.isEmpty()) {
            throw new NoHayEmpleadoException("Administrativo");
        }
        Integer random = (int)Math.floor(Math.random()*(administrativos.size()));
        return administrativos.get(random);
    }

}
