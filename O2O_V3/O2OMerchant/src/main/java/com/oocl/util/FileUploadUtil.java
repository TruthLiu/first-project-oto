package com.oocl.util;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
public class FileUploadUtil {
	private static Logger logger = Logger.getLogger(FileUploadUtil.class);
	
	private static String fileServerUrl="";

	public static String fileUpload(Map<String, MultipartFile> files){
		initProperties();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost uploadFile = new HttpPost(fileServerUrl);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		for (String fileNamePath : files.keySet()) {
			logger.info("upload fileName:"+fileNamePath);
			builder.addTextBody("storePath", fileNamePath,ContentType.TEXT_PLAIN);
			try {
				builder.addBinaryBody("file",files.get(fileNamePath).getInputStream(),ContentType.APPLICATION_OCTET_STREAM,"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		HttpEntity multipart = builder.build();
		uploadFile.setEntity(multipart);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(uploadFile);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return StatusCode.FILE_SERVER_FAIL;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity responseEntity = response.getEntity();
		return StatusCode.SUCCESS;
	}
	
	private static void initProperties() {
		Properties pro=null;
		try {
			pro = new Properties();
			pro.load(FileUploadUtil.class.getClassLoader().getResourceAsStream("fileserver.properties"));
			fileServerUrl = pro.getProperty("fileServerUrl");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
