package tp.aed2;

import org.joda.time.DateTime;
import tp.aed2.calculador.CalculadorDeSueldo;
import tp.aed2.consultas.Consulta;
import tp.aed2.empleados.Doctor;
import tp.aed2.excepciones.EmpleadoNoTrabajoException;
import tp.aed2.excepciones.SueldoNegativoException;
import tp.aed2.factory.ConsultaFactory;
import tp.aed2.factory.PacienteFactory;
import tp.aed2.obras_sociales.NullObraSocial;
import tp.aed2.obras_sociales.ObraSocial;
import tp.aed2.pacientes.Paciente;
import tp.aed2.pacientes.PacienteMayor;
import tp.aed2.pacientes.PacienteMenor;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) throws EmpleadoNoTrabajoException, SueldoNegativoException {

        CalculadorDeSueldo calculador = new CalculadorDeSueldo();

        Doctor doc = new Doctor();
        ObraSocial osde = new ObraSocial("OSDE", new BigDecimal(10));

        PacienteMayor pacienteCubiertoMayor = (PacienteMayor)PacienteFactory.getPaciente(35217075, 26, osde);
        PacienteMenor pacienteCubiertoMenor = (PacienteMenor)PacienteFactory.getPaciente(45217075, 17, osde);
        PacienteMayor pacienteNoCubiertoMayor = (PacienteMayor)PacienteFactory.getPaciente(35217075, 26);
        PacienteMenor pacienteNoCubiertoMenor = (PacienteMenor)PacienteFactory.getPaciente(45217075, 17);

        Consulta consultaPacienteCubiertoMayor = ConsultaFactory.getConsulta(pacienteCubiertoMayor);
        Consulta consultaPacienteCubiertoMenor = ConsultaFactory.getConsulta(pacienteCubiertoMenor);
        Consulta consultaPacienteNoCubiertoMayor = ConsultaFactory.getConsulta(pacienteNoCubiertoMayor);
        Consulta consultaPacienteNoCubiertoMenor = ConsultaFactory.getConsulta(pacienteNoCubiertoMenor);

        DateTime llegada = new DateTime(2016,06,18,9,0,0);
        DateTime salida = new DateTime(2016,06,18,18,0,0);

//        admin.fichar(llegada);
//        admin.fichar(salida);
    }
}
