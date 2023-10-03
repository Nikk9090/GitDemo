package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// To run this tests, in cucumber, we need to run SubmitOrder.feature file. For that we are writing some standard code 
//here in Runner file.

//With help of this @CucumberOptions we will get more helper attributes to run the test.
//@CucumberOptions will take 4 parameters.
//1. "features" --> where we need to provide .feature file location
//2. "glue" --> where we need to provide stepDefination file location. so that during mapping it will go to features and map it.
//3. "monochrome" --> to print the results in readable format.
//4. "plugin" --> generate record of html plugin. So the after test complition their will be one folder generates, "target/cucumber.html", in that records will be store.

@CucumberOptions(features="src/test/java/cucumber", glue="rahulshettyacademy.stepDefinations", monochrome = true, plugin= {"html:target/cucumber.html"}) 
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
//Cucumber is not able to scan testNG libraries, that is why we extends AbstractTestNGCucumberTests class.
//With help of this class Cucumber can scan the testNG libraries.
}
