package DAO;

import Entidad.Habitacion;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface HabitacionDAO {
    List<Habitacion> busquedaParametros(char tamanio, String cocineta);
    List<Habitacion> busquedaTodoDisponible();

    ArrayList<Habitacion> busquedaSinFiltro();

    void ocuparHabitacion(int idHabitacion);
}
