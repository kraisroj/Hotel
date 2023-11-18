package GUI;

import DAO.HuespedDAOImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginHuesped extends JFrame {
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
                if (huesDAO.buscarNombre(txtLogin.getText())==true) {
                    System.out.println("YA CREADO");
                    user = txtLogin.getText();
                    ReservacionGUI reservacionGUI = new ReservacionGUI();
                    reservacionGUI.setTxtUsuario(user);
                } else if (huesDAO.save(txtLogin.getText())!=true) {
                    JOptionPane.showMessageDialog(null, """
                            USUARIO CREADO, LA PROXIMA VEZ SE USARA PARA SU LOGEO EN PLATAFORMA
                               """, "", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("usuario creado");
                } else {
                    System.out.println("no creado");
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