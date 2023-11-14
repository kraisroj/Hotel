import DAO.HabitacionDAOImpl;
import DAO.ReservacionDAOImpl;
import GUI.ReservacionGUI;

import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        ResultSet rs;

        HabitacionDAOImpl habitacionDAO = new HabitacionDAOImpl();
        /*try {
            rs = habitacionDAO.busquedaEstado(false);
            if (rs.next()) {
                System.out.println(rs.getString("tamanio"));
                System.out.println(rs.getString("cocineta"));
                System.out.println(rs.getString("estado_ocupado"));
                System.out.println(rs.getString("numero_habitacion"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        ReservacionGUI gui = new ReservacionGUI();
    }
}
