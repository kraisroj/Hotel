package GUI;

import DAO.ReservacionDAO;
import DAO.ReservacionDAOImpl;
import Entidad.Reservacion;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class EditarReservaciones extends JFrame {
    //IMPLEMENTACIONES
    private Calendar cld = Calendar.getInstance();
    private JDateChooser dateChooser = new JDateChooser(cld.getTime());
    private ReservacionDAO reserDAO = new ReservacionDAOImpl();
    private Date sqlDate;
    /*VAIABLES*/
    private String user = "";

    //COMPONENTES
    private JTable jtReservaciones = new JTable(construirTabla(this.user), titulosTabla());;
    private JPanel pnlMain;
    private JPanel jpNorte;
    private JPanel jpTabla;
    private JScrollPane jScrollTabla;
    private JPanel jpCalendar;
    private JComboBox cbDias;


    public EditarReservaciones() {
        inicializarComponentes();
        //CALENDARIO
        dateChooser.setDateFormatString("dd/MM/yyyy");
        jpCalendar.add(dateChooser);

        jtReservaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                sqlDate = new java.sql.Date(getDate().getTime());
                int rowReservacion = jtReservaciones.getSelectedRow();
                int idReser = Integer.valueOf(jtReservaciones.getValueAt(rowReservacion, 0).toString());
                System.out.println(idReser);
                switch (JOptionPane.showOptionDialog(
                        null, "¿Seguro de actualizar la reservación?",
                        "Actualizacion de reservacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, jOptiones(), 0)){
                    case 0:
                        reserDAO.editarReservacion(sqlDate, cbDias.getSelectedIndex(), idReser);
                        construirTabla(user);
                        break;
                    case 1:
                        break;
                }

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

        this.setContentPane(pnlMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
        construirTabla(this.user);
        jScrollTabla.setViewportView(this.jtReservaciones);


    }

    //OPCIONES DE JOPTIONPANE
    private String[] jOptiones(){
        String[] opciones = {"si", "no"};
        return opciones;
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
