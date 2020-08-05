package ir.mine.project.base.Util.hash;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

	public static String sha1Hash(String str) {
		return DigestUtils.sha1Hex(str);
	}

	public static String md5(String str) {
		return DigestUtils.md5Hex(str);
	}
	
	public static void main(String[] args) {
		System.out.println(sha1Hash("a"));
	}
}
