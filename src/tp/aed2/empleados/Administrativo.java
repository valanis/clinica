package tp.aed2.empleados;

import tp.aed2.pacientes.PacienteSinCoberturaMenor;

public class Administrativo extends Empleado {

    private static final Long SUELDO = Long.valueOf(7000);

    private Integer cotizaciones;

    Administrativo() {
        this.setSueldoBasico(SUELDO);
        this.cotizaciones = 0;
    }

    Administrativo(Long sueldo) {
        this.setSueldoBasico(sueldo);
        this.cotizaciones = 0;
    }

    public Long cotizarConsulta(PacienteSinCoberturaMenor paciente) {
        this.cotizaciones++;
        return Long.valueOf(paciente.getEdad() * 75);
    }

}
