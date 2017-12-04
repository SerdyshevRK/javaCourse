package com.streams;

import java.io.IOException;
import java.io.InputStream;

public class SawInputStream extends InputStream {
    private final int MAX_VALUE = 256;
    private int value;

    public SawInputStream() {
        value = 0;
    }

    @Override
    public int read() throws IOException {
        return value++ % MAX_VALUE;
    }
}
