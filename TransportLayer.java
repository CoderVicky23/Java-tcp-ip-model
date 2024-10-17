// package LayerWiseD2;

// Transport Layer

import java.util.ArrayList;
import java.util.List;

public class TransportLayer {

    public List<String> segmentData(String data) {
        System.out.println("\nTransport Layer: Segmenting data...");
        List<String> segments = new ArrayList<>();
        int segmentSize = 16;
        int length = data.length();
        for (int i = 0; i < length; i += segmentSize) {
            segments.add(data.substring(i, Math.min(length, i + segmentSize)));
        }

        for (int i = 0; i < segments.size(); i++) {
            System.out.println("Segment " + (i + 1) + " [Seq: " + (i + 1) + "]: " + segments.get(i));
        }
        return segments;
    }

    public String reassembleSegments(List<String> segments) {
        System.out.println("\nTransport Layer: Reassembling segments...");
        StringBuilder reassembledData = new StringBuilder();
        for (String segment : segments) {
            reassembledData.append(segment);
        }
        System.out.println("Reassembled data: " + reassembledData.toString());
        return reassembledData.toString();
    }
}


