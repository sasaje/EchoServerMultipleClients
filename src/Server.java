import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;

            try {
                // Create a server socket
                serverSocket = new ServerSocket(1978);
            } catch (IOException ex) {
                System.err.println(ex);
            }

            while (true) {

                try{
                    // Listen for a new connection request
                    socket = serverSocket.accept();
                }catch(IOException e){
                    e.printStackTrace();
                }
                new HandleAClient(socket).start();
            }
        }
    }
    // Define the thread class for handling new connection
    class HandleAClient extends Thread {
        private Socket socket; // A connected socket

        /** Construct a thread */
        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        /** Run a thread */
        public void run() {
            try {
                System.out.println("Accepting connection on port 8000");
                System.out.println("Connection established " + socket.getRemoteSocketAddress().toString());

                //Trin 3 - Opret dataInputStream og dataOutputStream
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                // can also use BufferedInputStream class
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                //Trin 4 - Her kan man sende og modtage data
                while(true){
                    String incomingText = inputStream.readUTF(); // jeg læser en string på port 1709
                    System.out.println("Tekst modtaget: " + incomingText);
                    outputStream.writeUTF("Tak for teksten: " + incomingText); // meddelelse om at serveren har modtaget læsningen
                    outputStream.flush(); // flush to send tekst
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}

