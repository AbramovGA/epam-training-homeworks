package t02;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

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
    public HashMap<String, String> loadProps(String fileName) {
        property.load(PropertiesLoader.class.getResourceAsStream("/" + fileName));
        var props = new HashMap<String, String>();
        Set<Entry<Object, Object>> values = property.entrySet();
        for (Entry entry :
                values) {
            props.put((String) entry.getKey(), (String) entry.getValue());
        }

        return props;

    }

}
