package cursoSelenium.pages;

import cursoSelenium.utils.DriverType;

public class PageFactory {
	
	private PageFactory() {
	}
	
	public static SearchPage getSearchPage(DriverType driverType){
		return new SearchPage(driverType, "https://www.expedia.es");
	}
}
