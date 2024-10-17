// package LayerWiseD2;
// Link Layer

import java.util.ArrayList;
import java.util.List;

public class LinkLayer {

    private String sourceMAC = "00:1A:2B:3C:4D:5E";
    private String destinationMAC = "00:5E:4D:3C:2B:1A";

    public List<String> addMACHeaders(List<String> ipPackets) {
        System.out.println("\nLink Layer: Adding MAC headers...");
        List<String> ethernetFrames = new ArrayList<>();
        for (String ipPacket : ipPackets) {
            String ethernetFrame = "[Ethernet Frame] Src MAC: " + sourceMAC + ", Dest MAC: " + destinationMAC + ", Data: " + ipPacket;
            ethernetFrames.add(ethernetFrame);
            System.out.println("Ethernet Frame: " + ethernetFrame);
        }
        return ethernetFrames;
    }

    public List<String> removeMACHeaders(List<String> ethernetFrames) {
        System.out.println("\nLink Layer: Removing MAC headers...");
        List<String> ipPackets = new ArrayList<>();
        for (String ethernetFrame : ethernetFrames) {
            String ipPacket = ethernetFrame.replaceFirst("\\[Ethernet Frame\\] Src MAC: .*?, Dest MAC: .*?, Data: ", "");
            ipPackets.add(ipPacket);
            System.out.println("Extracted IP Packet: " + ipPacket);
        }
        return ipPackets;
    }
}