package Test.Automation.Stepdefinitions;

import Test.Automation.Utils.DriverFactory;
import Test.Automation.Utils.PropertyReader;
import Test.Automation.Utils.REST_GET_Action;
import cucumber.api.java.en.Given;

import java.sql.SQLException;

public class APIStepDefs extends DriverFactory {


    public APIStepDefs() throws SQLException {
    }

    @Given("^I should see response code \"([^\"]*)\" for the requested API \"([^\"]*)\"$")
    public void i_should_see_response_code_for_the_requested_API(String http_code, String Endpoint) throws Throwable {

        String url = (new PropertyReader().readProperty(Endpoint));
        System.out.println(url);
        REST_GET_Action.GETRequest(url);
        REST_GET_Action.getStatusCode(http_code);
    }

    @Given("^I should see response for the requested API \"([^\"]*)\" using Request Body \"([^\"]*)\"$")
    public void i_should_see_response_for_the_requested_API_using_Request_Body(String EndPoint, String RequestBody) throws Throwable {
        String url = (new PropertyReader().readProperty(EndPoint));
        ResponseID = REST_GET_Action.POSTRequest(url,RequestBody);
    }
}
