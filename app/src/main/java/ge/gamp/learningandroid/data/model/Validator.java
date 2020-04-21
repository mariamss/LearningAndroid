package ge.gamp.learningandroid.data.model;

public class Validator {
    String name;
    String occupation;
    int age;
    int salary;

    public Validator(String name,String occupation, int salary, int age){
        this.name=name;
        this.occupation= occupation;
        this.age =age;
        this.salary=salary;

    }
    public Boolean isProgrammerDataValid() {
        if(name.isEmpty() || occupation.isEmpty() || age==0 || salary==0){
            return false;
        }
        return true;
    }
}
