package com.cognizant.spring_learn.service;

import com.cognizant.spring_learn.Country;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    public Country getCountry(String code) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");

        Country country = context.getBean("country", Country.class);

        if (country.getCode().equalsIgnoreCase(code)) {
            ((ClassPathXmlApplicationContext) context).close();
            return country;
        }

        ((ClassPathXmlApplicationContext) context).close();

        return null;
    }
}