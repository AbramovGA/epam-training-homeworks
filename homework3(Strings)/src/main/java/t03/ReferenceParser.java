package t03;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReferenceParser {
    private Pattern referencePattern = Pattern.compile("[р,Р]ис(ун[а-я]*|.) \\d+");
    private Pattern numberPattern = Pattern.compile("\\d+");

    public static void main(String[] args) {
        var referenceParser = new ReferenceParser();
        System.out.println(referenceParser.isConsistentRef(
                ReferenceParser
                        .class
                        .getResourceAsStream("/index.html")
        ));
    }

    @SneakyThrows
    boolean isConsistentRef(InputStream file) {

        @Cleanup var input = new Scanner(new InputStreamReader(file, "cp1251"));

        int prevNumber = -1;
        int currNumber;

        String currRef = input.findWithinHorizon(referencePattern, 0);
        Matcher matcher;
        while (currRef != null) {
            matcher = numberPattern.matcher(currRef);
            currNumber = Integer.valueOf(matcher.group());
            if (currNumber < prevNumber) {
                return false;
            } else {
                prevNumber = currNumber;
            }
            currRef = input.findWithinHorizon(referencePattern, 0);
        }

        return true;
    }


}
