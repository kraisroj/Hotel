package DAO;

import java.sql.ResultSet;

public interface HabitacionDAO {
    ResultSet busquedaParametros(char tamanio, boolean cocineta, boolean estado);
    ResultSet busquedaEstado(boolean estado);

}
