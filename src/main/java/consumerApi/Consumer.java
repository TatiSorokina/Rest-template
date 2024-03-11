package consumerApi;

import model.User;
import org.springframework.web.client.RestTemplate;
import service.UserService;
import service.UserServiceImpl;

public class Consumer {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        UserService userService = new UserServiceImpl(restTemplate);

        User user = new User (3L, "James", "Brown", (byte) 19);
        User user1 = new User (3L, "Thomas", "Shelby", (byte) 19);

        userService.showAll();

        System.out.println((userService.addUser(user) + userService.updateUser(user1) + userService.deleteUser(3L))
                .length());
    }
}