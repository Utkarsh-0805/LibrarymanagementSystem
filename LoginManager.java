package manager;

import java.util.List;
import model.User;

public class LoginManager {
    public User login(String username, String password, List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
