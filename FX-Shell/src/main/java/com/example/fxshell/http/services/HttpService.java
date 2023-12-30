package com.example.fxshell.http.services;

import java.io.IOException;
import java.nio.file.Path;

public interface HttpService {
    byte[] prepareMultipartBody(Path filePath, String boundary) throws IOException;

    byte[] concatenateBytes(byte[]... arrays);
}
