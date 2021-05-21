package code.rdh.lfiochallenge.domain;

import java.util.Objects;
import java.util.Optional;

public class Employee {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final int supervisorId;

    public Employee(
            String firstName,
            String lastName,
            String email,
            String phone,
            int supervisorId
    ) {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = email;
        this.phone = phone;
        if (supervisorId <= 0) {
            throw new IllegalArgumentException("supervisorId must be positive.");
        }
        this.supervisorId = supervisorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public Optional<String> getPhone() {
        return Optional.ofNullable(phone);
    }

    public int getSupervisorId() {
        return supervisorId;
    }

}
