package code.rdh.lfiochallenge.domain;

public class Manager {

    private final int id;
    private final String phone;
    private final char jurisdiction;
    private final String identificationNumber;
    private final String lastName;
    private final String firstName;

    public Manager(
            int id,
            String phone,
            char jurisdiction,
            String identificationNumber,
            String lastName,
            String firstName
    ) {
        this.id = id;
        this.phone = phone;
        this.jurisdiction = jurisdiction;
        this.identificationNumber = identificationNumber;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public char getJurisdiction() {
        return jurisdiction;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

}
