import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {

    public static void main(String[] args) {
        
        try {
            // Trin 1 - lav socket
            Socket socket = new Socket("localhost", 1978);

            //Trin 2 - Opret dataInputStream og dataOutputStream
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            // can also use BufferedInputStream class
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            while(true){
                Scanner scanner = new Scanner(System.in);
                outputStream.writeUTF(scanner.nextLine());
                outputStream.flush();
                System.out.println(inputStream.readUTF());
            }
//            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
