package ge.gamp.learningandroid.data.model;

public class Programmer {
    private int id;
    private String name;
    private String occupation;
    private int age;
    private int salary;
    private String bio;
    private String icon;

    public Programmer() {
    }

    public Programmer(int id, String name, String occupation, int age, int salary, String bio, String icon) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
        this.age = age;
        this.salary = salary;
        this.bio = bio;
        this.icon = icon;
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

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public String getBio() {
        return bio;
    }

    public String getIcon() {
        return icon;
    }
}
