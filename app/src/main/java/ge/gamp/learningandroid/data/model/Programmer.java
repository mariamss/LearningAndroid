package ge.gamp.learningandroid.data.model;

public class Programmer {
    private int id;
    private String name;
    private String occupation;
    private int age;
    private int salary;

    public Programmer() {
    }

    public Programmer(int id, String name, String occupation, int age, int salary) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
