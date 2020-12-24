package Test.Automation.Utils;

import java.io.*;
import java.io.IOException;

import static Test.Automation.Utils.ExcelFileManager.readFromCell;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.commons.io.IOUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

public class REST_GET_Action {

    static Response GETResponse;
    static Response POSTResponse;

    public static Response GETRequest(String APIUrl) throws IOException
    {
        System.out.println(APIUrl);
        GETResponse=
                given().
                when().
                get(APIUrl);
        ResponseBody body = GETResponse.getBody();
        System.out.println(body.asString());
        return GETResponse;
    }

    public static String POSTRequest(String APIUrl,String requestBody) throws IOException {
        String resposneID = "";
        FileInputStream postBody = new FileInputStream(new File(	"src/test/resources/Request/"+requestBody+".json"));
        POSTResponse=
                given().header("Content-Type","application/json").
                        relaxedHTTPSValidation().
                        and().body(IOUtils.toString(postBody,"UTF-8")).
                        when().post(APIUrl);
        ResponseBody ResponseBody = POSTResponse.getBody();
        System.out.println(ResponseBody.asString());
        JsonPath jsonPathValidator = POSTResponse.jsonPath();
        String resultFormat = readFromCell("TestData","resultFormat",1,"sampleName");
        System.out.println(resultFormat);
        String result = jsonPathValidator.getString(resultFormat);
        System.out.println(result);

        return result;
    }

    public static void getStatusCode(String code) throws IOException

    {
        int get_response = GETResponse.getStatusCode();
        System.out.println(get_response);
        int status= Integer.parseInt(code);
        System.out.println(status);
        assertEquals(get_response,status);

    }
}
