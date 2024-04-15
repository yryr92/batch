package com.spring.batch.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloader {

    public void fileDownload(String httpUrl, String downPath, String downName) {

        // t.fileDownload("https://cdn.pixabay.com/photo/2021/08/22/08/54/bird-6564593__340.jpg", "D:/dev/", "bird.jpg");
		FileOutputStream fos = null;
		InputStream is = null;
		
		try {
			//디렉토리 체크
			File file = new File(downPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			
			fos = new FileOutputStream(downPath+downName);
			
			System.out.println("httpUrl = "+httpUrl);
			URL url = new URL(httpUrl);
			URLConnection urlConnection = url.openConnection();
			//https
			//HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
			is = urlConnection.getInputStream();
			
			byte[] buffer = new byte[1024];
			int readBytes;
			while ((readBytes = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readBytes);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    
}
