package code.rdh.lfiochallenge.service;

import code.rdh.lfiochallenge.domain.Employee;
import java.io.OutputStream;
import java.io.PrintStream;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final PrintStream out;

    public EmployeeService(OutputStream employeeInformationOutputStream) {
        this.out = new PrintStream(employeeInformationOutputStream);
    }

    public void printEmployee(Employee employee) {
        StringBuilder stringBuilder = new StringBuilder("Employee information received - ")
                .append("Name: ")
                .append(employee.getFirstName())
                .append(" ")
                .append(employee.getLastName())
                .append(" ");

        employee.getEmail().ifPresent(email -> {
            stringBuilder
                    .append("Email: ")
                    .append(email)
                    .append(" ");
        });

        employee.getPhone().ifPresent(phone -> {
            stringBuilder
                    .append("Phone: ")
                    .append(phone)
                    .append(" ");
        });

        stringBuilder
                .append("Supervisor ID: ")
                .append(employee.getSupervisorId());

        out.println(stringBuilder.toString());
    }
}
