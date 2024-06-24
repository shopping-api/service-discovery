package com.generoso.ft.sd.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(
    classes = {TestConfiguration.class})
@CucumberContextConfiguration
public class CucumberSpringConfiguration {
}