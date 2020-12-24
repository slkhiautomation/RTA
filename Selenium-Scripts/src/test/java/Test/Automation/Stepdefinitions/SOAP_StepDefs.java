package Test.Automation.Stepdefinitions;

import Test.Automation.Utils.DriverFactory;
import Test.Automation.Utils.PropertyReader;
import Test.Automation.Utils.SOAP_Action;
import Test.Automation.Utils.UtilityMethods;
import cucumber.api.java.en.Given;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SOAP_StepDefs extends SOAP_Action  {

    @Given("^Create Ticet with Order$")
    public void create_Ticet_with_Order() throws Throwable {
        String endPoint = new PropertyReader().readProperty("SOAP_EndPoint");
        InputStream msg = new FileInputStream(new File("src/test/resources/Sprint_4/Request/Ticket_Creation.xml"));
        SOAPMessage soapRequest = MessageFactory.newInstance().createMessage(null,msg);
        String message = soapMessageToString(soapRequest);
//        System.out.println(message);
//        sendSoapRequest(endPoint,soapRequest);
        sendSoapRequests(endPoint,soapRequest);
    }

    @Given("^Navigate to Google$")
    public void navigate_to_Google() throws Throwable {
        System.out.println("Pass");
    }

    @Given("^Pass the Value to Google$")
    public void pass_the_Value_to_Google() throws Throwable {

    }

}
