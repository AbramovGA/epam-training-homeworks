package t02;

import lombok.Cleanup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import t01.FileManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.util.Map.Entry;


public interface PropertiesLoader {

    Object locker = new Object();
    Logger logger = LoggerFactory.getLogger(FileManager.class);

    String NO_FILE_MESSAGE = "There is either no\n" +
            "     *          resource with this name is found, the resource is in a package\n" +
            "     *          that is not open to at\n" +
            "     *          least the caller module, or access to the resource is denied\n" +
            "     *          by the security manager.";


    static void main(String[] args) {

        var res = loadProps("test.properties");

        for (Entry entry :
                res.entrySet()) {
            System.out.printf("%s - %s%n", entry.getKey(), entry.getValue());
        }

    }


    static Properties loadProps(String fileName) {
        synchronized (locker) {
            @Cleanup InputStream inputStream = PropertiesLoader.class.getResourceAsStream(("/" + fileName));

            if (inputStream == null)
                logger.info(NO_FILE_MESSAGE);

            var property = new Properties();
            try {
                property.load(inputStream);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            return property;
        }
    }

}
