package starter.utils;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.guice.Injectors;

import java.util.Properties;
public class SerenityUtils {


    private static Properties properties;

    public static String getProperty(String propertyName) {
        EnvironmentVariables environmentVariables = Injectors.getInjector().getInstance(EnvironmentVariables.class);
        String property =  EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(propertyName);

        return  property;
    }


}
