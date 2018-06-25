package t01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.MINUTES;

public class CrazyLogger {

    private StringBuilder logBuffer = new StringBuilder();

    public static void main(String[] args) {

        var logger = new CrazyLogger();

        for (int i = 0; i < 20; i++) {
            logger.log("Hello world!" + i);
        }

        logger.printLog("0");

    }

    public void log(String message) {
        logBuffer.append(String.format("%s - %s;\n", LocalDateTime.now()
                .truncatedTo(MINUTES)
                .format(DateTimeFormatter.ofPattern("dd-MM-YYYY : hh-mm")), message));
    }

    public StringBuilder find(String text) {

        var result = new StringBuilder();

        int index = logBuffer.indexOf(text);

        String nextSubStr;

        while (index >= 0) {
            nextSubStr = logBuffer.substring(
                    logBuffer.substring(0, index).lastIndexOf("\n") + 1,
                    logBuffer.indexOf("\n", index)
            );
            if (result.indexOf(nextSubStr) < 0)
                result.append(String.format("%s\n", nextSubStr));
            index = logBuffer.indexOf(text, index + 1);

        }
        return result;
    }

    public void printLog(String text) {
        System.out.println(find(text));
    }

}
