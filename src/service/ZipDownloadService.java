package service;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import reader.IReader;
import reader.ReaderFactory;

public class ZipDownloadService {
	
	public void downloadZip(String dir, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "attachment; filename=data.zip");

		try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {

			IReader reader = ReaderFactory.getInstance("n");
			reader.showContent(dir);

			StringBuilder sb = new StringBuilder();
			sb.append(reader.getResult());

			ZipEntry e = new ZipEntry("output.txt");
			zos.putNextEntry(e);

			byte[] data = sb.toString().getBytes();
			zos.write(data, 0, data.length);
			zos.closeEntry();

		}
	}
}
