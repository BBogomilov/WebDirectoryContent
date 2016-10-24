package managedBeans;

import javax.faces.bean.ManagedBean;

import service.DBLoadingService;

@ManagedBean
public class ExtractFromDB {

	private DBLoadingService dbService;
	private String[] result;

	public ExtractFromDB() {
		dbService = new DBLoadingService();
	}

	public String extractContent() {

		result = dbService.getDataFromDB().split(System.getProperty("line.separator"));
		return "extractedContentFromDB";
	}

	public String[] getResult() {
		return result;
	}

}
