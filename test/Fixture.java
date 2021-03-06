import org.joda.time.DateTime;
import tp.aed2.clinica.Clinica;
import tp.aed2.consultas.Consulta;
import tp.aed2.empleados.Administrativo;
import tp.aed2.factory.ConsultaFactory;
import tp.aed2.factory.PacienteFactory;
import tp.aed2.fichaje.Fichaje;
import tp.aed2.obras_sociales.ObraSocial;
import tp.aed2.pacientes.Paciente;
import tp.aed2.viajes.Viaje;

import java.math.BigDecimal;

public class Fixture {

    //CREACIÓN DE OBJETOS PARA USAR SOLAMENTE EN TESTS

    private static final Integer HORA_ENTRADA = Fichaje.getHoraEntrada();
    private static final Integer HORA_SALIDA = Fichaje.getHoraSalida();
    private static final Integer EDAD_MENOR = 17;
    private static final Integer EDAD_MAYOR = 18;
    private static final Integer DNI = 1;
    private static final String OS = "OSDE";
    private static final BigDecimal PORCENTAJE = new BigDecimal(1.5);
    private static final BigDecimal VALOR_VIAJE = BigDecimal.valueOf(20);

    public static Administrativo getAdministrativoSinFaltas() {
        Administrativo admin = new Administrativo("Admin");
        Fichaje fichaje = admin.getFichaje();
        Integer mes = fichaje.getMes();
        Integer diasEnElMes = fichaje.getCantidadDeDias();

        for(int dia = 1; dia <= diasEnElMes; dia++) {
            DateTime entrada = new DateTime(2016, mes, dia, HORA_ENTRADA, 0, 0);
            DateTime salida = new DateTime(2016, mes, dia, HORA_SALIDA, 0, 0);
            admin.ficharEntrada(entrada);
            admin.ficharSalida(salida);
        }

        return admin;
    }

    public static Administrativo getAdministrativoConUnaFalta() {
        Administrativo admin = new Administrativo("Admin");
        Fichaje fichaje = admin.getFichaje();
        Integer mes = fichaje.getMes();
        Integer diasEnElMes = fichaje.getCantidadDeDias();

        for(int dia = 2; dia <= diasEnElMes; dia++) {
            DateTime entrada = new DateTime(2016, mes, dia, HORA_ENTRADA, 0, 0);
            DateTime salida = new DateTime(2016, mes, dia, HORA_SALIDA, 0, 0);
            admin.ficharEntrada(entrada);
            admin.ficharSalida(salida);
        }

        return admin;
    }

    public static Consulta getConsultaMayorCubierto() {
        ObraSocial os = new ObraSocial(OS, PORCENTAJE);
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MAYOR, os);
        return ConsultaFactory.getConsulta(paciente);
    }

    public static Consulta getConsultaMayorNoCubierto() {
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MAYOR);
        return ConsultaFactory.getConsulta(paciente);
    }

    public static Consulta getConsultaMenorCubierto() {
        ObraSocial os = new ObraSocial(OS, PORCENTAJE);
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MENOR, os);
        return ConsultaFactory.getConsulta(paciente);
    }

    public static Consulta getConsultaMenorNoCubierto(Administrativo admin) {
        Clinica clinica = Clinica.getInstance();
        clinica.agregarAdministrativo(admin);
        Paciente paciente = PacienteFactory.getPaciente(DNI, EDAD_MENOR);
        return ConsultaFactory.getConsulta(paciente);
    }

    public static Viaje getViajeDeUnPacienteEnDiaDeSemana() {
        //20/06/2015: lunes
        DateTime dateTime = new DateTime(2016,6,20,10,10,05);
        return new Viaje(1, new BigDecimal(100), dateTime);
    }

    public static Viaje getViajeDeUnPacienteEnFinDeSemana() {
        //25/06/2015: sábado
        DateTime dateTime = new DateTime(2016,6,25,10,10,05);
        return new Viaje(1, new BigDecimal(100), dateTime);
    }

    public static Viaje getViajeDeDosPacientesEnDiaDeSemana() {
        //20/06/2015: lunes
        DateTime dateTime = new DateTime(2016,6,20,10,10,05);
        return new Viaje(2, new BigDecimal(100), dateTime);
    }

    public static Viaje getViajeDeDosPacientesEnFinDeSemana() {
        //25/06/2015: sábado
        DateTime dateTime = new DateTime(2016,6,25,10,10,05);
        return new Viaje(2, new BigDecimal(100), dateTime);
    }

}
