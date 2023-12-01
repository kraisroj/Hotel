package DAO;

import Entidad.Reservacion;

import java.sql.Date;
import java.util.List;

public interface ReservacionDAO {
    List<Reservacion> buscarPorFecha(Date fecha);
    void crearReservacion(int idHabitacion, int idHuesped,
                             Date fReserva, int dReserva, char metodoPago);

    void editarReservacion(Date fecha, int dia, int idReser);

    List<Reservacion> buscarTodoFecha();

    List<Reservacion> buscarPorNombre(String nombre);
}
