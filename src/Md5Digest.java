import java.security.MessageDigest;

public class Md5Digest {

	public static String main(String arg) throws Exception {

		String original = arg;
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(original.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return(sb.toString());
	}

}