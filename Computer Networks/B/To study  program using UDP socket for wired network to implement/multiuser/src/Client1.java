import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Client1 {
    public static void main(String[] args) throws IOException
    {
        InetAddress addr=InetAddress.getLocalHost();
        DatagramSocket s=new DatagramSocket(1088);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        byte[] buffer=new byte[20];
        String str="";
        while(!str.equals("bye"))
        {
            System.out.println("Client_1:");
            str=br.readLine();
            str = "1:" + str ;
            buffer=str.getBytes();
            DatagramPacket p1=new DatagramPacket(buffer,buffer.length,addr,1161);
            s.send(p1);
        }
    }
}