package com.restapi.testcase;

import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restapi.utilities.MyXLSReader;
import com.restapi.utilities.ReadConfig;
import com.restapi.utilities.ReadXlsxFile;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TCAll_RestApi {

	ReadConfig config = new ReadConfig();
	String URL = config.getUrl();
	RequestSpecification httprequest;
	Response response;
	JSONObject data;
	MyXLSReader xlsreader;
	Logger log = LogManager.getLogger(TCAll_RestApi.class.getName());
	String sname = RandomStringUtils.randomAlphabetic(4);
	String sid = RandomStringUtils.randomNumeric(4);
	String sage = "25";
	String empid = "12";

	// @Test(priority = 1)
	public void getAllEmpRecords() throws InterruptedException {

		log.info("********** Started Test For GET Method ****************");
		RestAssured.baseURI = URL;
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET, "pet/findByStatus?status=available");
		Thread.sleep(5000);

		log.info("** Checking Get Response Body **");
		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody != null);
		// Assert.assertEquals(responsebody.contains("Fenrir"), true);

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

		// log.info("** Checking Get Content Length **");
		// String contentlength = response.header("Content-Length");
		// Assert.assertTrue(Integer.parseInt(contentlength)>1000);

	}

	@SuppressWarnings("unchecked")
	//@Test(priority = 2, dataProvider = "TestData")
	public void postNewEmpRecord(HashMap<String, String> hMap) throws InterruptedException {

		if (!ReadXlsxFile.isRunnable(xlsreader, "POST", "TestCases") || hMap.get("Runmode").equals("N")) {

			throw new SkipException("Test Case Skipped Due To Run Mode Set To N");
		}

		log.info("********** Started Test For Post Method ****************");
		RestAssured.baseURI = URL;
		httprequest = RestAssured.given();

		data = new JSONObject();
		data.put("id", hMap.get("ID"));
		data.put("username", hMap.get("username"));
		data.put("firstName", hMap.get("firstname"));
		data.put("email", hMap.get("email"));
		data.put("password", hMap.get("Password"));
		data.put("phone", hMap.get("Phone"));

		httprequest.header("Content-Type", "application/json");
		httprequest.body(data.toJSONString());

		response = httprequest.request(Method.POST, "v2/user");
		Thread.sleep(5000);

		log.info("** Checking Post Response Body **");
		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody.contains("200"));
		Assert.assertTrue(responsebody.contains("ID"));

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

		log.info("********** Started Test For Get Single Record Method ****************");
		RestAssured.baseURI = URL;
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET, "v2/user/" + hMap.get("username"));
		Thread.sleep(5000);

		log.info("** Checking Get Response Body **");
		String getresponsebody = response.getBody().asString();
		log.info(getresponsebody);
		Assert.assertTrue(getresponsebody.contains(hMap.get("ID")));
		Assert.assertTrue(getresponsebody.contains(hMap.get("username")));
		Assert.assertTrue(getresponsebody.contains(hMap.get("firstname")));
		Assert.assertTrue(getresponsebody.contains(hMap.get("email")));
		Assert.assertTrue(getresponsebody.contains(hMap.get("Password")));
		Assert.assertTrue(getresponsebody.contains(hMap.get("Phone")));

		log.info("** Checking Get Response Time **");
		long getresponsetime = response.getTime();
		Assert.assertTrue(getresponsetime < 5000);

		log.info("** Checking Get Status Code **");
		int getresponsecode = response.statusCode();
		Assert.assertEquals(getresponsecode, 200);

		log.info("** Checking Get Status Line **");
		String getresponsestatusline = response.statusLine();
		Assert.assertEquals(getresponsestatusline, "HTTP/1.1 200 OK");

		log.info("** Checking Get Content Type **");
		String getcontenttype = response.header("Content-Type");
		Assert.assertEquals(getcontenttype, "application/json");

		log.info("** Checking Get Server **");
		String getserver = response.header("Server");
		Assert.assertEquals(getserver, "Jetty(9.2.9.v20150224)");

		// log.info("** Checking Get Content Length **");
		// String contentlength = response.header("Content-Length");
		// Assert.assertTrue(Integer.parseInt(contentlength)>1000);

	}

	@DataProvider(name = "TestData")
	public Object[][] dataSupplier() {

		Object[][] data = null;
		try {
			String filepath = System.getProperty("user.dir") + "//TestDatas//DataDrivenTestRestApi.xlsx";
			xlsreader = new MyXLSReader(filepath);
			data = ReadXlsxFile.getTestData(xlsreader, "POST", "TestData");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@Test(priority = 3, dataProvider = "Data2")
	public void putEmpRecord(HashMap<String, String> hMap) throws InterruptedException {

		if (!ReadXlsxFile.isRunnable(xlsreader, "PUT", "TestCases") || hMap.get("Runmode").equals("N")) {

			throw new SkipException("Test Case Skipped Due To Run Mode Set To N");
		}

		log.info("********** Started Test For Put Method ****************");
		RestAssured.baseURI = URL;
		httprequest = RestAssured.given();

		data = new JSONObject();
		data.put("id", hMap.get("ID"));
		data.put("firstName", hMap.get("firstname"));

		httprequest.header("Content-Type", "application/json");
		httprequest.body(data.toJSONString());

		response = httprequest.request(Method.PUT, "v2/user/" + hMap.get("username"));
		Thread.sleep(5000);

		log.info("** Checking Put Response Body **");
		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody.contains("200"));
		log.info(responsebody);
		Assert.assertEquals(responsebody.contains(hMap.get("ID")), true);

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

		log.info("********** Started Test For Get Single Record Method ****************");
		RestAssured.baseURI = URL;
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET, "v2/user/" + hMap.get("username"));
		Thread.sleep(5000);

		log.info("** Checking Get Response Body **");
		String getresponsebody = response.getBody().asString();
		log.info(getresponsebody);
		Assert.assertTrue(getresponsebody.contains(hMap.get("ID")));
		Assert.assertTrue(getresponsebody.contains(hMap.get("firstname")));

		log.info("** Checking Get Response Time **");
		long getresponsetime = response.getTime();
		Assert.assertTrue(getresponsetime < 5000);

		log.info("** Checking Get Status Code **");
		int getresponsecode = response.statusCode();
		Assert.assertEquals(getresponsecode, 200);

		log.info("** Checking Get Status Line **");
		String getresponsestatusline = response.statusLine();
		Assert.assertEquals(getresponsestatusline, "HTTP/1.1 200 OK");

		log.info("** Checking Get Content Type **");
		String getcontenttype = response.header("Content-Type");
		Assert.assertEquals(getcontenttype, "application/json");

		log.info("** Checking Get Server **");
		String getserver = response.header("Server");
		Assert.assertEquals(getserver, "Jetty(9.2.9.v20150224)");

		// log.info("** Checking Get Content Length **");
		// String contentlength = response.header("Content-Length");
		// Assert.assertTrue(Integer.parseInt(contentlength)>1000);

	}

	@DataProvider(name = "Data2")
	public Object[][] dataSupplier2() {

		Object[][] data = null;
		try {
			String filepath = System.getProperty("user.dir") + "//TestDatas//DataDrivenTestRestApi.xlsx";
			xlsreader = new MyXLSReader(filepath);
			data = ReadXlsxFile.getTestData(xlsreader, "PUT", "TestData");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return data;
	}

	// @Test(priority = 6)
	public void deleteEmpRecord() throws InterruptedException {

		log.info("********** Started Test For Delete Method ****************");
		RestAssured.baseURI = URL;
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

		// log.info("** Checking Delete Content Length **");
		// String contentlength = response.header("Content-Length");
		// Assert.assertTrue(Integer.parseInt(contentlength) < 500);

	}

}
