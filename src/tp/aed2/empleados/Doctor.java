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

    /**
     *
     * @param id Genera un nuevo Doctor a partir del ID pasado por parámetro y le asigna el sueldo básico de Doctor
     *           y una lista vacía para las consultas atendidas
     */
    public Doctor(String id) {
        this.id = id;
        this.sueldoBasico = SUELDO_BASICO;
        this.consultasAtendidas = new ArrayList<Consulta>();
    }

    //Getters

    /**
     *
     * @return Devuelve un BigDecimal con el sueldo básico del Doctor
     */
    public BigDecimal getSueldoBasico() {
        return this.sueldoBasico;
    }

    /**
     *
     * @return Devuelve un string con el ID del Doctor
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return Devuelve una lista con las consultas taendidas por el Doctor
     */
    public ArrayList<Consulta> getConsultasAtendidas() {
        return consultasAtendidas;
    }

    //Métodos

    /**
     *
     * @param consulta: Se agrega la consulta a las consultas atendidas por el Doctor
     */
    public void atender(Consulta consulta) {
        this.consultasAtendidas.add(consulta);
        System.out.println("Consulta atendida.");
    }

    /**
     *
     * @return Devuelve un String con el ID del Doctor
     */
    @Override
    public String toString() {
        return "[Doctor: "+this.getId()+"]";
    }

}
