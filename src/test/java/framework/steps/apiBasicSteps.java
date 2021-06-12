package framework.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class apiBasicSteps {
    private static Response response;

    @When("I get from endpoint {string}")
    public void i_get_from_endpoint(String endpoint) {
        RequestSpecification request = RestAssured.given();
        RestAssured.baseURI = endpoint;
        response = request.get(endpoint);
    }

    @Then("the response status is {int}")
    public void the_response_status_is(long expectedCode) {
        Assert.assertEquals(expectedCode, response.getStatusCode());
    }

    @Then("the JSON response contains {string} {string}")
    public void the_JSON_response_contains(String key, String expected) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String actual = jsonPathEvaluator.get(key);
        Assert.assertEquals(expected, actual);
    }

    @Then("the XML response contains {string} {string} {string}")
    public void theXMLResponseContains(String nodeKey, String attributeKey, String expected) {
        XmlPath xmlPath = new XmlPath(response.asString());
        Assert.assertEquals(expected, xmlPath.getString(nodeKey + ".@" + attributeKey));
    }
}
