package Entidad;

public class Habitacion {
    private int id; // id_habitacion;
    private int numHabitacion; //numero_habitacion
    private char tamanio;
    private String cocineta;
    private String estadoOcupado; //estado_ocupado

    public Habitacion(int id, int numHabitacion, char tamanio, String cocineta, String estadoOcupado) {
        this.id = id;
        this.numHabitacion = numHabitacion;
        this.tamanio = tamanio;
        this.cocineta = cocineta;
        this.estadoOcupado = estadoOcupado;
    }

    public Habitacion() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public char getTamanio() {
        return tamanio;
    }

    public void setTamanio(char tamanio) {
        this.tamanio = tamanio;
    }

    public String getCocineta() {
        return cocineta;
    }

    public void setCocineta(String cocineta) {
        this.cocineta = cocineta;
    }

    public String getEstadoOcupado() {
        return estadoOcupado;
    }

    public void setEstadoOcupado(String estadoOcupado) {
        this.estadoOcupado = estadoOcupado;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", numHabitacion=" + numHabitacion +
                ", tamanio=" + tamanio +
                ", cocineta=" + cocineta +
                ", estadoOcupado=" + estadoOcupado +
                '}';
    }
}
