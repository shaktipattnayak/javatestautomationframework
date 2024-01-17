package com.tafu.api.features;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import com.tafu.baseSetup.SetupHelper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIHelper {
	public static RequestSpecification req_spec;
	public static JsonPath js;
	public static RequestSpecification requestSpecification() throws IOException {
		if(req_spec == null) {
		PrintStream logstream = new PrintStream(new FileOutputStream("APILog.log"));
		req_spec = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl")).addQueryParam("key", "qaclick123").
				setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(logstream))
				.addFilter(ResponseLoggingFilter.logResponseTo(logstream)).build();
		return req_spec;
		}
		return req_spec;
	}
	
	public static String getGlobalValues(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(SetupHelper.getUserDirectory()+"/Resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public static String getJsonPath(Response response, String key) {
		String response_json = response.asString();
	    js = new JsonPath(response_json);
	    return js.get(key).toString();
	}
}
