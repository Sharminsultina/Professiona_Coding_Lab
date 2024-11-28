import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Error: Please provide exactly one command-line argument.");
            System.out.println("Usage:");
            System.out.println("  l  - List all employees");
            System.out.println("  s  - Show a random employee");
            System.out.println(" +<name> - Add a new employee");
            System.out.println(" ?<name> - Search for an employee");
            System.out.println("  c  - Count words and characters");
            System.out.println(" u<name> - Update an employee name");
            System.out.println(" d<name> - Delete an employee");
            return;
        }

        // Check arguments
        if (args[0].equals("l")) {
            loadAndListEmployees();
        } else if (args[0].equals("s")) {
            loadAndShowRandomEmployee();
        } else if (args[0].contains("+")) {
            addEmployee(args[0].substring(1));
        } else if (args[0].contains("?")) {
            searchEmployee(args[0].substring(1));
        } else if (args[0].contains("c")) {
            countWordsAndCharacters();
        } else if (args[0].contains("u")) {
            updateEmployeeName(args[0].substring(1));
        } else if (args[0].contains("d")) {
            deleteEmployee(args[0].substring(1));
        }
    }

    private static void loadAndListEmployees() {
        System.out.println("Loading data ...");
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
            String[] employees = r.readLine().split(",");
            for (String emp : employees) {
                System.out.println(emp.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded.");
    }

    private static void loadAndShowRandomEmployee() {
        System.out.println("Loading data ...");
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
            String[] employees = r.readLine().split(",");
            Random rand = new Random();
            System.out.println(employees[rand.nextInt(employees.length)].trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded.");
    }

    private static void addEmployee(String name) {
        System.out.println("Loading data ...");
        try (BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt", true))) {
            w.write(", " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded.");
    }

    private static void searchEmployee(String name) {
        System.out.println("Loading data ...");
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
            String[] employees = r.readLine().split(",");
            boolean found = Arrays.stream(employees).anyMatch(emp -> emp.trim().equals(name));
            if (found) {
                System.out.println("Employee found!");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded.");
    }

    private static void countWordsAndCharacters() {
        System.out.println("Loading data ...");
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
            String line = r.readLine();
            char[] chars = line.toCharArray();
            int wordCount = line.isEmpty() ? 0 : line.trim().split("\\s+").length;
            System.out.println(wordCount + " word(s) found, " + chars.length + " character(s).");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded.");
    }

    private static void updateEmployeeName(String name) {
        System.out.println("Loading data ...");
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
            String[] employees = r.readLine().split(",");
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].trim().equals(name)) {
                    employees[i] = "Updated"; // or any other update logic
                }
            }
            try (BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt"))) {
                w.write(String.join(",", employees));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Data Updated.");
    }

    private static void deleteEmployee(String name) {
        System.out.println("Loading data ...");
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("employees.txt")))) {
            List<String> list = new ArrayList<>(Arrays.asList(r.readLine().split(",")));
            list.removeIf(emp -> emp.trim().equals(name));
            
            try (BufferedWriter w = new BufferedWriter(new FileWriter("employees.txt"))) {
                w.write(String.join(",", list));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Data Deleted.");
    }
}