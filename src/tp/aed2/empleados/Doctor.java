package tp.aed2.empleados;

import tp.aed2.consultas.Consulta;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Doctor implements IEmpleado {

    //Constantes
    private static final BigDecimal SUELDO_BASICO = new BigDecimal(50000);

    //Atributos
    private BigDecimal sueldoBasico;
    private ArrayList<Consulta> consultasAtendidas;

    //Constructor
    public Doctor() {
        this.sueldoBasico = SUELDO_BASICO;
        this.consultasAtendidas = new ArrayList<Consulta>();
    }

    //Getters
    public BigDecimal getSueldoBasico() {
        return this.sueldoBasico;
    }

    public ArrayList<Consulta> getConsultasAtendidas() {
        return consultasAtendidas;
    }

    //Métodos
    public void atender(Consulta consulta) {
        this.consultasAtendidas.add(consulta);
    }

    @Override
    public String toString() {
        String doctorToString = "[[Doctor]: \n  [Sueldo:" + this.getSueldoBasico() + "], \n" +
                "   [Consultas:\n";
        for (int i = 0; i < this.consultasAtendidas.size(); i++) {
            doctorToString += "         "+ (i+1)+':'+ this.consultasAtendidas.get(i) + "\n";
        }
        doctorToString += "]]";
        return doctorToString;
    }


}
