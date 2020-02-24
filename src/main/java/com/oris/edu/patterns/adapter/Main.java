package com.oris.edu.patterns.adapter;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StringsAdapter stringsAdapter = new StringsToBytesAdapter(outputStream);
        BytesAdapter bytesAdapter = new BytesToStringsAdapter(outputStream);

        String[] strings = new String[]{"Строка 1", "Строка 2", "Строка 3"};

        stringsAdapter.write(strings);

        System.out.println(Arrays.toString(outputStream.toByteArray()));

        System.out.println(Arrays.toString(bytesAdapter.read()));
    }
}
