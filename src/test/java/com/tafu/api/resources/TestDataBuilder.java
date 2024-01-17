package com.tafu.api.resources;

import java.util.ArrayList;

import com.tafu.api.pojoClasses.LocationCordination;
import com.tafu.api.pojoClasses.LocationDetails;

public class TestDataBuilder {
	public LocationDetails addPlacePayLoad(String name, String language, String address) {
		
		LocationDetails location_details = new LocationDetails();
		location_details.setAccuracy(50);
		location_details.setAddress(address);
		location_details.setName(name);
		location_details.setPhone_number("(+91) 983 893 3937");
		location_details.setWebsite("http://google.com");
		location_details.setLanguage(language);
		
		
		LocationCordination location_cordination = new LocationCordination();
		location_cordination.setLat(-38.383494);
		location_cordination.setLng(33.427362);
		location_details.setLocation(location_cordination);
		
		ArrayList<String> al = new ArrayList<String>();
		al.add("shoe park");
		al.add("shop");
		
		location_details.setTypes(al);
		return location_details;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\"place_id\":\""+placeId+"\"}";
	}

}
