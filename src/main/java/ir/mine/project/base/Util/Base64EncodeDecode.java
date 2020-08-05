package ir.mine.project.base.Util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class Base64EncodeDecode {

	public static String convertToBase64String(File in) throws IOException {

		DataInputStream dis = new DataInputStream(new FileInputStream(in));

		int available = dis.available();
		byte[] buffer = new byte[available];
		dis.read(buffer);
		Base64 base64 = new Base64();
		byte[] encoded = base64.encode(buffer);
		dis.close();

		return new String(encoded, "UTF-8");
	}
	public static String convertToBase64String(InputStream dis) throws IOException {
		
		int available = dis.available();
		byte[] buffer = new byte[available];
		dis.read(buffer);
		Base64 base64 = new Base64();
		byte[] encoded = base64.encode(buffer);
		dis.close();
		
		return new String(encoded, "UTF-8");
	}

	/*
	 * public static void convertBase64StringToFile(String data, File out) throws
	 * IOException {
	 *
	 * DataOutputStream dos = new DataOutputStream(new FileOutputStream(out));
	 *
	 * byte[] input = data.getBytes("UTF-8");
	 *
	 * Base64 base64 = new Base64(); byte[] decoded = base64.decode(input);
	 * dos.write(decoded); dos.flush(); dos.close();
	 *
	 * System.out.println("Done"); }
	 */

	public static String convertByteArrayToBase64String(byte[] input) throws UnsupportedEncodingException {
		if (input == null) {
			return null;
		}
		Base64 base64 = new Base64();
		byte[] encoded = base64.encode(input);
		return new String(encoded, "UTF-8");
	}

	public static String convertFileToBase64String(File file) throws IOException {
		byte[] byteArray = new byte[(int) file.length()];
		byteArray = FileUtils.readFileToByteArray(file);
		return convertByteArrayToBase64String(byteArray);
	}

	public static byte[] convertBase64StringToByteArray(String input) throws UnsupportedEncodingException {
		if (input == null) {
			return null;
		}
		Base64 base64 = new Base64();
		byte[] bytes = input.getBytes("UTF-8");
		byte[] decoded = base64.decode(bytes);
		return decoded;
	}

	public static void convertBase64StringToFile(String input, File out) throws IOException {
		byte[] byteArray = convertBase64StringToByteArray(input);
		FileUtils.writeByteArrayToFile(out, byteArray);
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static byte[] asByteArray(String hex) {
		byte[] bts = new byte[hex.length() / 2];
		for (int i = 0; i < bts.length; i++) {
			bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}

		return bts;
	}

}
