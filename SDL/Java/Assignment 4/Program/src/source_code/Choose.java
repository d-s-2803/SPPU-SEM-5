package source_code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choose {
    JFrame frame;
    JButton btnTeacher;
    JButton btnStudent;
    JPanel panel;

    Choose(){
        frame = new JFrame("Teacher or Student");

        btnTeacher = new JButton("Teacher");
        btnStudent = new JButton("Student");

        btnTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new source_code.Login();
                frame.dispose();
            }
        });
        btnStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new source_code.Student();
                frame.dispose();
            }
        });

        panel = new JPanel();

        panel.setLayout(new GridLayout(2,2));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(btnTeacher);
        panel.add(btnStudent);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(200,150);
        frame.setLocation(500,250);

    }
    public static void main(String [] args){
        new source_code.Choose();
    }
}
