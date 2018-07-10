package t01;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.sql.DriverManager;
import java.sql.ResultSet;

public interface JdbcBasics {
    String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    String CREATE_TABLE_SQL = "create table students (id identity, name varchar not null, group_id int)";
    String INSERT_STUDENT1_SQL = "insert into students (name, group_id) values ('Вася Пупкин', 123456)";
    String INSERT_STUDENT2_SQL = "insert into students (name, group_id) values ('Иван Иванов', 42)";
    String SELECT_ALL_SQL = "select id, name, group_id from students";
    String SELECT_ONE_SQL = "select id, name, group_id from students where id=?";
    String UPDATE_SQL = "update students set group_id=? where name=?";
    String DROP_TABLE_SQL = "DROP TABLE students;";


    String ID_FIELD = "id";
    String NAME_FIELD = "name";
    String GROUP_ID_FIELD = "group_id";

    @SneakyThrows
    static void printQuery(ResultSet resultSet) {
        while (resultSet.next())
            System.out.printf("%d %s %d%n",
                    resultSet.getInt(ID_FIELD),
                    resultSet.getString(NAME_FIELD),
                    resultSet.getInt(GROUP_ID_FIELD));
        System.out.println();

    }


    @SneakyThrows
    static void main(String[] args) {

        @Cleanup var connection = DriverManager.getConnection(DB_URL);
        @Cleanup var statement = connection.createStatement();
        statement.executeUpdate(CREATE_TABLE_SQL);
        statement.executeUpdate(INSERT_STUDENT1_SQL);
        statement.executeUpdate(INSERT_STUDENT2_SQL);

        var selectPS = connection.prepareStatement(SELECT_ALL_SQL);
        @Cleanup ResultSet resultSet = selectPS.executeQuery();
        printQuery(resultSet);

        var updatePS = connection.prepareStatement(UPDATE_SQL);
        updatePS.setInt(1, 42);
        updatePS.setString(2, "Вася Пупкин");
        updatePS.executeUpdate();

        resultSet = selectPS.executeQuery();
        printQuery(resultSet);


        var selectOnePS = connection.prepareStatement(SELECT_ONE_SQL);
        selectOnePS.setInt(1, 1);

        resultSet = selectOnePS.executeQuery();
        printQuery(resultSet);

        statement.executeUpdate(DROP_TABLE_SQL);


    }
}
