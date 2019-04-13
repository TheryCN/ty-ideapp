package com.github.therycn.tyideapp;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Cucumber junit runner.
 * 
 * @author THERY
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty" }, features = "classpath:features/")
public class RunCucumberTest {

}
