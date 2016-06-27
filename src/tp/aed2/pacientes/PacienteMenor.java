package tp.aed2.pacientes;

import tp.aed2.obras_sociales.IObraSocial;

public class PacienteMenor extends Paciente {

    /**
     *
     * @param os del paciente
     * @param dni del paciente
     * @param edad Debe ser menor a 18
     */
    public PacienteMenor(IObraSocial os, Integer dni, Integer edad) {
        super(os, dni, edad);
    }

    /**
     *
     * @return Devuelve siempre false pues se trata de un paciente menor de edad
     */
    @Override
    public Boolean esMayorDeEdad() {
        return false;
    }

}
