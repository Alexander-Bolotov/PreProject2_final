package src.main.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class ReadProperties {
    String readProperties(){
        String filename = "/TypeDAO.properties";
        String daoType = "";
        try(InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            daoType = properties.getProperty("typeDAO");
            return daoType;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return daoType;
    }
}
