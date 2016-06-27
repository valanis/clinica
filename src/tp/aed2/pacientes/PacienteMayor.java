package tp.aed2.pacientes;

import tp.aed2.obras_sociales.IObraSocial;

public class PacienteMayor extends Paciente {

    /**
     *
     * @param os del paciente
     * @param dni del paciente
     * @param edad Debe ser mayor a 18
     */
    public PacienteMayor(IObraSocial os, Integer dni, Integer edad){
        super(os, dni, edad);
    }

    /**
     *
     * @return Devuelve siempre true pues se trata de un paciente mayor de edad
     */
    @Override
    public Boolean esMayorDeEdad() {
        return true;
    }

}
