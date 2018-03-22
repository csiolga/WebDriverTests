package Task80.Browser;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private static Map<String, Object> prefs;
    private static ChromeOptions options;
    private static DesiredCapabilities capabilities;

    public static DesiredCapabilities capabilities() {
        prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "C:\\test");
        options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return capabilities;
    }
}
