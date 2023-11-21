package Entidad;

import java.sql.Date;

public class Reservacion {
    private Integer idReservacion;//id_reservacion
    private Integer numHabitacion; //numero_habitacion
    private String nombreHuesped; //nombre - de tabla HUESPEDES
    private Date fechaReserva;//fecha_reservacion
    private Integer diasReserva;//dias_reservacion
    private String metodoPago;//metodo_pago

    public Reservacion() {
    }

    public Reservacion(Integer idReservacion, Integer numHabitacion, String nombreHuesped, Date fechaReserva,
                       Integer diasReserva, String metodoPago){
        this.idReservacion = idReservacion;
        this.numHabitacion = numHabitacion;
        this.nombreHuesped = nombreHuesped;
        this.fechaReserva = fechaReserva;
        this.diasReserva = diasReserva;
        this.metodoPago = metodoPago;
    }

    public Integer getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(Integer idReservacion) {
        this.idReservacion = idReservacion;
    }

    public Integer getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(Integer numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public String getNombreHuesped() {
        return nombreHuesped;
    }

    public void setNombreHuesped(String nombreHuesped) {
        this.nombreHuesped = nombreHuesped;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Integer getDiasReserva() {
        return diasReserva;
    }

    public void setDiasReserva(Integer diasReserva) {
        this.diasReserva = diasReserva;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
