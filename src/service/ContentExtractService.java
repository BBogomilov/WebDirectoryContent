package service;

import reader.IReader;
import reader.ReaderFactory;

public class ContentExtractService {

	private IReader reader;
	private String result;

	public void extractContent(String dirPath) {

		reader = ReaderFactory.getInstance("n");
		reader.showContent(dirPath);

		result = reader.getResult();
	}

	public String[] showHtmlLikeResult(String dirPath) {

		extractContent(dirPath);

		String[] files = result.split(System.getProperty("line.separator"));
		return files;
	}
}
