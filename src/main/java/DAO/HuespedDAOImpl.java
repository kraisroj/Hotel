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
        boolean flag = false;
        ResultSet rs;
        try {
            pst = con.prepareStatement("""
                    insert into public.\"HUESPEDES\" (nombre)
                    values (?);                
                    """);
            pst.setString(1, nombre);
            rs = pst.executeQuery();
            flag = rs.rowInserted();
            return flag;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean buscarNombre(String nombre){
        boolean flag = false;
        ResultSet rs;
        try {
            pst = con.prepareStatement("""
                    select nombre from public.\"HUESPEDES\"
                    where nombre = ?;
                    """);
            pst.setString(1, nombre);
            rs = pst.executeQuery();
            if (rs.next()==true){
                flag = rs.getString("nombre").equals(nombre);
                return flag;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int obtenerIdHuesped(String nombre) {
        ResultSet rs;
        int idHuesped = 0;
        try{
            pst = con.prepareStatement("""
                    select id_huesped from public."HUESPEDES"
                                        where nombre = ?;
                    """);
            pst.setString(1, nombre);
            rs = pst.executeQuery();
            if (rs.next())
            idHuesped = rs.getInt("id_huesped");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idHuesped;
    }
}
