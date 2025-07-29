package rubrica.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;


public class FileManage {
    private static final String FILE_NAME = "Persona";
    private static final String DIR_NAME = "informazioni";
    private File file;
    private File directory;
    public FileManage() {
        this.directory = new File(DIR_NAME);
        if (!directory.exists()) {
            this.directory.mkdir();
        }
    }

    public Vector<Person> readFromDirectory() {
        Vector<Person> persons = new Vector<>();
        if (this.directory.listFiles().length > 0) {
            for (File f: this.directory.listFiles()) {
                persons.add(readFile(f));
            }
        }
        return persons;
    }
    private Person readFile(File file) {
        Scanner scanner;
        Person p = null;
        try {
            scanner = new Scanner(file);
            if (scanner.hasNextLine()){
  
                String line = scanner.nextLine();
                String[] parts = line.split(";", -1);

                if (parts.length == 5) { 
                    p = new Person(parts[0], parts[1], parts[2], parts[3],
                            parts[4].isEmpty() ? null : Integer.parseInt(parts[4]));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return p;
    }
    public void saveToDirectory(Vector<Person> persons) {
        for(File f: this.directory.listFiles()) {
            f.delete();
        }
        int i = 0;
        for(Person p : persons) {
            File f = new File(this.directory, FILE_NAME + Integer.toString(i)+ ".txt");
            saveToFile(f, p);
            i++;
        }
    }

    private void saveToFile(File f, Person p) {
        PrintStream printStream;
        try {
            printStream = new PrintStream(f);
            printStream.printf("%s;%s;%s;%s;%s\n", p.getName(), p.getSurname(), p.getAddress(),
            p.getPhoneNumber(), p.getAge() == null ? "" : p.getAge());
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
