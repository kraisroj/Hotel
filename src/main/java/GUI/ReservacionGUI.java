package GUI;

import DAO.HabitacionDAOImpl;
import Entidad.Habitacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
    private JScrollPane jspBarraTabla;
    private JButton btnBuscarTodo;

    public ReservacionGUI() {
        inicializarComponentes();
        btnBuscarHabitacion.addActionListener(new ActionListener() {
            //BUSCAR POR FILTRO
            @Override
            public void actionPerformed(ActionEvent e) {

                String tam = cbTamanio.getSelectedItem().toString();
                String coci = String.valueOf(cbCocineta.getSelectedItem());
                String est = "no";
                String informacion[][]=obtenerMatrizFiltro(tam.charAt(0), coci);

            }
        });

        tHabitacion.addMouseListener(new MouseAdapter() {
        });
    }

    private String[][] obtenerMatrizFiltro(char tam, String cocineta) {
        ArrayList<Habitacion> miLista = (ArrayList<Habitacion>) habiDao.busquedaParametros(tam, cocineta);
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

    private void construirTablaFiltros(){
        String titulos[]={"id habitación","No. habitación","tamaño","cocineta incluida", "ocupado"};

    }
    private void construirTabla() {
        String titulos[]={"id habitación","No. habitación","tamaño","cocineta incluida", "ocupado"};
        String informacion[][]=obtenerMatrizSinFiltro();
        tHabitacion=new JTable(informacion, titulos);
        jspBarraTabla.setViewportView(tHabitacion);
    }

    private String[][] obtenerMatrizSinFiltro() {
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
        this.setContentPane(pnlMain);
        this.setTitle("crear reservacion");
        this.setVisible(true);
        this.pack();
        this.construirTabla();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        new ReservacionGUI().setVisible(true);
    }
}
