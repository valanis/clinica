package tp.aed2.factory;

import tp.aed2.clinica.Clinica;
import tp.aed2.consultas.Consulta;
import tp.aed2.empleados.Administrativo;
import tp.aed2.excepciones.NoHayAdministrativoException;
import tp.aed2.pacientes.Paciente;
import tp.aed2.pacientes.PacienteMayor;
import tp.aed2.pacientes.PacienteMenor;

public class ConsultaFactory {

    public static Consulta getConsulta(Paciente paciente) {
        if (paciente.esMayorDeEdad()) {
            return getConsulta((PacienteMayor) paciente);
        } else {
            try {
                return getConsulta((PacienteMenor) paciente);
            } catch (NoHayAdministrativoException exc) {
                System.out.println(exc);
                System.out.println("No se creará la consulta");
                return null;
            }
        }
    }

    public static Consulta getConsulta(PacienteMayor paciente) {
        return new Consulta(paciente);
    }

    public static Consulta getConsulta(PacienteMenor paciente) throws NoHayAdministrativoException {
        if(paciente.getOs().cubre()) {
            return new Consulta(paciente);
        } else {
            Clinica clinica = Clinica.getInstance();
            Administrativo admin = clinica.getAdministrativo();
            return new Consulta(paciente, admin);
        }
    }

}
