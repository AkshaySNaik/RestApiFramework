package com.restapi.testcase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TCAll_RestApi {

	RequestSpecification httprequest;
	Response response;
	JSONObject data;
	Logger log = LogManager.getLogger(TCAll_RestApi.class.getName());
	String sname = RandomStringUtils.randomAlphabetic(4);
	String sid = RandomStringUtils.randomNumeric(4);
	String sage = "25";
	String empid = "12";

	@Test(priority = 1)
	public void getAllEmpRecords() throws InterruptedException {

		log.info("********** Started Test For GET Method ****************");
		RestAssured.baseURI = "https://petstore.swagger.io/v2/";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET, "pet/findByStatus?status=available");
		Thread.sleep(5000);

		log.info("** Checking Get Response Body **");
		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody != null);
		Assert.assertEquals(responsebody.contains("Fenrir"),true);

		log.info("** Checking Get Response Time **");
		long responsetime = response.getTime();
		Assert.assertTrue(responsetime < 5000);

		log.info("** Checking Get Status Code **");
		int responsecode = response.statusCode();
		Assert.assertEquals(responsecode, 200);

		log.info("** Checking Get Status Line **");
		String responsestatusline = response.statusLine();
		Assert.assertEquals(responsestatusline, "HTTP/1.1 200 OK");

		log.info("** Checking Get Content Type **");
		String contenttype = response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json");

		log.info("** Checking Get Server **");
		String server = response.header("Server");
		Assert.assertEquals(server, "Jetty(9.2.9.v20150224)");

		//log.info("** Checking Get Content Length **");
		// String contentlength = response.header("Content-Length");
		// Assert.assertTrue(Integer.parseInt(contentlength)>1000);

	}


	@SuppressWarnings("unchecked")
	@Test(priority = 2)
	public void postNewEmpRecord() throws InterruptedException {

		log.info("********** Started Test For Post Method ****************");
		RestAssured.baseURI = "https://petstore.swagger.io/v2/";
		httprequest = RestAssured.given();

		data = new JSONObject();
		data.put("id", sid);
		data.put("name", sname);
		
		httprequest.header("Content-Type", "application/json");
		httprequest.body(data.toJSONString());

		response = httprequest.request(Method.POST, "pet");
		Thread.sleep(5000);
		
		log.info("** Checking Post Response Body **");
		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody.contains(sid));
		Assert.assertTrue(responsebody.contains(sname));

		log.info("** Checking Post Response Time **");
		long responsetime = response.getTime();
		Assert.assertTrue(responsetime < 5000);

		log.info("** Checking Post Status Code **");
		int responsecode = response.statusCode();
		Assert.assertEquals(responsecode, 200);

		log.info("** Checking Post Status Line **");
		String responsestatusline = response.statusLine();
		Assert.assertEquals(responsestatusline, "HTTP/1.1 200 OK");

		log.info("** Checking Post Content Type **");
		String contenttype = response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json");

		log.info("** Checking Post Server **");
		String server = response.header("Server");
		Assert.assertEquals(server, "Jetty(9.2.9.v20150224)");

		log.info("** Checking Post access-control-allow-origin **");
		String contentlength = response.header("access-control-allow-origin");
		Assert.assertTrue(contentlength.contains("*"));

	}

	@Test(priority = 3)
	public void getSingleEmpRecord() throws InterruptedException {

		log.info("********** Started Test For Get Single Record Method ****************");
		RestAssured.baseURI = "https://petstore.swagger.io/v2/";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET, "pet/" + sid);
		Thread.sleep(5000);

		log.info("** Checking Get Response Body **");
		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody.contains(sid));
		Assert.assertTrue(responsebody.contains(sname));

		log.info("** Checking Get Response Time **");
		long responsetime = response.getTime();
		Assert.assertTrue(responsetime < 4000);

		log.info("** Checking Get Status Code **");
		int responsecode = response.statusCode();
		Assert.assertEquals(responsecode, 200);

		log.info("** Checking Get Status Line **");
		String responsestatusline = response.statusLine();
		Assert.assertEquals(responsestatusline, "HTTP/1.1 200 OK");

		log.info("** Checking Get Content Type **");
		String contenttype = response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json");

		log.info("** Checking Get Server **");
		String server = response.header("Server");
		Assert.assertEquals(server, "Jetty(9.2.9.v20150224)");

		//log.info("** Checking Get Content Length **");
		// String contentlength = response.header("Content-Length");
		// Assert.assertTrue(Integer.parseInt(contentlength)>1000);

	}

	@SuppressWarnings("unchecked")
    @Test(priority = 4)
	public void putEmpRecord() throws InterruptedException {

		log.info("********** Started Test For Put Method ****************");
		RestAssured.baseURI = "https://petstore.swagger.io/v2/";
		httprequest = RestAssured.given();

		data = new JSONObject();
		data.put("id", sid);
		data.put("name", sname);
		data.put("status", "available");

		httprequest.header("Content-Type", "application/json");
		httprequest.body(data.toJSONString());

		response = httprequest.request(Method.PUT, "pet");
		Thread.sleep(5000);

		log.info("** Checking Put Response Body **");
		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody.contains("available"));

		log.info("** Checking Put Response Time **");
		long responsetime = response.getTime();
		Assert.assertTrue(responsetime < 5000);

		log.info("** Checking Put Status Code **");
		int responsecode = response.statusCode();
		Assert.assertEquals(responsecode, 200);

		log.info("** Checking Put Status Line **");
		String responsestatusline = response.statusLine();
		Assert.assertEquals(responsestatusline, "HTTP/1.1 200 OK");

		log.info("** Checking Put Content Type **");
		String contenttype = response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json");

		log.info("** Checking Put Server **");
		String server = response.header("Server");
		Assert.assertEquals(server, "Jetty(9.2.9.v20150224)");

		//log.info("** Checking Put Content Length **");
		//String contentlength = response.header("Content-Length");
		//Assert.assertTrue(Integer.parseInt(contentlength) < 500);

	}

	@Test(priority = 5)
	public void deleteEmpRecord() throws InterruptedException {

		log.info("********** Started Test For Delete Method ****************");
		RestAssured.baseURI = "https://petstore.swagger.io/v2/";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.DELETE, "pet/" + sid);
		Thread.sleep(5000);

		log.info("** Checking Delete Response Body **");
		String responsebody = response.getBody().asString();
		Assert.assertEquals(responsebody.contains(sid), true);
		Assert.assertTrue(responsebody.contains("200"));

		log.info("** Checking Delete Response Time **");
		long responsetime = response.getTime();
		Assert.assertTrue(responsetime < 6000);

		log.info("** Checking Delete Status Code **");
		int responsecode = response.statusCode();
		Assert.assertEquals(responsecode, 200);

		log.info("** Checking Delete Status Line **");
		String responsestatusline = response.statusLine();
		Assert.assertEquals(responsestatusline, "HTTP/1.1 200 OK");

		log.info("** Checking Delete Content Type **");
		String contenttype = response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json");

		log.info("** Checking Delete Server **");
		String server = response.header("Server");
		Assert.assertEquals(server, "Jetty(9.2.9.v20150224)");

		//log.info("** Checking Delete Content Length **");
		//String contentlength = response.header("Content-Length");
		//Assert.assertTrue(Integer.parseInt(contentlength) < 500);

	}

}
