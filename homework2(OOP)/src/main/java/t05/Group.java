package t05;


import lombok.Data;

import java.util.HashMap;


@Data
public class Group<T extends Number> {

    final int id;
    final Courses course;
    HashMap<Student, T> studentsMarks = new HashMap<>();


    public void addStudent(Student student, T mark) {
        studentsMarks.put(student, mark);
    }

    public T getMark(Student student) {
        return studentsMarks.get(student);
    }
}
