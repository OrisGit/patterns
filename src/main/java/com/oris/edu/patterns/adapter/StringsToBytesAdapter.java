package com.oris.edu.patterns.adapter;

import com.oris.edu.patterns.logger.Logger;
import com.oris.edu.patterns.logger.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class StringsToBytesAdapter implements StringsAdapter {

    Logger logger = LoggerFactory.getLogger(StringsToBytesAdapter.class);

    private ByteArrayOutputStream byteArrayOutputStream;

    public StringsToBytesAdapter(ByteArrayOutputStream byteArrayOutputStream){
        this.byteArrayOutputStream = byteArrayOutputStream;
    }

    @Override
    public void write(String... strings) {
        for (String string : strings) {

            byte[] bytes = (string+"\n").getBytes(StandardCharsets.UTF_8);
            try {
                byteArrayOutputStream.write(bytes);
            } catch (IOException e) {
                logger.printError(e);
            }
        }
    }
}
