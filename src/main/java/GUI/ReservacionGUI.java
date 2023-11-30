package GUI;

import DAO.HabitacionDAOImpl;
import DAO.HuespedDAOImpl;
import DAO.ReservacionDAOImpl;
import Entidad.Habitacion;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

public class ReservacionGUI extends JFrame {
    //IMPLEMENTACIONES
    private ReservacionDAOImpl reserDAO = new ReservacionDAOImpl();
    private HabitacionDAOImpl habiDao = new HabitacionDAOImpl();
    private HuespedDAOImpl huesDAO = new HuespedDAOImpl();
    //VARIABLES
    private String tam = "";
    private String user = "";

    private Calendar cld = Calendar.getInstance();
    private JDateChooser dateChooser = new JDateChooser(cld.getTime());
    ////COMPONENTES
    private JPanel jBotonSur;
    private JPanel pnlCenterMain;
    private JPanel pnlNorteInterior;
    private JPanel pnlCentroInterior;
    private JPanel pnlMain;
    private JButton btnCrearReservacion;
    private JComboBox cbTamanio;
    private JComboBox cbCocineta;
    private JTable tHabitacion = new JTable(informacion(), titulosTabla());
    private JButton btnBuscarHabitacion;
    private JScrollPane jspBarraTabla;
    private JButton btnBuscarTodo;
    private JComboBox cbDias;
    private JButton btnSalir;
    private JTextField txtCodigoPromocional;
    private JPanel jpCalen;
    private JTextField txtUsuario;
    private JTextField txtIdHabitacion;

    public ReservacionGUI() {
        inicializarComponentes();

        //CALENDARIO
        dateChooser.setDateFormatString("dd/MM/yyyy");
        jpCalen.add(dateChooser);

        btnBuscarHabitacion.addActionListener(new ActionListener() {
            //BUSCAR HABITACIONES POR MEDIO DE FILTRO
            @Override
            public void actionPerformed(ActionEvent e) {
                tam = cbTamanio.getSelectedItem().toString();
                construirTablaFiltros(tam.charAt(0), cocinetaOpcion(cbCocineta.getSelectedItem().toString()));
            }
        });

        btnBuscarTodo.addActionListener(new ActionListener() {
            //MOSTRAR TODAS LAS HABITACIONES SIN USAR FILTRO DE BUSQUEDA
            @Override
            public void actionPerformed(ActionEvent e) {
                construirTablaSinFiltro();
            }
        });
        tHabitacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //obtener valores de tabla
                int rowIdHabit = tHabitacion.getSelectedRow();
                int idHabit = Integer.valueOf(tHabitacion.getValueAt(rowIdHabit, 0).toString());
                int noHabit = Integer.valueOf(tHabitacion.getValueAt(rowIdHabit, 1).toString());
                char tama = tHabitacion.getValueAt(rowIdHabit, 2).toString().charAt(0);
                String cocina = tHabitacion.getValueAt(rowIdHabit, 3).toString();
                String[] tarjetas = {"Crédito", "Débito"};

                switch (JOptionPane.showOptionDialog(
                        null, "Tarjetas de pago", "Metodo de pago",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, tarjetas, 0)
                ) {//seleccionar que tipo de tarjeta se selecciono
                    case 0:
                        reserDAO.crearReservacion(idHabit, huesDAO.obtenerIdHuesped(user),
                                new java.sql.Date(getDate().getTime()), Integer.parseInt(cbDias.getSelectedItem().toString()),
                                "C".charAt(0));
                        habiDao.ocuparHabitacion(idHabit);
                        break;
                    case 1:
                        reserDAO.crearReservacion(idHabit, huesDAO.obtenerIdHuesped(user),
                                new java.sql.Date(getDate().getTime()), Integer.parseInt(cbDias.getSelectedItem().toString())
                                , "D".charAt(0));
                        habiDao.ocuparHabitacion(idHabit);
                        break;
                    case 2:
                        break;
                }
                construirTablaSinFiltro();
            }
        });
        txtCodigoPromocional.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (txtCodigoPromocional.getText().equalsIgnoreCase("Ingrese codigo promocional...")) {
                    txtCodigoPromocional.setText("");
                }
            }
        });
    }

    public java.util.Date getDate() {
        return dateChooser.getDate();
    }


    private String cocinetaOpcion(String opcion) {
        String flag = "";
        if (opcion.equals("Si")) {
            return flag = "incluida";
        } else {
            return flag = "no incluida";
        }
    }

    private void construirTablaFiltros(char tam, String cocineta) {
        String titulos[] = {"id habitación", "No. habitación", "tamaño", "cocineta incluida", "ocupado"};
        String informacion[][] = obtenerMatrizFiltro(tam, cocineta);
        JTable dummyT = new JTable(informacion, titulos);
        this.tHabitacion.setModel(dummyT.getModel());
        jspBarraTabla.setViewportView(this.tHabitacion);
    }

    private void construirTablaSinFiltro() {
        String titulos[] = {"id habitación", "No. habitación", "tamaño", "cocineta incluida", "ocupado"};
        String informacion[][] = obtenerMatrizSinFiltro();
        JTable dummyT = new JTable(informacion, titulos);
        this.tHabitacion.setModel(dummyT.getModel());

        jspBarraTabla.setViewportView(this.tHabitacion);
    }

    private String[] titulosTabla() {
        String titulos[] = {"id habitación", "No. habitación", "tamaño", "cocineta incluida", "ocupado"};
        return titulos;
    }

    private String[][] informacion() {
        String informacion[][] = obtenerMatrizSinFiltro();
        return informacion;
    }

    private String[][] obtenerMatrizSinFiltro() {
        ArrayList<Habitacion> miLista = habiDao.busquedaSinFiltro();
        String matrizInfo[][] = new String[miLista.size()][5];
        for (int i = 0; i < miLista.size(); i++) {
            matrizInfo[i][0] = miLista.get(i).getId() + "";
            matrizInfo[i][1] = miLista.get(i).getNumHabitacion() + "";
            matrizInfo[i][2] = miLista.get(i).getTamanio() + "";
            matrizInfo[i][3] = miLista.get(i).getCocineta() + "";
            matrizInfo[i][4] = miLista.get(i).getEstadoOcupado() + "";
        }
        return matrizInfo;
    }

    private String[][] obtenerMatrizFiltro(char tam, String cocineta) {
        ArrayList<Habitacion> miLista = (ArrayList<Habitacion>) habiDao.busquedaParametros(tam, cocineta);
        String matrizInfo[][] = new String[miLista.size()][5];
        for (int i = 0; i < miLista.size(); i++) {
            matrizInfo[i][0] = miLista.get(i).getId() + "";
            matrizInfo[i][1] = miLista.get(i).getNumHabitacion() + "";
            matrizInfo[i][2] = miLista.get(i).getTamanio() + "";
            matrizInfo[i][3] = miLista.get(i).getCocineta() + "";
            matrizInfo[i][4] = miLista.get(i).getEstadoOcupado() + "";
        }
        return matrizInfo;
    }

    private void inicializarComponentes() {
        this.setContentPane(pnlMain);
        this.setTitle("crear reservacion");
        this.setVisible(true);
        this.pack();
        jspBarraTabla.setViewportView(this.tHabitacion);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void setUser(String user) {
        this.user = user;
    }

    public static void main(String[] args) {
        new ReservacionGUI().setVisible(true);
    }
}
