package tp.aed2.factory;

import tp.aed2.obras_sociales.IObraSocial;
import tp.aed2.obras_sociales.NullObraSocial;
import tp.aed2.pacientes.Paciente;
import tp.aed2.pacientes.PacienteMayor;
import tp.aed2.pacientes.PacienteMenor;

public class PacienteFactory {

    /**
     *
     * @param dni del paciente
     * @param edad del paciente
     * @return Devuelve un paciente nuevo sin obra social, con el dni y la edad pasados por parámetro
     */
    public static Paciente getPaciente(Integer dni, Integer edad) {
        return getPaciente(dni, edad, NullObraSocial.getInstance());
    }

    /**
     *
     * @param dni del paciente
     * @param edad del paciente
     * @param os del paciente, debe figurar entre las obras sociales cubiertas por la clínica
     * @return Devcuelve un nuevo pacienteMenor si la edad es menor a 18 y un pacienteMayor sino, con el dni y la obra social pasadas por parámtro
     */
    public static Paciente getPaciente(Integer dni, Integer edad, IObraSocial os) {
        if(edad < 18) {
            return new PacienteMenor(os, dni, edad);
        } else {
            return new PacienteMayor(os, dni, edad);
        }
    }

}
