package com.patterns.decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CryptoInputStream extends FilterInputStream {
    private byte[] password;
    int idx;

    public CryptoInputStream(InputStream in, byte[] password) {
        super(in);
        this.password = password;
        this.idx = 0;
    }

    @Override
    public int read() throws IOException {
        int value = in.read();
        if (value == -1) {
            return value;
        }
        value ^= password[idx];
        idx++;
        idx = idx % password.length;
        return value;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int length;

        length = in.read(b, off, len);
        if (length == -1) {
            return length;
        }
        for (int i = 0; i < length; i++) {
            b[i] ^= password[idx];
            idx++;
            idx = idx % password.length;
        }

        return length;
    }
}
