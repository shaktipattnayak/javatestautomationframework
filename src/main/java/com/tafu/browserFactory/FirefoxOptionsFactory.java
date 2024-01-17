package com.tafu.browserFactory;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


public class FirefoxOptionsFactory {
	
	public static FirefoxOptions setFirefoxOptions() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.disable_beforeunload", true);
		profile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", true);
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(profile);
        options.setAcceptInsecureCerts(true);
        return options;
	}

}
