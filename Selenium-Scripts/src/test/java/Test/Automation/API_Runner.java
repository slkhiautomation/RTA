package Test.Automation;

import com.intuit.karate.junit4.Karate;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
@CucumberOptions(
        features = {"src/test/resources/Sprint_4/Functional_UI/API.feature"}
)
@RunWith(Karate.class)
public class API_Runner {
}
