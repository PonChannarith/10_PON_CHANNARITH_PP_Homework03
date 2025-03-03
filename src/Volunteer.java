public class Volunteer extends StaffMember {
    static int idCounter = 8;
    private double salary;

    public Volunteer(String name, String address, double salary) {
        super(idCounter++,name, address);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary){
        this.salary =salary;
    }
    public static void setIdCounter(int idCounter) {
        Volunteer.idCounter = idCounter;
    }

    @Override
    public double pay() {
        return salary;
    }

    @Override
    public double getRate() {
        return 0;
    }

}