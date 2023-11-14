package GUI;

import DAO.HabitacionDAOImpl;
import Entidad.Habitacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReservacionGUI extends JFrame {
    private ResultSet rs;
    private HabitacionDAOImpl habiDao = new HabitacionDAOImpl();

    ////
    private JPanel jBotonSur;
    private JPanel pnlCenterMain;
    private JPanel pnlNorteInterior;
    private JPanel pnlCentroInterior;
    private JPanel pnlMain;
    private JButton btnCrearReservacion;
    private JButton btnSalir;
    private JComboBox cbTamanio;
    private JComboBox cbCocineta;
    private JTextField txtNombreHuesped;
    private JTable tHabitacion;
    private JButton btnBuscarHabitacion;
    private JButton btnBuscarTodo;

    public ReservacionGUI() {


        btnBuscarHabitacion.addActionListener(new ActionListener() {
            //BUSCAR POR FILTRO
            @Override
            public void actionPerformed(ActionEvent e) {

                String tam = cbTamanio.getSelectedItem().toString();
                String coci = String.valueOf(cbCocineta.getSelectedItem());
                String est = "no";
                try {
                    rs = habiDao.busquedaParametros(
                            tam.charAt(1),
                            String.valueOf(cocinetaOpcion(cbCocineta.getSelectedItem().toString()))
                            , est);
                    rs.close();
                    if (rs.next()){
                        rs.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnBuscarTodo.addActionListener(new ActionListener() {
            //BUSCAR SIN FILTRO
            @Override
            public void actionPerformed(ActionEvent e) {
                resultadosTabla();
            }
        });

        tHabitacion.addMouseListener(new MouseAdapter() {
        });
    }
    private boolean cocinetaOpcion(String opcion){
        boolean flag = false;
        switch (opcion){
            case "Si":
                flag = true;
                break;
            case "No":
                flag = false;
                break;
        }
        return flag;
    }

    private void contruirTitulosTabla(){
        String titulos[]={"id", "No.Habitacion", "tamaño", "Cocina incluida", "estado"};
    }

    private void resultadosTabla(){
        String titulos[]={"id", "No.Habitacion", "tamaño", "Cocina incluida", "estado"};
        String informacion[][]=obtenerMatriz();
        tHabitacion=new JTable(informacion, titulos);
    }

    private String[][] obtenerMatriz() {
        List<Habitacion>listaHabitaciones=habiDao.busquedaTodoDisponible();
        String matrizInfo[][] = new String[listaHabitaciones.size()][5];
        for (int i = 0; i < listaHabitaciones.size(); i++){
            matrizInfo[i][0]= String.valueOf(listaHabitaciones.get(i).getId())+"";
            matrizInfo[i][1]= String.valueOf(listaHabitaciones.get(i).getNumHabitacion())+"";
            matrizInfo[i][2]= String.valueOf(listaHabitaciones.get(i).getTamanio())+"";
            matrizInfo[i][3]= String.valueOf(listaHabitaciones.get(i).isCocineta())+"";
            matrizInfo[i][4]= String.valueOf(listaHabitaciones.get(i).isEstadoOcupado())+"";
        }
        return matrizInfo;
    }

    public static void main(String[] args) {
        ReservacionGUI gui = new ReservacionGUI();
        gui.setContentPane(gui.pnlMain);
        gui.setTitle("crear reservacion");
        gui.setVisible(true);
        gui.pack();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
