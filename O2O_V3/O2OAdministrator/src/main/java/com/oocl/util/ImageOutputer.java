package com.oocl.util;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.tomcat.util.codec.binary.Base64;
public class ImageOutputer {
	
	/**
	 * 将base64字符串转为字节并写到指定文件路径
	 * @param path
	 * @param base64Str
	 * @throws IOException
	 */
	public static void output(String path,String base64Str) throws IOException{
		byte[] buf = Base64.decodeBase64(base64Str);
		OutputStream out = null;
		out = new FileOutputStream(path);
		out.write(buf);
		out.flush();
		out.close();
	}
}
