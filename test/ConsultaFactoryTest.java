import org.junit.Test;
import tp.aed2.consultas.Consulta;
import tp.aed2.factory.ConsultaFactory;
import tp.aed2.factory.PacienteFactory;
import tp.aed2.obras_sociales.ObraSocial;
import tp.aed2.pacientes.Paciente;

import java.math.BigDecimal;

public class ConsultaFactoryTest {

    private static final Integer EDAD_MENOR = 17;
    private static final Integer EDAD_MAYOR = 18;
    private static final Integer DNI = 1;
    private static final String OS = "OSDE";
    private static final BigDecimal PORCENTAJE = new BigDecimal(1.5);
    private static final BigDecimal CIEN = new BigDecimal(100);

    @Test
    public void getConsultaPacienteMayorSinObraSocial() {
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MAYOR);
        Consulta consulta = ConsultaFactory.getConsulta(paciente);
        assert consulta.getPaciente() == paciente;
        //El valor de la consulta tiene que ser el valor base
        assert consulta.getValor() == consulta.getValorBase();
    }

    @Test
    public void getConsultaPacienteMayorConObraSocial() {
        ObraSocial os = new ObraSocial(OS, PORCENTAJE);
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MAYOR, os);
        Consulta consulta = ConsultaFactory.getConsulta(paciente);
        BigDecimal valorBase = consulta.getValorBase();
        assert consulta.getPaciente() == paciente;
        //El valor de la consulta tiene que ser el valor base - el porcentaje de descuento de la os
        assert consulta.getValor() == valorBase.subtract(valorBase.multiply(PORCENTAJE.divide(CIEN)));
    }

    @Test
    public void getConsultaPacienteMenorSinObraSocial() {
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MENOR);
        Consulta consulta = ConsultaFactory.getConsulta(paciente);
        BigDecimal valorParaMenoresSinOS = consulta.getValorParaMenoresSinOS();
        assert consulta.getPaciente() == paciente;
        //El valor de la consulta tiene que ser el valor base para menores * la edad del paciente
        assert consulta.getValor() == valorParaMenoresSinOS.multiply(new BigDecimal(EDAD_MENOR));
    }

    @Test
    public void getConsultaPacienteMenorConObraSocial() {
        ObraSocial os = new ObraSocial(OS, PORCENTAJE);
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MENOR, os);
        Consulta consulta = ConsultaFactory.getConsulta(paciente);
        BigDecimal valorBase = consulta.getValorBase();
        assert consulta.getPaciente() == paciente;
        //El valor de la consulta tiene que ser el valor base - el porcentaje de descuento de la os
        assert consulta.getValor() == valorBase.subtract(valorBase.multiply(PORCENTAJE.divide(CIEN)));
    }
}
