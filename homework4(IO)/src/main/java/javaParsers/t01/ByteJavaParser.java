package javaParsers.t01;

import javaParsers.JavaParser;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ByteJavaParser implements JavaParser {

    private static void putValue(Map<String, Integer> map, String value) {
        if (map.containsKey(value))
            map.replace(value, map.get(value) + 1);
        else
            map.put(value, 1);
    }

    @SneakyThrows
    @Override
    public Map<String, Integer> parse(String inFilePath) {
        var fileInputStream = new FileInputStream(inFilePath);
        @Cleanup var input = new Scanner(fileInputStream);
        String portion;
        var result = new HashMap<String, Integer>();


        while (input.hasNext()) {
            portion = input.next();
            for (String keyword : keywords) {
                if (portion.contains(keyword))
                    putValue(result, keyword);
            }
        }

        return result;
    }

    @SneakyThrows
    @Override
    public void writeToFile(String outFilePath, Map<String, Integer> values) {
        @Cleanup var fileOutputStream = new FileOutputStream(outFilePath);

        for (Entry<String, Integer> entry : values.entrySet()) {
            fileOutputStream.write(entry.getKey().getBytes());
            fileOutputStream.write(" ".getBytes());
            fileOutputStream.write(entry.getValue().toString().getBytes());
            fileOutputStream.write("\n".getBytes());
        }
    }

}
