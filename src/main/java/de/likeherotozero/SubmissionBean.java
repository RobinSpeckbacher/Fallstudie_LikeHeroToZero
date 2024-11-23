package de.likeherotozero;

import de.likeherotozero.Co2Data;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import jakarta.annotation.PostConstruct;
import java.util.List;

// wird verwendet um Daten aus dem JSF Formular zu empfangen
@Named
@RequestScoped
public class SubmissionBean {

    private String country;
    private Integer year;
    private Double co2Emissions;

    @Inject
    private Co2DataService co2DataService;

    // Länderliste

    private List<String> countries;

    // automatisch initialisieren bei laden der Seite

    @PostConstruct
    public void init() {
        try {
            this.countries = co2DataService.getAllCountries(); // Fetch countries and assign them to the list
        } catch (Exception e) {
            e.printStackTrace(); // For debugging
            throw new RuntimeException("Error in init()", e);
        }
    }

    // Methode zum Speichern der Daten

    public String submitData() {
        Co2Data data = new Co2Data();
        data.setLand(country);
        data.setJahr(year);
        data.setCo2EmissionenMt(co2Emissions);

        co2DataService.save(data);
        return "success.xhtml?faces-redirect=true";
    }

    // Getter und Setter
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(Double co2Emissions) {
        this.co2Emissions = co2Emissions;
    }
    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

}

