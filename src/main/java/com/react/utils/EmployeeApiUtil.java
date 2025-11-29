package com.react.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@Slf4j
public class EmployeeApiUtil {

    private static final int GATEWAY_SERVER_PORT = 32100;
    private static final int HASHICORP_SERVER_PORT = 32100;
    @Value("${server.ssl.enabled}")
    private boolean sslEnabled;
    private Environment env;

    private static final String EMPLOYEE_BY_ID = "/api/v1/employee/%d";
    public static final String HASHICORP_SECRETS_ENDPOINT = "/api/v1/hashicorp/accessAndRotate";

    public String getAPIUrl(String endpoint, Object... args) {
        String baseUrl = isDefaultProfileActive() || isLocalProfileActive() ? "localhost" : getCurrentServerHostName();
        String schema = sslEnabled ? "https" : "http";
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(schema)
                .host(baseUrl)
                .port(GATEWAY_SERVER_PORT);
        String formattedEndPoint = String.format(endpoint, args);
        return builder.path(formattedEndPoint).toUriString();
    }

    public boolean isLocalProfileActive() {
        for (String profileName : env.getActiveProfiles()) {
            if (profileName.equalsIgnoreCase("local")) {
                return true;
            }
        }
        return false;
    }

    public boolean isDefaultProfileActive() {
        return env.getActiveProfiles().length == 0;
    }

    public String getCurrentServerHostName() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String hostName = inetAddress.getHostName();
            if (!hostName.contains(".")) {
                hostName = inetAddress.getCanonicalHostName();
            }
            log.info("Applicatiopn current sERVER nODE IS : {}", hostName);
            return hostName;

        } catch (UnknownHostException e) {
            log.info("UnknownHostException Occur while accessing current host name: {}", e);
        }

        return null;
    }

    public <T> HttpEntity<T> getHttpEntityUserIdHeaderAndBody(T body) {
        String userId = "system-user";
        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id", userId);
        return new HttpEntity<>(body, headers);
    }


    public String getUserIdFroSecurityContext() {
       // Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return String.valueOf(null);
    }


    public String getAPIUrlWithHashicorpServicePort(String endpoint, Object... args) {
        return getAPIUrl(endpoint, args).replace(String.valueOf(GATEWAY_SERVER_PORT),
                String.valueOf(HASHICORP_SERVER_PORT));
    }


}