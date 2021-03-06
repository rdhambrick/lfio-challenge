package code.rdh.lfiochallenge.api;

import code.rdh.lfiochallenge.domain.Employee;
import code.rdh.lfiochallenge.domain.Manager;
import code.rdh.lfiochallenge.service.EmployeeService;
import code.rdh.lfiochallenge.service.ManagerService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ManagerService managerService;
    private final EmployeeService employeeService;

    public ApiController(ManagerService managerService, EmployeeService employeeService) {
        this.managerService = managerService;
        this.employeeService = employeeService;
    }

    @GetMapping("/supervisors")
    public List<String> getSupervisors() {
        return managerService.getManagers().stream()
                .filter(manager -> !Character.isDigit(manager.getJurisdiction()))
                .sorted(Comparator
                        .comparing(Manager::getJurisdiction)
                        .thenComparing(Manager::getLastName)
                        .thenComparing(Manager::getFirstName)
                )
                .map(manager -> manager.getJurisdiction() + " - " + manager.getLastName() + ", " + manager.getFirstName())
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/submit", consumes = "application/json")
    public void postSubmit(@RequestBody Employee employee) {
        employeeService.printEmployee(employee);
    }

}
