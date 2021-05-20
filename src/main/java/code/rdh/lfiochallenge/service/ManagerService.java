package code.rdh.lfiochallenge.service;

import code.rdh.lfiochallenge.domain.Manager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ManagerService {

    private final RestTemplate restTemplate;
    private final String managersEndpoint;

    public ManagerService(RestTemplate restTemplate, String managersEndpoint) {
        this.restTemplate = restTemplate;
        this.managersEndpoint = managersEndpoint;
    }

    public List<Manager> getManagers() {
        Manager[] managersArray = restTemplate.getForObject(managersEndpoint, Manager[].class);
        if (managersArray == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(managersArray);
    }

}
