package com.sinch.sbe;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SimpleBinaryEncoder {

    public static void main(String[] args) {
        try {
            String originalMessage = "Dulitha_slinch_SBE_Testing";
            byte[] originalMessageBytes = originalMessage.getBytes(StandardCharsets.UTF_8);

            MessageCodec codec = new MessageCodecImpl();
            Message message = new Message();

            message.headers.put("key1", "value1");
            message.headers.put("key2", "value2");
            message.payload = originalMessageBytes;

            byte[] encodedMessage = codec.encode(message);
            Message decodedMessage = codec.decode(encodedMessage);

            System.out.println("=====================Byte arrays in readable string format =============================");

            System.out.println("Original Message : " + new String(originalMessageBytes, StandardCharsets.UTF_8));
            System.out.println("Endcoded Message : " + new String(encodedMessage, StandardCharsets.UTF_8));
            System.out.println("Encoding Headers : " + decodedMessage.headers);
            System.out.println("Decoded Message : " + new String(decodedMessage.payload, StandardCharsets.UTF_8));

            System.out.println("=====================Byte arrays in string format =============================");

            System.out.println("Original Byte array : " +Arrays.toString(originalMessageBytes));
            System.out.println("Encoded Byte array : " + Arrays.toString(encodedMessage));
            System.out.println("Decoded Byte array : " + Arrays.toString(decodedMessage.payload));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
