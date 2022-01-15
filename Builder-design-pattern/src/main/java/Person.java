import java.util.OptionalInt;

public class Person {

    protected final String name;
    protected final String surname;
    private int age;
    private String address;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Person(String name, String surname, int age, String address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
    }

    public boolean hasAge() {
        return this.age == 0 ? false : true;
    }

    public boolean hasAdress() {
        return this.address.equals(null) ? false : true;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public OptionalInt getAge() {
        return OptionalInt.of(this.age);
    }

    public String getAddress() {
        return this.address;
    }

    public void setAdress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        this.age += 1;
    }

    PersonBuilder newChildBuilder() {
        PersonBuilder child = new PersonBuilder();
        child.setSurname(this.surname);
        child.setAge(0);
        if (!this.address.equals(null)) {
            child.setAddress(this.address);
        }
        return child;
    }

    @Override
    public String toString() {
        return "name: " + this.name +
                ", surname: " + this.surname +
                ", age: " + this.age +
                ", address: " + this.address;
    }

    @Override
    public int hashCode() {
        int result = 42;
        if (this.age == 0 && this.address.equals(null)) {
            result = 5 * result + this.name.hashCode() + this.surname.hashCode();
        }

        if (this.age == 0) {
            result = 5 * result + this.name.hashCode() + this.surname.hashCode() + this.address.hashCode();
        }
        if (this.address.equals(null)) {
            result = 5 * result + this.name.hashCode() + this.surname.hashCode() + this.age;
        }
        return result;
    }
}
