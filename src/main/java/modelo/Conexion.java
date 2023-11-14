package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String base = "CasoHotelera";
    private final String user = "postgres";
    private final String pwd = "sit2023";
    private final String url = "jdbc:postgresql://localhost:5433/" + base;
    private Connection con = null;
    public Connection getConexion(){
        try {
            Class.forName("org.postgresql.Driver");
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

}
