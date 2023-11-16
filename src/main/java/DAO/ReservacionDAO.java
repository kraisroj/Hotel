package DAO;

import Entidad.Reservacion;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

public interface ReservacionDAO {
    ResultSet buscarPorHuesped(int idHuesped);
    List<Reservacion> buscarPorFecha(Date fecha);

    boolean crearReservacion(int idHabitacion, int idHuesped,
                             Date fReserva, int dReserva, char metodoPago);
}
