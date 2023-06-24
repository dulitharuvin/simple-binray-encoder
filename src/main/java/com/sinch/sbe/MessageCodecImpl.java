package com.sinch.sbe;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class MessageCodecImpl implements MessageCodec{

    public byte[] encode(Message message) {
        ByteBuffer buffer = ByteBuffer.allocate(message.headers.size() * 1024 + message.payload.length);
        buffer.putInt(message.headers.size());
        for (Map.Entry<String, String> header : message.headers.entrySet()) {
            buffer.putInt(header.getKey().length());
            buffer.put(header.getKey().getBytes());
            buffer.putInt(header.getValue().length());
            buffer.put(header.getValue().getBytes());
        }
        buffer.put(message.payload);
        return buffer.array();
    }

    public Message decode(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int headerCount = buffer.getInt();
        Message message = new Message();
        message.headers = new HashMap<>();
        for (int i = 0; i < headerCount; i++) {
            int nameLength = buffer.getInt();
            byte[] nameBytes = new byte[nameLength];
            buffer.get(nameBytes);
            String name = new String(nameBytes);
            int valueLength = buffer.getInt();
            byte[] valueBytes = new byte[valueLength];
            buffer.get(valueBytes);
            String value = new String(valueBytes);
            message.headers.put(name, value);
        }
        message.payload = new byte[buffer.remaining()];
        buffer.get(message.payload);
        return message;
    }
}
