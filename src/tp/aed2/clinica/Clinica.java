package tp.aed2.clinica;

import tp.aed2.liquidador.LiquidadorDeSueldo;
import tp.aed2.empleados.Administrativo;
import tp.aed2.empleados.Camillero;
import tp.aed2.empleados.Doctor;
import tp.aed2.excepciones.NoHayAdministrativoException;

import java.util.ArrayList;

public class Clinica {

    private static Clinica instance;

    private ArrayList<Doctor> doctores;
    private ArrayList<Administrativo> administrativos;
    private ArrayList<Camillero> camilleros;
    private LiquidadorDeSueldo liquidadorDeSueldo;

    private Clinica() {
        this.doctores = new ArrayList();
        this.administrativos = new ArrayList();
        this.camilleros = new ArrayList();
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

    public void agregarCamillero(Camillero camillero) {
        this.camilleros.add(camillero);
    }

    public void reiniciar() {
        this.doctores = new ArrayList();
        this.administrativos = new ArrayList();
        this.camilleros = new ArrayList();
    }

    /**
     * @return un empleado administrativo al azar
     * @throws NoHayAdministrativoException cuando no hay administrativos
     */
    public Administrativo getAdministrativo() throws NoHayAdministrativoException {
        if(this.administrativos.isEmpty()) {
            throw new NoHayAdministrativoException();
        }
        Integer cantidadDeEmpleados = this.administrativos.size();
        Integer random = (int)Math.floor(Math.random()*(cantidadDeEmpleados));
        return this.administrativos.get(random);
    }

}
