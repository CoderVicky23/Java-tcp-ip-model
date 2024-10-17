// package LayerWiseD2;

// Physical Layer

import java.util.List;

public class PhysicalLayer {

    public void transmitData(List<String> ethernetFrames) {
        System.out.println("\nPhysical Layer: Transmitting data as binary signals...");
        for (String frame : ethernetFrames) {
            String binarySignal = convertToBinary(frame);
            System.out.println("Transmitting frame as binary signal: " + binarySignal);
        }
        System.out.println("\nData transmission complete!");
    }

    public List<String> receiveData(List<String> ethernetFrames) {
        System.out.println("\nPhysical Layer: Receiving data...");
        return ethernetFrames; // Simulating receiving the same Ethernet frames
    }

    private String convertToBinary(String data) {
        StringBuilder binary = new StringBuilder();
        for (char c : data.toCharArray()) {
            binary.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0')).append(" ");
        }
        return binary.toString();
    }
}
