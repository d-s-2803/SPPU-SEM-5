import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Client2 {
    public static void main(String[] args) throws IOException
    {
        InetAddress addr=InetAddress.getLocalHost();
                DatagramSocket s=new DatagramSocket(1089);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        byte[] buffer=new byte[20];
        String str="";
        while(!str.equals("bye"))
        {
            System.out.println("Client_2:");
            str=br.readLine();
            str = "2:" + str ;
            buffer=str.getBytes();
            DatagramPacket p1=new DatagramPacket(buffer,buffer.length,addr,1161);
            s.send(p1);
        }
    }

}