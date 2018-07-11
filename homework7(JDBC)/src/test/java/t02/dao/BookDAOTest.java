package t02.dao;

import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.*;
import t02.cp.ConnectionPool;
import t02.dao.model.Book;
import t02.functionalMagic.CheckedRunnable;

import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

@FieldDefaults(level = PRIVATE, makeFinal = true)
class BookDAOTest {

    static ConnectionPool CONNECTION_POOL = ConnectionPool.fromProps("jdbc");
    static BookDAO BOOK_DAO = CONNECTION_POOL::get;
    static long INITIAL_COUNT = BOOK_DAO.count();

    @SuppressWarnings("SpellCheckingInspection")
    Book newWorld = BOOK_DAO.save(new Book("Brave new world", "Huxley"));

    @AfterAll
    static void tearDown() {
        CONNECTION_POOL.close();
    }

    @Test
    @DisplayName("Save method works correctly")
    void testSave() {
        assertThat(newWorld.getId(), not(0L));
        BOOK_DAO.delete(newWorld);
    }

    @Test
    @DisplayName("FindAll method works correctly")
    void testFindAll() {
        assertThat(BOOK_DAO.mapAll(Stream::count), is(INITIAL_COUNT + 1));
        BOOK_DAO.delete(newWorld);
    }

    @Test
    @DisplayName("Update method works correctly")
    void testUpdate() {
        Book book1 = BOOK_DAO.findById(newWorld.getId()).orElseThrow(() -> {
            CheckedRunnable.narrow(Assertions::fail).unchecked();
            return null;
        });
        book1.setAuthor("Vasya Pupkin");
        BOOK_DAO.update(book1);
        Book book2 = BOOK_DAO.findById(newWorld.getId()).orElseThrow(() -> {
            CheckedRunnable.narrow(Assertions::fail).unchecked();
            return null;
        });
        assertEquals(book2, book1);
    }

    @Test
    @DisplayName("Delete method works correctly")
    void testDelete() {
        Book book = BOOK_DAO.findById(newWorld.getId())
                .orElseThrow(() -> {
                    CheckedRunnable.narrow(Assertions::fail).unchecked();
                    return null;
                });

        assertThat(BOOK_DAO.delete(book).count(), is(0L));
    }

    @Test
    @DisplayName("Clear method works correctly")
    void testClear() {
        assertThat(BOOK_DAO.clear().count(), is(0L));
    }

    @AfterEach
    void tearDown2() {
        BOOK_DAO.delete(newWorld);
    }
}