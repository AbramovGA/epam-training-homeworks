package t01;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.log4j.Logger;

import java.io.FileWriter;

import static t01.Ink.Color.BLUE;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pen {

    private static Logger logger = Logger.getLogger(Pen.class);
    Ink ink;

    public Pen() {
        this.ink = new Ink(1000, BLUE);
    }

    @SneakyThrows
    public void write(String text, String path) {

        try (FileWriter writer = new FileWriter(path)) {

            int textLength = text.length();
            if (ink.getInkAmount() < textLength) {
                writer.write(text, 0, ink.getInkAmount());
                ink.setInkAmount(0);
                logger.info("Refill the pen to continue writing.");
            } else {
                writer.write(text);
                ink.setInkAmount(ink.getInkAmount() - text.length());
            }
        }
    }

    public void refill(Ink ink) {
        this.ink = ink;
    }


}
