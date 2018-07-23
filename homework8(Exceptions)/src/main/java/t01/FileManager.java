package t01;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@AllArgsConstructor
@Data
public class FileManager {
    private static Logger logger = LoggerFactory.getLogger(FileManager.class);
    private File currDir;

    FileManager enterDirectory(String directory) {

        File nextDir = new File(String.format("%s/%s", currDir.getAbsolutePath(), directory));

        if (nextDir.isDirectory())
            currDir = nextDir;
        else {
            System.out.println("Not a directory.");
        }


        return this;
    }

    FileManager showFiles() {
        File[] files = currDir.listFiles();
        System.out.printf("In directory %s:%n", currDir.getAbsolutePath());
        for (File file :
                files) {
            System.out.println(file.getName());
        }
        return this;
    }

    FileManager createFile(String filename) {

        File newFile = new File(String.format("%s/%s", currDir.getAbsolutePath(), filename));

        try {
            if (!newFile.createNewFile())
                System.out.println("File with this name already exists.");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return this;
    }

    FileManager deleteFile(String filename) {

        File forDelete = new File(String.format("%s/%s", currDir.getAbsolutePath(), filename));

        if (!forDelete.delete())
            System.out.println("File was not deleted for some reason.");

        return this;
    }

    FileManager writeToFile(String filename, String data) {

        File forWrite = new File(String.format("%s/%s", currDir.getAbsolutePath(), filename));

        if (!forWrite.exists()) {
            System.out.println("File doesn't exist");
            return this;
        }

        try (FileWriter writer = new FileWriter(forWrite)) {
            writer.write(data);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return this;
    }
}