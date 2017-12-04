package com.patterns.decorator;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CryptoOutputStream extends FilterOutputStream {
    private byte[] password;
    int idx;

    public CryptoOutputStream(OutputStream out, byte[] password) {
        super(out);
        this.out = out;
        this.password = password;
        this.idx = 0;
    }

    @Override
    public void write(int b) throws IOException {
        b ^= password[idx];
        idx++;
        idx = idx % password.length;
        out.write(b);
        out.flush();
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            b[i] ^= password[idx];
            idx++;
            idx = idx % password.length;
        }
        out.write(b, off, len);
        out.flush();
    }
}
