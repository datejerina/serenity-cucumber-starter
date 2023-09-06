package starter.globalvariables;

import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.images.Eyes;
import com.applitools.eyes.images.ImageRunner;
import com.applitools.eyes.config.Configuration;


public class ApplitoolsGlobalVariables {
    public static EyesRunner runner = new ImageRunner();
    public static Eyes eyes = new Eyes();
    public static Configuration config = new Configuration();
}
