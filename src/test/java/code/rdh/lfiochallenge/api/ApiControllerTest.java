package code.rdh.lfiochallenge.api;

import code.rdh.lfiochallenge.domain.Manager;
import code.rdh.lfiochallenge.service.EmployeeService;
import code.rdh.lfiochallenge.service.ManagerService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

class ApiControllerTest {

    @Test
    public void getSupervisors_WhenProvidedManager_ResponseIsFormattedCorrectly() {
        ManagerService mockManagerService = Mockito.mock(ManagerService.class);
        when(mockManagerService.getManagers()).thenReturn(
                Arrays.asList(
                        new Manager(1, "", 'a', "", "last", "first")
                )
        );
        ApiController controller = new ApiController(mockManagerService, Mockito.mock(EmployeeService.class));

        String expected = "a - last, first";
        String actual = controller.getSupervisors().get(0);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getSupervisors_WhenProvidedUnsortedManagers_ResponseIsSortedByJurisdictionLastFirst() {
        ManagerService mockManagerService = Mockito.mock(ManagerService.class);
        when(mockManagerService.getManagers()).thenReturn(
                Arrays.asList(
                        new Manager(1, "", 'z', "", "lz", "fz"),
                        new Manager(2, "", 'a', "", "lab", "fax"),
                        new Manager(3, "", 'a', "", "lab", "fab"),
                        new Manager(4, "", 'a', "", "lax", "fa")
                )
        );
        ApiController controller = new ApiController(mockManagerService, Mockito.mock(EmployeeService.class));

        List<String> supervisors = controller.getSupervisors();
        Assertions.assertTrue(supervisors.get(0).endsWith("b"));
        Assertions.assertTrue(supervisors.get(1).endsWith("x"));
        Assertions.assertTrue(supervisors.get(2).endsWith("a"));
        Assertions.assertTrue(supervisors.get(3).endsWith("z"));
    }

    @Test
    public void getSupervisors_WhenProvidedNumericJurisdictions_ResponseDoesNotIncludeNumericJurisdictions() {
        ManagerService mockManagerService = Mockito.mock(ManagerService.class);
        when(mockManagerService.getManagers()).thenReturn(
                Arrays.asList(
                        new Manager(1, "", 'a', "", "", ""),
                        new Manager(2, "", '1', "", "", ""),
                        new Manager(3, "", 'b', "", "", ""),
                        new Manager(4, "", '2', "", "", ""),
                        new Manager(5, "", 'c', "", "", "")
                )
        );
        ApiController controller = new ApiController(mockManagerService, Mockito.mock(EmployeeService.class));

        List<String> supervisors = controller.getSupervisors();
        supervisors.forEach(supervisorString -> Assertions.assertFalse(Character.isDigit(supervisorString.charAt(0))));
    }

}
