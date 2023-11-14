package DAO;

import Entidad.Habitacion;
import modelo.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAOImpl implements HabitacionDAO{
    private Conexion conn = new Conexion();
    private Connection con = conn.getConexion();
    private PreparedStatement pst;


    @Override
    public ResultSet busquedaParametros(char tamanio, String cocineta, String estado) {
        String tama = String.valueOf(tamanio);
        ResultSet rs;
        try {
            pst = con.prepareStatement("""
                    select * from public.\"HABITACIONES\"
                    where public.\"HABITACIONES\".\"tamanio\" = ?
                    and public.\"HABITACIONES\".\"cocineta\" = ?
                    and public.\"HABITACIONES\".\"estado_ocupado\" = ?;
                    """);
            pst.setString(1, tama);
            pst.setString(2, cocineta);
            pst.setString(3, estado);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rs;
    }

    @Override
    public List<Habitacion> busquedaTodoDisponible() {
        List<Habitacion> habitDisponibles = new ArrayList<>();
        Habitacion habit;
        ResultSet rs;
        try {
            pst = con.prepareStatement("""
                    SELECT *
                    FROM public.\"HABITACIONES\"
                    WHERE estado_ocupado = ?;                  
                    """);
            pst.setString(1, "no");
            rs = pst.executeQuery();
            while (rs.next()){
                habit = new Habitacion();
                habit.setId(rs.getInt("id_habitacion"));
                habit.setNumHabitacion(rs.getInt("numero_habitacion"));
                habit.setTamanio(rs.getString("tamanio").charAt(1));
                habit.setCocineta(rs.getString("cocineta"));
                habit.setEstadoOcupado(rs.getString("estado_ocupado"));
                habitDisponibles.add(habit);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitDisponibles;
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
