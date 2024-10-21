package de.likeherotozero;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class TestBean {
    public String getMessage() {
        return "Hallo, CDI funktioniert!";
    }
}
