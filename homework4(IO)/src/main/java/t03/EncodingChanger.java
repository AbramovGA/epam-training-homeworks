package t03;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class EncodingChanger {

    @SneakyThrows
    public static void changeEncoding(String inFilePath, String outFilePath, String currEncoding, String newEncoding) {

        @Cleanup var input = new InputStreamReader(new FileInputStream(inFilePath), currEncoding);

        @Cleanup var output = new OutputStreamWriter(new FileOutputStream(outFilePath), newEncoding);

        while (input.ready())
            output.write(input.read());

    }

}
