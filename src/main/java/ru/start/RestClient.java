package ru.start;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import ru.db.entities.CallHistory;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vitaly on 10/03/15.
 *
 * used just for test
 */
@Deprecated
public class RestClient {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/calls/create?number={number}";
        HttpHeaders headers = new HttpHeaders();

        // headers.add("Authorization", "Basic bG9sOjFxYUBXUzNlZA=="); //here is some login and pass like this login:pass
        headers.add("x-auth-token", "ea662b30c8dd0262626d23b45b3c3443");
        HttpEntity<String> request = new HttpEntity<String>(headers);
        Map<String, String> map = new HashMap<String, String>();
        map.put("user", "lol");
        map.put("number", "89192192");
        CallHistory body = restTemplate.exchange(url, HttpMethod.GET, request, CallHistory.class, map).getBody();
        System.out.println(body.toString());

        url = "http://localhost:8080/api/hello/misha";
        String body1 = restTemplate.exchange(url, HttpMethod.GET, request, String.class, map).getBody();
        System.out.println(body1);

    }
}
