package tp.aed2.factory;

import tp.aed2.consultas.Consulta;
import tp.aed2.empleados.Administrativo;
import tp.aed2.pacientes.Paciente;
import tp.aed2.pacientes.PacienteMayor;
import tp.aed2.pacientes.PacienteMenor;

public class ConsultaFactory {

    public static Consulta getConsulta(Paciente paciente) {
        if(paciente.esMayorDeEdad()) {
            return getConsulta((PacienteMayor)paciente);
        } else {
            return getConsulta((PacienteMenor)paciente);
        }
    }

    public static Consulta getConsulta(PacienteMayor paciente) {
        return new Consulta(paciente);
    }

    public static Consulta getConsulta(PacienteMenor paciente) {
        if(paciente.getOs().cubre()) {
            return new Consulta(paciente);
        } else {
            //TODO: getEmpleadoAdministrativo
            Administrativo admin = new Administrativo();
            return new Consulta(paciente, admin);
        }
    }

}
