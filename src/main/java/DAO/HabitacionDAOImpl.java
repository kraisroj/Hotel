package DAO;

import modelo.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HabitacionDAOImpl implements HabitacionDAO{
    private Conexion conn = new Conexion();
    private Connection con = conn.getConexion();
    private PreparedStatement pst;
    @Override
    public ResultSet busquedaParametros(char tamanio, boolean cocineta, boolean estado) {
        String tama = String.valueOf(tamanio);
        ResultSet rs;
        try {
            pst = con.prepareStatement("""
                    select * from public.\"HABITACIONES\"
                    where public.\"HABITACIONES\".\"tamanio\" = ?
                    and public.\"HABITACIONES\".\"cocineta\" = ?
                    and public.\"HABITACIONES\".\"estado\" = ?;
                    """);
            pst.setString(1, tama);
            pst.setBoolean(2, cocineta);
            pst.setBoolean(3, estado);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    @Override
    public ResultSet busquedaEstado(boolean estado) {
        ResultSet rs;
        try {
            pst = con.prepareStatement("""
                    select * from public.\"HABITACIONES\"
                    where public.\"HABITACIONES\".\"estado\" = ?;
                    """);
            pst.setBoolean(1, estado);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
}
