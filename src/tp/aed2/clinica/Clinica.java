package tp.aed2.clinica;

import org.joda.time.DateTime;
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
    private ArrayList<ObraSocial> obrasSociales;
    private Sat sat;
    private LiquidadorDeSueldo liquidadorDeSueldo;
    private DateTime fecha;


    /**
     * Constructor
     */
    private Clinica() {
        this.doctores = new ArrayList();
        this.administrativos = new ArrayList();
        this.camilleros = new ArrayList();
        this.obrasSociales = new ArrayList();
        this.sat = Sat.getInstance();
        this.liquidadorDeSueldo = LiquidadorDeSueldo.getInstance();
        this.fecha = new DateTime();
    }

    /**
     * @use Verifica si hay instanciada una clinica, sino la instancia.
     * @return Clinica
     */
    public static Clinica getInstance() {
        if(instance == null) {
            instance = new Clinica();
        }
        return instance;
    }


    /**
     * @use Obtener la fecha actual de la clinica
     * @return DateTime
     */
    public DateTime getFecha() {
        return this.fecha;
    }

    /**
     * @use Setear la fecha de la clinica.
     * @param fecha
     */
    public void setFecha(DateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * @use Agregar al empleado a la lista de doctores
     * @param doctor
     */
    public void agregarDoctor(Doctor doctor) {
        this.doctores.add(doctor);
    }

    /**
     * @use Agregar al empleado a la lista de administrativos
     * @param administrativo
     */
    public void agregarAdministrativo(Administrativo administrativo) {
        this.administrativos.add(administrativo);
    }

    /**
     * @use Agregar al empleado a la lista de camilleros,
     * a la lista de suscriptores del SAT
     * @param camillero
     */
    public void agregarCamillero(Camillero camillero) {
        this.camilleros.add(camillero);
        this.sat.agregarSuscriptor(camillero);
    }

    /**
     * @use Agregar la obra social a la lista de obra sociales
     * @param os
     */
    public void agregarObraSocial(ObraSocial os) {
        this.obrasSociales.add(os);
    }


    /**
     * @use Reiniciar la instancia de la clínica.
     */
    public void reiniciar() {
        this.doctores = new ArrayList();
        this.administrativos = new ArrayList();
        this.camilleros = new ArrayList();
        this.sat.reiniciar();
    }

    /**
     * @use Obtener un empleado administrativo al azar
     * @return Administrativo
     * @throws tp.aed2.excepciones.NoHayEmpleadoException cuando no hay administrativos
     */
    public Administrativo getAdministrativo() throws NoHayEmpleadoException {
        if(administrativos.isEmpty()) {
            throw new NoHayEmpleadoException("Administrativo");
        }
        Integer random = (int)Math.floor(Math.random()*(administrativos.size()));
        return administrativos.get(random);
    }

    /**
     * @use Devolver el Sistema de Atencion Telefonica de la Clinica
     * @return Sat
     */
    public Sat getSat() {
        return sat;
    }

    /**
     * @use Devolver el Sistema de Atencion Telefonica de la Clinica
     * @return LiquidadorDeSueldo
     */
    public LiquidadorDeSueldo getLiquidadorDeSueldo() {
        return liquidadorDeSueldo;
    }

    /**
     * @use Devolver la lista de administrativos de la Clinica
     * @return ArrayList<Administrativo>
     */
    public ArrayList<Administrativo> getAdministrativos() {
        return administrativos;
    }

    /**
     * @use Devolver la lista de Camilleros de la Clinica
     * @return ArrayList<Camillero>
     */
    public ArrayList<Camillero> getCamilleros() {
        return camilleros;
    }

    /**
     * @use Devolver la lista de Obras Sociales de la Clinica
     * @return ArrayList<ObraSocial>
     */
    public ArrayList<ObraSocial> getObrasSociales() {
        return obrasSociales;
    }

    /**
     * @use Devolver la lista de Doctores de la Clinica
     * @return ArrayList<Doctor>
     */
    public ArrayList<Doctor> getDoctores() {
        return doctores;
    }

}
