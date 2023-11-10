package DAO;

import java.sql.ResultSet;
import java.sql.Timestamp;

public interface ReservacionDAO {
    ResultSet buscarPorHuesped(int idHuesped);
    ResultSet buscarPorFecha(Timestamp fecha);

    boolean crearReservacion(int idHabitacion, int idHuesped,
                             Timestamp fReserva, int dReserva, char metodoPago);
}
