package com.tafu.driverHelper;

public enum BrowserType {
	FireFox("*firefox"), 
	Chrome ("*chrome"), 
	InternetExplore("*iexplore"), 
	Safari("*safari"); 

	public static BrowserType getBrowserType(final String browserType) {
		if(browserType.equalsIgnoreCase("*chrome") || browserType.equalsIgnoreCase("chrome")) {
			return BrowserType.Chrome;
		}
		else if(browserType.equalsIgnoreCase("*firefox") || browserType.equalsIgnoreCase("firefox")) {
			return BrowserType.FireFox;
		}
		else if(browserType.equalsIgnoreCase("*iexplore") || browserType.equalsIgnoreCase("iexplore")) {
			return BrowserType.InternetExplore;
		}
		else if(browserType.equalsIgnoreCase("*safari") || browserType.equalsIgnoreCase("safari")) {
			return BrowserType.InternetExplore;
		}
		else {
			return BrowserType.Chrome;
		}
	}
	private String browserType;

    BrowserType(final String type) {
        this.browserType = type;
    }

    public String getBrowserType() {
        return this.browserType;
    }
}
