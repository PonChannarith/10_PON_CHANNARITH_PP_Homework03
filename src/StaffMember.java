public abstract class StaffMember {
    static int idCounter = 8;
    private int id;
    private String name;
    private String address;

    public StaffMember(int i, String name, String address) {
        this.id = idCounter++;  // Auto-incrementing unique ID
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public abstract double pay();

    public abstract double getRate();

    @Override
    public String toString() {
        return "StaffMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}