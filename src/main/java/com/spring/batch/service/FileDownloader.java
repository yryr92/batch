package com.spring.batch.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.spring.batch.domain.Movie;
import com.spring.batch.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FileDownloader {

	@Autowired
	MovieRepository movieRepository;

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
			
			System.out.println("httpUrl = " + httpUrl);
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

	public void getImageUrl(Movie movie) throws IOException {
        URL url = null;
        InputStream in = null;
        OutputStream out = null;
		String imageUrl = "https://image.tmdb.org/t/p/w500";
		//String posterPath = movie.getPoster_path().replaceAll("/", "\\\\");
		//log.info(">>> posterPath {} ", posterPath);
		String downPath = "D:/download" + movie.getPoster_path();

		File file = new File(downPath);
		if(!file.exists()) {
			try {
				url = new URL(imageUrl + movie.getPoster_path());
				in = url.openStream();
	
				// 컴퓨터 또는 서버의 저장할 경로(절대패스로 지정해 주세요.)
				out = new FileOutputStream(downPath);
	
				while (true) {
					// 루프를 돌면서 이미지데이터를 읽어들이게 됩니다.
					int data = in.read();
	
					// 데이터값이 -1이면 루프를 종료하고 나오게 됩니다.
					if (data == -1) {
						break;
					}
	
					// 읽어들인 이미지 데이터값을 컴퓨터 또는 서버공간에 저장하게 됩니다.
					out.write(data);
				}
	
				// 저장이 끝난후 사용한 객체는 클로즈를 해줍니다.
				in.close();
				out.close();
	
				movieRepository.updateMovieDownloadYn(movie);
	
			} catch (Exception e) {
				  // 예외처리
				e.printStackTrace();
			} finally {
				// 만일 에러가 발생해서 클로즈가 안됐을 가능성이 있기에
				// NULL값을 체크후 클로즈 처리를 합니다.
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			}
		} else {
			log.info(">>> this file is exists {}", movie.getPoster_path());
		}
    }
    
}
