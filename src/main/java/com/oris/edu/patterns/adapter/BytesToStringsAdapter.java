package com.oris.edu.patterns.adapter;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class BytesToStringsAdapter implements BytesAdapter {

    private ByteArrayOutputStream outputStream;

    public BytesToStringsAdapter(ByteArrayOutputStream byteArrayOutputStream){
        this.outputStream = byteArrayOutputStream;
    }

    @Override
    public String[] read() throws UnsupportedEncodingException {
        return outputStream.toString(String.valueOf(StandardCharsets.UTF_8)).split("\n");
    }
}
