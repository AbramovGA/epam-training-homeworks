package t02;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InfoTable {
    private Locale locale = Locale.getDefault();
    private ResourceBundle bundle;

    public static void main(String[] args) {

        InfoTable infoTable = new InfoTable();
        while (true) {
            infoTable.chooseLocale();
            infoTable.chooseQuestion();
        }

    }

    private void setLocale(Locale locale) {
        this.locale = locale;
        bundle = ResourceBundle.getBundle("prop", locale);
    }

    public void chooseLocale() {


        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean check;

        do {
            showLocaleOptions();

            check = false;
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    setLocale(Locale.ENGLISH);
                    break;
                case 2:
                    setLocale(new Locale("ru"));
                    break;
                case -1:
                    System.exit(0);
                default:
                    System.out.println("Please print one of the available options.");
                    check = true;
                    break;
            }
        } while (check);

    }

    private void showQuestions() {
        System.out.println("Press the number of question to see the answer:\n");
        System.out.printf("1.%s%n2.%s%n3.%s%n%nPress '0' to change language%nPress '-1' to exit program%n%n%n", bundle.getString("question1"),
                bundle.getString("question2"),
                bundle.getString("question3"));
    }

    private void showLocaleOptions() {
        System.out.println("Choose language:\nPress 1 for english\nPress 2 for russian\nPress '-1' to exit program\n");
    }

    public void chooseQuestion() {

        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean check;


        do {
            check = true;
            showQuestions();

            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    check = false;
                case 1:
                    System.out.println("Answer:\n" + bundle.getString("answer1"));
                    break;
                case 2:
                    System.out.println("Answer:\n" + bundle.getString("answer2"));
                    break;
                case 3:
                    System.out.println("Answer:\n" + bundle.getString("answer3"));
                    break;
                case -1:
                    System.exit(0);
                default:
                    System.out.println("Please print one of the available options.");
                    break;
            }
        } while (check);

    }

}
