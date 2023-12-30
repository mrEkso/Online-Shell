package com.example.fxshell.http.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpServiceImpl implements HttpService {
    public byte[] prepareMultipartBody(Path filePath, String boundary) throws IOException {
        String formPart = "--" + boundary + "\r\nContent-Disposition: form-data; name=\"file\"; filename=\"" + filePath.getFileName() + "\"\r\nContent-Type: " + Files.probeContentType(filePath) + "\r\n\r\n";
        byte[] fileData = Files.readAllBytes(filePath);
        byte[] endPart = ("\r\n--" + boundary + "--").getBytes();
        return concatenateBytes(formPart.getBytes(), fileData, endPart);
    }

    public byte[] concatenateBytes(byte[]... arrays) {
        int totalLength = 0;
        for (byte[] array : arrays) {
            totalLength += array.length;
        }
        byte[] result = new byte[totalLength];
        int offset = 0;
        for (byte[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
}