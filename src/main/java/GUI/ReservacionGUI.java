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
import java.util.ArrayList;
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

    private void contruirTabla() {
        String titulos[]={"id habitación","No. habitación","tamaño","cocineta incluida", "ocupado"};
        String informacion[][]=obtenerMatriz();
        tHabitacion=new JTable(informacion, titulos);
    }

    private String[][] obtenerMatriz() {
        ArrayList<Habitacion> miLista = habiDao.busquedaSinFiltro();
        String matrizInfo[][] = new String[miLista.size()][5];
        for (int i = 0; i < miLista.size(); i++){
            matrizInfo[i][0]=miLista.get(i).getId()+"";
            matrizInfo[i][1]=miLista.get(i).getNumHabitacion()+"";
            matrizInfo[i][2]=miLista.get(i).getTamanio()+"";
            matrizInfo[i][3]=miLista.get(i).getCocineta()+"";
            matrizInfo[i][4]=miLista.get(i).getEstadoOcupado()+"";
        }
        return matrizInfo;
    }

    private void inicializarComponentes(){
        ReservacionGUI gui = new ReservacionGUI();
        gui.setContentPane(gui.pnlMain);
        gui.setTitle("crear reservacion");
        gui.setVisible(true);
        gui.pack();
        gui.contruirTabla();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        //ReservacionGUI gui = new ReservacionGUI();
        //gui.inicializarComponentes();
        ReservacionGUI gui = new ReservacionGUI();
        gui.setContentPane(gui.pnlMain);
        gui.setTitle("crear reservacion");
        gui.setVisible(true);
        gui.pack();
        gui.contruirTabla();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
