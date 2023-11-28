package GUI;

import DAO.HuespedDAOImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginHuesped extends JFrame {
    //DAOS Implementaciones

    HuespedDAOImpl huesDAO = new HuespedDAOImpl();
    private JTextField txtLogin;
    private JButton btnLogin;
    private JPanel pnlMain;
    private JPanel jpBotonera;

    public String user;

    public LoginHuesped() {

        inicializarComponentes();
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtLogin.getText().equalsIgnoreCase("Ingresa tu nombre...")) {
                    if (huesDAO.buscarNombre(txtLogin.getText())) {
                        System.out.println("YA CREADO");
                        user = txtLogin.getText();
                        ReservacionGUI reservacionGUI = new ReservacionGUI();
                        reservacionGUI.setUser(user);
                    } else if (huesDAO.save(txtLogin.getText()) == 1) {
                        user = txtLogin.getText();
                        JOptionPane.showMessageDialog(null, """
                                USUARIO CREADO, LA PROXIMA VEZ SE USARA PARA SU LOGEO EN PLATAFORMA
                                   """, "", JOptionPane.INFORMATION_MESSAGE);
                        ReservacionGUI reservacionGUI = new ReservacionGUI();
                        reservacionGUI.setUser(user);
                        System.out.println("usuario creado");
                    } else {
                        System.out.println("no creado");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "favor de registrar un nombre",
                            "Aviso",
                            JOptionPane.INFORMATION_MESSAGE);
                }


            }
        });
        txtLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (txtLogin.getText().equalsIgnoreCase("Ingresa tu nombre...")) {
                    txtLogin.setText("");
                }
            }
        });
    }

    private void inicializarComponentes() {
        this.setContentPane(this.pnlMain);
        this.setTitle("Login");
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new LoginHuesped().setVisible(true);
    }
}
