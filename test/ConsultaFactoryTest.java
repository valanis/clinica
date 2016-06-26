import org.junit.After;
import org.junit.Test;
import tp.aed2.clinica.Clinica;
import tp.aed2.consultas.Consulta;
import tp.aed2.empleados.Administrativo;
import tp.aed2.factory.ConsultaFactory;
import tp.aed2.factory.PacienteFactory;
import tp.aed2.obras_sociales.ObraSocial;
import tp.aed2.pacientes.Paciente;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ConsultaFactoryTest {

    private static final Integer EDAD_MENOR = 17;
    private static final Integer EDAD_MAYOR = 18;
    private static final Integer DNI = 1;
    private static final String OS = "OSDE";
    private static final BigDecimal PORCENTAJE = new BigDecimal(1.5);
    private static final BigDecimal CIEN = new BigDecimal(100);

    @After
    public void reiniciarClinica() {
        Clinica.getInstance().reiniciar();
    }

    @Test
    public void getConsultaPacienteMayorSinObraSocial() {
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MAYOR);
        Consulta consulta = ConsultaFactory.getConsulta(paciente);
        assertEquals(consulta.getPaciente(), paciente);
        //El valor de la consulta tiene que ser el valor base
        assertEquals(consulta.getValor(), consulta.getValorBase());
    }

    @Test
    public void getConsultaPacienteMayorConObraSocial() {
        ObraSocial os = new ObraSocial(OS, PORCENTAJE);
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MAYOR, os);
        Consulta consulta = ConsultaFactory.getConsulta(paciente);
        BigDecimal valorBase = consulta.getValorBase();
        assertEquals(consulta.getPaciente(), paciente);
        //El valor de la consulta tiene que ser el valor base - el porcentaje de descuento de la os
        BigDecimal expected = valorBase.subtract(valorBase.multiply(PORCENTAJE.divide(CIEN)));
        assertEquals(consulta.getValor(), expected);
    }

    @Test
    public void getConsultaPacienteMenorSinObraSocial_conAdministrativo() {
        Clinica clinica = Clinica.getInstance();
        Administrativo admin = new Administrativo("Adm_ConsultaPacienteMenorSinObraSocial");
        clinica.agregarAdministrativo(admin);

        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MENOR);
        Consulta consulta = ConsultaFactory.getConsulta(paciente);
        BigDecimal valorParaMenoresSinOS = consulta.getValorParaMenoresSinOS();
        assertEquals(consulta.getPaciente(), paciente);
        //El valor de la consulta tiene que ser el valor base para menores * la edad del paciente
        BigDecimal expected = valorParaMenoresSinOS.multiply(new BigDecimal(EDAD_MENOR));
        assertEquals(consulta.getValor(), expected);
        //Al administrativo se le tiene que sumar una cotización
        assertEquals(admin.getCotizaciones(), BigDecimal.ONE);
    }

    @Test
    public void getConsultaPacienteMenorSinObraSocial_sinAdministrativo() {
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MENOR);
        Consulta consulta = ConsultaFactory.getConsulta(paciente);
        assertNull(consulta);
    }

    @Test
    public void getConsultaPacienteMenorConObraSocial() {
        ObraSocial os = new ObraSocial(OS, PORCENTAJE);
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MENOR, os);
        Consulta consulta = ConsultaFactory.getConsulta(paciente);
        BigDecimal valorBase = consulta.getValorBase();
        assertEquals(consulta.getPaciente(), paciente);
        //El valor de la consulta tiene que ser el valor base - el porcentaje de descuento de la os
        BigDecimal expected = valorBase.subtract(valorBase.multiply(PORCENTAJE.divide(CIEN)));
        assertEquals(consulta.getValor(), expected);
    }
}
