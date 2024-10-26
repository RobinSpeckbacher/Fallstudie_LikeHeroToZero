package de.likeherotozero;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import jakarta.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class Co2Bean implements Serializable {

    private BarChartModel barModel;
    private String selectedCountry;
    private List<String> selectedCountries = new ArrayList<>();
    private List<Country> availableCountries;
    private List<EmissionsData> emissionsDataList = new ArrayList<>();
    private EmissionsData emissionsData; // Add this line

    @PersistenceContext(unitName = "my-persistence-unit")
    private EntityManager entityManager;


    @PostConstruct
    public void init() {
        availableCountries = loadAvailableCountries();
        loadEmissionsData();
        createBarModel();
    }

    @Transactional
    private void loadEmissionsData() {
        emissionsDataList = entityManager.createQuery("SELECT e FROM EmissionsData e", EmissionsData.class).getResultList();
    }

    private void createBarModel() {
        barModel = new BarChartModel();
        ChartSeries series = new ChartSeries();
        series.setLabel("CO2 Emission (Mio Tons)");

        for (String country : selectedCountries) {
            EmissionsData data = getEmissionsDataForCountry(country);
            if (data != null) {
                series.set(country, data.getValue());
            }
        }

        if (!series.getData().isEmpty()) {
            barModel.addSeries(series);
        }
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void handleCountryChange() {
        // Update emissionsData based on selectedCountry
        emissionsData = getEmissionsDataForCountry(selectedCountry);
        createBarModel(); // Optional: Update the bar model if you want it to change with the country selection
    }

    public void onItemSelect(ItemSelectEvent event) {
        // Existing method
    }

    private EmissionsData getEmissionsDataForCountry(String country) {
        return emissionsDataList.stream()
                .filter(data -> data.getCountry().equalsIgnoreCase(country))
                .findFirst().orElse(null);
    }

    private List<Country> loadAvailableCountries() {
        // Load unique countries from the emissions data
        List<Country> countries = entityManager.createQuery("SELECT DISTINCT e.country FROM EmissionsData e", String.class)
                .getResultList()
                .stream()
                .map(Country::new)
                .toList();
        return countries;
    }

    // Getters and Setters
    public List<String> getSelectedCountries() {
        return selectedCountries;
    }

    public void setSelectedCountries(List<String> selectedCountries) {
        this.selectedCountries = selectedCountries;
        createBarModel();
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public List<Country> getAvailableCountries() {
        return availableCountries;
    }

    public EmissionsData getEmissionsData() { // Add this getter
        return emissionsData;
    }
}
