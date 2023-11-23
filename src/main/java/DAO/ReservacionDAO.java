package DAO;

import Entidad.Reservacion;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

public interface ReservacionDAO {
    List<Reservacion> buscarPorFecha(Date fecha);
    void crearReservacion(int idHabitacion, int idHuesped,
                             Date fReserva, int dReserva, char metodoPago);
    List<Reservacion> buscarTodoFecha();

    List<Reservacion> buscarPorNombre(String nombre);
}
