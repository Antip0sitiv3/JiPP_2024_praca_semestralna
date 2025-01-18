package App;

import java.io.Serializable;

public class Employee extends Person implements Serializable {
    private String department;

    public Employee(Person person, String department) {
        super(person.getName(), person.getSurname());
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return super.toString() + ", Dział: " + department;
    }
}
