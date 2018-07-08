package t02;

import lombok.SneakyThrows;

import java.util.Properties;

import static java.util.Map.Entry;


public class PropertiesLoader {

    private Properties property = new Properties();

    public static void main(String[] args) {

        var loader = new PropertiesLoader();

        var res = loader.loadProps("test.properties");

        for (Entry entry :
                res.entrySet()) {
            System.out.printf("%s - %s%n", entry.getKey(), entry.getValue());
        }

    }

    @SneakyThrows
    public Properties loadProps(String fileName) {
        property.load(PropertiesLoader.class.getResourceAsStream("/" + fileName));
        return property;
    }

}
