package com.react.utils;

import com.react.entity.Employee;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import static com.react.utils.EmployeeApiUtil.HASHICORP_SECRETS_ENDPOINT;

@Component
public class CommonApiUtils {

    private static final String EMPLOYEE_BY_ID = "/api/v1/employee/%d";

    private EmployeeApiUtil employeeApiUtil;
    RestTemplate restTemplate;

    public Employee getEmployeeByIdApi(int id) {
        String url = employeeApiUtil.getAPIUrl(EMPLOYEE_BY_ID, id);
        HttpEntity<Void> entity = employeeApiUtil.getHttpEntityUserIdHeaderAndBody(null);
        try {
            ResponseEntity<Employee> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    Employee.class);
            return response.getBody();

        } catch (HttpClientErrorException.NotFound e) {
            throw e;
        }

    }

    public Map<String, Pair<Boolean,String>> getHashiCorpValutSecrets(boolean forceRest, boolean restartApp) {
        String baseUrl =employeeApiUtil.getAPIUrlWithHashicorpServicePort(HASHICORP_SECRETS_ENDPOINT);
        String url = baseUrl +"?forceReset="+forceRest+"&restartApp="+restartApp;

        try {
            ResponseEntity<Map<String,Pair<Boolean,String>>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String,Pair<Boolean,String>>>() {
                    });
            return response.getBody();

        } catch (HttpClientErrorException.NotFound e) {
            throw e;
        }
    }

}
