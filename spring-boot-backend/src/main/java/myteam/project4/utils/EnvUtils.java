package myteam.project4.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

public class EnvUtils {

    public static String getProperty(String key) {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        if (context != null) {
            Environment environment = context.getBean(Environment.class);
//            System.out.println(environment.getProperty(key));
            return environment.getProperty(key);
        } else {
            return null;
        }
    }

}
