package managedBeans;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ContentExtractService;
import service.DBLoadingService;
import service.InputCheckerService;
import service.ZipDownloadService;

@ManagedBean(name = "mainBean")
public class ContentExtract {

	private ZipDownloadService downloadService;
	private InputCheckerService inputService;
	private ContentExtractService extractService;
	private DBLoadingService dbService;
	private String[] directoryContentResult;
	private boolean inputInvalid;
	private String directoryInput;

	public ContentExtract() {
		downloadService = new ZipDownloadService();
		inputService = new InputCheckerService();
		extractService = new ContentExtractService();
	}

	public String extractContent() {
		if (inputService.isDirValid(getSelectedDir())) {
			setInputInvalid(false);
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			if (inputService.isCheckboxClicked(request)) {
				try {

					HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
							.getExternalContext().getResponse();
					downloadService.downloadZip(getSelectedDir(), response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				directoryContentResult = extractService.showHtmlLikeResult(getSelectedDir());
				return "extractedContent";
			}

		} else if (getSelectedDir() != null) {
			setInputInvalid(true);
			return "homePage";
		}
		return "homePage";

	}

	public String loadContentInDB() {

		if (inputService.isDirValid(getSelectedDir())) {
			setInputInvalid(false);

			dbService = new DBLoadingService();
			dbService.writeOutputInDB(getSelectedDir());

			if (dbService.isDBLoaded())
				return "loadDBSuccess";
			else
				return "loadDBFail";

		} else if (getSelectedDir() != null) {
			setInputInvalid(true);
			return "homePage";
		}
		return null;
	}

	public String getSelectedDir() {
		return directoryInput;
	}

	public void setSelectedDir(String selectedDir) {
		this.directoryInput = selectedDir;
	}

	public String[] getDirectoryContentResult() {
		return directoryContentResult;
	}

	public void setDirectoryContentResult(String[] directoryContentResult) {
		this.directoryContentResult = directoryContentResult;
	}

	public boolean isInputInvalid() {
		return inputInvalid;
	}

	public void setInputInvalid(boolean inputInvalid) {
		this.inputInvalid = inputInvalid;
	}



}
