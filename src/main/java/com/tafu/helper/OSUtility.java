package com.tafu.helper;

import com.tafu.driverHelper.DriverConfig;

public class OSUtility {
	
	public static String getOSName() {
		return System.getProperty("os.name");
	}
	
	public static boolean isMac() {
		return getOSName().toLowerCase().startsWith("mac");
	}

	public static boolean isWindows() {
		return getOSName().toLowerCase().startsWith("win");
	}
	
	public static boolean isUnix() {
		return (getOSName().toLowerCase().contains("nix") || getOSName().toLowerCase().contains("nux"));
	}
	
//	public static String getOSBits() {
//        return System.getProperty("os.arch");
//    }
//
//    public static boolean is32() {
//        return getOSBits().equals("x86");
//    }
//
//    public static boolean is64() {
//        if (isWindows()) {
//            return (System.getenv("ProgramW6432") != null);
//        } else {
//            return !getOSBits().equals("x86");
//        }
//    }
//    
//    public static String getSlash() {
//        if (isWindows()) {
//            return "\\";
//        } else {
//            return "/";
//        }
//    }
	
	/***
     * Get Drivers Directory
     * @return Drivers Directory
     */
    public static String getDriversDirectory() {
    	if(OSUtility.getOSName().toLowerCase().contains("mac")) {
    		DriverConfig.DRIVERS_DIRECTORY = "/Resources/";
    	}
    	else if(OSUtility.getOSName().toLowerCase().contains("win")) {
    		DriverConfig.DRIVERS_DIRECTORY = "\\Resources\\";
    	}
    	return DriverConfig.DRIVERS_DIRECTORY;
    }

}
