package DAO;

import modelo.Conexion;

import javax.xml.transform.Result;
import java.net.ConnectException;
import java.sql.*;

public class ReservacionDAOImpl implements ReservacionDAO{
    private Conexion conn = new Conexion();
    private Connection con = conn.getConexion();
    private PreparedStatement pst;


    @Override
    public ResultSet buscarPorHuesped(int idHuesped) {
        ResultSet rs;
        try {
            pst = con.prepareStatement("""
                    select * from public.\"RESERVACIONES\"
                    where public.\"RESERVACIONES\".\"id_huesped\" = ?;
                    """);
            pst.setInt(1, idHuesped);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            pst.close();
            rs.close();
            con.close();
            finalize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    @Override
    public ResultSet buscarPorFecha(Timestamp fecha) {
        return null;
    }

    @Override
    public boolean crearReservacion(int idHabitacion, int idHuesped, Timestamp fReserva, int dReserva, char metodoPago) {
        ResultSet rs;
        try {
            pst = con.prepareStatement("""
                    insert into public.\"RESERVACIONES\" 
                    (id_habitacion, id_huesped, fecha_reservacion,
                    dias_reservacion, metodo_pago)
                    values (?, ?, ?, ?, ?);
                    """);
            pst.setInt(1, idHabitacion);
            pst.setInt(2, idHuesped);
            pst.setTimestamp(3, fReserva);
            pst.setInt(4, dReserva);
            pst.setString(5, String.valueOf(metodoPago));
            rs = pst.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            pst.close();
            rs.close();
            con.close();
            finalize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public int buscar(String nombre) throws SQLException {
        ResultSet rs;
        pst = con.prepareStatement("""
                    select id_huesped from public.\"HUESPEDES\"
                    where public.\"HUESPEDES\".\"nombre\" = ?;
                    """);
        pst.setString(1, nombre);
        rs = pst.executeQuery();

        try {
            pst.close();
            rs.close();
            con.close();
            finalize();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return rs.getInt("id_huesped");
    }

    protected void finalize() throws Throwable
    {
        try { con.close(); }
        catch (SQLException e) {
            e.printStackTrace();
        }
        super.finalize();
    }
}
