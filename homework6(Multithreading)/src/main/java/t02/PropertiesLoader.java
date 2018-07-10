package t02;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Properties;

import static java.util.Map.Entry;


public interface PropertiesLoader {

    Object locker = new Object();


    static void main(String[] args) {

        var res = loadProps("test.properties");

        for (Entry entry :
                res.entrySet()) {
            System.out.printf("%s - %s%n", entry.getKey(), entry.getValue());
        }

    }

    @SneakyThrows
    static Properties loadProps(String fileName) {
        synchronized (locker) {
            @Cleanup InputStream inputStream = PropertiesLoader.class.getResourceAsStream(("/" + fileName));
            var property = new Properties();
            property.load(inputStream);
            return property;
        }
    }

}
