package tp.aed2.empleados;

import tp.aed2.sat.Emergencia;
import tp.aed2.sat.IEmergenciaSuscriptor;
import tp.aed2.viajes.Viaje;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Camillero implements IEmpleado, IEmergenciaSuscriptor {

    //Constantes
    private static final BigDecimal SUELDO_BASICO = new BigDecimal(10000);

    //Atributos
    BigDecimal sueldoBasico;
    ArrayList<Viaje> viajesRealizados;

    //Constructor
    public Camillero() {
        this.sueldoBasico = SUELDO_BASICO;
        this.viajesRealizados = new ArrayList<Viaje>();
    }

    //Getters
    public BigDecimal getSueldoBasico() {
        return this.sueldoBasico;
    }

    public ArrayList<Viaje> getViajesRealizados() {
        return this.viajesRealizados;
    }

    //Métodos
    @Override
    public void update(Emergencia emergencia) throws InterruptedException {
        Random random = new Random();
        Thread.currentThread().sleep(random.nextInt(5000));
        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        if(!emergencia.fueAtendida()){
            emergencia.marcarComoAtendida();
            this.viajesRealizados.add(emergencia.getViaje());
        }

        lock.unlock();
    }
}
