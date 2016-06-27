package tp.aed2.empleados;

import org.joda.time.DateTime;
import tp.aed2.fichaje.Fichaje;
import tp.aed2.pacientes.PacienteMenor;
import java.math.BigDecimal;

public class Administrativo implements IEmpleado {

    //Constantes
    private static final BigDecimal SUELDO = new BigDecimal(7000);

    //Atributos
    private String id;
    private BigDecimal sueldoBasico;
    private BigDecimal cotizaciones;
    private Fichaje fichaje;

    //Constructor

    /**
     * @use Genera un nuevo Administrativo a partir de un ID
     * @param id
     */
    public Administrativo(String id) {
        this.id = id;
        this.sueldoBasico = SUELDO;
        this.cotizaciones = BigDecimal.ZERO;
        this.fichaje = new Fichaje();
    }

    //Getters

    /**
     * @return Obtiene cotizaciones adicionales al sueldo basico del Administrativo
     */
    public BigDecimal getCotizaciones() {
        return cotizaciones;
    }

    /**
     * @return Obtiene la instancia de Fichaje correspondiente al Administrativo
     */
    public Fichaje getFichaje() {
        return this.fichaje;
    }

    /**
     * @return Obtiene el valor del sueldo básico del Administrador
     */
    public BigDecimal getSueldoBasico() {
        return this.sueldoBasico;
    }

    /**
     * @return Obtiene el Id del Administrativo
     */
    public String getId() {
        return id;
    }

    //Métodos

    /**
     * @param paciente: Se recibe un paciente menor de edad sin obra social
     * @param valor: Valor base de la consulta
     * @return En base a la información del paciente y del valor de la consulta, realiza la cotización de la consulta
     */
    public BigDecimal cotizarConsulta(PacienteMenor paciente, BigDecimal valor) {
        System.out.println(this + " cotizando consulta.");
        this.cotizaciones = this.cotizaciones.add(BigDecimal.ONE);
        return valor.multiply(new BigDecimal(paciente.getEdad()));
    }

    /**
     * @param fecha: Tiene que coincidir el mes actual con el de la fecha a fichar. Se recibe una fecha que se almacena como horario de entrada
     */
        public void ficharEntrada(DateTime fecha) {
        this.fichaje.ficharEntrada(fecha);
    }

    /**
     * @param fecha: Tiene que coincidir el mes actual con el de la fecha a fichar.
     *             Se recibe una fecha que se almacena como horario de salida
     */
    public void ficharSalida(DateTime fecha) {
        this.fichaje.ficharSalida(fecha);
    }

    /**
     * @return Convierte el ID del Administrativo a String
     */
    public String toString() {
        return "[Administrativo: "+this.getId()+"]";
    }
}
