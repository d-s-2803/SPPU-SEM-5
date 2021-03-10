import java.io.*;
import java.net.*;


public class Server
{
    public static void main(String args[]) throws IOException
    {
        ServerSocket ss2=new ServerSocket(4445);
        System.out.println("Server Listening......");

        while(true)
        {
            try
            {
                Socket s= ss2.accept();
                System.out.println("connection Established");
                ServerThread st=new ServerThread(s);
                st.start();
            }
            catch(Exception e)
            {
                System.out.println("Connection Error");
            }
        }
    }
}

class ServerThread extends Thread
{
    String line=null;
    BufferedReader br = null;
    DataOutputStream dos=null;
    DataInputStream dis=null;
    Socket s=null;

    public ServerThread(Socket s)
    {
        this.s=s;
    }

    public void run()
    {
        try
        {
            br= new BufferedReader(new InputStreamReader(System.in));
            dos=new DataOutputStream(s.getOutputStream());
            dis=new DataInputStream(s.getInputStream());

        }
        catch(IOException e)
        {
            System.out.println("IO error in server thread");
        }

        try
        {
            line="";
            while(!line.equals("stop"))
            {
                line=dis.readUTF();
                System.out.println("client says: "+line);
                dos.flush();
            }
        }
        catch (IOException e)
        {

            System.out.println("IO Error/ Client ");
        }
        catch(NullPointerException e)
        {
            System.out.println("IO Error/ Client");
        }

        finally{
            try{
                br.close();
                dos.close();
                dis.close();
                s.close();
            }
            catch(IOException ie){
                System.out.println("Socket Close Error");
            }}
    }
}
