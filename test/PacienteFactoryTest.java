import org.junit.Test;
import tp.aed2.factory.PacienteFactory;
import tp.aed2.obras_sociales.NullObraSocial;
import tp.aed2.obras_sociales.ObraSocial;
import tp.aed2.pacientes.Paciente;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PacienteFactoryTest {

    private static final Integer EDAD_MENOR = 17;
    private static final Integer EDAD_MAYOR = 18;
    private static final Integer DNI = 1;
    private static final String OS = "OSDE";
    private static final String NULL_OS = NullObraSocial.getNullId();
    private static final BigDecimal PORCENTAJE = new BigDecimal(1.5);
    private static final BigDecimal NULL_PORCENTAJE = NullObraSocial.getNullPorcentaje();

    @Test
    public void getPacienteMenorDeEdadSinObraSocial() {
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MENOR);
        assertEquals(paciente.getDni(), DNI);
        assertEquals(paciente.getEdad(), EDAD_MENOR);
        assertEquals(paciente.esMayorDeEdad(), false);
        assertEquals(paciente.getOs().getId(), NULL_OS);
        assertEquals(paciente.getOs().getPorcentajeDescuento(), NULL_PORCENTAJE);
        assertEquals(paciente.getOs().cubre(), false);
    }

    @Test
    public void getPacienteMenorDeEdadConObraSocial() {
        ObraSocial os = new ObraSocial(OS, PORCENTAJE);
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MENOR, os);
        assertEquals(paciente.getDni(), DNI);
        assertEquals(paciente.getEdad(), EDAD_MENOR);
        assertEquals(paciente.esMayorDeEdad(), false);
        assertEquals(paciente.getOs().getId(), OS);
        assertEquals(paciente.getOs().getPorcentajeDescuento(), PORCENTAJE);
        assertEquals(paciente.getOs().cubre(), true);
    }

    @Test
    public void getPacienteMayorDeEdadSinObraSocial() {
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MAYOR);
        assertEquals(paciente.getDni(), DNI);
        assertEquals(paciente.getEdad(), EDAD_MAYOR);
        assertEquals(paciente.esMayorDeEdad(), true);
        assertEquals(paciente.getOs().getId(), NULL_OS);
        assertEquals(paciente.getOs().getPorcentajeDescuento(), NULL_PORCENTAJE);
        assertEquals(paciente.getOs().cubre(), false);
    }

    @Test
    public void getPacienteMayorDeEdadConObraSocial() {
        ObraSocial os = new ObraSocial(OS, PORCENTAJE);
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MAYOR, os);
        assertEquals(paciente.getDni(), DNI);
        assertEquals(paciente.getEdad(), EDAD_MAYOR);
        assertEquals(paciente.esMayorDeEdad(), true);
        assertEquals(paciente.getOs().getId(), OS);
        assertEquals(paciente.getOs().getPorcentajeDescuento(), PORCENTAJE);
        assertEquals(paciente.getOs().cubre(), true);
    }
}
