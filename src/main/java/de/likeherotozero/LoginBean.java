package de.likeherotozero;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.mindrot.jbcrypt.BCrypt;


@Named
@RequestScoped
public class LoginBean {
    private String username;
    private String password;
    private String email; // Neues Feld für die E-Mail-Adresse
    private String message;

    @Inject
    private UserRepository userRepository; // Repository für die Datenbankoperationen

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() { // Getter für die E-Mail
        return email;
    }

    public void setEmail(String email) { // Setter für die E-Mail
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    // Anmeldemethode
    public String login() {
        User user = userRepository.findByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            message = "Login erfolgreich!";
            return "data-management?faces-redirect=true";
        } else {
            message = "Ungültige Anmeldedaten.";
            return null;
        }
    }

    // Registrierungs-Methode
    public String register() {
        if (userRepository.findByUsername(username) != null) {
            message = "Benutzername bereits vergeben.";
            return null; // Benutzername ist bereits vergeben
        }

        // Hier könntest du auch eine Validierung für die E-Mail-Adresse hinzufügen

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email); // E-Mail-Adresse speichern
        newUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt())); // Passwort hashen
        userRepository.saveUser(newUser); // Neuen Benutzer speichern
        message = "Registrierung erfolgreich!";
        return null; // Nach der Registrierung auf derselben Seite bleiben
    }
}
