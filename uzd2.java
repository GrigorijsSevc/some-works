// 000RDB000 Jānis Programmētājs 1

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

//================================================

class Student implements Serializable {
    public String name;
    public String surname;
    public int mark1, mark2, mark3;

    public Student(String name, String surname, int mark1, int mark2, int mark3) {
        this.name = name;
        this.surname = surname;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }

    public void print(int numurs) {
        System.out.printf("\n%-4d%-15s%-15s%-12d%-12d%-12d", numurs, name, surname, mark1, mark2, mark3);
    }
}

//================================================

public class uzd2 {

    static Scanner sc = new Scanner(System.in);

    static String filename = "Students.dat";

    public static void main(String[] args) {
        int choise;
        String choiseStr;

        loop: while (true) {

            System.out.println("\n1) Create");
            System.out.println("2) Calculate");
            System.out.println("3) View");
            System.out.println("4) About");
            System.out.println("5) Exit");
            System.out.print("\nInput number from 1 till 5: ");

            choiseStr = sc.nextLine();

            try {
                choise = Integer.parseInt(choiseStr);
                if (choise < 1 || choise > 5) {
                    throw new Exception();
                }
            }
            catch (Exception ex) {
                System.out.println("Error, please, input number from 1 till 5");
                continue;
            }


            switch (choise) {
                case 1:
                    create();
                    break;
                case 2:
                    calculate();
                    break;
                case 3:
                    view();
                    break;
                case 4:
                    about();
                    break;
                case 5:
                    break loop;
            }
        }

        sc.close();
    }

    public static void create() {
        Student student;

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));

            student = new Student("Andris", "Kokins", 5, 4, 3);
            out.writeObject(student);

            student = new Student("Maris", "Lauva", 9, 9, 9);
            out.writeObject(student);

            student = new Student("Edvards", "Ozols", 8, 7, 8);
            out.writeObject(student);

            student = new Student("Mara", "Liepa", 4, 2, 9);
            out.writeObject(student);

            student = new Student("Inga", "Lapsa", 7, 7, 7);
            out.writeObject(student);

            out.close();

            System.out.println("\nFile " + filename + " succesfully created");
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void calculate() {
        HashMap<Integer, Student> students = new HashMap<>();
        int number = 1;
        Student s;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

            boolean EOF = false;

            while (!EOF) {
                try {
                    s = (Student) in.readObject();
                    students.put(number++, s);
                } catch (EOFException e) {
                    EOF = true;
                }
            }
            in.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Write student number");
        int StudentNumber = sc.nextInt();
        if (!students.containsKey(StudentNumber)) {
            System.out.println("no such student");
            return;
        }
        Student student = students.get(StudentNumber);
        System.out.print("mark 1: ");
        int newMark1 = sc.nextInt();
        System.out.print("mark 2: ");
        int newMark2 = sc.nextInt();
        System.out.print("mark 3: ");
        int newMark3 = sc.nextInt();
        student.mark1 = newMark1;
        student.mark2 = newMark2;
        student.mark3 = newMark3;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename)))
        {

            for (Student asd : students.values())
            {
                out.writeObject(asd);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        // TODO develop this method
        System.out.println("\nhello from calculate\n");
    }

    public static void view() {
        File f = new File (filename);
        if (!f.exists()) {
            System.out.println("The file does not exist, please use command \"Create\"");
            return;
        }

        System.out.println("\n-----------------------------------------------------------------------");
        System.out.printf("#   %-15s%-15s%-12s%-12s%-12s", "Name", "Surname", "Math", "Sport", "Programming");
        System.out.print("\n-----------------------------------------------------------------------");

        int numurs = 1;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

            Student s;
            boolean EOF = false;

            while (!EOF) {
                try {
                    s = (Student) in.readObject();
                    s.print(numurs++);
                }
                catch (EOFException e) {
                    EOF = true;
                }
            }

            in.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\n-----------------------------------------------------------------------");
    }

    public static void about() {
        // TODO insert information about authors
        System.out.println("241RDB052 Grigorijs Sevcenko 3");
    }
}

//================================================
