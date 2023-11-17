package GUI;

import DAO.HabitacionDAOImpl;
import Entidad.Habitacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservacionGUI extends JFrame {
    private ResultSet rs;
    private TableModel model;
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
    private JTextField txtIdHabitacion;

    public ReservacionGUI() {
        inicializarComponentes();
        btnBuscarHabitacion.addActionListener(new ActionListener() {
            //BUSCAR POR FILTRO
            @Override
            public void actionPerformed(ActionEvent e) {
                String tam = cbTamanio.getSelectedItem().toString();
                construirTablaFiltros(tam.charAt(0), cocinetaOpcion(cbCocineta.getSelectedItem().toString()));
            }
        });


        btnBuscarTodo.addActionListener(new ActionListener() {
            //BUSCAR SIN FILTROS APLICADOS
            @Override
            public void actionPerformed(ActionEvent e) {
                construirTablaSinFiltro();
            }
        });
        tHabitacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tHabitacion.getSelectedRow();
                System.out.println(model.getValueAt(row, 0).toString());
            }
        });
    }

    private String cocinetaOpcion(String opcion){
        String flag = "";
        if (opcion.equals("Si")){
            return flag = "incluida";
        }else{
            return flag = "no incluida";
        }
    }

    private void construirTablaFiltros(char tam, String cocineta){
        String titulos[]={"id habitación","No. habitación","tamaño","cocineta incluida", "ocupado"};
        String informacion[][]=obtenerMatrizFiltro(tam, cocineta);
        tHabitacion = new JTable(informacion, titulos);

        model = tHabitacion.getModel();
        jspBarraTabla.setViewportView(tHabitacion);
    }

    private void construirTablaSinFiltro() {
        String titulos[]={"id habitación","No. habitación","tamaño","cocineta incluida", "ocupado"};
        String informacion[][]=obtenerMatrizSinFiltro();
        tHabitacion = new JTable(informacion, titulos);
        model = tHabitacion.getModel();
        jspBarraTabla.setViewportView(tHabitacion);
    }

    private String[] titulosTabla(){
        String titulos[]={"id habitación","No. habitación","tamaño","cocineta incluida", "ocupado"};
        return titulos;
    }

    private String[][] informacion(){
        String informacion[][]=obtenerMatrizSinFiltro();
        return informacion;
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

    private void inicializarComponentes(){
        this.setContentPane(pnlMain);
        this.setTitle("crear reservacion");
        this.setVisible(true);
        this.pack();
        //this.construirTablaSinFiltro();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new ReservacionGUI().setVisible(true);
    }
}
