import java.io.*;
import javax.net.ssl.*;
import java.util.Scanner;

public class SSLSocketClient {

    public static Scanner scanner = new Scanner(System.in);

    public static boolean continueLooping(){
        System.out.print("Do you want to continue? (y/n): ");
        String cont = scanner.nextLine();
        return cont.startsWith("y");
    }

    public static Student createStudent(){
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student surname: ");
        String surname = scanner.nextLine();
        
        System.out.print("Enter student index number: ");
        String index_number = scanner.nextLine();

        return new Student(name, surname, index_number);
    }

    public static void main(String[] args) throws Exception {
        try {
            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket)factory.createSocket("127.0.0.1", 9999);

            socket.startHandshake();
            
            // create object streams
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.flush();
            System.out.println("Created output object stream");
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            System.out.println("Created input object stream");

            do{
                // create example object to send
                // object has to implement interface Serializable
                Student student = createStudent();
                System.out.println("Example object created: Student "+student.toString());
                
                System.out.println("Sending object to server");
                os.writeObject((Object)student);
                os.flush();
                System.out.println("Object sent!");

                String response = null;
                if((response = (String)is.readObject()) != null){
                    System.out.println("This is server answer: " + response);
                }
            }while(continueLooping());
            
            socket.close();    
            scanner.close();
            System.out.println("Finishing client");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}