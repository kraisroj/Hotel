package DAO;

import java.sql.ResultSet;

public interface HuespedDAO {
    boolean save(String nombre);

    boolean buscarNombre(String nombre);

    int obtenerIdHuesped(String nombre);
}
