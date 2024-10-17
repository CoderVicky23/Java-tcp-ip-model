// package Soketjava;
import java.io.*;
import java.net.*;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try {
            // Create a server socket to listen for incoming connections on port 5000
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started, waiting for a client...");

            // Accept a connection from a client
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            // Create input and output streams for communication
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Receive the message from the client (the Ethernet frames)
            String receivedFrame;
            StringBuilder receivedData = new StringBuilder();
            while ((receivedFrame = in.readLine()) != null) {
                System.out.println("Received frame: " + receivedFrame);
                receivedData.append(receivedFrame);
            }

            // Process the received data through each layer in reverse
            // (Link, Internet, Transport, Decrypt, Application Layer)
            LinkLayer linkLayer = new LinkLayer();
            InternetLayer internetLayer = new InternetLayer();
            TransportLayer transportLayer = new TransportLayer();
            EncryptionLayer encryptionLayer = new EncryptionLayer();
            ApplicationLayer appLayer = new ApplicationLayer();

            // Simulate decryption process through layers (Link, Internet, Transport, Encryption, Application)
            List<String> receivedIPPackets = linkLayer.removeMACHeaders(List.of(receivedData.toString()));
            List<String> receivedSegments = internetLayer.removeIPHeaders(receivedIPPackets);
            String reassembledData = transportLayer.reassembleSegments(receivedSegments);
            String decryptedMessage = encryptionLayer.decrypt(reassembledData);

            // Application layer - receive the original message
            appLayer.receiveMessage(decryptedMessage);

            // Send back a response to the client
            out.println("Message successfully decrypted: " + decryptedMessage);

            // Close the socket
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
