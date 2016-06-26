package tp.aed2.empleados;

import tp.aed2.consultas.Consulta;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Doctor implements IEmpleado {

    //Constantes
    private static final BigDecimal SUELDO_BASICO = new BigDecimal(50000);

    //Atributos
    private String id;
    private BigDecimal sueldoBasico;
    private ArrayList<Consulta> consultasAtendidas;

    //Constructor
    public Doctor(String id) {
        this.id = id;
        this.sueldoBasico = SUELDO_BASICO;
        this.consultasAtendidas = new ArrayList<Consulta>();
    }

    //Getters
    public BigDecimal getSueldoBasico() {
        return this.sueldoBasico;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Consulta> getConsultasAtendidas() {
        return consultasAtendidas;
    }

    //Métodos
    public void atender(Consulta consulta) {
        this.consultasAtendidas.add(consulta);
        System.out.println("Consulta atendida.");
    }

    @Override
    public String toString() {
        return "[Doctor: "+this.getId()+"]";
    }

}
