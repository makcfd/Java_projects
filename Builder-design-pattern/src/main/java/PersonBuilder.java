public class PersonBuilder {

    private String name;
    private String surname;
    private int age;
    private String address;

    //private PersonBuilder personBuilder = new PersonBuilder();

    public PersonBuilder setName(String name) {
        //PersonBuilder personBuilder = new PersonBuilder();
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) throws IllegalArgumentException {
        if (age <0 || age > 120) {
            throw new IllegalArgumentException("Passed age value is incorrect. Value should be between 0 and 120");
        } else {
        this.age = age;
        return this; }
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person build() throws IllegalStateException{

        if (this.name == null || this.surname == null) {
            throw new IllegalStateException("Compulsory data is missing. Please check that name and surname have been provided.");
        } else {
        Person person = new Person(this.name,
                                    this.surname,
                                    this.age,
                                    this.address);
        return person; }
    }


}
