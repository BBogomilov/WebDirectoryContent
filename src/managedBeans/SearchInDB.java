package managedBeans;

import javax.faces.bean.ManagedBean;

import service.SearchInDBService;

@ManagedBean
public class SearchInDB {

	private SearchInDBService service;
	private String noContentFoundMessage;
	private String searchInput;
	private boolean searchInvoked;
	private boolean somethingFound;
	private boolean nothingFound;
	private String[] searchResult;

	public SearchInDB() {
	}

	public String search() {
		searchInvoked = true;
		service = new SearchInDBService();
		String result = service.getSearchedContent(getSearchInput());
		String filesNumMsg = service.getFilesFound() + " files found!" + System.getProperty("line.separator");

		if (service.getAreAnyMatches()) {
			somethingFound = true;
			nothingFound = !somethingFound;
			result = filesNumMsg + result;
			searchResult = result.split(System.getProperty("line.separator"));
		} else {
			setNoContentFoundMessage("Sorry, there were no matches found for '" + getSearchInput() + "'.");
			somethingFound = false;
			nothingFound = !somethingFound;
		}
		return "homePage";
	}

	public String getNoContentFoundMessage() {
		return noContentFoundMessage;
	}

	public void setNoContentFoundMessage(String noContentFoundMessage) {
		this.noContentFoundMessage = noContentFoundMessage;
	}

	public String getSearchInput() {
		return searchInput;
	}

	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	public String[] getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(String[] searchResult) {
		this.searchResult = searchResult;
	}

	public boolean isSearchInvoked() {
		return searchInvoked;
	}

	public void setSearchInvoked(boolean searchInvoked) {
		this.searchInvoked = searchInvoked;
	}

	public boolean isSomethingFound() {
		return somethingFound;
	}

	public void setSomethingFound(boolean somethingFound) {
		this.somethingFound = somethingFound;
	}

	public boolean isNothingFound() {
		return nothingFound;
	}

	public void setNothingFound(boolean nothingFound) {
		this.nothingFound = nothingFound;
	}

}
