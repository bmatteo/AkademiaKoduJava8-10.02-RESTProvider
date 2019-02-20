package pl.academy.code.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.academy.code.model.Country;
import pl.academy.code.model.GetAllCountriesResponse;
import pl.academy.code.model.GetCountryRequest;
import pl.academy.code.model.GetCountryResponse;

import java.util.*;

@RestController
@RequestMapping("/api")
public class CountryController {

    HashMap<String, Country> countriesRepository;

    public CountryController() {
        this.countriesRepository = new HashMap<>();

        Country france = new Country();
        france.setName("France");
        france.setCapital("Paris");
        france.setPopulation(65000000);

        Country poland = new Country();
        poland.setName("Poland");
        poland.setCapital("Warsaw");
        poland.setPopulation(38000000);

        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setPopulation(66000000);

        this.countriesRepository.put(france.getName(), france);
        this.countriesRepository.put(poland.getName(), poland);
        this.countriesRepository.put(uk.getName(), uk);
    }

    @RequestMapping(value = "/getCountryByName", method = RequestMethod.POST)
    public GetCountryResponse getCountryByName(@RequestBody GetCountryRequest request) {
        Country countryToSend = this.countriesRepository.get(request.getName());

        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryToSend);

        return response;
    }

    @RequestMapping(value = "/getAllCountries", method = RequestMethod.GET)
    public GetAllCountriesResponse getAllCountries() {
        List<Country> countryList = new ArrayList<>();

        Iterator i = this.countriesRepository.values().iterator();

        while (i.hasNext()) {
            countryList.add((Country) i.next());
        }

        GetAllCountriesResponse response = new GetAllCountriesResponse();
        response.setCountryList(countryList);

        return response;
    }
}
