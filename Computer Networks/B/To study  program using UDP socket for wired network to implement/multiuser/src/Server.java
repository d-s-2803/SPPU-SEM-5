import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;


public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket s=new DatagramSocket(1161);
        System.out.println("Server is listening...........");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str="";

        while(!str.equals("bye"))
        {
            byte[] buffer1=new byte[20];
            DatagramPacket p2=new DatagramPacket(buffer1, buffer1.length);
            s.receive(p2);

            buffer1=p2.getData();
            String str1=new String(buffer1,StandardCharsets.UTF_8);
            System.out.println("Client"+str1);
        }
    }

}