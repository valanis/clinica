package tp.aed2.pacientes;

import tp.aed2.obras_sociales.IObraSocial;

public abstract class Paciente {

    //Atributos
    protected Integer dni;
    protected Integer edad;
    protected IObraSocial os;

    //Constructor
    public Paciente(IObraSocial os, Integer dni, Integer edad) {
        this.os = os;
        this.dni = dni;
        this.edad = edad;
    }

    //Getters
    public Integer getDni() {
        return this.dni;
    }
    public Integer getEdad() {
        return edad;
    }
    public IObraSocial getOs() {
        return os;
    }

    //Métodos
    abstract public Boolean esMayorDeEdad();

    @Override
    public String toString() {
        return "[DNI: "+this.getDni()+", Edad: "+this.getEdad()+ ", ObraSocial: "+this.getOs()+"]";
    }

}
