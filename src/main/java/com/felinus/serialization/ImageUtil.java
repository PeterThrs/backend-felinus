package com.felinus.serialization;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class ImageUtil {
    public static String convertBlobToBase64String(Blob blob) throws SQLException, IOException {
        byte[] bytes = blob.getBytes(1, (int) blob.length());
        return Base64.getEncoder().encodeToString(bytes);
    }
}