import java.io.*;
import java.net.*;

public class Client {

    public static void main(String args[]) throws IOException
    {
        InetAddress address=InetAddress.getLocalHost();
        Socket s1=null;
        String line=null;
        BufferedReader br=null;
        BufferedReader br1=null;
        DataOutputStream dos=null;

        try {
            s1=new Socket("localhost", 4445);
            br= new BufferedReader(new InputStreamReader(System.in));
            br1=new BufferedReader(new InputStreamReader(s1.getInputStream()));
            dos= new DataOutputStream(s1.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
            System.err.print("IO Exception");
        }

        System.out.println("Client Address : "+address);
        System.out.println("Enter Data to echo Server ( Enter QUIT to end):");

        String response="";
        try{
            line=br.readLine();
            while(!line.equals("stop"))
            {
                dos.writeUTF(line);
                dos.flush();
                System.out.println("Enter Data to echo Server ( Enter stop to end):");
                line=br.readLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Socket read Error");
        }
        finally{

            br1.close();
            dos.close();
            br.close();
            s1.close();
            System.out.println("Connection Closed");

        }

    }
}