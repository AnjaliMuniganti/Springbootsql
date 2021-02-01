package com.ytp.springbootsql.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
//import com.github.tomakehurst.wiremock.client.WireMock;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsDefinition {
	
private static final int PORT=8080;
private static final String HOST="localhost";

private static WireMockServer server=new WireMockServer(PORT);

//	private static WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());

	HttpHeaders headers=new HttpHeaders(); 
	String addURI;

	@Autowired 
	private RestTemplate restTemplate;
	private ResponseEntity<String> response;
	private String responseBodyPOST;
	private String responseBody;

	@Before
	public static void setup() {
		server.start();
		
//		configureFor("localhost", wireMockServer.port());
//		stubFor(post(urlEqualTo("/create"))
//		  .withHeader("content-type", equalTo("application/json"))
//		  .withRequestBody(containing("testing-framework"))
//		  .willReturn(aResponse().withStatus(200)));
//		ResponseDefinitionBuilder mockResponse=new ResponseDefinitionBuilder();
//		mockResponse.withStatus(200);
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//
//		HttpPost request = new HttpPost("http://localhost:" + wireMockServer.port() + "/create");
//		String jsonString;
//		StringEntity entity = new StringEntity(jsonString);
//		request.addHeader("content-type", "application/json");
//		request.setEntity(entity);
//		HttpResponse response = (HttpResponse) httpClient.execute(request);
//		
//		assertEquals(200, response.getStatusLine().getStatusCode());
//		verify(postRequestedFor(urlEqualTo("/create"))
//		  .withHeader("content-type", equalTo("application/json")));
//	}
//		
//private static void configureFor(String string, int port2) {
//		// TODO Auto-generated method stub
//		
//	}
//
//private static ResponseDefinitionBuilder aResponse() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//private static Object containing(String string) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//private static Object urlEqualTo(String string) {
//		// TODO Auto-generated method stub
//		return null;
//	}
		ResponseDefinitionBuilder mockResponse=new ResponseDefinitionBuilder();
		mockResponse.withStatus(200);
		WireMock.configureFor(HOST,PORT);
		WireMock.stubFor(WireMock.post("/flightInformation").willReturn(mockResponse));
	}
	@Given("I set POST flight service api endpoint")
	public void i_set_POST_flight_service_api_endpoint() {
		//addURI = "http://dummy.restapiexample.com/api/v1/create";
		addURI="http://localhost:8080/flight";
		System.out.println("Add URL :"+addURI);
	}

	@When("I set request HEADER")
	public void i_set_request_HEADER() {
		headers.add("Accept","application/json");
		headers.add("Content-Type","application/json");
	}

	@And("send a POST HTTP request")
	public void send_a_POST_HTTP_request() {
		String jsonBody="[{\"name\":\"Sushma\",\"source\":\"Hyderabad\",\"destination\":\"Mumbai\"}]";
		System.out.println("\n\n" + jsonBody);
		HttpEntity<String>entity = new HttpEntity<String>(jsonBody, headers);       
		//POST Method to Add New Flight Info
		restTemplate = new RestTemplate ();
		response = restTemplate.postForEntity(addURI + "/flightInformation", entity, String.class);
	}

	@Then("I receive a valid Response")
	public void i_receive_a_valid_Response() {
		responseBodyPOST = response.getBody();
		// Write response to file
		responseBody = response.getBody().toString();
		System.out.println("responseBody --->" + responseBody);
		// Get ID from the Response object
		String id = getFlightIdFromResponse(responseBody);
		System.out.println("flightId is :" + id);

		// Check if the status code is 201
		Assert.assertEquals(response.getStatusCode(),HttpStatus.OK);
		System.out.println("Flight info successfully flightId:"+id);    
	}

	private String getFlightIdFromResponse(String responseBody) {
		return responseBody;
	}


	@When ("^Send a Get HTTP request$")
	public void send_a_get_http_request() {
		headers.add("Accept","application/json");
		headers.add("Content-Type","application/json");
		this.restTemplate = new RestTemplate ();
		this.response = this.restTemplate.getForEntity(addURI+"/getInfo",String.class);
		System.out.println("\n\n" + response.getBody());
		System.out.println("flight details Retrieved");
	}

	@Then("^The flight info is Removed$")
	public void The_flight_info_is_removed() {
		this.restTemplate = new RestTemplate ();
		this.restTemplate.delete(addURI+"/delete/28");
		System.out.print("flight info Removed");
	}
	
//	@After
//	public static void teardown() {
//		if(null !=server && server.isRunning()) {
//			server.shutdownServer();
//		}
//	}
}



