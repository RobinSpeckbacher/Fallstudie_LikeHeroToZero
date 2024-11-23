package de.likeherotozero;

import jakarta.persistence.*;

//repräsentiert Datenbank Entitäten
@Entity
@Table(name = "co2_emissionen")
public class Co2Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "land", nullable = false)
    private String land;

    @Column(name = "jahr", nullable = false)
    private Integer jahr;

    @Column(name = "co2_emissionen_mt", nullable = false)
    private Double co2EmissionenMt;

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public Integer getJahr() {
        return jahr;
    }

    public void setJahr(Integer jahr) {
        this.jahr = jahr;
    }

    public Double getCo2EmissionenMt() {
        return co2EmissionenMt;
    }

    public void setCo2EmissionenMt(Double co2EmissionenMt) {
        this.co2EmissionenMt = co2EmissionenMt;
    }
}
