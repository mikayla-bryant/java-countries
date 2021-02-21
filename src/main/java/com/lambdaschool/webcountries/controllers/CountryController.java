package com.lambdaschool.webcountries.controllers;

import com.lambdaschool.webcountries.models.Country;
import com.lambdaschool.webcountries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class CountryController {

    @Autowired
    private CountryRepository countryrepos;

    private List<Country> findCountries(List<Country> myList, CheckCountry tester)
    {
        List<Country> tempList = new ArrayList<>();
        for (Country c : myList)
        {
            if(tester.test(c))
            {
                tempList.add(c);
            }
        }
        return tempList;
    }

    //http://localhost:2022/names/all ✔
    @GetMapping(value="/names/all", produces = {"application/json"})
    public ResponseEntity<?> listAllCountries()
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);
        myList.sort((c1, c2)-> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(myList, HttpStatus.OK);

    }

    //http://localhost:2022/names/start/u ✔
    @GetMapping(value="/names/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> listAllByName(@PathVariable char letter)
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add); // creates array of all countries
        List<Country> rtnList = findCountries(myList, c -> c.getName().toLowerCase().charAt(0) == letter);
        // creating temporary list
        // calling method that takes a list and a lambda expression
        // lambda expression gets name of each country, then checks if the first letter is equal to the path variable
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    //http://localhost:2022/population/total

    //http://localhost:2022/population/min

    //http://localhost:2022/population/max

}
