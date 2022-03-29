package utilities;

import java.io.File;

public class Constants {
    public static final String USER_DIR = System.getProperty("user.dir") + File.separator;
    public static final String TESTDATA_DIR = USER_DIR + "Data.xlsx";
    public static final String TESTDATA_SHEETNAME = "Test";
    public static final String RESOURCES = USER_DIR + "src" + File.separator + "main" + File.separator + "resources"
	    + File.separator;
    public static final String FIREFOX = "Firefox";
    public static final String CONTENTYPE ="application/json; charset=utf-8";
    public static final String CHROME = "Chrome";
    public static final String IE = "IE";
    public static final String EDGE = "Edge";
    public static final String CHCAPSPATH = USER_DIR + "Capabilites" + File.separator + "Web" + File.separator
	    + "Chrome.json";
    public static final String MECAPSPATH = USER_DIR + "Capabilites" + File.separator + "Web" + File.separator
	    + "Edge.json";

}
