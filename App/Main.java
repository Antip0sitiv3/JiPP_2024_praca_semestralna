package App;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Company company = null;
        String dataFileName = "company_data.dat";

        // Attempt to load data from file
        File dataFile = new File(dataFileName);
        if (dataFile.exists()) {
            company = Utils.loadCompanyData(dataFileName);
        }

        if (company == null) {
            company = Utils.initData();
        }

        Utils.printBanner("Panel administracyjny firmy " + company.getName());

        boolean done = false;
        while (!done) {
            System.out.println("Dostępne operacje:");
            System.out.println("1 - Wyświetl pracowników");
            System.out.println("2 - Dodaj pracownika");
            System.out.println("3 - Zwolnij pracownika");
            System.out.println("4 - Wyszukaj pracownika");
            System.out.println("5 - Edytuj dane pracownika");
            System.out.println("6 - Wyświetl statystyki firmy");
            System.out.println("7 - Zarządzaj działami (dodaj/usuwaj)");
            System.out.println("8 - Zapisz dane do pliku");
            System.out.println("9 - Wczytaj dane z pliku");
            System.out.println("0 - Zakończ program");
            System.out.println();

            int choice = Utils.inputInt("Wybierz operację: ");

            switch (choice) {
                case 1:
                    Utils.displayEmployees(company.getEmployees());
                    break;
                case 2:
                    String name = Utils.inputString("Podaj imię: ");
                    String surname = Utils.inputString("Podaj nazwisko: ");
                    String deptName;
                    while (true) {
                        deptName = Utils.inputString("Podaj dział (np. SALES, ADMINISTRATION lub nowy): ");
                        if (company.getDepartmentNames().contains(deptName.toUpperCase())) {
                            break;
                        } else {
                            Utils.printAnswer("Dział nie istnieje, spróbuj ponownie lub dodaj nowy dział z opcji 7.");
                        }
                    }
                    company.addEmployee(name, surname, deptName.toUpperCase());
                    Utils.printAnswer("Dodano pomyślnie pracownika: %s %s".formatted(name, surname));
                    break;
                case 3:
                    String lastName = Utils.inputString("Podaj nazwisko pracownika do zwolnienia: ");
                    boolean removed = company.removeEmployee(lastName);
                    if (removed) {
                        Utils.printAnswer("Zwolniono pracownika: " + lastName);
                    } else {
                        Utils.printAnswer("Nie znaleziono pracownika o nazwisku: " + lastName);
                    }
                    break;
                case 4:
                    String searchLastName = Utils.inputString("Podaj nazwisko pracownika do wyszukania: ");
                    List<Employee> foundEmployees = company.searchEmployeesByLastName(searchLastName);
                    if (!foundEmployees.isEmpty()) {
                        Utils.displayEmployees(foundEmployees);
                    } else {
                        Utils.printAnswer("Nie znaleziono pracownika o nazwisku: " + searchLastName);
                    }
                    break;
                case 5:
                    String editLastName = Utils.inputString("Podaj nazwisko pracownika do edycji: ");
                    boolean edited = company.editEmployeeData(editLastName);
                    if (edited) {
                        Utils.printAnswer("Dane pracownika zostały zaktualizowane.");
                    } else {
                        Utils.printAnswer("Nie znaleziono pracownika o nazwisku: " + editLastName);
                    }
                    break;
                case 6:
                    Utils.printAnswer("Statystyki firmy:");
                    Utils.displayDepartmentStats(company.getDepartmentStats());
                    break;
                case 7:
                    System.out.println("1 - Dodaj dział");
                    System.out.println("2 - Usuń dział");
                    int deptChoice = Utils.inputInt("Wybierz operację: ");
                    if (deptChoice == 1) {
                        String newDeptName = Utils.inputString("Podaj nazwę nowego działu: ");
                        boolean added = company.addDepartment(newDeptName);
                        if (added) {
                            Utils.printAnswer("Dodano nowy dział: " + newDeptName);
                        } else {
                            Utils.printAnswer("Dział o tej nazwie już istnieje.");
                        }
                    } else if (deptChoice == 2) {
                        String removeDeptName = Utils.inputString("Podaj nazwę działu do usunięcia: ");
                        boolean removedDept = company.removeDepartment(removeDeptName);
                        if (removedDept) {
                            Utils.printAnswer("Usunięto dział: " + removeDeptName);
                        } else {
                            Utils.printAnswer("Nie znaleziono działu o nazwie: " + removeDeptName);
                        }
                    } else {
                        Utils.printAnswer("Nieprawidłowy wybór.");
                    }
                    break;
                case 8:
                    Utils.saveCompanyData(company, dataFileName);
                    break;
                case 9:
                    company = Utils.loadCompanyData(dataFileName);
                    if (company == null) {
                        company = Utils.initData();
                        Utils.printAnswer("Wczytano domyślne dane, ponieważ nie udało się odczytać pliku.");
                    }
                    break;
                case 0:
                    Utils.saveCompanyData(company, dataFileName);
                    Utils.printAnswer("Do zobaczenia");
                    done = true;
                    break;
                default:
                    Utils.printAnswer("Podano błędną wartość, spróbuj jeszcze raz");
            }
        }
    }
}
