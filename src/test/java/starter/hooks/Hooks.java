package starter.hooks;

import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.images.ImageRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import starter.globalvariables.ApplitoolsGlobalVariables;
import starter.utils.ApplitoolsUtils;

public class Hooks {

    @Before(value = "@applitools")
    public void openEyes(Scenario scenario) {
        ApplitoolsUtils.setupEyes(scenario);
    }


}
