package rubrica.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;


public class FileManage {
    private static final String FILE_NAME = "informazioni.txt";
    private File file;

    public FileManage() {
        this.file = new File(FILE_NAME);
        if (!this.file.exists()) {
            System.out.println("non esisyre");
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Vector<Person> readFile() {
        Vector<Person> persons = new Vector<>();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Linea " + line);
                String[] parts = line.split(";", -1);

                if (parts.length == 5) { 
                    System.out.println(parts[4].isEmpty());
                    Person p = new Person(parts[0], parts[1], parts[2], parts[3],
                            parts[4].isEmpty() ? null : Integer.parseInt(parts[4]));
                    persons.add(p);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return persons;
    }

    public void saveToFile(Vector<Person> persons) {
        PrintStream printStream;
        try {
            printStream = new PrintStream(file);
            for (Person p : persons) {
                printStream.printf("%s;%s;%s;%s;%s\n", p.getName(), p.getSurname(), p.getAddress(),
                        p.getPhoneNumber(), p.getAge() == null ? "" : p.getAge());
            }
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
