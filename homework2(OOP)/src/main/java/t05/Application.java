package t05;


import lombok.Data;

import java.util.ArrayList;


@Data
public class Application {

    ArrayList<Group> groupList = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Application application = new Application();

        application.addGroup(0, Courses.STATISTICS);
        application.addGroup(1, Courses.DISCRETE_MATH);

        Student stud1 = new Student("A");
        Student stud2 = new Student("B");

        application.groupList.get(0).addStudent(stud1, 5.);
        application.groupList.get(0).addStudent(stud2, 3.);
        application.groupList.get(1).addStudent(stud1, 4);

        ArrayList<Group> stud1Groups = application.getGroupsByStudent(stud1);
        System.out.println(stud1Groups);
        System.out.println(application.getAverageMark(stud1));


    }

    public boolean addGroup(int id, Courses course) {
        return groupList.add(new Group(id, course));
    }

    public ArrayList<Group> getGroupsByStudent(Student student) {

        var studentGroups = new ArrayList<Group>();

        for (Group group : groupList) {
            if (group.getMark(student) != null)
                studentGroups.add(group);
        }
        return studentGroups;

    }

    public double getAverageMark(Student student) {
        ArrayList<Group> studentGroups = this.getGroupsByStudent(student);
        double averageMark = 0;
        for (Group group :
                studentGroups) {
            averageMark += group.getMark(student).doubleValue();
        }

        return averageMark / studentGroups.size();
    }
}
