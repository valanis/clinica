package tp.aed2;

import tp.aed2.liquidador.LiquidadorDeSueldo;
import tp.aed2.consultas.Consulta;
import tp.aed2.empleados.Doctor;
import tp.aed2.excepciones.EmpleadoNoTrabajoException;
import tp.aed2.factory.ConsultaFactory;
import tp.aed2.factory.PacienteFactory;
import tp.aed2.obras_sociales.ObraSocial;
import tp.aed2.pacientes.Paciente;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        LiquidadorDeSueldo calculador = LiquidadorDeSueldo.getInstance();

        Doctor doc = new Doctor();
        ObraSocial osde = new ObraSocial("OSDE", new BigDecimal(10));

        Paciente pacienteCubiertoMayor = PacienteFactory.getPaciente(35217075, 26, osde);
        Paciente pacienteCubiertoMenor = PacienteFactory.getPaciente(45217075, 17, osde);
        Paciente pacienteNoCubiertoMayor = PacienteFactory.getPaciente(35217075, 26);
        Paciente pacienteNoCubiertoMenor = PacienteFactory.getPaciente(45217075, 17);

        Consulta consultaPacienteCubiertoMayor = ConsultaFactory.getConsulta(pacienteCubiertoMayor);
        Consulta consultaPacienteCubiertoMenor = ConsultaFactory.getConsulta(pacienteCubiertoMenor);
        Consulta consultaPacienteNoCubiertoMayor = ConsultaFactory.getConsulta(pacienteNoCubiertoMayor);
        Consulta consultaPacienteNoCubiertoMenor = ConsultaFactory.getConsulta(pacienteNoCubiertoMenor);

        System.out.println(consultaPacienteCubiertoMayor);
        System.out.println(consultaPacienteCubiertoMenor);
        System.out.println(consultaPacienteNoCubiertoMayor);
        System.out.println(consultaPacienteNoCubiertoMenor);
    }
}
