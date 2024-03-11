package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService{

    private final RestTemplate restTemplate;
    private String sessionId;
    private String url = "http://94.198.50.185:7081/api/users/";

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<User[]> showAll(){
        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);
        User[] users = response.getBody();
        for (User user1 : users){
            System.out.println(user1.toString());
        }
        System.out.println();
        sessionId = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        return ResponseEntity.ok(users);
    }

    public String addUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionId);
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();
    }

    public String updateUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionId);
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
        return response.getBody();
    }

    public String deleteUser(Long id) {
        String urlDelete = url + id;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionId);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(urlDelete, HttpMethod.DELETE, request, String.class);
        return response.getBody();
    }

}
