package source_code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class Student {
    JFrame frame;
    JPanel main_panel;
    JButton btnUpdateStudent;


    Student(){
        frame = new JFrame("Welcome to Student");
        main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(1,1));
        btnUpdateStudent = new JButton("Update Student");


        btnUpdateStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new update_student1();
            }
        });

        main_panel.add(btnUpdateStudent);

        frame.getContentPane().add(main_panel);
        frame.setVisible(true);
        frame.setLocation(500,250);
        frame.setSize(100,100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}


class update_student1 {
    JFrame f;
    JPanel panel;
    JLabel id;
    JTextField idFld;
    JLabel name;
    JTextField nameFld;

    JButton btnUpdate;
    JButton btnBack;

    update_student1() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl", "root", "dhruvil283");
            Statement stmt = con.createStatement();
            f = new JFrame("Update Student Details");
            id = new JLabel("Enter ID or Prn:");
            idFld = new JTextField(20);
            name = new JLabel("Enter new Name :");
            nameFld = new JTextField(20);

            btnUpdate = new JButton("Update ");
            btnBack = new JButton("Back");

            btnUpdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Set<Integer> s = new HashSet<>();
                    try {
                        ResultSet ad_s = stmt.executeQuery("select id from teacher_database;");
                        while (ad_s.next()) {
                            Integer values = parseInt(ad_s.getString(1));
                            s.add(values);
                        }
                    }catch(Exception ert)
                    {
                        ert.printStackTrace();
                    }
                    String k = idFld.getText();
                    String p = nameFld.getText();
                    Integer m = Integer.parseInt(k);
                    int r=m;

                    if(m==r && s.contains(m))
                    {
                        try {
                            stmt.executeUpdate("update teacher_database set name ='"+ nameFld.getText() +"'where id =" + k + ";");
                            new source_code.Errorpane("Updated Details");
                            idFld.setText("");
                            nameFld.setText("");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    else{
                        f.setVisible(false);
                        new source_code.Errorpane("Error occured during updating the data");
                        f.setVisible(true);
                        idFld.setText("");
                        nameFld.setText("");
                    }
                }
            });

            btnBack.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    try {
                        con.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    new source_code.Choose();
                }
            });

            panel = new JPanel();

            panel.setLayout(new GridLayout(3,2));

            panel.add(id);
            panel.add(idFld);
            panel.add(name);
            panel.add(nameFld);
            panel.add(btnUpdate);
            panel.add(btnBack);

            f.getContentPane().add(panel);
            f.setVisible(true);
            f.setSize(600  ,150 );
            f.setLocation(500,250);
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

