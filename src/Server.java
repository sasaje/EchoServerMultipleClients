import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try {
            //Trin 1 - Lav serversocket
            ServerSocket serverSocket = new ServerSocket(1709);

            // Trin 2 - Få en socket til at lytte på den angivne port
            System.out.println("Accepting connection on port 1709");
            Socket socket = serverSocket.accept();
            System.out.println("Connection established " + socket.getRemoteSocketAddress().toString());

            //Trin 3 - Opret dataInputStream og dataOutputStream
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            // can also use BufferedInputStream class
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            //Trin 4 - Her kan man sende og modtage data
            String incomingText = inputStream.readUTF(); // jeg læser en string på port 1709
            System.out.println("Tekst modtaget: " + incomingText);
            outputStream.writeUTF("Tak for teksten " + incomingText); // meddelelse om at serveren har modtaget læsningen
            outputStream.flush(); // flush to send tekst

            //Trin 5 - forbindelsen lukkes
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
