package Connection_socket_TCP_TeacherStudentApplication;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerSide {
    ServerSocket server_socket; //declaring server socket
    Socket connection_socket; // declaring socket
    DataInputStream dis;    // To read from client side
    DataOutputStream dos;   // To write from Server side
    DataInputStream disi;   // To read from Server side then writing in the client side
    Map<Integer,String> names = new HashMap<>();
    Set<Integer> check = new HashSet<>();

    ServerSide()
    {
        try {
            server_socket = new ServerSocket(2000,1,InetAddress.getLocalHost());        // Creating an instance //backlog : The maximum length of the pending connections queue.
            System.out.println("Server Started ..............................");
            connection_socket = server_socket.accept(); // Acccepting the connection with the client side
            System.out.println("Connection achieved with Client side............................");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void login() throws IOException {
        int i=0;
        try{
            String k = "Welcome to Teacher Student Application \nEnter your teacher Id or username to proceed further";
            dis = new DataInputStream(connection_socket.getInputStream());          //to read data
            dos = new DataOutputStream(connection_socket.getOutputStream());        //to write data
            disi = new DataInputStream(System.in);                                  // to get input
            dos.writeUTF(k);
            String o = dis.readUTF();
            if (o.equals("Login"))
            {
                System.out.println("Login Successful");
                menu_state();
            }
            else
            {
                System.out.println("Login unsuccesful");
            }
            System.out.println("Connection with the CLient side is going to be closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            connection_socket.close();
            System.out.println("Connection Closed");
        }

    }
    void menu_state()                                                                           //menu function
    {
        try{
            String m = "Press 1, To Add student in the class:\n" +
                    "Press 2, To Update student details in the class:\n" +
                    "Press 3, To Delete student details from the class:\n" +
                    "Press 4, To Display all the students in the class:\n" +
                    "Press 5, To Search a particular student with roll number:\n" +
                    "Press 0, To Exit :";
            dos = new DataOutputStream(connection_socket.getOutputStream());
            dis = new DataInputStream(connection_socket.getInputStream());
            dos.writeUTF(m);

            String me = dis.readUTF();
            if(me.equals("1"))
            {
                Add_student();
                menu_state();
            }
            else if(me.equals("2"))
            {
                update_Student();
                menu_state();
            }
            else if(me.equals("3"))
            {
                delete_Student();
                menu_state();
            }
            else if(me.equals("4"))
            {
                displayall();
                menu_state();
            }
            else if(me.equals("5"))
            {
                search_student();
                menu_state();
            }
            else if(me.equals("0"))
            {
                System.out.println("Client has requested to close the program.");
            }
            else{
                menu_state();
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    void Add_student()                      //add Student function
    {
        try {
            String n = "Enter Student name:";
            dos.writeUTF(n);
            String name = dis.readUTF();
            String r = "Enter PRN :";
            dos.writeUTF(r);
            String prn = dis.readUTF();
            Integer p = Integer.parseInt(prn);
            names.put(p,name);
            check.add(p);
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }

    void displayall() throws IOException {                      //Display function
        String d="PRN"+"\t"+"Name\n";
        for (Map.Entry<Integer, String> entry : names.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            d = d + key + "\t" + value+"\n";
        }
        dos.writeUTF(d+"\n Enter continue or Y");
        String j = dis.readUTF();
    }

    void delete_Student() throws IOException {                                  // Delete function
        String k = "Enter Student Prn to be deleted:\n";
        dos.writeUTF(k);
        String l = dis.readUTF();
        Integer o = Integer.parseInt(l);
        names.remove(o);
        check.remove(o);
        dos.writeUTF("Student deleted \n Enter continue or Y");
        String m = dis.readUTF();
    }

    void search_student() throws IOException {                                          //search function
        String p ="Enter PRN to be searched in the class:\n";
        dos.writeUTF(p);
        Integer l = Integer.parseInt(dis.readUTF());
        if(check.contains(l))
        {
            dos.writeUTF("Name : "+names.get(l)+"\n Enter continue or Y");
        }
        else{
            dos.writeUTF("Student not found in the class.\n"+"Enter continue Or Y");
        }
        String i = dis.readUTF();
    }

    void update_Student() throws IOException                                        //Update function
    {
        String l = "Enter PRN to be updated :";
        dos.writeUTF(l);
        Integer j = Integer.parseInt(dis.readUTF());
        if(check.contains(j))
        {
            String h = "enter new name";
            dos.writeUTF(h);
            String o = dis.readUTF();
            names.remove(j);
            check.remove(j);
            names.put(j,o);
            check.add(j);
            dos.writeUTF("Student name updated."+"\n Enter continue or Y");
        }
        else{
            dos.writeUTF("PRN doesnt exist."+"\n Enter continue or Y");
        }
        String p = dis.readUTF();
    }

    public static void main(String [] args) throws IOException {
        ServerSide ss = new ServerSide();                       // instance created of server side
        ss.login();
    }
}
