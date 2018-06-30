package javaParsers.t02;

import javaParsers.JavaParser;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SymbolJavaParser implements JavaParser {

    private static void putValue(Map<String, Integer> map, String value) {
        if (map.containsKey(value))
            map.replace(value, map.get(value) + 1);
        else
            map.put(value, 1);
    }

    @SneakyThrows
    @Override
    public Map<String, Integer> parse(String inFilePath) {
        var fileReader = new FileReader(inFilePath);
        @Cleanup var input = new Scanner(fileReader);
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
        @Cleanup var fileWriter = new FileWriter(outFilePath);

        for (Map.Entry<String, Integer> entry : values.entrySet()) {
            fileWriter.write(entry.getKey());
            fileWriter.write(" ");
            fileWriter.write(entry.getValue().toString());
            fileWriter.write("\n");
        }
    }

}
