package de.likeherotozero;

import jakarta.persistence.*;

@Entity
@Table(name = "co2_emissionen", schema = "nutzer")

public class EmissionsData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "land")
    private String country;
    @Column (name = "co2_emissionen_mt")
    private double value;
    private int jahr;


    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public int getJahr() {
        return jahr;
    }
    public void setJahr(int jahr) {
        this.jahr = jahr;
    }
}
