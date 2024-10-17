// package LayerWiseD2;

// Application Layer

import java.nio.charset.Charset;

public class ApplicationLayer {

    public String asciiToEBCDIC(String asciiStr) {
        Charset ebcdicCharset = Charset.forName("IBM037");
        byte[] ebcdicBytes = asciiStr.getBytes(ebcdicCharset);
        return new String(ebcdicBytes, ebcdicCharset);
    }

    public String ebcdicToASCII(String ebcdicStr) {
        Charset asciiCharset = Charset.forName("IBM037");
        byte[] asciiBytes = ebcdicStr.getBytes(asciiCharset);
        return new String(asciiBytes, asciiCharset);
    }

    public String sendMessage(String message) {
        System.out.println("Application Layer (Sender): Sending message: " + message);
        String ebcdicMessage = asciiToEBCDIC(message);
        System.out.println("Message converted to EBCDIC: " + ebcdicMessage);
        return ebcdicMessage;
    }

    public void receiveMessage(String message) {
        String originalMessage = ebcdicToASCII(message);
        System.out.println("Application Layer (Receiver): Received and converted message back to ASCII: " + originalMessage);
    }
}

