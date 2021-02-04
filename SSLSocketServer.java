import java.io.*;
import javax.net.ssl.*;
public class SSLSocketServer
{

  public static void main(String [] args)
  {
    try
    {
      SSLServerSocketFactory factory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
      SSLServerSocket serverSocket = (SSLServerSocket)factory.createServerSocket(9999);
      SSLSocket socket = (SSLSocket)serverSocket.accept();
      
      ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

      // read objects from client and confirm them
      Object input = null;
      while((input = is.readObject()) != null) {
        Student receivedStudent = (Student)input;
        System.out.println("Received student with data: "+receivedStudent.toString());
        os.writeObject("Received student with data: "+receivedStudent.toString());
        System.out.flush();
      }
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
    }
  }
}