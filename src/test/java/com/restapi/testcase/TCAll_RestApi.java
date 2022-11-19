package com.restapi.testcase;

import org.apache.commons.lang3.RandomStringUtils;
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
	String sname = RandomStringUtils.randomAlphabetic(4);
	String ssalary = RandomStringUtils.randomNumeric(4);
	String sage = "25";
	String empid = "12";

	@Test(priority = 1)
	public void getAllEmpRecords() throws InterruptedException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET, "employees");
		Thread.sleep(5000);

		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody != null);

		long responsetime = response.getTime();
		Assert.assertTrue(responsetime < 6000);

		int responsecode = response.statusCode();
		Assert.assertEquals(responsecode, 200);

		String responsestatusline = response.statusLine();
		Assert.assertEquals(responsestatusline, "HTTP/1.1 200 OK");

		String contenttype = response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json");

		String server = response.header("Server");
		Assert.assertEquals(server, "nginx/1.21.6");

		// String contentlength = response.header("Content-Length");
		// Assert.assertTrue(Integer.parseInt(contentlength)>1000);

	}

	@SuppressWarnings("unchecked")
	@Test(priority = 2)
	public void postNewEmpRecord() throws InterruptedException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
		httprequest = RestAssured.given();

		data = new JSONObject();
		data.put("name", sname);
		data.put("salary", ssalary);
		data.put("age", sage);

		httprequest.header("Content-Type", "application/json");
		httprequest.body(data.toJSONString());

		response = httprequest.request(Method.POST, "create");
		Thread.sleep(5000);

		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody.contains(sname));
		Assert.assertTrue(responsebody.contains(ssalary));
		Assert.assertTrue(responsebody.contains(sage));

		long responsetime = response.getTime();
		Assert.assertTrue(responsetime < 6000);

		int responsecode = response.statusCode();
		Assert.assertEquals(responsecode, 200);

		String responsestatusline = response.statusLine();
		Assert.assertEquals(responsestatusline, "HTTP/1.1 200 OK");

		String contenttype = response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json");

		String server = response.header("Server");
		Assert.assertEquals(server, "Apache");

		String contentlength = response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentlength) < 500);

	}

	 @Test(priority = 3)
	public void getSingleEmpRecord() throws InterruptedException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET, "employee/" + empid);
		Thread.sleep(5000);

		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody.contains(responsebody));

		long responsetime = response.getTime();
		Assert.assertTrue(responsetime < 6000);

		int responsecode = response.statusCode();
		Assert.assertEquals(responsecode, 200);

		String responsestatusline = response.statusLine();
		Assert.assertEquals(responsestatusline, "HTTP/1.1 200 OK");

		String contenttype = response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json");

		String server = response.header("Server");
		Assert.assertEquals(server, "nginx/1.21.6");

		// String contentlength = response.header("Content-Length");
		// Assert.assertTrue(Integer.parseInt(contentlength)>1000);

	}

	@SuppressWarnings("unchecked")
    @Test(priority = 4)
	public void putEmpRecord() throws InterruptedException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
		httprequest = RestAssured.given();

		data = new JSONObject();
		data.put("name", sname);
		data.put("salary", ssalary);
		data.put("age", sage);

		httprequest.header("Content-Type", "application/json");
		httprequest.body(data.toJSONString());

		response = httprequest.request(Method.PUT, "update/" + empid);
		Thread.sleep(5000);

		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody.contains(responsebody));

		long responsetime = response.getTime();
		Assert.assertTrue(responsetime < 6000);

		int responsecode = response.statusCode();
		Assert.assertEquals(responsecode, 200);

		String responsestatusline = response.statusLine();
		Assert.assertEquals(responsestatusline, "HTTP/1.1 200 OK");

		String contenttype = response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json");

		String server = response.header("Server");
		Assert.assertEquals(server, "Apache");

		String contentlength = response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentlength) < 500);

	}

	@Test(priority = 5)
	public void deleteEmpRecord() throws InterruptedException {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.DELETE, "delete/" + empid);
		Thread.sleep(5000);

		String responsebody = response.getBody().asString();
		Assert.assertEquals(responsebody.contains("Successfully! Record has been deleted"), true);

		long responsetime = response.getTime();
		Assert.assertTrue(responsetime < 6000);

		int responsecode = response.statusCode();
		Assert.assertEquals(responsecode, 200);

		String responsestatusline = response.statusLine();
		Assert.assertEquals(responsestatusline, "HTTP/1.1 200 OK");

		String contenttype = response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json");

		String server = response.header("Server");
		Assert.assertEquals(server, "Apache");

		String contentlength = response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentlength) < 500);

	}

}
