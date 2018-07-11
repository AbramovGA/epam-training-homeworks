package t02.dao;


import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import t02.dao.model.Book;
import t02.functionalMagic.CheckedRunnable;
import t02.functionalMagic.StreamUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@FunctionalInterface
public interface BookDAO extends JdbcDao<Book>, Supplier<Connection> {

    String INSERT_SQL = "insert into books (name, author) values (?, ?)";
    String GET_ALL_SQL = "select id, name, author from books";
    String UPDATE_SQL = "update books set name = ?, author = ? where id = ?";
    String GET_SQL = "select name, author from books where id = ?";
    String DELETE_SQL = "delete from books where id = ?";
    String DELETE_ALL_SQL = "delete from books";
    String COUNT_SQL = "select count(id) from books";

    String ID_FIELD = "id";
    String NAME_FIELD = "name";
    String AUTHOR_FIELD = "author";

    BiFunction<ResultSet, Long, Book> ROW_MAPPER = CheckedFunction2.<ResultSet, Long, Book>of(
            (resultSet, id) -> new Book(id,
                    resultSet.getString(NAME_FIELD),
                    resultSet.getString(AUTHOR_FIELD)))
            .unchecked();

    Function<ResultSet, Book> ID_ROW_MAPPER = CheckedFunction1.<ResultSet, Book>of(resultSet ->
            ROW_MAPPER.apply(resultSet, resultSet.getLong(ID_FIELD)))
            .unchecked();

    @NotNull
    @Override
    @SneakyThrows
    default Book save(@NotNull Book book) {
        @Cleanup Connection connection = get();
        @Cleanup val ps = connection.prepareStatement(INSERT_SQL, RETURN_GENERATED_KEYS);
        ps.setString(1, book.getName());
        ps.setString(2, book.getAuthor());
        ps.executeUpdate();
        @Cleanup ResultSet rs = ps.getGeneratedKeys();
        if (!rs.next())
            throw new RuntimeException("Не был сгенерирован ключ!");

        //noinspection unchecked
        book.setId(rs.getInt(1));
        return book;
    }

    /**
     * @return Closeable Stream (!!!) of Books
     */
    @NotNull
    @Override
    @SneakyThrows
    default Stream<Book> findAll() {
        val connection = get();
        val statement = connection.createStatement();
        val resultSet = statement.executeQuery(GET_ALL_SQL);
        //noinspection unchecked
        return StreamUtils.toStream(resultSet, ID_ROW_MAPPER)
                .onClose(CheckedRunnable.narrow(resultSet::close).unchecked())
                .onClose(CheckedRunnable.narrow(statement::close).unchecked())
                .onClose(CheckedRunnable.narrow(connection::close).unchecked());
    }

    @NotNull
    @Override
    @SneakyThrows
    default Book update(@NotNull Book Book) {
        @Cleanup val connection = get();
        @Cleanup val preparedStatement = connection.prepareStatement(UPDATE_SQL);
        preparedStatement.setString(1, Book.getName());
        preparedStatement.setString(2, Book.getAuthor());
        preparedStatement.setLong(3, Book.getId());
        preparedStatement.executeUpdate();
        return Book;
    }

    @NotNull
    @Override
    @SneakyThrows
    default JdbcDao<Book> delete(@NotNull Book Book) {
        @Cleanup val connection = get();
        @Cleanup val preparedStatement = connection.prepareStatement(DELETE_SQL);
        preparedStatement.setLong(1, Book.getId());
        preparedStatement.executeLargeUpdate();
        return this;
    }

    @NotNull
    @Override
    @SneakyThrows
    default Optional<Book> findById(long id) {
        @Cleanup val connection = get();
        @Cleanup val preparedStatement = connection.prepareStatement(GET_SQL);
        preparedStatement.setLong(1, id);
        @Cleanup val resultSet = preparedStatement.executeQuery();
        //noinspection unchecked
        return Optional.ofNullable(resultSet.next() ?
                ROW_MAPPER.apply(resultSet, id) :
                null);
    }

    @NotNull
    @Override
    @SneakyThrows
    default JdbcDao<Book> clear() {
        @Cleanup val connection = get();
        @Cleanup val preparedStatement = connection.prepareStatement(DELETE_ALL_SQL);
        preparedStatement.executeLargeUpdate();
        return this;
    }

    @Override
    @SneakyThrows
    default long count() {
        @Cleanup val connection = get();
        @Cleanup val statement = connection.createStatement();
        @Cleanup val resultSet = statement.executeQuery(COUNT_SQL);
        return resultSet.next() ? resultSet.getLong(1) : 0L;
    }
}
