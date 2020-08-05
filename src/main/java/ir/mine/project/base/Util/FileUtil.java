package ir.mine.project.base.Util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	public static void storeFile(InputStream inputStream, String path, String name) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(path + "/" + name);
		IOUtils.copy(inputStream, fileOutputStream);
		fileOutputStream.flush();
		fileOutputStream.close();
	}

	public static void storeFileTh(InputStream inputStream, String path, String name) throws IOException {
		BufferedImage imBuff = ImageIO.read(inputStream);

		ByteArrayOutputStream byteArrayOutputStream = ImageUtil.smartCrop(imBuff, 456, 380);
		FileOutputStream fos = new FileOutputStream(path + "/" + name);

		byteArrayOutputStream.writeTo(fos);
		fos.flush();
		fos.close();
	}

	public static String getFileExtention(MultipartFile fileName) {
		return getFileExtention(fileName.getOriginalFilename());
	}

	public static String getFileExtention(File fileName) {
		return getFileExtention(fileName.getName());
	}

	public static String getFileExtention(String fileName) {
		return FilenameUtils.getExtension(fileName);
	}

	public static InputStream getFileByNameAndPath(String path, String fileName, String fileExtention) {

		File file = new File(path + "/" + fileName + "." + fileExtention);

		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
