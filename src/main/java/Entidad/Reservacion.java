package Entidad;

import java.sql.Date;

public class Reservacion {
    private Integer idReservacion;//id_reservacion
    private Integer idHabitacion;//id_habitacion
    private Integer idHuesped;//id_huesped
    private Date fechaReserva;//fecha_reservacion
    private Integer diasReserva;//dias_reservacion
    private String metodoPago;//metodo_pago

    public Reservacion() {
    }

    public Reservacion(Integer idReservacion, Integer idHabitacion, Integer idHuesped, Date fechaReserva, Integer diasReserva, String metodoPago) {
        this.idReservacion = idReservacion;
        this.idHabitacion = idHabitacion;
        this.idHuesped = idHuesped;
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

    public Integer getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Integer getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(Integer idHuesped) {
        this.idHuesped = idHuesped;
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
