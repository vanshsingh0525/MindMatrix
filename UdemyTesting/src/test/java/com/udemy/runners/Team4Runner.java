package com.udemy.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.udemy.stepdefinitions.Team4",
                "com.udemy.hooks"
        },
        tags = "@SearchFunctionality or @FilterValidation or @CourseValidation",
        plugin = {
                "pretty",
                "html:test-output/reports/Team4/cucumber-report.html",
                "json:test-output/reports/Team4/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false
)
public class Team4Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
