package DAO;

import Entidad.Habitacion;

import java.sql.ResultSet;
import java.util.List;

public interface HabitacionDAO {
    ResultSet busquedaParametros(char tamanio, String cocineta, String estado);
    List<Habitacion> busquedaTodoDisponible();

}
