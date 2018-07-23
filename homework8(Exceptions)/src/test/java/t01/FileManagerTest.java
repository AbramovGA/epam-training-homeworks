package t01;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static t02.PropertiesLoader.loadProps;

class FileManagerTest {

    static Properties properties = loadProps("test.properties");
    static String INITPATH = properties.getProperty("path");
    FileManager fileManager = new FileManager(
            new File(INITPATH));

    private static File randomFile(FileManager fileManager) {
        String filename = String.valueOf(new Random().nextInt());
        return new File(
                String.format("%s/%s", fileManager.getCurrDir().getAbsolutePath(), filename));
    }

    @BeforeEach
    void init() {
        fileManager.setCurrDir(new File(INITPATH));
    }


    @Test
    void enterDirectory() {
        fileManager.enterDirectory("homework8(Exceptions)");
        assertThat(fileManager.getCurrDir().getAbsolutePath(),
                is(properties.getProperty("testEnterPath")));
    }

    @Test
    void createFile() {
        File testFile = randomFile(fileManager);
        fileManager.createFile(testFile.getName());
        assertTrue(testFile.exists());

        testFile.delete();
    }

    @Test
    @SneakyThrows
    void deleteFile() {
        File testFile = randomFile(fileManager);
        testFile.createNewFile();

        fileManager.deleteFile(testFile.getName());
        assertFalse(testFile.exists());

        testFile.delete();

    }

    @Test
    @SneakyThrows
    void writeToFile() {
        File testFile = randomFile(fileManager);
        testFile.createNewFile();

        String data = String.valueOf(new Random().nextInt());

        fileManager.writeToFile(testFile.getName(), data);

        Scanner scanner = new Scanner(testFile);

        assertThat(data, is(scanner.next()));

        testFile.delete();

    }
}