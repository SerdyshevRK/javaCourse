package com.streams;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class RandomInputStream extends InputStream {
    Random rnd = new Random();

    @Override
    public int read() throws IOException {
        return rnd.nextInt(256);
    }
}
