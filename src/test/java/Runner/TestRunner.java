package Runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(  
		plugin = {"pretty"},
		features= "src//test//java//resources//features//login.feature",
		glue = {"StepDefination"}
		) 

 
public class TestRunner {

	
	
	
}