package com.oris.edu.patterns.adapter;

import java.io.UnsupportedEncodingException;

public interface BytesAdapter {
    String[] read() throws UnsupportedEncodingException;
}
