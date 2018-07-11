package t02.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import t02.dao.Identifiable;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Book implements Identifiable<Book> {

    long id;
    String name;
    String author;

    public Book(String name, String author) {
        this(0L, name, author);
    }

    @Override
    public Book setId(long id) {
        this.id = id;
        return this;
    }
}
