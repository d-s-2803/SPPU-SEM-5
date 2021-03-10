package source_code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OptionPaneExample {
    OptionPaneExample() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Wrong Username or Password");
    }
}


public class Login {
    JFrame frame;
    JLabel username;
    JTextField userField;
    JLabel password;
    JPasswordField passwordField;
    JButton btnLogin;
    JButton btnClear;
    JPanel panel;

    Login(){
        frame = new JFrame("Login Page");

        username = new JLabel("Username:");
        password = new JLabel("Password:");

        userField = new JTextField(20);
        passwordField = new JPasswordField(20);

        btnLogin = new JButton("Login");
        btnClear = new JButton("Clear");

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pf = new String(passwordField.getPassword());
                if(userField.getText().equals("login") && pf.equals("login")){
                    new source_code.Teacher();
                    frame.dispose();
                }
                else{
                    frame.setVisible(false);
                    new OptionPaneExample();
                    frame.setVisible(true);
                    userField.setText("");
                    passwordField.setText("");
                }
            }
        });

        panel = new JPanel();

        panel.setLayout(new GridLayout(3,2));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(username);
        panel.add(userField);
        panel.add(password);
        panel.add(passwordField);
        panel.add(btnLogin);
        panel.add(btnClear);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600,150);
        frame.setLocation(500,250);

    }
    public static void main(String [] args){
        new Login();
    }
}
