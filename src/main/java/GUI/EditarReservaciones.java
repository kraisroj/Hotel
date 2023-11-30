package GUI;

import DAO.ReservacionDAO;
import DAO.ReservacionDAOImpl;
import Entidad.Reservacion;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditarReservaciones extends JFrame {
    //IMPLEMENTACIONES
    private Calendar cld = Calendar.getInstance();
    private JDateChooser dateChooser = new JDateChooser(cld.getTime());
    private ReservacionDAO reserDAO = new ReservacionDAOImpl();

    /*VAIABLES*/
    private String user = "";

    //COMPONENTES
    private JTable jtReservaciones;
    private JPanel pnlMain;
    private JPanel jpNorte;
    private JPanel jpTabla;
    private JScrollPane jScrollTabla;
    private JPanel jpCalendar;


    public EditarReservaciones() {
        inicializarComponentes();
        //CALENDARIO
        dateChooser.setDateFormatString("dd/MM/yyyy");
        jpCalendar.add(dateChooser);

        jtReservaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);
            }
        });
    }

    public java.util.Date getDate() {
        return dateChooser.getDate();
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }

    private void inicializarComponentes() {
        //this.jtReservaciones = new JTable(informaciónTabla(this.getUser()), titulosTabla());
        this.setContentPane(pnlMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
        jScrollTabla.setViewportView(this.jtReservaciones);
        this.jtReservaciones = new JTable(construirTabla(this.user), titulosTabla());
    }

    //CONSTRUCCION DE TABLA//
    private String[] titulosTabla() {
        String titulos[] = {"ID reservacion", "numero habitacion",
                "fecha de reservación", "dias de reserva", "metodo de pago"};
        return titulos;
    }
    private String[][] construirTabla(String name) {
        String info[][] = obtenerMatrizInfo(name);
        JTable dummyT = new JTable(info, titulosTabla());
        this.jtReservaciones.setModel(dummyT.getModel());
        jScrollTabla.setViewportView(this.jtReservaciones);
        return info;
    }
    private String[][] obtenerMatrizInfo(String name) {
        List<Reservacion> miLista = reserDAO.buscarPorNombre(name);
        String matrizInfo[][] = new String[miLista.size()][5];
        for (int i = 0; i < miLista.size(); i++) {
            matrizInfo[i][0] = miLista.get(i).getIdReservacion() + "";
            matrizInfo[i][1] = miLista.get(i).getNumHabitacion() + "";
            matrizInfo[i][2] = miLista.get(i).getFechaReserva() + "";
            matrizInfo[i][3] = miLista.get(i).getDiasReserva() + "";
            matrizInfo[i][4] = miLista.get(i).getMetodoPago() + "";
        }
        return matrizInfo;
    }
    ////////////////////////////////////////////
    public static void main(String[] args) {
        EditarReservaciones edit = new EditarReservaciones();
        edit.setUser("capa");
        edit.inicializarComponentes();

    }
}
