package source_code;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

class Errorpane{
    Errorpane(String r){
        JFrame f = new JFrame("Alert Message");
        JOptionPane.showMessageDialog(f, r);
    }
}

public class Teacher {
    JFrame frame;
    JPanel main_panel;
    JButton btnAddStudent;
    JButton btnUpdateStudent;
    JButton btnDeleteStudent;
    JButton btnDisplayStudent;
    JButton btnSearchStudent;

    Teacher(){
        frame = new JFrame("Welcome to Teacher");
        main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(5,1));

        btnAddStudent = new JButton("Add Student");
        btnUpdateStudent = new JButton("Update Student");
        btnDeleteStudent = new JButton("Delete Student");
        btnDisplayStudent = new JButton("Display All Students");
        btnSearchStudent = new JButton("Search Student");

        btnAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ADD_student();
            }
        });

        btnUpdateStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new update_student();
            }
        });

        btnDeleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Delete_student();
            }
        });

        btnDisplayStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Display_student();
            }
        });

        btnSearchStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Search_student();
            }
        });
        main_panel.add(btnAddStudent);
        main_panel.add(btnUpdateStudent);
        main_panel.add(btnDeleteStudent);
        main_panel.add(btnDisplayStudent);
        main_panel.add(btnSearchStudent);

        frame.getContentPane().add(main_panel);
        frame.setVisible(true);
        frame.setLocation(500,250);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

class ADD_student{
    JFrame f;
    JPanel panel;
    JLabel id;
    JTextField idFld;
    JLabel name;
    JTextField nameFld;
    JLabel marks;
    JTextField marksFld;

    JButton btnAdd;
    JButton btnBack;

    ADD_student()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl","root","dhruvil283");
            Statement stmt = con.createStatement();

            f = new JFrame("Add Student");
            id = new JLabel("Enter ID or PRN : ");
            idFld = new JTextField(20);
            name = new JLabel("Enter Name: ");
            nameFld = new JTextField(20);
            marks = new JLabel("Enter marks:");
            marksFld = new JTextField(20);
            btnAdd = new JButton("ADD");
            btnBack = new JButton("Back");

            panel = new JPanel();

            panel.setLayout(new GridLayout(4,2));

            panel.add(id);
            panel.add(idFld);
            panel.add(name);
            panel.add(nameFld);
            panel.add(marks);
            panel.add(marksFld);
            panel.add(btnAdd);
            panel.add(btnBack);

            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String i = idFld.getText();
                    String j = nameFld.getText();
                    String k = marksFld.getText();

                    int p,m;
                    Integer o = Integer.parseInt(k);
                    Integer l = Integer.parseInt(i);
                    p = l;
                    m = o;
                    Set<Integer> s = new HashSet<>();
                    try {
                        ResultSet ad_s = stmt.executeQuery("select id from teacher_database;");
                        while (ad_s.next()) {
                            Integer values = parseInt(ad_s.getString(1));
                            s.add(values);
                        }
                    }
                    catch(Exception etr)
                    {
                        System.out.println(etr);
                    }
                    try{
                        if(p == l && (!j.equals("")) && m == o && (!s.contains(l)))
                        {
                            try {
                                stmt.executeUpdate("insert into teacher_database values("+i+",'"+j+"',"+k+");");
                                marksFld.setText("");
                                nameFld.setText("");
                                idFld.setText("");

                            } catch (Exception throwables) {
                                f.setVisible(false);
                                new Errorpane("Error Occured During Add operation");
                                f.setVisible(true);
                                marksFld.setText("");
                                nameFld.setText("");
                                idFld.setText("");
                                throwables.printStackTrace();
                            }
                        }
                        else{
                            f.setVisible(false);
                            new Errorpane("Error Occured During Add operation");
                            f.setVisible(true);
                            marksFld.setText("");
                            nameFld.setText("");
                            idFld.setText("");
                        }
                    }catch (Exception et){
                        f.setVisible(false);
                        new Errorpane("Error Occured During Add operation");
                        f.setVisible(true);
                        marksFld.setText("");
                        nameFld.setText("");
                        idFld.setText("");
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
                    new Teacher();
                }
            });

            f.getContentPane().add(panel);
            f.setVisible(true);
            f.setSize(600,150);
            f.setLocation(500,250);
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

class update_student {
    JFrame f;
    JPanel panel;
    JLabel id;
    JTextField idFld;
    JLabel marks;
    JTextField marksFld;

    JButton btnUpdate;
    JButton btnBack;

    update_student() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl", "root", "dhruvil283");
            Statement stmt = con.createStatement();
            f = new JFrame("Update Student marks");
            id = new JLabel("Enter ID or Prn:");
            idFld = new JTextField(20);
            marks = new JLabel("Enter new Marks :");
            marksFld = new JTextField(20);

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
                    String p = marksFld.getText();
                    Integer m = Integer.parseInt(k);
                    Integer n = Integer.parseInt(p);
                    int r=m,u=n;

                    if(m==r && n == u && s.contains(m))
                    {
                        try {
                            stmt.executeUpdate("update teacher_database set marks ="+p+" where id ="+k+" ;");
                            new Errorpane("Updated marks");
                            idFld.setText("");
                            marksFld.setText("");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    else{
                        f.setVisible(false);
                        new Errorpane("Error occured during updating the data");
                        f.setVisible(true);
                        idFld.setText("");
                        marksFld.setText("");
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
                    new Teacher();
                }
            });

            panel = new JPanel();

            panel.setLayout(new GridLayout(3,2));

            panel.add(id);
            panel.add(idFld);
            panel.add(marks);
            panel.add(marksFld);
            panel.add(btnUpdate);
            panel.add(btnBack);

            f.getContentPane().add(panel);
            f.setVisible(true);
            f.setSize(600  ,200 );
            f.setLocation(500,250);
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

class Delete_student
{
    JFrame f;
    JLabel id;
    JTextField idFld;
    JButton btnDel;
    JButton btnBack;
    JPanel panel;

    Delete_student()
    {
        f = new JFrame("Delete Student");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl","root","dhruvil283");
            Statement stmt = con.createStatement();

            id = new JLabel("Enter ID or PRN to delete");
            idFld = new JTextField(20);

            btnDel = new JButton("Delete Student");
            btnBack = new JButton("Back");

            panel = new JPanel();

            panel.setLayout(new GridLayout(2,2));

            panel.add(id);
            panel.add(idFld);
            panel.add(btnDel);
            panel.add(btnBack);

            btnDel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Set<Integer> s = new HashSet<>();
                    try{
                        ResultSet ad_s = stmt.executeQuery("select id from teacher_database;");
                        while(ad_s.next())
                        {
                            Integer values = parseInt(ad_s.getString(1));
                            s.add(values);
                        }
                    }
                    catch(Exception erth){
                        erth.printStackTrace();
                    }

                    String k = idFld.getText();
                    Integer l = Integer.parseInt(k);
                    int o = l;
                    if(o==l && (s.contains(l))){
                        try{
                            stmt.executeUpdate("delete from teacher_database where id ="+k+";");
                            new Errorpane("Student delete");
                            s.remove(l);
                            idFld.setText("");
                        }
                        catch (Exception kl)
                        {
                            kl.printStackTrace();
                        }
                    }
                    else{
                        f.setVisible(false);
                        new Errorpane("Error");
                        f.setVisible(true);
                        idFld.setText("");
                    }
                }
            });

            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    try {
                        con.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    new Teacher();
                }
            });

            f.getContentPane().add(panel);
            f.setVisible(true);
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setSize(600,200);
            f.setLocation(500,250);

        }catch (Exception ert){
            System.out.println(ert);
        }
    }
}

class Display_student{
    JFrame f;
    JTable t;
    JButton btnBack;
    JScrollPane panel;
    Display_student()
    {
        f = new JFrame("Displaying ALL Students");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl","root","dhruvil283");
            Statement stmt = con.createStatement();

            btnBack = new JButton("Back");

            Set<Integer> s = new HashSet<>();
            ResultSet ds = stmt.executeQuery("select id from teacher_database;");
            while(ds.next())
            {
                s.add(Integer.parseInt(ds.getString(1)));
            }

            String [][] data = new String[s.size()][3];
            String column[]={"ID","NAME","Marks"};
            int i=0;
            ResultSet ad_s = stmt.executeQuery("select * from teacher_database;");
            while(ad_s.next() && i<s.size())
            {
                for(int j=0;j<3;j++)
                {
                    data[i][j] = ad_s.getString(j+1);
                }
                i++;
            }
            t = new JTable(data,column);
            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    try {
                        con.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    new Teacher();
                }
            });

            t.setBounds(40,50,200,300);
            panel = new JScrollPane(t);

            f.setLayout(new GridLayout(2,1));
            f.add(panel);
            f.add(btnBack);
            f.setVisible(true);
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setSize(600,200);
            f.setLocation(500,250);

        }catch (Exception ert){
            System.out.println(ert);
        }
    }
}

class Search_student{
    JFrame f;
    JTable t;
    JLabel name,result;
    JTextField nameFld;
    JButton btnSearch;
    JButton btnBack;
    JScrollPane panel;
    Search_student()
    {
        f = new JFrame("Search Students");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl","root","dhruvil283");
            Statement stmt = con.createStatement();

            name = new JLabel("Enter ID or PRN to be searched:");
            nameFld = new JTextField(20);
            result = new JLabel("Result");
            btnSearch = new JButton("Search");
            btnBack = new JButton("Back");

            btnSearch.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String k = nameFld.getText();
                    Integer l = Integer.parseInt(k);
                    Set<Integer> s1 = new HashSet<>();
                    try {
                        ResultSet ds = stmt.executeQuery("select id from teacher_database ;");
                        while (ds.next()) {
                            s1.add(Integer.parseInt(ds.getString(1)));
                        }
                    }catch (Exception er){
                        er.printStackTrace();
                    }

                    if(s1.contains(l))
                    {
                        String s="";
                        try{
                            ResultSet ad_s = stmt.executeQuery("select name from teacher_database where id = "+k+";");
                            while(ad_s.next()) {
                                s = ad_s.getString(1);
                            }
                            result.setText(s);
                        }
                        catch (Exception lk){
                            lk.printStackTrace();
                        }
                    }
                    else{
                        f.setVisible(false);
                        new Errorpane("Not Found");
                        f.setVisible(true);
                        result.setText("Result");
                    }
                }
            });

            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.dispose();
                    try {
                        con.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    new Teacher();
                }
            });

            f.setLayout(new GridLayout(3,2));
            f.add(name);
            f.add(nameFld);
            f.add(btnSearch);
            f.add(btnBack);
            f.add(result);
            f.setVisible(true);
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setSize(600,200);
            f.setLocation(500,250);

        }catch (Exception ert){
            System.out.println(ert);
        }
    }
}

