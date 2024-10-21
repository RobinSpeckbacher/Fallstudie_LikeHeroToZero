package de.likeherotozero;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class LoginBean {
    private String username;
    private String password;
    private String message;

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

    public String getMessage() {
        return message;  // Return the message variable directly
    }

    public String login() {
        if ("admin".equals(username) && "password".equals(password)) { // To-do mit JPA und Hibernate Passwort managen
            message = "Login erfolgreich!";
            return "data-management?faces-redirect=true";  // Redirect to another page after successful login
        } else {
            message = "Ungültige Anmeldedaten.";
            return null;  // nutzer wissen lassen das falsche Daten
        }
    }
}
