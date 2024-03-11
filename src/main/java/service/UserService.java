package service;

import model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<User[]> showAll();
    String addUser(User user);
    String updateUser(User user);
    String deleteUser(Long id);
}
