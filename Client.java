// package Soketjava;
import java.io.*;
import java.net.*;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try {
            // Create a socket to connect to the server on port 5000
            Socket socket = new Socket("localhost", 5000);

            // Create input and output streams for communication
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Create instances of each layer
            ApplicationLayer appLayer = new ApplicationLayer();
            EncryptionLayer encryptionLayer = new EncryptionLayer();
            TransportLayer transportLayer = new TransportLayer();
            InternetLayer internetLayer = new InternetLayer();
            LinkLayer linkLayer = new LinkLayer();

            // Application Layer - Sending message
            String message = "Hello";
            String ebcdicMessage = appLayer.sendMessage(message);

            // Encrypt the message
            String encryptedMessage = encryptionLayer.encrypt(ebcdicMessage);
            System.out.println("Encrypted message: " + encryptedMessage);

            // Transport Layer - Segment the encrypted message
            List<String> segments = transportLayer.segmentData(encryptedMessage);

            // Internet Layer - Add IP headers
            List<String> ipPackets = internetLayer.addIPHeaders(segments);

            // Link Layer - Add MAC headers
            List<String> ethernetFrames = linkLayer.addMACHeaders(ipPackets);

            // Send the Ethernet frames to the server over the network
            for (String frame : ethernetFrames) {
                out.println(frame);
            }

            // Receive response from the server
            String serverResponse = in.readLine();
            System.out.println("Server response: " + serverResponse);

            // Close the socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

