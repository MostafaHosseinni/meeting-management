package ir.mine.project.web.rest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.InputStreamResource;

import lombok.SneakyThrows;

public class DataConvertor {

    public static String convertImageToBase64(String fileUrl) {
        if (fileUrl == null || "".equals(fileUrl))
            return null;
        File file = new File(fileUrl);
        try {
            InputStream fInput = new FileInputStream(file);
            byte[] imageBytes = new byte[(int) file.length()];
            fInput.read(imageBytes, 0, imageBytes.length);
            fInput.close();
            return Base64.encodeBase64String(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @SneakyThrows
    public static InputStreamResource convertImageToInputStream(String fileUrl) {
        if (fileUrl == null || "".equals(fileUrl))
            return null;
        File file = new File(fileUrl);
        return new InputStreamResource(new FileInputStream(file));
    }

    public static String convertStringToBase64(byte[] content) throws UnsupportedEncodingException {
        return new String(Base64.decodeBase64(content), "UTF-8");
    }

}
