package com.tafu.api.stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import com.tafu.api.features.APIHelper;
import com.tafu.api.resources.TestDataBuilder;
import com.tafu.api.resources.APIResources;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class PlaceSearchAPIStepsDefination {
	RequestSpecification req_spec;
	ResponseSpecification res_spec;
	Response response_json;
	TestDataBuilder data_builder = new TestDataBuilder();
	static String placeId;
	
	@Given("user has Add Place Payload with {string} {string} {string}")
	public void user_has_Add_Place_Payload_with(String name, String language, String address)  throws IOException {
		res_spec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		req_spec = given().log().all().spec(APIHelper.requestSpecification()).body(data_builder.addPlacePayLoad(name, language, address));
	}

	@When("user calls {string}  with {string} http request")
	public void user_calls_with_http_request(String resource, String http_method) {
		APIResources.valueOf(resource).getResource();
		if(http_method.equalsIgnoreCase("post"))
			response_json = req_spec.when().post(APIResources.valueOf(resource).getResource());
		else if(http_method.equalsIgnoreCase("get"))
			response_json = req_spec.when().get(APIResources.valueOf(resource).getResource());
	}

	@Then("the API call is success with response code {int}")
	public void the_API_call_is_success_with_response_code(Integer int1) {
		assertEquals(response_json.getStatusCode(), 200);	
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String expectedKey, String exceptedValue) {
	    assertEquals(APIHelper.getJsonPath(response_json, expectedKey), exceptedValue);
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String exceptedname, String resource) throws IOException {

		placeId = APIHelper.getJsonPath(response_json, "place_id");
		req_spec = given().spec(APIHelper.requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource, "get");
		String actname = APIHelper.getJsonPath(response_json, "name");
		assertEquals(exceptedname, actname);
	}

	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		req_spec = given().spec(APIHelper.requestSpecification()).body(data_builder.deletePlacePayload(placeId));
	}

}
