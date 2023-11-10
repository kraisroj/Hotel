package DAO;

import modelo.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HuespedDAOImpl implements HuespedDAO{
    private Conexion conn = new Conexion();
    private Connection con = conn.getConexion();
    private PreparedStatement pst;
    @Override
    public boolean save(String nombre) {
        ResultSet rs;
        try {
            pst = con.prepareStatement("""
                    inserto into public.\"HUESPEDES\" (nombre)
                    values (?);                
                    """);
            pst.setString(1, nombre);
            rs = pst.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
