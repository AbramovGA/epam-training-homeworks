package t04;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface SavingLoading {

    @SneakyThrows
    default void save(Object object, String outFilePath) {
        var fileOutputStream = new FileOutputStream(outFilePath);
        @Cleanup var objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(object);
    }

    @SneakyThrows
    default Object load(String inputFilePath) {
        var fileInputStream = new FileInputStream(inputFilePath);
        @Cleanup var objectInputStream = new ObjectInputStream(fileInputStream);

        return objectInputStream.readObject();
    }


}
