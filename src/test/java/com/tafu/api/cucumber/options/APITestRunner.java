package com.tafu.api.cucumber.options;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "/Users/shakti/eclipse-workspace/BDDAPIFramework/src/test/java/features",
		glue={"com.tafu.api.stepDefinations"},
		strict = true,
		dryRun = false,
		monochrome = true,
		plugin ="json:target/jsonReports/cucumber-report.json"
		//tags= {"@Regression"}
		)
public class APITestRunner {

}
