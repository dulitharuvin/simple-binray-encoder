package com.sinch.sbe;

import java.util.HashMap;
import java.util.Map;

public class Message {
    public Map<String, String> headers;
    public byte[] payload;

    public Message() {
        this.headers = new HashMap<String,String>();
        this.payload = new byte[256 * 1024];
    }
}
