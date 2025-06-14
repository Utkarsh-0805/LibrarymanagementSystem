import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class LoginManager {
    private HashMap<String, String> userDatabase = new HashMap<>();

    public LoginManager() {
        // Simulated user database with hashed passwords
        // Passwords: user1 -> "password123", admin -> "admin123"
        userDatabase.put("user1", hashPassword("password123"));
        userDatabase.put("admin", hashPassword("admin123"));
    }

    public boolean login() {
        Console console = System.console();
        if (console == null) {
            System.out.println("Console not available. Please run from terminal.");
            return false;
        }

        System.out.print("Enter username: ");
        String username = console.readLine();

        char[] passwordChars = console.readPassword("Enter password: ");
        String password = new String(passwordChars);

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username or password cannot be empty.");
            return false;
        }

        String hashedInputPassword = hashPassword(password);
        String storedHashedPassword = userDatabase.get(username);

        if (storedHashedPassword != null && storedHashedPassword.equals(hashedInputPassword)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
