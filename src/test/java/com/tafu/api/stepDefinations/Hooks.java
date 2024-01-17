package com.tafu.api.stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
PlaceSearchAPIStepsDefination step = new PlaceSearchAPIStepsDefination();
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		if(PlaceSearchAPIStepsDefination.placeId==null)
		{
		step.user_has_Add_Place_Payload_with("Shakti", "English", "Bangaolre");
		step.user_calls_with_http_request("addPlaceAPI", "POST");
		step.verify_place_id_created_maps_to_using("Shakti", "getPlaceAPI");
		}
	}
}
