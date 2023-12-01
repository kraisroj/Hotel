package GUI;

import DAO.HabitacionDAOImpl;
import DAO.ReservacionDAO;
import DAO.ReservacionDAOImpl;
import Entidad.Reservacion;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistorialReservaciones extends JFrame {
    private Calendar cld = Calendar.getInstance();
    private JDateChooser dateChos = new JDateChooser(cld.getTime());
    private ReservacionDAO reserDAO = new ReservacionDAOImpl();
    private HabitacionDAOImpl habiDAO = new HabitacionDAOImpl();

    //COMPONENTES DEL FRAME
    private JPanel jpMain;
    private JPanel jpNorte;
    private JPanel jpCalen;
    private JTable tReservaciones = new JTable(informacionTabla(), titulosTabla());
    private JButton btnBuscarReservaciones;
    private JScrollPane jspBarraTabla;
    private JButton btnBuscarNombre;
    private JTextField txtNombreHuesped;
    private JButton btnTodosFecha;


    public HistorialReservaciones() {
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
                int rowHabitacion = tReservaciones.getSelectedRow();
                int numHabitacion = Integer.valueOf(tReservaciones.getValueAt(rowHabitacion, 1).toString());
                String[] opciones = {"Si", "No"};

                switch (JOptionPane.showOptionDialog(
                        null, "¿Liberar habitación?", "Liberacion de habitación",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, opciones, 0
                )) {
                    case 0:
                        habiDAO.liberarHabitacion(numHabitacion);
                        construirTablaTodo();
                        break;
                    case 1:
                        break;
                }
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

    public Date getDate() {
        //OBTENER FECHA
        return dateChos.getDate();
    }

    //CONSTRUCCION DE TABLA
    private String[][] informacionTabla() {
        String informacion[][] = obtenerMatrizTodos();
        return informacion;
    }
    private String[] titulosTabla() {
        String titulos[] = {"ID reservacion", "numero habitacion", "nombre huesped",
                "fecha de reservación", "dias de reserva", "metodo de pago"};
        return titulos;
    }
    //////////////////////////////
    private void construirTablaFiltroFecha(Date fecha) {
        //CONTRUYE LA TABLA USANDO POR FILTRO LA FECHA SELECCIONADA
        String informacion[][] = obtenerMatrizPorFecha(fecha);
        JTable dummyT = new JTable(informacion, titulosTabla());
        this.tReservaciones.setModel(dummyT.getModel());
        jspBarraTabla.setViewportView(this.tReservaciones);
    }

    private void construirTablaFiltroNombre(String name) {
        //CONTRUYE LA TABLA USANDO EL FILTRO DE NOMBRE
        String informacion[][] = obtenerMatrizPorNombre(name);
        JTable dummyT = new JTable(informacion, titulosTabla());
        this.tReservaciones.setModel(dummyT.getModel());
        jspBarraTabla.setViewportView(this.tReservaciones);
    }

    private void construirTablaTodo() {
        //CONTRUYE LA TABLA SIN USAR FILTROS
        String informacion[][] = obtenerMatrizTodos();
        JTable dummyT = new JTable(informacion, titulosTabla());
        this.tReservaciones.setModel(dummyT.getModel());
        jspBarraTabla.setViewportView(this.tReservaciones);
    }

    private String[][] obtenerMatrizPorNombre(String name) {
        //CON METODO construirTablaFiltroNombre
        List<Reservacion> miLista = reserDAO.buscarPorNombre(name);
        String matrizInfo[][] = new String[miLista.size()][6];
        for (int i = 0; i < miLista.size(); i++) {
            matrizInfo[i][0] = miLista.get(i).getIdReservacion() + "";
            matrizInfo[i][1] = miLista.get(i).getNumHabitacion() + "";
            matrizInfo[i][2] = miLista.get(i).getNombreHuesped() + "";
            matrizInfo[i][3] = miLista.get(i).getFechaReserva() + "";
            matrizInfo[i][4] = miLista.get(i).getDiasReserva() + "";
            matrizInfo[i][5] = miLista.get(i).getMetodoPago() + "";
        }
        return matrizInfo;
    }

    private String[][] obtenerMatrizTodos() {
        //CON METODO contruirTablaTodo
        List<Reservacion> miLista = reserDAO.buscarTodoFecha();
        String matrizInfo[][] = new String[miLista.size()][6];
        for (int i = 0; i < miLista.size(); i++) {
            matrizInfo[i][0] = miLista.get(i).getIdReservacion() + "";
            matrizInfo[i][1] = miLista.get(i).getNumHabitacion() + "";
            matrizInfo[i][2] = miLista.get(i).getNombreHuesped() + "";
            matrizInfo[i][3] = miLista.get(i).getFechaReserva() + "";
            matrizInfo[i][4] = miLista.get(i).getDiasReserva() + "";
            matrizInfo[i][5] = miLista.get(i).getMetodoPago() + "";
        }
        return matrizInfo;
    }

    private String[][] obtenerMatrizPorFecha(Date fecha) {
        //CON METODO contruirTablaFiltroFecha
        List<Reservacion> miLista = reserDAO.buscarPorFecha((java.sql.Date) fecha);
        String matrizInfo[][] = new String[miLista.size()][6];
        for (int i = 0; i < miLista.size(); i++) {
            matrizInfo[i][0] = miLista.get(i).getIdReservacion() + "";
            matrizInfo[i][1] = miLista.get(i).getNumHabitacion() + "";
            matrizInfo[i][2] = miLista.get(i).getNombreHuesped() + "";
            matrizInfo[i][3] = miLista.get(i).getFechaReserva() + "";
            matrizInfo[i][4] = miLista.get(i).getDiasReserva() + "";
            matrizInfo[i][5] = miLista.get(i).getMetodoPago() + "";
        }
        return matrizInfo;
    }

    //MAIN PARA PRUEBAS
    public static void main(String[] args) {
        new HistorialReservaciones().setVisible(true);
    }
}
