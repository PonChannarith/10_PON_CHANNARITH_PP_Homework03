public class HourlySalaryEmployee extends StaffMember {
    private int hoursWorked;
    private double rate;

    public HourlySalaryEmployee(String name, String address, int hoursWorked, double rate) {
        super(idCounter++, name, address);
        this.hoursWorked = hoursWorked;
        this.rate = rate;
    }

    private static int getNextId() {
        return 0;
    }

    @Override
    public double getRate() {
        return rate;
    }


    @Override
    public double pay() {
        return hoursWorked * rate;  // Fix unnecessary '+'
    }

    @Override
    public String toString() {
        return super.toString() + ", Hours Worked: " + hoursWorked + ", Rate: $" + rate + ", Total Pay: $" + pay();
    }

    public char[] getHoursWorked() {
        return new char[0];
    }

    public void setRate(double salary) {
    }

    public Object getSalary() {
        return null;
    }

    public void setHoursWorked(int hoursWorked) {
    }
}
