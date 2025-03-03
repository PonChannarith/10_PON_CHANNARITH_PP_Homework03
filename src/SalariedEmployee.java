public class SalariedEmployee extends StaffMember {
    static int idCounter = 8;
    private double salary;
    private double bonus;

    public SalariedEmployee(String name, String address, double salary, double bonus) {
        super(idCounter++, name, address);
        this.salary = salary;
        this.bonus = bonus;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    @Override
    public String getName() {
        String name = null;

        return name;
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public static void setIdCounter(int idCounter) {
        SalariedEmployee.idCounter = idCounter;
    }

    @Override
    public void setName(String name) {
        this.setName(name);
    }

    @Override
    public double pay() {
        return 0;
    }

    @Override
    public double getRate() {
        return 0;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        String name = null;
        return "SalariedEmployee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", bonus=" + bonus +
                '}';
    }
}