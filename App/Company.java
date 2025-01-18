package App;

import java.io.Serializable;
import java.util.*;

public class Company implements Serializable {
    private String name;
    private Address address;
    private final List<Employee> employees;
    private final Set<String> departments;

    public Company(String name) {
        this.name = name;
        this.address = new Address("Miasto", "Ulica");
        this.employees = new ArrayList<>();
        this.departments = new HashSet<>(Arrays.asList("SALES", "ADMINISTRATION", "FINANCE"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(String name, String surname, String department) {
        if (!departments.contains(department.toUpperCase())) {
            throw new IllegalArgumentException("Nieznany dział: " + department);
        }
        Person person = new Person(name, surname);
        Employee employee = new Employee(person, department.toUpperCase());
        employees.add(employee);
    }

    public boolean removeEmployee(String surname) {
        return employees.removeIf(e -> e.getSurname().equalsIgnoreCase(surname));
    }

    public List<Employee> searchEmployeesByLastName(String surname) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSurname().equalsIgnoreCase(surname)) {
                result.add(employee);
            }
        }
        return result;
    }

    public boolean editEmployeeData(String surname) {
        for (Employee employee : employees) {
            if (employee.getSurname().equalsIgnoreCase(surname)) {
                Scanner input = new Scanner(System.in);
                System.out.println("Podaj nowe imię: ");
                String newName = input.nextLine();
                System.out.println("Podaj nowe nazwisko: ");
                String newSurname = input.nextLine();
                System.out.println("Podaj nowy dział: ");
                String newDept = input.nextLine();
                if (!departments.contains(newDept.toUpperCase())) {
                    System.out.println("Nieznany dział: " + newDept);
                    return false;
                }
                employee.setName(newName);
                employee.setSurname(newSurname);
                employee.setDepartment(newDept.toUpperCase());
                return true;
            }
        }
        return false;
    }

    public Map<String, Integer> getDepartmentStats() {
        Map<String, Integer> stats = new HashMap<>();
        for (String department : departments) {
            stats.put(department, 0);
        }
        for (Employee employee : employees) {
            stats.put(employee.getDepartment(), stats.get(employee.getDepartment()) + 1);
        }
        return stats;
    }

    public boolean addDepartment(String name) {
        return departments.add(name.toUpperCase());
    }

    public boolean removeDepartment(String name) {
        if (departments.remove(name.toUpperCase())) {
            employees.removeIf(e -> e.getDepartment().equalsIgnoreCase(name));
            return true;
        }
        return false;
    }

    public Set<String> getDepartmentNames() {
        return departments;
    }
}
