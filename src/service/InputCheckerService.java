package service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import utils.IOUtils;

public class InputCheckerService {

	private boolean checkbox = false;

	public boolean isCheckboxClicked(HttpServletRequest request) {
		return "Y".equals(request.getParameter("check"));
	}

	public boolean isDirValid(String dir) {
		if (IOUtils.isDirectory(new File(dir))) {
			return true;
		}
		return false;
	}

	public boolean getCheckbox() {
		return checkbox;
	}

}
