package Connection_socket_TCP_TeacherStudentApplication;
import java.net.*;
import java.io.*;

public class ClientSide {
    Socket client_socket;           // declaring socket
    DataInputStream dis;            // declaring read
    DataOutputStream dos;              // declaring write
    DataInputStream disi;              //declaring input from the client console
    ClientSide()
    {
        try {
            client_socket = new Socket(InetAddress.getLocalHost(),2000);
            System.out.println("Request sent to Server side..................................");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void Login() throws IOException {
        try{
            dis = new DataInputStream(client_socket.getInputStream());
            dos = new DataOutputStream(client_socket.getOutputStream());
            disi = new DataInputStream(System.in);
            System.out.println(dis.readUTF());
            String str = new String(disi.readLine());
            dos.writeUTF(str);
            String st = "1";
            while(!st.equals("0"))
            {
                System.out.println(dis.readUTF());
                st = new String (disi.readLine());
                dos.writeUTF(st);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }finally {
            client_socket.close();
        }
    }
    public static void main(String [] args) throws IOException {
        ClientSide cs = new ClientSide();       //instance created of the client side
        cs.Login();
    }
}
