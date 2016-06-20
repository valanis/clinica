import org.junit.Test;
import tp.aed2.factory.PacienteFactory;
import tp.aed2.obras_sociales.NullObraSocial;
import tp.aed2.obras_sociales.ObraSocial;
import tp.aed2.pacientes.Paciente;

import java.math.BigDecimal;

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
        assert paciente.getDni() == DNI;
        assert paciente.getEdad() == EDAD_MENOR;
        assert paciente.esMayorDeEdad() == false;
        assert paciente.getOs().getId() == NULL_OS;
        assert paciente.getOs().getPorcentajeDescuento() == NULL_PORCENTAJE;
        assert paciente.getOs().cubre() == false;
    }

    @Test
    public void getPacienteMenorDeEdadConObraSocial() {
        ObraSocial os = new ObraSocial(OS, PORCENTAJE);
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MENOR, os);
        assert paciente.getDni() == DNI;
        assert paciente.getEdad() == EDAD_MENOR;
        assert paciente.esMayorDeEdad() == false;
        assert paciente.getOs().getId() == OS;
        assert paciente.getOs().getPorcentajeDescuento() == PORCENTAJE;
        assert paciente.getOs().cubre() == true;
    }

    @Test
    public void getPacienteMayorDeEdadSinObraSocial() {
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MAYOR);
        assert paciente.getDni() == DNI;
        assert paciente.getEdad() == EDAD_MAYOR;
        assert paciente.esMayorDeEdad() == true;
        assert paciente.getOs().getId() == NULL_OS;
        assert paciente.getOs().getPorcentajeDescuento() == NULL_PORCENTAJE;
        assert paciente.getOs().cubre() == false;
    }

    @Test
    public void getPacienteMayorDeEdadConObraSocial() {
        ObraSocial os = new ObraSocial(OS, PORCENTAJE);
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MAYOR, os);
        assert paciente.getDni() == DNI;
        assert paciente.getEdad() == EDAD_MAYOR;
        assert paciente.esMayorDeEdad() == true;
        assert paciente.getOs().getId() == OS;
        assert paciente.getOs().getPorcentajeDescuento() == PORCENTAJE;
        assert paciente.getOs().cubre() == true;
    }
}
