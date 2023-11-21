package DAO;

import Entidad.Habitacion;
import modelo.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HabitacionDAOImpl implements HabitacionDAO{
    private Conexion conn = new Conexion();
    private Connection con = conn.getConexion();
    private PreparedStatement pst;

    @Override
    public List<Habitacion> busquedaParametros(char tamanio, String cocineta) {
        List<Habitacion> habitDisponibles = new ArrayList<>();
        String tama = String.valueOf(tamanio);
        Habitacion habit;
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
            pst.setString(3, "no");
            rs = pst.executeQuery();
            while (rs.next()){
                habit = new Habitacion();
                habit.setId(rs.getInt("id_habitacion"));
                habit.setNumHabitacion(rs.getInt("numero_habitacion"));
                habit.setTamanio(rs.getString("tamanio").charAt(0));
                habit.setCocineta(rs.getString("cocineta"));
                habit.setEstadoOcupado(rs.getString("estado_ocupado"));
                habitDisponibles.add(habit);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return habitDisponibles;
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
                    WHERE public.\"HABITACIONES\".\"estado_ocupado\" = ?;                  
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

    @Override
    public ArrayList<Habitacion> busquedaSinFiltro(){
        ArrayList miLista = new ArrayList();
        Habitacion habit;
        try {
            ResultSet rs = null;
            pst = con.prepareStatement("""
                    SELECT *
                    FROM public.\"HABITACIONES\"
                    WHERE public.\"HABITACIONES\".\"estado_ocupado\" = ?;  
                    """);
            pst.setString(1, "no");
            rs = pst.executeQuery();
            while (rs.next()){
                habit = new Habitacion();
                habit.setId(Integer.parseInt(rs.getString("id_habitacion")));
                habit.setNumHabitacion(Integer.parseInt(rs.getString("numero_habitacion")));
                habit.setTamanio(rs.getString("tamanio").charAt(0));
                habit.setCocineta(rs.getString("cocineta"));
                habit.setEstadoOcupado(rs.getString("estado_ocupado"));
                miLista.add(habit);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return miLista;
    }

    @Override
    public void ocuparHabitacion(int idHabitacion){
        try {
            pst = con.prepareStatement("""
                    UPDATE public."HABITACIONES"
                    	SET estado_ocupado= 'si'
                    	WHERE public."HABITACIONES".id_habitacion = ?;
                    """);
            pst.setInt(1, idHabitacion);
            int afecctado = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
