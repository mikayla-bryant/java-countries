package com.lambdaschool.webcountries.controllers;

import com.lambdaschool.webcountries.models.Country;
import com.lambdaschool.webcountries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryRepository countryrepos;

    //http://localhost:2022/names/all
    @GetMapping(value="/names/all", produces = {"application/json"})
    public ResponseEntity<?> listAllCountries()
    {
        List<Country> myList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(myList::add);
        myList.sort((c1, c2)-> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(myList, HttpStatus.OK);

    }

    //http://localhost:2022/names/start/u


    //http://localhost:2022/population/total

    //http://localhost:2022/population/min

    //http://localhost:2022/population/max

}
