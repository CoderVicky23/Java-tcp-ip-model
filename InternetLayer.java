// package LayerWiseD2;

// Internet Layer

import java.util.ArrayList;
import java.util.List;

public class InternetLayer {

    private String sourceIP = "192.168.1.1";
    private String destinationIP = "192.168.1.100";

    public List<String> addIPHeaders(List<String> segments) {
        System.out.println("\nInternet Layer: Adding IP headers...");
        List<String> ipPackets = new ArrayList<>();
        for (String segment : segments) {
            String ipPacket = "[IP Header] Src: " + sourceIP + ", Dest: " + destinationIP + ", Data: " + segment;
            ipPackets.add(ipPacket);
            System.out.println("IP Packet: " + ipPacket);
        }
        return ipPackets;
    }

    public List<String> removeIPHeaders(List<String> ipPackets) {
        System.out.println("\nInternet Layer: Removing IP headers...");
        List<String> segments = new ArrayList<>();
        for (String ipPacket : ipPackets) {
            String segment = ipPacket.replaceFirst("\\[IP Header\\] Src: .*?, Dest: .*?, Data: ", "");
            segments.add(segment);
            System.out.println("Extracted Data: " + segment);
        }
        return segments;
    }
}


