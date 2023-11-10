import DAO.HabitacionDAOImpl;
import DAO.ReservacionDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        ReservacionDAOImpl reservacionDAO = new ReservacionDAOImpl();
        ResultSet rs;
        /*try {
            rs = reservacionDAO.buscarPorHuesped(reservacionDAO.buscar("gerardo gonzalez rojas"));
            System.out.println(rs.getString("id_habitacion"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        HabitacionDAOImpl habitacionDAO = new HabitacionDAOImpl();
        try {
            rs = habitacionDAO.busquedaEstado(false);
            System.out.println(rs.getString("numero_habitacon"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
