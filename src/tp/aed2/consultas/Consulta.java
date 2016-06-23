package tp.aed2.consultas;

import tp.aed2.empleados.Administrativo;
import tp.aed2.pacientes.Paciente;
import tp.aed2.pacientes.PacienteMenor;

import java.math.BigDecimal;

public class Consulta {

    //Constantes
    private static final BigDecimal VALOR_BASE = new BigDecimal(100);
    private static final BigDecimal VALOR_MENORES_SIN_OS = new BigDecimal(75);
    private static final BigDecimal CIEN = new BigDecimal(100);

    //Atributos
    private Paciente paciente;
    private BigDecimal valor;

    //Constructores
    /**
     * Crea una consulta para un paciente (con o sin cobertura),
     * calcula el valor de la consulta.
     * @param paciente
     */
    public Consulta(Paciente paciente) {
        this.paciente = paciente;
        this.valor = cotizar(paciente);
    }

    /**
     * Crea una consulta para un paciente menor de edad, sin cobertura.
     * Se llama al empleado administrativo para que cotice la consulta.
     * @param paciente necesariamente menor de edad
     * @param admin empleado administrativo.
     */
    public Consulta(PacienteMenor paciente, Administrativo admin) {
        this.paciente = paciente;
        this.valor = admin.cotizarConsulta(paciente, VALOR_MENORES_SIN_OS);
    }

    //Getters
    public Paciente getPaciente() {
        return this.paciente;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public BigDecimal getValorBase() {
        return this.VALOR_BASE;
    }

    public BigDecimal getValorParaMenoresSinOS() {
        return this.VALOR_MENORES_SIN_OS;
    }

    //Métodos
    /** Devuelve el valor de la consulta, fórmula:
     * valor_base - (valor_base * porcentaje_desc_os / 100)
     * Si el paciente no tiene OS, porcentaje_desc_os es 0,
     * y el valor de la consulta queda igual al valor_base.
     * @param paciente
     * @return el valor de la consulta
     */
    private BigDecimal cotizar(Paciente paciente) {
        BigDecimal porcentaje = paciente.getOs().getPorcentajeDescuento();
        BigDecimal descuento = VALOR_BASE.multiply(porcentaje.divide(CIEN));
        return VALOR_BASE.subtract(descuento);
    }

    @Override
    public String toString(){
        return "[[Consulta] Valor:"+this.getValor()+", Paciente:"+this.getPaciente()+"]";
    }

}
