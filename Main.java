// package LayerWiseD2;

// Main Class

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        // Create instances of each layer
        ApplicationLayer appLayer = new ApplicationLayer();
        EncryptionLayer encryptionLayer = new EncryptionLayer();
        TransportLayer transportLayer = new TransportLayer();
        InternetLayer internetLayer = new InternetLayer();
        LinkLayer linkLayer = new LinkLayer();
        PhysicalLayer physicalLayer = new PhysicalLayer();

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

        // Physical Layer - Transmit the data
        physicalLayer.transmitData(ethernetFrames);

        // Now simulate receiving the data and reverse the process

        System.out.println("\n--- RECEPTION PROCESS ---\n");

        // Physical Layer - Receive data
        List<String> receivedFrames = physicalLayer.receiveData(ethernetFrames);

        // Link Layer - Remove MAC headers
        List<String> receivedIPPackets = linkLayer.removeMACHeaders(receivedFrames);

        // Internet Layer - Remove IP headers
        List<String> receivedSegments = internetLayer.removeIPHeaders(receivedIPPackets);

        // Transport Layer - Reassemble segments
        String reassembledData = transportLayer.reassembleSegments(receivedSegments);

        // Decrypt the message
        String decryptedMessage = encryptionLayer.decrypt(reassembledData);
        System.out.println("\nDecrypted message: " + decryptedMessage);

        // Application Layer - Convert EBCDIC back to ASCII and print
        appLayer.receiveMessage(decryptedMessage);
    }
}
