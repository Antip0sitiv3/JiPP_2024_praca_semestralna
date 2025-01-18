package App;

import java.io.*;
import java.util.*;

public class Utils {
    static Company initData() {
        Person firstPerson = new Person("Jan", "Testowy");
        Person secondPerson = new Person("Kazimierz", "Przykładowy");
        Person thirdPerson = new Person("Maria", "Demo");

        Company company = new Company("Super Firma");
        company.addEmployee(firstPerson.getName(), firstPerson.getSurname(), "SALES");
        company.addEmployee(secondPerson.getName(), secondPerson.getSurname(), "ADMINISTRATION");
        company.addEmployee(thirdPerson.getName(), thirdPerson.getSurname(), "FINANCE");

        return company;
    }

    static void printBanner(String text) {
        String border = "+" + "-".repeat(text.length() + 2) + "+";
        System.out.println(border);
        System.out.println("| " + text + " |");
        System.out.println(border);
    }

    static void printAnswer(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    static int inputInt(String message) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print(message);
            return input.nextInt();
        } catch (InputMismatchException e) {
            printAnswer("Podano błędną wartość, spróbuj jeszcze raz");
            return inputInt(message);
        }
    }

    static String inputString(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        return input.nextLine();
    }

    static void displayEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            printAnswer("Brak pracowników do wyświetlenia.");
        } else {
            System.out.println("Lista pracowników:");
            for (Employee employee : employees) {
                System.out.println("- " + employee);
            }
        }
    }

    static void displayDepartmentStats(Map<String, Integer> stats) {
        System.out.println("Statystyki działów:");
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            System.out.printf("%s: %d pracowników%n", entry.getKey(), entry.getValue());
        }
    }

    static void saveCompanyData(Company company, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(company);
            printAnswer("Dane firmy zostały zapisane do pliku: " + fileName);
        } catch (IOException e) {
            printAnswer("Błąd podczas zapisywania danych: " + e.getMessage());
        }
    }

    static Company loadCompanyData(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Company company = (Company) ois.readObject();
            printAnswer("Dane firmy zostały wczytane z pliku: " + fileName);
            return company;
        } catch (IOException | ClassNotFoundException e) {
            printAnswer("Błąd podczas wczytywania danych: " + e.getMessage());
            return null;
        }
    }
}
