package DAO;

import Entidad.Habitacion;
import Entidad.Reservacion;
import modelo.Conexion;

import javax.xml.transform.Result;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservacionDAOImpl implements ReservacionDAO {
    private Conexion conn = new Conexion();
    private Connection con = conn.getConexion();
    private PreparedStatement pst;


    @Override
    public List<Reservacion> buscarTodoFecha() {
        List<Reservacion> reservaciones = new ArrayList<>();
        Reservacion reser;
        try {
            ResultSet rs = null;
            pst = con.prepareStatement("""
                    select id_reservacion, numero_habitacion, nombre, fecha_reservacion, dias_reservacion, metodo_pago 
                    	from public."RESERVACIONES"
                    		inner join public."HUESPEDES" on public."HUESPEDES".id_huesped = public."RESERVACIONES".id_huesped
                    		inner join public."HABITACIONES" on public."HABITACIONES".id_habitacion = public."RESERVACIONES".id_habitacion;                
                    """);
            rs = pst.executeQuery();
            while (rs.next()) {
                reser = new Reservacion();
                reser.setIdReservacion(Integer.parseInt(rs.getString("id_reservacion")));
                reser.setNumHabitacion(rs.getInt("numero_habitacion"));
                reser.setNombreHuesped(rs.getString("nombre"));
                reser.setFechaReserva(Date.valueOf(rs.getString("fecha_reservacion")));
                reser.setDiasReserva(rs.getInt("dias_reservacion"));
                reser.setMetodoPago(rs.getString("metodo_pago"));
                reservaciones.add(reser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservaciones;
    }

    @Override
    public List<Reservacion> buscarPorFecha(Date fecha) {
        List<Reservacion> reservaciones = new ArrayList<>();
        Reservacion reser;
        ResultSet rs;
        try {
            pst = con.prepareStatement("""
                    select id_reservacion, numero_habitacion, nombre, fecha_reservacion, dias_reservacion, metodo_pago 
                    	from public."RESERVACIONES"
                    		inner join public."HUESPEDES" on public."HUESPEDES".id_huesped = public."RESERVACIONES".id_huesped
                    		inner join public."HABITACIONES" on public."HABITACIONES".id_habitacion = public."RESERVACIONES".id_habitacion
                    		where public."RESERVACIONES".fecha_reservacion = ?;
                    """);
            pst.setDate(1, fecha);
            rs = pst.executeQuery();
            while (rs.next()) {
                reser = new Reservacion();
                reser.setIdReservacion(rs.getInt("id_reservacion"));
                reser.setNumHabitacion(rs.getInt("numero_habitacion"));
                reser.setNombreHuesped(rs.getString("nombre"));
                reser.setFechaReserva(rs.getDate("fecha_reservacion"));
                reser.setDiasReserva(rs.getInt("dias_reservacion"));
                reser.setMetodoPago(rs.getString("metodo_pago"));
                reservaciones.add(reser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservaciones;
    }

    @Override
    public void crearReservacion(int idHabitacion, int idHuesped,
                                 Date fReserva, int dReserva, char metodoPago) {
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
            pst.setDate(3, fReserva);
            pst.setInt(4, dReserva);
            pst.setString(5, String.valueOf(metodoPago));
            int afectado = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reservacion> buscarPorNombre(String nombre) {
        List<Reservacion> reservaciones = new ArrayList<>();
        Reservacion reser;
        ResultSet rs;
        try {
            pst = con.prepareStatement("""
                    select id_reservacion, numero_habitacion, nombre, fecha_reservacion, dias_reservacion, metodo_pago 
                    	from public."RESERVACIONES"
                    		inner join public."HUESPEDES" on public."HUESPEDES".id_huesped = public."RESERVACIONES".id_huesped
                    		inner join public."HABITACIONES" on public."HABITACIONES".id_habitacion = public."RESERVACIONES".id_habitacion
                    		where public."HUESPEDES".nombre = ?;
                    """);
            pst.setString(1, nombre);
            rs = pst.executeQuery();
            while (rs.next()) {
                reser = new Reservacion();
                reser.setIdReservacion(rs.getInt("id_reservacion"));
                reser.setNumHabitacion(rs.getInt("numero_habitacion"));
                reser.setNombreHuesped(rs.getString("nombre"));
                reser.setFechaReserva(rs.getDate("fecha_reservacion"));
                reser.setDiasReserva(rs.getInt("dias_reservacion"));
                reser.setMetodoPago(rs.getString("metodo_pago"));
                reservaciones.add(reser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservaciones;
    }

    protected void finalize() throws Throwable {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.finalize();
    }
}
