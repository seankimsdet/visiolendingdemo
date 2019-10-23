package RulesEngineModel;

import java.io.*;
import java.util.Properties;

public class Utils {

    public Utils() {
    }

    private static final String configFile = "src/main/resources/config.properties";

    private void CreateProperties() {

        try (OutputStream output = new FileOutputStream(configFile)) {
            Properties prop = new Properties();

            //set key and value
            prop.setProperty("productRules.fileName", "src/main/resources/productRules.json");

            //save properties
            prop.store(output, null);
        }
        catch (IOException io) {
            io.printStackTrace();
        }
    }

    private static String GetProperties(String propertyKey) {

        Properties prop = new Properties();
        try(InputStream input = new FileInputStream(configFile)) {

            //load properties file
            prop.load(input);

        } catch (IOException io) {
            io.printStackTrace();
        }

        return prop.getProperty(propertyKey);
    }

    static Props RulesFileName = () -> GetProperties("productRules.fileName");

}

interface Props {
    String getValue();
}