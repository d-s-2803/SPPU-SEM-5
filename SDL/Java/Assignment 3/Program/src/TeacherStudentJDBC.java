package JDBCwithTeacherStudent;
import java.sql.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class TeacherStudentJDBC {
    public static void main(String args[]) {
        try {
//step1 load the driver class
            Class.forName("com.mysql.jdbc.Driver");

//step2 create  the connection object
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdl","root","dhruvil283");

//step3 create the statement object
            Statement stmt = con.createStatement();
            //stmt.executeUpdate("create table teacher_database(ID int(3),Name varchar(20),Marks int(3));");
            int i = 0 ;
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to Teacher Student Application ");
            System.out.println("Teacher Staff enter 1 ");
            System.out.println("Student enter 2");
            i = sc.nextInt();
            sc.nextLine();
            Set<Integer> s = new HashSet<>();
            ResultSet ad_s = stmt.executeQuery("select id from teacher_database;");
            while(ad_s.next())
            {
                Integer values = parseInt(ad_s.getString(1));
                s.add(values);
            }
            if ( i == 1 ){
                System.out.println("Enter your teacher Id or username to proceed further:");
                String login = sc.nextLine();
                if(login.equals("login")){
                    do{
                        System.out.println("Enter 1, To Add student in the class:");
                        System.out.println("Enter 2, To Update student details in the class:");
                        System.out.println("Enter 3, To Delete student details from the class:");
                        System.out.println("Enter 4, To Display all the students in the class:");
                        System.out.println("Enter 5, To Search a particular student with roll number:");
                        System.out.println("Enter 0, To Exit :");
                        i = sc.nextInt();
                        sc.nextLine();
                        switch(i)
                        {
                            case 1:
                                System.out.println("\n");
                                System.out.println("Enter Student ID : ");
                                Integer id = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Enter Student Name: ");
                                String name = sc.nextLine();
                                System.out.println("Enter Student Marks: ");
                                Integer marks = sc.nextInt();

                                String sql = "insert into teacher_database values("+id.toString()+","+"'"+name+"'"+","+marks.toString()+");";
                                stmt.executeUpdate(sql);
                                s.add(id);
                                System.out.println("\n");
                                break;

                            case 2:
                                System.out.println("\n");
                                System.out.println("Enter Student Id to be updated");
                                Integer k = sc.nextInt();
                                sc.nextLine();
                                if(s.contains(k))
                                {
                                    System.out.println("Enter 1, to update ID\t OR \t enter 2, to update name \tOR \tEnter 3, to update marks\nEnter 0, To update None:");
                                    Integer o = sc.nextInt();
                                    sc.nextLine();
                                    if(o==0){
                                        System.out.println("\n");
                                        System.out.println("Update none.");
                                        System.out.println("\n");
                                    }
                                    else if(o==1)
                                    {
                                        System.out.println("Enter new ID : ");
                                        Integer m = sc.nextInt();
                                        sc.nextLine();
                                        stmt.executeUpdate("update teacher_database set ID = "+m.toString()+" where ID = "+k.toString()+";");
                                        s.add(m);
                                        s.remove(k);
                                        System.out.println("\n");
                                        System.out.println("Student Id Updated to "+m.toString()+" with Student old Id : "+k.toString());
                                        System.out.println("\n");
                                    }
                                    else if(o==2)
                                    {
                                        System.out.println("Enter new name : ");
                                        String p = sc.nextLine();
                                        stmt.executeUpdate("update teacher_database set Name = "+"'"+p+"'"+" where ID = "+k.toString()+";");
                                        System.out.println("\n");
                                        System.out.println("Student name updated with student Id : "+k.toString());
                                        System.out.println("\n");
                                    }
                                    else if(o==3)
                                    {
                                        System.out.println("Enter new marks : ");
                                        Integer e = sc.nextInt();
                                        stmt.executeUpdate("update teacher_database set Marks = "+e.toString()+" where ID = "+k.toString()+";");
                                        System.out.println("\n");
                                        System.out.println("Student marks updated with Student Id : "+k.toString());
                                        System.out.println("\n");
                                    }
                                    else{
                                        System.out.println("Invalid Option.");
                                    }
                                }
                                else{
                                    System.out.println("ID doesnt exist.");
                                }
                                System.out.println("\n");
                                break;

                            case 3:
                                System.out.println("\n");
                                System.out.println("Enter student ID to be deleted:");
                                Integer d = sc.nextInt();
                                sc.nextLine();
                                stmt.executeUpdate("delete from teacher_database where ID = "+d.toString()+";");
                                s.remove(d);
                                System.out.println("\n");
                                System.out.println("Student ID "+ d.toString()+" deleted");
                                System.out.println("\n");
                                break;

                            case 4:
                                ResultSet re = stmt.executeQuery("select * from teacher_database;");
                                System.out.println("\n");
                                System.out.println("Displaying database ");
                                System.out.println("\n");
                                System.out.println("ID\t\tName\t\t\t\tMarks");
                                while(re.next())
                                {
                                    System.out.println(re.getString(1)+"\t\t"+re.getString(2)+"\t\t\t\t"+re.getString(3));
                                }
                                System.out.println("\n");
                                break;

                            case 5:
                                System.out.println("Enter Student ID to be searched in the class:");
                                Integer l = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Results : ");
                                ResultSet r = stmt.executeQuery("select * from teacher_database where ID = "+ l.toString()+";");
                                if(s.contains(l))
                                {
                                    System.out.println("\n");
                                    while(r.next())
                                    {
                                        System.out.println("ID :"+r.getString(1)+"\t\t"+"Name:"+r.getString(2)+"\t\t"+"Marks:"+r.getString(3));
                                    }
                                }
                                else{
                                    System.out.println("\n");
                                    System.out.println("Id doesnt exist.");
                                }
                                System.out.println("\n");
                                break;

                            case 0:
                                System.out.println("\n");
                                System.out.println("You have exited the program.");
                                break;

                            default:
                                System.out.println("Invalid Option");
                        }

                    }while(i!=0);
                }
                else{
                    System.out.println("\n");
                    System.out.println("Run the program again with valid username.");
                }
            }
            else if ( i == 2 ){
                System.out.println("\n");
                System.out.println("Enter Your Id to be updated");
                Integer k = sc.nextInt();
                sc.nextLine();
                if(s.contains(k))
                {
                    System.out.println("Enter 1, to update ID\t OR \t enter 2, to update name \tOR \tEnter 0, To update None:");
                    Integer o = sc.nextInt();
                    sc.nextLine();
                    if(o==0){
                        System.out.println("\n");
                        System.out.println("Update none.");
                        System.out.println("\n");
                    }
                    else if(o==1)
                    {
                        System.out.println("Enter new ID : ");
                        Integer m = sc.nextInt();
                        sc.nextLine();
                        stmt.executeUpdate("update teacher_database set ID = "+m.toString()+" where ID = "+k.toString()+";");
                        s.add(m);
                        s.remove(k);
                        System.out.println("\n");
                        System.out.println("Student Id Updated to "+m.toString()+" with Student old Id : "+k.toString());
                        System.out.println("\n");
                    }
                    else if(o==2)
                    {
                        System.out.println("Enter new name : ");
                        String p = sc.nextLine();
                        stmt.executeUpdate("update teacher_database set Name = "+"'"+p+"'"+" where ID = "+k.toString()+";");
                        System.out.println("\n");
                        System.out.println("Student name updated with student Id : "+k.toString());
                        System.out.println("\n");
                    }
                    else{
                        System.out.println("Invalid Option.");
                    }
                }
                else{
                    System.out.println("ID doesnt exist.");
                }
                System.out.println("\n");
            }
            else {
                System.out.println("Please enter valid input and run the program again.");
            }
//step5 close the connection object
            con.close();

        } catch (Exception e) {
            System.out.println("\n");
            System.out.println(e);
        }
    }
}


