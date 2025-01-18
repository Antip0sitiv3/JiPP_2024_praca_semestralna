package App;

public enum Departament {

    SALES("Sprzedaż"),
    ADMINISTRATION("Administracja"),
    FINANCE("Finanse");

    private String name;

    Departament(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}