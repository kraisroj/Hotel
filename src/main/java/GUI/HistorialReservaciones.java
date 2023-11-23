package GUI;

import DAO.ReservacionDAO;
import DAO.ReservacionDAOImpl;
import Entidad.Reservacion;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistorialReservaciones extends JFrame{
    private ReservacionDAO reserDAO = new ReservacionDAOImpl();;
    private JPanel jpMain;
    private JPanel jpNorte;
    private JPanel jpCalen;
    private JTable tReservaciones;
    private JButton btnBuscarReservaciones;
    private JScrollPane jspBarraTabla;
    private JButton btnBuscarNombre;
    private JTextField txtNombreHuesped;
    private JButton btnTodosFecha;

    Calendar cld = Calendar.getInstance();
    JDateChooser dateChos = new JDateChooser(cld.getTime());

    //titulos para JTable
    String titulos[] = {"ID reservacion", "numero habitacion", "nombre huesped",
            "fecha de reservación", "dias de reserva", "metodo de pago"};
    public HistorialReservaciones(){
        setContentPane(jpMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        construirTablaTodo();
        pack();

        //calendario
        dateChos.setDateFormatString("dd/MM/yyyy");
        jpCalen.add(dateChos);
        btnBuscarReservaciones.addActionListener(new ActionListener() {
            //BUSCAR RESERVACIONES POR FECHA
            @Override
            public void actionPerformed(ActionEvent e) {
                construirTablaFiltroFecha(new java.sql.Date(getDate().getTime()));
            }
        });
        tReservaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
        btnTodosFecha.addActionListener(new ActionListener() {
            //MOSTRAR TODAS LAS RESERVACIONES
            @Override
            public void actionPerformed(ActionEvent e) {
                construirTablaTodo();
            }
        });
        btnBuscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                construirTablaFiltroNombre(txtNombreHuesped.getText());
            }
        });
    }
    public Date getDate(){
        return dateChos.getDate();
    }

    private void construirTablaFiltroFecha(Date fecha){
        String informacion[][] = obtenerMatrizPorFecha(fecha);
        this.tReservaciones = new JTable(informacion, titulos);
        jspBarraTabla.setViewportView(this.tReservaciones);
    }

    private void construirTablaFiltroNombre(String name){
        String informacion[][] = obtenerMatrizPorNombre(name);
        this.tReservaciones = new JTable(informacion, titulos);
        jspBarraTabla.setViewportView(this.tReservaciones);
    }
    private void construirTablaTodo(){
        String informacion[][] = obtenerMatrizTodos();
        this.tReservaciones = new JTable(informacion, titulos);
        jspBarraTabla.setViewportView(this.tReservaciones);
    }

    private String[][] obtenerMatrizPorNombre(String name){
        List<Reservacion> miLista = reserDAO.buscarPorNombre(name);
        String matrizInfo[][] = new String[miLista.size()][6];
        for (int i = 0; i < miLista.size(); i++){
            matrizInfo[i][0] = miLista.get(i).getIdReservacion()+"";
            matrizInfo[i][1] = miLista.get(i).getNumHabitacion()+"";
            matrizInfo[i][2] = miLista.get(i).getNombreHuesped()+"";
            matrizInfo[i][3] = miLista.get(i).getFechaReserva()+"";
            matrizInfo[i][4] = miLista.get(i).getDiasReserva()+"";
            matrizInfo[i][5] = miLista.get(i).getMetodoPago()+"";
        }
        return matrizInfo;
    }
    private String[][] obtenerMatrizTodos(){
        List<Reservacion> miLista = reserDAO.buscarTodoFecha();
        String matrizInfo[][] = new String[miLista.size()][6];
        for (int i = 0; i < miLista.size(); i++){
            matrizInfo[i][0] = miLista.get(i).getIdReservacion()+"";
            matrizInfo[i][1] = miLista.get(i).getNumHabitacion()+"";
            matrizInfo[i][2] = miLista.get(i).getNombreHuesped()+"";
            matrizInfo[i][3] = miLista.get(i).getFechaReserva()+"";
            matrizInfo[i][4] = miLista.get(i).getDiasReserva()+"";
            matrizInfo[i][5] = miLista.get(i).getMetodoPago()+"";
        }
        return matrizInfo;
    }
    private String[][] obtenerMatrizPorFecha(Date fecha){
        List<Reservacion> miLista = reserDAO.buscarPorFecha((java.sql.Date) fecha);
        String matrizInfo[][] = new String[miLista.size()][6];
        for (int i = 0; i < miLista.size(); i++){
            matrizInfo[i][0] = miLista.get(i).getIdReservacion()+"";
            matrizInfo[i][1] = miLista.get(i).getNumHabitacion()+"";
            matrizInfo[i][2] = miLista.get(i).getNombreHuesped()+"";
            matrizInfo[i][3] = miLista.get(i).getFechaReserva()+"";
            matrizInfo[i][4] = miLista.get(i).getDiasReserva()+"";
            matrizInfo[i][5] = miLista.get(i).getMetodoPago()+"";
        }
        return matrizInfo;
    }
    public static void main(String[] args) {
        new HistorialReservaciones().setVisible(true);
    }
}
