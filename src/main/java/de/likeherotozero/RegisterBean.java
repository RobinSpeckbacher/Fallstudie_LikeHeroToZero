package de.likeherotozero;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.mindrot.jbcrypt.BCrypt;

@Named
@RequestScoped
public class RegisterBean {
    private String username;
    private String password;
    private String email; // Neues Feld für die E-Mail-Adresse


    @Inject
    private UserRepository userRepository;

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

    public String getEmail() { // Getter für die E-Mail-Adresse
        return email;
    }

    public void setEmail(String email) { // Setter für die E-Mail-Adresse
        this.email = email;
    }



    public String register() {
        if (userRepository.findByUsername(username) != null) {
            return null;  // Keine Weiterleitung, da Registrierung fehlschlägt
        } else {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            newUser.setEmail(email); // E-Mail-Adresse speichern
            userRepository.saveUser(newUser);
            return "login?faces-redirect=true";  // Weiterleitung zur Login-Seite
        }
    }



}
