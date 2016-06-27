package tp.aed2.factory;

import tp.aed2.clinica.Clinica;
import tp.aed2.consultas.Consulta;
import tp.aed2.empleados.Administrativo;
import tp.aed2.excepciones.NoHayEmpleadoException;
import tp.aed2.pacientes.Paciente;
import tp.aed2.pacientes.PacienteMayor;
import tp.aed2.pacientes.PacienteMenor;

public class ConsultaFactory {

    /**
     *
     * @param paciente Se genera una consulta para el paciente recibido por parámetro
     * @return Si el paciente es mayor de edad, se devuelve la consulta que se obtiene de
     * getConsulta(PacienteMayor paciente)
     * Si el paciente es menor de edad, se devuelve la salida de getConsulta(PacienteMenor paciente).
     * Se devuelve null si se genera la excepción NoHayEmpleadoException - cuando no hay administrativo
     * para tomar la consulta-
     */
    public static Consulta getConsulta(Paciente paciente) {
        if (paciente.esMayorDeEdad()) {
            return getConsulta((PacienteMayor) paciente);
        } else {
            try {
                return getConsulta((PacienteMenor) paciente);
            } catch (NoHayEmpleadoException exc) {
                System.out.println(exc);
                System.out.println("No se creará la consulta");
                return null;
            }
        }
    }

    /**
     *
     * @param paciente Se recibe un paciente mayor de edad
     * @return Se devuelve una nueva consulta generada con el constructor Consulta(paciente)
     */
    public static Consulta getConsulta(PacienteMayor paciente) {
        return new Consulta(paciente);
    }

    /**
     *
     * @param paciente: Paciente menor de edad para el cual se genera la consulta.
     * @return Se devuelve una nueva consulta generada con el constructor Consulta(paciente) si
     * el paciente tiene obra social válida, sino se devuelve una consulta generada con el contructor
     * Consulta(paciente, admin) seleccionando un administrativo disponible
     * @throws NoHayEmpleadoException: Se llama cuando no hay ningún administrativo disponible
     */
    public static Consulta getConsulta(PacienteMenor paciente) throws NoHayEmpleadoException {
        if(paciente.getOs().cubre()) {
            return new Consulta(paciente);
        } else {
            Clinica clinica = Clinica.getInstance();
            Administrativo admin = clinica.getAdministrativo();
            return new Consulta(paciente, admin);
        }
    }

}
